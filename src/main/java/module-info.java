module es.guillearana.ejerciciod {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.guillearana.ejerciciod to javafx.fxml;
    exports es.guillearana.ejerciciod;
    exports es.guillearana.ejerciciod.controlador;
    opens es.guillearana.ejerciciod.controlador to javafx.fxml;
}