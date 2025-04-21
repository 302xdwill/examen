package com.upeu.wom_estudiante.controller;

import com.upeu.wom_estudiante.entity.Estudiante;
import com.upeu.wom_estudiante.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Listar todos los estudiantes
    @GetMapping
    public List<Estudiante> listar() {
        return estudianteService.listar();
    }

    // Buscar un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscar(@PathVariable Integer id) {
        Optional<Estudiante> estudiante = estudianteService.buscar(id);
        if (estudiante.isPresent()) {
            return ResponseEntity.ok(estudiante.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar un nuevo estudiante
    @PostMapping
    public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteService.guardar(estudiante);
            return ResponseEntity.status(201).body(nuevoEstudiante);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Actualizar un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Integer id, @RequestBody Estudiante estudiante) {
        try {
            Estudiante estudianteActualizado = estudianteService.actualizar(id, estudiante);
            return ResponseEntity.ok(estudianteActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Eliminar un estudiante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
