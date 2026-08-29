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

import com.lab5.apis.model.Pedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {

        pedidos.add(new Pedido(1L, "Carlos Gomez",
                "Laptop", 1, 5000.0, "PENDIENTE"));

        pedidos.add(new Pedido(2L, "Maria Lopez",
                "Mouse", 2, 300.0, "ENVIADO"));

        pedidos.add(new Pedido(3L, "Juan Perez",
                "Teclado", 1, 300.0, "ENTREGADO"));

        pedidos.add(new Pedido(4L, "Ana Martinez",
                "Monitor", 1, 1800.0, "PENDIENTE"));

        pedidos.add(new Pedido(5L, "Luis Hernandez",
                "Audifonos", 2, 900.0, "ENVIADO"));
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }

        return null;
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {

        long nuevoId = pedidos.stream()
                .mapToLong(Pedido::getId)
                .max()
                .orElse(0) + 1;

        pedido.setId(nuevoId);
        pedidos.add(pedido);

        return pedido;
    }

    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable Long id,
                             @RequestBody Pedido actualizado) {

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getId().equals(id)) {

                actualizado.setId(id);
                pedidos.set(i, actualizado);

                return actualizado;
            }
        }

        return null;
    }

    @PatchMapping("/{id}")
    public Pedido actualizarParcial(@PathVariable Long id,
                                    @RequestBody Pedido datos) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId().equals(id)) {

                if (datos.getCliente() != null) {
                    pedido.setCliente(datos.getCliente());
                }

                if (datos.getProducto() != null) {
                    pedido.setProducto(datos.getProducto());
                }

                if (datos.getCantidad() != 0) {
                    pedido.setCantidad(datos.getCantidad());
                }

                if (datos.getTotal() != 0) {
                    pedido.setTotal(datos.getTotal());
                }

                if (datos.getEstado() != null) {
                    pedido.setEstado(datos.getEstado());
                }

                return pedido;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        for (int i = 0; i < pedidos.size(); i++) {

            if (pedidos.get(i).getId().equals(id)) {

                pedidos.remove(i);

                return "Pedido eliminado correctamente";
            }
        }

        return "Pedido no encontrado";
    }
}