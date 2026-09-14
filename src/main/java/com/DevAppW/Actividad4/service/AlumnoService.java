package com.DevAppW.Actividad4.service;

import com.DevAppW.Actividad4.model.Alumno;
import com.DevAppW.Actividad4.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // ── Agregar alumno ─────────────────────────────────────────────────────────

    /**
     * Guarda un nuevo alumno aplicando las reglas de negocio:
     *  - La boleta no debe estar duplicada.
     *  - El correo no debe estar duplicado.
     *
     * @throws IllegalArgumentException si alguna regla se viola.
     */
    public Alumno agregar(Alumno alumno) {
        if (alumnoRepository.existsByBoleta(alumno.getBoleta())) {
            throw new IllegalArgumentException(
                "Ya existe un alumno con la boleta: " + alumno.getBoleta());
        }
        if (alumnoRepository.existsByCorreo(alumno.getCorreo())) {
            throw new IllegalArgumentException(
                "Ya existe un alumno con el correo: " + alumno.getCorreo());
        }
        return alumnoRepository.save(alumno);
    }

    // ── Reporte agrupado por edad ──────────────────────────────────────────────

    /**
     * Retorna un mapa donde la clave es la edad y el valor es la lista
     * de alumnos que tienen esa edad, ordenado de menor a mayor edad.
     */
    public Map<Integer, List<Alumno>> reportePorEdad() {
        List<Alumno> todos = alumnoRepository.findAllByOrderByEdadAsc();

        // LinkedHashMap para conservar el orden de inserción (edad ascendente)
        return todos.stream()
                .collect(Collectors.groupingBy(
                        Alumno::getEdad,
                        LinkedHashMap::new,
                        Collectors.toList()));
    }
}
