package workmotion.hrplatform.core.edit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.EmployeeContract;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeEditStateUseCaseTest {

    @InjectMocks
    private EmployeeEditStateUseCase useCase;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeEditStateOutputBoundary outputBoundary;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldThrowExceptionWhenEmployeeNotFound() {
        EmployeeEditStateRequest request = constructRequest();
        assertThatThrownBy(() -> useCase.edit(request, outputBoundary))
                .hasMessageContaining("Employee not found")
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void itShouldThrowExceptionWhenStateInValid() {
        Employee employee = constructEmployee(StateMachine.ACTIVE);
        when(repository.findById(any())).thenReturn(Optional.of(employee));
        EmployeeEditStateRequest request = constructRequest();

        assertThatThrownBy(() -> useCase.edit(request, outputBoundary))
                .hasMessageContaining("Employee is active")
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void itShouldReturnCorrectResponse() {
        when(repository.findById(any())).thenReturn(Optional.of(constructEmployee(StateMachine.ADDED)));
        EmployeeEditStateRequest request = constructRequest();

        useCase.edit(request, outputBoundary);

        ArgumentCaptor<EmployeeEditStateResponse> captor = ArgumentCaptor.forClass(EmployeeEditStateResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());

        EmployeeEditStateResponse actual = captor.getValue();
        assertThat(actual.isSuccess()).isTrue();
    }

    @Test
    @DisplayName("It should save valid sate when transition from ADDED to IN_CHECK")
    void itShouldSaveValidAddedState() {
        when(repository.findById(any())).thenReturn(Optional.of(constructEmployee(StateMachine.ADDED)));
        EmployeeEditStateRequest request = constructRequest();

        useCase.edit(request, outputBoundary);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(repository, times(1)).save(captor.capture());

        Employee actual = captor.getValue();

        assertThat(actual.getState()).isEqualTo(StateMachine.IN_CHECK);
    }

    @Test
    @DisplayName("It should save valid sate when transition from IN_CHECK to APPROVED")
    void itShouldSaveValidInCheckState() {
        when(repository.findById(any())).thenReturn(Optional.of(constructEmployee(StateMachine.IN_CHECK)));
        EmployeeEditStateRequest request = constructRequest();

        useCase.edit(request, outputBoundary);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(repository, times(1)).save(captor.capture());

        Employee actual = captor.getValue();

        assertThat(actual.getState()).isEqualTo(StateMachine.APPROVED);
    }


    @Test
    @DisplayName("It should save valid sate when transition from APPROVED to ACTIVE")
    void itShouldSaveValidApproveState() {
        when(repository.findById(any())).thenReturn(Optional.of(constructEmployee(StateMachine.APPROVED)));
        EmployeeEditStateRequest request = constructRequest();

        useCase.edit(request, outputBoundary);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(repository, times(1)).save(captor.capture());

        Employee actual = captor.getValue();

        assertThat(actual.getState()).isEqualTo(StateMachine.ACTIVE);
    }

    private EmployeeEditStateRequest constructRequest() {
        EmployeeEditStateRequest request = new EmployeeEditStateRequest();
        request.setEmployeeId(1L);
        return request;
    }

    private Employee constructEmployee(StateMachine stateMachine) {
        Employee employee = new Employee();
        employee.setState(stateMachine);
        employee.setEmployeeName("dimas");
        employee.setContractInformation(EmployeeContract.FULL_TIME.getCode());
        employee.setAge(30);
        employee.setEmployeeId(1L);
        return employee;
    }


}