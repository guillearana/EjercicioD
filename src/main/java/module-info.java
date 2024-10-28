module es.guillearana.ejerciciod {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens es.guillearana.ejerciciod to javafx.fxml;
    exports es.guillearana.ejerciciod;
    exports es.guillearana.ejerciciod.controlador;
    opens es.guillearana.ejerciciod.controlador to javafx.fxml;
    opens es.guillearana.ejerciciod.model to javafx.fxml, javafx.base; // Permite acceso a clases del paquete model desde javafx.fxml y javafx.base.

}