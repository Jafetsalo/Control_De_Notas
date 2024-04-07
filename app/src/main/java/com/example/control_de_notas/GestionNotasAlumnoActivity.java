package com.example.control_de_notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.Vector;

public class GestionNotasAlumnoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_notas_alumno);
        Button botonGuardarCalificacion = findViewById(R.id.buttonGuardarCalificacion);
        spinnerNota = findViewById(R.id.spinnerNotaEstudiante);
        editTextNotaEstudiante = findViewById(R.id.editTextNotaEstudiante);
        textViewNombreEstudiante = findViewById(R.id.textViewNombreEstudiante);
        textViewMateriaEstudiante = findViewById(R.id.textViewNombreMateriaEstudiante);
        textViewCalificacionObtenida = findViewById(R.id.textViewCalificacionObtenida);
        spinnerNota.setOnItemSelectedListener(selectedNotaListener);

        estudianteSeleccionado = (Alumno)getIntent().getSerializableExtra("EstudianteSeleccionado");
        materiaSeleccionada = (Materia)getIntent().getSerializableExtra("MateriaSeleccionada");

        if (estudianteSeleccionado != null && materiaSeleccionada != null) {
        textViewNombreEstudiante.setText("Alumno: " + estudianteSeleccionado.NombreAlumno);
        textViewMateriaEstudiante.setText("Materia:" + materiaSeleccionada.NombreMateria);

        indice_materia = findIndexMateria(estudianteSeleccionado,materiaSeleccionada);
        indice_estudiante = findIndexEstudiante(estudianteSeleccionado);
        
        recargarSpinners();
        } else {
            estudianteSeleccionado = new Alumno();
            botonGuardarCalificacion.setClickable(false);
            ShowToast("Parámetros llegaron nulos - error de intent");
        }




    }

    Alumno estudianteSeleccionado;
    Materia materiaSeleccionada;
    Spinner spinnerNota;
    EditText editTextNotaEstudiante;
    TextView textViewNombreEstudiante;
    TextView textViewMateriaEstudiante;
    TextView textViewCalificacionObtenida;
    int indice_estudiante;
    int indice_materia;

    public void GoToMainActivity(View main)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }

    public void GuardarCalificacion(View main)
    {
        float nota = Float.parseFloat(editTextNotaEstudiante.getText().toString());
        if(nota < 0.0f || nota > 5.0f) {
            ShowToast("Valor ingresado no válido - Número entre 0.0 y 5.0");
            return;
        }
        if(spinnerNota.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            ShowToast("Actividad no seleccionada o vacía");
            return;
        }

        DatosPrograma.ListaEstudiantes.get(indice_estudiante).Materias.get(indice_materia).Notas.get(spinnerNota.getSelectedItemPosition()).Valor = Float.parseFloat(editTextNotaEstudiante.getText().toString());

        ShowToast("Nota actualizada correctamente: " +
                materiaSeleccionada.NombreMateria + " :" + materiaSeleccionada.Notas.get(spinnerNota.getSelectedItemPosition()).NombreActividad + " - "+ Float.parseFloat(editTextNotaEstudiante.getText().toString()));

        Nota notaEstudiante = DatosPrograma.ListaEstudiantes.elementAt(indice_estudiante).Materias.elementAt(indice_materia).Notas.elementAt(spinnerNota.getSelectedItemPosition());
        textViewCalificacionObtenida.setText("Calificación obtenida: "+ notaEstudiante.Valor +" (" + notaEstudiante.Peso * 100f +"%)");
        recargarSpinners();
    }


    public void cargarSpinnerActividades(Materia materiaAlumno)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                crearArregloActividadesMateria(materiaAlumno));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerNota.setAdapter(adapter);
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


    public void recargarSpinners()
    {
        cargarSpinnerActividades(DatosPrograma.ListaEstudiantes.elementAt(indice_estudiante).Materias.elementAt(indice_materia));

        if (DatosPrograma.ListaEstudiantes.elementAt(indice_estudiante).Materias.elementAt(indice_materia).Notas.isEmpty())
        {
            //ShowToast("No es posible editar Alumnos. No hay ninguno registrado");
        }
        else {spinnerNota.setSelection(0);}


        //Hay que colocar el listener para el Spinner Materia


    }
    AdapterView.OnItemSelectedListener selectedNotaListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == AdapterView.INVALID_POSITION) {
                ShowToast("No es posible EDITAR. No hay ninguna Actividad registrada");
                return;
            }

            //Cargar calificación de estudiante en editText
            //int indiceMateria = estudianteSeleccionado.Materias.indexOf(materiaSeleccionada);
            Nota notaEstudiante = DatosPrograma.ListaEstudiantes.elementAt(indice_estudiante).Materias.elementAt(indice_materia).Notas.elementAt(position);


            textViewCalificacionObtenida.setText("Calificación obtenida: "+ notaEstudiante.Valor +" (" + notaEstudiante.Peso * 100f +"%)");

            //recargarSpinners();
        }
        //Este es el listener con delegados que vamos a programar que ocurre cuando los siguientes eventos se desencadenan
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public int findIndexEstudiante(Alumno estudianteSeleccionado)
    {
        for (Alumno estudiante: DatosPrograma.ListaEstudiantes)
        {
         if(estudiante.NationalID.equals(estudianteSeleccionado.NationalID))
         {
             return DatosPrograma.ListaEstudiantes.indexOf(estudiante);
         }
        }
        return (-1);
    }

    public int findIndexMateria(Alumno estudianteSeleccionado, Materia materiaSeleccionada)
    {
        for (Materia asignatura: estudianteSeleccionado.Materias)
        {
            if(asignatura.NombreMateria.equals(materiaSeleccionada.NombreMateria))
            {
                return estudianteSeleccionado.Materias.indexOf(asignatura);
            }
        }
        return (-1);
    }


}