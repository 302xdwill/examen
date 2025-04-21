package com.upeu.wom_curso.service;

import com.upeu.wom_curso.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();                // Listar todos los cursos
    Optional<Curso> buscar(Integer id);  // Buscar un curso por ID
    Curso guardar(Curso curso);          // Guardar un nuevo curso
    Curso actualizar(Integer id, Curso curso);  // Actualizar curso existente
    void eliminar(Integer id);           // Eliminar un curso
    Curso buscarPorCodigo(String codigo); // Buscar un curso por código
}
