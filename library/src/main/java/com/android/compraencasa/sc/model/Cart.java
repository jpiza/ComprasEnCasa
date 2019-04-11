package com.android.compraencasa.sc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.android.compraencasa.sc.exception.ProductNotFoundException;
import com.android.compraencasa.sc.exception.QuantyOutOfRangeException;

public class Cart implements Serializable {
    private static final long serialVersionUID = 42L;

    private Map<Saleable, Integer> cartItemMap = new HashMap<Saleable, Integer>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int totalQuantity = 0;

    public void add(final Saleable sellable, int quantity) {
        if (cartItemMap.containsKey(sellable)) {
            cartItemMap.put(sellable, cartItemMap.get(sellable) + quantity);
        } else {
            cartItemMap.put(sellable, quantity);
        }

        totalPrice = totalPrice.add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity += quantity;
    }

    public void update(final Saleable sellable, int quantity) throws ProductNotFoundException, QuantyOutOfRangeException {
        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();
        if (quantity < 0)
            throw new QuantyOutOfRangeException(quantity + " no es una cantidad válida. Debe ser positiva.");

        int productQuantity = cartItemMap.get(sellable);
        BigDecimal productPrice = sellable.getPrice().multiply(BigDecimal.valueOf(productQuantity));

        cartItemMap.put(sellable, quantity);

        totalQuantity = totalQuantity - productQuantity + quantity;
        totalPrice = totalPrice.subtract(productPrice).add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    public void remove(final Saleable sellable, int quantity) throws ProductNotFoundException, QuantyOutOfRangeException {
        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();

        int productQuantity = cartItemMap.get(sellable);

        if (quantity < 0 || quantity > productQuantity)
            throw new QuantyOutOfRangeException(quantity + " no es una cantidad válida. Debe ser positiva y menor a la cantidad actual del producto en el carrito de compras.");

        if (productQuantity == quantity) {
            cartItemMap.remove(sellable);
        } else {
            cartItemMap.put(sellable, productQuantity - quantity);
        }

        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void remove(final Saleable sellable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();

        int quantity = cartItemMap.get(sellable);
        cartItemMap.remove(sellable);
        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void clear() {
        cartItemMap.clear();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    public int getQuantity(final Saleable sellable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();
        return cartItemMap.get(sellable);
    }

    public BigDecimal getCost(final Saleable sellable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(sellable)) throw new ProductNotFoundException();
        return sellable.getPrice().multiply(BigDecimal.valueOf(cartItemMap.get(sellable)));
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public Set<Saleable> getProducts() {
        return cartItemMap.keySet();
    }

    public Map<Saleable, Integer> getItemWithQuantity() {
        Map<Saleable, Integer> cartItemMap = new HashMap<Saleable, Integer>();
        cartItemMap.putAll(this.cartItemMap);
        return cartItemMap;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for (Entry<Saleable, Integer> entry : cartItemMap.entrySet()) {
            strBuilder.append(String.format("Producto: %s, Precio Unitario: %f, Cantidad: %d%n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue()));
        }
        strBuilder.append(String.format("Cantidad Total: %d, Total Price: %f", totalQuantity, totalPrice));

        return strBuilder.toString();
    }
}
