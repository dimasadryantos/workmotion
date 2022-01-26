package workmotion.hrplatform.service.controller.detail;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import workmotion.hrplatform.client.response.EmployeeDetailRestResponse;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.EmployeeContract;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeDetailControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = null;

    @Autowired
    private EmployeeRepository repository;


    @Test
    @SneakyThrows
    void itShouldReturnCorrectResponse() {
        constructEmployee();

        MvcResult result = mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        EmployeeDetailRestResponse restResponse = getMapper().readValue(contentAsString, EmployeeDetailRestResponse.class);
        List<Employee> employees = restResponse.getEmployees();

        assertEmployeeDetail(employees);
    }

    private void assertEmployeeDetail(List<Employee> employees) {
        employees.forEach(employee -> {
            assertThat(employee.getEmployeeName()).isNotEmpty();
            assertThat(employee.getState()).isIn(StateMachine.ADDED, StateMachine.IN_CHECK, StateMachine.APPROVED, StateMachine.ACTIVE);
            assertThat(employee.getAge()).isNotZero();
            assertThat(employee.getEmployeeId()).isNotZero();
            assertThat(employee.getContractInformation()).isNotEmpty();
        });
    }

    private void constructEmployee() {
        Employee employee = new Employee();
        employee.setState(StateMachine.IN_CHECK);
        employee.setEmployeeName("dimas");
        employee.setContractInformation(EmployeeContract.FULL_TIME.getCode());
        employee.setAge(30);
        employee.setEmployeeId(1L);
        employee.setContractInformation("CONTRACT");
        repository.save(employee);
    }


    public static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper;
        }
        return mapper;
    }
}