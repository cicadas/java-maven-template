package template.test.core;

import template.test.exception.NotSupportSpecTypeException;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import template.util.Logger;

/**
 * @author zhouzh
 */

public abstract class Spec extends TestListenerAdapter {

    public abstract String getContent() throws Exception;

    protected SpecMethodType previousType = SpecMethodType.EMPTY;
    protected boolean[][] methodPermissions =
            {
                    // Empty, desc, it, given, when, then, and, refCode, prepare
                    {false, false, false, false, false, false, false, true, true}, //empty
                    {true, false, true, false, false, false, false, true, true},   //desc
                    {false, true, true, true, true, false, true, true, true},      //it
                    {false, false, false, false, true, false, false, true, true},  //given
                    {false, false, true, false, false, true, true, true, true},   //when
                    {false, true, false, false, true, false, true, true, true},   //then
                    {false, true, true, false, false, true, true, true, true},   //and
                    {true, true, true, true, true, true, true, true, true},   //refCode
                    {false, true, false, false, false, false, false, true, true}  //prepare
            };

    protected boolean isValidMethod(SpecMethodType current) {
        return methodPermissions[current.getCode()][previousType.getCode()];
    }

    public Spec describe(String str) throws Exception {
        return describe(str, null);
    }

    public Spec describe(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.DESCRIBE)) {
            invokeAction(action);
            previousType = SpecMethodType.DESCRIBE;
        } else
            return unSupportException(SpecMethodType.DESCRIBE);
        return this;
    }

    private Spec unSupportException(SpecMethodType input) throws NotSupportSpecTypeException {
        throw new NotSupportSpecTypeException("Not support using " + input + " after " + previousType);
    }

    public Spec it(String str) throws Exception {
        return it(str, null);
    }

    public Spec it(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.IT)) {
            invokeAction(action);
            previousType = SpecMethodType.IT;
        } else
            return unSupportException(SpecMethodType.IT);
        return this;
    }

    public Spec prepare(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.PREPARE)) {
            invokeAction(action);
            previousType = SpecMethodType.PREPARE;
        } else
            return unSupportException(SpecMethodType.PREPARE);
        return this;
    }

    public Spec refCode(String str) throws Exception {
        if (isValidMethod(SpecMethodType.REF_CODE)) {
            previousType = SpecMethodType.REF_CODE;
        } else
            return unSupportException(SpecMethodType.REF_CODE);
        return this;
    }

    public Spec given(String str) throws Exception {
        return given(str, null);
    }

    public Spec given(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.GIVEN)) {
            invokeAction(action);
            previousType = SpecMethodType.GIVEN;
        } else
            return unSupportException(SpecMethodType.GIVEN);
        return this;
    }

    private void invokeAction(TestAction action) throws Exception {
        if (action != null) action.run();
    }

    public Spec when(String str) throws Exception {
        return when(str, null);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        try {
            Logger.info(getContent());
        } catch (Exception ignore) {
            ignore.printStackTrace();
            Logger.error("Failed generate test cases report!!!");
        }

    }

    public Spec when(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.WHEN)) {
            invokeAction(action);
            previousType = SpecMethodType.WHEN;
        } else
            return unSupportException(SpecMethodType.WHEN);
        return this;
    }


    public Spec then(String str) throws Exception {
        return then(str, null);
    }

    public Spec then(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.THEN)) {
            invokeAction(action);
            previousType = SpecMethodType.THEN;
        } else
            return unSupportException(SpecMethodType.THEN);
        return this;
    }

    public Spec and(String str) throws Exception {
        return and(str, null);
    }

    public Spec and(String str, TestAction action) throws Exception {
        if (isValidMethod(SpecMethodType.AND)) {
            invokeAction(action);
            previousType = SpecMethodType.AND;
        } else
            return unSupportException(SpecMethodType.AND);
        return this;
    }

    public static Spec get() {
        return new MarkdownAdaptor();
    }
}
