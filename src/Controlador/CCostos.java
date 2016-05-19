/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Costos;
import Modelo.Pagos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class CCostos extends ConexionBD {
    private static final String insertar = "insert into costos (nombre_Cos,costos) values (?,?);";
    private static final String modificacion = "UPDATE costos SET costos = ? WHERE id_Costos = ?";
    private static final String buscar = "SELECT id_Costos FROM costos WHERE nombre_Cos = ? AND costos = ?";
    private static final String obtenerPrecio = "SELECT nombre_Cos, costos FROM costos WHERE nombre_Cos = ?";
    private static final String buscarOtro = "SELECT id_Costos FROM costos WHERE nombre_Cos = ?";
    private static final String buscarCostoEsporadico = "SELECT * FROM costos where nombre_Cos = ? ";
    private static final String busquedaInscripcion = "SELECT id_Costos FROM costos WHERE nombre_Cos = 'inscripcion'";
    private static final String insertarToma = "INSERT INTO toma(id_Cuenta, id_Costos) VALUES (?, ?)";

 
    public CCostos(){
        super();
    }
 
    public void alta(Costos costos) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(insertar);
        ps.setString(1, costos.getNombreCos());
        ps.setDouble(2, costos.getCostos());
        System.out.println(ps);
        //ejecuta la actualizacion
     
        ps.executeUpdate();
        
        cerrar(ps);
    }
     
 
      public void modificacion(Costos costos) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(modificacion);
        ps.setDouble(1, costos.getCostos());
        ps.setInt(2, costos.getIdCostos());
        
        System.out.println(ps);
        //ejecuta la actualizacion
     
        ps.executeUpdate();
        
        cerrar(ps);
    }
      
        public int Busqueda(String nombreCos, Double costos) throws Exception {
        ResultSet rs;
        int id = 0;
	PreparedStatement ps = null;
        ps = conexion.prepareStatement(buscar);
        ps.setString(1, nombreCos);
        ps.setDouble(2, costos);
        rs = ps.executeQuery();
        while (rs.next()) {
           id=rs.getInt("id_Costos");
        }
        return id;
    }
        public double obtPrecio(String nombreCos) throws SQLException{
        ResultSet rs;
        double precio = 0;
	PreparedStatement ps = null;
        ps = conexion.prepareStatement(obtenerPrecio);
        ps.setString(1, nombreCos);
        rs = ps.executeQuery();
        while (rs.next()) {
           precio=rs.getDouble("costos");
        }
        return precio;
     }
        
        public int Busqueda(String nombreCos) throws Exception {
        ResultSet rs;
        int id = 0;
	PreparedStatement ps = null;
        ps = conexion.prepareStatement(buscarOtro);
        ps.setString(1, nombreCos);
        
        rs = ps.executeQuery();
        while (rs.next()) {
           id=rs.getInt("id_Costos");
        }
        return id;
    }
        
        public Costos ObtenerCostosEsporadicos(String nombreCurso) throws SQLException{
         ResultSet rs;
         Costos esporadico = null;
         PreparedStatement ps = null;
        ps = conexion.prepareStatement(buscarCostoEsporadico);
        ps.setString(1, nombreCurso);
        
        rs = ps.executeQuery();
        while (rs.next()) {
          esporadico = new Costos();
          esporadico.setIdCostos(rs.getInt("id_Costos"));
          esporadico.setCostos(rs.getInt("costos"));
          esporadico.setNombreCos(rs.getString("nombre_Cos"));
        }
       
         return esporadico;
        
        }  
        
    public int busquedaInscripcion () throws SQLException {
        ResultSet rs;
        int id = 0;
	PreparedStatement ps = null;
        ps = conexion.prepareStatement(busquedaInscripcion);
        rs = ps.executeQuery();
        while (rs.next()) {
           id=rs.getInt("id_Costos");
        }
        return id;
    }
    
    public void insertarToma( int idCuenta, int idCostos ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(insertarToma);
        ps.setInt(1, idCuenta);
        ps.setInt(2, idCostos);
        System.out.println(ps);
        //ejecuta la actualizacion
     
        ps.executeUpdate();
        
        cerrar(ps);
    
    }

}
