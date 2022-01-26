package workmotion.hrplatform.core.edit;

import lombok.RequiredArgsConstructor;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeEditStateUseCase implements EmployeeEditStateInputBoundary {

    private final EmployeeRepository employeeRepository;


    @Override
    public void edit(EmployeeEditStateRequest request, EmployeeEditStateOutputBoundary outputBoundary) {
        Employee employee = getEmployee(request);
        doValidateEmployeeState(employee);
        doConstructResponse(outputBoundary);
    }

    private void doConstructResponse(EmployeeEditStateOutputBoundary outputBoundary) {
        EmployeeEditStateResponse response = new EmployeeEditStateResponse();
        response.setSuccess(Boolean.TRUE);
        outputBoundary.present(response);
    }

    private void doValidateEmployeeState(Employee employee) {
        StateMachine currentState = employee.getState();
        if (currentState.equals(StateMachine.ACTIVE)) {
            throw new IllegalStateException("Employee is active");
        }
        doSaveValidState(employee, currentState);
    }

    private void doSaveValidState(Employee employee, StateMachine currentState) {
        employee.setState(currentState.nextState());
        employeeRepository.save(employee);
    }

    private Employee getEmployee(EmployeeEditStateRequest request) {
        Optional<Employee> employeeRepositoryById = employeeRepository.findById(request.getEmployeeId());

        if (employeeRepositoryById.isEmpty()) {
            throw new IllegalStateException("Employee not found");
        }
        return employeeRepositoryById.get();
    }


}
