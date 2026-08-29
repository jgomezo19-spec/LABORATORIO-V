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

import com.lab5.apis.model.Pelicula;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {

        peliculas.add(new Pelicula(1L, "Inception",
                "Christopher Nolan", "Ciencia ficcion", 2010));

        peliculas.add(new Pelicula(2L, "Titanic",
                "James Cameron", "Romance", 1997));

        peliculas.add(new Pelicula(3L, "Avatar",
                "James Cameron", "Ciencia ficcion", 2009));

        peliculas.add(new Pelicula(4L, "Matrix",
                "Lana y Lilly Wachowski", "Accion", 1999));

        peliculas.add(new Pelicula(5L, "Toy Story",
                "John Lasseter", "Animacion", 1995));
    }

    @GetMapping
    public List<Pelicula> obtenerTodos() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getId().equals(id)) {
                return pelicula;
            }
        }

        return null;
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {

        long nuevoId = peliculas.stream()
                .mapToLong(Pelicula::getId)
                .max()
                .orElse(0) + 1;

        pelicula.setId(nuevoId);
        peliculas.add(pelicula);

        return pelicula;
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(@PathVariable Long id,
                               @RequestBody Pelicula actualizada) {

        for (int i = 0; i < peliculas.size(); i++) {

            if (peliculas.get(i).getId().equals(id)) {

                actualizada.setId(id);
                peliculas.set(i, actualizada);

                return actualizada;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Pelicula actualizarParcial(@PathVariable Long id,
                                      @RequestBody Pelicula datos) {

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getId().equals(id)) {

                if (datos.getTitulo() != null) {
                    pelicula.setTitulo(datos.getTitulo());
                }

                if (datos.getDirector() != null) {
                    pelicula.setDirector(datos.getDirector());
                }

                if (datos.getGenero() != null) {
                    pelicula.setGenero(datos.getGenero());
                }

                if (datos.getAnio() != 0) {
                    pelicula.setAnio(datos.getAnio());
                }

                return pelicula;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < peliculas.size(); i++) {

            if (peliculas.get(i).getId().equals(id)) {

                peliculas.remove(i);

                return "Pelicula eliminada correctamente";
            }
        }

        return "Pelicula no encontrada";
    }
}