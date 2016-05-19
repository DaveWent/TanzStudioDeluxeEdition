/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author cristian
 */
public class RenglonAlumno {
    private int matricula;
    private String nombre;
    private boolean[] asistencia;

    public RenglonAlumno() {
    }
    
   
   public RenglonAlumno(int matricula,String nombre,boolean[] asistencia){
       this.matricula=matricula;
       this.nombre=nombre;
       this.asistencia=asistencia;
   }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean[] getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean[] asistencia) {
        this.asistencia = asistencia;
    }   
}
