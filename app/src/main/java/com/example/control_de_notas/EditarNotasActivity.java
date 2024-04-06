package com.example.control_de_notas;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Vector;

public class EditarNotasActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_notas);

        spinnerEstudiante = findViewById(R.id.spinnerEstudiante);
        spinnerMateriasAlumno = findViewById(R.id.spinnerMateriasAlumno);

        spinnerEstudiante.setOnItemSelectedListener(selectedEstudianteListener);

        recargarSpinners();
    }
    Alumno estudianteSeleccionado;
    Materia materiaSeleccionada;
    Spinner spinnerEstudiante;
    Spinner spinnerMateriasAlumno;
    public void GoToGestionNotasAlumnoActivity(View gestionNotas)
    {

        if (spinnerEstudiante.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            ShowToast("No es posible EDITAR. No hay ningún Estudiante registrado");
            recargarSpinners();
            return;
        }
        if (spinnerMateriasAlumno.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            ShowToast("No es posible EDITAR. No hay ningún Estudiante registrado");
            recargarSpinners();
            return;
        }
        int indice_estudiante = spinnerEstudiante.getSelectedItemPosition();
        int indice_materia = spinnerMateriasAlumno.getSelectedItemPosition();

        Intent intentGestionNotasAlumno = new Intent(this, GestionNotasAlumnoActivity.class);
        //That was the best command :_)
        intentGestionNotasAlumno.putExtra("DatosPrograma",DatosPrograma);
        intentGestionNotasAlumno.putExtra("EstudianteSeleccionado",DatosPrograma.ListaEstudiantes.get(indice_estudiante));
        intentGestionNotasAlumno.putExtra("MateriaSeleccionada",DatosPrograma.ListaEstudiantes.get(indice_estudiante).Materias.get(indice_materia));
        startActivity(intentGestionNotasAlumno);





    }


    public void cargarSpinnerConMaterias(@NonNull Vector<Materia> materiaVector, Spinner spinnerToLoad)
    {

        if (materiaVector.isEmpty())
        {
            ShowToast("No es posible modificar Materias. No hay ninguna registrada");
            ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[] {});
            spinnerToLoad.setAdapter(emptyAdapter);
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                crearArregloMaterias(materiaVector));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerToLoad.setAdapter(adapter);
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
                crearArregloEstudiantes(alumnoVector));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerToLoad.setAdapter(adapter);
    }
    public String[] crearArregloEstudiantes(@NonNull Vector<Alumno> ListaEstudiantes)
    {
        int numeroEstudiantes = ListaEstudiantes.size();
        String[] Estudiantes = new String[numeroEstudiantes];
        int x = 0;
        for (Alumno estudiante: ListaEstudiantes)
        {
            if(x==numeroEstudiantes){break;}

            Estudiantes[x] = (x+1) + " : " + estudiante.NombreAlumno;
            x++;

        }

        return Estudiantes;
    }

    public String[] crearArregloMaterias(@NonNull Vector<Materia> ListaMaterias)
    {
        int numeroMaterias = ListaMaterias.size();
        String[] ArregloMaterias = new String[numeroMaterias];
        int x = 0;
        for (Materia asignatura: ListaMaterias)
        {
            if(x==numeroMaterias){break;}

            ArregloMaterias[x] = (x+1) + " : " + asignatura.NombreMateria;
            x++;

        }

        return ArregloMaterias;
    }

    public void recargarSpinners()
    {
        cargarSpinnerConAlumnos(DatosPrograma.ListaEstudiantes,spinnerEstudiante);

        if (DatosPrograma.ListaMaterias.isEmpty())
        {
            //ShowToast("No es posible editar Alumnos. No hay ninguno registrado");
        }
        else {spinnerEstudiante.setSelection(0);}


        //Hay que colocar el listener para el Spinner Materia


    }



    AdapterView.OnItemSelectedListener selectedEstudianteListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == AdapterView.INVALID_POSITION) {
                ShowToast("No es posible EDITAR. No hay ningún Estudiante registrado");
                return;
            }
            //estudianteSeleccionado = DatosPrograma.ListaEstudiantes.get(position);
            cargarSpinnerConMaterias(DatosPrograma.ListaEstudiantes.get(position).Materias,spinnerMateriasAlumno);
        }
        //Este es el listener con delegados que vamos a programar que ocurre cuando los siguientes eventos se desencadenan
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };




}