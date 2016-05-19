/**
 * ControlProfesor.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import Modelo.Profesor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que lleva el control de todas las operaciones en la base de datos
 * referentes a los profesores y que hereda de ConexionBD para conectar con la 
 * base de datos
 * @author David Omar Cabrera bernal
 */
public class CProfesor extends ConexionBD{
    
    //Variables que almacenan la columna identificada en la base de datos
    private static final String idProfesor = "id_Profesor";
    private static final String nombreCurso = "nombre_CU";
    private static final String nombreProfesor = "nombre_P";
    private static final String apellidoPaterno = "apellidoPaterno_P";
    private static final String apellidoMaterno = "apellidoMaterno_P";
    //Variable que guarda el query que obtiene el nombre completo de los profesores
    private static final String obtenerProfesores ="SELECT " + idProfesor + ", "
            + nombreProfesor + ", " + apellidoPaterno + ", " + apellidoMaterno + 
            " FROM profesor";
    //Variable que busca y obtiene el nombre completo de un profesor en especifico
    private static final String busquedaEspecifica = "SELECT id_Profesor FROM profesor "
            + "WHERE nombre_P = ? AND apellidoPaterno_P = ? AND apellidoMaterno_P = ?";
    //Variable que guarda el query que obtiene el profesor de un curso en especifico
    private static final String busquedaProfesorDeCurso = "SELECT profesor." + idProfesor +",profesor." 
            + nombreProfesor + ", profesor." + apellidoPaterno + ", profesor." 
            + apellidoMaterno + " FROM profesor INNER JOIN impartido ON profesor." 
            + idProfesor + " = impartido." + idProfesor + " AND impartido." 
            + nombreCurso + " = ?";
    //Variable que guarda el query en el que se da de alta a un profesor
    private static final String insertar = "INSERT INTO profesor"
        + "(nombre_P,apellidoPaterno_P,apellidomaterno_P,telCasa_P,telCel_P"
        + ",email_P,facebook_P) VALUES(?,?,?,?,?,?,?)";
    //Variable que gaurda el query que consulta todos los profesore con todos sus datos
    private static final String consulta = "SELECT * FROM profesor";
    //Variable que guarda el query que elimina a un profesor
    private static final String baja = "DELETE FROM profesor WHERE id_Profesor = ?";
    //Variable que guarda el query que obtiene el último profesor registrado
    private static final String ultimoRegistro = "SELECT * FROM profesor "
            + "where id_Profesor = ( SELECT MAX(id_Profesor) FROM profesor);";
    //Variable que guarda el query para modificar datos del profesor
    private static final String modificacion = "UPDATE profesor SET nombre_P=?, "
        + "apellidoPaterno_P=?, apellidoMaterno_P=?,telCasa_P=?, telCel_P=?,"
        + "email_P=?,facebook_P=? WHERE id_Profesor=?";
    //Variable que guarda el query para obtener el nombre completo del profesor
    // sin el id que los identifica
    private static final String obtenerProfesoresSinId = "SELECT nombre_P,"
        + "apellidoPaterno_P, apellidoMaterno_P FROM profesor;";
    //Variableque guarda el query utilizado para obtener el la informacion
    //completa de un profesor en especifico
    private static final String busquedaEspecificaProfesor = "SELECT nombre_P, "
            + "apellidoPaterno_P, apellidoMaterno_P, telCasa_P, telCel_P, email_P, "
            + "facebook_P FROM profesor WHERE id_Profesor=?";
    //Variable que guarda el query que elimina la relacion entre un curso y un profesor
    private static final String eliminarRelacionCursoYProfesor = "DELETE FROM "
            + "impartido WHERE id_Profesor=?;";
    //Variable que guarda el query que establece la relacion entre un curso y un profesor
    private static final String agregarUnaRelacion = "INSERT INTO impartido"
            + "(nombre_CU, id_Profesor) VALUES(?,?)";
    //Variable que guarda el query que obtiene el id que puede tener un profesor
    private static final String obtenerNumero = "SELECT `AUTO_INCREMENT` FROM  "
            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'tanzstudiodb' "
            + "AND TABLE_NAME = 'profesor'";
    //Variable que guarda el query que obtiene los cursos que imparte un profesor
    private static final String obtenerCursosDeProfesor = "SELECT " + nombreCurso 
            + " FROM impartido WHERE " + idProfesor + "=?";
    //Variable que guarda el query que obtiene los profesores mediante una
    //coincidencia a partir de un número
    private static final String coincidenciaMatricula = "SELECT * FROM profesor "
            + "WHERE " + idProfesor + " LIKE ?";
    //Variable que guarda el query que obtiene los profesores mediante una
    //coincidencia a partir de una o mas letras
    private static final String coincidenciaNombre = "SELECT * FROM profesor WHERE " 
            + nombreProfesor + " LIKE ?";
    //Variable que guarda un query que sirve para obtener todos los de un profesor
    //en especifico
    private static final String busquedaPorId = "SELECT * FROM profesor WHERE "
            + idProfesor +"=?";
    
    /**
     * Constructor que inicializa la clase del profesor
     */
    public CProfesor() {
        super();
    }
    
    /**
     * Método que obtiene los profesores existente en la base de datos
     * @return profesores
     * @throws SQLException 
     */
    public ArrayList<Profesor> obtenerProfesores() throws SQLException, Exception {
        //ArrayList que gaurdara los profesores existentes
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para obtener los profesores
        ps = conexion.prepareStatement( obtenerProfesores );
        //Se ejecuta el query en un resulset que gaurda el resultado
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            //Se guardan los resultados en una instancia de profesor
            //que se agrega al arraylist de profesores
            Profesor profesor = new Profesor();
            profesor.setIdProfesor( rs.getInt( idProfesor ) );
            profesor.setNombre( rs.getString( nombreProfesor ) );
            profesor.setApellidoPaterno( rs.getString( apellidoPaterno ) );
            profesor.setApellidoMaterno( rs.getString( apellidoMaterno ) );
            profesores.add( profesor );
        }
        //Se cierra las conexiónes con base de datos
        cerrar(ps);
        cerrar(rs);
        return profesores;
    }
    
    /**
     * Método que busca a un profesor por medio de su nombre completo
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int busqueda( String nombre, String apellidoPaterno, 
            String apellidoMaterno ) throws SQLException, Exception {
        int idProfesor = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para obtener el id del profesor
        ps = conexion.prepareStatement( busquedaEspecifica );
        //Se indican los valores que se agregaran al query de bsuqueda
        ps.setString(1, nombre);
        ps.setString(2, apellidoPaterno);
        ps.setString(3, apellidoMaterno);
        //Se ejecuta el query
        rs = ps.executeQuery();
        while (rs.next()) {
            //Se guarda el id del profesor
            idProfesor = rs.getInt("id_Profesor");
        }
        //Se cierra las conexiónes con base de datos
        cerrar(ps);
        cerrar(rs);
        return idProfesor;
    }
    
    /**
     * Método que obtiene los profesores que imparten un curso
     * @param nombreCurso
     * @return
     * @throws SQLException 
     */
    public ArrayList<Profesor> obtenerProfesoresPorCurso( String nombreCurso ) throws SQLException {
        //ArrayList que gaurdara los profesores existentes
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para obtener los profesores por curso
        ps = conexion.prepareStatement( busquedaProfesorDeCurso );
        ps.setString( 1, nombreCurso );
        //Se ejecuta el query y se gaurda los resultado en la variable rs
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            //Se guardan los resultados en una instancia de profesor
            //que se agrega al arraylist de profesores
            Profesor profesor = new Profesor();
            profesor.setIdProfesor( rs.getInt( idProfesor ) );
            profesor.setNombre( rs.getString( nombreProfesor ) );
            profesor.setApellidoPaterno( rs.getString( apellidoPaterno ) );
            profesor.setApellidoMaterno( rs.getString( apellidoMaterno ) );
            profesores.add( profesor );
        }
        return profesores;
    }
    
    /**
     * Método que realiza una alta de profesor en la base de datos y retorna 
     * el número del nuevo profesor
     * @param profesor
     * @return ultimoId
     * @throws Exception 
     */
    public int alta(Profesor profesor) throws Exception {
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para insertar al nuevo profesor
        ps = conexion.prepareStatement( insertar );
        //Se agregan al query los datos del profesor
        ps.setString( 1, profesor.getNombre() );
        ps.setString( 2, profesor.getApellidoPaterno() );
        ps.setString( 3, profesor.getApellidoMaterno() );
        ps.setString( 4, profesor.getTelefonoCasa() );
        ps.setString( 5, profesor.getTelefonoCel() );
        ps.setString( 6, profesor.getEmail() );
        ps.setString( 7, profesor.getFacebook() );
        //Se ejecuta el query
        ps.executeUpdate();
        //Se cierra la conexion con la base de datos
        cerrar(ps);
        return ultimoId();
    }
    
    /**
     * Método que recibe los datos de un profesor para su posterior modificación
     * en la base de datos
     * @param profesor
     * @throws Exception 
     */
    public void modificacion( Profesor profesor ) throws Exception {
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para modificar los datos
        //del profesor
        ps = conexion.prepareStatement( modificacion );
        //Se agregan al query los datos del profesor
        ps.setString( 1, profesor.getNombre() );
        ps.setString( 2, profesor.getApellidoPaterno() );
        ps.setString( 3, profesor.getApellidoMaterno() );
        ps.setString( 4, profesor.getTelefonoCasa() );
        ps.setString( 5, profesor.getTelefonoCel() );
        ps.setString( 6, profesor.getEmail() );
        ps.setString( 7, profesor.getFacebook() );
        ps.setInt( 8, profesor.getIdProfesor() );
        //Se ejecuta el query
        ps.executeUpdate();
        //Se cierra la conexion con la base de datos
        cerrar(ps);
    }
    
    /**
     * Método que recibe el número del profesor para darlo de baja de la base de datos
     * @param id
     * @throws Exception 
     */
    public void baja(int id) throws Exception {
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para eliminar un profesor
        ps = conexion.prepareStatement( baja );
        //Se agrega el id del profesor al query
        ps.setInt( 1, id );
        //Se ejecuta el query
        ps.execute();
        //Se cierra la conexion con la base de datos
        cerrar( ps );
    }
    
    /**
     * Método que consulta  los profesores existentes en la base de datos y los 
     * retorna
     * @return
     * @throws Exception 
     */
    public ArrayList<Profesor> Consultas() throws Exception {
        ResultSet rs;
        //ArrayList que guardara los profesores existentes
        ArrayList<Profesor> resultado = new ArrayList<Profesor>();
	PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para las consultas de profesores
        ps = conexion.prepareStatement( consulta );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se llama al metodo que retorn a una instancia del profesor que
            //sera agregado al arraylist
            resultado.add( getProfesor( rs ) );
        }
        //Se cierran las conexiones
        cerrar( ps );
        cerrar( rs );
        return resultado;
    }
    
    /**
     * Método que recibe un resulset con una tupla de la tabla de profesores
     * para agregarlo en una instancia de profesor que sera retornada
     * @param rs
     * @return datosProfesor
     * @throws Exception 
     */
    private Profesor getProfesor( ResultSet rs ) throws Exception {
        Profesor datosProfesor = new Profesor();
        //Se toman los datos del resulset y se agregagn a la instancia de un profesor
        datosProfesor.setIdProfesor( rs.getInt( "id_Profesor" ) );
        datosProfesor.setNombre( rs.getString( "nombre_P" ) );
        datosProfesor.setApellidoPaterno( rs.getString( "apellidoPaterno_P" ) );
        datosProfesor.setApellidoMaterno( rs.getString( "apellidoMaterno_P" ) );
        datosProfesor.setTelefonoCasa( rs.getString( "telCasa_P" ) );
        datosProfesor.setTelefonoCel( rs.getString( "telCel_P" ) );
        datosProfesor.setEmail( rs.getString( "email_P" ) );
        datosProfesor.setFacebook( rs.getString( "facebook_P" ) );
        return datosProfesor;
    }
    
    /**
     * Método que busca el último profesor registrado para retornar su número de 
     * profesor
     * @return id
     * @throws SQLException
     * @throws Exception 
     */
    private int ultimoId() throws SQLException, Exception {
        int id = 0;  //Variable que guarda el id del profesor
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para obtener el id del
        //último profesor
        ps = conexion.prepareStatement( ultimoRegistro );
        //Se ejecuta el query 
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se guarda el del profesor
            id = rs.getInt( "id_Profesor" );
        }
        //Se cierran las conexiones con las bases de datos
        cerrar( ps );
        cerrar( rs );
        return id;
    }
    
    /**
     * Método que busca y retorna el nombre completo de los profesores
     * @return profesores
     * @throws SQLException 
     */
    public ArrayList<String> obtenerProfesoresSinId( ) throws SQLException {
        //ArrayList que guardara los profesores que se obtengan
        ArrayList<String> profesores = new ArrayList<String>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para obtener el nombre completo
        //de los profesores
        ps = conexion.prepareStatement( obtenerProfesoresSinId );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se obtiene el nombre completo del profesor y se guarda en el arraylist
            //de los profesores
            String nombreCompleto;
            nombreCompleto = rs.getString( "nombre_P" );
            nombreCompleto = nombreCompleto + " " + rs.getString( "apellidoPaterno_P" );
            nombreCompleto = nombreCompleto + " " + rs.getString( "apellidoMaterno_P" );
            profesores.add( nombreCompleto );
        }
        return profesores;
    }
    
    /**
     * Método que busca a un profesor por medio de su número de profesor
     * @param numProfesor
     * @return
     * @throws SQLException 
     */
    public ArrayList<String> busquedaEspecificaProfesor( int numProfesor ) 
            throws SQLException {
        //ArrayList que guardara los datos del profesor a obtener
        ArrayList<String> datosProfesor = new ArrayList<String>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para obtener el nombre completo
        //de un profesor en especifico
        ps = conexion.prepareStatement( busquedaEspecificaProfesor );
        //Se agrega el número del profesor a buscar al query
        ps.setInt( 1, numProfesor );
        //Se ejecuta el query
        rs = ps.executeQuery();
        
        while (rs.next()) {
            //Se agregan los datos al profesor al arrayList de los datos del profesor
            datosProfesor.add( rs.getString( "nombre_P" ) );
            datosProfesor.add( rs.getString( "apellidoPaterno_P" ) );
            datosProfesor.add( rs.getString( "apellidoMaterno_P" ) );
            datosProfesor.add( rs.getString( "telCasa_P" ) );
            datosProfesor.add( rs.getString( "telCel_P" ) );
            datosProfesor.add( rs.getString( "email_P" ) );
            datosProfesor.add( rs.getString( "facebook_P" ) );
        }
        return datosProfesor;
    }
    
    /**
     * Método que elimina la relacion existen entre un profesor y un curso, mediante
     * el número del profesor
     * @param numProfesor
     * @throws Exception 
     */
    public void eliminarRelacion( int numProfesor ) throws Exception {
        PreparedStatement ps = null;
        //Se indica el query que eliminara la relacion entre profesor y curso
        ps = conexion.prepareStatement( eliminarRelacionCursoYProfesor  );
        //Se agrega el número del profesor al query
        ps.setInt( 1, numProfesor );
        //Se ejecuta el query
        ps.execute();
        //Se cierra la conexion con base de datos
        cerrar(ps);
    }
    
    /**
     * Método que agregar una relacion entre el profesor y el curso, mediante
     * el número del profesor y el nombre del curso
     * @param numProfesor
     * @param nombreCurso
     * @throws Exception 
     */
    public void agregarRelacion( int numProfesor, String nombreCurso ) throws Exception {
        PreparedStatement ps = null;
        //Se indica el query que agrega una relacion entre nombre y curso
        ps = conexion.prepareStatement( agregarUnaRelacion );
        //Se agregan el nombre del curso y el número del profesor al query
        ps.setString( 1, nombreCurso );
        ps.setInt( 2, numProfesor );
        //Se ejecuta el query 
        ps.execute();
        //Se cierra la conexion con la base de datos
        cerrar(ps);
    }
    
    /**
     * Método que obtiene el número que tendra un profesor cuando se de alta
     * @return numero
     * @throws SQLException 
     */
    public int obtenerNumeroProfesor() throws SQLException, Exception {
        int numero = 0;
        PreparedStatement ps = null;
        ResultSet rs;
        //Se indica el query que obtiene el número que puede tener un profesor
        ps = conexion.prepareStatement( obtenerNumero );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while ( rs.next() ) {
            //Se guarda el número
            numero = rs.getInt( "AUTO_INCREMENT" );
        }
        //Se cierran las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return numero;
    }
    
    /**
     * Método que obtiene los cursos que imparte un profesor
     * @param numProfesor
     * @return
     * @throws SQLException 
     */
    public ArrayList<String> obtenerCursosDeProfesor( int numProfesor ) 
            throws SQLException, Exception {
        //Arraylist que guarda los cursos que se vana obtener
        ArrayList<String> cursos = new ArrayList<String>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que obtiene los cursos de un profesor
        ps = conexion.prepareStatement( obtenerCursosDeProfesor );
        //Se agrega el numero del profesor al query
        ps.setInt( 1, numProfesor );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while( rs.next() ) {
            //Se guarda el nombre del curso en el arraylist
            cursos.add( rs.getString( nombreCurso ) );
        }
        //Se cierran las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return cursos;
    }
    
    /**
     * Método que busca los profesores mediante la coincidencia que tenga con 
     * la matricula que recibe
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Profesor> busquedaCoincidenciaMatricula( String matricula ) 
            throws SQLException, Exception {
        //Arraylist que guarda los profesores que se obtendran
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se va a utilizar para busqueda de profesores
        //por coincidencias con la matricula
        ps = conexion.prepareStatement( coincidenciaMatricula );
        //Se agrega la matricula recibida en el query
        ps.setString( 1, "%" + matricula + "%" );
        //Se ejecuta el query
        rs = ps.executeQuery();
        while( rs.next() ) {
            //Se llama al metodo que retorna el profesor que se tenga en el resulset
            //para guardarlo en el arraylist de profesores
            profesores.add( getProfesor( rs ) );
        }
        //Se cierran las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return profesores;
    }
    
    /**
     * Método que busca a un profesor mediante las coincidencias por nombre
     * con una cadena de caracteres recibida
     * @param nombre
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Profesor> busquedaCoincidenciaNombre( String nombre ) throws SQLException, Exception {
        //Arraylist que guarda los profesores que se obtendran
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        ResultSet rs;
        PreparedStatement ps = null;
        //Se indica el query que se utilizara para la busqueda de los profesores
        //mediante la coincidencia por nombres
        ps = conexion.prepareStatement( coincidenciaNombre );
        //Se agrega el nombre recibido al aquery
        ps.setString( 1,  "%" + nombre + "%" );
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            profesores.add( getProfesor( rs ) );
        }
        //Se cierran las conexiones con la base de datos
        cerrar( ps );
        cerrar( rs );
        return profesores;
    }
}
