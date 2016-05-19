package Controlador;

import Modelo.Salud;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class CSalud extends ConexionBD {

    private static final String ID = "id_problema";
    private static final String PROBLEMA = "problemaSalud";
    private static final String CONTROLADA = "controlada";
    private static final String SQL_SLECTEC_ALL = "SELECT * FROM salud";
    private static String SELECT_ID = "select " + ID + " from tiene_problema where matricula=?";
    private static String SQL_ID = "select " + ID + " from salud where "
            + ID + "=(select max(" + ID + ") from salud)";
    private static String SQL_TIENEPROBLEMA = "INSERT INTO tiene_problema"
            + "(matricula,"
            + ID + ") VALUES(?,?)";
    private static final String SQL_INSERTAR = "INSERT INTO salud"
            + "(" + PROBLEMA
            + "," + CONTROLADA + ""
            + ")VALUES(?,?)";
    private static final String SQL_LEER = "SELECT * FROM salud WHERE "
            + ID + " = ?";
    private static final String SQL_MODIFICA = "UPDATE salud SET "
            + PROBLEMA + " =?,"
            + CONTROLADA + " =? "
            + "WHERE " + ID + "= ?";
    private static final String SQL_ELIMINAR="delete from salud where id_problema=?";
    private static final String SQL_ELEIMINAR_PROBLEMA="delete from tiene_problema where id_problema=?";
    
    public CSalud() {
        super();
    }

    /***
     * Metodo que transforma  un resultSet en un objeto tipo Salud
     * @param rs
     * @return
     * @throws Exception 
     */
    private Salud getObject( ResultSet rs ) throws Exception {
        Salud salud = new Salud();
        salud.setId( rs.getInt( ID ) );
        salud.setProblema( rs.getString( PROBLEMA ) );
        salud.setControlada( rs.getString( CONTROLADA ) );
        return salud;
    }

    /***
     * Metodo que se utiliza para obtener un registro en espesifico de la 
     * tabla Salud
     * @param id
     * @return
     * @throws Exception 
     */
    public Salud leer(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Salud result = null;
        ps = conexion.prepareStatement(SQL_LEER );
        ps.setInt( 1, id );
        rs = ps.executeQuery();
        if ( rs.next() ) {
            result = getObject( rs );
        }
        cerrar( ps );
        cerrar( rs );
        return result;
    }

    /***
     * Este metodo agrega un registro en la tabla salud 
     * @param salud
     * @throws Exception 
     */
    public void agregar( Salud salud) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSERTAR );
        ps.setString( 1, salud.getProblema() );
        ps.setString( 2, salud.getControlada() );
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }

    /***
     * Este metodo permite modificar un registro en espesifico de la tabla salud
     * @param dto
     * @throws Exception 
     */
    public void modificar( Salud dto) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_MODIFICA );
        ps.setString( 1, dto.getProblema() );
        ps.setString( 2, dto.getControlada() );
        ps.setInt( 3, dto.getId() );
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }
    public void eliminar(int id) throws  Exception{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( SQL_ELIMINAR );
        ps.setInt( 1, id );
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }
    public void eliminarTieneProblema(int id)throws Exception{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( SQL_ELEIMINAR_PROBLEMA );
        ps.setInt( 1, id );
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }
    public void baja(int id) throws Exception{
        eliminar(id);
        eliminarTieneProblema(id);
    }
    /***
     * Metodo que obtiene el ultimo id de la tabla salud
     * @return
     * @throws Exception 
     */
    public int obtenerId() throws Exception {
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conexion.prepareStatement( SQL_ID );
        System.out.println( ps );
        rs = ps.executeQuery();
        if ( rs.next() ) {
            result = rs.getInt( ID );
        }
        cerrar( ps );
        cerrar( rs );
        System.out.println("result"+result);
        return result;
    }
    /***
     * Metodo que hace un registro en la tabla puente tiene_problema 
     * 
     * @param id
     * @param matricula
     * @throws Exception 
     */
    public void tieneProlebla( int id, int matricula ) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( SQL_TIENEPROBLEMA );
        ps.setInt( 1, matricula );
        ps.setInt( 2, id );
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }

    /***
     * Este metodo  realiza un registro en la tabla salud y en la tabla tine
     * problema
     * @param salud
     * @param matricula
     * @throws Exception 
     */
    public void alta( Salud salud, int matricula ) throws Exception {
        agregar( salud );
        tieneProlebla( obtenerId(), matricula );
    }
    /**
     * Este metodo obtiene la salud correspondiente de determinado alumno 
     * @param matricula
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int SaludDe( int matricula ) throws SQLException, Exception {
        int id = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement( SELECT_ID );
        ps.setInt( 1, matricula );
        System.out.println( ps );
        rs = ps.executeQuery();
        if ( rs.next() ) {
            id = ( rs.getInt( ID ) );
        }
        cerrar( ps );
        cerrar( rs );
        return id;
    }
}
