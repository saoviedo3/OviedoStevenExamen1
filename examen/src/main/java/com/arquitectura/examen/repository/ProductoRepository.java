package com.arquitectura.examen.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.arquitectura.examen.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);



}
