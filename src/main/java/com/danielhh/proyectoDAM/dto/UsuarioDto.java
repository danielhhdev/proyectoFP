package com.danielhh.proyectoDAM.dto;

import javax.validation.constraints.NotBlank;

/**
 * The type Usuario dto.
 */
public class UsuarioDto {

    /**
     * The Nombre.
     */
    @NotBlank
    private String nombre;

    /**
     * The Apellido.
     */
    @NotBlank
    private String apellido;

    /**
     * Instantiates a new Usuario dto.
     */
    public UsuarioDto() {
    }

    /**
     * Instantiates a new Usuario dto.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     */
    public UsuarioDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
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
     * Gets apellido.
     *
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets apellido.
     *
     * @param apellido the apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
