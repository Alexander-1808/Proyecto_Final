package com.cbtis.plantillabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SeptimaActivity extends AppCompatActivity {

    TextView txtInfoAlumno, txtMatematicasNota, txtEspanolNota, txtHistoriaNota, txtPromedioGeneral;
    Button btnCalcularPromedio, btnCerrarSesionAlumno;

    double nMatematicas = 0.0, nEspanol = 0.0, nHistoria = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_septima);

        txtInfoAlumno = findViewById(R.id.txtInfoAlumno);
        txtMatematicasNota = findViewById(R.id.txtMatematicasNota);
        txtEspanolNota = findViewById(R.id.txtEspanolNota);
        txtHistoriaNota = findViewById(R.id.txtHistoriaNota);
        txtPromedioGeneral = findViewById(R.id.txtPromedioGeneral);
        btnCalcularPromedio = findViewById(R.id.btnCalcularPromedio);
        btnCerrarSesionAlumno = findViewById(R.id.btnCerrarSesionAlumno);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombreAlumno", "Estudiante").trim();
            String grupo = extras.getString("grupoAlumno", "S/G");

            txtInfoAlumno.setText("Alumno: " + nombre + "\nGrupo: " + grupo);

            SharedPreferences pref = getSharedPreferences("EduCoreData", MODE_PRIVATE);

            String sMat = pref.getString(nombre + "_Matemáticas", "0.0");
            String sEsp = pref.getString(nombre + "_Español", "0.0");
            String sHis = pref.getString(nombre + "_Historia", "0.0");

            nMatematicas = Double.parseDouble(sMat);
            nEspanol = Double.parseDouble(sEsp);
            nHistoria = Double.parseDouble(sHis);

            txtMatematicasNota.setText("Matemáticas: " + sMat);
            txtEspanolNota.setText("Español: " + sEsp);
            txtHistoriaNota.setText("Historia: " + sHis);
        }

        btnCalcularPromedio.setOnClickListener(v -> {
            double suma = nMatematicas + nEspanol + nHistoria;
            double promedioGeneral = suma / 3.0;

            String resultadoFormateado = String.format("%.1f", promedioGeneral);

            txtPromedioGeneral.setText("Promedio General: " + resultadoFormateado);
            Toast.makeText(this, "Promedio calculado correctamente", Toast.LENGTH_SHORT).show();
        });

        btnCerrarSesionAlumno.setOnClickListener(v -> {
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SeptimaActivity.this, MainActivity.class);
            
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}