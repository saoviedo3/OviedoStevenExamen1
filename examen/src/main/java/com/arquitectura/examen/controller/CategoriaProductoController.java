package com.arquitectura.examen.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arquitectura.examen.model.CategoriaProducto;
import com.arquitectura.examen.service.CategoriaProductoService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    public CategoriaProductoController(CategoriaProductoService categoriaProductoService) {
        this.categoriaProductoService = categoriaProductoService;
    }

    @PostMapping
    public ResponseEntity<CategoriaProducto> crearCategoria(@RequestBody CategoriaProducto categoria) {
        CategoriaProducto creado = categoriaProductoService.create(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProducto>> listarCategorias() {
        List<CategoriaProducto> lista = categoriaProductoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProducto> obtenerCategoria(@PathVariable Integer id) {
        CategoriaProducto categoria = categoriaProductoService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProducto> actualizarCategoria(@PathVariable Integer id,
            @RequestBody CategoriaProducto categoria) {
        CategoriaProducto actualizado = categoriaProductoService.update(id, categoria);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
