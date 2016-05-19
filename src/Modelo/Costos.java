/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Christian
 */
public class Costos {
    
    
    private int idCostos;
    private String nombreCos;
    private double costos;
    
        public Costos(String nombreCos, Double costos){
    
        this.nombreCos = nombreCos;
        this.costos = costos;
    }
        public Costos(int idCostos, Double costos){
        this.idCostos = idCostos;
        this.costos = costos;
        }
        
        public Costos() {
            
        }
    
    public int getIdCostos() {
        return idCostos;
    }

    public void setIdCostos(int idCostos) {
        this.idCostos = idCostos;
    }
    
     public String getNombreCos() {
        return nombreCos;
    }

    public void setNombreCos(String nombreCos) {
        this.nombreCos = nombreCos;
    }
    
     public double getCostos() {
        return costos;
    }

    public void setCostos(double costos) {
        this.costos = costos;
    }
    
}

