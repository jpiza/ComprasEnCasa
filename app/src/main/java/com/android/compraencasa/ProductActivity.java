package com.android.compraencasa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import com.android.compraencasa.constant.Constant;
import com.android.compraencasa.model.Product;
import com.android.compraencasa.sc.model.Cart;
import com.android.compraencasa.sc.util.CartHelper;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";

    TextView txtNombreProducto;
    TextView txtDescProducto;
    ImageView imgImagenProducto;
    Spinner spCantidad;
    Button btnAgregar;
    Product producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product);

        Bundle data = getIntent().getExtras();
        producto = (Product) data.getSerializable("product");

        Log.d(TAG, "Producto hashCode: " + producto.hashCode());

        //Set Shopping Cart link
        setShoppingCartLink();

        //Retrieve views
        retrieveViews();

        //Set product properties
        setProductProperties();

        //Initialize quantity
        initializeQuantity();

        //On ordering of product
        onOrderProduct();
    }

    private void setShoppingCartLink() {
        TextView tvViewShoppingCart = (TextView)findViewById(R.id.txtVerCarroCompra);
        SpannableString content = new SpannableString(getText(R.string.carro_compra));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvViewShoppingCart.setText(content);
        tvViewShoppingCart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveViews() {
        txtNombreProducto = (TextView) findViewById(R.id.txtNombreProducto);
        txtDescProducto = (TextView) findViewById(R.id.txtDescProducto);
        imgImagenProducto = (ImageView) findViewById(R.id.imgImagenProducto);
        spCantidad = (Spinner) findViewById(R.id.spCantidad);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
    }

    private void setProductProperties() {
        txtNombreProducto.setText(producto.getName());
        txtDescProducto.setText(producto.getDescription());
        imgImagenProducto.setImageResource(this.getResources().getIdentifier(producto.getImageName(), "drawable", this.getPackageName()));
    }

    private void initializeQuantity() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Constant.QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidad.setAdapter(dataAdapter);
    }

    private void onOrderProduct() {
        btnAgregar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = CartHelper.getCart();
                Log.d(TAG, "Agregando Producto: " + producto.getName());
                cart.add(producto, Integer.valueOf(spCantidad.getSelectedItem().toString()));
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }
}
