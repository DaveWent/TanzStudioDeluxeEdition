/**
 * Curso.java
 * Version 2
 * 04/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Modelo;

/**
 * Clase que contiene todos los datos del curso
 * @author David Omar Cabrera Bernal
 */
public class Curso {
    
    private String nombreCurso;      //Variable que guarda el nombre del curso
    private String tipo;             //Variable que guarda el tipo del curso
    private String requiereSilencio; //Variable que guarda el requerimiento de silencio
    private String fechaInicio;      //Variable que guarda la fecha de inicio del curso
    private String fechaFin;         //Variable que guarda la fecha de fin del curso
    
    /**
     * Constructor que inicializa la clase curso
     */
    public Curso() {
        
    }
    
    /**
     * Constructor que inicializa todos los atibutos del curso
     * @param nombreCurso
     * @param tipo
     * @param requiereSilencio
     * @param fechaInicio
     * @param fechaFin 
     */
    public Curso ( String nombreCurso, String tipo, String requiereSilencio,
            String fechaInicio, String fechaFin ) {
        this.nombreCurso = nombreCurso;
        this.tipo = tipo;
        this.requiereSilencio = requiereSilencio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    /**
     * Método que retorna el nombre del curso
     * @return nombreCurso
     */
    public String getNombreCurso() {
        return nombreCurso;
    }
    
    /**
     * Método que retorna el tipo de curso
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Método que retorna el requerimiento de silencio
     * @return requiereSilencio
     */
    public String getRequiereSilencio() {
        return requiereSilencio;
    }
    
    /**
     * Método que retorna la fecha de inicio del curso
     * @return fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * Método que retorna la fecha de fin del curso
     * @return fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }
    
    /**
     * Método que almacena el nombre del curso
     * @param nombreCurso 
     */
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    /**
     * Método que almacena el tipo del curso
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que almacena el requerimiento de silencio del curso
     * @param requiereSilencio 
     */
    public void setRequiereSilencio(String requiereSilencio) {
        this.requiereSilencio = requiereSilencio;
    }

    /**
     * Método que almacena la fecha de inicio de del curso
     * @param fechaInicio 
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Método que almacena la fecha de fin del curso
     * @param fechaFin 
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
