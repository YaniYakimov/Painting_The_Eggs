package org.example;

public class Kid extends Thread{
    private PaintingTable table;
    public Kid(PaintingTable table) {
        this.table = table;
    }

    @Override
    public void run() {
        while(true) {
            Egg egg = this.table.takeFromBasket();
            System.out.println("Kid "+ this.getName() + " take egg from basket");
            if(egg == null) {
                return;
            }
            this.table.putToJar(egg);
            System.out.println("Kid "+ this.getName() + " add egg to jar");
            egg.addKid(this);
//            if(egg != null) {
//                this.table.putToJar(egg);
//                System.out.println("Kid "+ this.getName() + " add egg to jar");
//                egg.addKid(this);
//            }
        }
    }
}
