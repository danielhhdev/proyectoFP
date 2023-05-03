package com.danielhh.proyectoDAM.repositorio;

import com.danielhh.proyectoDAM.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Usuario repositorio.
 */
@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     * Find by nombre and apellidos optional.
     *
     * @param nombre    the nombre
     * @param apellidos the apellidos
     * @return the optional
     */
    Optional<Usuario> findByNombreAndApellidos(String nombre, String apellidos);

}
