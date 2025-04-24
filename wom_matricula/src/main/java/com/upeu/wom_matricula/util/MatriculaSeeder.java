package com.upeu.wom_matricula.util;

import com.upeu.wom_matricula.entity.Matricula;
import com.upeu.wom_matricula.entity.MatriculaDetalle;
import com.upeu.wom_matricula.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class MatriculaSeeder implements CommandLineRunner {

    private final MatriculaRepository matriculaRepository;

    @Override
    public void run(String... args) {
        if (matriculaRepository.count() == 0) {
            // Detalles con IDs de cursos existentes
            MatriculaDetalle detalle1 = new MatriculaDetalle();
            detalle1.setCursoId(1); // Reemplaza con ID real de curso

            MatriculaDetalle detalle2 = new MatriculaDetalle();
            detalle2.setCursoId(2); // Reemplaza con otro ID real

            // Nueva matrícula
            Matricula matricula = new Matricula();
            matricula.setEstudianteId(1); // Reemplaza con ID real de estudiante
            matricula.setCiclo("2");
            matricula.setFechaMatricula(LocalDate.now());
            matricula.setDetalle(Arrays.asList(detalle1, detalle2));

            // Guardar
            matriculaRepository.save(matricula);

            System.out.println("✅ Seeder: Matrícula creada exitosamente.");
        } else {
            System.out.println("📌 Seeder: Ya existen matrículas. No se creó ninguna nueva.");
        }
    }
}
