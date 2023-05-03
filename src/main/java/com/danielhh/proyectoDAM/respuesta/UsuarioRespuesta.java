package com.danielhh.proyectoDAM.respuesta;

import java.util.Objects;

/**
 * The type Usuario respuesta.
 */
public class UsuarioRespuesta {


    /**
     * The Nombre.
     */
    private String nombre;


    /**
     * The Apellidos.
     */
    private String apellidos;

    /**
     * Instantiates a new Usuario respuesta.
     */
    public UsuarioRespuesta() {
    }

    /**
     * Instantiates a new Usuario respuesta.
     *
     * @param nombre    the nombre
     * @param apellidos the apellidos
     */
    public UsuarioRespuesta(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets apellidos.
     *
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets apellidos.
     *
     * @param apellidos the apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRespuesta that = (UsuarioRespuesta) o;
        return nombre.equals(that.nombre) && apellidos.equals(that.apellidos);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos);
    }
}
