package com.upeu.wom_matricula.feign;

import com.upeu.wom_matricula.dto.CursoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "wom-curso-service",
        url="${PORT:${SERVER_PORT:0}}",
        fallback = CursoClientFallback.class
)

public interface CursoClient {

    @GetMapping("/{id}")
    CursoDto obtenerCursoPorId(@PathVariable("id") Integer id);
}
