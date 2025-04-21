package com.upeu.wom_curso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String codigo;    // Código único del curso (por ejemplo: "CS101")

    @Column(nullable = false)
    private String nombre;    // Nombre del curso (por ejemplo: "Programación Básica")

    @Column(nullable = false)
    private String horario;   // Horario en el que se dicta (Ej: "Lunes 10:00 - 12:00")

    @Column(nullable = false)
    private Integer capacidad; // Capacidad máxima de estudiantes

    @Column(nullable = false)
    private Integer ciclo;    // Ciclo en el que se dicta el curso (Ej: ciclo 1, ciclo 2, etc.)

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horario='" + horario + '\'' +
                ", capacidad=" + capacidad +
                ", ciclo=" + ciclo +
                '}';
    }
}
