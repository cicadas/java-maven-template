package template.test.funclite;

import template.test.actions.CreateSegmentAction;
import template.test.actions.VerifyCommonAttributes;
import template.test.concept.EntryCommon;
import template.test.core.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author zhouzh
 */
@Test(groups = {"func_lite_test"})
public class TestExample implements EntryCommon {
    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        LocalDBManager.setLocalDBDriver();
    }

    @Test
    public void createEntry() throws Exception {
        TestContext context = new TestContext();
        TestContextItem item = new TestContextItem(TestActionType.ENTRY_CREATION);
        String resPath = "/entry_creation.json";
        context.add(item);
        context.addShares(TestContextSharesType.CURRENT, item.getInputData());
        Spec.get().describe("This is a test case's title").
                refCode((String) context.getShareData(TestContextSharesType.CURRENT))
                .it("should return http code 200 and segment id after completed the creation process",
                        new CreateSegmentAction(context))
                .refCode(context.get(0).getOutputData())
                .it("should insert segment basic info into <database table>",
                        new VerifyCommonAttributes(context));
    }


    @Override
    public void create() throws Exception {

    }

    @Override
    public void delete() throws Exception {

    }

    @Override
    public void read() throws Exception {

    }

    @Override
    public void update() throws Exception {

    }
}
