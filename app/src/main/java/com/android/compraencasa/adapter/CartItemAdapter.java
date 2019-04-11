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
import com.android.compraencasa.constant.Constant;
import com.android.compraencasa.model.CartItem;
import com.android.compraencasa.sc.model.Cart;
import com.android.compraencasa.sc.util.CartHelper;

public class CartItemAdapter extends BaseAdapter {
    private static final String TAG = "CartItemAdapter";

    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
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
                    .inflate(R.layout.adapter_cart_item, parent, false);
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

        final Cart cart = CartHelper.getCart();
        final CartItem cartItem = getItem(position);
        txtNombre.setText(cartItem.getProduct().getName());
        txtPrecioUnitario.setText(Constant.CURRENCY+String.valueOf(cartItem.getProduct().getPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
        txtCantidad.setText(String.valueOf(cartItem.getQuantity()));
        txtPrecio.setText(Constant.CURRENCY+String.valueOf(cart.getCost(cartItem.getProduct()).setScale(0, BigDecimal.ROUND_HALF_UP)));
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
