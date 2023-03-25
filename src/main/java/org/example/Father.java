package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Father extends Thread{
    public void createFile(Egg egg) {
        File f = new File(egg.getType()+".txt");
        if(!f.exists()) {
            try {
                f.createNewFile();
                String patterned = null;
                if(egg.getPaint() == Paint.PATTERNED) {
                    patterned = "patterned";
                }
                else {
                    patterned = "not patterned";
                }
                String text = egg.getPaint() + " - " + egg.getKid().getName() + " - " + patterned + egg.getGettingTime()+"\n";
                Files.write(f.toPath(), text.getBytes(), StandardOpenOption.APPEND);
                DBManager.getInstance().insertIntoDataBase(egg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
