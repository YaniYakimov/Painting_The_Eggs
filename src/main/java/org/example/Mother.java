package org.example;

import java.util.Random;

public class Mother extends Thread{
    private PaintingTable table;
    private Refrigerator refrigerator;
    private Father father;
    public Mother(PaintingTable table) {
        this.table = table;
        this.refrigerator = new Refrigerator();
        this.father = new Father();
    }

    @Override
    public void run() {
        while(true) {
            Egg egg = this.table.takeFromJar();
            egg.addGettingTime();
            int chance = new Random().nextInt(100);
            if(chance < 20) {
                egg.addPaint(Paint.PATTERNED);
            }
            this.refrigerator.addEgg(egg);
            this.father.createFile(egg);
        }
    }
}
