package workmotion.hrplatform.core.detail;

import lombok.RequiredArgsConstructor;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

import java.util.List;

@RequiredArgsConstructor
public class EmployeeDetailUseCase implements EmployeeDetailInputBoundary {

    private final EmployeeRepository repository;

    @Override
    public void detail(EmployeeDetailOutputBoundary outputBoundary) {
        List<Employee> employees = getEmployee();
        constructResponse(outputBoundary, employees);
    }

    private List<Employee> getEmployee() {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            throw new IllegalStateException("Employees are empty");
        }
        return employees;
    }


    private void constructResponse(EmployeeDetailOutputBoundary outputBoundary, List<Employee> employees) {
        EmployeeDetailResponse response = new EmployeeDetailResponse();
        response.setEmployees(employees);
        outputBoundary.present(response);
    }

}
