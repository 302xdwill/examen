package com.upeu.wom_estudiante.repository;

import com.upeu.wom_estudiante.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    // Método para verificar si el DNI ya existe
    boolean existsByDni(String dni);
}
