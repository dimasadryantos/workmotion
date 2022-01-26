package workmotion.hrplatform.service.controller.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import workmotion.hrplatform.client.response.EmployeeDetailRestResponse;
import workmotion.hrplatform.core.detail.EmployeeDetailInputBoundary;

@RestController
@RequiredArgsConstructor
public class EmployeeDetailController {

    private final EmployeeDetailInputBoundary useCase;

    @GetMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDetailRestResponse getDetail() {
        EmployeeDetailPresenter presenter = new EmployeeDetailPresenter();
        useCase.detail(presenter);
        return presenter.getRestResponse();
    }
}
