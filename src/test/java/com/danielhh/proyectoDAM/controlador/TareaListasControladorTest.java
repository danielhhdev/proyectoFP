package com.danielhh.proyectoDAM.controlador;


import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.servicio.TareaListasServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Tarea listas controlador test.
 */
@WebMvcTest(TareaListasControlador.class)
public class TareaListasControladorTest {

    /**
     * The Tarea listas servicio.
     */
    @MockBean
    private TareaListasServicio tareaListasServicio;

    /**
     * The Mock mvc.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * The Object mapper.
     */
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Is ok cuando se listan todas las tareas.
     *
     * @throws Exception the exception
     */
//  -----------------------------------TEST LISTAR TODAS LAS TAREAS-----------------------------------
    @Test
    public void isOkCuandoSeListanTodasLasTareas() throws Exception {
        List < Tarea> listaTareas = new ArrayList<>();
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/listaTarea")
                .contentType(APPLICATION_JSON));
        verify(tareaListasServicio).listarTareas();
        perform.andExpect(status().isOk());
    }

    //  -----------------------------------TEST LISTAR TAREAS USUARIO CONCRETO-----------------------------------

    /**
     * Is ok cuando se lista tareas usuairo concreto.
     *
     * @throws Exception the exception
     */
    @Test
    public void isOkCuandoSeListaTareasUsuairoConcreto() throws Exception {
        String nombre = "daniel";
        String apellido = "hernandez";
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/listaTarea/{nombre}/{apellido}", nombre,apellido)
                .contentType(APPLICATION_JSON));
        verify(tareaListasServicio).listarTareasUsuario(anyString(),anyString());
        perform.andExpect(status().isOk());
    }

    /**
     * Is not found cuando nombre apellido son null.
     *
     * @throws Exception the exception
     */
    @Test
    public void IsNotFoundCuandoNombreApellidoSonNull() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/listarTarea/{nombre}/{apellido}", null,null));
        verify(tareaListasServicio, times(0)).listarTareasUsuario(anyString(),anyString());
        perform.andExpect(status().isNotFound());
    }

    /**
     * Is ok cuando se lista una tarea correcta.
     *
     * @throws Exception the exception
     */
//  -----------------------------------TEST LISTAR TAREAS CONCRETA-----------------------------------
    @Test
    public void isOkCuandoSeListaUnaTareaCorrecta() throws Exception {
        long id=1L;
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/listaTarea/{id}", id)
                .contentType(APPLICATION_JSON));
        verify(tareaListasServicio).obtenerTarea(anyLong());
        perform.andExpect(status().isOk());
    }

    //  -----------------------------------TEST LISTAR TAREAS USUARIO Y ESTADO-----------------------------------


    /**
     * Is ok cuando se lista tareas usuario y estado.
     *
     * @throws Exception the exception
     */
    @Test
    public void isOkCuandoSeListaTareasUsuarioYEstado() throws Exception {
        String nombre= "Daniel";
        String apellido="hernandez";
        TareaEstado estado = TareaEstado.EN_PROCESO;
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/listaTarea/{nombre}/{apellido}/{estado}", nombre, apellido, estado)
                .contentType(APPLICATION_JSON));
        verify(tareaListasServicio).listarTareasUsuarioConEstado(anyString(),anyString(),any(TareaEstado.class));
        perform.andExpect(status().isOk());
    }

}
