package com.DevAppW.Actividad4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String boleta;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private Integer edad;

    // ── Constructores ──────────────────────────────────────────────────────────

    public Alumno() {}

    public Alumno(String boleta, String nombre, String correo, Integer edad) {
        this.boleta  = boleta;
        this.nombre  = nombre;
        this.correo  = correo;
        this.edad    = edad;
    }

    // ── Getters y Setters ──────────────────────────────────────────────────────

    public Long getId()               { return id; }
    public void setId(Long id)        { this.id = id; }

    public String getBoleta()              { return boleta; }
    public void   setBoleta(String boleta) { this.boleta = boleta; }

    public String getNombre()              { return nombre; }
    public void   setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo()              { return correo; }
    public void   setCorreo(String correo) { this.correo = correo; }

    public Integer getEdad()               { return edad; }
    public void    setEdad(Integer edad)   { this.edad = edad; }
}
