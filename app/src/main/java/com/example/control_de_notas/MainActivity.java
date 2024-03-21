package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


//Button crearAlumno = findViewById(R.id.btnCrearAlumno);

    public void GoToAlumnoActivity(View alumno)
    {
        Intent intentAlumno = new Intent(this, AlumnoActivity.class);
        //That was the best command :_)

        startActivity(intentAlumno);


    }

}