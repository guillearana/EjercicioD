package es.guillearana.ejerciciod.model;

import java.util.Objects;

/**
 * La clase {@code Persona} representa a una persona con atributos como nombre, apellidos, edad e idPersona.
 * Esta clase proporciona métodos para acceder y modificar estos atributos, así como para comparar objetos
 * de tipo {@code Persona}.
 *
 * <p>
 * Los objetos de la clase {@code Persona} se consideran iguales si tienen el mismo nombre, apellidos y edad,
 * aunque su {@code idPersona} sea diferente.
 * </p>
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;
    private int idPersona;

    /**
     * Retorna una representación en forma de cadena de la persona.
     *
     * <p>
     * El formato de la cadena es el siguiente:
     * </p>
     *
     * <pre>
     * Persona [nombre=Nombre, apellidos=Apellidos, edad=Edad, idPersona=ID]
     * </pre>
     *
     * @return una cadena con los valores de nombre, apellidos, edad e idPersona.
     */
    @Override
    public String toString() {
        return "Persona [nombre=" + this.nombre + ", apellidos=" + this.apellidos + ", edad=" + this.edad + ", idPersona=" + this.idPersona + "]";
    }

    /**
     * Genera un código hash basado en los atributos {@code nombre}, {@code apellidos} y {@code edad}.
     *
     * <p>
     * Este método es útil para almacenar objetos de tipo {@code Persona} en colecciones que usan tablas hash,
     * como {@code HashSet} o {@code HashMap}.
     * </p>
     *
     * @return el código hash generado a partir de los atributos de la persona.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.apellidos, this.edad, this.nombre);
    }

    /**
     * Compara si dos objetos son iguales.
     *
     * <p>
     * Dos objetos de tipo {@code Persona} se consideran iguales si tienen el mismo
     * {@code nombre}, {@code apellidos} y {@code edad}, independientemente de su {@code idPersona}.
     * </p>
     *
     * @param obj el objeto a comparar con esta persona.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Persona other = (Persona) obj;
        return Objects.equals(this.apellidos, other.apellidos) && this.edad == other.edad && Objects.equals(this.nombre, other.nombre);
    }

    /**
     * Constructor que inicializa los atributos {@code nombre}, {@code apellidos} y {@code edad} de la persona.
     *
     * <p>
     * Este constructor puede ser usado cuando no se requiere un {@code idPersona}, por ejemplo,
     * en casos donde la identificación única se genera posteriormente.
     * </p>
     *
     * @param nombre    El nombre de la persona.
     * @param apellidos Los apellidos de la persona.
     * @param edad      La edad de la persona.
     */
    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /**
     * Constructor que inicializa los atributos {@code idPersona}, {@code nombre}, {@code apellidos} y {@code edad} de la persona.
     *
     * <p>
     * Este constructor puede ser usado cuando ya se tiene un {@code idPersona} definido, por ejemplo, al recuperar
     * una persona de una base de datos.
     * </p>
     *
     * @param idPersona El identificador único de la persona.
     * @param nombre    El nombre de la persona.
     * @param apellidos Los apellidos de la persona.
     * @param edad      La edad de la persona.
     */
    public Persona(int idPersona, String nombre, String apellidos, int edad) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /**
     * Obtiene el identificador único de la persona.
     *
     * @return el {@code idPersona} de la persona.
     */
    public int getIdPersona() {
        return this.idPersona;
    }

    /**
     * Establece el identificador único de la persona.
     *
     * @param idPersona El nuevo {@code idPersona} de la persona.
     */
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos de la persona.
     *
     * @return los apellidos de la persona.
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Establece los apellidos de la persona.
     *
     * @param apellidos Los nuevos apellidos de la persona.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @return la edad de la persona.
     */
    public int getEdad() {
        return this.edad;
    }

    /**
     * Establece la edad de la persona.
     *
     * @param edad La nueva edad de la persona.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}

