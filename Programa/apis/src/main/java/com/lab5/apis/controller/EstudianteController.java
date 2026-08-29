package com.lab5.apis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab5.apis.model.Estudiante;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteController() {
        estudiantes.add(new Estudiante(1L, "Carlos", "Gomez",
                "Ingenieria en Sistemas", 20));

        estudiantes.add(new Estudiante(2L, "Maria", "Lopez",
                "Administracion", 21));

        estudiantes.add(new Estudiante(3L, "Juan", "Perez",
                "Ingenieria Civil", 22));

        estudiantes.add(new Estudiante(4L, "Ana", "Martinez",
                "Arquitectura", 19));

        estudiantes.add(new Estudiante(5L, "Luis", "Hernandez",
                "Ingenieria en Sistemas", 23));
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudiantes;
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(id)) {
                return estudiante;
            }
        }

        return null;
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {

        long nuevoId = estudiantes.stream()
                .mapToLong(Estudiante::getId)
                .max()
                .orElse(0) + 1;

        estudiante.setId(nuevoId);
        estudiantes.add(estudiante);

        return estudiante;
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id,
                                 @RequestBody Estudiante actualizado) {

        for (int i = 0; i < estudiantes.size(); i++) {

            if (estudiantes.get(i).getId().equals(id)) {

                actualizado.setId(id);
                estudiantes.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Estudiante actualizarParcial(@PathVariable Long id,
                                        @RequestBody Estudiante datos) {

        for (Estudiante estudiante : estudiantes) {

            if (estudiante.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    estudiante.setNombre(datos.getNombre());
                }

                if (datos.getApellido() != null) {
                    estudiante.setApellido(datos.getApellido());
                }

                if (datos.getCarrera() != null) {
                    estudiante.setCarrera(datos.getCarrera());
                }

                if (datos.getEdad() != 0) {
                    estudiante.setEdad(datos.getEdad());
                }

                return estudiante;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < estudiantes.size(); i++) {

            if (estudiantes.get(i).getId().equals(id)) {

                estudiantes.remove(i);

                return "Estudiante eliminado correctamente";
            }
        }

        return "Estudiante no encontrado";
    }
}