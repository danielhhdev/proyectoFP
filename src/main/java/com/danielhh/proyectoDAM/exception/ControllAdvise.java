package com.danielhh.proyectoDAM.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Controll advise.
 */
@ControllerAdvice
public class ControllAdvise extends ResponseEntityExceptionHandler {


    /**
     * No se encontro en base datos response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(NoSeEncontroEnBaseDatos.class)
    protected ResponseEntity<Object> noSeEncontroEnBaseDatos(NoSeEncontroEnBaseDatos ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Tarea no guardada response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(TareaNoGuardada.class)
    protected ResponseEntity<Object> tareaNoGuardada(TareaNoGuardada ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
