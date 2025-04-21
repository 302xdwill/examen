package com.upeu.wom_estudiante.entity;

import jakarta.persistence.*;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;  // Cambiado de Integer a String

    private String carrera;
    private String estado; // Ej: ACTIVO, INACTIVO
    private Integer cicloActual; // Ej: 1, 2, 3... hasta 10

    public Estudiante() {
    }

    public Estudiante(Integer id, String nombre, String apellido, String dni, String carrera, String estado, Integer cicloActual) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.carrera = carrera;
        this.estado = estado;
        this.cicloActual = cicloActual;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCicloActual() {
        return cicloActual;
    }

    public void setCicloActual(Integer cicloActual) {
        this.cicloActual = cicloActual;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", carrera='" + carrera + '\'' +
                ", estado='" + estado + '\'' +
                ", cicloActual=" + cicloActual +
                '}';
    }
}
