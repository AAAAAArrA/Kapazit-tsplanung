package com.example.kapazitatsplanung;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProdukteController {

    Grafik grafik;

    @FXML
    private TableView<Produkte> produkte_view;

    @FXML
    private TableColumn<Produkte, String> produkte_name;

    @FXML
    private TextField pr1_maschine;
    @FXML
    private TextField pr2_maschine;
    @FXML
    private TextField pr3_maschine;

    @FXML
    private TextField pr1_stunde;
    @FXML
    private TextField pr2_stunde;
    @FXML
    private TextField pr3_stunde;

    @FXML
    private TextField pr_name;

    @FXML
    private Label nameLabel;

    @FXML
    private Button newButton;

    @FXML
    private Button saveButton;

    private HelloApplication main;

    public void setMainApp(HelloApplication main){
        this.main = main;

        produkte_view.setItems(this.main.getProdukteData());

    }


    @FXML
    private void initialize(){
        grafik = new Grafik();
        produkte_name.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

        produkte_view.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));


        pr_name.setVisible(false);
        nameLabel.setVisible(false);
        saveButton.setVisible(false);
    }

    private void showDetails(Produkte produkte){
        int count = 1;
        LinkedHashMap<String, Integer> map = produkte.getMap();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(count == 1){
                pr1_maschine.setText(entry.getKey());
                pr1_stunde.setText(entry.getValue().toString());
                count++;
            } else if (count == 2) {
                pr2_maschine.setText(entry.getKey());
                pr2_stunde.setText(entry.getValue().toString());
                count++;
            } else if (count == 3) {
                pr3_maschine.setText(entry.getKey());
                pr3_stunde.setText(entry.getValue().toString());
                count++;
            }
        }
    }

    private void clearFields(){
        pr1_maschine.setText(null);
        pr1_stunde.setText(null);
        pr2_maschine.setText(null);
        pr2_stunde.setText(null);
        pr3_maschine.setText(null);
        pr3_stunde.setText(null);
    }

    @FXML
    public void clickNew(){
        clearFields();
        pr_name.setVisible(true);
        nameLabel.setVisible(true);
        saveButton.setVisible(true);
    }

    @FXML
    public void addNewProdukte(){
        String name = pr_name.getText();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        if(pr1_maschine.getText() != null && pr1_stunde.getText() != null){
            map.put(pr1_maschine.getText(), Integer.parseInt(pr1_stunde.getText()));
        }
        if(pr2_maschine.getText() != null && pr2_stunde.getText() != null){
            map.put(pr2_maschine.getText(), Integer.parseInt(pr2_stunde.getText()));
        }
        if(pr3_maschine.getText() != null && pr2_stunde.getText() != null){
            map.put(pr3_maschine.getText(), Integer.parseInt(pr3_stunde.getText()));
        }
        Produkte produkte = new Produkte(name);
        produkte.setMap(map);
        this.main.getProdukteData().add(produkte);
        grafik.addToProduktList(produkte);


    }
}
