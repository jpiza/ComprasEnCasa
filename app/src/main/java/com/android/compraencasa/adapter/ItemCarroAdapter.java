package com.android.compraencasa.adapter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.compraencasa.R;
import com.android.compraencasa.constant.Constante;
import com.android.compraencasa.model.ItemCarro;
import com.android.compraencasa.sc.model.Carro;
import com.android.compraencasa.sc.util.CarroHelper;

public class ItemCarroAdapter extends BaseAdapter {
    private static final String TAG = "ItemCarroAdapter";

    private List<ItemCarro> itemsCarro = Collections.emptyList();

    private final Context context;

    public ItemCarroAdapter(Context context) {
        this.context = context;
    }

    public void actualizarItemsCarro(List<ItemCarro> itemsCarro) {
        this.itemsCarro = itemsCarro;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemsCarro.size();
    }

    @Override
    public ItemCarro getItem(int position) {
        return itemsCarro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView txtNombre;
        TextView txtPrecioUnitario;
        TextView txtCantidad;
        TextView txtPrecio;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_item_carro, parent, false);
            txtNombre = (TextView) convertView.findViewById(R.id.txtNombreItem);
            txtPrecioUnitario = (TextView) convertView.findViewById(R.id.txtPrecioUnitarioItem);
            txtCantidad = (TextView) convertView.findViewById(R.id.txtCantidadItem);
            txtPrecio = (TextView) convertView.findViewById(R.id.txtPrecioItem);
            convertView.setTag(new ViewHolder(txtNombre, txtPrecioUnitario, txtCantidad, txtPrecio));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            txtNombre = viewHolder.txtNombreItem;
            txtPrecioUnitario = viewHolder.txtPrecioUnitarioItem;
            txtCantidad = viewHolder.txtCantidadItem;
            txtPrecio = viewHolder.txtPrecioItem;
        }

        final Carro carro = CarroHelper.getCarro();
        final ItemCarro itemCarro = getItem(position);
        txtNombre.setText(itemCarro.getProducto().getNombre());
        txtPrecioUnitario.setText(Constante.MONEDA+String.valueOf(itemCarro.getProducto().getPrecio().setScale(0, BigDecimal.ROUND_HALF_UP)));
        txtCantidad.setText(String.valueOf(itemCarro.getCantidad()));
        txtPrecio.setText(Constante.MONEDA+String.valueOf(carro.getCosto(itemCarro.getProducto()).setScale(0, BigDecimal.ROUND_HALF_UP)));
        return convertView;
    }

    private static class ViewHolder {
        public final TextView txtNombreItem;
        public final TextView txtPrecioUnitarioItem;
        public final TextView txtCantidadItem;
        public final TextView txtPrecioItem;

        public ViewHolder(TextView txtNombreItem, TextView txtPrecioUnitarioItem, TextView txtCantidadItem, TextView txtPrecioItem) {
            this.txtNombreItem = txtNombreItem;
            this.txtPrecioUnitarioItem = txtPrecioUnitarioItem;
            this.txtCantidadItem = txtCantidadItem;
            this.txtPrecioItem = txtPrecioItem;
        }
    }
}
