package es.guillearana.ejerciciod.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import es.guillearana.ejerciciod.model.Persona;

/**
 * Controlador para gestionar la interfaz de usuario del ejercicio C, que permite añadir, modificar y eliminar personas de una tabla.
 * Se encarga de manejar los eventos de los botones y gestionar la tabla con la información de personas.
 */
public class Ejercicio_D_Controlador {

    /** Botón para agregar una persona a la tabla. */
    @FXML
    private Button btnAgregar;

    /** Botón para eliminar una persona de la tabla. */
    @FXML
    private Button btnEliminar;

    /** Botón para modificar una persona existente en la tabla. */
    @FXML
    private Button btnModificar;

    /** Columna de la tabla que muestra los apellidos de las personas. */
    @FXML
    private TableColumn<Persona, String> colApellidos;

    /** Lista observable que contiene las personas agregadas. */
    private ObservableList<Persona> personas;

    /** Columna de la tabla que muestra la edad de las personas. */
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    /** Columna de la tabla que muestra el nombre de las personas. */
    @FXML
    private TableColumn<Persona, String> colNombre;

    /** Tabla que contiene la lista de personas. */
    @FXML
    private TableView<Persona> tableInfo;

    /** Campo de texto donde se ingresan los apellidos. */
    @FXML
    private TextField tfApellidos;

    /** Campo de texto donde se ingresa la edad. */
    @FXML
    private TextField tfEdad;

    /** Campo de texto donde se ingresa el nombre. */
    @FXML
    private TextField tfNombre;

    /**
     * Método de inicialización que se ejecuta al cargar la interfaz.
     * Configura las columnas de la tabla para que muestren los datos de las personas y establece un formato personalizado para la columna de edad.
     */
    @FXML
    void initialize() {
        this.personas = FXCollections.observableArrayList();  // Inicializa la lista de personas como observable.

        // Configura la columna 'Nombre' para que muestre la propiedad 'nombre' de la clase Persona.
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        // Configura la columna 'Apellidos' para que muestre la propiedad 'apellidos' de la clase Persona.
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        // Configura la columna 'Edad' para que muestre la propiedad 'edad' de la clase Persona.
        this.colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        // Personaliza las celdas de la columna 'Edad' para que los números aparezcan alineados a la derecha.
        this.colEdad.setCellFactory((col) -> {
            TableCell<Persona, Integer> cell = new TableCell<Persona, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);  // Llama al método de la superclase.
                    this.setText(null);  // Limpia el texto anterior.
                    this.setGraphic(null);  // Limpia el contenido gráfico anterior.
                    if (!empty) {  // Si la celda no está vacía, muestra el valor de 'item'.
                        this.setText(Integer.toString(item));  // Convierte el valor de edad en texto.
                    }
                }
            };
            cell.setAlignment(Pos.CENTER_RIGHT);  // Alinea el contenido de la celda a la derecha.
            return cell;  // Devuelve la celda personalizada.
        });
    }

    /**
     * Método que se ejecuta cuando el usuario hace clic en el botón "Agregar".
     * Este método valida los campos de entrada y agrega una nueva persona a la tabla si los datos son válidos.
     * En caso de errores, se muestran alertas con los mensajes correspondientes.
     *
     * @param event El evento generado por el clic del botón.
     */
    @FXML
    void accionAceptar(ActionEvent event) {
        // Crea un StringBuilder para acumular los errores.
        StringBuilder errores = new StringBuilder();

        // Obtiene el texto del campo 'Nombre'.
        String nombre = this.tfNombre.getText();
        if (nombre.isEmpty()) {  // Verifica si el campo 'Nombre' está vacío.
            errores.append("El campo Nombre es obligatorio\n");  // Agrega un mensaje de error si está vacío.
        }

        // Obtiene el texto del campo 'Apellidos'.
        String apellidos = this.tfApellidos.getText();
        if (apellidos.isEmpty()) {  // Verifica si el campo 'Apellidos' está vacío.
            errores.append("El campo Apellidos es obligatorio\n");  // Agrega un mensaje de error si está vacío.
        }

        // Inicializa la variable edad.
        int edad = 0;
        if (tfEdad.getText().isEmpty()) {  // Verifica si el campo 'Edad' está vacío.
            errores.append("El campo Edad es obligatorio\n");  // Agrega un mensaje de error si está vacío.
        } else {
            try {
                // Intenta convertir el texto en un número.
                edad = Integer.parseInt(this.tfEdad.getText());
            } catch (NumberFormatException e) {  // Captura el error si la conversión falla.
                errores.append("La edad tiene que ser numérica\n");  // Agrega un mensaje de error si no es un número.
            }
        }

        // Si no hay errores, se procede a agregar la persona.
        if (errores.isEmpty()) {
            Persona p = new Persona(nombre, apellidos, edad);  // Crea un nuevo objeto Persona con los datos ingresados.
            Alert ventanaEmergente;  // Declara la alerta que se mostrará al usuario.
            Button ocultarBtn;  // Botón para ocultar la alerta.

            // Si la persona no está ya en la lista, se añade.
            if (!this.personas.contains(p)) {
                this.personas.add(p);  // Agrega la nueva persona a la lista observable.

                // Crea una alerta de información para indicar que la persona se agregó correctamente.
                ventanaEmergente = new Alert(AlertType.INFORMATION);
                ventanaEmergente.setTitle("Información");
                ventanaEmergente.setContentText("Persona añadida correctamente");
                ventanaEmergente.setHeaderText(null);  // Sin encabezado.

                // Crea el botón para cerrar la alerta.
                ocultarBtn = new Button("Aceptar");
                ocultarBtn.setOnAction((e) -> ventanaEmergente.hide());  // Oculta la alerta cuando se hace clic.

                ventanaEmergente.show();  // Muestra la alerta al usuario.

                // Actualiza la tabla con la nueva lista de personas.
                this.tableInfo.setItems(this.personas);
                this.tableInfo.refresh();  // Refresca la tabla para que muestre los datos actualizados.
            } else {
                // Si la persona ya está en la lista, muestra un mensaje de error.
                ventanaEmergente = new Alert(AlertType.ERROR);
                ventanaEmergente.setTitle("ERROR");
                ventanaEmergente.setContentText("Esa persona ya existe");
                ventanaEmergente.setHeaderText(null);  // Sin encabezado.

                ocultarBtn = new Button("Aceptar");  // Botón para cerrar la alerta.
                ocultarBtn.setOnAction((e) -> ventanaEmergente.hide());  // Oculta la alerta al hacer clic.

                ventanaEmergente.show();  // Muestra la alerta de error.
            }
        } else {
            // Si hay errores, muestra todos los mensajes de error acumulados.
            Alert ventanaEmergente = new Alert(AlertType.ERROR);
            ventanaEmergente.setTitle("ERROR");
            ventanaEmergente.setContentText(String.valueOf(errores));  // Muestra los errores acumulados.
            ventanaEmergente.setHeaderText(null);  // Sin encabezado.

            Button ocultarBtn = new Button("Aceptar");  // Botón para cerrar la alerta.
            ocultarBtn.setOnAction((e) -> ventanaEmergente.hide());  // Oculta la alerta al hacer clic.

            ventanaEmergente.show();  // Muestra la alerta de error.
        }
    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Eliminar".
     * Elimina la persona seleccionada en la tabla.
     *
     * @param event El evento generado por el clic del botón.
     */
    @FXML
    private void accionEliminar(ActionEvent event) {
        // Obtiene la persona seleccionada de la tabla.
        Persona p = (Persona) this.tableInfo.getSelectionModel().getSelectedItem();
        this.borrarPersonaLista(p);  // Llama al método que elimina a la persona.
        this.alertaInformacion("Se ha eliminado a la persona seleccionada");  // Muestra un mensaje de confirmación.
    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Modificar".
     * Permite modificar los datos de la persona seleccionada.
     *
     * @param event El evento generado por el clic del botón.
     */
    @FXML
    private void accionModificar(ActionEvent event) {
        Persona p = (Persona) this.tableInfo.getSelectionModel().getSelectedItem();  // Obtiene la persona seleccionada.
        this.modificarPersona(p);  // Llama al método que modifica a la persona.
    }

    // Métodos privados adicionales para manejar la eliminación y modificación de personas.
    private void borrarPersonaLista(Persona p) {
        this.personas.remove(p);  // Elimina la persona de la lista observable.
        this.tableInfo.refresh();  // Refresca la tabla.
    }

    private void modificarPersona(Persona p) {
        // Actualiza los campos de texto con la información de la persona seleccionada.
        this.tfNombre.setText(p.getNombre());
        this.tfApellidos.setText(p.getApellidos());
        this.tfEdad.setText(String.valueOf(p.getEdad()));
        this.borrarPersonaLista(p);  // Elimina la persona antigua de la lista.
    }

    // Método para mostrar alertas informativas.
    private void alertaInformacion(String mensaje) {
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);  // Crea una alerta de tipo informativo.
        ventanaEmergente.setTitle("Información");
        ventanaEmergente.setContentText(mensaje);  // Establece el mensaje de la alerta.
        ventanaEmergente.setHeaderText(null);  // Sin encabezado.
        ventanaEmergente.show();  // Muestra la alerta.
    }
}
