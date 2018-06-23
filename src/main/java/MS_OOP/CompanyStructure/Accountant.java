package MS_OOP.CompanyStructure;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public class Accountant extends BusinessEmployee{

    TechnicalLead teamSupported = null;

    public Accountant(String name) {
        super(name);

        this.bonusBudget=0;
    }

    public TechnicalLead getTeamSupported() {
        return teamSupported;
    }

    public void supportTeam(TechnicalLead lead){
        teamSupported=lead;

        for (Employee e : lead.headCountList){
            bonusBudget+=e.baseSalary*1.1;
        }
    }

    public boolean approveBonus(double bonus){
        return (bonus<bonusBudget && !(teamSupported.equals(null)));
    }

    public String employeeStatus(){
        return employeeID+" "+name+" with a budget of "+bonusBudget+" is supporting "+getTeamSupported();
    }
}
