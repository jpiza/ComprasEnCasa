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
import com.android.compraencasa.constant.Constante;
import com.android.compraencasa.model.Producto;
import com.android.compraencasa.sc.model.Carro;
import com.android.compraencasa.sc.util.CarroHelper;

public class ProductoActivity extends AppCompatActivity {
    private static final String TAG = "ProductoActivity";

    TextView txtNombreProducto;
    TextView txtDescProducto;
    ImageView imgImagenProducto;
    Spinner spCantidad;
    Button btnAgregar;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_producto);

        Bundle data = getIntent().getExtras();
        producto = (Producto) data.getSerializable("producto");

        Log.d(TAG, "Producto hashCode: " + producto.hashCode());

        setLinkCarroCompras();

        iniciarVistas();

        setPropiedadesProductos();

        iniciarCantidades();

        onOrdenProducto();
    }

    private void setLinkCarroCompras() {
        TextView tvViewShoppingCart = (TextView)findViewById(R.id.txtVerCarroCompra);
        SpannableString content = new SpannableString(getText(R.string.carro_compra));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvViewShoppingCart.setText(content);
        tvViewShoppingCart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductoActivity.this, CarroComprasActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarVistas() {
        txtNombreProducto = (TextView) findViewById(R.id.txtNombreProducto);
        txtDescProducto = (TextView) findViewById(R.id.txtDescProducto);
        imgImagenProducto = (ImageView) findViewById(R.id.imgImagenProducto);
        spCantidad = (Spinner) findViewById(R.id.spCantidad);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
    }

    private void setPropiedadesProductos() {
        txtNombreProducto.setText(producto.getNombre());
        txtDescProducto.setText(producto.getDescripcion());
        imgImagenProducto.setImageResource(this.getResources().getIdentifier(producto.getNombreImagen(), "drawable", this.getPackageName()));
    }

    private void iniciarCantidades() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Constante.LISTA_CANTIDAD);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidad.setAdapter(dataAdapter);
    }

    private void onOrdenProducto() {
        btnAgregar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Carro carro = CarroHelper.getCarro();
                Log.d(TAG, "Agregando Producto: " + producto.getNombre());
                carro.agregar(producto, Integer.valueOf(spCantidad.getSelectedItem().toString()));
                Intent intent = new Intent(ProductoActivity.this, CarroComprasActivity.class);
                startActivity(intent);
            }
        });
    }
}
