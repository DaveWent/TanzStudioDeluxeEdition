/**
 * InterfaceRecibeDatosEnModificacion.java
 * Version 1
 * 31/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados para la vista de la modificacion de
 * un curso en específico
 * @author David Omar Cabrera Bernal
 */
public interface IModificacionCurso {
    /**
     * Método que crea la ventana con los datos de un curso en específico
     * @param curso 
     */
    public void creaVentanaModificacion( String[] curso );
    /**
     * Método que carga los profesores almacenadosen un jComboBox
     * @param cbProfesores 
     */
    public void recibeProfesoresEnComboBox( JComboBox cbProfesores );
    /**
     * Método que carga los profesores de un curso en una tabla
     * @param tbProfesores 
     */
    public void recibeProfesoresEnTabla( JTable tbProfesores );
    /**
     * Método que actualiza los profesores de un curso en una tabla
     * @param tbProfesores 
     */
    public void actualizaTabla( JTable tbProfesores );
}
