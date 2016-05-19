/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.swing.JTable;

/**
 *
 * @author Dave Went
 */
public class CValidacionClase {
    
    public void validarHorarioClases( String horaInicio, String horaFin ) throws Exception {
        if( horaInicio.equalsIgnoreCase( horaFin ) ) {
            throw new Exception("El inicio y fin de clase, no puede ser la misma hora");
        }
        String periodoDeInicio = obtenerPeriodo( horaInicio ) ;
        int horaDeInicio = obtenerHora( horaInicio, periodoDeInicio );
        String periodoDeFin = obtenerPeriodo( horaFin );
        int horaDeFin = obtenerHora( horaFin, periodoDeFin );
        if( ( periodoDeInicio.equalsIgnoreCase( "PM" ) 
                && periodoDeFin.equalsIgnoreCase( "AM" ) ) 
                && ( horaDeInicio >= 12 && horaDeFin >= 0 ) ) {
                throw new Exception("No puede terminar al dia siguiente la clase");
        } else {
            if( horaDeFin < horaDeInicio ) {
                throw new Exception("No puede iniciar la clase antes de comenzar");
            }
            
            if( horaDeFin == horaDeInicio ) {
                throw new Exception("No puede iniciar la clase antes de comenzar");
            }
        }
        
    }
    
    private int obtenerHora( String hora, String periodo ) {
        String[] horas = hora.split(" ");
        String[] horasYMinutos = horas[ 0 ].split( ":" );
        String soloHora = horasYMinutos[ 0 ];
        int horaFinal = Integer.parseInt( soloHora );
        if( periodo.equalsIgnoreCase( "PM" ) ) {
            switch( horaFinal ) {
                case 1:
                    horaFinal = 13;
                break;
                case 2:
                    horaFinal = 14;
                break;
                case 3:
                    horaFinal = 15;
                break;
                case 4:
                    horaFinal = 16;
                break;
                case 5:
                    horaFinal = 17;
                break;
                case 6:
                    horaFinal = 18;
                break;
                case 7:
                    horaFinal = 19;
                break;
                case 8:
                    horaFinal = 20;
                break;
                case 9:
                    horaFinal = 21;
                break;
                case 10:
                    horaFinal = 22;
                break;
                case 11:
                    horaFinal = 23;
                break;
            }
        }
        if( periodo.equalsIgnoreCase( "AM" ) ) {
            if( horaFinal == 12 ) {
                horaFinal = 0;
            }
        }
        return horaFinal;
    }
    
    private String obtenerPeriodo( String hora ) {
        String[] horas = hora.split(" ");
        String periodo = horas[ 1 ];
        return periodo;
    }
    
    public void validarDisponibilidadClases( String horaInicio, String horaFin, 
            int salon, String[][] clases, int numSalones, String[][] clasesConSilencio ) throws Exception {
        String periodoDeInicio = obtenerPeriodo( horaInicio ) ;
        int horaDeInicio = obtenerHora( horaInicio, periodoDeInicio );
        String periodoDeFin = obtenerPeriodo( horaFin );
        int horaDeFin = obtenerHora( horaFin, periodoDeFin );
        int longitudClases = clases.length;
        int longitudClasesSilencio = clasesConSilencio.length;
        
        String periodoDeInicioExistente;
        int horaDeInicioExistente;
        String periodoDeFinExistente;
        int horaDeFinExistente;
        String horaInicioExistente;
        String horaFinExistente;
        String salonAuxiliar;
        
        if( longitudClases != 0 ) {
            
            for( int x = 0 ; x < longitudClases ; x++ ) {
                salonAuxiliar = clases[ x ][ 2 ];
                int salonExistente = Integer.parseInt( salonAuxiliar );
                horaInicioExistente = clases[ x ][ 3 ];
                horaFinExistente = clases[ x ][ 4 ];
                periodoDeInicioExistente = obtenerPeriodo( horaInicioExistente );
                horaDeInicioExistente = obtenerHora( horaInicioExistente, periodoDeInicioExistente );
                periodoDeFinExistente = obtenerPeriodo( horaFinExistente );
                horaDeFinExistente = obtenerHora( horaFinExistente, periodoDeFinExistente );
                if( salon == salonExistente ) {
                    if( horaDeInicio == horaDeInicioExistente  ) {
                        throw new Exception( "Ya hay una clase a esa hora" );
                    }
                    if( horaDeInicio > horaDeInicioExistente && horaDeInicio < horaDeFinExistente ) {
                        throw new Exception( "Ya hay una clase a esa hora" );
                    }
                    if( ( horaDeInicio < horaDeInicioExistente ) && ( 
                            horaDeFin > horaDeInicioExistente && 
                            horaDeFin <= horaDeFinExistente ) ) {
                        throw new Exception( "Ya hay una clase a esa hora" );
                    }
                }
            }
        } 
        
        if( longitudClasesSilencio != 0 ) {
            
            for( int x = 0 ; x < longitudClasesSilencio ; x++ ) { 
                horaInicioExistente = clasesConSilencio[ x ][ 2 ];
                horaFinExistente = clasesConSilencio[ x ][ 3 ];
                periodoDeInicioExistente = obtenerPeriodo( horaInicioExistente );
                horaDeInicioExistente = obtenerHora( horaInicioExistente, periodoDeInicioExistente );
                periodoDeFinExistente = obtenerPeriodo( horaFinExistente );
                horaDeFinExistente = obtenerHora( horaFinExistente, periodoDeFinExistente );
                
                if( horaDeInicio == horaDeInicioExistente  ) {
                    throw new Exception( "Ya hay una clase que requiere silencio a esa ahora" );
                }
                if( horaDeInicio > horaDeInicioExistente && horaDeInicio < horaDeFinExistente ) {
                    throw new Exception( "Ya hay una clase que requiere silencio a esa ahora" );
                }
                if( ( horaDeInicio < horaDeInicioExistente ) && ( 
                        horaDeFin > horaDeInicioExistente && 
                        horaDeFin <= horaDeFinExistente ) ) {
                    throw new Exception( "Ya hay una clase que requiere silencio a esa ahora" );
                }
            }
        }
    }
    
    public void valdarClasesDeTabla( String horaInicio, String horaFin, int salon, 
            JTable tbClases ) throws Exception {
        int longitudClases = tbClases.getRowCount();
        
        if( longitudClases != 0 ) {
            
            String periodoDeInicio = obtenerPeriodo( horaInicio ) ;
            int horaDeInicio = obtenerHora( horaInicio, periodoDeInicio );
            String periodoDeFin = obtenerPeriodo( horaFin );
            int horaDeFin = obtenerHora( horaFin, periodoDeFin );
            
            String[][] clases = new String[ longitudClases ][ 2 ];
            for( int x = 0 ; x <longitudClases ; x++ ) {
                clases[ x ][ 0 ] = (String) tbClases.getValueAt( x, 0 );
                clases[ x ][ 1 ] = (String) tbClases.getValueAt( x, 1 );
            }
            
            int salonExistente;
            String horaTabla;
            String[] horas;
            String periodo;
            int horaDeInicioExistente;
            int horaDeFinExistente;
            for( int y = 0 ; y < longitudClases; y++ ) {
                salonExistente = Integer.parseInt( "" + clases[ y ][ 1 ].charAt( 6 ) );
                horaTabla = clases[ y ][ 0 ];
                horas = horaTabla.split( " " );
                periodo = horas[ 1 ];
                horaDeInicioExistente = obtenerHora( horas[ 0 ], periodo );
                periodo = horas[ 4 ];
                horaDeFinExistente = obtenerHora( horas[ 3 ], periodo );
                if( salon == salonExistente) {
                    if( horaDeInicio == horaDeInicioExistente  ) {
                        throw new Exception( "Ya hay una clase a esa ahora" );
                    }
                    if( horaDeInicio > horaDeInicioExistente && horaDeInicio < horaDeFinExistente ) {
                        throw new Exception( "Ya hay una clase a esa ahora" );
                    }
                    if( ( horaDeInicio < horaDeInicioExistente ) && 
                            ( horaDeFin > horaDeInicioExistente && horaDeFin <= horaDeFinExistente ) ) {
                        throw new Exception( "Ya hay una clase a esa ahora" );
                    }
                }
            }
            
        }
        
    }
}
