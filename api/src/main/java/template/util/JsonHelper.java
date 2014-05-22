package template.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Iterator;

public class JsonHelper {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        return mapper;
    }

    public static String writeValueAsString(Object input) throws IOException {
        return getMapper().writeValueAsString(input);
    }

    public static JsonNode valueToTree(Object input) throws IOException {
        return getMapper().valueToTree(input);
    }

    public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
        Iterator<String> fieldNames = updateNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode jsonNode = mainNode.get(fieldName);
            // if field doesn't exist or is an embedded object
            if (jsonNode != null && jsonNode.isObject()) {
                merge(jsonNode, updateNode.get(fieldName));
            } else {
                if (mainNode instanceof ObjectNode) {
                    // Overwrite field
                    JsonNode value = updateNode.get(fieldName);
                    ((ObjectNode) mainNode).put(fieldName, value);
                }
            }
        }
        return mainNode;
    }

    public static boolean isEquals(JsonNode left, JsonNode right) {
        Iterator<String> fieldNames = right.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode jsonNode = left.get(fieldName);
            // if field doesn't exist or is an embedded object
            if (jsonNode != null && jsonNode.isObject() && !isEquals(jsonNode, right.get(fieldName))) {
                return false;
            } else {
                if (left instanceof ObjectNode) {
                    if (!right.get(fieldName).equals(left.get(fieldName)))
                        return false;
                }
            }
        }
        return true;
    }
}
