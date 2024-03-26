package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Date;
import java.util.Vector;

public class CrearMateriaActivity extends BaseActivity {

    Programa DatosPrograma = new Programa();
    NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_materia);

        DatosPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");

        picker = (NumberPicker)findViewById(R.id.numberpicker_main_pickerMateria);
        picker.setMaxValue(10);
        picker.setMinValue(1);
    }


    public void GoToGestionActividadMateriasActivity(View alumno)
    {

        if (TextUtils.isEmpty( ((EditText)findViewById(R.id.editTextCrearMateria)).getText() ))
        {
            ShowToast("Datos no han sido llenados");
            return;
        }

        int numeroNotasMateria = picker.getValue();
        String nombreMateria = ((EditText)findViewById(R.id.editTextCrearMateria)).getText().toString();

        Materia nuevaMateria = new Materia(new Vector<Nota>(),nombreMateria,numeroNotasMateria);

        if(!materiaRepetida(nuevaMateria))
        {
            ShowToast(nuevaMateria.NombreMateria + " con: " + nuevaMateria.NumeroNotas + " notas. Ha sido Registrada en el Catálogo");

            Intent intentGestionActividadesMaterias = new Intent(this, GestionActividadesMateriasActivity.class);
            //That was the best command :_)
            intentGestionActividadesMaterias.putExtra("DatosPrograma",DatosPrograma);
            startActivity(intentGestionActividadesMaterias);

        }
        else {ShowToast("Materia con ese nombre ya existe.");}


        //Ver si materia está repetida o no
        //Guardarla en Vector
        //Lamar a nuevo Activity





    }

    public void GoToMainActivity(View materia)
    {
        if (TextUtils.isEmpty( ((EditText)findViewById(R.id.editTextCrearMateria)).getText() ))
        {
            ShowToast("Datos no han sido llenados");
            return;
        }

        int numeroNotasMateria = picker.getValue();
        String nombreMateria = ((EditText)findViewById(R.id.editTextCrearMateria)).getText().toString();

        Materia nuevaMateria = new Materia(new Vector<Nota>(),nombreMateria,numeroNotasMateria);

        if(!materiaRepetida(nuevaMateria))
        {
            ShowToast(nuevaMateria.NombreMateria + " con: " + nuevaMateria.NumeroNotas + " notas. Ha sido Registrada en el Catálogo");

            Intent intentMateria = new Intent(this, MainActivity.class);
            //That was the best command :_)
            intentMateria.putExtra("DatosPrograma",DatosPrograma);
            startActivity(intentMateria);
        }
        else {ShowToast("Materia con ese nombre ya existe.");}


        //Ver si materia está repetida o no
        //Guardarla en Vector
        //Lamar a nuevo Activity



    }

    public boolean materiaRepetida(Materia nuevaMateria)
    {
        boolean repetido = false;

        try { if (DatosPrograma.ListaMaterias == null) {return false;}}
        catch (Exception e)
        {
            ShowToast(e.toString());
            DatosPrograma.ListaMaterias = new Vector<Materia>();
            DatosPrograma.ListaMaterias.add(nuevaMateria);
            return false;
        }

        if (DatosPrograma.ListaMaterias.isEmpty()) { DatosPrograma.ListaMaterias.add(nuevaMateria); return false;}
        else
        {
            for (Materia materia: DatosPrograma.ListaMaterias)
            {
              if (materia.NombreMateria.equals(nuevaMateria.NombreMateria)) { repetido = true; break;}
            }

            if (!repetido) { DatosPrograma.ListaMaterias.add(nuevaMateria);}
        }


        return repetido;
    }


}