package module;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(){
        super();
    }
    public Admin(String lastName,String firstName,String password){
        super(lastName,firstName,password);
    }

    @Override
    public String toString() {
        return "Admin"+ ((User)this).toString();
    }
}
