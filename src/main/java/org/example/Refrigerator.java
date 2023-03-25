package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Refrigerator {
    private Map<Type, Set<Egg>> eggCartoon;
    public Refrigerator() {
        this.eggCartoon = new HashMap<>();
    }

    public void addEgg(Egg egg) {
        if(!this.eggCartoon.containsKey(egg.getType())) {
            this.eggCartoon.put(egg.getType(), new HashSet<>());
        }
        this.eggCartoon.get(egg.getType()).add(egg);
    }
}
