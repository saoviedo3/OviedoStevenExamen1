package com.arquitectura.examen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquitectura.examen.model.CategoriaProducto;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {

    Optional<CategoriaProducto> findByNombre(String nombre);

    List<CategoriaProducto> findByNombreContaining(String nombreParte);

}
