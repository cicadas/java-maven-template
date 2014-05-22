package template.core;

import java.util.List;

/**
 * @author zhouzh
 */
public interface Verifiable {
    /**
     * Currently only create operation need verify the input data
     *
     * @param rules
     * @throws Exception
     */
    void verify(List<VerifyRule> rules) throws Exception;
}
