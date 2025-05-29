package com.arquitectura.examen.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arquitectura.examen.model.Producto;
import com.arquitectura.examen.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto creado = productoService.create(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> lista = productoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id,
            @RequestBody Producto producto) {
        Producto actualizado = productoService.update(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Producto> cambiarEstado(@PathVariable Integer id, @RequestBody String nuevoEstado) {
        Producto actualizado = productoService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(actualizado);
    }

    @PostMapping("/{id}/aumentarstock")
    public ResponseEntity<Producto> aumentarStock(@PathVariable Integer id, @RequestBody Map<String, Object> datos) {
        Integer cantidad = (Integer) datos.get("cantidad");
        BigDecimal precioCompra = new BigDecimal(datos.get("precioCompra").toString());
        Producto actualizado = productoService.aumentarStock(id, cantidad, precioCompra);
        return ResponseEntity.ok(actualizado);
    }

    @PostMapping("/{id}/disminuirstock")
    public ResponseEntity<Producto> disminuirStock(@PathVariable Integer id, @RequestBody Map<String, Object> datos) {
        Integer cantidad = (Integer) datos.get("cantidad");
        Producto actualizado = productoService.disminuirStock(id, cantidad);
        return ResponseEntity.ok(actualizado);
    }

}
