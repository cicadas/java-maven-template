package template.core;


import org.apache.commons.lang3.StringUtils;

import java.io.InvalidObjectException;

/**
 * @author zhouzh
 */
public class ErrorCode {
    private int httpCode;
    private int localCode;
    private String errorMessage;

    public static final String ERROR_CODE_STR = "Error Code";
    public static final String ERROR_MESSAGE_STR = "Error Message";
    public static final String ERROR_STACK_STR = "Stack Trace";
    public static final String ERROR_INTERNAL_MESSAGE_STR = "Internal Error Message";

    public ErrorCode(int httpCode, int localCode, String errorMessage) throws InvalidObjectException {
        if (httpCode != 0 && localCode != 0 && StringUtils.isNotEmpty(errorMessage)) {
            this.httpCode = httpCode;
            this.localCode = localCode;
            this.errorMessage = errorMessage;
        } else throw new InvalidObjectException("HttpCode/LocalCode/ErrorMsg cannot be empty.");
    }

    public int getHttpCode() {
        return httpCode;
    }

    public int getLocalCode() {
        return localCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
