package com.danielhh.proyectoDAM.servicio;

import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;

import java.util.List;

/**
 * The interface Tarea listas servicio.
 */
public interface ITareaListasServicio {

    /**
     * Listar tareas list.
     *
     * @return the list
     */
    List<TareaRespuesta> listarTareas();

    /**
     * Listar tareas usuario list.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     * @return the list
     */
    List<TareaRespuesta> listarTareasUsuario(String nombre, String apellido);

    /**
     * Obtener tarea tarea respuesta.
     *
     * @param id the id
     * @return the tarea respuesta
     */
    TareaRespuesta obtenerTarea(Long id);

    /**
     * Listar tareas usuario con estado list.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     * @param estado   the estado
     * @return the list
     */
    List<TareaRespuesta> listarTareasUsuarioConEstado(String nombre, String apellido, TareaEstado estado);
}
