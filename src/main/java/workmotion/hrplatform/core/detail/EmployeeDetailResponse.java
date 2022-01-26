package workmotion.hrplatform.core.detail;

import lombok.Getter;
import lombok.Setter;
import workmotion.hrplatform.domain.persistence.Employee;

import java.util.List;

@Getter
@Setter
public class EmployeeDetailResponse {

    private List<Employee> employees;
}
