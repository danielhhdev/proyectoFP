package com.danielhh.proyectoDAM.mapper;

import com.danielhh.proyectoDAM.dto.UsuarioDto;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.respuesta.UsuarioRespuesta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Mapper usuario test.
 */
@ExtendWith(MockitoExtension.class)
public class MapperUsuarioTest {

    /**
     * The Mapper usuario.
     */
    @InjectMocks
    MapperUsuario mapperUsuario;

    /**
     * Convertir usuario dto en usuario.
     */
    @Test
    public void convertirUsuarioDtoEnUsuario(){
        UsuarioDto dto= new UsuarioDto("Daniel", "Hernandez");
        Usuario expect = new Usuario(1L,"daniel","hernandez",new ArrayList<>());
        Usuario actuall = mapperUsuario.dtoToUsuario(dto);
        assertEquals(expect.getNombre(),actuall.getNombre());
        assertEquals(expect.getApellidos(),actuall.getApellidos());
    }

    /**
     * Convertir usuario entidad a usuario respuesta.
     */
    @Test
    public void convertirUsuarioEntidadAUsuarioRespuesta(){
        UsuarioRespuesta expect = new UsuarioRespuesta("daniel", "hernandez");
        Usuario usuarioEntidad = new Usuario(1L,"daniel","hernandez",new ArrayList<>());
        UsuarioRespuesta actuall = mapperUsuario.entidadToRespuesta(usuarioEntidad);
        assertEquals(expect.getNombre(),actuall.getNombre());
        assertEquals(expect.getApellidos(),actuall.getApellidos());
    }
}
