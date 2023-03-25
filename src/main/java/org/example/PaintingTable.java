package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (basket.size() > 0) {
            return this.basket.remove(this.basket.size()-1);
        }
        return null;
    }

    public void putToJar(Egg egg) {
        Paint paint = null;
        switch (paint) {
            case GREEN -> paint = Paint.GREEN;
            case RED -> paint = Paint.RED;
            case BLUE -> paint = Paint.BLUE;
            case YELLOW -> paint = Paint.YELLOW;
            default -> paint = Paint.ORANGE;
        }
        this.jars.get(paint).put(egg);
        egg.addPaint(paint);
    }
    public Egg takeFromJar() {
        Paint paint = null;
        switch (paint) {
            case GREEN -> paint = Paint.GREEN;
            case RED -> paint = Paint.RED;
            case BLUE -> paint = Paint.BLUE;
            case YELLOW -> paint = Paint.YELLOW;
            default -> paint = Paint.ORANGE;
        }
        return this.jars.get(paint).take();
    }
}
