package workmotion.hrplatform.core.edit;

public interface EmployeeEditStateInputBoundary {

    void edit(EmployeeEditStateRequest request, EmployeeEditStateOutputBoundary outputBoundary);
}
