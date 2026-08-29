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

import com.lab5.apis.model.Vehiculo;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoController() {

        vehiculos.add(new Vehiculo(1L, "Toyota",
                "Corolla", 2022, 150000.0));

        vehiculos.add(new Vehiculo(2L, "Honda",
                "Civic", 2023, 180000.0));

        vehiculos.add(new Vehiculo(3L, "Ford",
                "Mustang", 2021, 300000.0));

        vehiculos.add(new Vehiculo(4L, "Nissan",
                "Sentra", 2022, 140000.0));

        vehiculos.add(new Vehiculo(5L, "Mazda",
                "CX-5", 2024, 250000.0));
    }

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculos;
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getId().equals(id)) {
                return vehiculo;
            }
        }

        return null;
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {

        long nuevoId = vehiculos.stream()
                .mapToLong(Vehiculo::getId)
                .max()
                .orElse(0) + 1;

        vehiculo.setId(nuevoId);
        vehiculos.add(vehiculo);

        return vehiculo;
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable Long id,
                               @RequestBody Vehiculo actualizado) {

        for (int i = 0; i < vehiculos.size(); i++) {

            if (vehiculos.get(i).getId().equals(id)) {

                actualizado.setId(id);
                vehiculos.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Vehiculo actualizarParcial(@PathVariable Long id,
                                      @RequestBody Vehiculo datos) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getId().equals(id)) {

                if (datos.getMarca() != null) {
                    vehiculo.setMarca(datos.getMarca());
                }

                if (datos.getModelo() != null) {
                    vehiculo.setModelo(datos.getModelo());
                }

                if (datos.getAnio() != 0) {
                    vehiculo.setAnio(datos.getAnio());
                }

                if (datos.getPrecio() != 0) {
                    vehiculo.setPrecio(datos.getPrecio());
                }

                return vehiculo;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < vehiculos.size(); i++) {

            if (vehiculos.get(i).getId().equals(id)) {

                vehiculos.remove(i);

                return "Vehiculo eliminado correctamente";
            }
        }

        return "Vehiculo no encontrado";
    }
}