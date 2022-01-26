package workmotion.hrplatform.core.create;

import lombok.Getter;
import lombok.Setter;
import workmotion.hrplatform.config.StateMachine;

@Getter
@Setter
public class EmployeeCreationRequest {

    private String employeeName;

    private String contractInformation;

    private int age;

    private StateMachine state;


}
