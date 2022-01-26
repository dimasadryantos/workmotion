package workmotion.hrplatform.domain.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.EmployeeContract;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void itShouldSaveEmployee() {

        employeeRepository.save(getEmployee());

        Optional<Employee> employeeRepositoryById = employeeRepository.findById(1L);

        employeeRepositoryById.ifPresent(employee -> {
            assertThat(employee.getEmployeeId()).isEqualTo(1L);
            assertThat(employee.getEmployeeName()).isEqualTo("dimas");
            assertThat(employee.getAge()).isEqualTo(30);
            assertThat(employee.getState()).isEqualTo(StateMachine.IN_CHECK);
        });
    }


    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setState(StateMachine.IN_CHECK);
        employee.setEmployeeName("dimas");
        employee.setContractInformation(EmployeeContract.FULL_TIME.getCode());
        employee.setAge(30);
        employee.setEmployeeId(1L);
        return employee;
    }

}