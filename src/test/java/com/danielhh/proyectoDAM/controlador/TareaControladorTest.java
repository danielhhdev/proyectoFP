package com.danielhh.proyectoDAM.controlador;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.dto.UsuarioDto;
import com.danielhh.proyectoDAM.entidad.Categoria;
import com.danielhh.proyectoDAM.servicio.ITareaServicio;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Tarea controlador test.
 */
@WebMvcTest(TareaControlador.class)
public class TareaControladorTest {

    /**
     * The Tarea servicio.
     */
    @MockBean
    private ITareaServicio tareaServicio;

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
     * Is ok cuando se crea tarea.
     *
     * @throws Exception the exception
     */
//  -----------------------------------TEST CREAR TAREA-----------------------------------
    @Test
    public void isOkCuandoSeCreaTarea() throws Exception {
        TareaDto tareaDto = crearTareaDto();
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/tarea")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tareaDto)));
        verify(tareaServicio).crearTarea(any(TareaDto.class));
        perform.andExpect(status().isOk());
    }

    /**
     * Bad request cuando se crea tarea y la tarea no cumple requisitos.
     *
     * @throws Exception the exception
     */
    @Test
    public void badRequestCuandoSeCreaTareaYLaTareaNoCumpleRequisitos() throws Exception {
        TareaDto dtoIncorrect = crearTareaDto();
        dtoIncorrect.setTitulo(null);
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/tarea")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoIncorrect)));
        verify(tareaServicio, times(0)).crearTarea(any(TareaDto.class));
        perform.andExpect(status().isBadRequest());
    }

    /**
     * Is ok cuando se elimina tare.
     *
     * @throws Exception the exception
     */
//  -----------------------------------TEST ELIMINA TAREA-----------------------------------
    @Test
    public void isOkCuandoSeEliminaTare() throws Exception {
        Long num=1L;
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/tarea/{id}", num));
        verify(tareaServicio).eliminarTareaId(anyLong());
        perform.andExpect(status().isOk());
    }

    /**
     * Is method nor allowed cuando se elimina tarea y el id es null.
     *
     * @throws Exception the exception
     */
    @Test
    public void IsMethodNorAllowedCuandoSeEliminaTareaYElIdEsNull() throws Exception {
        Long num=null;
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/tarea/{id}", num));
        verify(tareaServicio, times(0)).eliminarTareaId(anyLong());
        perform.andExpect(status().isMethodNotAllowed());
    }

    //  -----------------------------------TEST MODIFICA TAREA-----------------------------------

    /**
     * Is ok cuando se modifica tarea.
     *
     * @throws Exception the exception
     */
    @Test
    public void isOkCuandoSeModificaTarea() throws Exception {
        Long num=1L;
        TareaDto tareaDto = crearTareaDto();
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/tarea/{id}",num)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tareaDto)));
        verify(tareaServicio).modificarTarea(anyLong(),any(TareaDto.class));
        perform.andExpect(status().isOk());
    }

    /**
     * Bad request cuando se modifica tarea y no cumple requisitos.
     *
     * @throws Exception the exception
     */
    @Test
    public void badRequestCuandoSeModificaTareaYNoCumpleRequisitos() throws Exception {
        Long num=1L;
        TareaDto dtoIncorrect = crearTareaDto();
        dtoIncorrect.setTitulo(null);
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/tarea/{id}",num)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoIncorrect)));
        verify(tareaServicio, times(0)).modificarTarea(anyLong(),any(TareaDto.class));
        perform.andExpect(status().isBadRequest());
    }

    /**
     * Is method nor allowed cuando se modifica tarea y el id es null.
     *
     * @throws Exception the exception
     */
    @Test
    public void IsMethodNorAllowedCuandoSeModificaTareaYElIdEsNull() throws Exception {
        Long num=null;
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/tarea/{id}",num));
        verify(tareaServicio, times(0)).modificarTarea(anyLong(),any(TareaDto.class));
        perform.andExpect(status().isMethodNotAllowed());
    }

    //  -----------------------------------TEST MODIFICA ESTADO -----------------------------------

    /**
     * Is ok modifica estado.
     *
     * @throws Exception the exception
     */
    @Test
    public void IsOKModificaEstado() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.patch("/tarea/finalizada/{id}",1L)
                .contentType(APPLICATION_JSON));
        verify(tareaServicio).modificarEstado(anyLong());
        perform.andExpect(status().isOk());
    }

    /**
     * Is method not allowed modifica estado.
     *
     * @throws Exception the exception
     */
    @Test
    public void isMethodNotAllowedModificaEstado() throws Exception {
        Long num=null;
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.patch("/tarea/finalizada/{id}",num)
                .contentType(APPLICATION_JSON));
        verify(tareaServicio, times(0)).modificarEstado(anyLong());
        perform.andExpect(status().isMethodNotAllowed());
    }


    /**
     * Crear tarea dto tarea dto.
     *
     * @return the tarea dto
     */
    private TareaDto crearTareaDto() {
        UsuarioDto dto1 = new UsuarioDto("Daniel", "Hernandez");
        UsuarioDto dto2 = new UsuarioDto("Juan", "Lopez");
        List<UsuarioDto> listaUsuarioDto = new ArrayList<>();
        listaUsuarioDto.add(dto1);
        listaUsuarioDto.add(dto2);
        return new TareaDto("poner lavadora", "poner a las 10", Categoria.CASA, listaUsuarioDto);
    }


}
