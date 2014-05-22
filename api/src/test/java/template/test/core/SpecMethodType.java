package template.test.core;

/**
 * @author zhouzh
 *         Date: 8/6/13
 *         Time: 4:18 PM
 */
public enum SpecMethodType {
    EMPTY(0),
    DESCRIBE(1),
    IT(2),
    GIVEN(3),
    WHEN(4),
    THEN(5),
    AND(6),
    REF_CODE(7),
    PREPARE(8);

    private final int code;

    public int getCode() {
        return code;
    }

    SpecMethodType(int code) {
        this.code = code;
    }
}
