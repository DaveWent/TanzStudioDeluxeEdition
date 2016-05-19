/**
 * Profesor.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Modelo;

import java.io.Serializable;

/**
 * Clase que contiene todos los datos de un profesor
 * @author David Omar Cabrera Bernal
 */
public class Profesor implements Serializable {
    private int idProfesor;         //Variable que guarda el id del profesor
    private String nombre;          //Variable que guarda el nombre del profesor
    private String apellidoPaterno; //Variable que guarda el apellido paterno del profesor
    private String apellidoMaterno; //Variable que guarda el apellido materno del profesor
    private String telefonoCasa;    //Variable que guarda el tefono de casa
    private String telefonoCel;     //Variable que guarda el telefono celular
    private String email;           //variable que guarda el correo electronico
    private String facebook;        //Variable que guarda la cuenta de facebook
    
    /**
     * Constructor vacio que inicializa la clase
     */
    public Profesor( ) {
        
    }
    
    /**
     * Constructor que inicializa los datos del profesor
     * @param idProfesor
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param telefonoCasa
     * @param telefonoCel
     * @param email
     * @param facebook 
     */
    public Profesor( int idProfesor, String nombre, String apellidoPaterno, 
        String apellidoMaterno, String telefonoCasa, String telefonoCel, 
        String email, String facebook) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCel = telefonoCel;
        this.email = email;
        this.facebook = facebook;
    }
    
    /**
     * Constructor que inicializa los datos del profesor
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param telefonoCasa
     * @param telefonoCel
     * @param email
     * @param facebook 
     */
    public Profesor( String nombre, String apellidoPaterno, String apellidoMaterno,
        String telefonoCasa, String telefonoCel, String email, String facebook) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCel = telefonoCel;
        this.email = email;
        this.facebook = facebook;
    }
    
    /**
     * Método que retorn el id del profesor
     * @return idProfesor
     */
    public int getIdProfesor() {
        return idProfesor;
    }
    
    /**
     * Método que almacena el id del profesor
     * @param idProfesor 
     */
    public void setIdProfesor( int idProfesor ) {
        this.idProfesor = idProfesor;
    }
    
    /**
     * Método que retorna el nombre del profesor
     * @return nombre 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que almacena el nombre del profesor
     * @param nombre 
     */
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    
    /**
     * Método que retorna el apellido paterno del profesor
     * @return apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    
    /**
     * Método que almacena el apellido paterno al profesor
     * @param apellidoPaterno 
     */
    public void setApellidoPaterno( String apellidoPaterno ) {
        this.apellidoPaterno = apellidoPaterno;
    }
    
    /**
     * Método que retorna el apellido materno del profesor
     * @return apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    
    /**
     * Método que almacena el apellido materno del profesor
     * @param apellidoMaterno 
     */
    public void setApellidoMaterno( String apellidoMaterno ) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Método que retorna el telefono de casa del profesor
     * @return telefonoCasa
     */
    public String getTelefonoCasa() {
        return telefonoCasa;
    }
    
    /**
     * Método que almacena el telefono de casa del profesor
     * @param telefonoCasa 
     */
    public void setTelefonoCasa( String telefonoCasa ) {
        this.telefonoCasa = telefonoCasa;
    }
    
    /**
     * Metodo que retorna el telefono celular del profesor
     * @return telefonoCel
     */
    public String getTelefonoCel() {
        return telefonoCel;
    }
    
    /**
     * Método que almacena el telefono celular del profesor
     * @param telefonoCel 
     */
    public void setTelefonoCel( String telefonoCel ) {
        this.telefonoCel = telefonoCel;
    }
    
    /**
     * Método que retorna el email del profesor
     * @return email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Método que almacena el email del profesor
     * @param email 
     */
    public void setEmail( String email ) {
        this.email = email;
    }
    
    /**
     * Método que retorna la cuenta de facebook del profesor
     * @return facebook
     */
    public String getFacebook() {
        return facebook;
    }
    
    /**
     * Método que almacena la cuenta del facebook del profesor
     * @param facebook 
     */
    public void setFacebook( String facebook ) {
        this.facebook = facebook;
    }
}
