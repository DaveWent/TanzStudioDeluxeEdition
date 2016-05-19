/**
 * HistorialDeCambios.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Modelo;

/**
 * Clase modelo para el historial de cambios
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class HistorialCambios {
    
    private int idCambio;
    private String usuario;
    private String mantenimiento;
    private String accion;
    private String fecha;
    private String hora;
    
    /**
     * Constructor que inicializa el Historial de cambios
     */
    public HistorialCambios() {}
    
    /**
     * Método que retorna el nombre de usuario
     * @return 
     */
    public String getUsuario(){
        return usuario;
    }
    /**
     * Método que almacena el nombre de usuario 
     */
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public String getMantenimiento(){
        return mantenimiento;
    }
        
    public void setMantenimiento(String mantenimiento){
        this.mantenimiento = mantenimiento;
    }
    public String getAccion(){
        return accion;
    }
        
    public void setAccion(String accion){
        this.accion = accion;
    }
    public String getFecha(){
        return fecha;
    }
        
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public String getHora(){
        return hora;
    }
        
    public void setHora(String hora){
        this.hora = hora;
    }

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }
    
}
