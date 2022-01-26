package workmotion.hrplatform.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workmotion.hrplatform.core.create.EmployeeCreationInputBoundary;
import workmotion.hrplatform.core.create.EmployeeCreationUseCase;
import workmotion.hrplatform.core.detail.EmployeeDetailInputBoundary;
import workmotion.hrplatform.core.detail.EmployeeDetailUseCase;
import workmotion.hrplatform.core.edit.EmployeeEditStateInputBoundary;
import workmotion.hrplatform.core.edit.EmployeeEditStateUseCase;
import workmotion.hrplatform.domain.persistence.EmployeeRepository;

@Configuration
public class ServiceConfig {


    @Bean
    public EmployeeCreationInputBoundary creationUseCase(EmployeeRepository repository) {
        return new EmployeeCreationUseCase(repository);
    }


    @Bean
    public EmployeeDetailInputBoundary detailUseCase(EmployeeRepository repository) {
        return new EmployeeDetailUseCase(repository);
    }

    @Bean
    public EmployeeEditStateInputBoundary editUseCase(EmployeeRepository repository) {
        return new EmployeeEditStateUseCase(repository);
    }


}
