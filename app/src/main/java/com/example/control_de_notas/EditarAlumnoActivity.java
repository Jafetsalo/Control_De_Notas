package com.example.control_de_notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Vector;

public class EditarAlumnoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);

        spinnerEstudiantes = (Spinner) findViewById(R.id.spinnerEstudiante);

        recargarSpinners();




    }

    Spinner spinnerEstudiantes;
    Alumno AlumnoCambiar;

    public void GoToGestionMateriasAlumno(View gestionMateriasAlumno)
    {

        //Verificar si seleccionado es correcto como al borrar Alumno
        ShowToast("INDICE: "+ spinnerEstudiantes.getSelectedItemPosition());
        if(spinnerEstudiantes.getSelectedItemPosition() == AdapterView.INVALID_POSITION)
        {
            ShowToast("No es posible Modificar Matricula Alumno. No hay ninguno registrado");
            recargarSpinners();
            return;
        }

        int indiceEstudiante = spinnerEstudiantes.getSelectedItemPosition();
        // Alumno passedAlumno = (Alumno)getIntent().getSerializableExtra("AlumnoCambiar");

        Intent intentGestionMateriasAlumno = new Intent(this, GestionMateriasAlumnoActivity.class);
        //That was the best command :_)
        intentGestionMateriasAlumno.putExtra("DatosPrograma",DatosPrograma);
        intentGestionMateriasAlumno.putExtra("AlumnoCambiar",DatosPrograma.ListaEstudiantes.get(indiceEstudiante));
        startActivity(intentGestionMateriasAlumno);


    }


    public void GoToMainActivity(View editarAlumno)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }

    public void cargarSpinnerConAlumnos(@NonNull Vector<Alumno> alumnoVector, Spinner spinnerToLoad)
    {

        if (alumnoVector.isEmpty())
        {
            ShowToast("No es posible modificar Alumno. No hay ninguno registrado");
            ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[] {});
            spinnerToLoad.setAdapter(emptyAdapter);
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                crearArregloAlumnoVector(alumnoVector));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerToLoad.setAdapter(adapter);
    }


    public String[] crearArregloAlumnoVector(@NonNull Vector<Alumno> alumnoVector)
    {
        int numeroAlumnos = alumnoVector.size();
        String[] ArregloAlumnos = new String[numeroAlumnos];
        int x = 0;
        for (Alumno estudiante: alumnoVector)
        {
            ArregloAlumnos[x] = (x+1) + " : " + estudiante.NombreAlumno;
            x++;
        }

        return ArregloAlumnos;
    }

    public void eliminarAlumno(View editarAlumno)
    {
        ShowToast("INDICE: "+ spinnerEstudiantes.getSelectedItemPosition());
        if(spinnerEstudiantes.getSelectedItemPosition() == AdapterView.INVALID_POSITION)
        {
            ShowToast("No es posible BORRAR Alumno. No hay ninguno registrado");
            recargarSpinners();
            return;
        }

        int indiceEstudiante = spinnerEstudiantes.getSelectedItemPosition();

        if (indiceEstudiante<DatosPrograma.ListaEstudiantes.size()) {
            ShowToast("Alumno: " + DatosPrograma.ListaEstudiantes.get(indiceEstudiante).NombreAlumno + " borrado correctamente");
            DatosPrograma.ListaEstudiantes.removeElement(DatosPrograma.ListaEstudiantes.get(indiceEstudiante));

        }
        recargarSpinners();

    }

    public void recargarSpinners()
    {

        cargarSpinnerConAlumnos(DatosPrograma.ListaEstudiantes,spinnerEstudiantes);
        if (DatosPrograma.ListaMaterias.isEmpty())
        {
            //ShowToast("No es posible editar Alumnos. No hay ninguno registrado");
        }
        else {spinnerEstudiantes.setSelection(0);}



    }




}