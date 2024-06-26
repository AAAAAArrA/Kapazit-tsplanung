package com.example.kapazitatsplanung;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class Grafik {
    private Map<String, Color> productColors;
    List<Produkte> produkteList = new ArrayList<>();
    HashMap<String, String[]> maschinen = new HashMap<>();

    HashMap<String, Integer> anzahlVonProdukte = new HashMap<>();

    public Grafik(){
        fillColors();
    }

    public void fillColors(){
        productColors = new HashMap<>();
        productColors.put("Produkte1", Color.RED);
        productColors.put("Produkte2", Color.BLUE);
        productColors.put("Produkte3", Color.GREEN);
        productColors.put("Produkte4", Color.ORANGE);
        productColors.put("Produkte5", Color.PURPLE);
        productColors.put("Produkte6", Color.YELLOW);
    }

    public void fillMap(int days){
        maschinen.put("Maschine1", new String[days]);
        maschinen.put("Maschine2", new String[days]);
        maschinen.put("Maschine3", new String[days]);
        maschinen.put("Maschine4", new String[days]);
        maschinen.put("Maschine5", new String[days]);
    }

    public void addProduktToMap(Produkte produkte){
        int index = -1;
        LinkedHashMap<String, Integer> map = produkte.getMap();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String maschine = entry.getKey(); //Maschine5
            Integer hours = entry.getValue(); // 3 index = 0
            String[] day = maschinen.get(maschine); //[]
            for(int i = 0; i < day.length; i++){
                if(hours == 1 && day[i] == null && i > index){
                    System.out.println(produkte.getName() + " " + i);
                    day[i] = produkte.getName();
                    index = i;
                    break;
                }else{
                    if(day[i] == null && day[i+ (hours -1)] == null && i > index){
                        int temp = i+(hours -1); //1
                        boolean empty = true;
                        int tempI = i;
                        while(tempI <= temp){
                            if(day[tempI] == null){
                                tempI++;
                            }else{
                                empty = false;
                                break;
                            }
                        }

                        if(empty){
                            while(i <= temp){

                                day[i] = produkte.getName();
                                i++; // 1, 2
                            }
                            index = i-1;
                            break;
                        }else{
                            continue;
                        }


                    }
                }
            }

        }

    }

    public void addToProduktList(Produkte produkte){
        produkteList.add(produkte);
    }



    public void drawChart(GraphicsContext gc) {
        createData();
        int[] machineY = {250, 200, 150, 100, 50}; // Y позиции для машин в обратном порядке
        String[] machines = {"Maschine1", "Maschine2", "Maschine3", "Maschine4", "Maschine5"};

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(50, 30, 50, 300); // Ось Y
        gc.strokeLine(50, 300, 550, 300); // Ось X

        for (int i = 0; i <= 8; i++) {
            gc.strokeText(String.valueOf(i), 50 + i * 50, 320); // Метки на оси X (часы)
        }
        printMap();
        for (int i = 0; i < machines.length; i++) {
            String name = "M"+(i+1);
            gc.strokeText(name, 10, machineY[i] + 10); // Метки на оси Y (машины)
            String[] hours = maschinen.get(machines[i]);
            for (int j = 0; j < hours.length; j++) {

                String product = hours[j];
                if (product != null) {
                    gc.setFill(productColors.getOrDefault(product, Color.GRAY)); // Устанавливаем цвет продукта
                    gc.fillRect((j + 1) * 50, machineY[i], 50, 20);
                }
            }
        }
    }

    private void createData(){
        Produkte produkte1 = new Produkte("Produkte1");
        LinkedHashMap<String, Integer> process1 = new LinkedHashMap<>();
        process1.put("Maschine1", 1);
        process1.put("Maschine2", 3);
        produkte1.setMap(process1);
        addToProduktList(produkte1);
        addProduktToMap(produkte1);

        Produkte produkte2 = new Produkte("Produkte2");
        LinkedHashMap<String, Integer> process2 = new LinkedHashMap<>();
        process2.put("Maschine4", 2);
        process2.put("Maschine5", 1);
        produkte2.setMap(process2);
        addToProduktList(produkte2);
        addProduktToMap(produkte2);
        System.out.println("Produkt 2");
        System.out.println("===========================");
        printMap();
        System.out.println("===========================");


        Produkte produkte3 = new Produkte("Produkte3");
        LinkedHashMap<String, Integer> process3 = new LinkedHashMap<>();
        process3.put("Maschine3", 2);
        produkte3.setMap(process3);
        addToProduktList(produkte3);
        addProduktToMap(produkte3);

        System.out.println("Produkt 3");
        System.out.println("===========================");
        printMap();
        System.out.println("===========================");

        Produkte produkte4 = new Produkte("Produkte4");
        LinkedHashMap<String, Integer> process4 = new LinkedHashMap<>();
        process4.put("Maschine1", 2);
        process4.put("Maschine3", 3);
        produkte4.setMap(process4);
        addToProduktList(produkte4);
        addProduktToMap(produkte4);

        System.out.println("Produkt 4");
        System.out.println("===========================");
        printMap();
        System.out.println("===========================");

        Produkte produkte5 = new Produkte("Produkte5");
        LinkedHashMap<String, Integer> process5 = new LinkedHashMap<>();
        process5.put("Maschine2", 1);
        process5.put("Maschine5", 3);
        produkte5.setMap(process5);
        addToProduktList(produkte5);
        addProduktToMap(produkte5);

        System.out.println("Produkt 5");
        System.out.println("===========================");
        printMap();
        System.out.println("===========================");

        Produkte produkte6 = new Produkte("Produkte6");
        LinkedHashMap<String, Integer> process6 = new LinkedHashMap<>();
        process6.put("Maschine3", 1);
        process6.put("Maschine4", 3);
        produkte6.setMap(process6);
        addToProduktList(produkte6);
        addProduktToMap(produkte6);
    }

    public void printMap(){
        for(Map.Entry<String, String[]> entry : maschinen.entrySet()){
            String key = entry.getKey();
            String[] values = entry.getValue();
            System.out.println(key + " : ");
            for (String name : values){
                System.out.print(name + " ");
            }
            System.out.println("\n");
        }
    }

    // Prozess 1 label

}
