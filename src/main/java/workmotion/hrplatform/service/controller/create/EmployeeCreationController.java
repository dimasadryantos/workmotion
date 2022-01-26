package workmotion.hrplatform.service.controller.create;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import workmotion.hrplatform.client.request.EmployeeCreationRestRequest;
import workmotion.hrplatform.client.response.EmployeeCreationRestResponse;
import workmotion.hrplatform.core.create.EmployeeCreationInputBoundary;
import workmotion.hrplatform.core.create.EmployeeCreationRequest;
import workmotion.hrplatform.exception.BadRequestException;

@RestController
@RequiredArgsConstructor
public class EmployeeCreationController {

    private final EmployeeCreationInputBoundary useCase;


    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeCreationRestResponse create(@RequestBody EmployeeCreationRestRequest restRequest) {
        try {
            EmployeeCreationRequest request = doConstructRequest(restRequest);
            EmployeeCreationPresenter presenter = new EmployeeCreationPresenter();
            useCase.create(request, presenter);
            return presenter.getResponse();
        } catch (BadRequestException exception) {
            throw new BadRequestException("Please input valid requests");
        }
    }

    private EmployeeCreationRequest doConstructRequest(EmployeeCreationRestRequest restRequest) {
        EmployeeCreationRequest request = new EmployeeCreationRequest();
        request.setEmployeeName(restRequest.getEmployeeName());
        request.setAge(restRequest.getAge());
        request.setContractInformation(restRequest.getContractInformation());
        return request;
    }
}
