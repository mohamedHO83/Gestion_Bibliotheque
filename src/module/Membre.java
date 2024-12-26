package module;

import java.io.Serializable;

public class Membre extends User implements Serializable {
    public Membre(){
        super();
    }
    public Membre(String lastName,String firstName,String password){
        super(lastName,firstName,password);
    }

    @Override
    public String toString() {
        return "Membre"+((User)this).toString();
    }
}
