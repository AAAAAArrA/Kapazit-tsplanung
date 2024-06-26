package com.example.kapazitatsplanung;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GrafikController {

    private HelloApplication main;

    @FXML
    private Button buildButton;
    @FXML
    private VBox root;

    @FXML
    private TextField period;

    private int periode;

    public Map<String, Color> getProductColors() {
        return productColors;
    }

    private Map<String, Color> productColors;
    private Map<Produkte, Integer> produkteMap = new LinkedHashMap<>();

    private Grafik grafik = new Grafik();

    HashMap<String, String[]> maschinen = new HashMap<>();

    public void setMainApp(HelloApplication main){
        this.main = main;


        setProdukteList(this.main.getProdukteData());

    }

    @FXML
    public void initialize() {
        // Добавление слушателя изменений текста для поля period
        period.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                periode = Integer.parseInt(newValue);
            } catch (NumberFormatException e) {
                periode = 0; // Значение по умолчанию, если ввод некорректен
            }
        });

        // Установка обработчика на кнопку buildButton
//        buildButton.setOnAction(event -> buildAction());
    }

    public void setProdukteList(List<Produkte> produkteList) {
        for (Produkte produkte : produkteList) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            Label label = new Label(produkte.getName());
            TextField textField = new TextField();
            textField.setPrefWidth(50);

            // Добавление слушателя изменений текста
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    int number = Integer.parseInt(newValue);
                    produkteMap.put(produkte, number);
                } catch (NumberFormatException e) {
                    produkteMap.remove(produkte);
                }
            });

            hbox.getChildren().addAll(label, textField);
            root.getChildren().add(hbox);
        }
    }

    public int getPeriode() {
        return periode;
    }

    public void buildGrafik(){
        int per = getPeriode();
        System.out.println("Days in buildGrafik" + per*8);
        makeDataMap(per*8);

        showGrafik();

    }
    private void showGrafik() {
        // Получаем количество часов
        int totalHours = getPeriode() * 8;

        // Создаем холст с размерами в зависимости от количества часов
        double canvasWidth = 50 + totalHours * 50;
        Canvas canvas = new Canvas(canvasWidth, 350);  // Увеличиваем ширину в зависимости от totalHours
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Рисуем график
        drawChart(gc, totalHours, maschinen, productColors);

        // Оборачиваем холст в ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(800); // Устанавливаем видимую область
        scrollPane.setContent(canvas);

        VBox legend = createLegend();

        // Создаем новое окно для отображения графика
        Stage chartStage = new Stage();
        chartStage.setTitle("Kapazitätsplanung");

        HBox rootPane = new HBox();
        rootPane.getChildren().addAll(scrollPane, legend);

//        Pane rootPane = new Pane();
//        rootPane.getChildren().add(scrollPane);

        Scene scene = new Scene(rootPane, 1000, 400);
        chartStage.setScene(scene);
        chartStage.show();
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

    public void makeDataMap(int days){
        fillColors();
        fillMap(days);
        System.out.println("days in makeDataMap");
        while (!produkteMap.isEmpty()) {
            Map<Produkte, Integer> tempMap = new LinkedHashMap<>(produkteMap); // Создаем временную копию карты для итерации
            for (Map.Entry<Produkte, Integer> entry : tempMap.entrySet()) {
                Produkte produkte = entry.getKey();
                int quantity = entry.getValue();
                if (quantity > 0) {
                    addProduktToMap(produkte);
                    quantity--;
                    if (quantity > 0) {
                        produkteMap.put(produkte, quantity); // Обновляем количество в оригинальной карте
                    } else {
                        produkteMap.remove(produkte); // Удаляем пару, если количество равно нулю
                    }
                }
            }
        }
    }

    public void addProduktToMap(Produkte produkte){
        int index = -1;
        LinkedHashMap<String, Integer> map = produkte.getMap();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String maschine = entry.getKey(); //Maschine5
            Integer hours = entry.getValue(); // 3 index = 0
            String[] day = maschinen.get(maschine); //[]
            System.out.println("Length of array " + day.length);
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

//    public void drawChart(GraphicsContext gc) {
//        int[] machineY = {250, 200, 150, 100, 50}; // Y позиции для машин в обратном порядке
//        String[] machines = {"Maschine1", "Maschine2", "Maschine3", "Maschine4", "Maschine5"};
//
//        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(2);
//        gc.strokeLine(50, 30, 50, 300); // Ось Y
//        gc.strokeLine(50, 300, 550, 300); // Ось X
//
//        for (int i = 0; i <= 8; i++) {
//            gc.strokeText(String.valueOf(i), 50 + i * 50, 320); // Метки на оси X (часы)
//        }
//        for (int i = 0; i < machines.length; i++) {
//            String name = "M"+(i+1);
//            gc.strokeText(name, 10, machineY[i] + 10); // Метки на оси Y (машины)
//            String[] hours = maschinen.get(machines[i]);
//            for (int j = 0; j < hours.length; j++) {
//
//                String product = hours[j];
//                if (product != null) {
//                    gc.setFill(productColors.getOrDefault(product, Color.GRAY)); // Устанавливаем цвет продукта
//                    gc.fillRect((j + 1) * 50, machineY[i], 50, 20);
//                }
//            }
//        }
//    }
//public void drawChart(GraphicsContext gc, int period, Map<String, String[]> maschinen, Map<String, Color> productColors) {
//    int totalHours = period; // Общее количество часов (8 часов в день)
//    System.out.println("Total hours " + totalHours);
//    int[] machineY = {250, 200, 150, 100, 50}; // Y позиции для машин в обратном порядке
//    String[] machines = {"Maschine1", "Maschine2", "Maschine3", "Maschine4", "Maschine5"};
//
//    // Устанавливаем размеры холста в соответствии с общим количеством часов
//    Canvas canvas = gc.getCanvas();
//    canvas.setWidth(50 + totalHours * 50); // Устанавливаем ширину холста
//
//    gc.setStroke(Color.BLACK);
//    gc.setLineWidth(2);
//    gc.strokeLine(50, 30, 50, 300); // Ось Y
//    gc.strokeLine(50, 300, 50 + totalHours * 50, 300); // Ось X
//
//    // Метки на оси X (часы)
//    for (int i = 0; i <= totalHours; i++) {
//        gc.strokeText(String.valueOf(i), 50 + i * 50, 320);
//    }
//
//    // Метки на оси Y (машины)
//    for (int i = 0; i < machines.length; i++) {
//        String name = "M" + (i + 1);
//        gc.strokeText(name, 10, machineY[i] + 10);
//        String[] hours = maschinen.getOrDefault(machines[i], new String[totalHours]);
//
//        for (int j = 0; j < hours.length; j++) {
//            String product = hours[j];
//            if (product != null) {
//                gc.setFill(productColors.getOrDefault(product, Color.GRAY)); // Устанавливаем цвет продукта
//                gc.fillRect(50 + j * 50, machineY[i], 50, 20); // Рисуем прямоугольник для продукта
//            }
//        }
//    }
//}

    private void drawChart(GraphicsContext gc, int totalHours, Map<String, String[]> maschinen, Map<String, Color> productColors) {
        int[] machineY = {250, 200, 150, 100, 50};  // Y позиции для машин в обратном порядке
        String[] machines = {"Maschine1", "Maschine2", "Maschine3", "Maschine4", "Maschine5"};

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(50, 30, 50, 300); // Ось Y (машины)
        gc.strokeLine(50, 300, 50 + totalHours * 50, 300); // Ось X (часы)

        // Метки на оси X (часы)
        for (int i = 0; i < totalHours; i++) {
            int hourLabel = (i % 8) + 1;
            gc.strokeText(String.valueOf(hourLabel), 50 + i * 50, 320);
        }

        // Метки на оси Y (машины)
        for (int i = 0; i < machines.length; i++) {
            String name = "M" + (i + 1);
            gc.strokeText(name, 10, machineY[i] + 10);

            String[] hours = maschinen.getOrDefault(machines[i], new String[totalHours]);
            for (int j = 0; j < hours.length; j++) {
                String product = hours[j];
                if (product != null) {
                    gc.setFill(productColors.getOrDefault(product, Color.GRAY)); // Устанавливаем цвет продукта
                    gc.fillRect(50 + j * 50, machineY[i], 50, 20); // Рисуем прямоугольник для продукта
                }
            }
        }
    }

    private VBox createLegend() {
        VBox legend = new VBox();
        legend.setSpacing(10);

        for (Map.Entry<String, Color> entry : productColors.entrySet()) {
            HBox legendItem = new HBox();
            legendItem.setSpacing(10);


            Rectangle colorBox = new Rectangle(20, 20, entry.getValue());
            Label productLabel = new Label(entry.getKey());

            legendItem.getChildren().addAll(colorBox, productLabel);
            legend.getChildren().add(legendItem);
        }

        return legend;
    }






    //textField Build
}
