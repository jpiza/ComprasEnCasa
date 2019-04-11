package com.android.compraencasa.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.compraencasa.R;
import com.android.compraencasa.constant.Constant;
import com.android.compraencasa.model.Product;

public class ProductAdapter extends BaseAdapter {
    private static final String TAG = "ProductAdapter";

    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtNombre;
        TextView txtPrecio;
        ImageView imgImagen;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_product, parent, false);
            txtNombre = (TextView) convertView.findViewById(R.id.txtNombreProducto);
            txtPrecio = (TextView) convertView.findViewById(R.id.txtPrecioProducto);
            imgImagen = (ImageView) convertView.findViewById(R.id.imgImagenProducto);
            convertView.setTag(new ViewHolder(txtNombre, txtPrecio, imgImagen));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            txtNombre = viewHolder.txtNombreProducto;
            txtPrecio = viewHolder.txtPrecioProducto;
            imgImagen = viewHolder.imgImagenProducto;
        }

        final Product product = getItem(position);
        txtNombre.setText(product.getName());
        txtPrecio.setText(Constant.CURRENCY+String.valueOf(product.getPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
        Log.d(TAG, "Context package name: " + context.getPackageName());
        imgImagen.setImageResource(context.getResources().getIdentifier(
                product.getImageName(), "imagen", context.getPackageName()));
        return convertView;
    }

    private static class ViewHolder {
        public final TextView txtNombreProducto;
        public final TextView txtPrecioProducto;
        public final ImageView imgImagenProducto;

        public ViewHolder(TextView txtNombreProducto, TextView txtPrecioProducto, ImageView imgImagenProducto) {
            this.txtNombreProducto = txtNombreProducto;
            this.txtPrecioProducto = txtPrecioProducto;
            this.imgImagenProducto = imgImagenProducto;
        }
    }
}
