package com.upeu.wom_matricula.dto;

import lombok.Data;

@Data
public class EstudianteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String carrera;
    private String estado;       // ACTIVO o INACTIVO
    private Integer cicloActual;
}
