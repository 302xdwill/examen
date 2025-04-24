package com.upeu.wom_matricula.controller;

import com.upeu.wom_matricula.entity.Matricula;
import com.upeu.wom_matricula.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    public Matricula registrarMatricula(@RequestBody Matricula matricula) {
        return matriculaService.registrarMatricula(matricula);
    }

    @GetMapping
    public List<Matricula> listarMatriculas() {
        return matriculaService.listarMatriculas();
    }

    @GetMapping("/{id}")
    public Matricula obtenerMatricula(@PathVariable Integer id) {
        return matriculaService.obtenerMatriculaPorId(id);
    }

    @PutMapping("/{id}")
    public Matricula actualizarMatricula(@PathVariable Integer id, @RequestBody Matricula matricula) {
        return matriculaService.actualizarMatricula(id, matricula);
    }

    @DeleteMapping("/{id}")
    public void eliminarMatricula(@PathVariable Integer id) {
        matriculaService.eliminarMatricula(id);
    }
}
