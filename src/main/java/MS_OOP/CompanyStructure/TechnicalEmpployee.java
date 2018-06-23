package MS_OOP.CompanyStructure;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public abstract class TechnicalEmpployee extends Employee{
    String name;
    int successfulCheckIns;



    public TechnicalEmpployee(String name) {
        super(name, 75000);

        this.name=name;

    }

    public String employeeStatus(){
        return employeeID+" "+ name + " has "+successfulCheckIns+" successful check ins.";
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
