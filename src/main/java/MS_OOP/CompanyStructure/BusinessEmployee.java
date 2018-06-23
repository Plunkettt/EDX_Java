package MS_OOP.CompanyStructure;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public class BusinessEmployee extends Employee{

    double bonusBudget=0;

    public BusinessEmployee(String name) {
        super(name, 50000);
    }

    public double getBonusBudget() {
        return bonusBudget;
    }

    public String employeeStatus(){
        return employeeID+" "+name+" with a budget of "+bonusBudget;
    }
}
