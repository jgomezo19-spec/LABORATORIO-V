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

import com.lab5.apis.model.Libro;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final List<Libro> libros = new ArrayList<>();

    public LibroController() {

        libros.add(new Libro(1L, "Cien años de soledad",
                "Gabriel Garcia Marquez", "Novela", 120.0));

        libros.add(new Libro(2L, "Don Quijote de la Mancha",
                "Miguel de Cervantes", "Novela", 150.0));

        libros.add(new Libro(3L, "1984",
                "George Orwell", "Ciencia ficcion", 100.0));

        libros.add(new Libro(4L, "El Principito",
                "Antoine de Saint-Exupery", "Fabula", 80.0));

        libros.add(new Libro(5L, "Harry Potter",
                "J.K. Rowling", "Fantasia", 180.0));
    }

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libros;
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {

        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                return libro;
            }
        }

        return null;
    }

    @PostMapping
    public Libro crear(@RequestBody Libro libro) {

        long nuevoId = libros.stream()
                .mapToLong(Libro::getId)
                .max()
                .orElse(0) + 1;

        libro.setId(nuevoId);
        libros.add(libro);

        return libro;
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id,
                            @RequestBody Libro actualizado) {

        for (int i = 0; i < libros.size(); i++) {

            if (libros.get(i).getId().equals(id)) {

                actualizado.setId(id);
                libros.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Libro actualizarParcial(@PathVariable Long id,
                                   @RequestBody Libro datos) {

        for (Libro libro : libros) {

            if (libro.getId().equals(id)) {

                if (datos.getTitulo() != null) {
                    libro.setTitulo(datos.getTitulo());
                }

                if (datos.getAutor() != null) {
                    libro.setAutor(datos.getAutor());
                }

                if (datos.getGenero() != null) {
                    libro.setGenero(datos.getGenero());
                }

                if (datos.getPrecio() != 0) {
                    libro.setPrecio(datos.getPrecio());
                }

                return libro;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < libros.size(); i++) {

            if (libros.get(i).getId().equals(id)) {

                libros.remove(i);

                return "Libro eliminado correctamente";
            }
        }

        return "Libro no encontrado";
    }
}