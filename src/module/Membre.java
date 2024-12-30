package module;
import java.sql.Date;

/**
 * Represents a member of the library system.
 *
 *   @author Monsef
 *   @author Amine
 *   @author Ismail
 *   @author Mohammed
 */
public class Membre extends User {
    private boolean penalized=false;
    private Date finPenalite;

    /**
     * Default constructor for the Membre class.
     * Calls the default constructor of the User class.
     */
    public Membre(){
        super();
    }

    public boolean isPenalized() {
        return penalized;
    }

    public void setPenalized(boolean penalized) {
        this.penalized = penalized;
    }

    public Date getFinPenalite() {
        return finPenalite;
    }

    public void setFinPenalite(Date penalite) {
        this.finPenalite = penalite;
    }

    /**
     * Constructor for the Membre class.
     *
     * @param uid      The ID of the member.
     * @param lastName The last name of the member.
     * @param firstName The first name of the member.
     * @param password The password for the member's account.
     * @param age      The age of the member.
     * @param adresse  The address of the member.
     */
    public Membre(int uid, String lastName, String firstName, String password, int age, String adresse){
        super(lastName,firstName,password,age,adresse);
    }
    public String getFullName(){
        return this.lastName+" "+this.firstName;
    }


}
