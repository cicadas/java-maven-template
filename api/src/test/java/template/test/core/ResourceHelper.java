package template.test.core;

import com.fasterxml.jackson.databind.node.ObjectNode;
import template.util.JsonHelper;
import template.util.StringHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhouzh
 */
public class ResourceHelper {
    public static String getStringFromResource(String path) throws URISyntaxException, IOException {
        String content =
                new String(Files.readAllBytes(
                        Paths.get(ResourceHelper.class.getResource(path).toURI())), "UTF8");

        ObjectNode rootNode = JsonHelper.getMapper().readValue(StringHelper.toInputStream(content), ObjectNode.class);
        return JsonHelper.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }
}
