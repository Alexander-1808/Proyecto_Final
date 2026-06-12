package com.cbtis.plantillabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMaestro, btnAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMaestro = findViewById(R.id.btnMaestro);
        btnAlumno = findViewById(R.id.btnAlumno);

        btnMaestro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
            startActivity(intent);
        });

        btnAlumno.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CuartaActivity.class);
            startActivity(intent);
        });
    }
}