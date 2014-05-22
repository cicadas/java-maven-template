package template.test.actions;

import template.test.core.TestAction;
import template.test.core.TestActionType;
import template.test.core.TestContext;
import template.test.util.ServiceHelper;
import template.RestResource;
import template.core.Id;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author zhouzh
 *         Date: 8/9/13
 *         Time: 2:12 PM
 */
public class CreateSegmentAction extends TestAction {
    private Id id;

    public CreateSegmentAction(TestContext context) {
        super(context);
    }

    @Override
    protected void prepare() throws Exception {
        input = context.getLast(TestActionType.ENTRY_CREATION).getInputData();
    }

    @Override
    protected void tearDown() throws Exception {
    }

    @Override
    protected void callMethod() throws Exception {
        response = new RestResource().store(input);
    }

    @Override
    protected void callService() throws Exception {
        response = new ServiceHelper().build()
                .path("/api/entry")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(
                        input,
                        MediaType.APPLICATION_JSON_TYPE), Response.class);
    }

}
