package MS_OOP.CompanyStructure;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public class TestRUN {
    public static void main(String[] args) {
        SoftwareEngineer softwareEngineer1 = new SoftwareEngineer("Zbig");

        SoftwareEngineer softwareEngineer2 = new SoftwareEngineer("Isabell") ;

        SoftwareEngineer softwareEngineer3 = new SoftwareEngineer("Scully");

        SoftwareEngineer softwareEngineer4 = new SoftwareEngineer("Fox");

        SoftwareEngineer softwareEngineer5 = new SoftwareEngineer("Jade");

        TechnicalLead technicalLead = new TechnicalLead("Cleopatra");



        technicalLead.addReport(softwareEngineer1);
        technicalLead.addReport(softwareEngineer2);
        technicalLead.addReport(softwareEngineer3);
        technicalLead.addReport(softwareEngineer4);
        technicalLead.addReport(softwareEngineer5);

        System.out.println(technicalLead.getEmployeeID());
        System.out.println(softwareEngineer1.getBaseSalary());
        System.out.println(technicalLead.getBaseSalary());

        System.out.println(softwareEngineer1.getManager());


        System.out.println(softwareEngineer1.equals(softwareEngineer2));

        System.out.println(softwareEngineer1.employeeStatus());

        System.out.println(softwareEngineer1.codeAccess);

        softwareEngineer1.setCodeAccess(true);

        System.out.println(softwareEngineer1.codeAccess);

        System.out.println(softwareEngineer1.getSuccessfulCheckIns());

        technicalLead.approveCheckIn(softwareEngineer1);

        System.out.println(softwareEngineer1.getSuccessfulCheckIns());

        System.out.println("\n"+technicalLead.getTeamStatus());

        //---Business
        System.out.println("\n");

        BusinessLead businessLead = new BusinessLead("Ceasar");

        Accountant accountant1 = new Accountant("Bugs");
        Accountant accountant2 = new Accountant("Daffy");
        Accountant accountant3 = new Accountant("Marvin");

        System.out.println("Bugs base salary: "+accountant1.baseSalary);

        businessLead.addReport(accountant1, technicalLead);

        System.out.println(accountant1.getTeamSupported());


        System.out.println(accountant1.bonusBudget);
        System.out.println(accountant1.approveBonus(3000000));

        System.out.println(businessLead.hasHeadCount());

        System.out.println(technicalLead.requestBonus(businessLead, softwareEngineer2, 3000));
        System.out.println(softwareEngineer2.baseSalary);

        System.out.println(businessLead.requestBonus(accountant1, 3000));
        System.out.println("Bugs after bonus: "+accountant1.baseSalary);


        System.out.println(businessLead.approveBonus(accountant3, 4000));

        System.out.println(businessLead.approveBonus(accountant2, 2000));
        System.out.println(accountant2.baseSalary);  //no team no bonus :(
















    }
}













