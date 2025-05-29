package com.arquitectura.examen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquitectura.examen.model.CategoriaProducto;
import com.arquitectura.examen.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByEstado(String estado);

    List<Producto> findByCategoria(CategoriaProducto categoria);

}
