package com.android.compraencasa.sc.exception;

public class QuantityOutOfRangeException extends RuntimeException {
    private static final long serialVersionUID = 44L;

    private static final String DEFAULT_MESSAGE = "Cantidad fuera de rango";

    public QuantityOutOfRangeException() {
        super(DEFAULT_MESSAGE);
    }

    public QuantityOutOfRangeException(String message) {
        super(message);
    }
}
