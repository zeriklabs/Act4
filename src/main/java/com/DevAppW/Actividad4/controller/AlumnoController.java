package com.DevAppW.Actividad4.controller;

import com.DevAppW.Actividad4.model.Alumno;
import com.DevAppW.Actividad4.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // ── POST /alumnos ──────────────────────────────────────────────────────────

    /**
     * Agrega un nuevo alumno.
     *
     * Ejemplo de body (JSON):
     * {
     *   "boleta":  "2024630123",
     *   "nombre":  "Juan Pérez",
     *   "correo":  "juan@example.com",
     *   "edad":    20
     * }
     *
     * Respuestas:
     *  - 201 Created  → alumno guardado correctamente.
     *  - 409 Conflict → boleta o correo duplicado.
     */
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Alumno alumno) {
        try {
            Alumno guardado = alumnoService.agregar(alumno);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // ── GET /alumnos/reporte ───────────────────────────────────────────────────

    /**
     * Retorna un reporte de alumnos agrupados por edad.
     *
     * Ejemplo de respuesta:
     * {
     *   "18": [ { "id": 1, "boleta": "...", ... } ],
     *   "20": [ { "id": 2, "boleta": "...", ... } ]
     * }
     */
    @GetMapping("/reporte")
    public ResponseEntity<Map<Integer, List<Alumno>>> reporte() {
        return ResponseEntity.ok(alumnoService.reportePorEdad());
    }
}
