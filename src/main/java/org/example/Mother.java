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
            System.out.println("Mother " + this.getName() + " take egg from jar");
            egg.addGettingTime();
            int chance = new Random().nextInt(100);
            if(chance < 20) {
                egg.addPaint(Paint.PATTERNED);
                System.out.println("Mother " + this.getName() + " make egg with patterned color");
            }
            this.refrigerator.addEgg(egg);
            System.out.println("Mother " + this.getName() + " add egg to refrigerator");
            this.father.createFile(egg);
        }
    }
}
