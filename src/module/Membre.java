package module;


public class Membre extends User {
    public Membre(){
        super();
    }
    public Membre(int uid,String lastName,String firstName,String password,int age,String adresse){
        super(lastName,firstName,password,age,adresse);
    }


}
