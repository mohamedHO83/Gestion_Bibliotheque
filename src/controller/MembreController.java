package controller;

import interfaces.MembreFile;
import module.Membre;

import java.io.*;
import java.util.*;

public class MembreController implements MembreFile{
    public static List<Membre> membersList =new ArrayList<>();
    @Override
    public void readMemberFile() {
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Membres.csv"));
            Membre m=new Membre();
            while((m=(Membre)ois.readObject())!=null) {
                membersList.add(m);
            }
            ois.close();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void WriteMemberFile() {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Membres.csv"));
            for(Membre m: membersList){
                oos.writeObject(m);
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
