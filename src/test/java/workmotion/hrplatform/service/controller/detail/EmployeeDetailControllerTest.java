package workmotion.hrplatform.service.controller.detail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import workmotion.hrplatform.client.response.EmployeeDetailRestResponse;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.core.detail.EmployeeDetailInputBoundary;
import workmotion.hrplatform.core.detail.EmployeeDetailOutputBoundary;
import workmotion.hrplatform.core.detail.EmployeeDetailResponse;
import workmotion.hrplatform.domain.EmployeeContract;
import workmotion.hrplatform.domain.persistence.Employee;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

class EmployeeDetailControllerTest {

    @InjectMocks
    private EmployeeDetailController controller;

    @Mock
    private EmployeeDetailInputBoundary useCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldReturnCorrectResult() {
        Answer<Void> getPresenterResponse = this::getPresenterResponse;
        doAnswer(getPresenterResponse).when(useCase).detail(any());

        EmployeeDetailRestResponse controllerDetail = controller.getDetail();

        assertThat(controllerDetail.getEmployees()).isNotEmpty();

        assertEmployeeDetail(controllerDetail);
    }

    private void assertEmployeeDetail(EmployeeDetailRestResponse controllerDetail) {
        List<Employee> employees = controllerDetail.getEmployees();

        employees.forEach(employee -> {
            assertThat(employee.getState()).isBetween(StateMachine.ADDED, StateMachine.IN_CHECK);
            assertThat(employee.getEmployeeName()).isBetween("andy", "dimas");
            assertThat(employee.getAge()).isBetween(30, 31);
            assertThat(employee.getContractInformation()).isEqualTo(EmployeeContract.FULL_TIME.getCode());
            assertThat(employee.getEmployeeId()).isBetween(1L, 2L);
        });
    }

    private Void getPresenterResponse(InvocationOnMock invocationOnMock) {
        EmployeeDetailOutputBoundary presenter = invocationOnMock.getArgument(0);
        EmployeeDetailResponse response = new EmployeeDetailResponse();
        response.setEmployees(injectResponse());
        presenter.present(response);
        return null;
    }


    private List<Employee> injectResponse() {
        Employee employee1 = new Employee(1L, "dimas", EmployeeContract.FULL_TIME.getCode(), 30, StateMachine.IN_CHECK);
        Employee employee2 = new Employee(2L, "andy", EmployeeContract.FULL_TIME.getCode(), 31, StateMachine.ADDED);
        return Arrays.asList(employee1, employee2);
    }
}