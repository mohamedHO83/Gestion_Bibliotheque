package module;

import java.io.Serializable;

public class Bibliothecaire extends User implements Serializable {
    public Bibliothecaire(){
        super();
    }
    public Bibliothecaire(String lastName, String firstName, String password, int age, String adresse){
        super(lastName,firstName,password,age,adresse);
    }

    @Override
    public String toString() {
        return "Admin"+ ((User)this).toString();
    }
}
