package com.android.compraencasa.sc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.android.compraencasa.sc.exception.ProductoNotFoundException;
import com.android.compraencasa.sc.exception.CantidadOutOfRangeException;

public class Carro implements Serializable {
    private static final long serialVersionUID = 42L;

    private Map<Venta, Integer> itemsCarroSel = new HashMap<Venta, Integer>();
    private BigDecimal precioTotal = BigDecimal.ZERO;
    private int cantidadTotal = 0;

    public void agregar(final Venta venta, int cantidad) {
        if (itemsCarroSel.containsKey(venta)) {
            itemsCarroSel.put(venta, itemsCarroSel.get(venta) + cantidad);
        } else {
            itemsCarroSel.put(venta, cantidad);
        }

        precioTotal = precioTotal.add(venta.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        cantidadTotal += cantidad;
    }

    public void actualizar(final Venta venta, int cantidad) throws ProductoNotFoundException, CantidadOutOfRangeException {
        if (!itemsCarroSel.containsKey(venta)) throw new ProductoNotFoundException();
        if (cantidad < 0)
            throw new CantidadOutOfRangeException(cantidad + " no es una cantidad válida. Debe ser positiva.");

        int productcantidad = itemsCarroSel.get(venta);
        BigDecimal productPrice = venta.getPrecio().multiply(BigDecimal.valueOf(productcantidad));

        itemsCarroSel.put(venta, cantidad);

        cantidadTotal = cantidadTotal - productcantidad + cantidad;
        precioTotal = precioTotal.subtract(productPrice).add(venta.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
    }

    public void eliminar(final Venta venta, int cantidad) throws ProductoNotFoundException, CantidadOutOfRangeException {
        if (!itemsCarroSel.containsKey(venta)) throw new ProductoNotFoundException();

        int productcantidad = itemsCarroSel.get(venta);

        if (cantidad < 0 || cantidad > productcantidad)
            throw new CantidadOutOfRangeException(cantidad + " no es una cantidad válida. Debe ser mayor a cero y menor a la cantidad actual del producto en el carrito de compras.");

        if (productcantidad == cantidad) {
            itemsCarroSel.remove(venta);
        } else {
            itemsCarroSel.put(venta, productcantidad - cantidad);
        }

        precioTotal = precioTotal.subtract(venta.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        cantidadTotal -= cantidad;
    }

    public void eliminar(final Venta venta) throws ProductoNotFoundException {
        if (!itemsCarroSel.containsKey(venta)) throw new ProductoNotFoundException();

        int cantidad = itemsCarroSel.get(venta);
        itemsCarroSel.remove(venta);
        precioTotal = precioTotal.subtract(venta.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        cantidadTotal -= cantidad;
    }

    public void limpiar() {
        itemsCarroSel.clear();
        precioTotal = BigDecimal.ZERO;
        cantidadTotal = 0;
    }

    public int getCantidad(final Venta venta) throws ProductoNotFoundException {
        if (!itemsCarroSel.containsKey(venta)) throw new ProductoNotFoundException();
        return itemsCarroSel.get(venta);
    }

    public BigDecimal getCosto(final Venta venta) throws ProductoNotFoundException {
        if (!itemsCarroSel.containsKey(venta)) throw new ProductoNotFoundException();
        return venta.getPrecio().multiply(BigDecimal.valueOf(itemsCarroSel.get(venta)));
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public Set<Venta> getProductos() {
        return itemsCarroSel.keySet();
    }

    public Map<Venta, Integer> getItemConCantidad() {
        Map<Venta, Integer> itemCarroSel = new HashMap<Venta, Integer>();
        itemCarroSel.putAll(this.itemsCarroSel);
        return itemCarroSel;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for (Entry<Venta, Integer> entry : itemsCarroSel.entrySet()) {
            strBuilder.append(String.format("Producto: %s, Precio Unitario: %f, Cantidad: %d%n", entry.getKey().getNombre(), entry.getKey().getPrecio(), entry.getValue()));
        }
        strBuilder.append(String.format("Cantidad Total: %d, Total Price: %f", cantidadTotal, precioTotal));

        return strBuilder.toString();
    }
}
