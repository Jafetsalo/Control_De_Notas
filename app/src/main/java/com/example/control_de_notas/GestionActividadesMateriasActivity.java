package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class GestionActividadesMateriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_actividades_materias);
        DatosPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");


        picker = (NumberPicker)findViewById(R.id.numberpicker_main_picker);
        picker.setMaxValue(100);
        picker.setMinValue(1);
    }

    Programa DatosPrograma = new Programa();

    NumberPicker picker;


    public void GoToMainActivity(View materia)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }

}