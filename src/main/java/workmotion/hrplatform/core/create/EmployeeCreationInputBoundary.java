package workmotion.hrplatform.core.create;

public interface EmployeeCreationInputBoundary {

    void create(EmployeeCreationRequest request, EmployeeCreationOutputBoundary output);
}
