package com.upeu.wom_matricula.repository;

import com.upeu.wom_matricula.entity.MatriculaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaDetalleRepository extends JpaRepository<MatriculaDetalle, Integer> {
    // Puedes agregar consultas personalizadas si hace falta
}
