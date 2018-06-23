package MS_OOP.CompanyStructure;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public abstract class Employee {
    String name;
    double baseSalary;
    Employee manager;

    static int counter;
    int employeeID =0;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;

        counter++;
        employeeID=counter;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public boolean equals(Employee other){
        return this.employeeID==other.employeeID;
    }

    @Override
    public String toString() {
        return   employeeID+" "+ name;
    }

    public String employeeStatus(){
        return employeeID + name+".";
    }

}
