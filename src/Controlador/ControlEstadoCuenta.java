/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EstadoCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Christian
 */
public class ControlEstadoCuenta extends ConexionBD{
    
    private static final String crearEstadoCuenta = "INSERT INTO estadocuenta "
            + "( tipo_EC, saldo, fecha_EC ) VALUES ( ? , ?, CURDATE() )";
    private static final String ultimoEstadoCuenta = "SELECT MAX(id_Cuenta) FROM estadocuenta";
    private static final String agregarRelacion = "INSERT INTO hace1 (matricula, id_Cuenta) VALUES(?, ?)";
    
    public void alta( EstadoCuenta estadoCuenta ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( crearEstadoCuenta );
        ps.setString( 1, estadoCuenta.getTipo() );
        ps.setDouble( 2, estadoCuenta.getSaldo() );
        //ejecuta la actualizacion
     
        ps.executeUpdate();
        
        cerrar(ps);
    }
    
    public int ultimoEstadoCuenta() throws Exception {
        int id = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( ultimoEstadoCuenta );
        //ejecuta la actualizacion
     
        rs = ps.executeQuery();
        
        while( rs.next() )  {
            id = rs.getInt("MAX(id_Cuenta)");
        }
        
        cerrar(ps);
        return id;
    }
    
    public void agregarRelacion( int matricula, int idCuenta ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( agregarRelacion );
        ps.setInt( 1, matricula );
        ps.setInt( 2, idCuenta );
        //ejecuta la actualizacion
     
        ps.executeUpdate();
        
        cerrar(ps);
    }
    
}
