package Modelo;

import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Alumno {
    private int matricula;
    private String nombre;  
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int diaNac;
    private String mesNac;
    private int  añoNac;
    private String ciudadNac;
    private String estadoNac; 
    private String sexo;
    private String colonia;
    private String calle;   
    private int  numero;
    private String profesion;
    private String email;
    private String facebook;
    private String telefonoCasa;
    private String telefonoCelular;
    //hasta aqui datos alumno ordinario
    private String controlada;
    private String escuela;
    private String grado;
    private String estado;
    private int edad;

    public Alumno() {
        
    }
    public Alumno(ArrayList alDatos){
        this.nombre=alDatos.get(0).toString();
        this.apellidoPaterno=alDatos.get(1).toString();
        this.apellidoMaterno=alDatos.get(2).toString();
        this.sexo=alDatos.get(3).toString();
        this.diaNac=Integer.parseInt(alDatos.get(4).toString());
        this.mesNac=alDatos.get(5).toString();
        this.añoNac=Integer.parseInt(alDatos.get(6).toString());
        this.edad = Integer.parseInt(alDatos.get(7).toString());
        this.estadoNac =alDatos.get(8).toString();
        this.ciudadNac =alDatos.get(9).toString();
        this.calle= alDatos.get(10).toString();
        this.colonia=alDatos.get(11).toString();
        this.numero = Integer.parseInt(alDatos.get(12).toString());
        this.profesion= alDatos.get(13).toString();
        this.email = alDatos.get(14).toString();
        this.facebook = alDatos.get(15).toString();
        this.telefonoCasa =alDatos.get(16).toString();
        this.telefonoCelular = alDatos.get(17).toString();
        this.estado="ALTA";
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getDiaNac() {
        return diaNac;
    }

    public void setDiaNac(int diaNac) {
        this.diaNac = diaNac;
    }

    public String getMesNac() {
        return mesNac;
    }

    public void setMesNac(String mesNac) {
        this.mesNac = mesNac;
    }

    public int getAñoNac() {
        return añoNac;
    }

    public void setAñoNac(int añoNac) {
        this.añoNac = añoNac;
    }

    public String getCiudadNac() {
        return ciudadNac;
    }

    public void setCiudadNac(String ciudadNac) {
        this.ciudadNac = ciudadNac;
    }

    public String getEstadoNac() {
        return estadoNac;
    }

    public void setEstadoNac(String estadoNac) {
        this.estadoNac = estadoNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getControlada() {
        return controlada;
    }

    public void setControlada(String controlada) {
        this.controlada = controlada;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }   
}
