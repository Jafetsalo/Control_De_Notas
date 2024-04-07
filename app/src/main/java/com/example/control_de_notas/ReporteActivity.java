package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class ReporteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        spinnerMateriasReporte = findViewById(R.id.spinnerMateriasReporte);
        textViewAprobados = findViewById(R.id.textViewEstudiantesAprobados);
        textViewReprobados= findViewById(R.id.textViewEstudiantesReprobados);
        estudiantesAprobados = 0;
        estudiantesReprobados = 0;

        recargarSpinners();
    }
    Spinner spinnerMateriasReporte;
    TextView textViewReprobados;
    int estudiantesReprobados;
    TextView textViewAprobados;
    int estudiantesAprobados;

    public void cargarSpinnerMaterias(Vector<Materia> listaMaterias)
    {
        if (listaMaterias.isEmpty())
        {
            ShowToast("No es posible generar reporte de materias. No hay ninguna registrada");
            ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[] {});
            spinnerMateriasReporte.setAdapter(emptyAdapter);
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                crearArreglo(listaMaterias, Materia::getNombreMateria));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerMateriasReporte.setAdapter(adapter);
    }

    public void GoToMainActivity(View main)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);

    }

    public void GenerarReporteMateria(View main)
    {
        int position = spinnerMateriasReporte.getSelectedItemPosition();
        if ((position == AdapterView.INVALID_POSITION) || !(position<DatosPrograma.ListaMaterias.size())) {
            ShowToast("No es posible Generar reporte. No hay ninguna Materia registrada");
            return;
        }
        Materia materia = DatosPrograma.ListaMaterias.elementAt(position);

        Vector<Alumno> estudianteMatriculado = crearVectorEstudianteMatriculado(materia);
        reportarAprobadoReprobado(estudiantesAprobados,estudiantesReprobados);

        //It is possible to report each student approval of failure on the subject "Materia" here
        //Using the vector and some for loops to report whether they passed or not on an individual level


    }

    public Vector<Alumno> crearVectorEstudianteMatriculado(Materia materia)
    {
        estudiantesAprobados = 0;
        estudiantesReprobados = 0;
        Vector<Alumno> estudiantesMatriculados = new Vector<>();
        for (Alumno estudiante: DatosPrograma.ListaEstudiantes)
        {
            for (Materia asignatura: estudiante.Materias)
            {
                if(asignatura.NombreMateria.equals(materia.NombreMateria))
                {
                    estudiantesMatriculados.add(estudiante);
                    calcularAprobadoReprobado(asignatura);
                }

            }
        }
        return estudiantesMatriculados;
    }


    public void calcularAprobadoReprobado(Materia asignatura)
    {
        float calificacionMateria = 0f;
        for (int x = 0;x<asignatura.NumeroNotas;x++ )
        {
            Nota calificacion = asignatura.Notas.get(x);
            calificacionMateria += (calificacion.Peso*calificacion.Valor);
        }

        if(calificacionMateria>=3f)
        {
            estudiantesAprobados++;
        } else if (calificacionMateria<3f) {
            estudiantesReprobados++;
        }
    }


    public void recargarSpinners()
    {
        cargarSpinnerMaterias(DatosPrograma.ListaMaterias);

        if (DatosPrograma.ListaEstudiantes.isEmpty())
        {
            //ShowToast("No es posible generar reporte Materias. No hay ninguna registrada");
        }
        else {spinnerMateriasReporte.setSelection(0);}


        //Hay que colocar el listener para el Spinner Materia


    }


    public void reportarAprobadoReprobado(int aprobado, int reprobado)
    {

        textViewAprobados.setText("Estudiantes Aprobados: " + aprobado);
        textViewReprobados.setText("Estudiantes Reprobados: " + reprobado);

    }


}

