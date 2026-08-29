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

import com.lab5.apis.model.Tarea;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final List<Tarea> tareas = new ArrayList<>();

    public TareaController() {

        tareas.add(new Tarea(1L, "Estudiar Java",
                "Repasar Spring Boot", "ALTA", false));

        tareas.add(new Tarea(2L, "Hacer ejercicio",
                "Entrenamiento de 30 minutos", "MEDIA", false));

        tareas.add(new Tarea(3L, "Leer libro",
                "Leer un capitulo", "BAJA", true));

        tareas.add(new Tarea(4L, "Hacer proyecto",
                "Terminar API REST", "ALTA", false));

        tareas.add(new Tarea(5L, "Enviar tarea",
                "Enviar laboratorio", "MEDIA", true));
    }

    @GetMapping
    public List<Tarea> obtenerTodos() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable Long id) {

        for (Tarea tarea : tareas) {

            if (tarea.getId().equals(id)) {
                return tarea;
            }
        }

        return null;
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {

        long nuevoId = tareas.stream()
                .mapToLong(Tarea::getId)
                .max()
                .orElse(0) + 1;

        tarea.setId(nuevoId);
        tareas.add(tarea);

        return tarea;
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Long id,
                            @RequestBody Tarea actualizada) {

        for (int i = 0; i < tareas.size(); i++) {

            if (tareas.get(i).getId().equals(id)) {

                actualizada.setId(id);
                tareas.set(i, actualizada);

                return actualizada;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Tarea actualizarParcial(@PathVariable Long id,
                                   @RequestBody Tarea datos) {

        for (Tarea tarea : tareas) {

            if (tarea.getId().equals(id)) {

                if (datos.getTitulo() != null) {
                    tarea.setTitulo(datos.getTitulo());
                }

                if (datos.getDescripcion() != null) {
                    tarea.setDescripcion(datos.getDescripcion());
                }

                if (datos.getPrioridad() != null) {
                    tarea.setPrioridad(datos.getPrioridad());
                }

                tarea.setCompletada(datos.isCompletada());

                return tarea;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < tareas.size(); i++) {

            if (tareas.get(i).getId().equals(id)) {

                tareas.remove(i);

                return "Tarea eliminada correctamente";
            }
        }

        return "Tarea no encontrada";
    }
}