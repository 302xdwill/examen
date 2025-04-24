package com.upeu.wom_matricula.service.impl;

import com.upeu.wom_matricula.dto.EstudianteDto;
import com.upeu.wom_matricula.dto.CursoDto;
import com.upeu.wom_matricula.entity.Matricula;
import com.upeu.wom_matricula.feign.CursoClient;
import com.upeu.wom_matricula.feign.EstudianteClient;
import com.upeu.wom_matricula.repository.MatriculaRepository;
import com.upeu.wom_matricula.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final EstudianteClient estudianteClient;
    private final CursoClient cursoClient;

    @Override
    public Matricula registrarMatricula(Matricula matricula) {
        EstudianteDto estudiante = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
        if (!"ACTIVO".equalsIgnoreCase(estudiante.getEstado())) {
            throw new RuntimeException("Estudiante no está activo.");
        }

        matricula.getDetalle().forEach(detalle -> {
            CursoDto curso = cursoClient.obtenerCursoPorId(detalle.getCursoId());
            if (curso.getCapacidad() <= 0) {
                throw new RuntimeException("El curso " + curso.getNombre() + " no tiene capacidad.");
            }
        });

        matricula.setFechaMatricula(LocalDate.now());
        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> listarMatriculas() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula obtenerMatriculaPorId(Integer id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));
    }

    @Override
    public Matricula actualizarMatricula(Integer id, Matricula matricula) {
        Matricula existente = obtenerMatriculaPorId(id);
        existente.setDetalle(matricula.getDetalle());
        existente.setCiclo(matricula.getCiclo());
        return matriculaRepository.save(existente);
    }

    @Override
    public void eliminarMatricula(Integer id) {
        matriculaRepository.deleteById(id);
    }
}
