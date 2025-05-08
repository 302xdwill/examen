package com.upeu.wom_matricula.feign;

import com.upeu.wom_matricula.dto.EstudianteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Nombre del microservicio (según Eureka) y URL base para desarrollo local
@FeignClient(name = "wom-estudiante-service")
public interface EstudianteClient {

    @GetMapping("/{id}")
    EstudianteDto obtenerEstudiantePorId(@PathVariable("id") Integer id);
}
