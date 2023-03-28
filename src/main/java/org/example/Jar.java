package org.example;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Jar {
    private PaintingTable table;
    private Paint paint;
    private List<Egg> capacity = new ArrayList<>();
    private final static int MAX_CAPACITY = 2;
    public Jar(Paint paint, PaintingTable table) {
        this.paint = paint;
        this.table = table;
    }
    public synchronized void put(Egg egg) {
        try {
            while(capacity.size() >= MAX_CAPACITY) {
                wait();
            }
            egg.addPuttingTime();
            this.capacity.add(egg);
            notifyAll();
        }catch (InterruptedException e) {
            System.out.println("opa");
        }
    }
    public synchronized Egg take() {
        try {
            while (capacity.size() <= 0) {
                wait();
            }
            Egg egg = this.capacity.get(this.capacity.size()-1);
            this.capacity.remove(egg);
            System.out.println("Quantity in this jar - " + this.capacity.size());
            if(egg.getPuttingTime().plusSeconds(egg.getBoilingTime()).isBefore(LocalDateTime.now())) {
                Thread.sleep(egg.getBoilingTime());
            }
            notifyAll();
            return egg;
        }catch (InterruptedException e) {
            System.out.println("opa2");
            return null;
        }
    }
}
