module com.example.kapazitatsplanung {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.kapazitatsplanung to javafx.fxml;
    exports com.example.kapazitatsplanung;
}