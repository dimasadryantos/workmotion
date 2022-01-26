package workmotion.hrplatform.domain;

import lombok.Getter;

@Getter
public enum EmployeeContract {

    FULL_TIME("FULL_TIME", "Full Time Employee"),
    CONTRACT("CONTRACT", "Contract Employee");


    private String name;

    private String code;


    EmployeeContract(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
