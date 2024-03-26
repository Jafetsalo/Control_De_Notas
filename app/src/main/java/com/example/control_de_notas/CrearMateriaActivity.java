package com.example.control_de_notas;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Vector;

public class CrearMateriaActivity extends BaseActivity {


    NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_materia);

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

        Materia nuevaMateria = crearMateria();

        if(!materiaRepetida(nuevaMateria))
        {
            ShowToast(nuevaMateria.NombreMateria + " con: " + nuevaMateria.NumeroNotas + " notas. Ha sido Registrada en el Catálogo");

            Intent intentGestionActividadesMaterias = new Intent(this, GestionActividadesMateriasActivity.class);
            //That was the best command :_)
            intentGestionActividadesMaterias.putExtra("DatosPrograma",DatosPrograma);
            startActivity(intentGestionActividadesMaterias);

        }
        else {ShowToast("Materia con ese nombre ya existe.");}

    }

    @NonNull
    private Materia crearMateria() {
        int numeroNotasMateria = (picker.getValue() +1);
        String nombreMateria = ((EditText)findViewById(R.id.editTextCrearMateria)).getText().toString();
        Vector<Nota> NotasMateria = new Vector<>();

        for (int x = 0; x<numeroNotasMateria; x++)
        {
            if (x == 1) {Nota nuevaNota = new Nota(1,1/(numeroNotasMateria-1),("NO ASIGNADO"));}
            Nota nuevaNota = new Nota(1,1/(numeroNotasMateria-1),("Nota # "+x));
            // Crear notas para la materia
            NotasMateria.add(nuevaNota);
        }

        Materia nuevaMateria = new Materia(NotasMateria,nombreMateria,numeroNotasMateria);
        return nuevaMateria;
    }

    public void GoToMainActivity(View materia)
    {
        if (TextUtils.isEmpty( ((EditText)findViewById(R.id.editTextCrearMateria)).getText() ))
        {
            ShowToast("Datos no han sido llenados");
            return;
        }

        Materia nuevaMateria = crearMateria();

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