package com.danielhh.proyectoDAM.exception;

/**
 * The type Tarea no guardada.
 */
public class TareaNoGuardada extends RuntimeException {
    /**
     * Instantiates a new Tarea no guardada.
     */
    public TareaNoGuardada() {
        super("La tarea no se ha guardado en la base de datos");
    }
}