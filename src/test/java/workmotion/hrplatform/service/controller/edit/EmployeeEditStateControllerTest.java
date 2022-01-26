package workmotion.hrplatform.service.controller.edit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import workmotion.hrplatform.client.request.EmployeeEditStateRestRequest;
import workmotion.hrplatform.core.edit.EmployeeEditStateInputBoundary;
import workmotion.hrplatform.core.edit.EmployeeEditStateOutputBoundary;
import workmotion.hrplatform.core.edit.EmployeeEditStateRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EmployeeEditStateControllerTest {

    @InjectMocks
    private EmployeeEditStateController controller;

    @Mock
    private EmployeeEditStateInputBoundary useCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCheck_itShouldSendCorrectRequest() {
        EmployeeEditStateRestRequest restRequest = new EmployeeEditStateRestRequest();
        restRequest.setEmployeeId(1L);
        controller.editState(restRequest);

        ArgumentCaptor<EmployeeEditStateRequest> captor = ArgumentCaptor.forClass(EmployeeEditStateRequest.class);
        verify(useCase, times(1)).edit(captor.capture(), any(EmployeeEditStateOutputBoundary.class));

        EmployeeEditStateRequest actual = captor.getValue();
        assertThat(actual.getEmployeeId()).isEqualTo(1L);
    }
}