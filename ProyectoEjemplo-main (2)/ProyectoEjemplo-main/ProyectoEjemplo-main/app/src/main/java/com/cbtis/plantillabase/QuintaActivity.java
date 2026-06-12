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

public class QuintaActivity extends AppCompatActivity {

    TextView txtIniciarSesionAlumno;
    EditText edtNombre, edtContrasena;
    Spinner spinGrupoAlumno;
    Button btnRegistrarAlumno;

    String[] grupos = {"Grupo A", "Grupo B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quinta);

        txtIniciarSesionAlumno = findViewById(R.id.txtIniciarSesionAlumno);
        edtNombre = findViewById(R.id.edtNombre);
        edtContrasena = findViewById(R.id.edtContrasena);
        spinGrupoAlumno = findViewById(R.id.spinGrupoAlumno);
        btnRegistrarAlumno = findViewById(R.id.btnRegistrarAlumno);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grupos);
        spinGrupoAlumno.setAdapter(adapter);

        btnRegistrarAlumno.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString().trim();
            String grupo = spinGrupoAlumno.getSelectedItem().toString();
            String contrasena = edtContrasena.getText().toString();

            if (nombre.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences pref = getSharedPreferences("EduCoreData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                // GUARDAR ALUMNO PERMANENTEMENTE
                editor.putString("alumno_pass_" + nombre, contrasena);
                editor.putString("alumno_grupo_" + textAluKey(nombre), grupo);

                // Agregar el alumno al listado global del maestro
                String alumnosExistentes = pref.getString("listaAlumnos", "Juan Pérez,María López,Carlos Gómez,Ana Rodríguez");
                if (!alumnosExistentes.contains(nombre)) {
                    alumnosExistentes = alumnosExistentes + "," + nombre;
                    editor.putString("listaAlumnos", alumnosExistentes);
                }

                editor.apply();

                Toast.makeText(this, "Registro de Alumno Exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(QuintaActivity.this, CuartaActivity.class);
                startActivity(intent);
            }
        });

        txtIniciarSesionAlumno.setOnClickListener(v -> {
            Intent intent = new Intent(QuintaActivity.this, CuartaActivity.class);
            startActivity(intent);
        });
    }

    private String textAluKey(String name) {
        return name.toLowerCase().replace(" ", "_");
    }
}