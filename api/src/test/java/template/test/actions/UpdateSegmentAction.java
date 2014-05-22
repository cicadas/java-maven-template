package template.test.actions;

import template.test.core.*;
import template.test.util.ServiceHelper;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import template.RestResource;
import template.core.Id;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author zhouzh
 *         Date: 8/14/13
 *         Time: 2:56 PM
 */
public class UpdateSegmentAction extends TestAction {
    public UpdateSegmentAction(TestContext context) {
        super(context);
    }

    private Id id;

    @Override
    protected void prepare() throws Exception {
        input = (String) context.getShareData(TestContextSharesType.UPDATED);
        id = (Id) context.getShareData(TestContextSharesType.SEGMENT_ID);
    }

    @Override
    protected void tearDown() throws Exception {
        TestContextItem item = new TestContextItem(TestActionType.ENTRY_UPDATING, (String) context.getShareData(TestContextSharesType.UPDATED), StringUtils.EMPTY);
        context.add(item);
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode(),
                "segment read return code should be " + String.valueOf(Response.Status.OK.getStatusCode()));
    }

    @Override
    protected void callMethod() throws Exception {
        response = new RestResource().update(input, id);
    }

    @Override
    protected void callService() throws Exception {
        response = new ServiceHelper().build()
                .path("/api/segment/" + id.toString())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(
                        input,
                        MediaType.APPLICATION_JSON_TYPE), Response.class);
    }
}
