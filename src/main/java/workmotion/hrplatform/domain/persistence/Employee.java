package workmotion.hrplatform.domain.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workmotion.hrplatform.config.StateMachine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "contract_information")
    private String contractInformation;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateMachine state;


}
