package com.arquitectura.examen.service;

import com.arquitectura.examen.model.CategoriaProducto;
import com.arquitectura.examen.repository.CategoriaProductoRepository;
import com.arquitectura.examen.exception.CreateEntityException;
import com.arquitectura.examen.exception.ResourceNotFoundException;
import com.arquitectura.examen.exception.UpdateEntityException;
import com.arquitectura.examen.exception.DeleteEntityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProductoService(CategoriaProductoRepository categoriaProductoRepository) {
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    public List<CategoriaProducto> findAll() {
        return categoriaProductoRepository.findAll();
    }

    public CategoriaProducto findById(Integer id) {
        return categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto no encontrada con id=" + id));
    }

    @Transactional
    public CategoriaProducto create(CategoriaProducto categoriaProducto) {
        try {
            return categoriaProductoRepository.save(categoriaProducto);
        } catch (RuntimeException e) {
            throw new CreateEntityException("CategoriaProducto",
                    "Error al crear la categoria. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public CategoriaProducto update(Integer id, CategoriaProducto datos) {
        try {
            CategoriaProducto existente = findById(id);
            existente.setNombre(datos.getNombre());
            existente.setDescripcion(datos.getDescripcion());
            return categoriaProductoRepository.save(existente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new UpdateEntityException("CategoriaProducto",
                    "Error al actualizar la categoria. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            CategoriaProducto existente = findById(id);
            categoriaProductoRepository.delete(existente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new DeleteEntityException("CategoriaProducto",
                    "Error al eliminar la categoria. Detalle: " + e.getMessage());
        }
    }
}