package com.arquitectura.examen.service;

import com.arquitectura.examen.model.Producto;
import com.arquitectura.examen.model.CategoriaProducto;
import com.arquitectura.examen.repository.ProductoRepository;
import com.arquitectura.examen.exception.CreateEntityException;
import com.arquitectura.examen.exception.ResourceNotFoundException;
import com.arquitectura.examen.exception.UpdateEntityException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaProductoService categoriaProductoService;

    public ProductoService(ProductoRepository productoRepository,
            CategoriaProductoService categoriaProductoService) {
        this.productoRepository = productoRepository;
        this.categoriaProductoService = categoriaProductoService;
    }

    public Producto findById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id=" + id));
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional
    public Producto create(Producto producto) {
        try {
            CategoriaProducto categoria = categoriaProductoService.findById(producto.getCategoria().getId());
            producto.setCategoria(categoria);
            producto.setEstado("Activo");
            return productoRepository.save(producto);
        } catch (RuntimeException e) {
            throw new CreateEntityException("Producto", "Error al crear el producto. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public Producto update(Integer id, Producto datos) {
        try {
            Producto existente = findById(id);
            CategoriaProducto categoria = categoriaProductoService.findById(datos.getCategoria().getId());
            existente.setNombre(datos.getNombre());
            existente.setDescripcion(datos.getDescripcion());
            existente.setPrecioVenta(datos.getPrecioVenta());
            existente.setCostoCompra(datos.getCostoCompra());
            existente.setStock(datos.getStock());
            existente.setEstado(datos.getEstado());
            existente.setCategoria(categoria);
            return productoRepository.save(existente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new UpdateEntityException("Producto", "Error al actualizar el producto. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Producto existente = findById(id);
            productoRepository.delete(existente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new UpdateEntityException("Producto", "Error al eliminar el producto. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public Producto cambiarEstado(Integer id, String nuevoEstado) {
        try {
            Producto existente = findById(id);
            existente.setEstado(nuevoEstado);
            return productoRepository.save(existente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new UpdateEntityException("Producto", "Error al cambiar estado. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public Producto aumentarStock(Integer id, Integer cantidad, BigDecimal precioCompra) {
        try {
            Producto existente = findById(id);

            existente.setStock(existente.getStock() + cantidad);
            BigDecimal precioVenta = precioCompra.multiply(BigDecimal.valueOf(1.25));
            existente.setPrecioVenta(precioVenta);
            existente.setEstado("Activo");
            return productoRepository.save(existente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new UpdateEntityException("Producto", "Error al aumentar stock. Detalle: " + e.getMessage());
        }
    }

    @Transactional
    public Producto disminuirStock(Integer id, Integer cantidad) {
        try {
            Producto existente = findById(id);
            int nuevoStock = existente.getStock() - cantidad;
            if (nuevoStock < 0) {
                throw new IllegalArgumentException("No se puede disminuir más del stock actual");
            }
            existente.setStock(nuevoStock);
            return productoRepository.save(existente);
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new UpdateEntityException("Producto", "Error al disminuir stock. Detalle: " + e.getMessage());
        }
    }
}
