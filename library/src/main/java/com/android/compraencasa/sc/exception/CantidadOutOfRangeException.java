package com.android.compraencasa.sc.exception;

public class CantidadOutOfRangeException extends RuntimeException {
    private static final long serialVersionUID = 44L;

    private static final String DEFAULT_MESSAGE = "Cantidad fuera de rango.";

    public CantidadOutOfRangeException() {
        super(DEFAULT_MESSAGE);
    }

    public CantidadOutOfRangeException(String message) {
        super(message);
    }
}
