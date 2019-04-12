package com.android.compraencasa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.compraencasa.adapter.CoverFlowAdapter;
import com.android.compraencasa.model.Producto;

import java.math.BigDecimal;
import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class GaleriaActivity  extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        coverFlow = (FeatureCoverFlow)findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter(this, productos);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("GaleriaActivity", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("GaleriaActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        productos = new ArrayList<>();
        productos.add(new Producto(R.drawable.mango, "Mango",  BigDecimal.valueOf(2000), "Mango.", "mango"));
        productos.add(new Producto(R.drawable.acelga, "Acelga", BigDecimal.valueOf(800), "Acelga.", "acelga"));
        productos.add(new Producto(R.drawable.manzanas, "Manzana", BigDecimal.valueOf(3000), "Manzana.", "manzanas"));
        productos.add(new Producto(R.drawable.pepino, "Pepino", BigDecimal.valueOf(1500), "Pepino.", "pepino"));
        productos.add(new Producto(R.drawable.melon, "Melon", BigDecimal.valueOf(1400), "Melon.", "melon"));
        productos.add(new Producto(R.drawable.zanahoria, "Zanahoria", BigDecimal.valueOf(1200), "Zanahoria.", "zanahoria"));
    }
}
