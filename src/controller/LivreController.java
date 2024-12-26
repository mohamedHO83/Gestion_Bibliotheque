package controller;

import interfaces.LivreFile;
import module.Livre;
import java.util.*;
import java.io.*;

public class LivreController implements LivreFile {
    public static List<Livre> livreslist=new ArrayList<>();

    @Override
    public void readLivreFile() {
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Livres.csv"));
            Livre l=new Livre();
            while((l=(Livre)ois.readObject())!=null) {
                livreslist.add(l);
            }
            ois.close();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\ibrah\\OneDrive\\Bureau\\ProjetJava\\src\\Livres.csv"));
            Livre l=new Livre();
            while((l=(Livre) ois.readObject())!=null) {
                livreslist.add(l);
            }
            ois.close();
        } catch (IOException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void WriteLivreFile() {

    }
}
