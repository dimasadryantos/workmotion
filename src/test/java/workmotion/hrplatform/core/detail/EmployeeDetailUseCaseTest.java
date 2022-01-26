package workmotion.hrplatform.core.detail;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class EmployeeDetailUseCaseTest {

    @InjectMocks
    private EmployeeDetailUseCase useCase;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeDetailOutputBoundary outputBoundary;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldThrowExceptionWhenGetEmptyEmployee() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> useCase.detail(outputBoundary))
                .hasMessageContaining("Employees are empty")
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void itShouldReturnCorrectEmployeeDetails() {
        when(repository.findAll()).thenReturn(injectResponse());

        useCase.detail(outputBoundary);

        ArgumentCaptor<EmployeeDetailResponse> captor = ArgumentCaptor.forClass(EmployeeDetailResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());

        EmployeeDetailResponse actual = captor.getValue();
        assertThat(actual.getEmployees()).isNotEmpty();
        assertEmployeeDetail(actual);
    }

    private void assertEmployeeDetail(EmployeeDetailResponse actual) {
        List<Employee> employees = actual.getEmployees();

        employees.forEach(employee -> {
            assertThat(employee.getState()).isBetween(StateMachine.ADDED, StateMachine.IN_CHECK);
            assertThat(employee.getEmployeeName()).isBetween("andy", "dimas");
            assertThat(employee.getAge()).isBetween(30, 31);
            assertThat(employee.getContractInformation()).isEqualTo(EmployeeContract.FULL_TIME.getCode());
            assertThat(employee.getEmployeeId()).isBetween(1L, 2L);
        });
    }


    private List<Employee> injectResponse() {
        Employee employee1 = new Employee(1L, "dimas", EmployeeContract.FULL_TIME.getCode(), 30, StateMachine.IN_CHECK);
        Employee employee2 = new Employee(2L, "andy", EmployeeContract.FULL_TIME.getCode(), 31, StateMachine.ADDED);
        return Arrays.asList(employee1, employee2);
    }
}