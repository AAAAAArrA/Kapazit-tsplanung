package com.example.kapazitatsplanung;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Produkte {

    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
    private String name;

    public Produkte(String name) {

        this.name = name;
    }

    public LinkedHashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, Integer> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
