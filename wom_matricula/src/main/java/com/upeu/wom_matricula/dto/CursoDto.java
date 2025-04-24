package com.upeu.wom_matricula.dto;

import lombok.Data;

@Data
public class CursoDto {
    private Integer id;
    private String codigo;
    private String nombre;
    private String horario;
    private Integer capacidad;
    private Integer ciclo;
}
