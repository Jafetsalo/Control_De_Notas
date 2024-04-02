package com.example.control_de_notas;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

public class GestionActividadesMateriasActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_actividades_materias);


        nombreMateriaTextView = findViewById(R.id.textViewNombreMateriaAEditarActividades);

        //Aquí *
        materiaAEditar = buscarMateriaNueva();
        //*TO DO: Pendiente asignar materia a editar según si viene de Crear o Editar materia



        nombreMateriaTextView.setText(new String("NOMBRE MATERIA: " + materiaAEditar.NombreMateria));

        picker = (NumberPicker)findViewById(R.id.numberpicker_main_picker);
        picker.setMaxValue(100);
        picker.setMinValue(0);

        spinnerActividadesMateria = findViewById(R.id.spinnerActividadesMateria);
        cargarSpinnerActividades(materiaAEditar);
        spinnerActividadesMateria.setSelection(0);
        nombreActividadEditText = ((EditText)findViewById(R.id.editTextNombreActividad));


        spinnerActividadesMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombreActividadEditText.setText(materiaAEditar.Notas.elementAt(position).NombreActividad);
                picker.setValue((int)(materiaAEditar.Notas.elementAt(position).Peso*100f));

                int MaxValor = ((int)(materiaAEditar.Notas.elementAt(position).Peso*100f)+
                        (int)(materiaAEditar.Notas.elementAt(materiaAEditar.NumeroNotas).Peso*100f));
                picker.setMaxValue(MaxValor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    NumberPicker picker;
    TextView nombreMateriaTextView;
    Materia materiaAEditar;
    Spinner spinnerActividadesMateria;
    EditText nombreActividadEditText;


// Crear método para devolver la materia más recientemente añadida

    public Materia buscarMateriaNueva()
    {
        return DatosPrograma.ListaMaterias.lastElement();
    }

    public void cargarSpinnerActividades(Materia materiaNueva)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                crearArregloActividadesMateria(materiaNueva));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerActividadesMateria.setAdapter(adapter);
    }
    public String[] crearArregloActividadesMateria(@NonNull Materia nuevaMateria)
    {
        int numeroActividades = nuevaMateria.NumeroNotas;
        String[] ActividadesMateria = new String[numeroActividades];
        int x = 0;
        for (Nota actividad: nuevaMateria.Notas)
        {
            if(x==numeroActividades){break;}

            ActividadesMateria[x] = (x+1) + " : " + actividad.NombreActividad;
            x++;

        }

        return ActividadesMateria;
    }


    public void GoToMainActivity(View materia)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }

    public void GuardarNotaActividad(View materia)
    {
        int indiceNota = spinnerActividadesMateria.getSelectedItemPosition();
        //Indice de lo que está seleccionado en el Spinner
        if (TextUtils.isEmpty( nombreActividadEditText.getText()) ||
                ( (nombreActividadEditText.getText().toString().equals(materiaAEditar.Notas.elementAt(indiceNota).NombreActividad)) && (picker.getValue() == ((int)(materiaAEditar.Notas.elementAt(indiceNota).Peso*100f)))) )
        {
            //Verificar si no han cambiado las dos propiedades o editText está vacío

            ShowToast("Datos no han sido llenados o no cambiaron. Sin Cambios");
            return;
        }
        else
        {

            //DONE: Verificar que porcentaje es viable O limitar el valor del picker de acuerdo a dispo
            int indiceMateria = DatosPrograma.ListaMaterias.indexOf(materiaAEditar);
            DatosPrograma.ListaMaterias.elementAt(indiceMateria).Notas.elementAt(indiceNota).NombreActividad = ((EditText)findViewById(R.id.editTextNombreActividad)).getText().toString();

            int notaTotal = ((int)(materiaAEditar.Notas.elementAt(indiceNota).Peso*100f)+
                (int)(materiaAEditar.Notas.elementAt(materiaAEditar.NumeroNotas).Peso*100f));

            DatosPrograma.ListaMaterias.elementAt(indiceMateria).Notas.elementAt(materiaAEditar.NumeroNotas).Peso = ((notaTotal - picker.getValue()) / 100f);
            DatosPrograma.ListaMaterias.elementAt(indiceMateria).Notas.elementAt(indiceNota).Peso = (picker.getValue() / 100f);
            //Cargar Spinner de nuevo
            cargarSpinnerActividades(materiaAEditar); //Error resuelto, aquí ya se comprobó

            ShowToast("Datos guardados correctamente para actividad: " + materiaAEditar.Notas.elementAt(indiceNota).NombreActividad + " Peso de %" + materiaAEditar.Notas.elementAt(indiceNota).Peso*100);
            spinnerActividadesMateria.setSelection(indiceNota);
            //nombreActividadEditText.setText(materiaAEditar.Notas.elementAt(position).NombreActividad);
            //picker.setValue((int)(materiaAEditar.Notas.elementAt(position).Peso*100f));

        }
    }



}