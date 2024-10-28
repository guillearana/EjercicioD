package es.guillearana.ejerciciod.controlador;

import es.guillearana.ejerciciod.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador para la gestión de personas.
 * Este controlador se encarga de la lógica de negocio para la interfaz de usuario
 * relacionada con la creación y gestión de objetos {@link Persona}.
 */
public class EjercicioDcontroller {

    /** Botón para agregar una nueva persona. */
    @FXML
    private Button btnAgregar;

    /** Columna de apellidos en la tabla. */
    @FXML
    private TableColumn<Persona, String> colApellidos;

    /** Lista observable de personas. */
    private ObservableList<Persona> personas;

    /** Columna de edad en la tabla. */
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    /** Columna de nombre en la tabla. */
    @FXML
    private TableColumn<Persona, String> colNombre;

    /** Tabla que muestra la información de las personas. */
    @FXML
    private TableView<Persona> tableInfo;

    /** Campo de texto para ingresar apellidos. */
    @FXML
    private TextField tfApellidos;

    /** Campo de texto para ingresar edad. */
    @FXML
    private TextField tfEdad;

    /** Campo de texto para ingresar nombre. */
    @FXML
    private TextField tfNombre;

    /**
     * Inicializa el controlador. Este método se llama después de que se haya
     * cargado el archivo FXML.
     * Se inicializa la lista de personas y se configuran las columnas de la tabla.
     */
    @FXML
    void initialize() {
        personas = FXCollections.observableArrayList();
        // Asigna las propiedades de las columnas a los atributos de la clase Persona
        colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    }

    /**
     * Maneja la acción de agregar una nueva persona.
     * Abre una ventana emergente donde se pueden ingresar los detalles de la persona.
     *
     * @param event el evento de acción que provoca la apertura de la ventana emergente
     */
    @FXML
    void accionAgregar(ActionEvent event) {
        // Crea la ventana emergente y el contenedor
        Stage ventanaEmergente = new Stage();
        VBox contenedorRaiz = new VBox();

        // Contenedor para el Nombre
        HBox contenedorNombre = new HBox();
        contenedorNombre.setSpacing(10);
        tfNombre = new TextField();
        contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);

        // Contenedor para los Apellidos
        HBox contenedorApellidos = new HBox();
        contenedorApellidos.setSpacing(10);
        tfApellidos = new TextField();
        contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);

        // Contenedor para la Edad
        HBox contenedorEdad = new HBox();
        contenedorEdad.setSpacing(10);
        tfEdad = new TextField();
        contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);

        // Contenedor para los botones
        HBox contenedorBotones = new HBox();
        contenedorBotones.setSpacing(10);
        Button guardarBtn = new Button("Guardar");
        guardarBtn.setOnAction(e -> guardar(e));
        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> ventanaEmergente.close());

        contenedorBotones.getChildren().addAll(guardarBtn, cerrarBtn);

        // Añade los contenedores al contenedor raíz
        contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorApellidos, contenedorEdad, contenedorBotones);
        // Establece propiedades para el contenedor raíz
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        // Crea la escena y establece la ventana emergente
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);
        ventanaEmergente.setTitle("Nueva Persona");
        ventanaEmergente.setResizable(false);
        ventanaEmergente.show();
    }

    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
            METODOS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    /**
     * Guarda la nueva persona en la lista y actualiza la tabla.
     * Valida los campos antes de crear la persona y muestra alertas según sea necesario.
     *
     * @param event el evento de acción del botón "Guardar"
     */
    void guardar(ActionEvent event) {
        // Valida que los campos sean correctos
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Crea la persona
            Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
            // La añade a la tabla
            aniadirPersona(p);
            alertaInformacion("Se ha añadido a la persona correctamente");
        } else {
            alertaError(errores);
        }
    }

    /**
     * Valida los campos de entrada para asegurarse de que no están vacíos y que la edad es numérica.
     *
     * @return un String con los mensajes de error encontrados; vacío si no hay errores
     */
    private String validarCampos() {
        String errores = "";

        // Valida el campo Nombre
        String nombre = tfNombre.getText();
        if (nombre.isEmpty()) {
            errores += "Tienes que rellenar el campo Nombre\n";
        }
        // Valida el campo Apellidos
        String apellidos = tfApellidos.getText();
        if (apellidos.isEmpty()) {
            errores += "Tienes que rellenar el campo Apellidos\n";
        }
        // Valida el campo Edad
        int edad = 0;
        try {
            edad = Integer.parseInt(tfEdad.getText());
        } catch (NumberFormatException e) {
            errores += "La edad tiene que ser numérica\n";
        }

        return errores;
    }

    /**
     * Muestra una alerta de error con el mensaje proporcionado.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void alertaError(String mensaje) {
        Alert ventanaEmergente = new Alert(AlertType.ERROR);
        ventanaEmergente.setTitle("Error");
        ventanaEmergente.setHeaderText(null);  // Sin encabezado.
        ventanaEmergente.setContentText(mensaje);
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }

    /**
     * Muestra una alerta de información con el mensaje proporcionado.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void alertaInformacion(String mensaje) {
        Alert ventanaEmergente = new Alert(AlertType.INFORMATION);
        ventanaEmergente.setTitle("Información");
        ventanaEmergente.setHeaderText(null);  // Sin encabezado.
        ventanaEmergente.setContentText(mensaje);
        Button ocultarBtn = new Button("Aceptar");
        ocultarBtn.setOnAction(e -> {
            ventanaEmergente.hide();
        });
        ventanaEmergente.show();
    }

    /**
     * Añade una nueva persona a la lista y actualiza la tabla si la persona no existe ya.
     *
     * @param p la persona a añadir
     */
    private void aniadirPersona(Persona p) {
        // Comprueba si la persona ya existe en la lista
        if (!personas.contains(p)) {
            personas.add(p);
            tableInfo.setItems(personas);
            tableInfo.refresh();
            vaciarCampos();  // Vacía los campos después de añadir
        } else {
            alertaError("Esa persona ya existe");
        }
    }

    /**
     * Vacía los campos de entrada.
     */
    private void vaciarCampos() {
        tfNombre.setText("");
        tfApellidos.setText("");
        tfEdad.setText("");
    }
}
