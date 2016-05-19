/**
 * ControlDanza.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import Modelo.Danzas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que lleva el control de todas las operaciones en la base de datos
 * referentes a las danzas y que hereda de ConexionBD para conectar con esta
 * @author David Omar Cabrera bernal
 */
public class CDanza extends ConexionBD{
    
    //Variable que guarda el query para dar de alta una danza conocida
    private static final String altaDanza = "INSERT INTO danzas"
        + "( danzaConocida) VALUES(?)";
    //Variable que guarda el query que retorna todas las danzas existentes
    private static final String consultaDanzas = "SELECT * FROM danzas";
    //Variable que gaurda el query que retorna la ultima danza registrada
    private static final String ultimoRegistro = "SELECT * FROM danzas "
        + "where id_Danza = ( SELECT MAX(id_Danza) FROM danzas )";
    //Variable que gaurda el query para dar de baja una danza
    private static final String bajaDanza = "DELETE FROM danzas WHERE id_Danza = ?";
    //Variable que guarda el query para la búsqueda especifica de una danza
    private static final String busquedaEspecifica = "SELECT * FROM danzas WHERE id_Danza=?";
    //Variable que guarda el query qe retorna los ids de las danzas impartidas
    //por un profesor
    private static final String busquedaDanzasPorIdProfesor = "SELECT id_Danza "
        + "FROM conoce WHERE id_Profesor=?";
    
    /**
     * Constructor que inicializa la clase
     */
    public CDanza() {
        super();
    }
    
    /**
     * Método que realiza la alta de las danzas que se deseen registrar
     * @param danzas
     * @return vectorId
     * @throws SQLException
     * @throws Exception 
     */
    public int[] alta( ArrayList<Danzas> danzas ) throws SQLException, Exception {
        //Se crea un vector de id de danzas en base al numero de danzas recibidas
        int[] vectorId = new int[ danzas.size() ];
        int contador = 0;
        PreparedStatement ps = null;
        //Se indica el query que dara de alta una danza
        ps = conexion.prepareStatement( altaDanza );
        
        //Se realiza un ciclo for para las danzas recibidas
        for ( Danzas danza : danzas ) {
            //Se agrega la danza correspondiente a la vuelta de este ciclo al query
            ps.setString( 1, danza.getDanza() );
            //Se ejecuta el query
            ps.executeUpdate();
            //Se llama al metodo de ultimoId que retornara el id de la última 
            //danz registrada
            vectorId[ contador ] = ultimoId();
            contador++;
	}
        //Se cierra la conexion con la base de datos
        cerrar( ps );
        return vectorId;
    }
    
    /**
     * Método que retorna el id de la última danza registrada
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    private int ultimoId() throws SQLException, Exception {
        int id = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que retorna el id del último registro
        ps = conexion.prepareStatement( ultimoRegistro );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se guarda el id
            id = rs.getInt( "id_Danza" );
        }
        //Se cierran las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return id;
    }
    
    /**
     * Método que retorna las danzas existentes en la base de datos
     * @return danzas
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Danzas> consulta() throws SQLException, Exception {
        ResultSet rs;
        //ArrayList que guardara las danzas que se obtengan
        ArrayList<Danzas> danzas = new ArrayList<Danzas>();
	PreparedStatement ps = null;
        //Se indica el query que retornara las danzas existentes
        ps = conexion.prepareStatement( consultaDanzas );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se llama al método getDanza que regresa la danza guardada en una
            //instancia de danza, que sera añadido al arraylist de danzas
            danzas.add( getDanza( rs ) );
        }
        //Se cierra las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return danzas;
    }
    
    /**
     * Método que guarda los datos de una danza en una instancia mediante
     * un resulset que contiene una tupla de las danzas existentes
     * @param rs
     * @return
     * @throws Exception 
     */
    private Danzas getDanza( ResultSet rs ) throws Exception {
        //Se crea una instancia donde se guardaran los datos de las danzas
        Danzas datosDanzas = new Danzas();
        datosDanzas.setIdDanza( rs.getInt( "id_Danza" ) );
        datosDanzas.setDanza( rs.getString( "DanzaConocida" ) );
        return datosDanzas;
    }
    
    /**
     * Método que realiza una baja de una danza por medio de un id 
     * @param id
     * @throws Exception 
     */
    public void baja( int id ) throws Exception {
        PreparedStatement ps = null;
        //Se indica el query que realizara la baja de la danza
        ps = conexion.prepareStatement( bajaDanza );
        //Se agrega el id de la danza al query
        ps.setInt( 1, id );
        //Se ejecuta el query
        ps.execute();
        //Se cierrar la conexion con la base de datos
        cerrar( ps );
    }
    
    /**
     * Método que realiza una búsqueda especifica de una danza por medio de 
     * su id y que retorna el nombre de la danza
     * @param idDanza
     * @return danzaConocida
     * @throws SQLException
     * @throws Exception 
     */
    public String busqueda( String idDanza ) throws SQLException, Exception {
        String danzaConocida = null;
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se utilizara para la busqueda especifica de danza
        ps = conexion.prepareStatement( busquedaEspecifica );
        //Se agrega el id al query
        ps.setString( 1, idDanza );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se alamacena la danza encontrada
            danzaConocida = rs.getString( "danzaConocida" );
        }
        //Se cierras las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return danzaConocida;
    }
    
    /**
     * Método que retorna los ids de las danzas que conoce un profesor
     * @param numProfesor
     * @return ids
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Integer> busquedaDanzasPorIdProfesor( int numProfesor ) 
            throws SQLException, Exception {
        ResultSet rs;
        //ArrayList que almacena los ids de la danzas que conoce un profesor
        ArrayList<Integer> ids = new ArrayList<Integer>();
        PreparedStatement ps = null;
        //Indica el query para buscar las danzas por medio del numero de profesor
        ps = conexion.prepareStatement( busquedaDanzasPorIdProfesor );
        //Se agrega el numero del profesor al query
        ps.setInt( 1, numProfesor );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se agrega el id de las danzas encontradas
           ids.add( rs.getInt( "id_Danza" ) ); 
        }
        //Se cierrala conexion con la base de datos
        cerrar( ps );
        cerrar( rs );
        return ids;
    }
    
}
