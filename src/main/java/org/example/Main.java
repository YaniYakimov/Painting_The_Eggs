package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        PaintingTable table = new PaintingTable();
        Egg egg = null;
        for (int i = 0; i < 50; i++) {
            egg = new Egg(getRandomType());
            table.addToBasket(egg);
        }
        Kid kid = null;
        for (int i = 0; i < 3; i++) {
            kid = new Kid(table);
            kid.start();
        }
        Mother mother = new Mother(table);
        mother.start();
        Granny granny = new Granny();
        granny.start();
    }

    private static Type getRandomType() {
        return Type.values()[new Random().nextInt(Type.values().length)];
    }
}