package Controlador;

import Modelo.CicloEscolar;
import Modelo.Clase;
import Modelo.Curso;
import Modelo.Profesor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dave Went
 */
public class MCurso extends ConexionBD{
    
    CProfesor controlProfesor;
    CCurso controlCurso;
    CClase controlClase;
//    ControlCicloEscolar controlCicloEscolar;
    
    public String[][] obtenerProfesores( ) throws SQLException, Exception {
        controlProfesor = new CProfesor();
        ArrayList<Profesor> profesores;
        profesores = controlProfesor.obtenerProfesores();
        
        int cantidadProfesores = profesores.size();
        String[][] profesoresArray = new String[ cantidadProfesores ][ 4 ];
        int contador = 0;
        for( Profesor profesor : profesores ) {
            profesoresArray[ contador ][ 0 ] = Integer.toString( profesor.getIdProfesor() );
            profesoresArray[ contador ][ 1 ] = profesor.getNombre();
            profesoresArray[ contador ][ 2 ] = profesor.getApellidoPaterno();
            profesoresArray[ contador ][ 3 ] = profesor.getApellidoMaterno();
            contador++;
        }
        return profesoresArray;
    }
    
    public String[] obtenerCursos() throws SQLException, Exception {
        controlCurso = new CCurso();
        ArrayList<Curso> cursos = controlCurso.consultaGeneral();
        int longitudCursoArray = cursos.size();
        String[] cursosArray = new String[ longitudCursoArray ];
        int contadorCursos = 0;
        
        for ( Curso curso : cursos ) {
            cursosArray[ contadorCursos ] = curso.getNombreCurso();
            contadorCursos++;
        }
        
        return cursosArray;
    }
    
    public void altaCurso( String nombreCurso, 
            String tipoCurso, String requiereSilencio, String fechaInicio, 
            String fechaFin ) throws Exception {
        controlCurso = new CCurso();
        controlProfesor = new CProfesor();
        String nombreDelCurso = "nombre_CU";
        String idProfesor = "id_Profesor";
        String insertarEnImpartido = "INSERT INTO impartido (" +nombreDelCurso+ 
                "," +idProfesor+ ") VALUES (?,?)";
        if( tipoCurso.equals("esporadico") ) {
            
            String[] fecha = fechaInicio.split( "/" );
            fechaInicio = fecha[ 2 ] + "-" + fecha[ 1 ] + "-" +fecha[ 0 ] ;
            fecha = fechaFin.split( "/" );
            fechaFin = fecha[ 2 ] + "-" + fecha[ 1 ] + "-" +fecha[ 0 ] ;
        }
        Curso curso = new Curso( nombreCurso, tipoCurso, requiereSilencio, 
                fechaInicio, fechaFin);
        controlCurso.altaCurso( curso, tipoCurso );
    }
    
//    public String[] obtenerFechaCicloEscolarActual() throws SQLException {
//        controlCicloEscolar = new ControlCicloEscolar();
//        CicloEscolar cicloEscolar = new CicloEscolar();
//        cicloEscolar = controlCicloEscolar.obtenerCicloEscolarActual();
//        String[] fechasCicloEscolar = new String[ 2 ];
//        String fechaInicio = cicloEscolar.getFechaInicio();
//        String fechaFin = cicloEscolar.getFechaFin();
//        String[] fecha = fechaInicio.split( "-" );
//        fechasCicloEscolar[ 0 ] = fecha[ 2 ] + "/" + fecha[ 1 ] + "/" + fecha[ 0 ];
//        fecha = fechaFin.split( "-" );
//        fechasCicloEscolar[ 1 ] = fecha[ 2 ] + "/" + fecha[ 1 ] + "/" + fecha[ 0 ];
//        return fechasCicloEscolar;
//    }
    
    public void bajaCurso ( String nombreCurso ) throws Exception {
        controlCurso = new CCurso();
        controlClase = new CClase();
        ArrayList<Clase> clases =controlClase.obtenerClasesDeUnCurso(nombreCurso);
        for( Clase clase : clases ) {
            controlClase.eliminarClase( clase.getIdClase() );
        }
        controlCurso.eliminar( nombreCurso );
    }
    
    public String[] busquedaCurso( String nombreDelCurso ) throws Exception {
        controlCurso = new CCurso();
        String[] cursoString = new String[ 5 ];
        Curso curso = controlCurso.busquedaCurso( nombreDelCurso );
        cursoString[ 0 ] = curso.getNombreCurso();
        cursoString[ 1 ] = curso.getTipo();
        cursoString[ 2 ] = curso.getRequiereSilencio();
        String fechaInicio = curso.getFechaInicio();
        String fechaFin = curso.getFechaFin();
        if( curso.getTipo().equalsIgnoreCase("esporadico") ) {
            String[] fechas = fechaInicio.split( "-" );
            cursoString[ 3 ] = DarFormatoFecha( fechas );
            fechas = fechaFin.split( "-" );
            cursoString[ 4 ] = DarFormatoFecha( fechas );
        }
        cursoString[ 3 ] = "";
        cursoString[ 4 ] = "";
        return cursoString;
    }
    
    private String DarFormatoFecha( String[] fechas ) {
        String dia = fechas[ 2 ];
        String mes = fechas[ 1 ];
        String año = fechas[ 0 ];
        String fechaConFormato = dia + "/" + mes + "/" + año;
        return fechaConFormato;
    }
    
    public String[][] obtenerProfesoresPorCurso( String nombreCurso ) throws SQLException {
        controlProfesor = new CProfesor();
        ArrayList<Profesor> profesores;
        profesores = controlProfesor.obtenerProfesoresPorCurso( nombreCurso );
        
        int cantidadProfesores = profesores.size();
        String[][] profesoresArray = new String[ cantidadProfesores ][ 4 ];
        int contador = 0;
        for( Profesor profesor : profesores ) {
            profesoresArray[ contador ][ 0 ] = Integer.toString( profesor.getIdProfesor() );
            profesoresArray[ contador ][ 1 ] = profesor.getNombre();
            profesoresArray[ contador ][ 2 ] = profesor.getApellidoPaterno();
            profesoresArray[ contador ][ 3 ] = profesor.getApellidoMaterno();
            contador++;
        }
        return profesoresArray;
    }
    
    public void modificacion( String nombreOriginal, String nombreCurso, 
            String tipoCurso, String requiereSilencio, String fechaInicio, 
            String fechaFin, ArrayList<String> profesores ) throws Exception {
        controlCurso = new CCurso();
        String fechaInicioFormateada = "";
        String fechaFinFormateada = "";
        if( tipoCurso.equalsIgnoreCase("esporadico") ) {
            String[] fecha = fechaInicio.split( "/" );
            String dia = fecha[ 0 ];
            String mes = fecha[ 1 ];
            String año = fecha[ 2 ];
            fechaInicioFormateada = año + "-" + mes + "-" + dia;
            fecha = fechaFin.split( "/" );
            dia = fecha[ 0 ];
            mes = fecha[ 1 ];
            año = fecha[ 2 ];
            fechaFinFormateada = año + "-" + mes + "-" + dia;
        }
        Curso curso = new Curso( nombreCurso, tipoCurso, requiereSilencio,
                fechaInicioFormateada, fechaFinFormateada );
        controlCurso.modificacion( curso, nombreOriginal );
        controlCurso.eliminarRelaciones( nombreCurso );
        int longitudProfesores = profesores.size();
        if( longitudProfesores != 0 ) {
            for( String profesor : profesores ) {
                String[] informacionProfesor = profesor.split( "/" );
                int idProfesor = Integer.parseInt( informacionProfesor[ 0 ] );
                controlCurso.agregarRelacionesDeCursosYProfesores( nombreCurso, 
                        idProfesor );
            }
        }
    }
    
    public ArrayList<String> obtenerClases( String nombreCurso ) {
        ArrayList<String> clasesArray = new ArrayList<String>();
        controlClase = new CClase();
        return clasesArray;
    }
    
    public void actualizaSalones( int nuevoNumSalones, int viejoNumSalones ) throws Exception {
        controlCurso = new CCurso();
        controlCurso.actualizaSalones( nuevoNumSalones, viejoNumSalones );
    }
    
    public int obtieneSalones() throws Exception {
        controlCurso = new CCurso();
        int numeroSalones = controlCurso.obtieneSalones();
        return numeroSalones;
    }
    
    public void agregaSalonesPorDefault() throws Exception {
        controlCurso = new CCurso();
        controlCurso.insertarSalonesPorDefault();
    }
    
    public void insertarEnHistorialCambios() {
        
    }
    
    public boolean verificarAlumnosDeCurso( String nombre ) throws SQLException {
        controlCurso = new CCurso();
        boolean paso = controlCurso.verificarAlumnosDeCurso( nombre );
        return paso;
    } 
    
    public void relacionarCursoYProfesor( int[] idProfesores, String nombreCurso ) 
            throws Exception {
        controlCurso = new CCurso();
        controlCurso.eliminarRelaciones( nombreCurso );
        int cantidadProfesores = idProfesores.length;
        for( int x = 0 ; x < cantidadProfesores ; x++ ) {
            controlCurso.agregarRelacionesDeCursosYProfesores( nombreCurso, idProfesores[ x ] );
        }
    }
    
    public void eliminarRelacionCurso( String nombreCurso ) throws Exception {
        controlCurso = new CCurso();
        controlCurso.eliminarRelaciones( nombreCurso );
    }
}
