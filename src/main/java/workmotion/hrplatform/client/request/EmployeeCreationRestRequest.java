package workmotion.hrplatform.client.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCreationRestRequest {

    private String employeeName;

    private String contractInformation;

    private int age;

}
