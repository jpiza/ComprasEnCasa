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

import com.android.compraencasa.adapter.ItemCarroAdapter;
import com.android.compraencasa.constant.Constante;
import com.android.compraencasa.model.ItemCarro;
import com.android.compraencasa.model.Producto;
import com.android.compraencasa.sc.model.Carro;
import com.android.compraencasa.sc.model.Venta;
import com.android.compraencasa.sc.util.CarroHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CarroComprasActivity extends AppCompatActivity {
    private static final String TAG = "CarroComprasActivity";

    ListView lstItemsCarro;
    Button btnLimpiar;
    Button btnComprar;
    TextView txtPrecioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        lstItemsCarro = (ListView) findViewById(R.id.lstItemsCarro);
        LayoutInflater layoutInflater = getLayoutInflater();

        final Carro carro = CarroHelper.getCarro();
        final TextView txtPrecioTotal = (TextView) findViewById(R.id.txtPrecioTotal);
        txtPrecioTotal.setText(Constante.MONEDA+String.valueOf(carro.getPrecioTotal().setScale(0, BigDecimal.ROUND_HALF_UP)));

        lstItemsCarro.addHeaderView(layoutInflater.inflate(R.layout.carro_header, lstItemsCarro, false));

        final ItemCarroAdapter cartItemAdapter = new ItemCarroAdapter(this);
        cartItemAdapter.actualizarItemsCarro(getitemsCarro(carro));

        lstItemsCarro.setAdapter(cartItemAdapter);

        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnComprar = (Button) findViewById(R.id.btnComprar);

        btnLimpiar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Limpiando el carro de compras");
                carro.limpiar();
                cartItemAdapter.actualizarItemsCarro(getitemsCarro(carro));
                cartItemAdapter.notifyDataSetChanged();
                txtPrecioTotal.setText(Constante.MONEDA+String.valueOf(carro.getPrecioTotal().setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });

        btnComprar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarroComprasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lstItemsCarro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(CarroComprasActivity.this)
                        .setTitle(getResources().getString(R.string.eliminar_item))
                        .setMessage(getResources().getString(R.string.eliminar_item_mensaje))
                        .setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<ItemCarro> itemsCarro = getitemsCarro(carro);
                                carro.eliminar(itemsCarro.get(position-1).getProducto());
                                itemsCarro.remove(position-1);
                                cartItemAdapter.actualizarItemsCarro(itemsCarro);
                                cartItemAdapter.notifyDataSetChanged();
                                txtPrecioTotal.setText(Constante.MONEDA+String.valueOf(carro.getPrecioTotal().setScale(0, BigDecimal.ROUND_HALF_UP)));
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
                List<ItemCarro> itemsCarro = getitemsCarro(carro);
                Producto producto = itemsCarro.get(position-1).getProducto();
                Log.d(TAG, "Viendo producto: " + producto.getNombre());
                bundle.putSerializable("producto", producto);
                Intent intent = new Intent(CarroComprasActivity.this, ProductoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<ItemCarro> getitemsCarro(Carro carro) {
        List<ItemCarro> itemsCarro = new ArrayList<ItemCarro>();
        Log.d(TAG, "Carro de compras actual: " + carro);

        Map<Venta, Integer> itemMap = carro.getItemConCantidad();

        for (Entry<Venta, Integer> entry : itemMap.entrySet()) {
            ItemCarro itemCarro = new ItemCarro();
            itemCarro.setProducto((Producto) entry.getKey());
            itemCarro.setCantidad(entry.getValue());
            itemsCarro.add(itemCarro);
        }

        Log.d(TAG, "Lista de artículos del carrito: " + itemsCarro);
        return itemsCarro;
    }
}
