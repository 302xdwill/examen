package com.upeu.wom_curso.util;

import com.upeu.wom_curso.entity.Curso;
import com.upeu.wom_curso.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CursoSeeder implements CommandLineRunner {

    private final CursoRepository cursoRepository;

    public CursoSeeder(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public void run(String... args) {
        if (cursoRepository.count() == 0) {
            Curso curso1 = new Curso();
            curso1.setCodigo("CS101");
            curso1.setNombre("Programación Básica");
            curso1.setHorario("Lunes, Miércoles 10:00 - 12:00");
            curso1.setCapacidad(30);
            curso1.setCiclo(1);

            Curso curso2 = new Curso();
            curso2.setCodigo("CS102");
            curso2.setNombre("Estructuras de Datos");
            curso2.setHorario("Martes, Jueves 14:00 - 16:00");
            curso2.setCapacidad(25);
            curso2.setCiclo(2);

            cursoRepository.saveAll(Arrays.asList(curso1, curso2));

            System.out.println("Cursos de ejemplo insertados correctamente.");
        } else {
            System.out.println("Los cursos ya existen, no se insertaron datos.");
        }
    }
}
