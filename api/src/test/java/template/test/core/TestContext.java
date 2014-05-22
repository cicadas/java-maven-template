package template.test.core;

import template.test.exception.NotFoundTestAction;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author zhouzh
 *         Date: 8/6/13
 *         Time: 3:46 PM
 */
public class TestContext {
    private Hashtable<TestContextSharesType, Object> shares = new Hashtable<>();

    public Object get(TestContextSharesType key) {
        return shares.get(key);
    }

    private List<TestContextItem> items = new ArrayList<>();

    public TestContext add(TestContextItem item) {
        items.add(item);
        return this;
    }

    public TestContextItem get(int index) {
        return items.get(index);
    }

    public TestContextItem getLast(TestActionType type) throws NotFoundTestAction {
        for (int i = items.size() - 1; i >= 0; i--)
            if (items.get(i).getActionType().equals(type))
                return items.get(i);
        return null;
    }

    public TestContext addShares(TestContextSharesType key, Object item) {
        shares.put(key, item);
        return this;
    }

    public Object getShareData(TestContextSharesType key) {
        return shares.get(key);
    }

    public int getShareSize() {
        return shares.size();
    }

    public int getSize() {
        return items.size();
    }
}
