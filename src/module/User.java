package module;

public class User {
    protected int uid;
    protected String lastName;
    protected String firstName;
    protected String password;
    protected static int cpt;
    //private int cin;

    public User() {uid=cpt++; }
    public User(String lastName,String firstName,String password) {
        this.uid=cpt++;
        this.lastName=lastName;
        this.firstName=firstName;
        this.password=password;
    }

    public int getUid() {
        return uid;
    }

//    public void setUid(int uid) {
//        this.uid = uid;
//    }

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
        return "{" +
                "uid=" + uid +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
