package workmotion.hrplatform.service.controller.edit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import workmotion.hrplatform.client.request.EmployeeEditStateRestRequest;
import workmotion.hrplatform.config.StateMachine;
import workmotion.hrplatform.domain.persistence.Employee;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

import java.util.Objects;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeEditStateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repository;


    @Test
    @SneakyThrows
    void itShouldReturnCorrectResponse() {
        constructEmployee();

        EmployeeEditStateRestRequest restRequest = new EmployeeEditStateRestRequest();
        restRequest.setEmployeeId(1L);

        ResultActions resultActions = mockMvc.perform(put("/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(restRequest))));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.success", is(true)));
    }

    private void constructEmployee() {
        Employee employee = new Employee();
        employee.setState(StateMachine.IN_CHECK);
        employee.setEmployeeName("Dimas");
        employee.setAge(30);
        employee.setEmployeeId(1L);
        repository.save(employee);
    }


    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }

}