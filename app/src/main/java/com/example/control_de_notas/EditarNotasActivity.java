package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditarNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_notas);

        DatosPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");

    }

    Programa DatosPrograma;

    public void GoToGestionNotasAlumnoActivity(View gestionNotas)
    {
        Intent intentGestionNotasAlumno = new Intent(this, GestionNotasAlumnoActivity.class);
        //That was the best command :_)
        intentGestionNotasAlumno.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentGestionNotasAlumno);


    }


}