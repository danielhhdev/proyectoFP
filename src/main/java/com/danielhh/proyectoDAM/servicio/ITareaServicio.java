package com.danielhh.proyectoDAM.servicio;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;

/**
 * The interface Tarea servicio.
 */
public interface ITareaServicio {

     /**
      * Crear tarea.
      *
      * @param tareaDto the tarea dto
      */
     void crearTarea(TareaDto tareaDto);

     /**
      * Eliminar tarea id.
      *
      * @param id the id
      */
     void eliminarTareaId(Long id);

     /**
      * Modificar tarea.
      *
      * @param id       the id
      * @param tareaDto the tarea dto
      */
     void modificarTarea(Long id, TareaDto tareaDto);

     /**
      * Modificar estado tarea respuesta.
      *
      * @param id the id
      * @return the tarea respuesta
      */
     TareaRespuesta modificarEstado(Long id);
}
