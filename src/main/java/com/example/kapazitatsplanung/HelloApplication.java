package com.example.kapazitatsplanung;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedHashMap;

public class HelloApplication extends Application {

    private ObservableList<Produkte> produkteData = FXCollections.observableArrayList();


    private Grafik grafik = new Grafik();

    public HelloApplication() {
        createData();
    }

    public ObservableList<Produkte> getProdukteData() {
        return this.produkteData;
    }

    @Override
    public void start(Stage stage) {
//        primaryStage.setTitle("Kapazitätsplanung");
//
//        Pane root = new Pane();
//        Canvas canvas = new Canvas(800, 400);  // Увеличиваем размер холста
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        grafik.drawChart(gc);
//
//
//        root.getChildren().add(canvas);
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Grafik.fxml"));
            Parent root = loader.load();
            GrafikController controller = loader.getController();
            controller.setMainApp(this);


            Scene scene = new Scene(root);


            stage.setTitle("База данных 'Unwetter'");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createData(){

        Produkte produkte1 = new Produkte("Produkte1");
        LinkedHashMap<String, Integer> process1 = new LinkedHashMap<>();
        process1.put("Maschine1", 1);
        process1.put("Maschine2", 3);
        produkte1.setMap(process1);
        grafik.addToProduktList(produkte1);
        produkteData.add(produkte1);
//        grafik.addProduktToMap(produkte1);

        Produkte produkte2 = new Produkte("Produkte2");
        LinkedHashMap<String, Integer> process2 = new LinkedHashMap<>();
        process2.put("Maschine4", 2);
        process2.put("Maschine5", 1);
        produkte2.setMap(process2);
        grafik.addToProduktList(produkte2);
        produkteData.add(produkte2);
//        grafik.addProduktToMap(produkte2);



        Produkte produkte3 = new Produkte("Produkte3");
        LinkedHashMap<String, Integer> process3 = new LinkedHashMap<>();
        process3.put("Maschine3", 2);
        produkte3.setMap(process3);
        grafik.addToProduktList(produkte3);
        produkteData.add(produkte3);
//        grafik.addProduktToMap(produkte3);



        Produkte produkte4 = new Produkte("Produkte4");
        LinkedHashMap<String, Integer> process4 = new LinkedHashMap<>();
        process4.put("Maschine1", 2);
        process4.put("Maschine3", 3);
        produkte4.setMap(process4);
        grafik.addToProduktList(produkte4);
        produkteData.add(produkte4);
//        grafik.addProduktToMap(produkte4);



        Produkte produkte5 = new Produkte("Produkte5");
        LinkedHashMap<String, Integer> process5 = new LinkedHashMap<>();
        process5.put("Maschine2", 1);
        process5.put("Maschine5", 3);
        produkte5.setMap(process5);
        grafik.addToProduktList(produkte5);
        produkteData.add(produkte5);
//        grafik.addProduktToMap(produkte5);


        Produkte produkte6 = new Produkte("Produkte6");
        LinkedHashMap<String, Integer> process6 = new LinkedHashMap<>();
        process6.put("Maschine3", 1);
        process6.put("Maschine4", 3);
        produkte6.setMap(process6);
        grafik.addToProduktList(produkte6);
        produkteData.add(produkte6);
//        grafik.addProduktToMap(produkte6);
    }













//    private void drawChart(GraphicsContext gc) {
//        int[] machineY = {250, 200, 150, 100, 50}; // Y позиции для машин в обратном порядке
//
//        // Продукция 1
//        gc.setFill(Color.RED);
//        gc.fillRect(1 * 50, machineY[4], 2 * 50, 20); // Машина 1 - 2 часа
//        gc.fillRect(3 * 50, machineY[3], 3 * 50, 20); // Машина 2 - 3 часа
//
//        // Продукция 2
//        gc.setFill(Color.BLUE);
//        gc.fillRect(1 * 50, machineY[1], 2 * 50, 20); // Машина 4 - 2 часа
//        gc.fillRect(3 * 50, machineY[0], 1 * 50, 20); // Машина 5 - 1 час
//
//        // Продукция 3
//        gc.setFill(Color.GREEN);
//        gc.fillRect(1 * 50, machineY[2], 2 * 50, 20); // Машина 3 - 2 часа
//
//        // Продукция 4
//        gc.setFill(Color.ORANGE);
//        gc.fillRect(1 * 50, machineY[4], 1 * 50, 20); // Машина 1 - 1 час
//        gc.fillRect(2 * 50, machineY[2], 3 * 50, 20); // Машина 3 - 3 часа
//
//        // Продукция 5
//        gc.setFill(Color.PURPLE);
//        gc.fillRect(1 * 50, machineY[3], 1 * 50, 20); // Машина 2 - 1 час
//        gc.fillRect(2 * 50, machineY[0], 3 * 50, 20); // Машина 5 - 3 часа
//
//        // Рисование осей и меток
//        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(2);
//        gc.strokeLine(50, 30, 50, 300); // Ось Y
//        gc.strokeLine(50, 300, 550, 300); // Ось X
//
//        for (int i = 1; i <= 8; i++) {
//            gc.strokeText(String.valueOf(i), i * 50, 320); // Метки на оси X (часы)
//        }
//
//        String[] machines = {"M5", "M4", "M3", "M2", "M1"};
//        for (int i = 0; i < machines.length; i++) {
//            gc.strokeText(machines[i], 10, machineY[i] + 10); // Метки на оси Y (машины)
//        }
//    }
}
