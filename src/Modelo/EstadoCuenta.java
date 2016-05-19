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
public class EstadoCuenta {
    private int idCuenta;
    private String tipo;
    private double saldo;
    private String fecha;
    private double Costo_EC;
    private double multa;
    
    public EstadoCuenta() {
        
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getFecha() {
        return fecha;
    }
    
    public double getCosto_EC() {
        return Costo_EC;
    }

    public double getMulta() {
        return multa;
    }
        public void setCosto_EC(Double Costo_EC){
        this.Costo_EC = Costo_EC;
        }
        
        public void setMulta(Double multa){
        this.multa = multa;
        }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
