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

import com.lab5.apis.model.Producto;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Laptop", 5000.0, "Tecnologia"));
        productos.add(new Producto(2L, "Mouse", 150.0, "Tecnologia"));
        productos.add(new Producto(3L, "Teclado", 300.0, "Tecnologia"));
        productos.add(new Producto(4L, "Monitor", 1800.0, "Tecnologia"));
        productos.add(new Producto(5L, "Audifonos", 450.0, "Audio"));
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        long nuevoId = productos.stream()
                .mapToLong(Producto::getId)
                .max()
                .orElse(0) + 1;

        producto.setId(nuevoId);
        productos.add(producto);

        return producto;
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id,
                               @RequestBody Producto actualizado) {

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {

                actualizado.setId(id);
                productos.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Producto actualizarParcial(@PathVariable Long id,
                                      @RequestBody Producto datos) {

        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    producto.setNombre(datos.getNombre());
                }

                if (datos.getPrecio() != 0) {
                    producto.setPrecio(datos.getPrecio());
                }

                if (datos.getCategoria() != null) {
                    producto.setCategoria(datos.getCategoria());
                }

                return producto;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {

                productos.remove(i);

                return "Producto eliminado correctamente";
            }
        }

        return "Producto no encontrado";
    }
}