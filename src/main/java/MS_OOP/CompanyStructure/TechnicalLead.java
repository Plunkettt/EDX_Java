package MS_OOP.CompanyStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public class TechnicalLead extends TechnicalEmpployee implements Manager{
    List<SoftwareEngineer> headCountList = new ArrayList<>();


    final int headcountPossible = 4;


    public TechnicalLead(String name) {
        super(name);
        super.baseSalary=baseSalary*1.3;


    }

    @Override
    public boolean hasHeadCount() {
        if (headcountPossible > headCountList.size()) return true;
        else return false;
    }

    public boolean addReport(SoftwareEngineer e) {
        if (hasHeadCount()) {
            headCountList.add(e);
            e.setManager(this);
            System.out.println(e + " has been added.");
            return true;
        }
        System.out.println("Sorry, headocunt exceeded. "+e+" has not been added.");
        return false;
    }

    public boolean approveCheckIn(SoftwareEngineer e) {
        if (headCountList.contains(e) && e.codeAccess){
            e.checkInCode(true);
            return true;
        }else {
            e.checkInCode(false);
        return false;}
    }

    public boolean requestBonus(BusinessLead l, Employee e, double bonus) {
        if (l.approveBonus(e, bonus)){
            e.baseSalary+=bonus;
            return true;
        }
        return false;
    }

    public String getTeamStatus() {
        String str;

        if (headCountList.size() == 0) {
            return employeeStatus() + " and no direct reports yet.";
        } else{
            str = employeeStatus() + " and is managing: \n";

            for (SoftwareEngineer se : headCountList){
                str+= se.employeeStatus()+"\n";
            }
        }
        return str;
    }
}





















