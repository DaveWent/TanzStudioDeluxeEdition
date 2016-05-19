/**
 * MantenimientoEstadisticas.java
 * Version 1
 * 18/04/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Curso;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que contiene los metodos necesarios para realizar las solicitudes
 * de información estadistica y enviarla al controlador de la vista
 * @author David Omar Cabrera Bernal
 */
public class MantenimientoEstadisticas {
    //Variable que realiza llamadas a los metodos del controlEsadisticas
    CEstadisticas control;    
    
    /**
     * Metodo que obtiene una matriz con la información de los alumnos 
     * isncritos en determinados cursos
     * @return
     * @throws SQLException 
     */
    public String[][] obtenerInformacionCursos() throws SQLException {
        control = new CEstadisticas();
        ArrayList<Curso> cursos = control.consultaCursos();
        String[][] cursosConAlumnos = control.obtenerInformacionCursos( cursos );
        return cursosConAlumnos;
    }
    
    public String[][] ObtenerInformacionEdades() throws SQLException {
        control = new CEstadisticas();
        ArrayList<Alumno> alumnos = control.existenciaAlumnos();
        String[][] alumnosPorEdad = control.alumnosPorEdad(alumnos);
        for( int x = 0 ; x < alumnosPorEdad.length; x++ ) {
            alumnosPorEdad[ x ][ 0 ] = alumnosPorEdad[ x ][ 0 ] + " años";
        }
        return alumnosPorEdad;
    }
    
    public String[][] obtenerInformacionMedios() throws SQLException {
        control = new CEstadisticas();
        ArrayList<Alumno> alumnos = control.existenciaAlumnos();
        String[][] estadisticasMedios = control.obtenerInformacionMediosYDanzas( alumnos, "medios" );
        return estadisticasMedios;
    }
    
    public String[][] obtenerInformacionDanzas() throws SQLException {
        control = new CEstadisticas();
        ArrayList<Alumno> alumnos = control.existenciaAlumnos();
        String[][] estadisticasMedios = control.obtenerInformacionMediosYDanzas( alumnos, "danzas" );
        return estadisticasMedios;
    }
    
    public String[][] ObtenerInformacionRangoEdad( int edadInicio, int edadFin ) throws SQLException, Exception {
        control = new CEstadisticas();
        ArrayList<Alumno> alumnos = control.existenciaAlumnos();
        if( alumnos.size() == 0 ) {
            throw new Exception("No hay alumnos registrados");
        }
        String[][] alumnosPorEdad = control.alumnosPorRangoEspecifico(edadInicio, edadFin);
        if( alumnosPorEdad.length == 0 ) {
            throw new Exception("No hay alumnos con esa edad");
        }
        for( int x = 0 ; x < alumnosPorEdad.length; x++ ) {
            alumnosPorEdad[ x ][ 0 ] = alumnosPorEdad[ x ][ 0 ] + " años";
        }
        return alumnosPorEdad;
    }
    
    public String[] obtenerCiclos() throws SQLException {
        control = new CEstadisticas();
        String[] ciclos = control.ciclosEscolares();
        return ciclos;
    }
    
    public String[][] obtenerInformacionCiclo( String nombreCiclo, int opcion ) throws SQLException {
        String[][] estadisticas = null;
        control = new CEstadisticas();
        String[] ciclos = control.ciclosEscolares();
        int posicion = ciclos.length-1;
        if( ciclos[ posicion ].equalsIgnoreCase( nombreCiclo ) ) {
            if( opcion == 1 ) {
                estadisticas = obtenerInformacionCursos();
            } else {
                estadisticas = ObtenerInformacionEdades();
            }
            return estadisticas;
        } else {
            estadisticas = control.obtenerCicloEspecifico( nombreCiclo,opcion );
            return estadisticas;
        }
    }
}
