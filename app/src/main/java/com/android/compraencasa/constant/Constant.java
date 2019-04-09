package com.android.compraencasa.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.android.compraencasa.model.Product;

public final class Constant {
    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();

    static {
        for (int i = 1; i < 11; i++) QUANTITY_LIST.add(i);
    }

    public static final Product PRODUCT1 = new Product(1, "Mango", BigDecimal.valueOf(2.000), "Mango.", "mango");
    public static final Product PRODUCT2 = new Product(2, "Acelga", BigDecimal.valueOf(800), "Acelga.", "acelga");
    public static final Product PRODUCT3 = new Product(3, "Manzana", BigDecimal.valueOf(3.000), "Manzana.", "manzanas");
    public static final Product PRODUCT4 = new Product(4, "Pepino", BigDecimal.valueOf(1.500), "Pepino.", "pepino");
    public static final Product PRODUCT5 = new Product(5, "Melon", BigDecimal.valueOf(1.400), "Melon.", "melon");
    public static final Product PRODUCT6 = new Product(6, "Zanahoria", BigDecimal.valueOf(1.200), "Zanahoria.", "zanahoria");

    public static final List<Product> PRODUCT_LIST = new ArrayList<Product>();

    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
        PRODUCT_LIST.add(PRODUCT4);
        PRODUCT_LIST.add(PRODUCT5);
        PRODUCT_LIST.add(PRODUCT6);
    }

    public static final String CURRENCY = "$";
}