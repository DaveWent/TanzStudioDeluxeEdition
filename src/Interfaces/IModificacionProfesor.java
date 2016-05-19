/**
 * IRecibeDatosParaModificacion.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados para la interface de modificación
 * de profesores
 * @author David Omar Cabrera Bernal
 */
public interface IModificacionProfesor {
    
    /**
     * Metodo que actualiza los datos del profesor en la vista de modificación
     * de profesores
     * @param datos
     * @param danzasConocidas
     * @param cursos
     * @param cursosJComboBox 
     */
    public void recibeDatosParaModificacion( String[] datos, String[] danzasConocidas, 
            String[] cursos, JComboBox cursosJComboBox );
    /**
     * Método que permite actualizar en pantalla los cursos que imparte el profesor
     * en una tabla
     * @param cursosJTable 
     */
    public void recibeTablaCursos( JTable cursosJTable );
}
