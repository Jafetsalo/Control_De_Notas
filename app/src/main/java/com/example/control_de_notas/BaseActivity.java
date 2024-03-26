package com.example.control_de_notas;
//import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


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

    //Copiemos aquí los métodos que queremos para las clases de activity y no tener que repetirlos


}
