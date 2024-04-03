package com.example.control_de_notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Vector;

public class GestionMateriasAlumnoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);


        //Aquí *
        alumnoAEditar = buscarAlumnoNuevo();
        //*TO DO: Pendiente asignar Alumno a editar según si viene de Crear o Editar Alumno

        spinnerMateriaAlumno = (Spinner)findViewById(R.id.spinnerMateriasAlumno);
        spinnerMateriaCatalogo = (Spinner)findViewById(R.id.spinnerMateriasCatalogo);

        nombreAlumnoEncabezado = (TextView)findViewById(R.id.textViewNombreAlumno);
        nombreAlumnoEncabezado.setText(new String("ALUMNO: " + alumnoAEditar.NombreAlumno));

        recargarSpinners();



    }


    Spinner spinnerMateriaCatalogo;
    Spinner spinnerMateriaAlumno;
    Alumno alumnoAEditar;
    TextView nombreAlumnoEncabezado;

    public void cargarSpinnerConMaterias(@NonNull Vector<Materia> materiaVector, Spinner spinnerToLoad)
    {

        if (materiaVector.isEmpty())
        {
            ShowToast("No es posible cancelar materias. No hay ninguna registrada");
            ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[] {});
            spinnerToLoad.setAdapter(emptyAdapter);
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
               crearArregloMateriasVector(materiaVector));

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

       spinnerToLoad.setAdapter(adapter);
    }

    public String[] crearArregloMateriasVector(@NonNull Vector<Materia> materiaVector)
    {
        int numeroMaterias = materiaVector.size();
        String[] ArregloMaterias = new String[numeroMaterias];
        int x = 0;
        for (Materia curso: materiaVector)
        {
            ArregloMaterias[x] = (x+1) + " : " + curso.NombreMateria;
            x++;
        }

        return ArregloMaterias;
    }

    public Alumno buscarAlumnoNuevo()
    {
        return DatosPrograma.ListaEstudiantes.lastElement();
    }

    public void GoToMainActivity(View main)
    {
        Intent intentMateria = new Intent(this, MainActivity.class);
        //That was the best command :_)
        intentMateria.putExtra("DatosPrograma",DatosPrograma);
        startActivity(intentMateria);
    }

    public void MatricularMateriaAlumno(View materia) {
        int indiceMateria = spinnerMateriaCatalogo.getSelectedItemPosition();

        if (indiceMateria == AdapterView.INVALID_POSITION) {
            ShowToast("No es posible MATRICULAR materias. No hay ninguna registrada");
        } else if (isMateriaAlreadyMatriculated(indiceMateria)) {
            ShowToast("No es posible MATRICULAR materias. Ya está matriculada");
        } else {
            alumnoAEditar.Materias.add(DatosPrograma.ListaMaterias.elementAt(indiceMateria));
            ShowToast("Materia: "+ DatosPrograma.ListaMaterias.elementAt(indiceMateria).NombreMateria + " matriculada correctamente");
        }

        recargarSpinners();
    }

    private boolean isMateriaAlreadyMatriculated(int indiceMateria) {
        for (Materia curso : alumnoAEditar.Materias) {
            if (curso.equals(DatosPrograma.ListaMaterias.elementAt(indiceMateria))) {
                return true;
            }
        }
        return false;
    }

    public void CancelarMateriaAlumno(View materia)
    {
        ShowToast("INDICE: "+ spinnerMateriaAlumno.getSelectedItemPosition());
        if(spinnerMateriaAlumno.getSelectedItemPosition() == AdapterView.INVALID_POSITION)
        {
            ShowToast("No es posible CANCELAR materias. No hay ninguna registrada");
            recargarSpinners();
            return;
        }

        int indiceMateria = spinnerMateriaAlumno.getSelectedItemPosition();

        if (indiceMateria<alumnoAEditar.Materias.size()) {
            ShowToast("Materia: " + alumnoAEditar.Materias.get(indiceMateria).NombreMateria + " cancelada correctamente");
            alumnoAEditar.Materias.removeElement(alumnoAEditar.Materias.get(indiceMateria));

        }
       recargarSpinners();

    }

    public void recargarSpinners()
    {

        cargarSpinnerConMaterias(DatosPrograma.ListaMaterias, spinnerMateriaCatalogo);
        if (DatosPrograma.ListaMaterias.isEmpty())
        {
            //ShowToast("No es posible agregar materias. No hay ninguna registrada");
        }
        else {spinnerMateriaCatalogo.setSelection(0);}


        cargarSpinnerConMaterias(alumnoAEditar.Materias, spinnerMateriaAlumno);
        if (alumnoAEditar.Materias.isEmpty())
        {
            // ShowToast("No es posible cancelar materias. No hay ninguna registrada");

        }
        else {spinnerMateriaAlumno.setSelection(0);}



    }

}