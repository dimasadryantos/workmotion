package workmotion.hrplatform.core.create;

import lombok.RequiredArgsConstructor;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

@RequiredArgsConstructor
public class EmployeeCreationUseCase implements EmployeeCreationInputBoundary {

    private final EmployeeRepository repository;


    @Override
    public void create(EmployeeCreationRequest request, EmployeeCreationOutputBoundary outputBoundary) {
        doSaveNewEmployee(request);
        doConstructResponse(outputBoundary);
    }

    private void doConstructResponse(EmployeeCreationOutputBoundary outputBoundary) {
        outputBoundary.present(EmployeeCreationResponse.valueOf(Boolean.TRUE));
    }


    private void doSaveNewEmployee(EmployeeCreationRequest request) {
        Employee employee = new Employee();
        employee.setAge(request.getAge());
        employee.setEmployeeName(request.getEmployeeName());
        employee.setContractInformation(request.getContractInformation());
        employee.setState(StateMachine.ADDED);
        repository.save(employee);
    }

}
