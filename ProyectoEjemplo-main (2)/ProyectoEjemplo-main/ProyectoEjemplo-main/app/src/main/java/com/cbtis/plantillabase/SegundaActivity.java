package com.cbtis.plantillabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    TextView txtCrearCuenta;
    EditText edtNombreLogin, edtContrasenaLogin;
    Button btnIngresarMaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        txtCrearCuenta = findViewById(R.id.txtCrearCuenta);
        edtNombreLogin = findViewById(R.id.edtNombreLogin);
        edtContrasenaLogin = findViewById(R.id.edtContrasenaLogin);
        btnIngresarMaestro = findViewById(R.id.btnIngresarMaestro);

        btnIngresarMaestro.setOnClickListener(v -> {
            String usuarioIngresado = edtNombreLogin.getText().toString().trim();
            String contrasenaIngresada = edtContrasenaLogin.getText().toString();

            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences pref = getSharedPreferences("EduCoreData", MODE_PRIVATE);

            // Buscar la contraseña del maestro en las preferencias
            String contrasenaCorrecta = pref.getString("maestro_pass_" + usuarioIngresado, null);

            // Cuenta de respaldo por defecto para pruebas
            if (usuarioIngresado.equalsIgnoreCase("Gael") && contrasenaCorrecta == null) {
                contrasenaCorrecta = "123";
            }

            if (contrasenaCorrecta != null && contrasenaIngresada.equals(contrasenaCorrecta)) {
                Toast.makeText(this, "Acceso Maestro Autorizado", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SegundaActivity.this, SextaActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Nombre o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        txtCrearCuenta.setOnClickListener(v -> {
            Intent intent = new Intent(SegundaActivity.this, TerceraActivity.class);
            startActivity(intent);
        });
    }
}