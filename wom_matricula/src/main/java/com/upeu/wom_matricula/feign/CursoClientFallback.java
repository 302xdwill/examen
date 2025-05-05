package com.upeu.wom_matricula.feign;

import com.upeu.wom_matricula.dto.CursoDto;
import org.springframework.stereotype.Component;

@Component
public class CursoClientFallback implements CursoClient {

    @Override
    public CursoDto obtenerCursoPorId(Integer id) {
        CursoDto curso = new CursoDto();
        curso.setId(id);
        curso.setCodigo("N/A");
        curso.setNombre("Curso no disponible");
        curso.setHorario("Sin horario");
        curso.setCapacidad(0);
        curso.setCiclo(0);
        return curso;
    }

}
