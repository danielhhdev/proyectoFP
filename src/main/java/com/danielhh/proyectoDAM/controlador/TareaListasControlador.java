package com.danielhh.proyectoDAM.controlador;

import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import com.danielhh.proyectoDAM.servicio.ITareaListasServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The type Tarea listas controlador.
 */
@RestController
@RequestMapping("/listaTarea")
@Tag(name = "Listar Tareas")
public class TareaListasControlador implements ITareaListasControlador {
    /**
     * The Tarea listas servicio.
     */
    private final ITareaListasServicio tareaListasServicio;

    /**
     * Instantiates a new Tarea listas controlador.
     *
     * @param tareaListasServicio the tarea listas servicio
     */
    public TareaListasControlador(ITareaListasServicio tareaListasServicio) {
        this.tareaListasServicio = tareaListasServicio;
    }

    /**
     * Listar tarea list.
     *
     * @return the list
     */
    @Override
    @GetMapping
    public List<TareaRespuesta> listarTarea() {
        return tareaListasServicio.listarTareas();
    }

    /**
     * Listar tarea usuario list.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     * @return the list
     */
    @Override
    @GetMapping("/{nombre}/{apellido}")
    public List<TareaRespuesta> listarTareaUsuario(@PathVariable("nombre") @NotNull String nombre,
                                                   @PathVariable("apellido") @NotNull String apellido) {
        return tareaListasServicio.listarTareasUsuario(nombre, apellido);
    }

    /**
     * Obtener tarea tarea respuesta.
     *
     * @param id the id
     * @return the tarea respuesta
     */
    @Override
    @GetMapping("/{id}")
    public TareaRespuesta obtenerTarea(@PathVariable("id") @NotNull long id) {
        return tareaListasServicio.obtenerTarea(id);
    }

    /**
     * Listar tarea usuario con estado list.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     * @param estado   the estado
     * @return the list
     */
    @Override
    @GetMapping("/{nombre}/{apellido}/{estado}")
    public List<TareaRespuesta> listarTareaUsuarioConEstado(@PathVariable("nombre") @NotNull String nombre,
                                                            @PathVariable("apellido") @NotNull String apellido,
                                                            @PathVariable("estado") @NotNull TareaEstado estado) {
        return tareaListasServicio.listarTareasUsuarioConEstado(nombre, apellido, estado);
    }
}
