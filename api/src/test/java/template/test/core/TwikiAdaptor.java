package template.test.core;

import template.util.Logger;

/**
 * @author zhouzh
 *         Date: 8/2/13
 *         Time: 11:58 AM
 */
public class TwikiAdaptor extends Spec {
    private final String DESCRIBE_HEADER = "\n---+++ ";
    private final String IT_HEADER = "   1. It ";
    private final String GIVEN_HEADER = "      * Given ";
    private final String WHEN_HEADER = "      * When ";
    private final String THEN_HEADER = "      * Then ";
    private final String AND_HEADER = "      * And ";

    private final String PENDING_HEADER = "\t%ICONURL{todo}%";

    private static volatile StringBuilder content = new StringBuilder();

    @Override
    public String getContent() throws Exception {
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < 80; i++)
            start.append("*");
        start.append("\n");
        Logger.info(start.toString());
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
        content.append("\n").append("<div style=\"border:solid 1px #CCCCCC; background-color:rgb(231,242,249);padding:10px;\"><div style=\"margin-left: 40px;\"><verbatim>\n").append(str).append("\n</verbatim></div></div>");
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
        combine(str, DESCRIBE_HEADER);
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
                public void run() {
                    System.out.println(inputs[0]);
                }
            };
        outputMethod.run();
    }

}
