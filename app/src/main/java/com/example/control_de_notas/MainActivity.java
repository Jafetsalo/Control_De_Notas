package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




//Button crearAlumno = findViewById(R.id.btnCrearAlumno);

    public void GoToAlumnoActivity(View alumno)
    {
        Intent intentAlumno = new Intent(this, CrearAlumnoActivity.class);
        //That was the best command :_)
        intentAlumno.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentAlumno);


    }

    public void GoToCrearMateriaActivity(View crearMateria)
    {
        Intent intentCrearMateria = new Intent(this, CrearMateriaActivity.class );
        //That was the best command :_)
        intentCrearMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentCrearMateria);


    }

    public void GoToEditarNotasActivity(View editarNotas)
    {
        Intent intentEditarNotas = new Intent(this, EditarNotasActivity.class);
        //That was the best command :_)
        intentEditarNotas.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentEditarNotas);


    }

    public void GoToEditarAlumnoActivity(View editarAlumno)
    {
        Intent intentEditarAlumno = new Intent(this, EditarAlumnoActivity.class);
        //That was the best command :_)
        intentEditarAlumno.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentEditarAlumno);


    }


    public void GoToReporteActivity(View reporteMateria)
    {
        Intent intentReporteMateria = new Intent(this, ReporteActivity.class);
        //That was the best command :_)
        intentReporteMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentReporteMateria);


    }





}