package template.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import javassist.bytecode.DuplicateMemberException;
import template.core.ErrorCode;
import template.core.ErrorCodeManager;
import template.util.JsonHelper;
import template.util.Logger;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * @author zhouzh
 *         Offical http return code web site http://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
 */

public final class ExceptionManager {

    public static Response _try(Callable<Response> action) {
        javax.ws.rs.core.Response response;
        try {
            response = action.call();
        } catch (final UnsupportedOperationException e) {
            response = getResponse(e, 103);
        } catch (final InvalidOriginatorUpdateException e) {
            response = getResponse(e, 104);
        } catch (final InvalidSegmentCreateException e) {
            response = getResponse(e, 105);
        } catch (final OriginatorMoreThanOneException e) {
            response = getResponse(e, 106);
        } catch (final NotSupportSegmentTypeException e) {
            response = getResponse(e, 107);
        } catch (final UnSupportedSegmentStatus e) {
            response = getResponse(e, 108);
        } catch (final BowAttributeNotExistException e) {
            response = getResponse(e, 110);
        } catch (final CustomAttributeNotExistException e) {
            response = getResponse(e, 111);
        } catch (final ExchangeNotFoundException e) {
            response = getResponse(e, 112);
        } catch (final NumberFormatException e) {
            response = getResponse(e, 150);
        } catch (final NotFoundException e) {
            response = getResponse(e, 151);
        } catch (final HasChildrenException e) {
            response = getResponse(e, 152);
        } catch (final MergeException e) {
            response = getResponse(e, 153);
        } catch (final DuplicateMemberException e) {
            response = getResponse(e, 154);
        } catch (final SQLException e) {
            response = getResponse(e, 200);
        } catch (final Exception e) {
            response = getResponse(e, 100);
        }
        return response;
    }

    private static Response getResponse(Exception e, int internalErrorCode) {
        Response response;
        e.printStackTrace();
        ErrorCode code = ErrorCodeManager.instance().get(internalErrorCode);
        String msgJson = getJson(code, e);
        Logger.error(msgJson, e);
        response = Response.status(code.getHttpCode()).entity(msgJson).build();
        return response;
    }

    private static String getJson(ErrorCode code, Exception e) {
        ObjectNode root = JsonHelper.getMapper().createObjectNode();
        root.put(ErrorCode.ERROR_CODE_STR, code.getLocalCode());
        root.put(ErrorCode.ERROR_MESSAGE_STR, code.getErrorMessage());
        root.put(ErrorCode.ERROR_INTERNAL_MESSAGE_STR, e.getMessage());
        return root.toString();
    }
}
