package Controlador;

import Modelo.Progenitor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class CProgenitor extends ConexionBD {

    private static final String ID = "id_progenitor";
    private static final String NOMBRE_PR = "nombre_PR";
    private static final String APELLIDO_PATERNO_PR = "apellidoPaterno_PR";
    private static final String APELLIDO_MATERNO_PR = "apellidoMaterno_PR";
    private static final String DIA_NAC_PR = "diaNac_PR";
    private static final String MES_NAC_PR = "mesNac_PR";
    private static final String AÑO_NAC_PR = "anoNac_PR";
    private static final String PROFESION_PR = "profesion_PR";
    private static final String TEL_CEL_PR = "telCel_PR";
    private static final String PERSONA_DE_CONFIANZA = "personaDeConfianza";
    private static final String TIPO = "tipo_PR";
    private static String SELECT_ID="select "+ID+" from tiene where matricula=?";
    private static String SQL_TIENE = "INSERT INTO tiene (matricula,"+ID +") values(?,?) ";
    private static String SQL_ID = "select " + ID + " from progenitor where "
            + ID + "=(select max(" + ID + ") from progenitor)";
    private static final String SQL_LEER = "SELECT * FROM progenitor WHERE "
            + ID + " = ?";
    private static final String SQL_MODIFICA = "UPDATE progenitor SET "
            + NOMBRE_PR + " =?,"
            + APELLIDO_PATERNO_PR + " =?,"
            + APELLIDO_MATERNO_PR + " =?,"
            + DIA_NAC_PR + " =?,"
            + MES_NAC_PR + " =?,"
            + AÑO_NAC_PR + " =?,"
            + PROFESION_PR + " =?,"
            + TEL_CEL_PR + " =?,"
            + PERSONA_DE_CONFIANZA + " =?,"
            + TIPO + " =? "
            + "WHERE " + ID + "= ?";
    private static final String SQL_INSERTAR = "INSERT INTO progenitor"
            + "(" + NOMBRE_PR
            + "," + APELLIDO_PATERNO_PR
            + "," + APELLIDO_MATERNO_PR
            + "," + DIA_NAC_PR
            + "," + MES_NAC_PR
            + "," + AÑO_NAC_PR
            + "," + PROFESION_PR
            + "," + TEL_CEL_PR
            + "," + PERSONA_DE_CONFIANZA
            + "," + TIPO + ""
            + ") VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_LEER_TODO = "SELECT * FROM progenitor";

    
    /***
     * Metodo que transforma un ResultSet en un objeto tipo progenitor
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Progenitor getObject( ResultSet rs ) throws SQLException {
        Progenitor progenitor = new Progenitor();
        progenitor.setId( rs.getInt( ID ) );
        progenitor.setNombre( rs.getString( NOMBRE_PR ) );
        progenitor.setApellidoPaterno( rs.getString( APELLIDO_PATERNO_PR ) );
        progenitor.setApellidoMaterno( rs.getString( APELLIDO_MATERNO_PR ) );
        progenitor.setDiaNac( rs.getInt( DIA_NAC_PR ) );
        progenitor.setMesNac( rs.getString( MES_NAC_PR ) );
        progenitor.setAñoNac( rs.getInt( AÑO_NAC_PR ) );
        progenitor.setProfesion( rs.getString( PROFESION_PR ) );
        progenitor.setTelefonoCelular( rs.getString( TEL_CEL_PR ) );
        progenitor.setTipo( rs.getString( TIPO ) );
        progenitor.setPersonaConfianza( rs.getString( PERSONA_DE_CONFIANZA ) );
        return progenitor;
    }
    
    
    /**
     * Este metodo ingresa a un progenitor en la tabla progenitor
     * @param progenitor 
     * @throws Exception 
     */
    public void agregar( Progenitor progenitor ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSERTAR );
        ps.setString( 1, progenitor.getNombre() );
        ps.setString( 2, progenitor.getApellidoPaterno() );
        ps.setString( 3, progenitor.getApellidoMaterno() );
        ps.setInt( 4, progenitor.getDiaNac() );
        ps.setString( 5, progenitor.getMesNac() );
        ps.setInt( 6, progenitor.getAñoNac() );
        ps.setString( 7, progenitor.getProfesion() );
        ps.setString( 8, progenitor.getTelefonoCelular() );
        ps.setString( 9, progenitor.getPersonaConfianza() );
        ps.setString( 10, progenitor.getTipo() );
        System.out.println( ps );
        System.out.println(SQL_INSERTAR );
        ps.executeUpdate();
        cerrar( ps );
    }

    /***
     * Metodo que modifica un registro en especifico en la tabla progenitor
     * @param progenitor 
     * @throws Exception 
     */
    public void modificar( Progenitor progenitor ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_MODIFICA );
        ps.setString( 1, progenitor.getNombre() );
        ps.setString( 2, progenitor.getApellidoPaterno() );
        ps.setString( 3, progenitor.getApellidoMaterno() );
        ps.setInt( 4, progenitor.getDiaNac() );
        ps.setString( 5, progenitor.getMesNac() );
        ps.setInt( 6, progenitor.getAñoNac() );
        ps.setString( 7, progenitor.getProfesion() );
        ps.setString( 8, progenitor.getTelefonoCelular() );
        ps.setString( 9, progenitor.getPersonaConfianza() );
        ps.setString( 10, progenitor.getTipo() );
        ps.setInt( 11, progenitor.getId() );
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }

    /**
     * Este metodo lee todos los registros de la tabla prgenitor
     * @return
     * @throws Exception 
     */
    public ArrayList leerTodo() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_LEER_TODO );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            result.add( getObject( rs ) );
        }
        
        cerrar( ps );
        cerrar( rs );
        return result;
    }

    /**
     * Lee a un progenitor en la tabla porgenitor dependiendo el id 
     * @param id
     * @return
     * @throws Exception 
     */
    public Progenitor leer( int id ) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Progenitor result = null;
        System.out.println( ps );
        ps = conexion.prepareStatement(SQL_LEER );
        ps.setInt(1, id);
        rs = ps.executeQuery();
        
        if ( rs.next() ) {
            result = getObject( rs );
        }
        
        cerrar( ps );
        cerrar( rs );
        return result;
    }

    
    /**
     * Metodo que obtiene el ultimo id registrado en la tabla progenitor
     * @return
     * @throws Exception 
     */
    public int obtenerId() throws Exception {
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conexion.prepareStatement( SQL_ID );
        System.out.println(ps);
        rs = ps.executeQuery();
        
        if ( rs.next() ) {
            result = rs.getInt( ID );
        }
        cerrar( ps );
        cerrar( rs );
        return result;
    }

    /**
     * Este metodo ingresa la matricula y el id del progenitor en la tabla puente
     * tiene 
     * @param id
     * @param matricula
     * @throws Exception 
     */
    public void tiene( int id, int matricula ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( SQL_TIENE );
        ps.setInt( 1, matricula );
        ps.setInt( 2, id );
        System.out.println( ps );

        ps.executeUpdate();
        cerrar( ps );
    }
    /**
     * Metodo utilizado para ingresar a un progenitor en la tabla progenitor
     * y ingresar la matricula y el id en la tabla puente tiene
     * @param progenitor
     * @param matricula
     * @throws Exception 
     */
    public void alta( Progenitor progenitor, int matricula ) throws Exception{
        agregar( progenitor );
        tiene( obtenerId(), matricula );
    }
    
    /***
     * Este metodo  obtine de la tabla progenitores el padre y la madre de un 
     * alumno
     * @param matricula
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int[] padresDe(int matricula) throws SQLException, Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int[] id = new int[ 2 ];
        int contador = 0;
        ps = conexion.prepareStatement( SELECT_ID );
        ps.setInt( 1, matricula );
        System.out.println( ps );
        rs = ps.executeQuery();
        while ( rs.next() ) {
            id[ contador ] = ( rs.getInt( ID ) );
            contador++;
        }
        cerrar( ps );
        cerrar( rs );
        return id;
    }
}
