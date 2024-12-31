package controller;

import module.Membre;

import java.io.*;
import java.util.*;

/**
 * Controls operations related to members in the library system.
 * This class manages the list of members, provides search functionality,
 * and manages reading from and writing to a file.
 */
public class MembreController {
    /**
     * List of members in the library system.
     */
    public static List<Membre> membersList =new ArrayList<>();
    /**
     * Counter used to generate IDs for members.
     */
    public static int MEMBER_ID_CPT=0;
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
                if(MEMBER_ID_CPT<Integer.parseInt(memberField[0])) {
                    MEMBER_ID_CPT=Integer.parseInt(memberField[0])+1;
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
}
