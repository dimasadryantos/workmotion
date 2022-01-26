package workmotion.hrplatform.service.controller.create;

import workmotion.hrplatform.client.response.EmployeeCreationRestResponse;
import workmotion.hrplatform.core.create.EmployeeCreationOutputBoundary;
import workmotion.hrplatform.core.create.EmployeeCreationResponse;

public class EmployeeCreationPresenter implements EmployeeCreationOutputBoundary {
    private EmployeeCreationRestResponse restResponse;

    @Override
    public void present(EmployeeCreationResponse response) {
        restResponse = new EmployeeCreationRestResponse();
        restResponse.setSuccess(response.isSuccess());
    }


    public EmployeeCreationRestResponse getResponse() {
        return restResponse;
    }


}
