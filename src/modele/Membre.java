package modele;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a member of the library system.
 *
 *   @author Monsef
 *   @author Amine
 *   @author Ismail
 *   @author Mohammed
 */
public class Membre extends User {
    private boolean penalized;
    private Date finPenalite;
    public static List<Membre> membersList =new ArrayList<>();
    /**
     * Counter used to generate IDs for members.
     */
    public static int memberIdCpt =0;

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
        penalized=false;
        finPenalite=null;
    }

    /**
     * Searches for a member by its ID in the list of members.
     *
     * @param id The ID of the member to search for.
     * @return The member with the specified ID, or null if the member doesn't exist.
     */
    public static Membre findMember(int id){
        for(Membre m:membersList){
            if(m.getUid()==id){
                return m;
            }
        }
        return null;
    }

    public static Membre findMember(String fullName){
        for(Membre m:membersList){
            if(m.getFullName().equals(fullName)){
                return m;
            }
        }
        return null;
    }

    /**
     * Reads the list of members from a CSV file and populates {@link #membersList}.
     * Each line in the file represents a member's details.
     *
     * @throws RuntimeException if there is an I/O error while reading the file.
     */
    public static void readMemberFile() {
        try{
            BufferedReader ois=new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\Membres.csv"));
            String s;
            while((s=ois.readLine())!=null) {
                if(s.isEmpty()){return;}
                Membre m=new Membre();
                String[] memberField=s.split(",");
                if(memberIdCpt <Integer.parseInt(memberField[0])) {
                    memberIdCpt =Integer.parseInt(memberField[0])+1;
                }
                m.setUid(Integer.parseInt(memberField[0]));
                m.setLastName(memberField[1]);
                m.setFirstName(memberField[2]);
                m.setPassword(memberField[3]);
                m.setAge(Integer.parseInt(memberField[4]));
                m.setAdresse(memberField[5]);
                membersList.add(m);
            }
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the list of members to a CSV file.
     * Each line in the file represents a member's details.
     *
     * @throws RuntimeException if an I/O error occurs while writing to the file.
     */
    public static void WriteMemberFile() {
        try{
            BufferedWriter oos=new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Membres.csv"));
            for(Membre m: membersList){
                oos.write(m.toString());
                oos.newLine();
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getFullName(){
        return this.lastName+" "+this.firstName;
    }

    @Override
    public String toString() {
        return  super.toString() + "," + penalized + "," + finPenalite ;
    }
}
