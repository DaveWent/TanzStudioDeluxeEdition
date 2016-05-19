/**
 * ConexionBD.java
 * Version 1
 * 1/11/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase que realiza la conexión con la base de datos mediante el driver JDBC
 * @author Dave Went
 */
public class ConexionBD {

    protected Connection conexion; //Variable que realiza la conexion abase de datos
    
    /**
     * Constructor que realiza la conexión con base de datos
     */
    public ConexionBD() {
        //Parametros para la conexion de la base de datos
        String usuario = "root";
        String contrasena = "1234";
        String url = "jdbc:mysql://localhost:3306/tanzstudiodb";
        String driver = "com.mysql.jdbc.Driver";
        try{
            //Carga el driver
            Class.forName(driver);
            //Crea la conexion con la base de datos por medio del driver
            conexion = DriverManager.getConnection(url,usuario,contrasena);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Método que cierra el prepareStatemet con el que se ejecuta el query
     * @param ps
     * @throws Exception 
     */
    protected void cerrar(PreparedStatement ps)throws  Exception{
        if(ps != null){
            ps.close();
        }
    }
    
    /**
     * Método que cierra el flujo de resultSet con el que se guarda los resultados
     * del query
     * @param rs
     * @throws Exception 
     */
    protected void cerrar(ResultSet rs)throws Exception{
        if(rs != null){
            rs.close();
        }
    }
}