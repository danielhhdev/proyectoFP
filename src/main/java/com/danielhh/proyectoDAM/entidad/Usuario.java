package com.danielhh.proyectoDAM.entidad;

import javax.persistence.*;
import java.util.List;

/**
 * The type Usuario.
 */
@Entity
@Table(name = "Usuarios")

public class Usuario {
    /**
     * The Id usuario.
     */
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long idUsuario;

    /**
     * The Nombre.
     */
    @Column(name = "Nombre")
    private String nombre;

    /**
     * The Apellidos.
     */
    @Column(name = "Apellidos")
    private String apellidos;

    /**
     * The Tareas.
     */
    @ManyToMany(mappedBy = "usuarios")
    private List<Tarea> tareas;

    /**
     * Instantiates a new Usuario.
     */
    public Usuario() {
    }

    /**
     * Instantiates a new Usuario.
     *
     * @param idUsuario the id usuario
     * @param nombre    the nombre
     * @param apellidos the apellidos
     * @param tareas    the tareas
     */
    public Usuario(long idUsuario, String nombre, String apellidos, List<Tarea> tareas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tareas = tareas;
    }

    /**
     * Instantiates a new Usuario.
     *
     * @param nombre    the nombre
     * @param apellidos the apellidos
     */
    public Usuario(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**
     * Gets id usuario.
     *
     * @return the id usuario
     */
    public long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets id usuario.
     *
     * @param idUsuario the id usuario
     */
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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
     * Gets tareas.
     *
     * @return the tareas
     */
    public List<Tarea> getTareas() {
        return tareas;
    }

    /**
     * Sets tareas.
     *
     * @param tareas the tareas
     */
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
}
