/**
 * ControlUsuarios.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Controlador;

import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase donde se crean y utilizan los Querys para la base de datos
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class CUsuario extends ConexionBD {
    private static final String nombreUsuario = "nombre_U";
    private static final String contraseña = "contraseña";
    private static final String tipoUsuario = "tipo_EC";
    private static final String pregunta = "pregunta";
    private static final String respuesta = "respuesta";
    private static final String ejecuccion = "ejecuccion";
    private static final String insertar = "INSERT INTO usuario"
        + "(nombre_U,contraseña,tipo_EC,pregunta,respuesta) VALUES(?,?,?,?,?)";
    private static final String consulta = "SELECT * FROM usuario";
    private static final String baja = "DELETE FROM usuario WHERE nombre_U = ?";
    private static final String iniciarSesion = "SELECT * FROM usuario WHERE "
            + "nombre_U = ? AND contraseña=?";
    private static final String retornarTipo = "SELECT tipo_EC FROM usuario WHERE "
            + "nombre_U = ?";
    private static final String limpiarEjecuccionUsuario = "UPDATE usuario SET "
            + ejecuccion + "='NO' WHERE " + nombreUsuario + " = ?";
    private static final String asignarEjecuccion = "UPDATE usuario SET "
            + ejecuccion + "='SI' WHERE " + nombreUsuario + " = ?";
    private static final String detectarUsuario = " SELECT " + nombreUsuario 
            + " FROM usuario WHERE " + ejecuccion + "='SI'";
    
    public CUsuario(){
        super();
    }
    /**
     * Método que registra en la base de datos
     * @param usuario
     * @throws Exception 
     */
    public void alta(Usuario usuario) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(insertar);
        ps.setString(1, usuario.getNombreDeUsuario());
        ps.setString(2, usuario.getContraseña());
        ps.setString(3, usuario.getTipoDeUsuario());
        ps.setString(4, usuario.getPregunta());
        ps.setString(5, usuario.getRespuesta());
        ps.executeUpdate();
        cerrar(ps);
    }
    /**
     * Método para consultar en la base de datos
     * @return
     * @throws Exception 
     */
    public ArrayList<Usuario> Consultas() throws Exception {
    //Los Resulset son los encargados de almacenar el resultado de la consulta a la base de datos
        ResultSet rs;
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(consulta);
        //Ejecuta la sentencia ps para obtener el total de filas en la bd
        rs = ps.executeQuery();
            while (rs.next()) {
                 resultado.add(getUsuario(rs));
               }
            return resultado;
    }
    /**
     * Método para obtener los datos del usuario en la base de datos
     * @param rs
     * @return
     * @throws Exception 
     */
    private Usuario getUsuario( ResultSet rs ) throws Exception {
        Usuario datosUsuario = new Usuario();
        datosUsuario.setNombreDeUsuario(rs.getString("nombre_U"));        
        return datosUsuario;
    }          
    /**
     * Método para eliminar un usuario de la base de datos
     * @param id
     * @throws Exception 
     */
    public void baja(String id) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(baja);
        //Ejecuta la sentencia ps para obtener el total de filas en la bd
        ps.setString(1,id);
        ps.execute();
        cerrar(ps);
}
        
    /**
     * ´Método para iniciar sesión
     * @param nombre
     * @param contraseña
     * @return
     * @throws SQLException 
     */
    public String iniciarSesion ( String nombre, String contraseña ) throws SQLException {
        ResultSet rs;
        String nombreUsuario = "";
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( iniciarSesion );
        ps.setString( 1, nombre );
        ps.setString( 2, contraseña );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            nombreUsuario = rs.getString( "nombre_U" );
        }
        return nombreUsuario;
    }
 
    /**
     * Método para validar si un usuario es un administrador principal
     * @param nombreUsuario
     * @return
     * @throws SQLException 
     */
    public boolean validarAdminPrincipal( String nombreUsuario ) throws SQLException {
        boolean tipo = false;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( retornarTipo );
        ps.setString( 1, nombreUsuario );
        rs = ps.executeQuery();
        while( rs.next() ) {
            String admin = rs.getString( "tipo_EC" );
            if( admin.equalsIgnoreCase( "administradorPrincipal" ) ) {
                tipo=true;
            }
        }
        return tipo;
    }
    
    public void limpiarEjecuccionUsuario( String nombreUsuario ) 
            throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( limpiarEjecuccionUsuario );
        ps.setString( 1, nombreUsuario );
        ps.execute();
        cerrar( ps );
    }
    
    public void asignarEjecuccionUsuario( String nombreUsuario ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( asignarEjecuccion );
        ps.setString( 1, nombreUsuario );
        ps.execute();
        cerrar( ps );
    }
    
    public String detectarUsuario () throws SQLException {
        String usuario = null;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( detectarUsuario );
        System.out.println( ps );
        rs = ps.executeQuery();
        while ( rs.next() ) {
            usuario = rs.getString( nombreUsuario );
        }
        return usuario;
    }
}


   

