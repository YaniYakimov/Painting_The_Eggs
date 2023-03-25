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
            if(egg != null) {
                this.table.putToJar(egg);
                egg.addKid(this);
            }
        }
    }
}
