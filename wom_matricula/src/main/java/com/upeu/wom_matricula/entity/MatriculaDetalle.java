package com.upeu.wom_matricula.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MatriculaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cursoId;

    private String codigoCurso;

    private String nombreCurso;
}
