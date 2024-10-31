package es.guillearana.ejerciciod.controlador;

import es.guillearana.ejerciciod.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la gestión de la ventana modal de entrada de datos de una persona.
 * Este controlador maneja la lógica de negocio y la interacción con la interfaz de usuario
 * para la creación de objetos {@link Persona}.
 */
public class ControllerModalEjerD {

    /** Campo de texto para el nombre de la persona. */
    @FXML
    private TextField txtNombre;

    /** Campo de texto para los apellidos de la persona. */
    @FXML
    private TextField txtApellidos;

    /** Campo de texto para la edad de la persona. */
    @FXML
    private TextField txtEdad;

    /** Botón para guardar la persona. */
    @FXML
    private Button btnGuardar;

    /** Botón para cancelar la operación. */
    @FXML
    private Button btnCancelar;

    /** Objeto persona que se está creando. */
    private Persona persona;

    /**
     * Maneja la acción de guardar una nueva persona.
     * Valida los campos de entrada y crea un objeto Persona si los campos son válidos.
     *
     * @param event el evento de acción del botón "Guardar"
     */
    @FXML
    void guardarPersona(ActionEvent event) {
        String errores = validarCampos();
        if (errores.isEmpty()) {
            persona = new Persona(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtEdad.getText()));
            cerrarVentana();
        } else {
            mostrarAlertaError(errores);
        }
    }

    /**
     * Maneja la acción de cancelar la operación.
     * Cierra la ventana modal sin realizar cambios.
     *
     * @param event el evento de acción del botón "Cancelar"
     */
    @FXML
    void cancelarPersona(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Devuelve la persona creada.
     *
     * @return el objeto Persona creado, o null si no se ha creado
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Cierra la ventana modal actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    /**
     * Valida los campos de entrada y devuelve un mensaje de error si hay campos inválidos.
     *
     * @return un mensaje de error si hay errores, de lo contrario, una cadena vacía
     */
    private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().isEmpty()) {
            errores.append("Debe ingresar un nombre.\n");
        }
        if (txtApellidos.getText().isEmpty()) {
            errores.append("Debe ingresar apellidos.\n");
        }
        if (txtEdad.getText().isEmpty()) {
            errores.append("Debe ingresar una edad.\n");
        } else {
            try {
                int edad = Integer.parseInt(txtEdad.getText());
                if (edad < 0 || edad > 120) {
                    errores.append("La edad debe estar entre 0 y 120 años.\n");
                }
            } catch (NumberFormatException e) {
                errores.append("La edad debe ser un número.\n");
            }
        }
        return errores.toString();
    }

    /**
     * Muestra un mensaje de alerta de error.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
