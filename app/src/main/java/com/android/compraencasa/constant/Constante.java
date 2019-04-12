package com.android.compraencasa.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.android.compraencasa.R;
import com.android.compraencasa.model.Producto;

public final class Constante {
    public static final List<Integer> LISTA_CANTIDAD = new ArrayList<Integer>();

    static {
        for (int i = 1; i < 11; i++) LISTA_CANTIDAD.add(i);
    }

    public static final Producto PRODUCTO1 = new Producto(R.drawable.mango, "Mango", BigDecimal.valueOf(2000), "Mango.", "mango");
    public static final Producto PRODUCTO2 = new Producto(R.drawable.acelga, "Acelga", BigDecimal.valueOf(800), "Acelga.", "acelga");
    public static final Producto PRODUCTO3 = new Producto(R.drawable.manzanas, "Manzana", BigDecimal.valueOf(3000), "Manzana.", "manzanas");
    public static final Producto PRODUCTO4 = new Producto(R.drawable.pepino, "Pepino", BigDecimal.valueOf(1500), "Pepino.", "pepino");
    public static final Producto PRODUCTO5 = new Producto(R.drawable.melon, "Melon", BigDecimal.valueOf(1400), "Melon.", "melon");
    public static final Producto PRODUCTO6 = new Producto(R.drawable.zanahoria, "Zanahoria", BigDecimal.valueOf(1200), "Zanahoria.", "zanahoria");

    public static final List<Producto> LISTA_PRODUCTO = new ArrayList<Producto>();

    static {
        LISTA_PRODUCTO.add(PRODUCTO1);
        LISTA_PRODUCTO.add(PRODUCTO2);
        LISTA_PRODUCTO.add(PRODUCTO3);
        LISTA_PRODUCTO.add(PRODUCTO4);
        LISTA_PRODUCTO.add(PRODUCTO5);
        LISTA_PRODUCTO.add(PRODUCTO6);
    }

    public static final String MONEDA = "$";
}
