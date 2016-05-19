/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class CAlumno extends ConexionBD {

    private static final String MATRICULA = "matricula";
    private static final String NOMBRE = "nombre_A";
    private static final String APELLIDO_PATERNO = "apellidoPaterno_A";
    private static final String APELLIDO_MATERNO = "apellidoMaterno_A";
    private static final String DIA_NAC = "diaNac_A";
    private static final String MES_NAC = "mesNac_A";
    private static final String AÑO_NAC = "anoNac_A";
    private static final String EDAD = "edad";
    private static final String CIUDAD_NAC = "ciudadNac";
    private static final String ESTADO_NAC = "estadoNac";
    private static final String SEXO = "sexo";
    private static final String COLONIA = "colonia";
    private static final String CALLE = "calle";
    private static final String NUMERO = "numero";
    private static final String PROFESION = "profesion_A";
    private static final String EMAIL = "email_A";
    private static final String FACEBOOK = "facebook_A";
    private static final String TEL_CASA = "telCasa_A";
    private static final String TEL_CEL = "telCel_A";
    private static final String ESCUELA = "escuela";
    private static final String GRADO = "grado";
    private static final String ESTADO = "estado";
    private static final String SQL_SLECTEC_ALL = "SELECT * FROM alumno";
    public static String SQL_REALIZA = "insert into realiza (" + MATRICULA + ") values(?)";
    private static String SQL_TIENE = "insert into tiene(" + MATRICULA + ") values(?)";
    public static String SQL_INSCRITO = "insert into inscrito (matricula,nombre_CU) values(?,?)";
    public static String SQL_TIENE_PROBLEMA = "insert into tiene_problema (" + MATRICULA + ") values(?)";
    public static String SQL_INSCRITO3 = "insert into inscrito3 (matricula,id_Clase,nombre_CU)value(?,?,?)";
    private static String SQL_ID = "SELECT " + MATRICULA + " FROM alumno  WHERE "
            + MATRICULA + "= (select max(" + MATRICULA + ") from alumno)";
    private static final String SQL_LEER_ESTADO = "SELECT * FROM alumno WHERE "
            + ESTADO + " = ?";
    private static final String SQL_LEER = "SELECT * FROM alumno WHERE "
            + MATRICULA + " = ?";
    private final String SQL_ESTADO = "UPDATE alumno SET "
            + ESTADO + " =?"
            + "WHERE " + MATRICULA + "= ?";
    private static final String SQL_MODIFICA = "UPDATE alumno SET "
            + NOMBRE + " =?,"
            + APELLIDO_PATERNO + " =?,"
            + APELLIDO_MATERNO + " =?,"
            + DIA_NAC + " =?,"
            + MES_NAC + " =?,"
            + AÑO_NAC + " =?,"
            + EDAD + " =?,"
            + CIUDAD_NAC + " =?,"
            + ESTADO_NAC + " =?,"
            + SEXO + " =?,"
            + COLONIA + " =?,"
            + CALLE + " =?,"
            + NUMERO + " =?,"
            + PROFESION + " =?,"
            + EMAIL + " =?,"
            + FACEBOOK + " =?,"
            + TEL_CASA + " =?,"
            + TEL_CEL + " =?,"
            + ESCUELA + " =?,"
            + GRADO + " =? "
            + "WHERE " + MATRICULA + "= ?";
    private static final String SQL_INSERTAR = "INSERT INTO alumno"
            + "(" + NOMBRE
            + "," + APELLIDO_PATERNO
            + "," + APELLIDO_MATERNO
            + "," + DIA_NAC
            + "," + MES_NAC
            + "," + AÑO_NAC
            + "," + EDAD
            + "," + CIUDAD_NAC
            + "," + ESTADO_NAC
            + "," + SEXO
            + "," + COLONIA
            + "," + CALLE
            + "," + NUMERO
            + "," + PROFESION
            + "," + EMAIL
            + "," + FACEBOOK
            + "," + TEL_CASA
            + "," + TEL_CEL
            + "," + ESCUELA
            + "," + GRADO
            + "," + ESTADO + ""
            + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String SQL_CONSULTA_INSCRITO = "select nombre_CU from inscrito where matricula=?";
    public static String SQL_CONSULTA_NO_INSCRITO = "select * from curso where nombre_CU != "
            + "(select nombre_CU from inscrito where matricula=?)";
    public static String SQL_ELIMINAR_INSCRITO = "delete from inscrito where matricula=? and nombre_CU=?";
    public static String SQL_ELIMINAR_INSCRITO3 = "delete from inscrito3 where matricula=?";
    public static String SQL_OBTENER_ESTADO = "select estado from alumno where matricula=?";
    public static String SQL_LEER_INSCRITO1 = "SELECT nombre_CU FROM tanzstudiodb.inscrito where matricula=?";

    public CAlumno() {
        super();
    }

    /**
     * Metodo que transforma un resultSet en un objeto de alumno
     *
     * @param rs
     * @return
     * @throws Exception
     */
    private Alumno getObject(ResultSet rs) throws Exception {
        Alumno dtoAlumno = new Alumno();
        dtoAlumno.setMatricula(rs.getInt(MATRICULA));
        dtoAlumno.setNombre(rs.getString(NOMBRE));
        dtoAlumno.setApellidoPaterno(rs.getString(APELLIDO_PATERNO));
        dtoAlumno.setApellidoMaterno(rs.getString(APELLIDO_MATERNO));
        dtoAlumno.setDiaNac(rs.getInt(DIA_NAC));
        dtoAlumno.setMesNac(rs.getString(MES_NAC));
        dtoAlumno.setAñoNac(rs.getInt(AÑO_NAC));
        dtoAlumno.setEdad(rs.getInt(EDAD));
        dtoAlumno.setCiudadNac(rs.getString(CIUDAD_NAC));
        dtoAlumno.setEstadoNac(rs.getString(ESTADO_NAC));
        dtoAlumno.setSexo(rs.getString(SEXO));
        dtoAlumno.setColonia(rs.getString(COLONIA));
        dtoAlumno.setCalle(rs.getString(CALLE));
        dtoAlumno.setNumero(rs.getInt(NUMERO));
        dtoAlumno.setProfesion(rs.getString(PROFESION));
        dtoAlumno.setEmail(rs.getString(EMAIL));
        dtoAlumno.setFacebook(rs.getString(FACEBOOK));
        dtoAlumno.setTelefonoCasa(rs.getString(TEL_CASA));
        dtoAlumno.setTelefonoCelular(rs.getString(TEL_CEL));
        dtoAlumno.setEscuela(rs.getString(ESCUELA));
        dtoAlumno.setGrado(rs.getString(GRADO));
        dtoAlumno.setEstado(rs.getString(ESTADO));
        return dtoAlumno;
    }

    /**
     * Metodo que obtine todos los registros de los alumnos en la tabla alumno.
     *
     * @return
     * @throws Exception
     */
    public ArrayList leerTodo() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_SLECTEC_ALL);
        rs = ps.executeQuery();
        while (rs.next()) {
            result.add(getObject(rs));
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    /**
     * Metodo que busca en especifico a un alumno en la tabla alumno por medio
     * de su matricula
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public Alumno leer(int matricula) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno result = null;
        ps = conexion.prepareStatement(SQL_LEER);
        ps.setInt(1, matricula);
        System.out.println(ps);
        rs = ps.executeQuery();
        if (rs.next()) {
            result = getObject(rs);
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    public String[] leerCursoInscrito(int matricula) throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        String nombreCurso = "";
      
        ps = conexion.prepareStatement(SQL_LEER_INSCRITO1);
        ps.setInt(1, matricula);
        rs = ps.executeQuery();
        System.out.println(ps);
        while (rs.next()) {
            nombreCurso+=rs.getString("Nombre_CU")+"@";
           
        }
        nombreCurso= nombreCurso.substring(0, nombreCurso.length()-1);
        System.out.println("nombrecurso= "+nombreCurso);
        String[] cursos=nombreCurso.split("@");
        cerrar(ps);
        cerrar(rs);
        
        return cursos;
    }
/**
 * Este metodo busca en la tabla de alumno un grupo de alumnos en la base de
 * datos que concuerden con el estado con el parametro enviado alta,baja
 *
 * @param estado
 * @return
 * @throws Exception
 */
public ArrayList leer(String estado) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_LEER_ESTADO);
        ps.setString(1, estado);
        rs = ps.executeQuery();

        while (rs.next()) {
            result.add(getObject(rs));
        }
        cerrar(ps);
        cerrar(rs);

        return result;
    }
    public ArrayList ObtenerIdClase(String clase) throws SQLException, Exception{
        PreparedStatement ps = null;
        ResultSet rs;
        int id=-1;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_LEER_ESTADO);
        ps.setString(1, clase);
        rs = ps.executeQuery();

        if(rs.next()) {
            id=rs.getInt("ID");
        }
        cerrar(ps);
        cerrar(rs);

        return result;
    }

    /**
     * *
     * Metodo que ingresa en la tabla alumno en la base de datos a un alumno
     *
     * @param alumno
     * @throws Exception
     */
    public void agregar(Alumno alumno) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSERTAR);
        ps.setString(1, alumno.getNombre());
        ps.setString(2, alumno.getApellidoPaterno());
        ps.setString(3, alumno.getApellidoMaterno());
        ps.setInt(4, alumno.getDiaNac());
        ps.setString(5, alumno.getMesNac());
        ps.setInt(6, alumno.getAñoNac());
        ps.setInt(7, alumno.getEdad());
        ps.setString(8, alumno.getCiudadNac());
        ps.setString(9, alumno.getEstadoNac());
        ps.setString(10, alumno.getSexo());
        ps.setString(11, alumno.getColonia());
        ps.setString(12, alumno.getCalle());
        ps.setInt(13, alumno.getNumero());
        ps.setString(14, alumno.getProfesion());
        ps.setString(15, alumno.getEmail());
        ps.setString(16, alumno.getFacebook());
        ps.setString(17, alumno.getTelefonoCasa());
        ps.setString(18, alumno.getTelefonoCelular());
        ps.setString(19, alumno.getEscuela());
        ps.setString(20, alumno.getGrado());
        ps.setString(21, alumno.getEstado());
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * Metodo que modifica un registro e la base de datos
     *
     * @param alumno
     * @throws Exception
     */
    public void modificar(Alumno alumno) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_MODIFICA);
        ps.setString(1, alumno.getNombre());
        ps.setString(2, alumno.getApellidoPaterno());
        ps.setString(3, alumno.getApellidoMaterno());
        ps.setInt(4, alumno.getDiaNac());
        ps.setString(5, alumno.getMesNac());
        ps.setInt(6, alumno.getAñoNac());
        ps.setInt(7, alumno.getEdad());
        ps.setString(8, alumno.getCiudadNac());
        ps.setString(9, alumno.getEstadoNac());
        ps.setString(10, alumno.getSexo());
        ps.setString(11, alumno.getColonia());
        ps.setString(12, alumno.getCalle());
        ps.setInt(13, alumno.getNumero());
        ps.setString(14, alumno.getProfesion());
        ps.setString(15, alumno.getEmail());
        ps.setString(16, alumno.getFacebook());
        ps.setString(17, alumno.getTelefonoCasa());
        ps.setString(18, alumno.getTelefonoCelular());
        ps.setString(19, alumno.getEscuela());
        ps.setString(20, alumno.getGrado());
        ps.setInt(21, alumno.getMatricula());
        System.out.println(ps);
        ps.executeUpdate();

        cerrar(ps);
    }

    /**
     * Este metodo modificia el estado de un registro en especifico en la base
     * de datos
     *
     * @param alumno
     * @throws Exception
     */
    public void ModifEstado(Alumno alumno) throws Exception {
        PreparedStatement ps = null;
        System.out.println(SQL_ESTADO);
        ps = conexion.prepareStatement(SQL_ESTADO);
        ps.setString(1, alumno.getEstado());
        ps.setInt(2, alumno.getMatricula());
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * Metodo que obtiene la ultima matricula registrada en la tabla de alumno retorna un entero
     *
     * @return
     * @throws Exception
     */
    public int obtenerMatricula() throws Exception {
        int result = 0;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_ID);
        ResultSet rs = null;
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getInt(MATRICULA);
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    /**
     * *
     * Este metodo que ingresa la amtricula en la base de datos la matricula del
     * alumno en la tabla puente
     *
     * @param matricula
     * @throws Exception
     */
    public void tiene(int matricula) throws Exception {
        PreparedStatement ps = null;
        System.out.println(SQL_TIENE);
        ps = conexion.prepareStatement(SQL_TIENE);
        ps.setInt(1, matricula);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * Este metodo ingresa la matricula en la tabla puente tiene_problema
     *
     * @param matricula
     * @throws Exception
     */
    public void tieneProblema(int matricula) throws Exception {
        PreparedStatement ps = null;
        System.out.println(SQL_TIENE);
        ps = conexion.prepareStatement(SQL_TIENE_PROBLEMA);
        ps.setInt(1, matricula);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * Este metodo inserta la matricula en la tabla puente realiza
     *
     * @param matricula
     * @throws Exception
     */
    public void realiza(int matricula) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_REALIZA);
        ps.setInt(1, matricula);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * metodo que ingresa en la tabla puente inscrito la matricula y el nombre
     * del curso
     *
     * @param matricula
     * @param curso
     * @throws Exception
     */
    public void inscrito(int matricula, String curso) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSCRITO);
        ps.setInt(1, matricula);
        ps.setString(2, curso);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    public ArrayList consultaCursosInscrito(int matricula) throws Exception {
        ArrayList alResult = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs;
        ps = conexion.prepareStatement(SQL_CONSULTA_INSCRITO);
        ps.setInt(1, matricula);
        System.out.println(ps);
        rs = ps.executeQuery();
        while (rs.next()) {
            alResult.add(rs.getString("nombre_CU"));
        }
        cerrar(ps);
        cerrar(rs);
        return alResult;
    }

    /***
     * metodo que consulta en la base de datos a los cursos que esta inscrito un alumno
     * @param matricula
     * @return
     * @throws Exception 
     */
    public ArrayList<String> consultaNoIscrito(int matricula)throws Exception{
        ArrayList<String> alResult = new ArrayList<String>();
        PreparedStatement ps = null;
        ResultSet rs;
        ps = conexion.prepareStatement(SQL_CONSULTA_NO_INSCRITO);
        ps.setInt(1, matricula);
        System.out.println(ps);
        rs = ps.executeQuery();
        while (rs.next()) {
            alResult.add(rs.getString("nombre_CU"));
        }
        cerrar(ps);
        cerrar(rs);
        return alResult;
    }

    /***
     * metodo que elimina en la tabla inscrito un registro
     * @param matricula
     * @param nombreCurso
     * @throws Exception 
     */
    public void EliminaIscrito(int matricula, String nombreCurso) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_ELIMINAR_INSCRITO);
        ps.setInt(1, matricula);
        ps.setString(2, nombreCurso);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * *
     * Metodo que ingresa en la tabla puente inscrito2
     *
     * @param matricula
     * @param idClase
     * @param curso
     * @throws Exception
     */
    public void inscrito3(int matricula, int idClase, String curso) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSCRITO3);
        ps.setInt(1, matricula);
        ps.setInt(2, idClase);
        ps.setString(3, curso);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    /**
     * Este metodo que ingresa a un alumno y sus tablas peuntes
     *
     * @param alumno
     * @param curso
     * @throws Exception
     */
    public void alta(Alumno alumno, ArrayList curso) throws Exception {
        agregar(alumno);
        int mat = obtenerMatricula();
        realiza(mat);
        String nomCurso = "";
        for (int i = 0; i < curso.size(); i++) {
            if (curso.get(i) instanceof String) {
                inscrito(mat, (String) curso.get(i));
                nomCurso = (String) curso.get(i);
            } else {
                System.out.println("matricula=" + mat + " idclase :" + curso.get(i) + "nomcurso :" + nomCurso);
                inscrito3(mat, (int) curso.get(i), nomCurso);
            }
        }
    }
    /***
     * metodo que registra en la tablas puente inscrito y inscrito2 relacionada
     * con el nombre del cuso y e alumno al cual pertenece la matricula
     * @param matricula
     * @param curso
     * @throws Exception 
     */
    public void alta(int matricula,ArrayList curso) throws Exception{
        String nomCurso="";
        for (int i = 0; i < curso.size(); i++) {
            if (curso.get(i) instanceof String) {
                System.out.println("inscrito");
                inscrito(matricula, (String) curso.get(i));
                nomCurso = (String) curso.get(i);
            } else {
                System.out.println("matricula=" + matricula + " idclase :" + curso.get(i) + "nomcurso :" + nomCurso);
                inscrito3(matricula, (int) curso.get(i), nomCurso);
            }
        }
    }

    static final String SQL_EstadoCON = "SELECT * FROM alumno WHERE estado=? and "
            + "(nombre_A LIKE ?or ApellidoPaterno_A LIKE ? or  ApellidoMaterno_A LIKE ?)";

    /**
     * Este metodo hace una busqueda en la tabla alumno filtrando por el nombre
     * y el estado
     *
     * @param estado
     * @param cadena
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList ConsultaEstadoConcidencia(String estado, String cadena) throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_EstadoCON);
        ps.setString(1, estado);
        ps.setString(2, "%" + cadena + "%");
        ps.setString(3, "%" + cadena + "%");
        ps.setString(4, "%" + cadena + "%");
        System.out.println(ps);
        rs = ps.executeQuery();

        while (rs.next()) {
            result.add(getObject(rs));
        }

        cerrar(ps);
        cerrar(rs);

        return result;
    }
    static final String SQL_CONCIDENCIA = "SELECT * FROM alumno WHERE "
            + "(nombre_A LIKE ?or ApellidoPaterno_A LIKE ? or  ApellidoMaterno_A LIKE ?)";

    public ArrayList ConsultaConcidencia(String cadena) throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_CONCIDENCIA);
        ps.setString(1, "%" + cadena + "%");
        ps.setString(2, "%" + cadena + "%");
        ps.setString(3, "%" + cadena + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            result.add(getObject(rs));
        }
        cerrar(ps);
        cerrar(rs);

        return result;
    }
    static final String SQL_CONCIDENCIA2 = "SELECT * FROM alumno WHERE matricula=? and estado=?";

    /**
     * Este metodo consulta en la tabla de alumno a el alumno que coencida con
     * el estado y la matricula enviados
     *
     * @param matricula
     * @param estado
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public Alumno consultaConcidencia(int matricula, String estado) throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        Alumno result = new Alumno();
        ps = conexion.prepareStatement(SQL_CONCIDENCIA2);
        ps.setInt(1, matricula);
        ps.setString(2, estado);
        System.out.println(ps);
        rs = ps.executeQuery();

        if (rs.next()) {
            result = getObject(rs);
        }
        cerrar(ps);
        cerrar(rs);

        return result;
    }
    private static String SQL_OBTENER_NOMBRE = "SELECT "
            + "nombre_A, apellidoPaterno_a, apellidoMaterno_a FROM alumno"
            + " WHERE matricula = ?";

    /**
     * *
     * Metodo que busca en la tabla alumno y genere una cadena con el nombre
     * completo del salumno
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public String obtenerNombre(int matricula) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        ps = conexion.prepareStatement(SQL_OBTENER_NOMBRE);
        ps.setInt(1, matricula);
        System.out.println(ps);
        rs = ps.executeQuery();
        if (rs.next()) {
            result += " " + rs.getString(NOMBRE);
            result += " " + rs.getString(APELLIDO_PATERNO);
            result += " " + rs.getString(APELLIDO_MATERNO);
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }
    
    public void bajaInscrito3(int matricula) throws SQLException, Exception{
      PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_ELIMINAR_INSCRITO3);
        ps.setInt(1, matricula);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);   
    }
}
