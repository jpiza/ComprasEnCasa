package com.android.compraencasa.sc.exception;

public class QuantyOutOfRangeException extends RuntimeException {
    private static final long serialVersionUID = 44L;

    private static final String DEFAULT_MESSAGE = "Cantidad fuera de rango";

    public QuantyOutOfRangeException() {
        super(DEFAULT_MESSAGE);
    }

    public QuantyOutOfRangeException(String message) {
        super(message);
    }
}
