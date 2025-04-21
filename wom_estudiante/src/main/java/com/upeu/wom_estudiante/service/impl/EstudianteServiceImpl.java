package com.upeu.wom_estudiante.service.impl;

import com.upeu.wom_estudiante.entity.Estudiante;
import com.upeu.wom_estudiante.repository.EstudianteRepository;
import com.upeu.wom_estudiante.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> buscar(Integer id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        // Validación de que el DNI es único
        if (!validarDniUnico(estudiante.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Integer id, Estudiante estudiante) {
        // Verificamos si existe el estudiante
        Optional<Estudiante> estudianteExistente = estudianteRepository.findById(id);
        if (estudianteExistente.isEmpty()) {
            throw new IllegalArgumentException("Estudiante no encontrado");
        }

        // Validación de que el DNI es único (excepto para el propio estudiante)
        Estudiante estudianteActualizado = estudianteExistente.get();
        if (!estudianteActualizado.getDni().equals(estudiante.getDni()) && !validarDniUnico(estudiante.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }

        estudiante.setId(id);
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public boolean validarDniUnico(String dni) {
        return !estudianteRepository.existsByDni(dni);
    }
}
