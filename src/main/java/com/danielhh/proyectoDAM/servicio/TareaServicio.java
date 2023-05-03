package com.danielhh.proyectoDAM.servicio;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.exception.NoSeEncontroEnBaseDatos;
import com.danielhh.proyectoDAM.exception.TareaNoGuardada;
import com.danielhh.proyectoDAM.mapper.MapperTarea;
import com.danielhh.proyectoDAM.repositorio.ITareaRepositorio;
import com.danielhh.proyectoDAM.repositorio.IUsuarioRepositorio;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Tarea servicio.
 */
@Service
public class TareaServicio implements ITareaServicio {

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
     * Instantiates a new Tarea servicio.
     *
     * @param tareaRepositorio   the tarea repositorio
     * @param usuarioRepositorio the usuario repositorio
     * @param mapperTarea        the mapper tarea
     */
    public TareaServicio(ITareaRepositorio tareaRepositorio, IUsuarioRepositorio usuarioRepositorio, MapperTarea mapperTarea) {
        this.tareaRepositorio = tareaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.mapperTarea = mapperTarea;
    }


    /**
     * Crear tarea.
     *
     * @param tareaDto the tarea dto
     */
    @Override
    @Transactional
    public void crearTarea(TareaDto tareaDto) {

        try {
            Tarea tarea = mapperTarea.dtoToTarea(tareaDto);
            List<Usuario> listaUsuario = new ArrayList<>();
            for (int i = 0; i < tarea.getUsuarios().size(); i++) {
                Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByNombreAndApellidos(tarea.getUsuarios().get(i).getNombre(),
                                                                                                    tarea.getUsuarios().get(i).getApellidos());
                if (usuarioEncontrado.isPresent()) {
                    listaUsuario.add(usuarioEncontrado.get());
                } else {
                    listaUsuario.add(tarea.getUsuarios().get(i));
                }
            }
            tarea.setUsuarios(listaUsuario);
            tareaRepositorio.save(tarea);

        } catch (RuntimeException rte) {
            throw new TareaNoGuardada();
        }
    }

    /**
     * Se elimina una tarea que se ha buscado en la base de datos  traves de su id
     *
     * @param id the id
     */
    @Override
    @Transactional
    public void eliminarTareaId(Long id) {
        Optional<Tarea> tareaEncontrada = tareaRepositorio.findById(id);

        if (tareaEncontrada.isEmpty()) throw new NoSeEncontroEnBaseDatos();

        tareaRepositorio.delete(tareaEncontrada.get());
    }

    /**
     * Modificar tarea.
     *
     * @param id       the id
     * @param tareaDto the tarea dto
     */
    @Override
    @Transactional
    public void modificarTarea(Long id, TareaDto tareaDto) {
        Tarea tarea = mapperTarea.dtoToTarea(tareaDto);
        Optional<Tarea> tareaEncontrada=tareaRepositorio.findById(id);

        if (tareaEncontrada.isEmpty()) throw new NoSeEncontroEnBaseDatos();

        tareaEncontrada.get().setTitulo(tarea.getTitulo());
        tareaEncontrada.get().setDescripcion(tarea.getDescripcion());
        tareaEncontrada.get().setCategoria(tarea.getCategoria());
        tareaEncontrada.get().setUsuarios(tarea.getUsuarios());

        tareaRepositorio.save(tareaEncontrada.get());
    }

    /**
     * Modificar estado tarea respuesta.
     *
     * @param id the id
     * @return the tarea respuesta
     */
    @Override
    @Transactional
    public TareaRespuesta modificarEstado(Long id) {

        if(!tareaRepositorio.existsById(id)) throw new NoSeEncontroEnBaseDatos();

        tareaRepositorio.cambiarTareaFinalizada(id);

        Optional<Tarea> tareaEncontrada = tareaRepositorio.findById(id);

        return mapperTarea.entidadToRespuesta(tareaEncontrada.get());
    }


}
