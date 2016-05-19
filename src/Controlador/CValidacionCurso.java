package Controlador;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dave Went
 */
public class CValidacionCurso {
    
    MCurso mantenimiento;
    
    public void validarExistenciaCursos( String[] cursos ) throws Exception {
        int cantidadCursos = cursos.length;
        if( cantidadCursos == 0 ) {
            throw new Exception("No hay cursos");
        }
    }
    
    public void validarCamposVacios( String palabra ) throws Exception {
        if( palabra.trim().length() == 0 ) {
            throw new Exception("No deje espacios en blanco");
        }
        
        if( palabra.charAt(0) == 32 ) {
            throw new Exception("Quite los espacios al comienzo del nombre del curso");
        }
    }
    
    public void validarRepeticionCurso( String curso ) throws SQLException, Exception {
        mantenimiento = new MCurso();
        String[] cursos = mantenimiento.obtenerCursos();
        
        for ( int x = 0 ; x < cursos.length ; x++ ) {
            if( cursos[ x ].equalsIgnoreCase( curso ) ) {
                throw new Exception("Curso Repetido");
            }
        }
    }
    
    public void validarRepeticionCursoAModificar( String nombreCurso, 
            String nombreOriginal ) throws SQLException, Exception {
        mantenimiento = new MCurso();
        String[] cursos = mantenimiento.obtenerCursos();
        
        for ( int x = 0 ; x < cursos.length ; x++ ) {
            if( cursos[ x ].equalsIgnoreCase( nombreCurso ) ) {
                if( !nombreCurso.equalsIgnoreCase( nombreOriginal ) ) {
                    throw new Exception("Curso Repetido");
                }
            }
        }
    }
    
    public void validacionProfesor( int profesor ) throws Exception {
        if( profesor == 0 ) {
            throw new Exception("Seleccione un profesor");
        }
    }
    
    public String[] validarFechas( String formato, Date fechaInicio, Date fechaFin ) throws Exception {
        
        if ( fechaInicio == null  && fechaFin == null ) {
            throw new Exception("Seleccione una fecha de inicio y fin");
        }
        
        if ( fechaInicio == null ) {
            throw new Exception("Seleccione una fecha de inicio");
        }
        
        if ( fechaFin == null ) {
            throw new Exception("Seleccione una fecha de fin");
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fechaInicioString = String.valueOf( sdf.format( fechaInicio ) );
        String fechaFinString = String.valueOf( sdf.format( fechaFin ) );
        
        if ( fechaInicioString.equals( fechaFinString ) ) {
            throw new Exception("Ambas fechas no pueden ser iguales");
        }
        String[] fechasInicio = fechaInicioString.split( "/" );
        String[] fechasFin = fechaFinString.split( "/" );
        int diaInicio = Integer.parseInt( fechasInicio[ 0 ] );
        int mesInicio = Integer.parseInt( fechasInicio[ 1 ] );
        int añoInicio = Integer.parseInt(fechasInicio[ 2 ] );
        int diaFin = Integer.parseInt(fechasFin[ 0 ] );
        int mesFin = Integer.parseInt(fechasFin[ 1 ] );
        int añoFin = Integer.parseInt(fechasFin[ 2 ] );
        
        if( añoFin < añoInicio ) {
            throw new Exception("Un curso no puede finalizar antes de comenzar");
        }
        
        if( añoFin == añoInicio ) {
            if( mesFin < mesInicio ) {
                throw new Exception("Un curso no puede finalizar antes de comenzar");
            }
            if( mesFin == mesInicio ) {
                if( diaFin < diaInicio ) {
                    throw new Exception("Un curso no puede finalizar antes de comenzar");
                }
            }
        }
        
        String fechas[] = new String[ 2 ];
        fechas[ 0 ] = fechaInicioString;
        fechas[ 1 ] = fechaFinString;
        return fechas;
    }
    
    public void validarNombreCurso( String nombreCurso ) throws Exception {
        if( nombreCurso.equalsIgnoreCase( "Seleccione un curso" ) ) {
            throw new Exception( "Seleccione un curso" );
        }
    }
    
    public void validarProfesorRepetido( String nombreProfesor, ArrayList<String> profesores ) throws Exception {
        int cantidadProfesores = profesores.size();
        if( cantidadProfesores != 0 ) {
            for( String profesor : profesores ) {
                String[] profesorArray = profesor.split( "/" );
                String profesorString = profesorArray[ 1 ];
                profesorString = profesorString + " " +profesorArray[ 2 ];
                profesorString = profesorString + " " +profesorArray[ 3 ];
                if( profesorString.equals( nombreProfesor ) ) {
                    throw new Exception("Ya posee ese profesor");
                }
            }
        }
    }
    
    public void validarNumero( String cadena ) throws Exception {
        int longitudCadena = cadena.length();
        for( int x = 0 ; x < longitudCadena ; x++ ) {
            if( cadena.charAt( x ) < 48 || cadena.charAt( x ) > 57 ) {
                throw new Exception("Solo numeros en el numero de salones");
            }
        }
    }
    
    public void validarProfesorPorId( String[] numProfesores, String nombreProfesor ) 
            throws Exception {
        int cantidadProfesores = numProfesores.length;
        for( int x = 0 ; x < cantidadProfesores ; x++ ) {
            if( numProfesores[ x ].equalsIgnoreCase( nombreProfesor ) ) {
                throw new Exception("Ya pusiste ese profesor");
            }
        }
    }
}
