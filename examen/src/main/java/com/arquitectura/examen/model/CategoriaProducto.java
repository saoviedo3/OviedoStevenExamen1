package com.arquitectura.examen.model;

import jakarta.persistence.Version;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categorias_Producto")
public class CategoriaProducto {

    @Id
    @Column(name = "id_categoria", nullable = false)
    private Integer id;

    @Column(name = "nombre_categoria", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Version
    private Long version;

    public CategoriaProducto() {
    }

    public CategoriaProducto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategoriaProducto other = (CategoriaProducto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoriaProducto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", version="
                + version + "]";
    }

}
