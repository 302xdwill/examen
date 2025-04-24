package com.upeu.wom_matricula.service.impl;

import com.upeu.wom_matricula.dto.CursoDto;
import com.upeu.wom_matricula.dto.EstudianteDto;
import com.upeu.wom_matricula.entity.Matricula;
import com.upeu.wom_matricula.entity.MatriculaDetalle;
import com.upeu.wom_matricula.feign.CursoClient;
import com.upeu.wom_matricula.feign.EstudianteClient;
import com.upeu.wom_matricula.repository.MatriculaDetalleRepository;
import com.upeu.wom_matricula.repository.MatriculaRepository;
import com.upeu.wom_matricula.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final MatriculaDetalleRepository matriculaDetalleRepository;
    private final EstudianteClient estudianteClient;
    private final CursoClient cursoClient;

    @Override
    public Matricula registrarMatricula(Matricula matricula) {
        // Validar si el estudiante está activo
        EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
        if (!"ACTIVO".equalsIgnoreCase(estudianteDto.getEstado())) {
            throw new IllegalStateException("El estudiante no está activo para matricularse.");
        }

        // Validación de capacidad por curso
        for (MatriculaDetalle detalle : matricula.getDetalle()) {
            CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());

            // Validar capacidad
            int inscritos = matriculaDetalleRepository.countByCursoId(detalle.getCursoId());
            if (inscritos >= cursoDto.getCapacidad()) {
                throw new IllegalStateException("El curso '" + cursoDto.getNombre() + "' ya alcanzó su capacidad máxima.");
            }

            // Setear datos del curso en el detalle
            detalle.setCodigoCurso(cursoDto.getCodigo());
            detalle.setNombreCurso(cursoDto.getNombre());
        }

        return matriculaRepository.save(matricula);
    }

    @Override
    public List<Matricula> listarMatriculas() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        matriculas.forEach(this::enrichMatricula);
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

    private void enrichMatricula(Matricula matricula) {
        EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
        matricula.setEstudiante(estudianteDto);

        List<CursoDto> cursos = new ArrayList<>();
        for (MatriculaDetalle detalle : matricula.getDetalle()) {
            CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());
            detalle.setCodigoCurso(cursoDto.getCodigo());
            detalle.setNombreCurso(cursoDto.getNombre());
            cursos.add(cursoDto);
        }
        matricula.setCursos(cursos);
    }
}
