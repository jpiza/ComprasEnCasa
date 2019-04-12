package com.android.compraencasa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android.compraencasa.adapter.ProductoAdapter;
import com.android.compraencasa.constant.Constante;
import com.android.compraencasa.model.Producto;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvViewShoppingCart = (TextView)findViewById(R.id.txtVerCarroCompra);
        SpannableString content = new SpannableString(getText(R.string.carro_compra));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvViewShoppingCart.setText(content);
        tvViewShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarroActivity.class);
                startActivity(intent);
            }
        });

        ListView lstProductos = (ListView) findViewById(R.id.lstProductos);
        lstProductos.addHeaderView(getLayoutInflater().inflate(R.layout.producto_lista_header, lstProductos, false));

        ProductoAdapter productAdapter = new ProductoAdapter(this);
        productAdapter.actualizarProducto(Constante.LISTA_PRODUCTO);

        lstProductos.setAdapter(productAdapter);

        lstProductos.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Producto producto = Constante.LISTA_PRODUCTO.get(position - 1);
                Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                Log.d(TAG, "Viendo Producto: " + producto.getNombre());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
