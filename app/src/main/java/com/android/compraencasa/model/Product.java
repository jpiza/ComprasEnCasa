package com.android.compraencasa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.android.compraencasa.sc.model.Saleable;

public class Product implements Saleable, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    private int pId;
    private String pNombre;
    private BigDecimal pPrecio;
    private String pDescripcion;
    private String pImagenNombre;

    public Product() {
        super();
    }

    public Product(int pId, String pNombre, BigDecimal pPrecio, String pDescripcion, String pImagenNombre) {
        setId(pId);
        setName(pNombre);
        setPrice(pPrecio);
        setDescription(pDescripcion);
        setImageName(pImagenNombre);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;

        return (this.pId == ((Product) o).getId());
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
    public BigDecimal getPrice() {
        return pPrecio;
    }

    @Override
    public String getName() {
        return pNombre;
    }

    public void setPrice(BigDecimal price) {
        this.pPrecio = price;
    }

    public void setName(String name) {
        this.pNombre = name;
    }

    public String getDescription() {
        return pDescripcion;
    }

    public void setDescription(String pDescription) {
        this.pDescripcion = pDescription;
    }

    public String getImageName() {
        return pImagenNombre;
    }

    public void setImageName(String imageName) {
        this.pImagenNombre = imageName;
    }
}
