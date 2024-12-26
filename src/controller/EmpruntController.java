package controller;

import interfaces.EmpruntFile;
import module.Emprunt;

import java.io.*;
import java.util.*;

public class EmpruntController implements EmpruntFile {
    List<Emprunt> empruntList=new ArrayList<>();
    @Override
    public void readEmpruntFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Emprunts.csv"));
            Emprunt e=new Emprunt();
            while((e=(Emprunt)ois.readObject())!=null){
                empruntList.add(e);
            }
            ois.close();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeEmpruntFile() {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Emprunts.csv"));
            for(Emprunt e:empruntList){
                oos.writeObject(e);
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
