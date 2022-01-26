package workmotion.hrplatform.service.controller.edit;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import workmotion.hrplatform.client.request.EmployeeEditStateRestRequest;
import workmotion.hrplatform.client.response.EmployeeEditStateRestResponse;
import workmotion.hrplatform.core.edit.EmployeeEditStateInputBoundary;
import workmotion.hrplatform.core.edit.EmployeeEditStateRequest;
import workmotion.hrplatform.exception.BadRequestException;

@RestController
@RequiredArgsConstructor
public class EmployeeEditStateController {

    private final EmployeeEditStateInputBoundary useCase;


    @PutMapping(value = "/edit")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeEditStateRestResponse editState(@RequestBody EmployeeEditStateRestRequest restRequest) {
        try {
            EmployeeEditStateRequest request = constructRequest(restRequest);
            EmployeeEditStatePresenter presenter = new EmployeeEditStatePresenter();
            useCase.edit(request, presenter);
            return presenter.getResponse();
        } catch (BadRequestException exception) {
            throw new BadRequestException("Please input correct request");
        }
    }

    private EmployeeEditStateRequest constructRequest(EmployeeEditStateRestRequest restRequest) {
        EmployeeEditStateRequest request = new EmployeeEditStateRequest();
        request.setEmployeeId(restRequest.getEmployeeId());
        return request;
    }


}
