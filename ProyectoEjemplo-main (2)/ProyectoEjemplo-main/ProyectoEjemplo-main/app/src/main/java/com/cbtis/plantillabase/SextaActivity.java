package com.cbtis.plantillabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class SextaActivity extends AppCompatActivity {

    Spinner spinGrupo, spinAlumno;
    RadioGroup rgMaterias;
    EditText edtPromedio;
    Button btnSubirCalificacion, btnCerrarSesionMaestro;

    String[] grupos = {"Grupo A", "Grupo B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sexta);

        spinGrupo = findViewById(R.id.spinGrupo);
        spinAlumno = findViewById(R.id.spinAlumno);
        rgMaterias = findViewById(R.id.rgMaterias);
        edtPromedio = findViewById(R.id.edtPromedio);
        btnSubirCalificacion = findViewById(R.id.btnSubirCalificacion);
        btnCerrarSesionMaestro = findViewById(R.id.btnCerrarSesionMaestro);

        ArrayAdapter<String> adapterGrupos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grupos);
        spinGrupo.setAdapter(adapterGrupos);

        SharedPreferences pref = getSharedPreferences("EduCoreData", MODE_PRIVATE);
        String listaDeAlumnosString = pref.getString("listaAlumnos", "Juan Pérez,María López,Carlos Gómez,Ana Rodríguez");
        String[] listaAlumnosActualizada = listaDeAlumnosString.split(",");

        ArrayAdapter<String> adapterAlumnos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaAlumnosActualizada);
        spinAlumno.setAdapter(adapterAlumnos);

        btnSubirCalificacion.setOnClickListener(v -> {
            String grupo = spinGrupo.getSelectedItem().toString();
            String alumno = spinAlumno.getSelectedItem().toString().trim();
            String promedio = edtPromedio.getText().toString().trim();

            int idSeleccionado = rgMaterias.getCheckedRadioButtonId();

            if (idSeleccionado == -1) {
                Toast.makeText(this, "Por favor, selecciona una materia", Toast.LENGTH_SHORT).show();
            } else if (promedio.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa un promedio", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rbMateria = findViewById(idSeleccionado);
                String materia = rbMateria.getText().toString();

                SharedPreferences.Editor editor = pref.edit();
                editor.putString(alumno + "_" + materia, promedio);
                editor.apply();

                Toast.makeText(this, "Promedio subido con éxito para " + alumno, Toast.LENGTH_LONG).show();
                edtPromedio.setText("");
            }
        });

        // Evento para Cerrar Sesión y volver al inicio absoluto de la app
        btnCerrarSesionMaestro.setOnClickListener(v -> {
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SextaActivity.this, MainActivity.class);
            // Estas banderas borran el historial para que no se pueda regresar con el botón "atrás" del cel
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}