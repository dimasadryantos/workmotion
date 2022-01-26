package workmotion.hrplatform.core.create;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@Value(staticConstructor = "valueOf")
public class EmployeeCreationResponse {

    private boolean success;

}
