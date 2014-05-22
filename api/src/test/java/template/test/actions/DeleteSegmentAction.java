package template.test.actions;

import template.test.core.*;
import template.test.util.ServiceHelper;
import org.testng.Assert;
import template.RestResource;
import template.core.Id;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author zhouzh
 *         Date: 8/14/13
 *         Time: 11:02 AM
 */
public class DeleteSegmentAction extends TestAction {
    public DeleteSegmentAction(TestContext context) {
        super(context);
    }

    private Id id;

    @Override
    protected void prepare() throws Exception {
        id = (Id) context.getShareData(TestContextSharesType.SEGMENT_ID);
    }

    @Override
    protected void tearDown() throws Exception {
        TestContextItem item = new TestContextItem(TestActionType.ENTRY_DELETION, response);
        context.add(item);
        Assert.assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode(),
                "segment deletion return code should be " + String.valueOf(Response.Status.NO_CONTENT.getStatusCode()));
    }

    @Override
    protected void callMethod() throws Exception {
        response = new RestResource().delete(id);
    }

    @Override
    protected void callService() throws Exception {
        response = new ServiceHelper().build()
                .path("/api/segment/" + id.toString())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
    }
}
