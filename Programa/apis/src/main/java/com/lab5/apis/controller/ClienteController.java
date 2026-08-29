package com.lab5.apis.controller;

import com.lab5.apis.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {

        clientes.add(new Cliente(1L, "Carlos", "Gomez",
                "carlos@gmail.com", "5555-1111"));

        clientes.add(new Cliente(2L, "Maria", "Lopez",
                "maria@gmail.com", "5555-2222"));

        clientes.add(new Cliente(3L, "Juan", "Perez",
                "juan@gmail.com", "5555-3333"));

        clientes.add(new Cliente(4L, "Ana", "Martinez",
                "ana@gmail.com", "5555-4444"));

        clientes.add(new Cliente(5L, "Luis", "Hernandez",
                "luis@gmail.com", "5555-5555"));
    }

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        return null;
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {

        long nuevoId = clientes.stream()
                .mapToLong(Cliente::getId)
                .max()
                .orElse(0) + 1;

        cliente.setId(nuevoId);
        clientes.add(cliente);

        return cliente;
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id,
                              @RequestBody Cliente actualizado) {

        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getId().equals(id)) {

                actualizado.setId(id);
                clientes.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcial(@PathVariable Long id,
                                     @RequestBody Cliente datos) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {

                if (datos.getNombre() != null) {
                    cliente.setNombre(datos.getNombre());
                }

                if (datos.getApellido() != null) {
                    cliente.setApellido(datos.getApellido());
                }

                if (datos.getCorreo() != null) {
                    cliente.setCorreo(datos.getCorreo());
                }

                if (datos.getTelefono() != null) {
                    cliente.setTelefono(datos.getTelefono());
                }

                return cliente;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getId().equals(id)) {

                clientes.remove(i);

                return "Cliente eliminado correctamente";
            }
        }

        return "Cliente no encontrado";
    }
}