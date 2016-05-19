/**
 * ControlHistorialCambios.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Controlador;

import Modelo.HistorialCambios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase donde se realiza los metodos que gestionan el historial de cambios
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class CHistorialCambios extends ConexionBD{
    
    //Variables que guardan los querys de la gestión de la información del
    //historialde cambios
    private static final String agregarRelacion = "INSERT INTO haceuncambio "
            + "(nombre_U, id_Cambio) VALUES(?, ?);";
    private static final String altaHistorial = "INSERT INTO historialcambios "
            + "(mantenimiento, accion, fecha_H, hora_H) VALUES( ?, ?, "
            + "CURDATE(), CURTIME() )";
    private static final String consultaHistorial = "SELECT * FROM historialcambios;";
    private static final String ultimoIdHistorial = "SELECT MAX(id_Cambio) "
            + "FROM historialcambios";
    
    /**
     * Método que permite realizar una alta en el historial de cambios
     * @param historial
     * @throws SQLException
     * @throws Exception 
     */
    public void altaEnHistorialCambios( HistorialCambios historial ) 
            throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( altaHistorial );
        ps.setString(1, historial.getMantenimiento() );
        ps.setString(2, historial.getAccion() );
        ps.executeUpdate();
        cerrar(ps);
    }
    
    /**
     * Método que retorna el id del último historial de cambios registrado
     * @return
     * @throws SQLException 
     */
    public int ultimoId() throws SQLException {
        int id = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( ultimoIdHistorial );
        //Ejecuta la sentencia ps para obtener el total de filas en la bd
        rs = ps.executeQuery();
        while (rs.next()) {
            id = rs.getInt( "MAX(id_Cambio)" );
        }
        return id;
    }
    
    /**
     * Método que agrega una relación entre el usuario que realizo un cambio
     * y el cambio que realizo
     * @param nombre
     * @param id
     * @throws Exception 
     */
    public void agregarUnaRelacion( String nombre, int id ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( agregarRelacion );
        ps.setString(1, nombre );
        ps.setInt(2, id );
        ps.executeUpdate();
        cerrar(ps);
    }
    
    /**
     * Método que captura y retorna todo el historial de cambios realizado
     * @return
     * @throws SQLException 
     */
    public ArrayList<HistorialCambios> consultaHistorialCambios() throws SQLException {
        ArrayList<HistorialCambios> historiales = new ArrayList<HistorialCambios>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaHistorial );
        rs = ps.executeQuery();
        while (rs.next()) {
            HistorialCambios historial = new HistorialCambios();
            historial.setIdCambio( rs.getInt( "id_Cambio" ) );
            historial.setMantenimiento( rs.getString( "mantenimiento" ) );
            historial.setAccion( rs.getString( "accion" ) );
            historial.setFecha( rs.getString( "fecha_H" ) );
            historial.setHora( rs.getString( "hora_H" ) );
            historiales.add( historial );
        }
        return historiales;
    }
    
}
