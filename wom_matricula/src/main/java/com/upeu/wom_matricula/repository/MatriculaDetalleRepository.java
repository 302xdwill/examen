package com.upeu.wom_matricula.repository;

import com.upeu.wom_matricula.entity.MatriculaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaDetalleRepository extends JpaRepository<MatriculaDetalle, Integer> {
    int countByCursoId(Integer cursoId);
}
