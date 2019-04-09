package com.android.compraencasa.sc.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43L;

    private static final String DEFAULT_MESSAGE = "El producto no se encuentra en el carrito de compras.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
