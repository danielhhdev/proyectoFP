package com.danielhh.proyectoDAM.entidad;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Tarea.
 */
@Entity
@Table(name = "Tareas")
public class Tarea {
    /**
     * The Id tarea.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTarea;
    /**
     * The Titulo.
     */
    @Column(name = "Titulo", nullable = false)
    private String titulo;
    /**
     * The Descripcion.
     */
    @Column(name = "Descripcion")
    private String descripcion;
    /**
     * The Categoria.
     */
    @Column(name = "Categoria")
    private Categoria categoria;
    /**
     * The Estado.
     */
    @Column(name = "Estado")
    private TareaEstado estado;
    /**
     * The Fecha creacion.
     */
    @Column(name = "FechaDeCreacion")
    private LocalDate fechaCreacion;
    /**
     * The Usuarios.
     */
    @Column(name = "Usuarios")
    @JoinTable(
            name = "TareasUsuarios",
            joinColumns = @JoinColumn(name = "idTarea", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idUsuario", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    /**
     * Instantiates a new Tarea.
     */
    public Tarea() {
    }

    /**
     * Instantiates a new Tarea.
     *
     * @param idTarea       the id tarea
     * @param titulo        the titulo
     * @param descripcion   the descripcion
     * @param categoria     the categoria
     * @param estado        the estado
     * @param fechaCreacion the fecha creacion
     * @param usuarios      the usuarios
     */
    public Tarea(long idTarea, String titulo, String descripcion, Categoria categoria, TareaEstado estado, LocalDate fechaCreacion, List<Usuario> usuarios) {
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
    public long getIdTarea() {
        return idTarea;
    }

    /**
     * Sets id tarea.
     *
     * @param idTarea the id tarea
     */
    public void setIdTarea(long idTarea) {
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
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Sets usuarios.
     *
     * @param usuarios the usuarios
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
