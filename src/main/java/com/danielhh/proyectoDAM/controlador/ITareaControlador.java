package com.danielhh.proyectoDAM.controlador;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ITareaControlador {
    @Operation(summary = "Guardar una tarea en la Base de datos", description = "El campo Categoria tiene que tener uno de estos campos: CASA, TRABAJO, OCIO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La tarea se ha guardado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al ingresar datos"),
            @ApiResponse(responseCode = "404", description = "Error al guardar la tarea")
    })
    void guardarTarea(TareaDto tareaDto);
    @Operation(summary = "Eliminar una tarea en la Base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La tarea se ha eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "La tarea no se encuentra en la base de datos"),
            @ApiResponse(responseCode = "405", description = "No se ha ingresado dato")
    })
    void eliminarTareaId(long id);
    @Operation(summary = "Modificar una tarea en la Base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La tarea se ha eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "La tarea no se encuentra en la base de datos"),
            @ApiResponse(responseCode = "405", description = "No se ha ingresado dato")
    })
    void modificarTarea(long id, TareaDto tareaDto);
    @Operation(summary = "Finalizar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La tarea se ha cambiado a FINALIZADO correctamente"),
            @ApiResponse(responseCode = "404", description = "La tarea no se encuentra en la base de datos"),
            @ApiResponse(responseCode = "405", description = "No se ha ingresado dato")
    })
    TareaRespuesta modificarEstado(Long id);
}
