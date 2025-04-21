package com.upeu.wom_curso.repository;

import com.upeu.wom_curso.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    // Método personalizado para encontrar un curso por su código
    Curso findByCodigo(String codigo);
}
