package com.danielhh.proyectoDAM.mapper;

import com.danielhh.proyectoDAM.dto.UsuarioDto;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.respuesta.UsuarioRespuesta;
import org.springframework.stereotype.Component;

/**
 * The type Mapper usuario.
 */
@Component
public class MapperUsuario {

    /**
     * Dto to usuario usuario.
     *
     * @param dto the dto
     * @return the usuario
     */
    public Usuario dtoToUsuario (UsuarioDto dto){

        Usuario usuario = new Usuario();
            usuario.setNombre(dto.getNombre().toLowerCase());
            usuario.setApellidos(dto.getApellido().toLowerCase());

        return usuario;
    }

    /**
     * Entidad to respuesta usuario respuesta.
     *
     * @param usuario the usuario
     * @return the usuario respuesta
     */
    public UsuarioRespuesta entidadToRespuesta(Usuario usuario){
        UsuarioRespuesta respuesta = new UsuarioRespuesta();
            respuesta.setNombre(usuario.getNombre());
            respuesta.setApellidos(usuario.getApellidos());

        return respuesta;
    }
}
