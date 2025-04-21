package com.upeu.wom_estudiante.util;

import com.upeu.wom_estudiante.entity.Estudiante;
import com.upeu.wom_estudiante.repository.EstudianteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstudianteSeeder implements CommandLineRunner {

    private final EstudianteRepository estudianteRepository;

    public EstudianteSeeder(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (estudianteRepository.count() == 0) {  // Verifica si ya hay datos
            List<Estudiante> estudiantes = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {  // Generamos 20 estudiantes
                Estudiante estudiante = new Estudiante();
                estudiante.setNombre("Nombre" + i);
                estudiante.setApellido("Apellido" + i);
                estudiante.setDni("1234567" + String.format("%02d", i));  // DNI de 8 dígitos
                estudiante.setCarrera("Carrera " + (i % 3 + 1));  // Asignar alguna carrera (Carrera 1, Carrera 2, etc)
                estudiante.setEstado(i % 2 == 0 ? "ACTIVO" : "INACTIVO");  // Alterna entre "ACTIVO" e "INACTIVO"
                estudiante.setCicloActual(i % 10 + 1);  // Ciclo entre 1 y 10

                estudiantes.add(estudiante);
            }

            // Guardar los estudiantes en la base de datos
            estudianteRepository.saveAll(estudiantes);
            System.out.println("Datos de estudiantes insertados correctamente.");
        } else {
            System.out.println("Los estudiantes ya existen, no se insertaron datos.");
        }
    }
}
