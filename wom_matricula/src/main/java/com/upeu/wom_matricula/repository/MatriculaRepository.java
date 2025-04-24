package com.upeu.wom_matricula.repository;

import com.upeu.wom_matricula.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    // Aquí puedes agregar métodos personalizados si necesitas, como por ejemplo:
    // List<Matricula> findByEstudianteId(Integer estudianteId);
}
