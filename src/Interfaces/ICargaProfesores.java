/**
 * InterfaceRecibeProfesoresEnAltaCurso.java
 * Version 1
 * 31/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados para la vista de la alta de un curso
 * @author David Omar Cabrera Bernal
 */
public interface ICargaProfesores {
    /**
     * Método que carga los profesores de la institución en jComboBox
     * @param cbProfesor 
     */
    public void actualizaProfesores( JComboBox cbProfesor );
    public void insertarProfesoresEnTabla( JTable tbProfesores );
}
