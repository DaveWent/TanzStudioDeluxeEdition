/**
 * Clase.java
 * Version 2
 * 04/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Modelo;

/**
 * Clase que contiene todos los datos de la clase
 * @author David Omar Cabrera Bernal
 */
public class Clase {
    
    private int idClase;        //Variable que guarda el id de la clase
    private String dia;         //Variable que guarda el dia de la clase
    private String salon;       //Variable que guarda el salón de la clase
    private String horaInicio;  //Variable que guarda la hora de inicio de la clase
    private String horaFin;     //Vairable que guarda la hora de fin de clase

    /**
     * Métodoque retorna el id de la clase
     * @return idClase 
     */
    public int getIdClase() {
        return idClase;
    }

    /**
     * Método que retorna el dia de la clase
     * @return 
     */
    public String getDia() {
        return dia;
    }
    
    /**
     * Método que retorna el salon de la clase
     * @return salon
     */
    public String getSalon() {
        return salon;
    }

    /**
     * Método que retorna la hora de inicio de la clase
     * @return horaInicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }
    
    /**
     * Método que retorna la hora de fin de la clase
     * @return 
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Método que almacena el id de la clase
     * @param idClase 
     */
    public void setIdClase( int idClase) {
        this.idClase = idClase;
    }
    
    /**
     * Método que almacena el dia de la clase
     * @param dia 
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Método que almacena el salon de la clase
     * @param salon 
     */
    public void setSalon(String salon) {
        this.salon = salon;
    }
    
    /**
     * Método que almacena la hora de inicio de la clase
     * @param horaInicio 
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Método que almacena la hora de fin de la clase
     * @param horaFin 
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    
    
}
