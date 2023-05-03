package com.danielhh.proyectoDAM.servicio;

import com.danielhh.proyectoDAM.entidad.Categoria;
import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.exception.NoSeEncontroEnBaseDatos;
import com.danielhh.proyectoDAM.mapper.MapperTarea;
import com.danielhh.proyectoDAM.repositorio.ITareaRepositorio;
import com.danielhh.proyectoDAM.repositorio.IUsuarioRepositorio;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import com.danielhh.proyectoDAM.respuesta.UsuarioRespuesta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * The type Tarea listas servicio test.
 */
@ExtendWith(MockitoExtension.class)
public class TareaListasServicioTest {

    /**
     * The Tarea listas servicio.
     */
    @InjectMocks
    private TareaListasServicio tareaListasServicio;

    /**
     * The Tarea repositorio.
     */
    @Mock
    private ITareaRepositorio tareaRepositorio;

    /**
     * The Usuario repositorio.
     */
    @Mock
    private IUsuarioRepositorio usuarioRepositorio;

    /**
     * The Mapper tarea.
     */
    @Mock
    private MapperTarea mapperTarea;

    //  -----------------------------------TEST LISTAR TAREA-----------------------------------

    /**
     * Listar tareas correctamente.
     */
    @Test
    public void listarTareasCorrectamente(){
        List<Tarea> listaTareas = crearListaTarea();
        TareaRespuesta tarea1 = crearTareaRespuesta1();
        TareaRespuesta tarea2 = crearTareaRespuesta2();
        List<TareaRespuesta> expect = crearListaTareaRespuesta();
        when(tareaRepositorio.findAll()).thenReturn(listaTareas);
        when(mapperTarea.entidadToRespuesta(any())).thenReturn(tarea1,tarea2);
        List<TareaRespuesta> actual = tareaListasServicio.listarTareas();
        assertEquals(expect, actual);
    }


    //  -----------------------------------TEST LISTAR TAREAS USUARIO CONCRETO-----------------------------------


    /**
     * Listar tareas usuario correctamente.
     */
    @Test
    public void listarTareasUsuarioCorrectamente(){
        List<Tarea> listaTarea = crearListaTarea();
        Usuario usuario = new Usuario(1L,"Daniel", "Hernandez",listaTarea);
        TareaRespuesta tarea1 = crearTareaRespuesta1();
        TareaRespuesta tarea2 = crearTareaRespuesta2();
        List<TareaRespuesta> expect = crearListaTareaRespuesta();
        when(usuarioRepositorio.findByNombreAndApellidos(anyString(),anyString())).thenReturn(Optional.of(usuario));
        when(mapperTarea.entidadToRespuesta(any())).thenReturn(tarea1,tarea2);
        List<TareaRespuesta> actual = tareaListasServicio.listarTareasUsuario(anyString(),anyString());
        assertEquals(expect, actual);
    }


    /**
     * Listar tarea usuario no se encontro en base datos.
     */
    @Test
    public void listarTareaUsuarioNoSeEncontroEnBaseDatos(){
        when(usuarioRepositorio.findByNombreAndApellidos(anyString(),anyString())).thenReturn(Optional.empty());
        NoSeEncontroEnBaseDatos exception = assertThrows(NoSeEncontroEnBaseDatos.class, () -> {
            tareaListasServicio.listarTareasUsuario(anyString(),anyString());
        });
        assertEquals("No se encontro en la Base de Datos", exception.getMessage());
    }

    //  -----------------------------------TEST LISTAR UNA TAREA -----------------------------------


    /**
     * Obtener tarea correctamente.
     */
    @Test
    public void obtenerTareaCorrectamente(){
        Tarea tarea = crearTarea1();
        TareaRespuesta expect =crearTareaRespuesta1();
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.of(tarea));
        when(mapperTarea.entidadToRespuesta(any(Tarea.class))).thenReturn(expect);
        TareaRespuesta actual = tareaListasServicio.obtenerTarea(anyLong());
        assertEquals(expect, actual);
    }


    /**
     * Obtener tarea no se encontro en base datos.
     */
    @Test
    public void obtenerTareaNoSeEncontroEnBaseDatos(){
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.empty());
        NoSeEncontroEnBaseDatos exception = assertThrows(NoSeEncontroEnBaseDatos.class, () -> {
            tareaListasServicio.obtenerTarea(anyLong());
        });
        assertEquals("No se encontro en la Base de Datos", exception.getMessage());
    }


    //  -----------------------------------TEST LISTAR TAREAS DE UN USUARIO CONCRETO Y UN ESTADO -----------------------------------

    /**
     * Listarusuario estado concreto correctamente.
     */
    @Test
    public void listarusuarioEstadoConcretoCorrectamente(){
        Usuario user1 = new Usuario(1L, "Daniel", "Hernandez", crearListaTarea());
        TareaRespuesta tarea1 = crearTareaRespuesta1();
        TareaRespuesta tarea2 = crearTareaRespuesta2();
        List<TareaRespuesta> expect = crearListaTareaRespuesta();
        when(usuarioRepositorio.findByNombreAndApellidos(any(), any())).thenReturn(Optional.of(user1));
        when(mapperTarea.entidadToRespuesta(any())).thenReturn(tarea1,tarea2);
        List <TareaRespuesta> actual = tareaListasServicio.listarTareasUsuarioConEstado(any(),any(),TareaEstado.EN_PROCESO);
        assertEquals(expect, actual);
    }


    /**
     * Listarusuario estado concreto no se encontro en base datos.
     */
    @Test
    public void listarusuarioEstadoConcretoNoSeEncontroEnBaseDatos(){
        when(usuarioRepositorio.findByNombreAndApellidos(any(), any())).thenReturn(Optional.empty());
        NoSeEncontroEnBaseDatos exception = assertThrows(NoSeEncontroEnBaseDatos.class, () -> {
            tareaListasServicio.listarTareasUsuarioConEstado(any(),any(),TareaEstado.EN_PROCESO);
        });
        assertEquals("No se encontro en la Base de Datos", exception.getMessage());
    }


    /**
     * Crear tarea respuesta 1 tarea respuesta.
     *
     * @return the tarea respuesta
     */
    private TareaRespuesta crearTareaRespuesta1() {
        UsuarioRespuesta user1 = new UsuarioRespuesta("Daniel", "Hernandez");
        List<UsuarioRespuesta> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new TareaRespuesta(1L, "poner lavadora", "poner a las 10", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear tarea respuesta 2 tarea respuesta.
     *
     * @return the tarea respuesta
     */
    private TareaRespuesta crearTareaRespuesta2() {
        UsuarioRespuesta user1 = new UsuarioRespuesta("Daniel", "Hernandez");
        List<UsuarioRespuesta> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new TareaRespuesta(2L, "poner lavavajillas", "poner a las 20", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear tarea 1 tarea.
     *
     * @return the tarea
     */
    private Tarea crearTarea1(){
        Usuario user1 = new Usuario(1L, "Daniel", "Hernandez", new ArrayList<>());
        List<Usuario> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new Tarea(1L, "poner lavadora", "poner a las 10", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear tarea 2 tarea.
     *
     * @return the tarea
     */
    private Tarea crearTarea2(){
        Usuario user1 = new Usuario(1L, "Daniel", "Hernandez", new ArrayList<>());
        List<Usuario> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new Tarea(2L, "poner lavavajillas", "poner a las 20", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear lista tarea list.
     *
     * @return the list
     */
    private List<Tarea> crearListaTarea(){
        Tarea tarea1 = crearTarea1();
        Tarea tarea2 = crearTarea2();

        List<Tarea> listaTarea = new ArrayList<>();
            listaTarea.add(tarea1);
            listaTarea.add(tarea2);
        return listaTarea;
    }

    /**
     * Crear lista tarea respuesta list.
     *
     * @return the list
     */
    private List<TareaRespuesta> crearListaTareaRespuesta(){
        List<TareaRespuesta> listaTarea = new ArrayList<>();
            listaTarea.add(crearTareaRespuesta1());
            listaTarea.add(crearTareaRespuesta2());
            return listaTarea;
    }

}
