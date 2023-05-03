package com.danielhh.proyectoDAM.dto;

import com.danielhh.proyectoDAM.entidad.Categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The type Tarea dto.
 */
public class TareaDto {

    /**
     * The Titulo.
     */
    @NotBlank
    private String titulo;

    /**
     * The Descripcion.
     */
    private String descripcion;

    /**
     * The Categoria.
     */
    @NotNull
    private Categoria categoria;

    /**
     * The Usuarios.
     */
    @NotNull
    private List<UsuarioDto> usuarios;


    /**
     * Instantiates a new Tarea dto.
     */
    public TareaDto() {
    }

    /**
     * Instantiates a new Tarea dto.
     *
     * @param titulo      the titulo
     * @param descripcion the descripcion
     * @param categoria   the categoria
     * @param usuarios    the usuarios
     */
    public TareaDto(String titulo, String descripcion, Categoria categoria, List<UsuarioDto> usuarios) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.usuarios = usuarios;
    }

    /**
     * Gets titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets titulo.
     *
     * @param titulo the titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets categoria.
     *
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Sets categoria.
     *
     * @param categoria the categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets usuarios.
     *
     * @return the usuarios
     */
    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }

    /**
     * Sets usuarios.
     *
     * @param usuarios the usuarios
     */
    public void setUsuarios(List<UsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }
}
