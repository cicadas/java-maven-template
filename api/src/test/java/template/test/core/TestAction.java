package template.test.core;

import template.test.util.TestConfig;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;

/**
 * @author zhouzh
 */
public abstract class TestAction {
    public static final String PLEASE_OVERRIDE_THIS_METHOD_IN_YOUR_CUSTOM_ACTION = "Please @Override this method in your custom action";
    protected String input;
    protected String output;
    protected Response response;
    protected TestContextItem item;

    public static final int DEFAULT_TIMEOUT = 24; // hours
    protected TestContext context;

    public TestAction() {
        this(null);
    }

    public TestAction(TestContext context) {
        this.context = context;
    }

    public void run() throws Exception {
        if (StringUtils.isEmpty(TestConfig.INSTANCE.get(TestConfig.INVOKE_TYPE))
                || TestConfig.INSTANCE.get(TestConfig.INVOKE_TYPE).equalsIgnoreCase(TestInvokeType.METHOD.toString()))
            this.run(TestInvokeType.METHOD);
        else run(TestInvokeType.SERVICE);
    }

    public void run(TestInvokeType type) throws Exception {
        prepare();
        switch (type) {
            case SERVICE:
                callService();
                output = response.readEntity(String.class);
                break;
            case METHOD:
                callMethod();
                if (response.getEntity() != null)
                    output = response.getEntity().toString();
                break;
            default:
                throw new UnsupportedOperationException("Not support test invoke type: " + type.toString());
        }
        tearDown();
    }

    protected void prepare() throws Exception {
        throw new UnsupportedOperationException(PLEASE_OVERRIDE_THIS_METHOD_IN_YOUR_CUSTOM_ACTION);
    }

    protected void tearDown() throws Exception {
        throw new UnsupportedOperationException(PLEASE_OVERRIDE_THIS_METHOD_IN_YOUR_CUSTOM_ACTION);
    }

    protected void callMethod() throws Exception {
        throw new UnsupportedOperationException(PLEASE_OVERRIDE_THIS_METHOD_IN_YOUR_CUSTOM_ACTION);
    }

    protected void callService() throws Exception {
        throw new UnsupportedOperationException(PLEASE_OVERRIDE_THIS_METHOD_IN_YOUR_CUSTOM_ACTION);
    }

}
