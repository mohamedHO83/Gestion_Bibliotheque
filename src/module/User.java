package module;

import controller.MembreController;


public class User{
    protected int uid;
    protected String lastName;
    protected String firstName;
    protected String password;
    protected int age;
    protected String adresse;

    public User() {uid= MembreController.MEMBER_ID_CPT++;}

    public User(String lastName, String firstName, String password, int age, String adresse) {
        this.uid=MembreController.MEMBER_ID_CPT++;
        this.lastName=lastName;
        this.firstName=firstName;
        this.password=password;
        this.age=age;
        this.adresse=adresse;
    }
    public int getAge() {
        return age;
    }
    public void setUid(int uid) {this.uid=uid;}
    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getUid() {
        return uid;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return  uid +"," + lastName  +"," + firstName +"," + password +"," + age +"," + adresse;
    }
}
