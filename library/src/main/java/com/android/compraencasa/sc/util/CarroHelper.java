package com.android.compraencasa.sc.util;

import com.android.compraencasa.sc.model.Carro;

public class CarroHelper {
    private static Carro carro = new Carro();

    public static Carro getCarro() {
        if (carro == null) {
            carro = new Carro();
        }
        return carro;
    }
}
