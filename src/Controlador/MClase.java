/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clase;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dave Went
 */
public class MClase {
    private CClase controlClase;
    
    public String[][] obtenerClasesDeUnCurso( String nombreCurso ) throws SQLException, Exception {
        controlClase = new CClase();
        ArrayList<Clase> clases = controlClase.obtenerClasesDeUnCurso( nombreCurso );
        int cantidadClases = clases.size();
        String[][] clasesArray = new String[ cantidadClases ][ 5 ];
        int contador = 0;
        for( Clase clase : clases ) {
            clasesArray[ contador ][ 0 ] = Integer.toString( clase.getIdClase() );
            clasesArray[ contador ][ 1 ] = clase.getDia();
            clasesArray[ contador ][ 2 ] = clase.getSalon();
            clasesArray[ contador ][ 3 ] = clase.getHoraInicio();
            clasesArray[ contador ][ 4 ] = clase.getHoraFin();
            contador++;
        }
        return clasesArray;
    }
    
    public String[][] obtenerClasesPorDia( String nombreCurso, String diaDeLaClase ) throws Exception {
        controlClase = new CClase();
        String[] clases[] = controlClase.obtenerClasesPorDia( nombreCurso, diaDeLaClase );
        return clases;
    }
    
    public String[][] consultaGeneral() throws SQLException {
        controlClase = new CClase();
        ArrayList<Clase> clases = controlClase.consultaClases();
        int cantidadClases = clases.size();
        String[][] clasesArray = new String[ cantidadClases ][ 5 ];
        int contador = 0;
        
        for( Clase clase : clases ) {
            clasesArray[ contador ][ 0 ] = Integer.toString( clase.getIdClase() );
            clasesArray[ contador ][ 1 ] = clase.getDia();
            clasesArray[ contador ][ 2 ] = clase.getSalon();
            clasesArray[ contador ][ 3 ] = clase.getHoraInicio();
            clasesArray[ contador ][ 4 ] = clase.getHoraFin();
            contador++;
        }
        
        return clasesArray;
    }
    
    public String[][] consultaGeneralEspecifica( String dia ) throws SQLException {
        controlClase = new CClase();
        ArrayList<Clase> clases = controlClase.consultaClasesEspecifica( dia );
        int cantidadClases = clases.size();
        String[][] clasesArray = new String[ cantidadClases ][ 5 ];
        int contador = 0;
        
        for( Clase clase : clases ) {
            clasesArray[ contador ][ 0 ] = Integer.toString( clase.getIdClase() );
            clasesArray[ contador ][ 1 ] = clase.getDia();
            clasesArray[ contador ][ 2 ] = clase.getSalon();
            clasesArray[ contador ][ 3 ] = clase.getHoraInicio();
            clasesArray[ contador ][ 4 ] = clase.getHoraFin();
            contador++;
        }
        
        return clasesArray;
    }
    
    public String[][] obtenerClasesConRequisitoDeSilencio( String dia ) throws SQLException {
        ArrayList<Clase> clases = controlClase.consultaClasesEspecifica( dia );
        int cantidadClases = clases.size();
        String[][] clasesArray = new String[ cantidadClases ][ 4 ];
        int contador = 0;
        
        for( Clase clase : clases ) {
            clasesArray[ contador ][ 0 ] = Integer.toString( clase.getIdClase() );
            clasesArray[ contador ][ 1 ] = clase.getSalon();
            clasesArray[ contador ][ 2 ] = clase.getHoraInicio();
            clasesArray[ contador ][ 3 ] = clase.getHoraFin();
            contador++;
        }
        
        return clasesArray;
    }
    
    public void eliminarClase( String nombreDelCurso, int idClase ) throws Exception {
        controlClase = new CClase();
        controlClase.eliminarRelacionCurso( nombreDelCurso, idClase );
        controlClase.eliminarClase(idClase);
        
    }
    
    public void agregarClase( String nombreDelCurso, String dia, int salonRecibido, 
            String horaInicio, String horaFin ) throws Exception {
        controlClase = new CClase();
        Clase clase = new Clase();
        clase.setDia(dia);
        String salon = Integer.toString( salonRecibido ); 
        clase.setSalon(salon);
        clase.setHoraInicio(horaInicio);
        clase.setHoraFin(horaFin);
        controlClase.agregarClase(clase);
        int idClase = controlClase.obtenerIdClase();
        controlClase.agregarRelacionClaseYCurso( nombreDelCurso, idClase );
    }
}
