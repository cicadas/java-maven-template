package template.test.core;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

/**
 * @author zhouzh
 */
public class TestContextItem {
    private TestActionType actionType;

    public TestContextItem(TestActionType actionType, Request request, Response response, String inputData, String outputData) {
        this.actionType = actionType;
        this.request = request;
        this.response = response;
        this.inputData = inputData;
        this.outputData = outputData;
    }

    public TestContextItem(TestActionType actionType, Response response, String inputData, String outputData) {
        this(actionType, null, response, inputData, outputData);
    }

    public TestContextItem(TestActionType actionType, String inputData, String outputData) {
        this(actionType, null, null, inputData, outputData);
    }

    public TestContextItem(TestActionType actionType, String inputData) {
        this(actionType, null, null, inputData, null);
    }

    public TestContextItem(TestActionType actionType) {
        this(actionType, null, null, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    public TestContextItem(TestActionType actionType, Response response) {
        this(actionType, null, response, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public TestActionType getActionType() {
        return actionType;
    }


    private Request request;
    private Response response;
    private String inputData;
    private String outputData;

    public Request getRequest() {
        return request;
    }


    public Response getResponse() {
        return response;
    }

    public String getInputData() {
        return inputData;
    }


    public String getOutputData() {
        return outputData;
    }

}
