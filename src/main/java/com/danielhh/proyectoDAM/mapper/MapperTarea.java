package com.danielhh.proyectoDAM.mapper;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import com.danielhh.proyectoDAM.respuesta.UsuarioRespuesta;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Mapper tarea.
 */
@Component
public class MapperTarea {

    /**
     * The Mapper usuario.
     */
    public final MapperUsuario mapperUsuario;

    /**
     * Instantiates a new Mapper tarea.
     *
     * @param mapperUsuario the mapper usuario
     */
    public MapperTarea(MapperUsuario mapperUsuario) {
        this.mapperUsuario = mapperUsuario;
    }

    /**
     * Dto to tarea tarea.
     *
     * @param dto the dto
     * @return the tarea
     */
    public Tarea dtoToTarea(TareaDto dto) {
        Tarea tarea = new Tarea();
            tarea.setTitulo(dto.getTitulo());
            tarea.setDescripcion(dto.getDescripcion());
            tarea.setCategoria(dto.getCategoria());
            tarea.setEstado(TareaEstado.EN_PROCESO);
            tarea.setFechaCreacion(LocalDate.now());

        List<Usuario> listaUsuarios = new ArrayList<>();
        for (int i = 0; i < dto.getUsuarios().size(); i++) {
            listaUsuarios.add(mapperUsuario.dtoToUsuario(dto.getUsuarios().get(i)));
        }
            tarea.setUsuarios(listaUsuarios);
        return tarea;
    }

    /**
     * Entidad to respuesta tarea respuesta.
     *
     * @param tarea the tarea
     * @return the tarea respuesta
     */
    public TareaRespuesta entidadToRespuesta(Tarea tarea){
        TareaRespuesta respuesta = new TareaRespuesta();
            respuesta.setIdTarea(tarea.getIdTarea());
            respuesta.setTitulo(tarea.getTitulo());
            respuesta.setDescripcion(tarea.getDescripcion());
            respuesta.setCategoria(tarea.getCategoria());
            respuesta.setEstado(tarea.getEstado());
            respuesta.setFechaCreacion(tarea.getFechaCreacion());

        List<UsuarioRespuesta> listaUsuariosRespuesta = new ArrayList<>();
        for(int i=0; i<tarea.getUsuarios().size();i++){
            listaUsuariosRespuesta.add(mapperUsuario.entidadToRespuesta(tarea.getUsuarios().get(i)));
        }
            respuesta.setUsuarios(listaUsuariosRespuesta);
        return respuesta;
    }
}
