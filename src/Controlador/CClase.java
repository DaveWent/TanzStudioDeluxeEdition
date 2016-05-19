/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dave Went
 */
public class CClase extends ConexionBD {
    
    private static final String idClase = "id_Clase";
    private static final String dia = "dia";
    private static final String salon = "salon";
    private static final String horaInicio = "horaInicio";
    private static final String horaFin = "horaFin";
    private static final String nombreCurso = "nombre_CU";
    private static final String obtenerClases = "SELECT clase." + idClase + 
            ", clase." + dia + ", clase." + salon + ", clase." + horaInicio + 
            ", clase." + horaFin + " FROM clase INNER JOIN posee ON clase." 
            + idClase + " = posee." + idClase + " AND posee." + nombreCurso + "=?";
    private static final String obtenerClasesDeUnDia = "SELECT clase." + idClase 
            + ", clase." + salon + ",clase." + horaInicio + ", clase." + horaFin 
            + " FROM posee INNER JOIN clase ON posee." + idClase + " = clase." 
            + idClase + " AND clase." + dia +" = ? AND posee." + nombreCurso + "=?";
    private static final String consultaClases = "SELECT * FROM clase";
    private static final String agregarClase = "INSERT INTO clase ( " + dia + 
            ", " + salon + ", " + horaInicio + "," + horaFin + " ) VALUES(?, ?, ?,?)";
    private static final String ultimoIdClase = "SELECT max(" + idClase + ") "
            + "FROM clase;";
    private static final String eliminarClase = "DELETE FROM clase WHERE " + idClase + "=?";
    private static final String agregarRelacion = "INSERT INTO posee (" + 
            nombreCurso + ", " + idClase + ") VALUES (?, ?)";
    private static final String consultaEspecifica = "SELECT * FROM clase WHERE " + dia + "=?";
    private static final String consultaClasesDeSilencio = "SELECT P." + idClase 
            + ", P." + salon + ", P." + horaInicio + ", P." + horaFin + 
            " FROM curso INNER JOIN ( (SELECT posee." + nombreCurso + ", clase." 
            + idClase + ", clase." + salon + ",clase." + horaInicio + ", clase." 
            + horaFin + " FROM clase INNER JOIN posee ON clase." + idClase + 
            " = posee." + idClase + " AND clase." + dia + " = ? ) AS P) ON curso." 
            + nombreCurso + " = P." + nombreCurso + " AND curso.requiereSilencio ='SI'";
    private static final String eliminarRelacion = "DELETE FROM posee WHERE " + 
            nombreCurso + "=? AND "+ idClase + "=?";
    private static final String clasesAlumno=" select inscrito3.nombre_CU,clase.*" +
            " from clase inner join inscrito3 on inscrito3.id_clase=clase.id_clase" 
            +" where inscrito3.matricula=?";
    
    public ArrayList<Clase> obtenerClasesDeUnCurso( String nombreDelCurso ) throws SQLException,
            Exception {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( obtenerClases );
        ps.setString( 1, nombreDelCurso );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            Clase clase = new Clase();
            clase.setIdClase( rs.getInt( idClase ) );
            clase.setDia( rs.getString( dia ) );
            clase.setSalon( rs.getString( salon ) );
            clase.setHoraInicio( rs.getString( horaInicio ) );
            clase.setHoraFin( rs.getString( horaFin ) );
            clases.add( clase );
        }
        cerrar( ps );
        cerrar( rs );
        return clases;
    }
    
    public String[][] obtenerClasesPorDia( String nombreDelCurso, String diaDeLaClase ) 
            throws SQLException, Exception {
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( obtenerClasesDeUnDia );
        ps.setString( 1, diaDeLaClase );
        ps.setString( 2, nombreDelCurso );
        rs = ps.executeQuery();
        int contadorResultados = 0;
        
        while( rs.next() ) {
            contadorResultados++;
        }
        cerrar( ps );
        cerrar( rs );
        
        String[][] resultados = new String[ contadorResultados ][ 4 ];
        ps = conexion.prepareStatement( obtenerClasesDeUnDia );
        ps.setString( 1, diaDeLaClase );
        ps.setString( 2, nombreDelCurso );
        rs = ps.executeQuery();
        int contador = 0;
        
        while( rs.next() ) {
            resultados[ contador ][ 0 ] = Integer.toString( rs.getInt( idClase ) );
            resultados[ contador ][ 1 ] = rs.getString( salon );
            resultados[ contador ][ 2 ] = rs.getString( horaInicio );
            resultados[ contador ][ 3 ] = rs.getString( horaFin );
            contador++;
        }
        return resultados;
    }
    
    public ArrayList<Clase> consultaClases() throws SQLException {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaClases );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            Clase clase = new Clase();
            clase.setIdClase( rs.getInt( idClase ) );
            clase.setDia( rs.getString( dia ) );
            clase.setSalon( rs.getString( salon ) );
            clase.setHoraInicio( rs.getString( horaInicio) );
            clase.setHoraFin( rs.getString( horaFin ) );
            clases.add( clase );
        }
        
        return clases;
    } 
    
    public void agregarClase( Clase clase ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( agregarClase );
        ps.setString( 1, clase.getDia() );
        ps.setString( 2, clase.getSalon() );
        ps.setString( 3, clase.getHoraInicio() );
        ps.setString( 4, clase.getHoraFin() );
        ps.executeUpdate();
        
        cerrar(ps);
    }
    
    public int obtenerIdClase() throws SQLException {
        int id = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( ultimoIdClase );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            id = rs.getInt( "max(" + idClase + ")" );
        }
        return id;
    }
    
    public void eliminarClase( int idDeLaClase ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( eliminarClase );
        ps.setInt( 1, idDeLaClase );
        ps.executeUpdate();
        
        cerrar(ps);
    }
    
    public void agregarRelacionClaseYCurso( String nombreDelCurso, int idDeLaClase ) 
            throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( agregarRelacion );
        ps.setString( 1, nombreDelCurso );
        ps.setInt( 2, idDeLaClase );
        ps.executeUpdate();
        
        cerrar(ps);
    }
    
    public ArrayList<Clase> consultaClasesEspecifica( String diaDeLasClases ) throws SQLException {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaEspecifica );
        ps.setString( 1, diaDeLasClases );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            Clase clase = new Clase();
            clase.setIdClase( rs.getInt( idClase ) );
            clase.setDia( rs.getString( dia ) );
            clase.setSalon( rs.getString( salon ) );
            clase.setHoraInicio( rs.getString( horaInicio) );
            clase.setHoraFin( rs.getString( horaFin ) );
            clases.add( clase );
        }
        
        return clases;
    } 
    
    public ArrayList<Clase> consultarClasesDeSilencio( String diaDeLasClases ) 
            throws SQLException {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaClasesDeSilencio );
        ps.setString( 1, diaDeLasClases );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            Clase clase = new Clase();
            clase.setIdClase( rs.getInt( idClase ) );
            clase.setSalon( rs.getString( salon ) );
            clase.setHoraInicio( rs.getString( horaInicio) );
            clase.setHoraFin( rs.getString( horaFin ) );
            clases.add( clase );
        }
        return clases;
    }
    
    public void eliminarRelacionCurso( String nombreDelCurso, int idDeLaClase ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( eliminarRelacion );
        ps.setString( 1, nombreDelCurso );
        ps.setInt( 2, idDeLaClase );
        ps.executeUpdate();
        
        cerrar(ps);
    }
    
    public String[] ClasesDeAlumno(int matricula) throws SQLException{
        
        ResultSet rs;
        PreparedStatement ps = null;
        String registro="";
        ps = conexion.prepareStatement(clasesAlumno);
        ps.setInt(1, matricula );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
        
        registro += rs.getString(nombreCurso)+"@"+rs.getString(dia)+"@";
        registro +=rs.getString(horaInicio)+" a "+rs.getString(horaFin)+"@";
        registro +=rs.getString(salon)+"%";
        }
        registro= registro.substring(0, registro.length()-1);
        String[] clases=registro.split("%");
        return clases;
    }
}
