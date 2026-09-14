package com.DevAppW.Actividad4.repository;

import com.DevAppW.Actividad4.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    /** Verifica si ya existe un alumno con esa boleta (regla de negocio). */
    boolean existsByBoleta(String boleta);

    /** Verifica si ya existe un alumno con ese correo (regla de negocio). */
    boolean existsByCorreo(String correo);

    /** Obtiene alumnos ordenados por edad para el reporte agrupado. */
    List<Alumno> findAllByOrderByEdadAsc();
}
