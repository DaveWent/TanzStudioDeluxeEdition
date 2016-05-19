/**
 * InterfaceRecibeCursosEnBaja.java
 * Version 1
 * 31/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JComboBox;

/**
 * Clase que contiene los metodos utilizados por la interface en la vista
 * de la baja de cursos
 * @author David Omar Cabrera Bernal
 */
public interface ICargaCursos {
    /**
     * Método que recibe un jComboBox con los cursos con los cursos disponibles
     * y lo almacena
     * @param cbCursos 
     */
    public void recibeCursos( JComboBox cbCursos );
}
