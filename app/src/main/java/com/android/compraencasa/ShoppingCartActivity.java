package com.android.compraencasa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.compraencasa.adapter.CartItemAdapter;
import com.android.compraencasa.constant.Constant;
import com.android.compraencasa.model.CartItem;
import com.android.compraencasa.model.Product;
import com.android.compraencasa.sc.model.Cart;
import com.android.compraencasa.sc.model.Saleable;
import com.android.compraencasa.sc.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ShoppingCartActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingCartActivity";

    ListView lstItemsCarro;
    Button btnLimpiar;
    Button btnComprar;
    TextView txtPrecioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lstItemsCarro = (ListView) findViewById(R.id.lstItemsCarro);
        LayoutInflater layoutInflater = getLayoutInflater();

        final Cart cart = CartHelper.getCart();
        final TextView txtPrecioTotal = (TextView) findViewById(R.id.txtPrecioTotal);
        txtPrecioTotal.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));

        lstItemsCarro.addHeaderView(layoutInflater.inflate(R.layout.cart_header, lstItemsCarro, false));

        final CartItemAdapter cartItemAdapter = new CartItemAdapter(this);
        cartItemAdapter.updateCartItems(getCartItems(cart));

        lstItemsCarro.setAdapter(cartItemAdapter);

        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnComprar = (Button) findViewById(R.id.btnComprar);

        btnLimpiar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();
                txtPrecioTotal.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });

        btnComprar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lstItemsCarro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ShoppingCartActivity.this)
                        .setTitle(getResources().getString(R.string.eliminar_item))
                        .setMessage(getResources().getString(R.string.eliminar_item_mensaje))
                        .setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<CartItem> cartItems = getCartItems(cart);
                                cart.remove(cartItems.get(position-1).getProduct());
                                cartItems.remove(position-1);
                                cartItemAdapter.updateCartItems(cartItems);
                                cartItemAdapter.notifyDataSetChanged();
                                txtPrecioTotal.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();
                return false;
            }
        });

        lstItemsCarro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                List<CartItem> cartItems = getCartItems(cart);
                Product product = cartItems.get(position-1).getProduct();
                Log.d(TAG, "Viendo producto: " + product.getName());
                bundle.putSerializable("product", product);
                Intent intent = new Intent(ShoppingCartActivity.this, ProductActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Carro de compras actual: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Lista de artículos del carrito: " + cartItems);
        return cartItems;
    }
}
