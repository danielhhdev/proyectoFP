package com.danielhh.proyectoDAM.respuesta;

import com.danielhh.proyectoDAM.entidad.Categoria;
import com.danielhh.proyectoDAM.entidad.TareaEstado;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The type Tarea respuesta.
 */
public class TareaRespuesta {

    /**
     * The Id tarea.
     */
    private Long idTarea;

    /**
     * The Titulo.
     */
    private String titulo;

    /**
     * The Descripcion.
     */
    private String descripcion;

    /**
     * The Categoria.
     */
    private Categoria categoria;

    /**
     * The Estado.
     */
    private TareaEstado estado;

    /**
     * The Fecha creacion.
     */
    private LocalDate fechaCreacion;

    /**
     * The Usuarios.
     */
    private List<UsuarioRespuesta> usuarios;

    /**
     * Instantiates a new Tarea respuesta.
     */
    public TareaRespuesta() {
    }

    /**
     * Instantiates a new Tarea respuesta.
     *
     * @param idTarea       the id tarea
     * @param titulo        the titulo
     * @param descripcion   the descripcion
     * @param categoria     the categoria
     * @param estado        the estado
     * @param fechaCreacion the fecha creacion
     * @param usuarios      the usuarios
     */
    public TareaRespuesta(Long idTarea, String titulo, String descripcion, Categoria categoria, TareaEstado estado, LocalDate fechaCreacion, List<UsuarioRespuesta> usuarios) {
        this.idTarea = idTarea;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.usuarios = usuarios;
    }

    /**
     * Gets id tarea.
     *
     * @return the id tarea
     */
    public Long getIdTarea() {
        return idTarea;
    }

    /**
     * Sets id tarea.
     *
     * @param idTarea the id tarea
     */
    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
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
     * Gets estado.
     *
     * @return the estado
     */
    public TareaEstado getEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(TareaEstado estado) {
        this.estado = estado;
    }

    /**
     * Gets fecha creacion.
     *
     * @return the fecha creacion
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Sets fecha creacion.
     *
     * @param fechaCreacion the fecha creacion
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets usuarios.
     *
     * @return the usuarios
     */
    public List<UsuarioRespuesta> getUsuarios() {
        return usuarios;
    }

    /**
     * Sets usuarios.
     *
     * @param usuarios the usuarios
     */
    public void setUsuarios(List<UsuarioRespuesta> usuarios) {
        this.usuarios = usuarios;
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
        TareaRespuesta that = (TareaRespuesta) o;
        return Objects.equals(idTarea, that.idTarea) && Objects.equals(titulo, that.titulo) && Objects.equals(descripcion, that.descripcion) && categoria == that.categoria && estado == that.estado && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(usuarios, that.usuarios);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(idTarea, titulo, descripcion, categoria, estado, fechaCreacion, usuarios);
    }
}


