package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CrearAlumnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);


        //Catch DatosPrograma

        DatosPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");
        Bundle bundle = new Bundle();

        bundle.putSerializable("DatosPrograma", DatosPrograma);

    }

    Programa DatosPrograma;


    public void GoToMateriaActivity(View materia)
    {
        Intent intentMateria = new Intent(this, GestionMateriasAlumnoActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }


    public void GoToMainActivity(View materia)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }

    //My second activity :)

    //It grasps our XML viewpager
    //Then It assigns an instance of pageradapter to it


}