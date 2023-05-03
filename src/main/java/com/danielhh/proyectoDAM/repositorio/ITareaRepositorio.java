package com.danielhh.proyectoDAM.repositorio;

import com.danielhh.proyectoDAM.entidad.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Tarea repositorio.
 */
@Repository
public interface ITareaRepositorio extends JpaRepository<Tarea, Long> {

    /**
     * Cambiar tarea finalizada.
     *
     * @param id the id
     */
    @Modifying
    @Query(value = "UPDATE TAREAS SET ESTADO=1 WHERE ID_TAREA=:id", nativeQuery=true)
    public void cambiarTareaFinalizada(@Param("id") Long id);

}
