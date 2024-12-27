package module;

import java.io.Serializable;

public class Admin extends User {
    public Admin(){
        super();
    }
    public Admin(String lastName, String firstName, String password, int age, String adresse){
        super(lastName,firstName,password,age,adresse);
    }
}
