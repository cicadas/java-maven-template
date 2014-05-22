package template.test.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import template.core.ContextMap;
import template.util.JsonHelper;
import template.util.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouzh
 * Date: 8/9/13
 * Time: 11:09 AM
 */
public class TestConfig {

    static final String CONFIG_FILE = "/testconfig.json";
    static final String YINST_PREFIX = "targeting_api_test__";
    static final ConcurrentHashMap<String, String> configs = new ContextMap<>();
    public static final TestConfig INSTANCE = new TestConfig();

    private TestConfig() {
        try {
            setTestConfigFile(TestConfig.class.getResourceAsStream(CONFIG_FILE));
        } catch (IOException e) {
            Logger.error("Cannot found default test configuration file.");
            e.printStackTrace();
        }
    }

    public void setTestConfigFile(InputStream inputStream) throws IOException {
        ObjectMapper mapper = JsonHelper.getMapper();
        JsonNode rootNode = mapper.readTree(inputStream);
        Iterator<JsonNode> it = rootNode.elements();
        configs.clear();
        while (it.hasNext()) {
            ObjectNode node = (ObjectNode) it.next();
            String key = node.get("key").asText();
            String value = node.get("value").asText();
            configs.put(key, value);
        }
    }

    public String get(String key) {
        String result = StringUtils.EMPTY;
        Logger.debug("Get key:" + key);
        if (StringUtils.isNotEmpty(key)) {
            String sysValue = System.getenv(YINST_PREFIX + key);
            if (StringUtils.isNotEmpty(sysValue))
                result = sysValue;
            else if (configs.get(key) == null)
                result = null;
            else
                result = configs.get(key);
        }
        return result;
    }

    public void set(String key, String value) {
        configs.put(key, value);
    }


    public final static String RMX_SEGMENT_SERVICE_URL = "rmx_segmentservice_url";

    public final static String PORT = "test_port";

    public final static String PROTOCOL = "test_protocol";
    public final static String DB_CONNECTION_STRING = "test_db_connection_string";
    public final static String DB_USERNAME = "test_db_user";
    public final static String DB_PASSWORD = "test_db_pw";
    public final static String INVOKE_TYPE = "invoke_type"; // method or service
    public final static String TEST_HOSTNAME = "test_hostname";
}
