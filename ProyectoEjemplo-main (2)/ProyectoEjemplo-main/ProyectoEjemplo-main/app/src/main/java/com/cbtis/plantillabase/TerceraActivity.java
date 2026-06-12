package com.cbtis.plantillabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TerceraActivity extends AppCompatActivity {

    TextView txtIniciarSesion;
    EditText edtNombre, edtContrasena;
    CheckBox chbMatematicas, chbEspanol, chbHistoria;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        txtIniciarSesion = findViewById(R.id.txtIniciarSesion);
        edtNombre = findViewById(R.id.edtNombre);
        edtContrasena = findViewById(R.id.edtContrasena);
        chbMatematicas = findViewById(R.id.chbMatematicas);
        chbEspanol = findViewById(R.id.chbEspanol);
        chbHistoria = findViewById(R.id.chbHistoria);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString().trim();
            String contrasena = edtContrasena.getText().toString();

            boolean tieneMateria = chbMatematicas.isChecked() || chbEspanol.isChecked() || chbHistoria.isChecked();

            if (nombre.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor llena los campos de texto", Toast.LENGTH_SHORT).show();
            } else if (!tieneMateria) {
                Toast.makeText(this, "Selecciona al menos una materia", Toast.LENGTH_SHORT).show();
            } else {
                // GUARDAR MAESTRO PERMANENTEMENTE
                SharedPreferences pref = getSharedPreferences("EduCoreData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("maestro_pass_" + nombre, contrasena);

                // Guardamos también las materias que imparte por si las necesitas después
                editor.putBoolean("maestro_mat_" + nombre, chbMatematicas.isChecked());
                editor.putBoolean("maestro_esp_" + nombre, chbEspanol.isChecked());
                editor.putBoolean("maestro_his_" + nombre, chbHistoria.isChecked());

                editor.apply();

                Toast.makeText(this, "Registro de Maestro Exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TerceraActivity.this, SegundaActivity.class);
                startActivity(intent);
            }
        });

        txtIniciarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(TerceraActivity.this, SegundaActivity.class);
            startActivity(intent);
        });
    }
}