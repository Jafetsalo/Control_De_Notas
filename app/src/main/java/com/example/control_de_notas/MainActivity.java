package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatosPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");
    }

    Programa DatosPrograma = new Programa();

//Button crearAlumno = findViewById(R.id.btnCrearAlumno);

    public void GoToAlumnoActivity(View alumno)
    {
        Intent intentAlumno = new Intent(this, CrearAlumnoActivity.class);
        //That was the best command :_)
        intentAlumno.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentAlumno);


    }

    public void GoToCrearMateriaActivity(View alumno)
    {
        Intent intentCrearMateria = new Intent(this, CrearMateriaActivity.class );
        //That was the best command :_)
        intentCrearMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentCrearMateria);


    }

}