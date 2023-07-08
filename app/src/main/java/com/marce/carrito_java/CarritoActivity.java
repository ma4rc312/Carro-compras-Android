package com.marce.carrito_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.marce.carrito_java.adapter.AdaptadorCarroCompras;
import com.marce.carrito_java.models.Producto;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {
    List<Producto> carroCompras;

    AdaptadorCarroCompras adaptador;

    RecyclerView rvListaCarro;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        getSupportActionBar().hide();

        carroCompras = (List<Producto>) getIntent().getSerializableExtra("CarroCompras");

        rvListaCarro = findViewById(R.id.rvListaCarro);
        rvListaCarro.setLayoutManager(new GridLayoutManager(CarritoActivity.this, 1));
        tvTotal = findViewById(R.id.tvTotal);

        adaptador = new AdaptadorCarroCompras(CarritoActivity.this, carroCompras, tvTotal);
        rvListaCarro.setAdapter(adaptador);
    }
}