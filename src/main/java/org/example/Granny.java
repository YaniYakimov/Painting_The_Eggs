package org.example;

import java.io.IOException;
import java.sql.SQLException;

public class Granny extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            DBManager.stat1();
            DBManager.stat2();
            DBManager.stat3();
            DBManager.stat4();
            DBManager.stat5();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            System.out.println("oelle");
        }catch (IOException e) {
            System.out.println("pak li");
        }
    }
}
