package com.example.control_de_notas;

import java.io.Serializable;
import java.util.Vector;

public class Programa implements Serializable {

    //Programa has a list of Students + a catalog of Subjects

    public Vector<Alumno> getListaEstudiantes() {
        return ListaEstudiantes;
    }

    public void setListaEstudiantes(Vector<Alumno> listaEstudiantes) {
        ListaEstudiantes = listaEstudiantes;
    }

    //We're gonna pass that through the set of activities where it will get updated
    //AS it goes through
    Vector<Alumno> ListaEstudiantes  ;

    public Vector<Materia> getListaMaterias() {
        return ListaMaterias;
    }

    public void setListaMaterias(Vector<Materia> listaMaterias) {
        ListaMaterias = listaMaterias;
    }

    Vector<Materia> ListaMaterias ;

    Programa(Vector<Materia> materiaVector, Vector<Alumno> alumnoVector)
    {
        ListaMaterias = materiaVector;
        ListaEstudiantes = alumnoVector;
    }

    Programa()
    {
        ListaMaterias = null;
        ListaEstudiantes = null;
    }
}
