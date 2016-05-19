/**
 * InterfaceRecibeDatosHorario.java
 * Version 1
 * 31/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados para la vista del horario de los
 * cursos con sus clases
 * @author David Omar Cabrera Bernal
 */
public interface IHorario {
    /**
     * Método que carga todos los cursos con sus clases en un horario, contenida 
     * en una tabla
     * @param tbHorario 
     */
    public void recibeCursosYClases( JTable tbHorario );
    /**
     * Método que carga los salones que se encuentran en la institucion
     * @param numSalones 
     */
    public void recibeSalones( int numSalones );
}
