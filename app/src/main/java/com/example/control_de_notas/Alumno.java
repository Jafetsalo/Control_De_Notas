package com.example.control_de_notas;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

public class Alumno implements Serializable {

    String NombreAlumno;
    BigInteger NationalID;
    Vector<Materia> Materias = new Vector<Materia>();

    Alumno(String nombreAlumno, Vector<Materia> materias, BigInteger nationalID)
    {
        NombreAlumno = nombreAlumno;
        Materias = materias;
        NationalID = nationalID;

    }


}
