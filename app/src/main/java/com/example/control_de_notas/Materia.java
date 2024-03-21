package com.example.control_de_notas;

import java.io.Serializable;
import java.util.Vector;

public class Materia implements Serializable {

    Vector<Nota> Notas = new Vector<Nota>();

    String NombreMateria;

    int NumeroNotas;






    Materia(Vector<Nota> notas, String nombreMateria, int numeroNotas)
    {
        this.Notas = notas;
        this.NombreMateria = nombreMateria;
        this.NumeroNotas = numeroNotas;


        //Cuando se crea materia se inicializa qué?

    }


}
