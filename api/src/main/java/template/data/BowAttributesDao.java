/*
package template.data;

import com.yahoo.miners.bow.common.*;
import template.core.GenericDao;
import template.core.SegmentStatus;
import template.util.Logger;
import template.util.StringHelper;
import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 1/15/13
 * Time: 3:29 PM
 * BowAttributes segment related database operations.
 *//*

public class BowAttributesDao implements GenericDao<BowAttributes> {

    @Language("Oracle")
    static final String SUBSEG_DELETE =
            "DELETE FROM CAT_BOW_SUBSEGMENT " +
                    "WHERE CATEGORY_ID = ?";

    @Language("Oracle")
    static final String FEATURE_TYPE_GETID_BY_NAME =
            "SELECT CAT_BOW_FEATURE_TYPE_ID " +
                    "  FROM CAT_BOW_FEATURE_TYPE" +
                    " WHERE CAT_BOW_FEATURE_TYPE_NAME = ?";


    @Language("Oracle")
    static final String GET_FEATURE_BY_ID =
            "SELECT CAT_BOW_FEATURE_ID FROM CAT_BOW_FEATURE_DEFINITION WHERE HASH_VALUE=?";

    @Override
    public void create(final BowAttributes in) throws Exception {
        Connection connection = in.getConnection();
        upsertSegmentBowPart(connection, in);
    }

    private void upsertSegmentBowPart(Connection connection, BowAttributes in) throws Exception {
        SegmentDef segmentDef = BowLanguageHelper.get().extract(in.getExprNode(), in);
        segmentDef.getExpandedSegmentDefString();

        // Step 1: delete existing sub segment
        deleteSubSegment(connection, in);

        // Step 2: Upsert feature definitions
        Map<String, String> featureIdMap = mergeFeatureDefinitions(connection, in, segmentDef);

        // Step 3: Upsert segment without transformed definition
        mergeSegment(connection, in, segmentDef, featureIdMap);

        // Step 4: Insert sub segments
        Map<String, String> subSegIdMap = insertSubSegments(connection, in.getId().toLong(), segmentDef, featureIdMap, in.getLogInfo().getLastModifiedUser().getUserName());

        //Step 5: replace subsegid in the segment and merge the segment def.
        replaceSubsegmentIds(segmentDef, subSegIdMap);

        //Step 6: update the transformed segment definition.
        insertSegmentTransformedDef(in.getId().toLong(), segmentDef.getTransformedSegDef().toString(), in.getLogInfo().getLastModifiedUser().getUserName(), connection);

        //Step 7: deactive unused features.
        // disable this since data model changed by Pritik
//        deactiveFeature(connection, in.getLogInfo().getLastModifiedUser().getUserName());

        if (segmentDef.getNestedSegments() != null && !segmentDef.getNestedSegments().isEmpty()) {
            //Step 8: Save the expanded segment definition in the database
            insertDefinition(in.getId().toLong(), segmentDef.getExpandedSegmentDefString(), BowSegmentType.EXPANDED, in.getLogInfo().getLastModifiedUser().getUserName(), connection);

            //Step 9: update the nested segment mapping table to keep a track of any nested segment that the current segment has
            updateNestedSegmentMapping(connection, in.getId().toLong(), segmentDef.getNestedSegments(), in.getLogInfo().getLastModifiedUser().getUserName());
        }

        //Step 10: Check if the current segment is referred by any other segment. If it is, then re process those segments;
        reprocessReferringSegments(connection, in.getId().toLong(), in);

    }

    @Language("Oracle")
    static final String INSERT_TRANSFORM_DEF = "INSERT INTO " +
            "CAT_BOW_ATTR_DATA " +
            "(CAT_BOW_ATTR_DATA_ID," +
            "CATEGORY_ID," +
            "CREATED_BY," +
            "CREATED_TIME," +
            "SEQUENCE_NUMBER," +
            "DEFINITION_STRING,LAST_MODIFIED_BY," +
            "LAST_MODIFIED_TIME,STATUS," +
            "TYPE) " +
            "VALUES (CAT_BOW_ATTR_DATA_SEQ.NEXTVAL,?,?,SYSDATE,?,?,?,SYSDATE,1,1)";

    private void insertSegmentTransformedDef(long segmentId, String segmentDefStringTransformed, String creator, Connection con) throws SQLException {
        try (PreparedStatement s = con.prepareStatement(
                INSERT_TRANSFORM_DEF)) {

            List<String> list = StringHelper.splitBySize(segmentDefStringTransformed, MAX_LENGTH_OF_VARCHAR2);
            //Transformed string, to be updated.
            int seq = 1;
            for (String item : list) {
                int i = 1;
                s.setLong(i++, segmentId);
                s.setString(i++, creator);
                s.setLong(i++, seq++);
                s.setString(i++, item);
                s.setString(i, creator);
                s.addBatch();
            }
            s.executeBatch();
        }
    }

    private void replaceSubsegmentIds(SegmentDef segmentDef, Map<String, String> subSegIdMap) throws Exception {
        // this codes guarantee at least one sub-segment exist in the feature definition
        ReplaceIdVisitor subsegIdReplaceVisitor = new ReplaceIdVisitor((HashMap<String, String>) subSegIdMap);
        segmentDef.getTransformedSegDef().accept(subsegIdReplaceVisitor);
    }

    @Language("Oracle")
    static final String INSERT_SUB_SEGMENT = "INSERT INTO CAT_BOW_SUBSEGMENT " +
            "(CAT_BOW_SUBSEGMENT_ID " +
            ",CATEGORY_ID " +
            ",CAT_BOW_FEATURE_ID " +
            ",DEFINITION " +
            ",CREATED_BY " +
            ",LAST_MODIFIED_BY) " +
            "VALUES " +
            "(CAT_BOW_SUBSEGMENT_SEQ.NEXTVAL " +
            ",? " +
            ",? " +
            ",? " +
            ",? " +
            ",?) ";

    @Language("Oracle")
    static final String GET_SUBSEGMENT_ID = "SELECT CAT_BOW_SUBSEGMENT_SEQ.CURRVAL AS SUBSEGMENTID FROM DUAL";

    private Map<String, String> insertSubSegments(Connection con, long segmentId, SegmentDef segmentDef, Map<String, String> featureIdMap, String creator) throws Exception {
        Map<String, String> subSegIdMap = new HashMap<>();
        try (PreparedStatement stmtInsert = con.prepareStatement(INSERT_SUB_SEGMENT);
             PreparedStatement stmtGetSubSegId = con.prepareStatement(GET_SUBSEGMENT_ID)) {
            ReplaceIdVisitor featureIdReplaceVisitor = new ReplaceIdVisitor((HashMap<String, String>) featureIdMap);
            for (SubSegmentDef subSegDef : segmentDef.getSubSegments()) {
                //Replace the feature id.
                subSegDef.getDefinitionNode().accept(featureIdReplaceVisitor);
                stmtInsert.setLong(1, segmentId);
                //Feature Id
                stmtInsert.setLong(2, Long.parseLong(subSegDef.getFeatureDef().getFeatureId()));
                //Definition String
                stmtInsert.setString(3, subSegDef.getDefinitionNode().toString());
                stmtInsert.setString(4, creator);
                stmtInsert.setString(5, creator);
                stmtInsert.executeUpdate();
                ResultSet rsGetId = stmtGetSubSegId.executeQuery();
                if (rsGetId.next()) {
                    String subSegId = rsGetId.getString(1);
                    subSegIdMap.put(subSegDef.getId(), subSegId);
                } else {
                    Logger.error(" No corresponding sub segment id returned.");
                    throw new SQLException("Cannot toLong sub segment id from database");
                }
            }
        }
        return subSegIdMap;
    }

    private void mergeSegment(Connection conn, BowAttributes in, SegmentDef segmentDef, Map<String, String> featureIdMap) throws SQLException {
        String originalDefinitionStr = segmentDef.getOriginalString();
        String creator = in.getLogInfo().getLastModifiedUser().getUserName();
        long id = in.getId().toLong();
        if (isSegmentExist(in.getId().toLong(), conn)) {
            updateCurrentSegment(in, originalDefinitionStr, conn);
        } else {
            insertSegmentWithoutTransDef(id, originalDefinitionStr, creator, conn);
        }
    }

    private void insertSegmentWithoutTransDef(long id, String originalDefinitionStr, String creator, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO CAT_BOW_ATTR " +
                        "(CATEGORY_ID,CREATED_BY,CREATED_TIME,LAST_MODIFIED_BY,LAST_MODIFIED_TIME,STATUS) " +
                        "VALUES (?,?,SYSDATE,?,SYSDATE,1)")) {
            int i = 1;
            stmt.setLong(i++, id);
            stmt.setString(i++, creator);
            stmt.setString(i, creator);
            stmt.executeUpdate();
        }
        //insert into CAT_BOW_ATTR_DATA
        insertDefinition(id, originalDefinitionStr, BowSegmentType.ORIGINAL, creator, conn);
    }

    private void updateCurrentSegment(BowAttributes in, String originalDefinitionStr, Connection conn) throws SQLException {
        //update CAT_BOW_ATTR
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE CAT_BOW_ATTR SET LAST_MODIFIED_BY=?, LAST_MODIFIED_TIME=SYSDATE WHERE CATEGORY_ID=?")) {
            stmt.setString(1, in.getLogInfo().getLastModifiedUser().getUserName());
            stmt.setLong(2, in.getId().toLong());
            stmt.executeUpdate();
        }

        //update CAT_BOW_ATTR_DATA, first delete than insert
        try (PreparedStatement stmtDel = conn.prepareStatement("DELETE CAT_BOW_ATTR_DATA WHERE CATEGORY_ID=?")) {
            stmtDel.setLong(1, in.getId().toLong());
            stmtDel.executeUpdate();
            insertDefinition(in.getId().toLong(), originalDefinitionStr, BowSegmentType.ORIGINAL, in.getLogInfo().getLastModifiedUser().getUserName(), conn);
        }
    }

    private void insertDefinition(long segmentId, String definitionStr, BowSegmentType type, String lastModify, Connection conn) throws SQLException {
        // split original definition if it is bigger than 4k(max size of Oracle varchar2)
        List<String> list = StringHelper.splitBySize(definitionStr, MAX_LENGTH_OF_VARCHAR2);
        try (PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO CAT_BOW_ATTR_DATA " +
                        "(CAT_BOW_ATTR_DATA_ID, CATEGORY_ID, CREATED_BY,CREATED_TIME,SEQUENCE_NUMBER,DEFINITION_STRING,LAST_MODIFIED_BY,LAST_MODIFIED_TIME,STATUS,TYPE)" +
                        "VALUES (CAT_BOW_ATTR_DATA_SEQ.NEXTVAL,?,?,SYSDATE,?,?,?,SYSDATE,1,?)")) {
            int seq = 1;
            for (String item : list) {
                int i = 1;
                insert.setLong(i++, segmentId);
                insert.setString(i++, lastModify);
                insert.setInt(i++, seq++);
                insert.setString(i++, item);
                insert.setString(i++, lastModify);
                insert.setInt(i++, type.getValue());
                insert.addBatch();
            }
            insert.executeBatch();
        }
    }

    private boolean isSegmentExist(long segmentId, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM CAT_BOW_ATTR WHERE CATEGORY_ID=?")) {
            stmt.setLong(1, segmentId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Language("Oracle")
    static final String GET_FEATURE_DEF_ID_BY_HASH = "SELECT CAT_BOW_FEATURE_ID FROM CAT_BOW_FEATURE_DEFINITION WHERE HASH_VALUE=?";

    private Map<String, String> mergeFeatureDefinitions(Connection connection, BowAttributes in, SegmentDef
            segmentDef) throws SQLException {
        int featureTypeId = -1;
        Map<String, String> featureDefIdMap = new HashMap<>();
        try (PreparedStatement psGetFeatureTypeId = connection.prepareStatement(FEATURE_TYPE_GETID_BY_NAME);
             PreparedStatement psGetIdByHash = connection.prepareStatement(GET_FEATURE_DEF_ID_BY_HASH)) {
            for (FeatureDef featureDef : getFeatureDefs(segmentDef)) {
                psGetFeatureTypeId.setString(1, featureDef.getFeatureType().getFeatureType().trim());
                ResultSet rs = psGetFeatureTypeId.executeQuery();
                if (rs.next()) {
                    featureTypeId = rs.getInt(1);
                } else
                    throw new SQLException("Not found feature type id.");
                String featureDefString = featureDef.getFeatureDefString();
                String hashValue = StringHelper.shaHex(featureDefString);
                long featureId = getFeatureById(hashValue, connection);
                if (featureId != 0)
                    updateFeatureDefinition(connection, featureId, in);
                else
                    insertNewFeatureDefinition(featureDefString, hashValue, featureTypeId, in, connection);
                psGetIdByHash.setString(1, hashValue);
                ResultSet rsGetFeatureId = psGetIdByHash.executeQuery();

                if (rsGetFeatureId.next()) {
                    String fId = rsGetFeatureId.getString(1);
                    featureDefIdMap.put(featureDef.getFeatureId(), fId);
                    featureDef.setFeatureId(fId);
                    upsertFeatures(connection, fId, featureTypeId, featureDef.getFeatures());
                } else {
                    Logger.error("SegmentDefinitionDBAccessor::mergeFeatureDefs -  Could not find the corresponding feature id for def string " + featureDefString + ", hash value " + hashValue);
                    throw new SQLException("Could not toLong feature id for def string " + featureDefString + ", hash value " + hashValue);
                }
            }
        }
        return featureDefIdMap;
    }

   */
/* static final String FEATURE_ATTRIBUTE_GET_BY_FEATURE_TYPE_AND_NAME =
            "SELECT CAT_BOW_ATTRIBUTE_ID FROM CAT_BOW_FEATURE_ATTRIBUTE " +
                    "WHERE CAT_BOW_FEATURE_TYPE_ID = ? " +
                    "AND ATTRIBUTE_TYPE_NAME = ?";

    private static final String GET_FEATURES_BY_VALUE_ATTR = "SELECT CAT_BOW_ATTRIBUTE_VALUE_ID FROM CAT_BOW_FEATURE_ATTR_VAL WHERE VALUE = ? AND CAT_BOW_ATTRIBUTE_ID = ?";*//*


    private static final String MERGE_FEATURES = "{call bow_merge_features(?,?,?)}"; // call must be lower case

    private void upsertFeatures(Connection connection, String fId, int featureTypeId, List<FAttrValuePairNode> features) throws SQLException {
        OracleConnection oracleConnection;
        if (connection.isWrapperFor(OracleConnection.class))
            oracleConnection = connection.unwrap(OracleConnection.class);
        else
            oracleConnection = (OracleConnection) connection;
        StructDescriptor structDescriptor = StructDescriptor.createDescriptor("FEATURE_ATTR_VALUE_PAIR_NODE", oracleConnection);  // Must be upper case
        ArrayDescriptor featureAttrValuesDesc = ArrayDescriptor.createDescriptor("FEATURE_ATTR_VALUES", oracleConnection);
        ArrayDescriptor bowAttrValuesTblDesc = ArrayDescriptor.createDescriptor("BOW_ATTR_VALUES_TBL", oracleConnection);
        Object[] nodesList = new Object[features.size()];
        for (int i = 0; i < features.size(); ++i) {
            Object[] node = new Object[4];
            node[0] = features.get(i).getFeatureName();
            node[1] = features.get(i).getAttrName();
            node[2] = features.get(i).getOperator();
            Object[] values = new Object[features.get(i).getValue().length];
            for (int j = 0; j < features.get(i).getValue().length; ++j) {
                values[j] = features.get(i).getValue()[j];
            }
            node[3] = new ARRAY(featureAttrValuesDesc, oracleConnection, values);
            nodesList[i] = new STRUCT(structDescriptor, oracleConnection, node);
        }

        try (CallableStatement callableStatement = oracleConnection.prepareCall(MERGE_FEATURES)) {
            callableStatement.setObject(1, new ARRAY(bowAttrValuesTblDesc, oracleConnection, nodesList), Types.ARRAY);
            callableStatement.setLong(2, Long.valueOf(fId));
            callableStatement.setLong(3, featureTypeId);
            callableStatement.execute();
        }

     */
/*   int attributeId = 0;

        try (PreparedStatement getAttrId = connection.prepareStatement(FEATURE_ATTRIBUTE_GET_BY_FEATURE_TYPE_AND_NAME);
             PreparedStatement getFeatures = connection.prepareStatement(GET_FEATURES_BY_VALUE_ATTR)) {

            getAttrId.setInt(1, featureTypeId);
            List<String> notInDbValues = new ArrayList<String>();
            List<Integer> idsOfValuesInDb = new ArrayList<Integer>();

            for (FAttrValuePairNode node : features) {
                getAttrId.setString(2, node.getAttrName());
                ResultSet rs = getAttrId.executeQuery();
                while (rs.next()) {
                    attributeId = rs.getInt(1);
                    for (String value : node.getValue()) {
                        getFeatures.setString(1, value);
                        getFeatures.setInt(2, attributeId);
                        ResultSet featuresRS = getFeatures.executeQuery();
                        if (featuresRS.next()) {
                            idsOfValuesInDb.add(featuresRS.getInt(1));
                        } else {
                            notInDbValues.add(value);
                        }
                    }
                    idsOfValuesInDb.addAll(insertIntoFeaturesTable(connection, attributeId, notInDbValues));
                    insertIntoFeaturesMapping(connection, idsOfValuesInDb, fId);
                }
            }
        }*//*

    }

    private static final String INSERT_FEATURES = "INSERT INTO CAT_BOW_FEATURE_ATTR_VAL " +
            "VALUES (cat_bow_feature_attr_val_seq.nextval,?,?,'1', 'apimeta',sysdate,'apimeta',sysdate)";

    private List<Integer> insertIntoFeaturesTable(Connection connection, int attributeId, List<String> values) throws SQLException {

        String cols[] = {"CAT_BOW_ATTRIBUTE_VALUE_ID"};
        List<Integer> result = new ArrayList<Integer>();
        if (values == null || values.size() == 0) return result;
        try (PreparedStatement insertFeatures = connection.prepareStatement(INSERT_FEATURES, cols)) {
            for (String value : values) {
                insertFeatures.setInt(1, attributeId);
                insertFeatures.setString(2, value);
                insertFeatures.executeUpdate();
                ResultSet ids = insertFeatures.getGeneratedKeys();
                while (ids.next()) {
                    result.add(ids.getInt(1));
                }
                insertFeatures.clearParameters();
            }
        }
        return result;
    }

    private static final String INSERT_FEATURES_MAP =
            "INSERT INTO CAT_BOW_FDEF_TO_FAV_MAPPING (CAT_BOW_ATTRIBUTE_VALUE_ID, CAT_BOW_FEATURE_ID) VALUES (?, ?)";
    private static final String DELETE_FROM_FEATURES_MAP =
            "DELETE FROM CAT_BOW_FDEF_TO_FAV_MAPPING WHERE CAT_BOW_FEATURE_ID = ?";

    private void insertIntoFeaturesMapping(Connection connection, List<Integer> featureids, String fId) throws SQLException {
        try (PreparedStatement insertMap = connection.prepareStatement(INSERT_FEATURES_MAP);
             PreparedStatement deleteMap = connection.prepareStatement(DELETE_FROM_FEATURES_MAP)) {

            deleteMap.setString(1, fId);
            deleteMap.executeUpdate();

            insertMap.setString(2, fId);
            for (Integer id : featureids) {
                insertMap.setInt(1, id);
                insertMap.addBatch();
            }
            insertMap.executeBatch();
        }

    }

    static final int MAX_LENGTH_OF_VARCHAR2 = 1000;

    private void insertNewFeatureDefinition(String featureDefString, String hashValue, int featureTypeId, BowAttributes in, Connection conn) throws SQLException {
        String creator;
        String modifier;
        if (in.getLogInfo().getCreatedUser() == null)
            creator = in.getLogInfo().getLastModifiedUser().getUserName();
        else {
            creator = in.getLogInfo().getCreatedUser().getUserName();
        }
        modifier = in.getLogInfo().getLastModifiedUser().getUserName();
        insertNewFeatureDefinition(featureDefString, hashValue, featureTypeId, creator, modifier, conn);
    }

    public static long insertNewFeatureDefinition(String featureDefString, String hashValue, int featureTypeId, String creator, String modifier, Connection conn) throws SQLException {

        long featureId;
        try (Statement statement = conn.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT CAT_BOW_FEATURE_DEFINITION_SEQ.NEXTVAL FROM dual");
            rs.next();
            featureId = rs.getLong(1);
        }

        if (hashValue == null)
            hashValue = StringHelper.shaHex(featureDefString);

        try (PreparedStatement insertDef = conn.prepareStatement(
                "INSERT INTO CAT_BOW_FEATURE_DEFINITION " +
                        "(CAT_BOW_FEATURE_ID, CAT_BOW_FEATURE_TYPE_ID,CREATED_BY,CREATED_TIME,HASH_VALUE,LAST_MODIFIED_BY,LAST_MODIFIED_TIME,STATUS) " +
                        "VALUES" +
                        " (?,?,?,SYSDATE,?,?,SYSDATE,1)")) {
            int i = 1;
            insertDef.setLong(i++, featureId);
            insertDef.setInt(i++, featureTypeId);
            insertDef.setString(i++, creator);
            insertDef.setString(i++, hashValue);
            insertDef.setString(i, modifier);
            insertDef.executeUpdate();
        }

        // insert to data table
        List<String> list = StringHelper.splitBySize(featureDefString, MAX_LENGTH_OF_VARCHAR2);
        try (PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO CAT_BOW_FEATURE_DEF_DATA " +
                        "(CAT_BOW_FEATURE_EXTRA_ID,CAT_BOW_FEATURE_ID,CREATED_BY,CREATED_TIME,DEFINITION_STRING,LAST_MODIFIED_BY,LAST_MODIFIED_TIME,SEQUENCE_NUMBER,STATUS) " +
                        "VALUES" +
                        " (CAT_BOW_FEATURE_DEF_DATA_SEQ.NEXTVAL,?,?,SYSDATE,?,?,SYSDATE,?,1)")) {
            int seq = 1;
            for (String item : list) {
                int i = 1;
                insert.setLong(i++, featureId);
                insert.setString(i++, creator);
                insert.setString(i++, item);
                insert.setString(i++, creator);
                insert.setInt(i, seq++);
                insert.addBatch();
            }
            insert.executeBatch();
        }

        return featureId;
    }

    public static void updateFeatureDefinition(long featureId, String featureDefString, String hashValue, int featureTypeId, String modifier, Connection conn) throws SQLException {

        if (hashValue == null)
            hashValue = StringHelper.shaHex(featureDefString);

        try (PreparedStatement updateDefinition = conn.prepareStatement(
                "UPDATE CAT_BOW_FEATURE_DEFINITION " +
                        "SET CAT_BOW_FEATURE_TYPE_ID = ?, HASH_VALUE = ?, LAST_MODIFIED_BY = ?, LAST_MODIFIED_TIME = SYSDATE, STATUS = 1 " +
                        "WHERE CAT_BOW_FEATURE_ID = ?")) {
            int i = 1;
            updateDefinition.setInt(i++, featureTypeId);
            updateDefinition.setString(i++, hashValue);
            updateDefinition.setString(i++, modifier);
            updateDefinition.setLong(i++, featureId);
            updateDefinition.executeUpdate();
        }

        try (PreparedStatement updateDefinition = conn.prepareStatement(
                "DELETE FROM CAT_BOW_FEATURE_DEF_DATA " +
                        "WHERE CAT_BOW_FEATURE_ID = ?")) {
            updateDefinition.setLong(1, featureId);
            updateDefinition.executeUpdate();
        }

        // insert to data table
        List<String> list = StringHelper.splitBySize(featureDefString, MAX_LENGTH_OF_VARCHAR2);
        try (PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO CAT_BOW_FEATURE_DEF_DATA " +
                        "(CAT_BOW_FEATURE_EXTRA_ID,CAT_BOW_FEATURE_ID,CREATED_BY,CREATED_TIME,DEFINITION_STRING,LAST_MODIFIED_BY,LAST_MODIFIED_TIME,SEQUENCE_NUMBER,STATUS) " +
                        "VALUES" +
                        " (CAT_BOW_FEATURE_DEF_DATA_SEQ.NEXTVAL,?,?,SYSDATE,?,?,SYSDATE,?,1)")) {
            int seq = 1;
            for (String item : list) {
                int i = 1;
                insert.setLong(i++, featureId);
                insert.setString(i++, modifier);
                insert.setString(i++, item);
                insert.setString(i++, modifier);
                insert.setInt(i, seq++);
                insert.addBatch();
            }
            insert.executeBatch();
        }
    }

    public static void softDeleteFeature(long featureId, boolean delete, Connection conn) throws Exception {
        try (PreparedStatement updateDefinition = conn.prepareStatement(
                "UPDATE CAT_BOW_FEATURE_DEFINITION " +
                        "SET LAST_MODIFIED_TIME = SYSDATE, STATUS = ? " +
                        "WHERE CAT_BOW_FEATURE_ID = ?")) {
            int i = 1;
            updateDefinition.setInt(i++, (delete ? SegmentStatus.ARCHIVED.getCode() : SegmentStatus.ACTIVE.getCode()));
            updateDefinition.setLong(i++, featureId);
            updateDefinition.executeUpdate();
        }

        try (PreparedStatement updateDefinition = conn.prepareStatement(
                "UPDATE CAT_BOW_FEATURE_DEF_DATA " +
                        "SET LAST_MODIFIED_TIME = SYSDATE, STATUS = ? " +
                        "WHERE CAT_BOW_FEATURE_ID = ?")) {
            int i = 1;
            updateDefinition.setInt(i++, (delete ? SegmentStatus.ARCHIVED.getCode() : SegmentStatus.ACTIVE.getCode()));
            updateDefinition.setLong(i++, featureId);
            updateDefinition.executeUpdate();
        }
    }

    public static Integer getFeatureTypeIdFromName(String featureTypeName, Connection conn) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement("SELECT CAT_BOW_FEATURE_TYPE_ID FROM CAT_BOW_FEATURE_TYPE WHERE CAT_BOW_FEATURE_TYPE_NAME = ?")) {
            statement.setString(1, featureTypeName);

            ResultSet rs = statement.executeQuery();
            return (rs.next() ? rs.getInt(1) : null);
        }
    }

    @Language("Oracle")
    static final String UPDATE_FEATURE_DEF = "UPDATE CAT_BOW_FEATURE_DEFINITION SET LAST_MODIFIED_BY=?, LAST_MODIFIED_TIME=SYSDATE,STATUS=1 WHERE   CAT_BOW_FEATURE_ID=?";

    @Language("Oracle")
    static final String UPDATE_FEATURE_DEF_DATA = "UPDATE CAT_BOW_FEATURE_DEF_DATA SET LAST_MODIFIED_BY=?, LAST_MODIFIED_TIME=SYSDATE,STATUS=1 WHERE   CAT_BOW_FEATURE_ID=?";

    private void updateFeatureDefinition(Connection connection, long featureId, BowAttributes in) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_FEATURE_DEF)) {
            stmt.setString(1, in.getLogInfo().getLastModifiedUser().getUserName());
            stmt.setLong(2, featureId);
            stmt.executeUpdate();
        }
        // update data table
        try (PreparedStatement stData = connection.prepareStatement(UPDATE_FEATURE_DEF_DATA)) {
            stData.setString(1, in.getLogInfo().getLastModifiedUser().getUserName());
            stData.setLong(2, featureId);
            stData.executeUpdate();
        }
    }

    private long getFeatureById(String hashValue, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(GET_FEATURE_BY_ID)) {
            stmt.setString(1, hashValue);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return rs.getLong(1);
            else
                return 0;
        }
    }

    private void deleteSubSegment(Connection connection, BowAttributes in) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SUBSEG_DELETE)) {
            ps.setLong(1, in.getId().toLong());
            ps.execute();
        }

    }

    private List<FeatureDef> getFeatureDefs(SegmentDef segmentDef) {
        List<FeatureDef> featureDefs = new ArrayList<>();
        for (SubSegmentDef subSegDef : segmentDef.getSubSegments()) {
            featureDefs.add(subSegDef.getFeatureDef());
        }
        return featureDefs;
    }

    @Language("Oracle")
    static final String GET_BOW_STRING = "SELECT DEFINITION_STRING " +
            "FROM CAT_BOW_ATTR_DATA " +
            "WHERE CATEGORY_ID=? AND TYPE=? " +
            "ORDER BY SEQUENCE_NUMBER ASC";

    @Override
    public void read(final BowAttributes in) throws Exception {
        in.setBowSegmentType(BowSegmentType.ORIGINAL);
        getBowDataAttribute(in);
    }

    public BowAttributes getBowDataAttribute(BowAttributes in) throws Exception {
        StringBuilder result = new StringBuilder();
        Connection connection = in.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_BOW_STRING)) {
            ps.setLong(1, in.getId().toLong());
            ps.setInt(2, in.getBowSegmentType().getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.append(rs.getString(1));
            }
        }
        if (result.length() != 0) {
            in.setValue(result.toString());
            in.setExprNode(ExprNodeFactory.fromString(result.toString()));
        }
        return in;
    }

    @Override
    public void update(BowAttributes in) throws Exception {
        Connection connection = in.getConnection();
        upsertSegmentBowPart(connection, in);
    }

    @Language("Oracle")
    static final String SUBSEG_SOFT_DELETE = "UPDATE CAT_BOW_SUBSEGMENT " +
            "SET STATUS = ?, " +
            "LAST_MODIFIED_TIME =  sysdate " +
            "WHERE CATEGORY_ID = ? ";

    @Language("Oracle")
    static final String ATTR_SOFT_DELETE = "UPDATE CAT_BOW_ATTR " +
            "SET STATUS = ?, LAST_MODIFIED_TIME = SYSDATE " +
            "WHERE CATEGORY_ID = ? ";
    @Language("Oracle")
    static final String ATTR_DATA_SOFT_DELETE = "UPDATE CAT_BOW_ATTR_DATA " +
            "SET STATUS=?,  LAST_MODIFIED_TIME=SYSDATE " +
            "WHERE CATEGORY_ID=?";


    private void softDeleteSubSegment(Connection connection, BowAttributes in) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(SUBSEG_SOFT_DELETE)) {
            ps.setInt(1, SegmentStatus.ARCHIVED.getCode());
            ps.setLong(2, in.getId().toLong());
            ps.executeUpdate();
        }

    }

    private void softDeleteAttr(Connection connection, BowAttributes in) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(ATTR_SOFT_DELETE)) {
            ps.setInt(1, SegmentStatus.ARCHIVED.getCode());
            ps.setLong(2, in.getId().toLong());
            ps.executeUpdate();
        }
        try (PreparedStatement ps = connection.prepareStatement(ATTR_DATA_SOFT_DELETE)) {
            ps.setInt(1, SegmentStatus.ARCHIVED.getCode());
            ps.setLong(2, in.getId().toLong());
            ps.executeUpdate();
        }
    }


    @Override
    public void delete(BowAttributes in) throws Exception {
        Connection connection = in.getConnection();
        softDeleteSubSegment(connection, in);
        softDeleteAttr(connection, in);
    }


    private static String DELETE_SEGMENT_MAPPING = "DELETE FROM CAT_BOW_NESTED_SEG_MAPPING WHERE REFERED_BY_CATEGORY_ID = ?";
    private static String INSERT_SEGMENT_MAPPING = "INSERT INTO CAT_BOW_NESTED_SEG_MAPPING " +
            "(CAT_BOW_NESTED_SEG_MAPPING_ID, CATEGORY_ID, REFERED_BY_CATEGORY_ID, " +
            "CREATED_BY, CREATED_TIME, LAST_MODIFIED_BY, LAST_MODIFIED_TIME) " +
            "VALUES (CAT_BOW_NESTEDSEG_SEQ.nextval, ?, ?, ?, SYSDATE, ?, SYSDATE)";

    private void updateNestedSegmentMapping(Connection connection, long id,
                                            List<String> nestedSegments, String userName) throws SQLException {
        try (PreparedStatement deleteStatement = connection.prepareStatement(DELETE_SEGMENT_MAPPING);
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_SEGMENT_MAPPING)) {
            deleteStatement.setLong(1, id);
            deleteStatement.execute();
            for (String segId : nestedSegments) {
                int i = 1;
                insertStatement.setLong(i++, Long.parseLong(segId));
                insertStatement.setLong(i++, id);
                insertStatement.setString(i++, userName);
                insertStatement.setString(i, userName);
                insertStatement.addBatch();
            }
            insertStatement.executeBatch();
        }
    }

    private static String GET_REFERRING_SEGMENTS = "SELECT REFERED_BY_CATEGORY_ID FROM CAT_BOW_NESTED_SEG_MAPPING" +
            " WHERE CATEGORY_ID = ?";

    private void reprocessReferringSegments(Connection connection, long id,
                                            BowAttributes in) throws Exception {
        try (PreparedStatement getSegments = connection.prepareStatement(GET_REFERRING_SEGMENTS)) {
            getSegments.setLong(1, id);
            ResultSet rs = getSegments.executeQuery();
            while (rs.next()) {
                String referringSegmentId = rs.getString(1);
                BowAttributes attributes = new BowAttributes();
                attributes.setId(referringSegmentId);
                attributes.setBowSegmentType(BowSegmentType.ORIGINAL);
                attributes.setParentChain(in.getParentChain());
                attributes = getBowDataAttribute(attributes);
                if (attributes != null) {
                    attributes.setLogInfo(in.getLogInfo());
                    upsertSegmentBowPart(connection, attributes);
                }
            }
        }
    }
}
*/
