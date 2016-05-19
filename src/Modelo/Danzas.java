/**
 * Danzas.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Modelo;

/**
 * Clase que contiene todos los datos de una danza
 * @author David Omar Cabrera Bernal
 */
public class Danzas {
    private int idDanza;    //Variable que guarda el id de la danza
    private String danza;   //Variable que guarda el nombre de la danza
    
    /**
     * Constructor vacio de la clase
     */
    public Danzas(  ) {
        
    }
    
    /**
     * Constructor que inicializa el nombre de la danza
     * @param danza 
     */
    public Danzas( String danza ) {
        this.danza = danza;
    }
    
    /**
     * Método que retorna el id de la danza
     * @return 
     */
    public int getIdDanza() {
        return idDanza;
    }
    
    /**
     * Metodo que guarda el id de la danza
     * @param idDanza 
     */
    public void setIdDanza( int idDanza ) {
        this.idDanza = idDanza;
    }
    
    /**
     * Método que retorna el nombre de la danza
     * @return 
     */
    public String getDanza() {
        return danza;
    }
    
    /**
     * Metodo que almacena el nombre de la danza
     * @param danza 
     */
    public void setDanza( String danza ) {
        this.danza = danza;
    }
}
