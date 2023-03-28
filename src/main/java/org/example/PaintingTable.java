package org.example;

import java.util.*;

public class PaintingTable {
    private Map<Paint, Jar> jars = new HashMap<>();
    private List<Egg> basket = new ArrayList<>();
    public PaintingTable() {
        for (int i = 0; i < 5; i++) {
            jars.put(Paint.values()[i], new Jar(Paint.values()[i], this));
        }
    }
    public void addToBasket(Egg egg) {
        this.basket.add(egg);
    }

    public synchronized Egg takeFromBasket() {
        System.out.println(this.basket.size());
        if (basket.size() > 0) {
            return this.basket.remove(this.basket.size()-1);
        }
        return null;
    }

    public void putToJar(Egg egg) {
        int chance = new Random().nextInt(5);
        Paint paint = null;
        switch (chance) {
            case 0 -> paint = Paint.GREEN;
            case 1 -> paint = Paint.RED;
            case 2 -> paint = Paint.BLUE;
            case 3 -> paint = Paint.YELLOW;
            default -> paint = Paint.ORANGE;
        }
        this.jars.get(paint).put(egg);
        egg.addPaint(paint);
    }
    public Egg takeFromJar() {
        int chance = new Random().nextInt(5);
        Paint paint = null;
        switch (chance) {
            case 0 -> paint = Paint.GREEN;
            case 1 -> paint = Paint.RED;
            case 2 -> paint = Paint.BLUE;
            case 3 -> paint = Paint.YELLOW;
            default -> paint = Paint.ORANGE;
        }
        return this.jars.get(paint).take();
    }
}
