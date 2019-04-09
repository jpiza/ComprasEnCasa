package com.android.compraencasa.sc.util;

import com.android.compraencasa.sc.model.Cart;

public class CartHelper {
    private static Cart cart = new Cart();

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }
}
