package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Egg {
    private org.example.Type type;
    private int boilingTime;
    private LocalDateTime puttingTime;
    private LocalDateTime gettingTime;
    private Paint paint;
    private Kid kid;
    public Egg(Type type){
        this.type = type;
        switch (type) {
            case CHICKEN -> this.boilingTime = 10;
            case GOOSE -> this.boilingTime = 5;
            default -> this.boilingTime = 3;
        }
    }

    public Type getType() {
        return type;
    }

    public int getBoilingTime() {
        return boilingTime;
    }
    public void addPuttingTime() {
        this.puttingTime = LocalDateTime.now();
    }

    public LocalDateTime getPuttingTime() {
        return puttingTime;
    }

    public void addPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }
    public void addKid(Kid kid) {
        this.kid = kid;
    }

    public Kid getKid() {
        return kid;
    }
    public void addGettingTime() {
        this.gettingTime = LocalDateTime.now();
    }

    public LocalDateTime getGettingTime() {
        return gettingTime;
    }
}
