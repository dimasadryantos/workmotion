package workmotion.hrplatform.service.controller.create;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import workmotion.hrplatform.client.request.EmployeeCreationRestRequest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EmployeeCreationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @SneakyThrows
    void itShouldCreateNewEmployee() {

        EmployeeCreationRestRequest restRequest = new EmployeeCreationRestRequest();
        restRequest.setEmployeeName("dimas");
        restRequest.setAge(30);
        restRequest.setContractInformation("CONTRACT");

        ResultActions resultActions = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(restRequest))));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.success", is(true)));
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