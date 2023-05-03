package com.danielhh.proyectoDAM.controlador;

import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface ITareaListasControlador {
    @Operation(summary = "Lista todas las tareas en la Base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La tarea se ha eliminado correctamente")
    })
    List<TareaRespuesta> listarTarea();
    @Operation(summary = "Lista todas las tareas de un usuario en concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las tareas se han listado correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son incorrectos")
    })
    List<TareaRespuesta> listarTareaUsuario(String nombre, String apellido);
    @Operation(summary = "Lista una tarea concreta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La tarea se ha listado correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son incorrectos")
    })
    TareaRespuesta obtenerTarea(long id);
    @Operation(summary = "Lista todas las tareas de un usuario en concreto segun su estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las tareas se han listado correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos son incorrectos")
    })
    List<TareaRespuesta> listarTareaUsuarioConEstado(String nombre, String apellido, TareaEstado estado);
}
