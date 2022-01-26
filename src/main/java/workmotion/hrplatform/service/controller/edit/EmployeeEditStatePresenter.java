package workmotion.hrplatform.service.controller.edit;

import workmotion.hrplatform.client.response.EmployeeEditStateRestResponse;
import workmotion.hrplatform.core.edit.EmployeeEditStateOutputBoundary;
import workmotion.hrplatform.core.edit.EmployeeEditStateResponse;

public class EmployeeEditStatePresenter implements EmployeeEditStateOutputBoundary {

    private EmployeeEditStateRestResponse restResponse;

    @Override
    public void present(EmployeeEditStateResponse response) {
        restResponse = new EmployeeEditStateRestResponse();
        restResponse.setSuccess(response.isSuccess());
    }


    public EmployeeEditStateRestResponse getResponse() {
        return restResponse;
    }
}
