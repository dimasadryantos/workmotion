package workmotion.hrplatform.client.response;

import lombok.Getter;
import lombok.Setter;
import workmotion.hrplatform.domain.persistence.Employee;

import java.util.List;

@Getter
@Setter
public class EmployeeDetailRestResponse {
   private List<Employee> employees;
}
