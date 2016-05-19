package Modelo;

import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Progenitor{
    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int diaNac;
    private String mesNac;
    private int añoNac;
    private String profesion;
    private String telefonoCelular;
    private String tipo;
    private String personaConfianza;
    
    public Progenitor(){
        
    }

    public Progenitor(ArrayList datos) {
        this.nombre=datos.get(0).toString();
        this.apellidoPaterno=datos.get(1).toString();
        this.apellidoMaterno=datos.get(2).toString();
        this.diaNac=Integer.parseInt(datos.get(3).toString());
        this.mesNac=datos.get(4).toString();
        this.añoNac=Integer.parseInt(datos.get(5).toString());
        this.profesion=datos.get(6).toString();
        this.telefonoCelular=datos.get(7).toString();
        this.personaConfianza=datos.get(8).toString();
        this.tipo=datos.get(9).toString();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public int getDiaNac() {
        return diaNac;
    }

    public String getMesNac() {
        return mesNac;
    }

    public int getAñoNac() {
        return añoNac;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPersonaConfianza() {
        return personaConfianza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno( String apellidoPaterno ) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno( String apellidoMaterno ) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setDiaNac( int diaNac ) {
        this.diaNac = diaNac;
    }

    public void setMesNac( String mesNac ) {
        this.mesNac = mesNac;
    }

    public void setAñoNac( int añoNac ) {
        this.añoNac = añoNac;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPersonaConfianza(String personaConfianza) {
        this.personaConfianza = personaConfianza;
    }
    
    public String toString() {
        return "ProgenitorDTO{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", diaNac=" + diaNac + ", mesNac=" + mesNac + ", a\u00f1oNac=" + añoNac + ", profesion=" + profesion + ", telefonoCelular=" + telefonoCelular + ", tipo=" + tipo + ", personaConfianza=" + personaConfianza + ", id=" + id + '}';
    }
    
}
