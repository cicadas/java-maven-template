package template.test.core;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author zhouzh
 *         Date: 8/2/13
 *         Time: 11:59 AM
 */
public class MarkdownAdaptor extends Spec {
    private final String DESCRIBE_HEADER = "\n1. ";
    private final String IT_HEADER = "    * It ";
    private final String GIVEN_HEADER = "    * Given ";
    private final String WHEN_HEADER = "    * When ";
    private final String THEN_HEADER = "    * Then ";
    private final String AND_HEADER = "    * And ";
    private final String REF_CODE = "    * Reference data: ";

    private static volatile StringBuilder content = new StringBuilder();

    @Override
    public String getContent() throws Exception {
        StringBuilder start = new StringBuilder();
        start.append("#Test Cases\n\n");
        for (int i = 0; i < 80; i++)
            start.append("*");
        start.append("\n");
        content.insert(0, start);
        String r = content.toString();
        output(r);
        return r;
    }

    @Override
    public Spec it(String str, TestAction action) throws Exception {
        combine(str, IT_HEADER);
        return super.it(str, action);
    }

    @Override
    public Spec refCode(String str) throws Exception {
        content.append(REF_CODE).append(" `").append(str).append("` ");
        return super.refCode(str);
    }

    @Override
    public Spec given(String str, TestAction action) throws Exception {
        super.given(str, action);
        combine(str, GIVEN_HEADER);
        return this;
    }

    @Override
    public Spec when(String str, TestAction action) throws Exception {
        super.when(str, action);
        combine(str, WHEN_HEADER);
        return this;
    }

    public Spec describe(String str, TestAction action) throws Exception {
        super.describe(str, action);
        combine(str + "\n", DESCRIBE_HEADER);

        return this;
    }

    @Override
    public Spec then(String str, TestAction action) throws Exception {
        super.then(str, action);
        combine(str, THEN_HEADER);
        return this;
    }

    public Spec and(String str, TestAction action) throws Exception {
        super.and(str, action);
        combine(str, AND_HEADER);
        return this;
    }


    void combine(String str, String header) throws Exception {
        try {
            content.append("\n").append(header).append(str);
        } catch (Exception e) {
            output(content.toString());
            e.printStackTrace();
            throw e;
        }
    }


    void output(String text) throws Exception {
        output(text, null);
    }

    void output(String text, TestAction outputMethod) throws Exception {
        final String[] inputs = new String[1];
        inputs[0] = text;
        if (outputMethod == null)
            outputMethod = new TestAction() {
                @Override
                public void run() throws IOException {
                    FileUtils.writeStringToFile(new File("docs/test/cases.md"), inputs[0]);
                }
            };
        outputMethod.run();
    }
}
