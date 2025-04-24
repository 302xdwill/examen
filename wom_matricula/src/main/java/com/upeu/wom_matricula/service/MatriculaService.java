package com.upeu.wom_matricula.service;

import com.upeu.wom_matricula.entity.Matricula;

import java.util.List;

public interface MatriculaService {

    Matricula registrarMatricula(Matricula matricula);

    List<Matricula> listarMatriculas();

    Matricula obtenerMatriculaPorId(Integer id);

    Matricula actualizarMatricula(Integer id, Matricula matricula);

    void eliminarMatricula(Integer id);
}
