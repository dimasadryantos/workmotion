package workmotion.hrplatform.service.controller.create;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import workmotion.hrplatform.client.request.EmployeeCreationRestRequest;
import workmotion.hrplatform.core.create.EmployeeCreationInputBoundary;
import workmotion.hrplatform.core.create.EmployeeCreationOutputBoundary;
import workmotion.hrplatform.core.create.EmployeeCreationRequest;
import workmotion.hrplatform.domain.EmployeeContract;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EmployeeCreationControllerTest {


    @InjectMocks
    private EmployeeCreationController controller;

    @Mock
    private EmployeeCreationInputBoundary useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldConstructCorrectRequest() {
        EmployeeCreationRestRequest restRequest = getEmployeeCreationRestRequest();

        controller.create(restRequest);

        ArgumentCaptor<EmployeeCreationRequest> captor = ArgumentCaptor.forClass(EmployeeCreationRequest.class);
        verify(useCase, times(1)).create(captor.capture(), any(EmployeeCreationOutputBoundary.class));

        EmployeeCreationRequest actual = captor.getValue();

        assertThat(actual.getEmployeeName()).isEqualTo("DIMAS");
        assertThat(actual.getAge()).isEqualTo(30);
        assertThat(actual.getContractInformation()).isEqualTo(EmployeeContract.FULL_TIME.getCode());
    }


    private EmployeeCreationRestRequest getEmployeeCreationRestRequest() {
        EmployeeCreationRestRequest restRequest = new EmployeeCreationRestRequest();
        restRequest.setEmployeeName("DIMAS");
        restRequest.setAge(30);
        restRequest.setContractInformation(EmployeeContract.FULL_TIME.getCode());
        return restRequest;
    }
}