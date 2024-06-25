package com.example.kapazitatsplanung;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedHashMap;

public class HelloApplication extends Application {

    private Grafik grafik = new Grafik();

    public HelloApplication() {

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kapazitätsplanung");

        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 400);  // Увеличиваем размер холста
        GraphicsContext gc = canvas.getGraphicsContext2D();

        grafik.drawChart(gc);


        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
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
