package workmotion.hrplatform.core.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.EmployeeContract;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class EmployeeCreationUseCaseTest {

    @InjectMocks
    private EmployeeCreationUseCase useCase;

    @Mock
    private EmployeeCreationOutputBoundary outputBoundary;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldReturnSuccessResponse() {
        EmployeeCreationRequest request = getEmployeeCreationRequest();

        useCase.create(request, outputBoundary);

        ArgumentCaptor<EmployeeCreationResponse> captor = ArgumentCaptor.forClass(EmployeeCreationResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());

        EmployeeCreationResponse response = captor.getValue();
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void itShouldSaveCorrectRequest() {
        EmployeeCreationRequest request = getEmployeeCreationRequest();

        useCase.create(request, outputBoundary);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).save(captor.capture());

        Employee actual = captor.getValue();

        assertThat(actual.getEmployeeName()).isEqualTo("DIMAS");
        assertThat(actual.getAge()).isEqualTo(30);
        assertThat(actual.getState()).isEqualTo(StateMachine.ADDED);
        assertThat(actual.getContractInformation()).isEqualTo(EmployeeContract.FULL_TIME.getCode());
    }

    private EmployeeCreationRequest getEmployeeCreationRequest() {
        EmployeeCreationRequest request = new EmployeeCreationRequest();
        request.setAge(30);
        request.setContractInformation(EmployeeContract.FULL_TIME.getCode());
        request.setState(StateMachine.ADDED);
        request.setEmployeeName("DIMAS");
        return request;
    }
}