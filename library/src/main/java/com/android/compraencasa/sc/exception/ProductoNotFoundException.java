package com.android.compraencasa.sc.exception;

public class ProductoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43L;

    private static final String DEFAULT_MESSAGE = "El producto no se encuentra en el carro de compras.";

    public ProductoNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
