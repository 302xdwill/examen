package com.upeu.wom_matricula.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upeu.wom_matricula.dto.EstudianteDto;
import com.upeu.wom_matricula.dto.CursoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer estudianteId;

    private LocalDate fechaMatricula;

    private String ciclo;

    @Transient
    private EstudianteDto estudiante;

    @Transient
    private List<CursoDto> cursos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "matricula_id") // ✅ El campo en la tabla detalle
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<MatriculaDetalle> detalle;
}
