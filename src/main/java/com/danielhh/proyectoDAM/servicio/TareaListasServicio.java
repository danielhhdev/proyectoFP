package com.danielhh.proyectoDAM.servicio;

import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.exception.NoSeEncontroEnBaseDatos;
import com.danielhh.proyectoDAM.mapper.MapperTarea;
import com.danielhh.proyectoDAM.repositorio.ITareaRepositorio;
import com.danielhh.proyectoDAM.repositorio.IUsuarioRepositorio;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Tarea listas servicio.
 */
@Service
public class TareaListasServicio implements ITareaListasServicio {

    /**
     * The Tarea repositorio.
     */
    public final ITareaRepositorio tareaRepositorio;

    /**
     * The Usuario repositorio.
     */
    public final IUsuarioRepositorio usuarioRepositorio;

    /**
     * The Mapper tarea.
     */
    public final MapperTarea mapperTarea;

    /**
     * Instantiates a new Tarea listas servicio.
     *
     * @param tareaRepositorio   the tarea repositorio
     * @param usuarioRepositorio the usuario repositorio
     * @param mapperTarea        the mapper tarea
     */
    public TareaListasServicio(ITareaRepositorio tareaRepositorio, IUsuarioRepositorio usuarioRepositorio, MapperTarea mapperTarea) {
        this.tareaRepositorio = tareaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.mapperTarea = mapperTarea;
    }


    /**
     * Listar tareas list.
     *
     * @return the list
     */
    @Override
    public List<TareaRespuesta> listarTareas() {

        List<Tarea> listaTareas = tareaRepositorio.findAll();

        List<TareaRespuesta> listaTareasRespuesta = new ArrayList<>();

        for (Tarea tarea : listaTareas) {
            listaTareasRespuesta.add(mapperTarea.entidadToRespuesta(tarea));
        }
        return listaTareasRespuesta;
    }

    /**
     * Listar tareas usuario list.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     * @return the list
     */
    @Override
    public List<TareaRespuesta> listarTareasUsuario(String nombre, String apellido) {
        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByNombreAndApellidos(nombre, apellido);

        if (usuarioEncontrado.isEmpty()) throw new NoSeEncontroEnBaseDatos();

        List<Tarea> listaTareasUsuario = new ArrayList<>();
        for (int i = 0; i < usuarioEncontrado.get().getTareas().size(); i++) {
            listaTareasUsuario.add(usuarioEncontrado.get().getTareas().get(i));
        }
        List<TareaRespuesta> listaTareasRespuesta = new ArrayList<>();

        for (Tarea tarea : listaTareasUsuario) {
            listaTareasRespuesta.add(mapperTarea.entidadToRespuesta(tarea));
        }
        return listaTareasRespuesta;
    }

    /**
     * Obtener tarea tarea respuesta.
     *
     * @param id the id
     * @return the tarea respuesta
     */
    @Override
    public TareaRespuesta obtenerTarea(Long id) {
        Optional<Tarea> tareaEncontrada = tareaRepositorio.findById(id);

        if (tareaEncontrada.isEmpty()) throw new NoSeEncontroEnBaseDatos();

        TareaRespuesta tareaRespuesta = mapperTarea.entidadToRespuesta(tareaEncontrada.get());

        return tareaRespuesta;
    }

    /**
     * Listar tareas usuario con estado list.
     *
     * @param nombre   the nombre
     * @param apellido the apellido
     * @param estado   the estado
     * @return the list
     */
    @Override
    public List<TareaRespuesta> listarTareasUsuarioConEstado(String nombre, String apellido, TareaEstado estado) {
        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByNombreAndApellidos(nombre, apellido);

        if (usuarioEncontrado.isEmpty()) throw new NoSeEncontroEnBaseDatos();

        List<Tarea> listaTareasUsuario = new ArrayList<>();
        for (int i = 0; i < usuarioEncontrado.get().getTareas().size(); i++) {
            if (usuarioEncontrado.get().getTareas().get(i).getEstado()==estado)
                listaTareasUsuario.add(usuarioEncontrado.get().getTareas().get(i));
        }
        List<TareaRespuesta> listaTareasRespuesta = new ArrayList<>();

        for (Tarea tarea : listaTareasUsuario) {
            listaTareasRespuesta.add(mapperTarea.entidadToRespuesta(tarea));
        }
        return listaTareasRespuesta;
    }
}
