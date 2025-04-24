package com.upeu.wom_matricula.service.impl;

import com.upeu.wom_matricula.dto.CursoDto;
import com.upeu.wom_matricula.dto.EstudianteDto;
import com.upeu.wom_matricula.entity.Matricula;
import com.upeu.wom_matricula.entity.MatriculaDetalle;
import com.upeu.wom_matricula.feign.CursoClient;
import com.upeu.wom_matricula.feign.EstudianteClient;
import com.upeu.wom_matricula.repository.MatriculaRepository;
import com.upeu.wom_matricula.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private final MatriculaRepository matriculaRepository;

    @Autowired
    private final EstudianteClient estudianteClient;

    @Autowired
    private final CursoClient cursoClient;

    @Override
    public Matricula registrarMatricula(Matricula matricula) {
        EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());

        if (estudianteDto == null || !estudianteDto.getEstado().equalsIgnoreCase("ACTIVO")) {
            throw new RuntimeException("El estudiante no está activo");
        }

        for (MatriculaDetalle detalle : matricula.getDetalle()) {
            CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());
            if (cursoDto.getCapacidad() <= 0) {
                throw new RuntimeException("El curso " + cursoDto.getNombre() + " ha alcanzado su capacidad máxima");
            }
        }

        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> listarMatriculas() {
        List<Matricula> matriculas = matriculaRepository.findAll();

        for (Matricula matricula : matriculas) {
            enrichMatricula(matricula);
        }

        return matriculas;
    }

    @Override
    public Matricula obtenerMatriculaPorId(Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        enrichMatricula(matricula);

        return matricula;
    }

    @Override
    public Matricula actualizarMatricula(Integer id, Matricula matriculaDetails) {
        Matricula existingMatricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        existingMatricula.setEstudianteId(matriculaDetails.getEstudianteId());
        existingMatricula.setFechaMatricula(matriculaDetails.getFechaMatricula());
        existingMatricula.setDetalle(matriculaDetails.getDetalle());
        existingMatricula.setCiclo(matriculaDetails.getCiclo());

        return matriculaRepository.save(existingMatricula);
    }

    @Override
    public void eliminarMatricula(Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
        matriculaRepository.delete(matricula);
    }

    // Método para enriquecer la matrícula con datos externos
    private void enrichMatricula(Matricula matricula) {
        // Obtener datos del estudiante
        EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
        matricula.setEstudiante(estudianteDto);

        // Enriquecer detalle y llenar lista de cursos
        List<CursoDto> cursos = new ArrayList<>();

        for (MatriculaDetalle detalle : matricula.getDetalle()) {
            CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());
            detalle.setCodigoCurso(cursoDto.getCodigo());
            detalle.setNombreCurso(cursoDto.getNombre());
            cursos.add(cursoDto); // Llenar la lista de cursos
        }

        matricula.setCursos(cursos); // Asignar cursos a la matrícula
    }
}
