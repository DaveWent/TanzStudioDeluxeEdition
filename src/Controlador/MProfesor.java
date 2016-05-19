/**
 * MantenimientoProfesor.java
 * Version 1
 * 30/11/2015
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import Modelo.Danzas;
import Modelo.Profesor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Dave Went
 */
public class MProfesor extends ConexionBD{
    
    CProfesor controlProfesor;
    CDanza controlDanza = new CDanza();
    CCurso controlCurso = new CCurso();
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefonoCasa;
    private String telefonoCel;
    private String correo;
    private String facebook;
    ArrayList<String> danzas;
    ArrayList<String> cursos;
    private static final String insertar = "INSERT INTO conoce"
        + "( id_Profesor, id_Danza) VALUES(?,?)";
    private static final String impartido = "SELECT * FROM impartido "
            + "where id_Profesor = ?"; 
    private static final String danzasDeProfesor = "SELECT profesor.id_Profesor, "
        + "P.danzaConocida FROM profesor INNER JOIN ( (SELECT conoce.id_Profesor, "
        + "conoce.Id_Danza, danzas.danzaConocida FROM danzas INNER JOIN "
        + "conoce ON (danzas.id_Danza = conoce.id_Danza) ) as P ) "
        + "ON P.id_Profesor = ?";
    private static final String idDanzasProfesor = "SELECT id_Danza FROM conoce "
        + "WHERE id_Profesor = ?";
    private static final String cursosDeProfesor = "SELECT nombre_CU FROM impartido "
            + "WHERE id_Profesor=?";
    PreparedStatement ps = null;
    ResultSet rs;

    public MProfesor( String nombre, String apellidoPaterno, 
            String apellidoMaterno, String telefonoCasa, String telefonoCel, 
            String correo, String facebook, ArrayList<String> danzas ) {
        this.nombre = nombre.toUpperCase();
        this.apellidoPaterno = apellidoPaterno.toUpperCase();
        this.apellidoMaterno = apellidoMaterno.toUpperCase();
        this.telefonoCasa = telefonoCasa;
        this.telefonoCel =telefonoCel;
        this.correo = correo.toUpperCase();
        this.facebook = facebook.toUpperCase();
        this.danzas = danzas;
    }
    
    public MProfesor( String nombre, String apellidoPaterno, 
            String apellidoMaterno, String telefonoCasa, String telefonoCel, 
            String correo, String facebook, ArrayList<String> danzas, 
            ArrayList<String> cursos ) {
        this.nombre = nombre.toUpperCase();
        this.apellidoPaterno = apellidoPaterno.toUpperCase();
        this.apellidoMaterno = apellidoMaterno.toUpperCase();
        this.telefonoCasa = telefonoCasa;
        this.telefonoCel =telefonoCel;
        this.correo = correo.toUpperCase();
        this.facebook = facebook.toUpperCase();
        this.danzas = danzas;
        this.cursos = cursos;
    }
    
    public MProfesor() {
        
    }
    
    public void altaInformacionProfesor( ) throws Exception {
        int contador = 0;
        ArrayList<Danzas> listaDanzas = new ArrayList<>();
        String[] danzasRecibidas = new String[5];
        Profesor profesor = new Profesor( nombre, apellidoPaterno, apellidoMaterno,
            telefonoCasa, telefonoCel, correo, facebook);
        controlProfesor = new CProfesor();
        int idProfesor = controlProfesor.alta( profesor );
        
        for( String danza : danzas ) {
            if( !danza.equals( "" ) ) {
                danzasRecibidas[ contador ] = danza.toUpperCase();
                contador++;
            }
        }
        
        if( contador > 0 ) {
            for ( int x = 0 ; x < contador ; x++ ) {
                Danzas danza = new Danzas( danzasRecibidas[ x ] );
                listaDanzas.add( danza );
            }
            CDanza control = new CDanza();
            int[] vectorId = control.alta( listaDanzas );
        
            for( int x = 0 ; x < vectorId.length ; x++ ) {
                ps = conexion.prepareStatement( insertar );
                ps.setInt( 1, idProfesor );
                ps.setInt( 2, vectorId[ x ] );
                ps.executeUpdate();
            }
            cerrar(ps);
        }
    }
    
    public String[][] consulta( String proveniente, String dato ) throws Exception{
        int contador = 0;
        controlProfesor = new CProfesor();
        ArrayList<Profesor> resultado = null;
        if( proveniente.equalsIgnoreCase( "consulta" ) ) {
            resultado = controlProfesor.Consultas();
        }
        
        if( proveniente.equalsIgnoreCase( "matricula" ) ) {
            resultado = controlProfesor.busquedaCoincidenciaMatricula( dato );
        }
        
        if( proveniente.equalsIgnoreCase( "nombre" ) ) {
            resultado = controlProfesor.busquedaCoincidenciaNombre( dato );
        }
        
        String[][] profesores = new String[ resultado.size() ][7];
        
        for( Profesor profesor: resultado ) {
            profesores[ contador ][ 0 ] = Integer.toString( profesor.getIdProfesor() );
            profesores[ contador ][ 1 ] = profesor.getNombre();
            profesores[ contador ][ 2 ] = profesor.getApellidoPaterno();
            profesores[ contador ][ 3 ] = profesor.getApellidoMaterno();
            ArrayList<String> cursos = controlProfesor.obtenerCursosDeProfesor( 
                    profesor.getIdProfesor() );
            
            if( cursos.size() != 0) {
                profesores[ contador ][ 4 ] = "<html>";
                String cursosDeProfesor = "";
                for( String curso : cursos ) {
                    cursosDeProfesor = cursosDeProfesor + curso + "<br>";
                }
                profesores[ contador ][ 4 ] = profesores[ contador ][ 4 ] +
                        cursosDeProfesor + "</html>";
            }
            
            if( profesor.getTelefonoCasa() == null ) {
                profesores[ contador ][ 5 ] = " ";
            } else {
                profesores[ contador ][ 5 ] = profesor.getTelefonoCasa();
            }
            
            if( profesor.getTelefonoCel() == null ) {
                profesores[ contador ][ 6 ] = " ";
            } else {
                profesores[ contador ][ 6 ] = profesor.getTelefonoCel() ;
            }
            contador++;
        }
        return profesores;
    }
    
    public void bajaProfesor( String nombre, String apellidoPaterno, 
            String apellidoMaterno ) throws SQLException, Exception {
        ArrayList<String> danzasConocidas = new ArrayList<String>();
        controlProfesor = new CProfesor();
        int idProfesor = controlProfesor.busqueda(nombre, apellidoPaterno, 
                apellidoMaterno);
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( idDanzasProfesor );
        ps.setInt(1, idProfesor);
        rs = ps.executeQuery();
        while (rs.next()) {
            danzasConocidas.add( Integer.toString( rs.getInt( "id_Danza" ) ) );
        }
        cerrar(ps);
        controlProfesor.baja(idProfesor);
        for( String danzas : danzasConocidas ) {
            controlDanza.baja(Integer.parseInt(danzas));
        }
    }
    
    public String[] busquedaEspecificaDeDanzas( String nombre, String apellidoPaterno, 
            String apellidoMaterno ) throws SQLException, Exception {
        int contadorDanzas = 0;
        controlProfesor = new CProfesor();
        ArrayList<String> danzasConocidas = new ArrayList<String>();
        int idProfesor = controlProfesor.busqueda(nombre, apellidoPaterno, 
                apellidoMaterno);
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( idDanzasProfesor );
        ps.setInt(1, idProfesor);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            danzasConocidas.add( Integer.toString( rs.getInt( "id_Danza" ) ) );
            contadorDanzas++;
        }
        cerrar(ps);
        contadorDanzas = 0;
        String[] danzasConocidas2 = new String[danzasConocidas.size()];
        for( String danza : danzasConocidas ) {
            danzasConocidas2[contadorDanzas] = controlDanza.busqueda(danza);
            contadorDanzas++;
        }
        
        cerrar(ps);
        return danzasConocidas2;
    }
    
    public String[] busquedaEspecificaDeCursos( String nombre, String apellidoPaterno, 
        String apellidoMaterno ) throws SQLException, Exception {
        controlProfesor = new CProfesor();
        int idProfesor = controlProfesor.busqueda(nombre, apellidoPaterno, apellidoMaterno);
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( cursosDeProfesor );
        ps.setInt(1, idProfesor );
        rs = ps.executeQuery();
        ArrayList<String> cursos = new ArrayList<String>();
        while (rs.next()) {
            cursos.add( rs.getString( "nombre_CU" )  );
        }
        cerrar(ps);
        
        int contadorCursos = 0;
        String[] listadoCursos = new String[cursos.size()];
        for( String curso : cursos ) {
            listadoCursos[contadorCursos] =curso;
             contadorCursos++;
        }
        return listadoCursos;
    }
    
    public String[] obtenerCursos() throws Exception {
        ArrayList<String> resultado = controlCurso.busquedaDeCursos();
        String[] cursos = new String[resultado.size()];
        int contadorCursos = 0;
        
        for( String curso : resultado ) {
            cursos[contadorCursos] = curso;
            contadorCursos++;
        }
        
        return cursos;
    }
    
    public void modificacion( String[] nombreCompleto, String nombreProfesor,
            String apellidoPaternoProfesor, String apellidoMaternoProfesor,
            String telefonoCasa,String telefonoCel, String correo, String facebook,
            String[] cursos, ArrayList<String> danzas ) throws Exception {
        
        CProfesor controlProfesor = new CProfesor();
        CDanza controlDanza = new CDanza();
        int idProfesor = controlProfesor.busqueda( nombreCompleto[ 0 ], 
                nombreCompleto[ 1 ], nombreCompleto[ 2 ] );
        Profesor profesor = new Profesor( nombreProfesor, apellidoPaternoProfesor, 
                apellidoMaternoProfesor, telefonoCasa, telefonoCel, correo, facebook );
        profesor.setIdProfesor( idProfesor );
        controlProfesor.modificacion( profesor );
        int contadorDanzas = danzas.size();
        if( contadorDanzas !=0 ) {
            ArrayList<Integer> ids = controlDanza.busquedaDanzasPorIdProfesor( idProfesor );
            int contadorId = ids.size();
            
            if( contadorId != 0  ) {
                for( Integer idDanza : ids ) {
                    controlDanza.baja( idDanza );
                }
            }
            
            ArrayList<Danzas> listaDanzas = new ArrayList<>(); 
            for ( int x = 0 ; x < danzas.size() ; x++ ) {
                Danzas danza = new Danzas( danzas.get( x ) );
                listaDanzas.add(danza);
            }

            CDanza control = new CDanza();
            int[] vectorId = control.alta( listaDanzas );

            for( int x = 0 ; x < vectorId.length ; x++ ) {
                ps = conexion.prepareStatement( insertar );
                ps.setInt(1, idProfesor);
                ps.setInt(2, vectorId[x]);
                ps.executeUpdate();
            }
            cerrar(ps);
        } else {
            ArrayList<Integer> ids = controlDanza.busquedaDanzasPorIdProfesor( idProfesor );
            int contadorId = ids.size();
            
            if( contadorId != 0  ) {
                for( Integer idDanza : ids ) {
                    controlDanza.baja( idDanza );
                }
            } 
        }
        
        if( cursos.length != 0 ) {
            controlProfesor.eliminarRelacion(idProfesor);
            for( int x = 0 ; x < cursos.length ; x++ ) {
                if( cursos[ x ] != null ) {
                    controlProfesor.agregarRelacion(idProfesor, cursos[x]);
                }
            }
        } else {
            controlProfesor.eliminarRelacion(idProfesor);
        }
        
    }
    
    public String[] busquedaEspecificaProfesor( int numProfesor ) throws SQLException {
        CProfesor controlProfesor = new CProfesor();
        ArrayList<String> profesores = controlProfesor.busquedaEspecificaProfesor
        ( numProfesor );
        String[] datos = new String[ 7 ];
        int contador = 0;
        for( String profesor : profesores ) {
            datos[ contador ] = profesor;
            contador++;
        }
        return datos;
    }
    
    public int obtenerNumero() throws SQLException, Exception {
        controlProfesor = new CProfesor();
        int numero = controlProfesor.obtenerNumeroProfesor();
        return numero;
    }
    
    public String[][] busquedaCoincidenciaMatricula( String id ) throws Exception{
        int contador = 0;
        controlProfesor = new CProfesor();
        ArrayList<Profesor> resultado = controlProfesor.busquedaCoincidenciaMatricula( id );
        ArrayList<Danzas> danzas = controlDanza.consulta();
        String[][] profesores = new String[resultado.size()][7];
        
        for( Profesor profesor: resultado ) {
            profesores[ contador ][ 0 ] = Integer.toString( profesor.getIdProfesor() );
            profesores[ contador ][ 1 ] = profesor.getNombre();
            profesores[ contador ][ 2 ] = profesor.getApellidoPaterno();
            profesores[ contador ][ 3 ] = profesor.getApellidoMaterno();
            ArrayList<String> cursos = controlProfesor.obtenerCursosDeProfesor( 
                    profesor.getIdProfesor() );
            
            if( cursos.size() != 0) {
                profesores[ contador ][ 4 ] = "<html>";
                String cursosDeProfesor = "";
                for( String curso : cursos ) {
                    cursosDeProfesor = cursosDeProfesor + curso + "<br>";
                }
                profesores[ contador ][ 4 ] = profesores[ contador ][ 4 ] +
                        cursosDeProfesor + "</html>";
            }
            
            if( profesor.getTelefonoCasa() == null ) {
                profesores[ contador ][ 5 ] = " ";
            } else {
                profesores[ contador ][ 5 ] = profesor.getTelefonoCasa();
            }
            
            if( profesor.getTelefonoCel() == null ) {
                profesores[ contador ][ 6 ] = " ";
            } else {
                profesores[ contador ][ 6 ] = profesor.getTelefonoCel() ;
            }
            contador++;
        }
        return profesores;
    }
    
}