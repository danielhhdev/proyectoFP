package com.danielhh.proyectoDAM.servicio;

import com.danielhh.proyectoDAM.dto.TareaDto;
import com.danielhh.proyectoDAM.dto.UsuarioDto;
import com.danielhh.proyectoDAM.entidad.Categoria;
import com.danielhh.proyectoDAM.entidad.Tarea;
import com.danielhh.proyectoDAM.entidad.TareaEstado;
import com.danielhh.proyectoDAM.entidad.Usuario;
import com.danielhh.proyectoDAM.exception.NoSeEncontroEnBaseDatos;
import com.danielhh.proyectoDAM.exception.TareaNoGuardada;
import com.danielhh.proyectoDAM.mapper.MapperTarea;
import com.danielhh.proyectoDAM.repositorio.ITareaRepositorio;
import com.danielhh.proyectoDAM.repositorio.IUsuarioRepositorio;
import com.danielhh.proyectoDAM.respuesta.TareaRespuesta;
import com.danielhh.proyectoDAM.respuesta.UsuarioRespuesta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * The type Tarea service test.
 */
@ExtendWith(MockitoExtension.class)
public class TareaServiceTest {

    /**
     * The Tarea servicio.
     */
    @InjectMocks
    private ITareaServicio tareaServicio;

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

    /**
     * The Tarea argument captor.
     */
    @Captor
    ArgumentCaptor<Tarea> tareaArgumentCaptor;

    /**
     * The User.
     */
    Usuario user = new Usuario(1L, "Daniel", "Hernandez", new ArrayList<>());

    /**
     * Tarea creada correctamente cuando usuario no existe.
     */
//  -----------------------------------TEST CREAR TAREA-----------------------------------
    @Test
    public void tareaCreadaCorrectamenteCuandoUsuarioNoExiste() {
        TareaDto dto = crearTareaDto();
        Tarea tarea = crearTarea();
        when(mapperTarea.dtoToTarea(any())).thenReturn(tarea);
        when(usuarioRepositorio.findByNombreAndApellidos(any(), any())).thenReturn(Optional.empty());
        tareaServicio.crearTarea(dto);
        verify(tareaRepositorio).save(tareaArgumentCaptor.capture());
        Tarea tareaCapturada = tareaArgumentCaptor.getValue();
        assertEquals(tarea.getTitulo(), tareaCapturada.getTitulo());
        assertEquals(tarea.getDescripcion(), tareaCapturada.getDescripcion());
        assertEquals(tarea.getCategoria(), tareaCapturada.getCategoria());
        assertEquals(tarea.getEstado(), tareaCapturada.getEstado());
        assertIterableEquals(tarea.getUsuarios(), tareaCapturada.getUsuarios());
    }

    /**
     * Tarea creada correctamente cuando usuario existe.
     */
    @Test
    public void tareaCreadaCorrectamenteCuandoUsuarioExiste() {
        TareaDto dto = crearTareaDto();
        Tarea tarea = crearTarea();
        when(mapperTarea.dtoToTarea(any())).thenReturn(tarea);
        when(usuarioRepositorio.findByNombreAndApellidos(any(), any())).thenReturn(Optional.ofNullable(user));
        tareaServicio.crearTarea(dto);
        verify(tareaRepositorio).save(tareaArgumentCaptor.capture());
        Tarea tareaCapturada = tareaArgumentCaptor.getValue();
        assertEquals(tarea.getTitulo(), tareaCapturada.getTitulo());
        assertEquals(tarea.getDescripcion(), tareaCapturada.getDescripcion());
        assertEquals(tarea.getCategoria(), tareaCapturada.getCategoria());
        assertEquals(tarea.getEstado(), tareaCapturada.getEstado());
        assertEquals(tarea.getUsuarios().get(0).getNombre(), tareaCapturada.getUsuarios().get(0).getNombre());
        assertEquals(tarea.getUsuarios().get(0).getApellidos(), tareaCapturada.getUsuarios().get(0).getApellidos());

    }

    /**
     * Exception tarea no guardada cuando se crea tarea.
     */
    @Test
    public void exceptionTareaNoGuardadaCuandoSeCreaTarea() {
        Throwable exception = assertThrows(TareaNoGuardada.class, () -> {
            tareaServicio.crearTarea(any());
        });
        verify(tareaRepositorio, times(0)).save(any());
        assertEquals("La tarea no se ha guardado en la base de datos", exception.getMessage());
    }

    /**
     * Tarea eliminada cuando tarea existente.
     */
//  -----------------------------------TEST ELIMINAR TAREA-----------------------------------
    @Test
    public void tareaEliminadaCuandoTareaExistente() {
        Tarea tarea = crearTarea();
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.ofNullable(tarea));
        tareaServicio.eliminarTareaId(tarea.getIdTarea());
        verify(tareaRepositorio).delete(any());
    }

    /**
     * Exception no se encontro en base datos cuando tarea no existe al eliminar.
     */
    @Test
    public void exceptionNoSeEncontroEnBaseDatosCuandoTareaNoExisteAlEliminar() {
        Tarea tarea = crearTarea();
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(NoSeEncontroEnBaseDatos.class, () -> {
            tareaServicio.eliminarTareaId(tarea.getIdTarea());
        });
        verify(tareaRepositorio, times(0)).delete(any());
        assertEquals("No se encontro en la Base de Datos", exception.getMessage());
    }

    //  -----------------------------------TEST MODIFICAR TAREA-----------------------------------

    /**
     * Tarea modificada correctamente.
     */
    @Test
    public void tareaModificadaCorrectamente(){
        Tarea tarea = crearTarea();
        Tarea tareaModificada = crearTareaModificada();
        TareaDto dto = crearTareaModificadaDto();
        Tarea expect = crearTareaModificada();
        when(mapperTarea.dtoToTarea(any(TareaDto.class))).thenReturn(tareaModificada);
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.ofNullable(tarea));
        tareaServicio.modificarTarea(1L,dto);
        verify(tareaRepositorio).save(tareaArgumentCaptor.capture());
        Tarea tareaCapturada = tareaArgumentCaptor.getValue();
        assertEquals(expect.getTitulo(), tareaCapturada.getTitulo());
        assertEquals(expect.getDescripcion(), tareaCapturada.getDescripcion());
        assertEquals(expect.getCategoria(), tareaCapturada.getCategoria());
        assertEquals(expect.getEstado(), tareaCapturada.getEstado());
        assertEquals(expect.getUsuarios().get(0).getNombre(), tareaCapturada.getUsuarios().get(0).getNombre());
        assertEquals(expect.getUsuarios().get(0).getApellidos(), tareaCapturada.getUsuarios().get(0).getApellidos());
    }

    /**
     * Exception no se encontro en base datos cuando tarea no existe al modificar.
     */
    @Test
    public void exceptionNoSeEncontroEnBaseDatosCuandoTareaNoExisteAlModificar(){
        Tarea tareaModificada = crearTareaModificada();
        TareaDto dto = crearTareaModificadaDto();
        when(mapperTarea.dtoToTarea(any(TareaDto.class))).thenReturn(tareaModificada);
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(NoSeEncontroEnBaseDatos.class, () -> {
            tareaServicio.modificarTarea(tareaModificada.getIdTarea(),dto);
        });
        verify(tareaRepositorio,times(0)).save(any(Tarea.class));
        assertEquals("No se encontro en la Base de Datos", exception.getMessage());
    }


    //  -----------------------------------TEST MODIFICAR ESTADO-----------------------------------


    /**
     * Modifica estado correctamente.
     */
    @Test
    public void modificaEstadoCorrectamente(){
        Tarea tarea = crearTarea();
        TareaRespuesta expect = crearTareaRespuesta();
        when(tareaRepositorio.existsById(anyLong())).thenReturn(true);
        when(tareaRepositorio.findById(anyLong())).thenReturn(Optional.ofNullable(tarea));
        when(mapperTarea.entidadToRespuesta(any(Tarea.class))).thenReturn(expect);
        TareaRespuesta actuall = tareaServicio.modificarEstado(anyLong());
        assertTrue(expect.equals(actuall));
    }

    /**
     * Exception no se encontro en base datos cuando se modifica estado.
     */
    @Test
    public void exceptionNoSeEncontroEnBaseDatosCuandoSeModificaEstado(){
        when(tareaRepositorio.existsById(anyLong())).thenReturn(false);
        NoSeEncontroEnBaseDatos exception = assertThrows(NoSeEncontroEnBaseDatos.class, () -> {
            tareaServicio.modificarEstado(anyLong());
        });
        verify(tareaRepositorio,times(0)).cambiarTareaFinalizada(anyLong());
        assertEquals("No se encontro en la Base de Datos", exception.getMessage());
    }


    /**
     * Crear tarea tarea.
     *
     * @return the tarea
     */
    private Tarea crearTarea() {
        Usuario user1 = new Usuario(1L, "Daniel", "Hernandez", new ArrayList<>());
        List<Usuario> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new Tarea(1L, "poner lavadora", "poner a las 10", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear tarea respuesta tarea respuesta.
     *
     * @return the tarea respuesta
     */
    private TareaRespuesta crearTareaRespuesta() {
        UsuarioRespuesta user1 = new UsuarioRespuesta("Daniel", "Hernandez");
        List<UsuarioRespuesta> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new TareaRespuesta(1L, "poner lavadora", "poner a las 10", Categoria.CASA, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear tarea modificada tarea.
     *
     * @return the tarea
     */
    private Tarea crearTareaModificada() {
        Usuario user1 = new Usuario( "Juan", "Lopez");
        List<Usuario> listaUsuario = new ArrayList<>();
        listaUsuario.add(user1);

        return new Tarea(1L, "titulo modificado", "descripcion modificada", Categoria.TRABAJO, TareaEstado.EN_PROCESO, LocalDate.now(), listaUsuario);
    }

    /**
     * Crear tarea dto tarea dto.
     *
     * @return the tarea dto
     */
    private TareaDto crearTareaDto() {
        UsuarioDto dto1 = new UsuarioDto("Daniel", "Hernandez");
        List<UsuarioDto> listaUsuarioDto = new ArrayList<>();
        listaUsuarioDto.add(dto1);
        return new TareaDto("poner lavadora", "poner a las 10", Categoria.CASA, listaUsuarioDto);
    }

    /**
     * Crear tarea modificada dto tarea dto.
     *
     * @return the tarea dto
     */
    private TareaDto crearTareaModificadaDto() {
        UsuarioDto dto1 = new UsuarioDto("Juan", "Lopez");
        List<UsuarioDto> listaUsuarioDto = new ArrayList<>();
        listaUsuarioDto.add(dto1);
        return new TareaDto("titulo modificado", "descripcion modificada", Categoria.TRABAJO, listaUsuarioDto);
    }


}
