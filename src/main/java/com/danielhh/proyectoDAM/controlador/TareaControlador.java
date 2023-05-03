package com.danielhh.proyectoDAM.controlador;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import com.danielhh.proyectoDAM.servicio.ITareaServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The type Tarea controlador.
 */
@RestController
@RequestMapping("/tarea")
@Tag(name = "CUD Tareas")
public class TareaControlador implements ITareaControlador {

    /**
     * The Tarea servicio.
     */
    private final ITareaServicio tareaServicio;

    /**
     * Instantiates a new Tarea controlador.
     *
     * @param tareaServicio the tarea servicio
     */
    public TareaControlador(ITareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    /**
     * Guardar tarea.
     *
     * @param tareaDto the tarea dto
     */
    @Override
    @PostMapping
    public void guardarTarea(@Valid @RequestBody TareaDto tareaDto) {
        tareaServicio.crearTarea(tareaDto);
    }

    /**
     * Eliminar tarea id.
     *
     * @param id the id
     */
    @Override
    @DeleteMapping("/{id}")
    public void eliminarTareaId(@NotNull @PathVariable("id") long id) {
        tareaServicio.eliminarTareaId(id);
    }

    /**
     * Modificar tarea.
     *
     * @param id       the id
     * @param tareaDto the tarea dto
     */
    @Override
    @PutMapping("/{id}")
    public void modificarTarea(@NotNull @PathVariable("id") long id,
                               @Valid @RequestBody TareaDto tareaDto) {
        tareaServicio.modificarTarea(id,tareaDto);
    }

    /**
     * Modificar estado tarea respuesta.
     *
     * @param id the id
     * @return the tarea respuesta
     */
    @Override
    @PatchMapping("/finalizada/{id}")
    public TareaRespuesta modificarEstado(@NotNull @PathVariable ("id") Long id) {
        return tareaServicio.modificarEstado(id);
    }

}
