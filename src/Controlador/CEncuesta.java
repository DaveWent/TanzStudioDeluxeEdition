package Controlador;

import Modelo.Encuesta;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CEncuesta extends ConexionBD {

    //INSERT INTO `tanzstudiodb`.`encuesta` (`medioPublicidad`, `danzaSugerida`, `lugarDanzaAnterior`, `sugerencia`) 
    private static final String ID = "id_Encuesta";
    private static final String MEDIO_PUBLICIDAD = "medioPublicidad";
    private static final String DANZA_SUGERIDA = "danzaSugerida";
    private static final String LUGAR_DANZA = "lugarDanzaAnterior";
    private static final String SUGERENCIA = "sugerencia";
    private static final String SQL_LEER_TODO = "SELECT * FROM encuesta";
    private static String SQL_REALIZA = "update realiza set " + ID + "=? where matricula=?";
    private static String SQL_ID = "select " + ID + " from encuesta where "
            + ID + "=(select max(" + ID + ") from encuesta)";
    private static String SELECT_ID = "select " + ID + " from realiza where matricula=?";
    private static final String SQL_MODIFICA = "UPDATE encuesta SET "
            + MEDIO_PUBLICIDAD + " =?,"
            + DANZA_SUGERIDA + " =?,"
            + LUGAR_DANZA + " =?,"
            + SUGERENCIA + " =? "
            + "WHERE " + ID + "= ?";
    private static final String SQL_INSERTAR = "INSERT INTO encuesta"
            + "(" + MEDIO_PUBLICIDAD
            + "," + DANZA_SUGERIDA
            + "," + LUGAR_DANZA
            + "," + SUGERENCIA + ""
            + ")VALUES(?,?,?,?)";
    private static final String SQL_LEER = "SELECT * FROM encuesta WHERE "
            + ID + " = ?";
   

    public CEncuesta() {
        super();
    }

    /**
     * Este metodo convierte un resultSet en un objeto tipo encuesta
     *
     * @param rs
     * @return
     * @throws Exception
     */
    private Encuesta getObject(ResultSet rs) throws Exception {
        Encuesta encuesta = new Encuesta();
        encuesta.setId(rs.getInt(ID));
        encuesta.setMedioPublicidad(rs.getString(MEDIO_PUBLICIDAD));
        encuesta.setDanzaSugerida(rs.getString(DANZA_SUGERIDA));
        encuesta.setLugarDanza(rs.getString(LUGAR_DANZA));
        encuesta.setSugerencia(rs.getString(SUGERENCIA));
        return encuesta;
    }

    /**
     * Este metdo agrega a una encuesta en la tabla encuesta
     *
     * @param dto
     * @throws Exception
     */
    public void agregar(Encuesta encuesta) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSERTAR);
        ps.setString(1, encuesta.getMedioPublicidad());
        ps.setString(2, encuesta.getDanzaSugerida());
        ps.setString(3, encuesta.getLugarDanza());
        ps.setString(4, encuesta.getSugerencia());

        System.out.println(ps + "hola");
        ps.executeUpdate();
        cerrar(ps);
    }




    /**
     * *
     * Este metodo modifica un registro en espesifico en la tabla encuesta
     *
     * @param dto
     * @throws Exception
     */
    public void modificar(Encuesta encuesta) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_MODIFICA);
        ps.setString(1, encuesta.getMedioPublicidad());
        ps.setString(2, encuesta.getDanzaSugerida());
        ps.setString(3, encuesta.getLugarDanza());
        ps.setString(4, encuesta.getSugerencia());
        ps.setInt(5, encuesta.getId());
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * *
     * este metodo toma de la tabla encuesta un registro en especifico
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Encuesta leer(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Encuesta result = null;
        ps = conexion.prepareStatement(SQL_LEER);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            result = getObject(rs);
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    /**
     * *
     * Este metodo ontiene el ultimo id registrado en la tabla encuesta
     *
     * @return
     * @throws Exception
     */
    public int obtenerId() throws Exception {
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conexion.prepareStatement(SQL_ID);
        System.out.println(ps);
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getInt(ID);
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    /**
     * *
     * Este metodo registra en la tabla puente realiza
     *
     * @param id
     * @param matricula
     * @throws Exception
     */
    public void realiza(int id, int matricula) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_REALIZA);
        ps.setInt(1, id);
        ps.setInt(2, matricula);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * *
     * Este metodo obtiene de la base de datos encuesta el registro de la
     * encuesta que pertenece a un determinado alumno utilizando la tabla puente
     * tiene
     *
     * @param matricula
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public int encuestaDe(int matricula) throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        ps = conexion.prepareStatement(SELECT_ID);
        ps.setInt(1, matricula);
        System.out.println(ps);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = (rs.getInt(ID));
        }
        cerrar(ps);
        cerrar(rs);
        return id;
    }

    /**
     * *
     * Metodo que ingresa en la tabla ecuesta y la tabla puente los alumnos
     *
     * @param encuesta
     * @param matricula
     * @throws Exception
     */
    public void Alta(Encuesta encuesta, int matricula) throws Exception {
        agregar(encuesta);
        realiza(obtenerId(), matricula);
    }
}
