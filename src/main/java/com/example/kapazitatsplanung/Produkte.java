package com.example.kapazitatsplanung;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Produkte {

    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
    private StringProperty name;

    public Produkte(String name) {

        this.name = new SimpleStringProperty(name);
    }

    public LinkedHashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, Integer> map) {
        this.map = map;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty(){
        return name;
    }


//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produkte produkte = (Produkte) o;
        return Objects.equals(name.get(), produkte.name.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.get());
    }

    @Override
    public String toString() {
        return "Produkte{" +
                "name=" + name.get() +
                '}';
    }
}
