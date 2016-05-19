/**
 * IAltaProfesor.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados para la vista de alta de profesor
 * @author David Omar Cabrera Bernal
 */
public interface IAltaProfesor {
    /**
     * Método que permite actualizarla tabla de danzas conocidas de un profesor
     * @param tbDanzas 
     */
    public void actualizaTablaDanzas( JTable tbDanzas );
    /**
     * Método que permite cargar un número que puede tener un profesor
     * al ser registrado
     * @param numero 
     */
    public void cargaNuevoNumero( int numero );
}
