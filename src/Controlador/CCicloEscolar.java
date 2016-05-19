/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CicloEscolar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dave Went
 */
public class CCicloEscolar extends ConexionBD{
    
    private static final String nombreCicloEscolar = "nombre_CE";
    private static final String fechaInicio = "fechaInicio";
    private static final String fechaFin ="fechaFin";
    private static final String consultaCicloEscolar = "SELECT *FROM tanzstudiodb.cicloescolar where fechaFin=(select MAX(fechaFin) from cicloescolar)";
    /**
     * SQL_INSERT permite hacer la inserción de un ciclo escolar a la BD
     */
    private static final String SQL_INSERT =
            "INSERT INTO cicloescolar(nombre_CE, fechaInicio, fechaFin) VALUES(?,?,?)";
    /**
     * SQL_SELECT permite obtener el rango de fechas de el ciclo escolar definido
     */
    private static final String SQL_SELECT =
            "SELECT * FROM cicloescolar WHERE nombre_CE = ?";
    /**
     * SQL_UPDATE permite actualizar el ciclo escolar definido con nuevas fechas
     */
    private static final String SQL_UPDATE =
            "UPDATE cicloescolar SET fechaInicio = ?, fechaFin = ? WHERE nombre_CE = ?";
    
    public CicloEscolar obtenerCicloEscolarActual() throws SQLException {
        CicloEscolar cicloEscolar = new CicloEscolar();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaCicloEscolar );
        rs = ps.executeQuery();
        
        int tamanoConsulta = 0;
        while ( rs.next() ) {
            tamanoConsulta ++;
        }
        
        rs = ps.executeQuery();
        int contador = 0;
        if ( rs.next() ) {
            cicloEscolar= getObject(rs);
        }
        return cicloEscolar;
    }
    public CicloEscolar getObject(ResultSet rs) throws SQLException{
        CicloEscolar cicloEscolar = new CicloEscolar();
        cicloEscolar.setNombreCicloEscolar(rs.getString(nombreCicloEscolar));
        cicloEscolar.setFechaInicio(rs.getDate(fechaInicio));
        cicloEscolar.setFechaFin(rs.getDate(fechaFin));
        return cicloEscolar;
    }
    /**
     * Permite validar que el rango de las fechas sea correcto
     * @param d1 es la fecha de inicio
     * @param d2 es la fecha de fin
     * @return true si queda dentro del rango correcto de fechas
     */
    public boolean rangoFechasCorrecto(java.util.Date d1, java.util.Date d2){
        java.util.Calendar d1Cal = Calendar.getInstance();
        d1Cal.setTime(d1);
        // Convertimos todo el tiempo a 12:00:00'00
        d1Cal.set(java.util.Calendar.HOUR, 12);
        d1Cal.set(java.util.Calendar.MINUTE, 0);
        d1Cal.set(java.util.Calendar.SECOND, 0);
        d1Cal.set(java.util.Calendar.MILLISECOND, 0);
        java.util.Calendar d2Cal = Calendar.getInstance();
        d2Cal.setTime(d2);
        // Igual, Convertimos todo el tiempo a 12:00:00'00
        d2Cal.set(java.util.Calendar.HOUR, 12);
        d2Cal.set(java.util.Calendar.MINUTE, 0);
        d2Cal.set(java.util.Calendar.SECOND, 0);
        d2Cal.set(java.util.Calendar.MILLISECOND, 0);
        java.util.Calendar fechaHoy = Calendar.getInstance();
        // La fecha de hoy siempre está por default a las 12 y no se puede cambiar
        fechaHoy.set(java.util.Calendar.HOUR, 0);
        fechaHoy.set(java.util.Calendar.MINUTE, 0);
        fechaHoy.set(java.util.Calendar.SECOND, 0);
        fechaHoy.set(java.util.Calendar.MILLISECOND, 0);
        
        if(d1Cal.equals(fechaHoy)){
            if(d2Cal.before(d1Cal)){
                return false;
            }else{
                return true;
            }
        }else{
            if(d1Cal.before(fechaHoy)){
                return false;
            }else{
                if(d2Cal.before(d1Cal)){
                    return false;
                }else{
                    return true;
                }
            }
        }
    }
    
    /**
     * Permite convertir la fecha a java.sql.Date para su almacenamiento en la BD
     * @param fechaJava es la fecha en tipo java.util.Date
     * @return dicha fecha convertida a java.sql.Date con tiempo de 00:00:00'00
     */
    public java.sql.Date convertirFecha(java.util.Date fechaJava){
        // Convertimos fechaJava de Date a Calendar
        java.util.Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fechaJava);
        // Convertimos todo el tiempo a 00:00:00'00
        fechaCalendar.set(java.util.Calendar.HOUR, 0);
        fechaCalendar.set(java.util.Calendar.MINUTE, 0);
        fechaCalendar.set(java.util.Calendar.SECOND, 0);
        fechaCalendar.set(java.util.Calendar.MILLISECOND, 0);
        // Pasamos el valor de fechaCalendar a fechaSQL
        java.sql.Date fechaSql = new java.sql.Date(fechaCalendar.getTimeInMillis());
        return fechaSql;
    }
    
    /**
     * Permite la creación de un ciclo escolar en la BD
     * @param fechaInico es la fecha de inicio del ciclo
     * @param fechaFin es la fecha de fin del ciclo
     * @return 0 si es exitoso, 1 si las fechas están en rango equivocado, 2 si
     * las fechas están vacías.
     * @throws Exception 
     */
    public void Alta(Date fechaInico, Date fechaFin,String nombreCiclo) throws Exception{
        
                java.sql.Date fecha1 = convertirFecha(fechaInico);
                java.sql.Date fecha2 = convertirFecha(fechaFin);                
                PreparedStatement ps = null;
                System.out.println(ps);
                ps = conexion.prepareStatement(SQL_INSERT);
                // Se le agregan al query los parametros necesarios
                ps.setString(1, nombreCiclo);
                ps.setDate(2, fecha1);
                ps.setDate(3, fecha2);
                ps.executeUpdate();
                cerrar(ps);
                
    }
    
    /**
     * Permite verificar que tipo de ciclo escolar es.
     * Si es antes del 1º de Julio, es [AÑO]-1
     * de lo contrario, es [AÑO]-2
     * @param fecha es la fecha de inicio del ciclo escolar
     * @return "1" si el ciclo comienza antes del 1º de Julio, "2" si no.
     */
    public String tipoDeCiclo(java.util.Date fecha){
        String tipo="";
        String año =new SimpleDateFormat("yyyy").format(fecha);
        java.util.Date comparable = new java.util.Date();
        comparable.setMonth(7);
        comparable.setDate(1);
        if(fecha.before(comparable)){
            tipo="1";
        }else{
            tipo="2";
        }
        return "Ciclo"+año+"-"+tipo;
    }
    
    /**
     * Obtiene el rango de fechas del ciclo escolar actual
     * @return un arreglo con 2 fechas, la de inicio del ciclo y la del fin del ciclo
     * puede retornarse vacío si no se encuentra un ciclo
     * @throws Exception 
     */
    public java.util.Date[] getCiclo() throws Exception{
        java.sql.ResultSet rs;
        // nomCiclo contiene el nombre del ciclo
        String nomCiclo = new java.sql.Date(new java.util.Date().getTime()).toString().split("-")[0];
        // se le agrega el 1 o 2 al nombre
        nomCiclo += ("-"+tipoDeCiclo(new java.util.Date()));
        PreparedStatement ps = conexion.prepareStatement(SQL_SELECT);
        // Se le agrega al query el nombre del ciclo
        ps.setString(1, nomCiclo);
        rs = ps.executeQuery();
        rs.next();
        java.util.Date[] fechas = new java.util.Date[2];
        try{
            if(rs.getDate("fechaInicio")==null || rs.getDate("fechaFin")==null){
                fechas = null;
            }else{
                fechas[0] = rs.getDate("fechaInicio");
                fechas[1] = rs.getDate("fechaFin");
            }
        }catch(NullPointerException ex){
            fechas = null;
        }
        return fechas;
    }
    
    /**
     * Permite modificar el ciclo escolar que se encuenta actualmente vigente
     * @param d1 es la nueva fecha de inicio del ciclo
     * @param d2 es la nueva fecha de fin del ciclo
     * @return 0 si es exitoso, 1 si las fechas están en rango equivocado, 2 si
     * las fechas están vacías.
     * @throws Exception 
     */
    public void modificarCiclo(java.util.Date d1, java.util.Date d2) throws Exception {
        /*java.util.Date[] cicloAnterior = getCiclo();*/
      
                java.sql.Date fecha1 = convertirFecha(d1);
                java.sql.Date fecha2 = convertirFecha(d2);
                String nombreCiclo = obtenerCicloEscolarActual().getNombreCicloEscolar();
                PreparedStatement ps = null;
                ps = conexion.prepareStatement(SQL_UPDATE);
                // Se le agregan al query los parametros necesarios
                ps.setDate(1, fecha1);
                ps.setDate(2, fecha2);
                ps.setString(3, nombreCiclo);
                ps.executeUpdate();
                cerrar(ps);
        
    }
}
