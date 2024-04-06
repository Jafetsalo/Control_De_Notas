package com.example.control_de_notas;
//import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Vector;


public class BaseActivity extends AppCompatActivity {
    public void ShowToast(String message) {
        Toast warningToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM, 0, 0);
        warningToast.show();
    }


    Programa DatosPrograma = new Programa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Programa passedPrograma = (Programa)getIntent().getSerializableExtra("DatosPrograma");
        if (passedPrograma != null) {
            DatosPrograma = passedPrograma;
        }
    }


    @FunctionalInterface
    public interface PropertyGetter<T> {
        String getProperty(T t);
    }

    // Generic method to create an array from a Vector of any type
    public static <T> String[] crearArreglo(@NonNull Vector<T> lista, PropertyGetter<T> getter) {
        int size = lista.size();
        String[] arreglo = new String[size];
        int x = 0;
        for (T item : lista) {
            if (x == size) { break; }
            arreglo[x] = (x + 1) + " : " + getter.getProperty(item);
            x++;
        }
        return arreglo;
    }

    //Copiemos aquí los métodos que queremos para las clases de activity y no tener que repetirlos


}
