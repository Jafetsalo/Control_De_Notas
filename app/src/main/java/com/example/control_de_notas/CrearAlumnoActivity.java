package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Vector;

public class CrearAlumnoActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);


        //Catch DatosPrograma


    }




    public void GoToMateriaActivity(View materia)
    {
        if (TextUtils.isEmpty(((EditText)findViewById(R.id.editTextNombreAlumno)).getText()) ||
                TextUtils.isEmpty(((EditText)findViewById(R.id.editTextNationalID)).getText())) {
            ShowToast("Datos no han sido llenados");
            return;
        }

        BigInteger nationalID = new BigInteger(((EditText)findViewById(R.id.editTextNationalID)).getText().toString());
        String nombreAlumno = ((EditText)findViewById(R.id.editTextNombreAlumno)).getText().toString();

        if ((!nombreAlumno.isEmpty()) &&
                (nationalID.toString().length() > 8) && (!nationalID.toString().isEmpty()))
        {
            Alumno nuevoEstudiante = new Alumno(nombreAlumno, new Vector<>(),nationalID);

            if(!estudianteRepetido(nuevoEstudiante))
            {

                ShowToast( nombreAlumno + " " + nationalID + " Ha sido Matriculado");

                Intent intentMateria = new Intent(this, GestionMateriasAlumnoActivity.class);
                //That was the best command :_)
                intentMateria.putExtra("DatosPrograma",DatosPrograma);
                startActivity(intentMateria);

            }
            else {ShowToast("Estudiante con esa Cédula o ID ya existe.");}

        }
        else
        {
            ShowToast("Faltan Datos");
        }






    }


    public void GoToMainActivity(View materia)
    {
        // Lógica para revisar que alumno no exista y guardarlo en DatosPrograma


        if (TextUtils.isEmpty(((EditText)findViewById(R.id.editTextNombreAlumno)).getText()) ||
                TextUtils.isEmpty(((EditText)findViewById(R.id.editTextNationalID)).getText())) {
            ShowToast("Datos no han sido llenados");
            return;
        }



        BigInteger nationalID = new BigInteger(((EditText)findViewById(R.id.editTextNationalID)).getText().toString());
        String nombreAlumno = ((EditText)findViewById(R.id.editTextNombreAlumno)).getText().toString();




        if ((!nombreAlumno.isEmpty()) &&
                (nationalID.toString().length() > 8) && (!nationalID.toString().isEmpty()))
        {
            Alumno nuevoEstudiante = new Alumno(nombreAlumno, new Vector<>(),nationalID);

            if(!estudianteRepetido(nuevoEstudiante))
            {

                ShowToast( nombreAlumno + " " + nationalID + " Ha sido Matriculado");

                Intent intentMain = new Intent(this, MainActivity.class);
                //That was the best command :_)
                intentMain.putExtra("DatosPrograma",DatosPrograma);
                startActivity(intentMain);

            }
            else {ShowToast("Estudiante con esa Cédula o ID ya existe.");}

        }
        else
        {
            ShowToast("Faltan Datos");
        }


    }

    public boolean estudianteRepetido(Alumno nuevoEstudiante)
    {
        Boolean repetido = false;




        try {

            if (DatosPrograma.ListaEstudiantes == null)
            {
                return  false;
            }


        }
        catch (Exception e)
        {
            ShowToast(e.toString());
            DatosPrograma.ListaEstudiantes = new Vector<Alumno>();
            DatosPrograma.ListaEstudiantes.add(nuevoEstudiante);
            return false;
        }

        if (DatosPrograma.ListaEstudiantes.isEmpty()) {
            DatosPrograma.ListaEstudiantes.add(nuevoEstudiante); return false;
        }
        else {

            for (Alumno alumno: DatosPrograma.ListaEstudiantes
            ) {

                if (alumno.NationalID.equals(nuevoEstudiante.NationalID))
                {
                    repetido = true;
                    break;
                }

            }

            if (!repetido) { DatosPrograma.ListaEstudiantes.add(nuevoEstudiante);}
        }


        return repetido;
    }

    public void ShowToast(String Message)
    {
        Toast warningToast = Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM,0,0);

        warningToast.show();
    }




    //My second activity :)

    //It grasps our XML viewpager
    //Then It assigns an instance of pageradapter to it


}