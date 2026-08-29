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

import com.lab5.apis.model.Empleado;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final List<Empleado> empleados = new ArrayList<>();

    public EmpleadoController() {

        empleados.add(new Empleado(1L, "Carlos Gomez",
                "Programador", 6500.0, "Tecnologia"));

        empleados.add(new Empleado(2L, "Maria Lopez",
                "Contadora", 6000.0, "Finanzas"));

        empleados.add(new Empleado(3L, "Juan Perez",
                "Gerente", 9000.0, "Administracion"));

        empleados.add(new Empleado(4L, "Ana Martinez",
                "Disenadora", 5500.0, "Marketing"));

        empleados.add(new Empleado(5L, "Luis Hernandez",
                "Soporte", 5000.0, "Tecnologia"));
    }

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {

        for (Empleado empleado : empleados) {

            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }

        return null;
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {

        long nuevoId = empleados.stream()
                .mapToLong(Empleado::getId)
                .max()
                .orElse(0) + 1;

        empleado.setId(nuevoId);
        empleados.add(empleado);

        return empleado;
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id,
                               @RequestBody Empleado actualizado) {

        for (int i = 0; i < empleados.size(); i++) {

            if (empleados.get(i).getId().equals(id)) {

                actualizado.setId(id);
                empleados.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Empleado actualizarParcial(@PathVariable Long id,
                                      @RequestBody Empleado datos) {

        for (Empleado empleado : empleados) {

            if (empleado.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    empleado.setNombre(datos.getNombre());
                }

                if (datos.getPuesto() != null) {
                    empleado.setPuesto(datos.getPuesto());
                }

                if (datos.getSalario() != 0) {
                    empleado.setSalario(datos.getSalario());
                }

                if (datos.getDepartamento() != null) {
                    empleado.setDepartamento(datos.getDepartamento());
                }

                return empleado;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < empleados.size(); i++) {

            if (empleados.get(i).getId().equals(id)) {

                empleados.remove(i);

                return "Empleado eliminado correctamente";
            }
        }

        return "Empleado no encontrado";
    }
}