package com.cbtis.plantillabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CuartaActivity extends AppCompatActivity {

    TextView txtCrearCuentaAlumno;
    EditText edtNombreLogin, edtContrasenaLogin;
    Spinner spinGrupoLogin;
    Button btnIngresarAlumno;

    String[] grupos = {"Grupo A", "Grupo B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarta);

        txtCrearCuentaAlumno = findViewById(R.id.txtCrearCuentaAlumno);
        edtNombreLogin = findViewById(R.id.edtNombreLogin);
        edtContrasenaLogin = findViewById(R.id.edtContrasenaLogin);
        spinGrupoLogin = findViewById(R.id.spinGrupoLogin);
        btnIngresarAlumno = findViewById(R.id.btnIngresarAlumno);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grupos);
        spinGrupoLogin.setAdapter(adapter);

        btnIngresarAlumno.setOnClickListener(v -> {
            String usuarioIngresado = edtNombreLogin.getText().toString().trim();
            String grupoIngresado = spinGrupoLogin.getSelectedItem().toString();
            String contrasenaIngresada = edtContrasenaLogin.getText().toString();

            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences pref = getSharedPreferences("EduCoreData", MODE_PRIVATE);

            // Intentar recuperar el perfil guardado
            String contrasenaCorrecta = pref.getString("alumno_pass_" + usuarioIngresado, null);
            String grupoCorrecto = pref.getString("alumno_grupo_" + usuarioIngresado.toLowerCase().replace(" ", "_"), null);

            // Cuenta por defecto para pruebas
            if (usuarioIngresado.equalsIgnoreCase("Juan Pérez") && contrasenaCorrecta == null) {
                contrasenaCorrecta = "123";
                grupoCorrecto = "Grupo A";
            }

            if (contrasenaCorrecta != null && contrasenaIngresada.equals(contrasenaCorrecta) && grupoIngresado.equals(grupoCorrecto)) {
                Toast.makeText(this, "Acceso Alumno Autorizado", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CuartaActivity.this, SeptimaActivity.class);
                intent.putExtra("nombreAlumno", usuarioIngresado);
                intent.putExtra("grupoAlumno", grupoIngresado);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Usuario, contraseña o grupo incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        txtCrearCuentaAlumno.setOnClickListener(v -> {
            Intent intent = new Intent(CuartaActivity.this, QuintaActivity.class);
            startActivity(intent);
        });
    }
}