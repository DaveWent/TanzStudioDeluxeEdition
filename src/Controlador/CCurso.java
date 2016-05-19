/**
 * ConexionBD.java
 * Version 2
 * 11/11/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import Modelo.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que lleva el control de todas las operaciones en la base de datos
 * referentes a los cursos y que hereda de ConexionBD para coenctar con la 
 * base de datos
 * @author David Omar Cabrera bernal
 */
public class CCurso extends ConexionBD {
    
    private static final String nombreCurso = "nombre_CU";
    private static final String tipo = "tipo";
    private static final String requiereSilencio = "requiereSilencio";
    private static final String fechaInicio = "fechaInicio_CU";
    private static final String fechaFin = "fechaFin_CU";
    private static final String consulta = "SELECT * FROM curso";
    private static final String idProfesor = "id_Profesor";
    private static final String salones = "salonesDisponibles";
    private static final String insertarCursoEsporadico = "INSERT INTO curso ("+nombreCurso+
            "," +tipo+ "," +requiereSilencio+ "," +fechaInicio+ "," +fechaFin+ ")"
            + " VALUES (?,?,?,?,?)";
    private static final String insertarCursoNormal = "INSERT INTO curso ("+nombreCurso+
            "," +tipo+ "," +requiereSilencio+ ") VALUES (?,?,?)";
    private static final String baja = "DELETE FROM curso WHERE "+nombreCurso+ "= ?";
    private static final String busqueda = "SELECT * FROM curso WHERE " 
            +nombreCurso+ "= ?";
    private static final String modificacionCursoNormal = "UPDATE curso SET " + nombreCurso 
            +"=?," + tipo + "=?," +requiereSilencio + "=? WHERE nombre_CU=? ";
    private static final String modificacionCursoEsporadico = "UPDATE curso SET " + nombreCurso 
            +"=?," + tipo + "=?," +requiereSilencio + "=?," + fechaInicio + "=?," 
            + fechaFin + "=? WHERE nombre_CU=? ";
    private static final String eliminarRelaciones = " DELETE FROM impartido WHERE " 
            + nombreCurso + "=?";
    private static final String insertarEnImpartido = "INSERT INTO impartido "
            + "(" + nombreCurso + "," + idProfesor + ") VALUES (?,?)";
    private static final String insertaSalonesPorDefault = "INSERT INTO salones (" 
            + salones + ") VALUES (2);";
    private static final String obtieneSalones = "SELECT * FROM salones";
    private static final String actualizaSalones = "UPDATE salones SET " + salones 
            + " = ? WHERE " + salones + " =?;";
    private static final String busquedaCursos = "SELECT nombre_CU FROM curso";
    private static final String tipoCurso="select tipo from curso where nombre_CU=?";
    private static final String busquedaClasesEspecifica = 
        "SELECT clase.dia, clase.salon, clase.horaInicio, clase.horaFin FROM clase"
        + " INNER JOIN posee ON clase.id_Clase = posee.id_Clase AND posee.nombre_CU = ?";
    private static final String contadorRegistrosClases = 
        "SELECT COUNT(*) FROM clase INNER JOIN posee ON clase.id_Clase = posee.id_Clase "
        + "AND posee.nombre_CU = ? ";
    private static final String busquedaId = "SELECT id_Clase FROM clase WHERE "
        + "dia=? AND salon=? AND horaInicio=? AND horaFin=?";
    private static final String obtenerAlumnosEnCurso = "SELECT matricula FROM "
            + "inscrito WHERE " + nombreCurso + "=?";
    private static final String obtenerCursosEsporadicos = "SELECT " + nombreCurso 
            + " FROM curso WHERE " + tipo + "='esporadico'";
    
    /**
     * Método que toma todos los cursos existentes en la base de datos junto con 
     * todos sus atributosy los retorna para ser utilizados en donde requiere
     * @return ArrayList<Curso>
     * @throws SQLException 
     */
    public ArrayList<Curso> consultaGeneral() throws SQLException {
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consulta );
        rs = ps.executeQuery();
        
        //Realiza un ciclo donde recorre todas las tuplas de la tabla de cursos
        // y los inserta en un objeto curso que posteriormente sera cargado
        //en un arraylist
        while ( rs.next() ) {
            Curso curso = new Curso();
            curso.setNombreCurso( rs.getString( nombreCurso ) );
            curso.setTipo( rs.getString( tipo ) );
            curso.setRequiereSilencio( rs.getString( requiereSilencio ) );
            curso.setFechaInicio( rs.getString( fechaInicio ) );
            curso.setFechaFin( rs.getString( fechaFin ) );
            cursos.add( curso );
        }
        return cursos;
    }
    
    /**
     * Método que realiza una alta de un curso en la base de datos, tomando
     * los datos de un objeto recibido
     * @param curso
     * @throws SQLException
     * @throws Exception 
     */
    public void altaCurso( Curso curso, String tipoCurso ) throws SQLException, Exception {
        PreparedStatement ps = null;
        if( tipoCurso.equalsIgnoreCase("esporadico") ) {
            ps = conexion.prepareStatement( insertarCursoEsporadico );
        } else {
            ps = conexion.prepareStatement( insertarCursoNormal );
        }
        
        ps.setString( 1, curso.getNombreCurso() );
        ps.setString( 2, curso.getTipo() );
        ps.setString( 3, curso.getRequiereSilencio() );
        
        if( tipoCurso.equalsIgnoreCase("esporadico") ) {
            ps.setString( 4, curso.getFechaInicio() );
            ps.setString( 5, curso.getFechaFin() );
        } 
        
        ps.executeUpdate();
        //Se cierran el flujo de conexión
        cerrar(ps);
    }
    
    /**
     * Metodo que retorna una lista de los cursos existentes, pero solo devolviendo
     * los nombres de los cursos
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<String> busquedaDeCursos() throws SQLException, Exception {
        ResultSet rs;
        ArrayList<String> resultado = new ArrayList<String>();
	PreparedStatement ps = null;
        ps = conexion.prepareStatement( consulta );
        rs = ps.executeQuery();
        while ( rs.next() ) {
            resultado.add( rs.getString( nombreCurso ) );
        }
        //Se cierran los flujos de conexión
        cerrar( ps );
        cerrar( rs );
        return resultado;
    }
    
    /**
     * Método que realiza una baja en la base de datos, recibe el nombre del curso
     * y realiza la baja
     * @param nombreCurso
     * @throws SQLException
     * @throws Exception 
     */
    public void eliminar( String nombreCurso ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( baja );
        ps.setString( 1, nombreCurso );
        ps.executeUpdate();
        //Se cierra el flujo de conexión
        cerrar(ps);
    }
    
    /**
     * Método que realiza una busqueda especifica de un curso, recibe el nombre
     * @param nombreCurso
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public Curso busquedaCurso( String nombreCurso ) throws SQLException, Exception {
        Curso resultado = new Curso();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( busqueda);
        ps.setString( 1, nombreCurso );
        rs = ps.executeQuery();
        while ( rs.next() ) {
            resultado.setNombreCurso( rs.getString( this.nombreCurso ) );
            resultado.setTipo( rs.getString( tipo ) );
            resultado.setRequiereSilencio( rs.getString( requiereSilencio ) );
            resultado.setFechaInicio( rs.getString( fechaInicio ) );
            resultado.setFechaFin( rs.getString( fechaFin ) );
        }
        cerrar( ps );
        cerrar( rs );
        return resultado;
    }
    
    public void modificacion ( Curso curso, String nombreOriginalCurso ) 
            throws Exception {
        PreparedStatement ps = null;
        if( curso.getTipo().equalsIgnoreCase("esporadico") ) {
            ps = conexion.prepareStatement( modificacionCursoEsporadico );
        } else {
            ps = conexion.prepareStatement( modificacionCursoNormal );
        }
        
        ps.setString( 1, curso.getNombreCurso() );
        ps.setString( 2, curso.getTipo() );
        ps.setString( 3, curso.getRequiereSilencio() );
        if( curso.getTipo().equalsIgnoreCase("esporadico") ) {
            ps.setString( 4, curso.getFechaInicio() );
            ps.setString( 5, curso.getFechaFin() );
            ps.setString( 6, nombreOriginalCurso );
        } else {
            ps.setString( 4, nombreOriginalCurso );
        }
        
        System.out.println( ps );
        ps.executeUpdate();
        cerrar( ps );
    }
    
    public void eliminarRelaciones( String nombreCurso ) throws SQLException, 
            Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( eliminarRelaciones );
        ps.setString( 1, nombreCurso );
        ps.executeUpdate();
        cerrar( ps );
    }
    
    public void agregarRelacionesDeCursosYProfesores( String nombreCurso, 
            int idProfesor ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( insertarEnImpartido );
        ps.setString( 1, nombreCurso );
        ps.setInt( 2, idProfesor );
        ps.executeUpdate();
        cerrar(ps);
    }
    
    public void actualizaSalones( int nuevoNumSalones, int viejoNumSalones ) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( actualizaSalones );
        ps.setInt( 1, nuevoNumSalones );
        ps.setInt( 2, viejoNumSalones );
        ps.executeUpdate();
        cerrar(ps);
    }
    public void insertarSalonesPorDefault() throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( insertaSalonesPorDefault );
        ps.executeUpdate();
        cerrar(ps);
    }
    
    public int obtieneSalones() throws SQLException, Exception {
        int salones = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( obtieneSalones );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            salones = rs.getInt( "salonesDisponibles" );
        }
        cerrar( ps );
        cerrar( rs );
        return salones;
    }
    
    public String obtenerTipo(String nombreCurso)throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result=null;
         ps = conexion.prepareStatement( tipoCurso );
        ps.setString(1, nombreCurso);
        System.out.println(ps);
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getString("tipo");
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }
    
    public ArrayList<String> busquedaDeCursos2() throws SQLException, Exception {
        ResultSet rs;
        ArrayList<String> resultado = new ArrayList<String>();
	PreparedStatement ps = null;
        ps = conexion.prepareStatement( busquedaCursos );
        rs = ps.executeQuery();
        while ( rs.next() ) {
            resultado.add( rs.getString( "nombre_CU" ) );
        }
        cerrar( ps );
        cerrar( rs );
        return resultado;
    }
    
    public String[][] busquedaDeClases( String nombre ) throws SQLException, Exception {
        ResultSet rs;
        int registros = 0;
        
	PreparedStatement ps = null;
        ps = conexion.prepareStatement( contadorRegistrosClases );
        ps.setString(1, nombre );
        rs = ps.executeQuery();

        while ( rs.next() ) {
            registros = rs.getInt( "COUNT(*)" );
        }
        cerrar( ps );
        cerrar( rs );
        
        if( registros == 0  ) {
           // throw new Exception("No hay clases");
        }
        //Sacar registros
        int contadorCursos = 0;
        String[][] informacionCursos = new String[ registros ][ 4 ];
        ps = null;
        ps = conexion.prepareStatement( busquedaClasesEspecifica );
        ps.setString( 1, nombre );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            informacionCursos[ contadorCursos ][ 0 ] = rs.getString( "dia" );
            informacionCursos[ contadorCursos ][ 1 ] = rs.getString( "salon" );
            informacionCursos[ contadorCursos ][ 2 ] = rs.getString( "horaInicio" );
            informacionCursos[ contadorCursos ][ 3 ] = rs.getString( "horaFin" );
            contadorCursos++;
        }
        cerrar( ps );
        cerrar( rs );
        return informacionCursos;
    }
 
    public String busquedaId( String dia, int salon, String horaInicio, String horaFin ) throws SQLException {
        System.out.println(dia);
        System.out.println(salon);
        System.out.println(horaInicio);
        System.out.println(horaFin);
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( busquedaId );
        ps.setString( 1, dia );
        ps.setInt( 2, salon );
        ps.setString( 3, horaInicio );
        ps.setString( 4, horaFin );
         System.out.println(ps);
        rs = ps.executeQuery();
       
        String id = null;
        
        while ( rs.next() ) {
            id = rs.getString( "id_Clase" );
        }
        return id;
    }
    
    public boolean verificarAlumnosDeCurso( String nombre ) throws SQLException {
        boolean verificacion = false;
        int paso = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( obtenerAlumnosEnCurso );
        ps.setString( 1, nombre );
        System.out.println(ps);
        rs = ps.executeQuery();
        
        while( rs.next() ) {
            paso = rs.getInt( "matricula" );
        }
        if( paso != 0 ) {
            verificacion = true;
        }
        return verificacion;
    } 
    
    public ArrayList<Curso> obtenerCursosEsporadicos() throws SQLException {
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( obtenerCursosEsporadicos );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            Curso curso = new Curso();
            curso.setNombreCurso( rs.getString( nombreCurso ) );
            cursos.add( curso );
        }
        return cursos;
    }
}
