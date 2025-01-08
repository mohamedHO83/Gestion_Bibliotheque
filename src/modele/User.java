package modele;

import controller.MembreController;

/**
 * Represents a user of the library system.
 *
 *  @author Monsef
 *  @author Amine
 *  @author Ismail
 *  @author Mohammed
 */
public class User{
    /**
     * Represents the ID of the user.
     */
    protected int uid;
    /**
     * Represents the last name of the user.
     */
    protected String lastName;
    /**
     * Represents the first name of the user.
     */
    protected String firstName;
    /**
     * Represents the password of the user.
     */
    protected String password;
    /**
     * Represents the age of the user.
     */
    protected int age;
    /**
     * Represents the adresse of the user.
     */
    protected String adresse;

    /**
     * Default constructor for the User class.
     */
    public User() {uid= Membre.memberIdCpt++;}
    /**
     * Parameterized constructor for the User class.
     *
     * @param lastName  The last name of the user.
     * @param firstName The first name of the user.
     * @param password  The password for the user's account.
     * @param age       The age of the user.
     * @param adresse   The address of the user.
     */
    public User(String lastName, String firstName, String password, int age, String adresse) {
        this.uid=Membre.memberIdCpt++;
        this.lastName=lastName;
        this.firstName=firstName;
        this.password=password;
        this.age=age;
        this.adresse=adresse;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getUid() {
        return uid;
    }
    /**
     * Sets the ID of the user.
     *
     * @param uid The new ID of the user.
     */
    public void setUid(int uid) {this.uid=uid;}

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }
    /**
     * Sets the age of the user.
     *
     * @param age The new age of the user.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the address of the user.
     *
     * @return The address of the user.
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * Sets the address of the user.
     *
     * @param adresse The new address of the user.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     *
     * @param password The new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name of the user.
     *
     * @param lastName The new last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the user.
     *
     * @param firstName The new first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Provides a string representation of the user instance.
     * @return A string representing the user.
     */
    @Override
    public String toString() {
        return  uid +"," + lastName  +"," + firstName +"," + password +"," + age +"," + adresse;
    }
}
