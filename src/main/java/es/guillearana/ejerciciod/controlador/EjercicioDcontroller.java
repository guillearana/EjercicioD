package es.guillearana.ejerciciod.controlador;

import es.guillearana.ejerciciod.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controlador para la gestión de la vista principal de la aplicación.
 * Este controlador maneja la lógica para agregar personas a la lista y
 * actualiza la vista de la tabla.
 */
public class EjercicioDcontroller {

    /** Botón para agregar una nueva persona. */
    @FXML
    private Button btnAgregar;

    /** Columna para mostrar los apellidos de las personas. */
    @FXML
    private TableColumn<Persona, String> colApellidos;

    /** Columna para mostrar la edad de las personas. */
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    /** Columna para mostrar el nombre de las personas. */
    @FXML
    private TableColumn<Persona, String> colNombre;

    /** Tabla para mostrar la información de las personas. */
    @FXML
    private TableView<Persona> tableInfo;

    /** Lista observable que contiene las personas. */
    private ObservableList<Persona> personas;

    /**
     * Método que se llama al inicializar el controlador.
     * Configura las columnas de la tabla y la lista de personas.
     */
    @FXML
    public void initialize() {
        personas = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    /**
     * Maneja la acción de agregar una nueva persona.
     * Abre una ventana modal para ingresar los datos de la nueva persona
     * y la agrega a la lista si es válida.
     *
     * @param event el evento de acción del botón "Agregar"
     */
    @FXML
    void accionAgregar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/guillearana/ejerciciod/ejerDmodal.fxml"));
            Parent root = loader.load();

            ControllerModalEjerD modalController = loader.getController();

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Agregar Persona");
            modalStage.setScene(new Scene(root));
            modalStage.setResizable(false);
            modalStage.showAndWait();

            Persona nuevaPersona = modalController.getPersona();
            if (nuevaPersona != null && !personas.contains(nuevaPersona)) {
                personas.add(nuevaPersona);
                tableInfo.setItems(personas);
            } else {
                mostrarAlerta("La persona ya existe en la lista");
            }

        } catch (IOException e) {
            mostrarAlerta("Error al abrir la ventana modal: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            mostrarAlerta("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Muestra un mensaje de alerta con la información proporcionada.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
