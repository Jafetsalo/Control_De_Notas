package com.example.control_de_notas;



import java.io.Serializable;

public class Nota implements Serializable {

    float Valor;
    float Peso;

    public String getNombreActividad() {
        return NombreActividad;
    }

    String NombreActividad;

    Nota(float valor, float peso, String nombreActividad)
    {
        this.Valor = valor;
        this.Peso = peso;
        this.NombreActividad = nombreActividad;
    }


}

//Seems to be done!