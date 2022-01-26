package workmotion.hrplatform.service.controller.detail;

import workmotion.hrplatform.client.response.EmployeeDetailRestResponse;
import workmotion.hrplatform.core.detail.EmployeeDetailOutputBoundary;
import workmotion.hrplatform.core.detail.EmployeeDetailResponse;

public class EmployeeDetailPresenter implements EmployeeDetailOutputBoundary {

    private EmployeeDetailRestResponse restResponse;


    @Override
    public void present(EmployeeDetailResponse response) {
        restResponse = new EmployeeDetailRestResponse();
        restResponse.setEmployees(response.getEmployees());
    }

    public EmployeeDetailRestResponse getRestResponse() {
        return restResponse;
    }


}
