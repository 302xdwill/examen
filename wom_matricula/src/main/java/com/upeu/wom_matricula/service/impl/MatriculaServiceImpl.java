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

    // Método para registrar una nueva matrícula
    @Override
    public Matricula registrarMatricula(Matricula matricula) {
        // Validar que el estudiante esté activo antes de registrar la matrícula
        EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
        if (estudianteDto == null || !estudianteDto.getEstado().equalsIgnoreCase("ACTIVO")) {
            throw new RuntimeException("El estudiante no está activo");
        }

        // Verificar que los cursos no superen la capacidad
        for (MatriculaDetalle detalle : matricula.getDetalle()) {
            CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());
            if (cursoDto.getCapacidad() <= 0) {
                throw new RuntimeException("El curso " + cursoDto.getNombre() + " ha alcanzado su capacidad máxima");
            }
        }

        // Guardar la matrícula en la base de datos
        return matriculaRepository.save(matricula);
    }

    // Método para listar todas las matriculas con detalles enriquecidos
    @Override
    public List<Matricula> listarMatriculas() {
        List<Matricula> matriculas = matriculaRepository.findAll();

        // Enriquecer cada matrícula con los datos del estudiante y los cursos
        for (Matricula matricula : matriculas) {
            // 1. Obtener el estudiante relacionado con la matrícula
            EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
            matricula.setEstudiante(estudianteDto);

            // 2. Enriquecer los detalles de la matrícula con los cursos
            for (MatriculaDetalle detalle : matricula.getDetalle()) {
                CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());
                detalle.setCodigoCurso(cursoDto.getCodigo());
                detalle.setNombreCurso(cursoDto.getNombre());
            }
        }

        return matriculas;
    }

    // Método para obtener una matrícula por su ID con detalles enriquecidos
    @Override
    public Matricula obtenerMatriculaPorId(Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        // 1. Obtener el estudiante relacionado con la matrícula
        EstudianteDto estudianteDto = estudianteClient.obtenerEstudiantePorId(matricula.getEstudianteId());
        matricula.setEstudiante(estudianteDto);

        // 2. Enriquecer los detalles con los cursos
        for (MatriculaDetalle detalle : matricula.getDetalle()) {
            CursoDto cursoDto = cursoClient.obtenerCursoPorId(detalle.getCursoId());
            detalle.setCodigoCurso(cursoDto.getCodigo());
            detalle.setNombreCurso(cursoDto.getNombre());
        }

        return matricula;
    }

    // Método para actualizar los datos de una matrícula
    @Override
    public Matricula actualizarMatricula(Integer id, Matricula matriculaDetails) {
        Matricula existingMatricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        // Actualizar la matrícula existente con los nuevos detalles
        existingMatricula.setEstudianteId(matriculaDetails.getEstudianteId());
        existingMatricula.setFechaMatricula(matriculaDetails.getFechaMatricula());
        existingMatricula.setDetalle(matriculaDetails.getDetalle());

        // Guardar y devolver la matrícula actualizada
        return matriculaRepository.save(existingMatricula);
    }

    // Método para eliminar una matrícula
    @Override
    public void eliminarMatricula(Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
        matriculaRepository.delete(matricula);
    }
}
