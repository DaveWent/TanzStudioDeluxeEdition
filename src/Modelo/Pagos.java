/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;

/**
 *
 * @author Christian
 */
public class Pagos {
    
    private int idPago;
    private String tipo;
    private String fecha;
    private double abono;
    private String hora;
    
    public Pagos() {
        
    }

    public int getIdPago() {
        return idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public double getAbono() {
        return abono;
    }

    public String getHora() {
        return hora;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha( String fecha) {
        this.fecha = fecha;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
}
