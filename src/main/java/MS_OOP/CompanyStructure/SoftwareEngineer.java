package MS_OOP.CompanyStructure;

/**
 * @author Ja on 22/06/2018.
 * @project EDX,
 */
public class SoftwareEngineer extends TechnicalEmpployee {

    boolean codeAccess =false;


    public SoftwareEngineer(String name) {
        super(name);
        this.successfulCheckIns=0;


    }

    public boolean getCodeAccess() {
        return codeAccess;
    }

    public void setCodeAccess(boolean codeAccess) {
        this.codeAccess = codeAccess;
    }

    public int getSuccessfulCheckIns() {
        return successfulCheckIns;
    }

    public boolean checkInCode(boolean apporval){
        if (apporval){
            successfulCheckIns++;
            return true;
        }else {
            setCodeAccess(false);
            return false;}
    }
}
