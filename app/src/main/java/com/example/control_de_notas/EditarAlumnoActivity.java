package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditarAlumnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);
        DatosPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");

    }

    Programa DatosPrograma;


    public void GoToGestionMateriasAlumno(View gestionMateriasAlumno)
    {
        Intent intentGestionMateriasAlumno = new Intent(this, GestionMateriasAlumnoActivity.class);
        //That was the best command :_)
        intentGestionMateriasAlumno.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentGestionMateriasAlumno);


    }


    public void GoToMainActivity(View materia)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }




}