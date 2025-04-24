package com.upeu.wom_matricula.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class MatriculaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cursoId;
    private String codigoCurso;
    private String nombreCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id")
    @JsonIgnore //  Evita ciclos de serialización
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Matricula matricula;
}
