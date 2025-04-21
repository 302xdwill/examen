package com.upeu.wom_curso.controller;

import com.upeu.wom_curso.entity.Curso;
import com.upeu.wom_curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Curso> buscar(@PathVariable Integer id) {
        return cursoService.buscar(id);
    }

    @PostMapping
    public Curso guardar(@RequestBody Curso curso) {
        return cursoService.guardar(curso);
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Integer id, @RequestBody Curso curso) {
        return cursoService.actualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        cursoService.eliminar(id);
    }

    @GetMapping("/codigo/{codigo}")
    public Curso buscarPorCodigo(@PathVariable String codigo) {
        return cursoService.buscarPorCodigo(codigo);
    }
}
