package com.android.compraencasa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.compraencasa.adapter.CoverFlowAdapter;
import com.android.compraencasa.model.Galeria;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class GaleriaActivity  extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Galeria> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter(this, productos);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        productos = new ArrayList<>();
        productos.add(new Galeria(R.drawable.mango, "Mango"));
        productos.add(new Galeria(R.drawable.acelga, "Acelga"));
        productos.add(new Galeria(R.drawable.manzanas, "Manzana"));
        productos.add(new Galeria(R.drawable.pepino, "Pepino"));
        productos.add(new Galeria(R.drawable.melon, "Melon"));
    }
}
