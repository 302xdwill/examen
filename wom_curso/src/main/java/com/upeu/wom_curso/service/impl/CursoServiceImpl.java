package com.upeu.wom_curso.service.impl;

import com.upeu.wom_curso.entity.Curso;
import com.upeu.wom_curso.repository.CursoRepository;
import com.upeu.wom_curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> buscar(Integer id) {
        return cursoRepository.findById(id);
    }

    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizar(Integer id, Curso curso) {
        curso.setId(id);  // Establecer el ID al curso para que se actualice
        return cursoRepository.save(curso);
    }

    @Override
    public void eliminar(Integer id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso buscarPorCodigo(String codigo) {
        return cursoRepository.findByCodigo(codigo);
    }
}
