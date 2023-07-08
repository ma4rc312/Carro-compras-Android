package com.marce.carrito_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.marce.carrito_java.CarritoActivity;
import com.marce.carrito_java.R;
import com.marce.carrito_java.models.Producto;

import java.io.Serializable;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    Context context;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Producto> listaProductos;
    List<Producto> carroCompra;

    public AdaptadorProductos(Context context, TextView tvCantProductos, Button btnVerCarro, List<Producto> listaProductos, List<Producto> carroCompra) {
        this.context = context;
        this.tvCantProductos = tvCantProductos;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carroCompra = carroCompra;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_productos, null, false);
        return new ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder( final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getNomProducto());
        productosViewHolder.tvDescripcion.setText(listaProductos.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText(""+listaProductos.get(i).getPrecio());

        productosViewHolder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(productosViewHolder.cbCarro.isChecked() == true) {
                    tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(listaProductos.get(i));
                } else if(productosViewHolder.cbCarro.isChecked() == false) {
                    tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
                    carroCompra.remove(listaProductos.get(i));
                }
            }
        });

        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarritoActivity.class);
                intent.putExtra("CarroCompras", (Serializable) carroCompra);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        CheckBox cbCarro;

        public ProductosViewHolder(View itemView) {
            super(itemView);

            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            cbCarro = itemView.findViewById(R.id.cbCarro);
        }
    }
}
