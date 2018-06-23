package MS_OOP.CompanyStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public class BusinessLead extends BusinessEmployee implements Manager{
    List<Accountant> headCountList = new ArrayList<>();

    int headcountPossible = 10;

    public BusinessLead(String name) {
        super(name);
        super.baseSalary=baseSalary*2;
    }


    @Override
    public boolean hasHeadCount() {
        return (headcountPossible>headCountList.size());
    }

    boolean addReport(Accountant a, TechnicalLead supportedTeam){
        if (hasHeadCount()){
            headCountList.add(a);
            System.out.println(a.employeeStatus()+" has been added.");
            this.bonusBudget+=a.baseSalary*1.1;
            a.supportTeam(supportedTeam);
            return true;
        }
        System.out.println("boo");
        return false;
    }

    boolean requestBonus(Employee e, double bonus) {
        if (bonus<this.bonusBudget){
            this.bonusBudget-=bonus;
            e.baseSalary+=bonus;
            System.out.println("Bonus for "+e+" approved.");
            return true;
        }
        System.out.println("The bonnus budget is not sufficient. Poor "+e);
        return false;
    }

    boolean approveBonus(Employee e, double bonus){

        try {
            for (Accountant a : headCountList) {
                if (a.getTeamSupported().equals(e.getManager())) {
                    if (a.bonusBudget > bonus) {
                        System.out.println("Bonus for " + e + " approved.");
                        a.baseSalary+=bonus;
                        a.bonusBudget-=bonus;
                        return true;
                    }
                }
            }
        }catch (NullPointerException e1){
            System.out.println("This accountant is not assigned to any team. No bonus budget, no bonus, sorry.");
        }
        return false;
    }

    @Override
    public String getTeamStatus() {
        String str;

        if (headCountList.size() == 0) {
            return employeeStatus() + " and no direct reports yet.";
        } else{
            str = employeeStatus() + " and is managing: \n";

            for (Accountant acc : headCountList){
                str+= acc.employeeStatus()+"\n";
            }
        }
        return str;
    }
}
