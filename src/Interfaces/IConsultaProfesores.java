/**
 * IRecibeDatos.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados para la actualizacion de una tabla
 * profesores
 * @author David Omar Cabrera Bernal
 */
public interface IConsultaProfesores {
    
    /**
     * Método que permite actualizar la tabla de profesores
     * @param ConsultasJTable 
     */
    public void recibeDatos( JTable ConsultasJTable );
}
