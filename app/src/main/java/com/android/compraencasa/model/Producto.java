package com.android.compraencasa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.android.compraencasa.sc.model.Venta;

public class Producto implements Venta, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    private int pId;
    private String pNombre;
    private BigDecimal pPrecio;
    private String pDescripcion;
    private String pImagen;

    public Producto() {
        super();
    }

    public Producto(int pId, String pNombre, BigDecimal pPrecio, String pDescripcion, String pImagen) {
        setId(pId);
        setNombre(pNombre);
        setPrecio(pPrecio);
        setDescripcion(pDescripcion);
        setImagen(pImagen);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Producto)) return false;

        return (this.pId == ((Producto) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (pNombre == null ? 0 : pNombre.hashCode());
        hash = hash * prime + (pPrecio == null ? 0 : pPrecio.hashCode());
        hash = hash * prime + (pDescripcion == null ? 0 : pDescripcion.hashCode());

        return hash;
    }


    public int getId() {
        return pId;
    }

    public void setId(int id) {
        this.pId = id;
    }

    @Override
    public BigDecimal getPrecio() {
        return pPrecio;
    }

    @Override
    public String getNombre() {
        return pNombre;
    }

    public void setPrecio(BigDecimal precio) {
        this.pPrecio = precio;
    }

    public void setNombre(String nombre) {
        this.pNombre = nombre;
    }

    public String getDescripcion() {
        return pDescripcion;
    }

    public void setDescripcion(String pDescripcion) {
        this.pDescripcion = pDescripcion;
    }

    public String getImagen() {
        return pImagen;
    }

    public void setImagen(String imagenNombre) {
        this.pImagen = imagenNombre;
    }
}
