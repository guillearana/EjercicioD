package es.guillearana.ejerciciod;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

/**
 * Clase principal de la aplicación de gestión de personas.
 * Extiende de {@link Application} para usar el marco JavaFX.
 */
public class GestionPersonas extends Application {

    /**
     * Método principal de inicio de la aplicación JavaFX.
     * Carga la interfaz desde un archivo FXML y la asigna a la escena principal.
     *
     * @param primaryStage el escenario principal de la aplicación donde se muestra la escena
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga el archivo FXML y lo establece en el panel raíz
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/es/guillearana/ejerciciod/ejercicioD.fxml"));
            // Crea una escena con el panel raíz
            Scene scene = new Scene(root);
            // Asigna la escena al escenario principal
            primaryStage.setScene(scene);
            // Muestra el escenario
            primaryStage.show();
        } catch (Exception e) {
            // Imprime la traza del error en caso de fallo
            e.printStackTrace();
        }
    }

    /**
     * Método principal de la aplicación.
     * Llama al método {@link Application#launch(String...)} para iniciar la aplicación JavaFX.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}
