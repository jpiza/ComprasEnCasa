package com.android.compraencasa.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.compraencasa.MainActivity;
import com.android.compraencasa.ProductoActivity;
import com.android.compraencasa.R;
import com.android.compraencasa.model.Producto;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<Producto> datos;
    private AppCompatActivity activity;

    public CoverFlowAdapter(AppCompatActivity context, ArrayList<Producto> objects) {
        this.activity = context;
        this.datos = objects;
    }

    @Override
    public int getCount() { return datos.size(); }

    @Override
    public Producto getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_view, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imagenProducto.setImageResource(datos.get(position).getId());
        viewHolder.nombreProducto.setText(datos.get(position).getNombre());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_product_info);
                dialog.setCancelable(true);
                dialog.setTitle("Detalle Producto");

                TextView text = (TextView) dialog.findViewById(R.id.nombre);
                text.setText(getItem(position).getNombre());
                ImageView image = (ImageView) dialog.findViewById(R.id.imagen);
                image.setImageResource(getItem(position).getId());
                Button btnVer = (Button)dialog.findViewById(R.id.btnVer);
                btnVer.setOnClickListener(this);

                dialog.show();
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnVer){
            //Intent i = new Intent(this, ProductoActivity.class);
            //startActivity(i);
        }
    }

    private static class ViewHolder {
        private TextView nombreProducto;
        private ImageView imagenProducto;

        public ViewHolder(View v) {
            imagenProducto = (ImageView)v.findViewById(R.id.imagen);
            nombreProducto = (TextView)v.findViewById(R.id.nombre);
        }
    }
}