package template.test.actions;

import template.test.core.*;
import template.test.util.ServiceHelper;
import template.RestResource;
import template.core.Id;

import javax.ws.rs.core.MediaType;

/**
 * @author zhouzh
 *         Date: 8/14/13
 *         Time: 11:25 AM
 */
public class ReadSegmentAction extends TestAction {
    public ReadSegmentAction(TestContext context) {
        super(context);
    }

    private Id id;

    @Override
    protected void prepare() throws Exception {
        id = (Id) context.getShareData(TestContextSharesType.SEGMENT_ID);
    }

    @Override
    protected void tearDown() throws Exception {
    }

    @Override
    protected void callMethod() throws Exception {
        response = new RestResource().represent(id);
    }

    @Override
    protected void callService() throws Exception {
        response = new ServiceHelper().build()
                .path("/api/segment/" + id.toString())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
    }
}
