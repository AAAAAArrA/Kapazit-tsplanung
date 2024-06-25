package com.example.kapazitatsplanung;

import java.util.LinkedHashMap;

public class Start {
    public static void main(String[] args) {
        Grafik grafik = new Grafik();
        Produkte produkte1 = new Produkte("Produkte1");
        LinkedHashMap<String, Integer> process1 = new LinkedHashMap<>();
        process1.put("Maschine1", 1);
        process1.put("Maschine2", 3);
        produkte1.setMap(process1);
        grafik.addProduktToMap(produkte1);

//        Produkte produkte2 = new Produkte("Produkte2");
//        LinkedHashMap<String, Integer> process2 = new LinkedHashMap<>();
//        process2.put("Maschine4", 2);
//        process2.put("Maschine5", 1);
//        produkte2.setMap(process2);
//        grafik.addProduktToMap(produkte2);
//
//
//        Produkte produkte3 = new Produkte("Produkte3");
//        LinkedHashMap<String, Integer> process3 = new LinkedHashMap<>();
//        process3.put("Maschine3", 2);
//        produkte3.setMap(process3);
//        grafik.addProduktToMap(produkte3);
//
//        Produkte produkte4 = new Produkte("Produkte4");
//        LinkedHashMap<String, Integer> process4 = new LinkedHashMap<>();
//        process4.put("Maschine1", 2);
//        process4.put("Maschine3", 3);
//        produkte4.setMap(process4);
//        grafik.addProduktToMap(produkte4);
//
//        Produkte produkte5 = new Produkte("Produkte5");
//        LinkedHashMap<String, Integer> process5 = new LinkedHashMap<>();
//        process5.put("Maschine2", 1);
//        process5.put("Maschine5", 3);
//        produkte5.setMap(process5);
//        grafik.addProduktToMap(produkte5);
    }

}
