package com.danielhh.proyectoDAM.mapper;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.dto.UsuarioDto;
import com.danielhh.proyectoDAM.entidad.Categoria;
import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import com.danielhh.proyectoDAM.respuesta.UsuarioRespuesta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Mapper tarea test.
 */
@ExtendWith(MockitoExtension.class)
public class MapperTareaTest {

    /**
     * The Mapper tarea.
     */
    @InjectMocks
    MapperTarea mapperTarea;

    /**
     * The Mapper usuario.
     */
    @Mock
    MapperUsuario mapperUsuario;

    /**
     * Convertir tarea dto en tarea.
     */
    @Test
    public void convertirTareaDtoEnTarea() {
        TareaDto dto = crearTareaDto();
        Tarea expect = crearTarea();
        Tarea actuall = mapperTarea.dtoToTarea(dto);
        assertEquals(expect.getTitulo(), actuall.getTitulo());
        assertEquals(expect.getDescripcion(), actuall.getDescripcion());
        assertEquals(expect.getCategoria(), actuall.getCategoria());
        assertEquals(expect.getEstado(), actuall.getEstado());
        assertEquals(expect.getFechaCreacion(), actuall.getFechaCreacion());
        assertEquals(expect.getUsuarios().size(), actuall.getUsuarios().size());
    }

    /**
     * Convertir tarea entidad a tarea respuesta.
     */
    @Test
    public void convertirTareaEntidadATareaRespuesta() {
        Tarea tarea = crearTarea();
        TareaRespuesta expect = crearTareaRespuesta();
        TareaRespuesta actuall = mapperTarea.entidadToRespuesta(tarea);
        assertEquals(expect.getTitulo(), actuall.getTitulo());
        assertEquals(expect.getDescripcion(), actuall.getDescripcion());
        assertEquals(expect.getCategoria(), actuall.getCategoria());
        assertEquals(expect.getEstado(), actuall.getEstado());
        assertEquals(expect.getFechaCreacion(), actuall.getFechaCreacion());
        assertEquals(expect.getUsuarios().size(), actuall.getUsuarios().size());
    }


    /**
     * Crear tarea respuesta tarea respuesta.
     *
     * @return the tarea respuesta
     */
    private TareaRespuesta crearTareaRespuesta(){
        UsuarioRespuesta usuarioRespuesta = new UsuarioRespuesta("Daniel", "Hernandez");
        UsuarioRespuesta usuarioRespuesta2 = new UsuarioRespuesta("Juan", "Lopez");
        List<UsuarioRespuesta> listaUsuarioRespuesta = new ArrayList<>();
            listaUsuarioRespuesta.add(usuarioRespuesta);
            listaUsuarioRespuesta.add(usuarioRespuesta2);

        return new TareaRespuesta(1L, "poner lavadora","poner a las 10", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuarioRespuesta);
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

    /**
     * Crear tarea tarea.
     *
     * @return the tarea
     */
    private Tarea crearTarea() {
        Usuario user1 = new Usuario(1L, "Daniel", "Hernandez", new ArrayList<>());
        Usuario user2 = new Usuario(1L, "Juan", "Lopez", new ArrayList<>());
        List<Usuario> listaUsuario = new ArrayList<>();
            listaUsuario.add(user1);
            listaUsuario.add(user2);
        return new Tarea(1L, "poner lavadora", "poner a las 10", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

}
