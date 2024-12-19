package module;

public class User {
    private int uid;
    private String lastName;
    private String firstName;
    private String role;
    private static int cpt;

    public User() {uid=cpt++; }
    public User(String lastName,String firstName,int cin,String role) {
        this.uid=cpt++;
        this.lastName=lastName;
        this.firstName=firstName;
        this.role=role;
    }

    public int getUid() {
        return uid;
    }

//    public void setUid(int uid) {
//        this.uid = uid;
//    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
