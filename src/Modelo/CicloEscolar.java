/**
 * CicloEscolar.java
 * Version 2
 * 04/01/2016
 * Copyright (c) 
 * All rights reserved
 */
package Modelo;

/**
 * Clase que contiene todos los datos del ciclo escolar
 * @author Cristian Millan Ruiz
 */
public class CicloEscolar {
    
    private String nombreCicloEscolar;  //Variable que guarda el nombre del ciclo escolar
    private java.util.Date fechaInicio; //Vairable que guarda la fecha de incio del ciclo
    private java.util.Date fechaFin;    //Variable que guarda la fecha de fin del ciclo
    
    /**
     *  Constructor que inicializa el ciclo escolar
     */
    public void cicloEscolar() {
        
    }
    
    /**
     * Constructor que inicializa los atributos del ciclo escolar
     * @param nombreCicloEscolar
     * @param fechaInicio
     * @param fechaFin 
     */
    public void cicloEscolar( String nombreCicloEscolar, java.util.Date fechaInicio, java.util.Date fechaFin ) {
        this.nombreCicloEscolar = nombreCicloEscolar;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    /**
     * Metodo que retorna el nombre del ciclo escolar
     * @return 
     */
    public String getNombreCicloEscolar() {
        return nombreCicloEscolar;
    }
    
    /**
     * Metodo que retorna la fecha de inicio del ciclo escolar
     * @return fechaInicio
     */
    public java.util.Date getFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * Metodo que retorna la fecha de fin del ciclo escolar
     * @return 
     */
    public java.util.Date getFechaFin() {
        return fechaFin;
    }
    
    /**
     * Metodo que alamcena el nombre del ciclo escolar
     * @param nombreCicloEscolar 
     */
    public void setNombreCicloEscolar( String nombreCicloEscolar ) {
        this.nombreCicloEscolar = nombreCicloEscolar;
    }
    /**
     * Metodo que almacena la fecha de inicio del ciclo escolar
     * @param fechaInicio 
     */
    public void setFechaInicio( java.util.Date fechaInicio ) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Meotodo que almacena la fecha de fin del ciclo escolar
     * @param fechaFin 
     */
    public void setFechaFin( java.util.Date fechaFin ) {
        this.fechaFin = fechaFin;
    }
    public boolean isIniciado(){
        java.util.Date hoy = new java.util.Date();
        if(fechaInicio.after(hoy)){
            return false;
        }else{
            return true;
        }
    }
    
}
