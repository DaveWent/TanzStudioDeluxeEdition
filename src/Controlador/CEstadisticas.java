/**
 * ControlEstadisticas.java
 * Version 1
 * 18/04/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que contiene los metodos necesarios para extraer la informacion
 * estadistica
 * @author David Omar Cabrera Bernal
 */
public class CEstadisticas extends ConexionBD{
    
    //Variables que contienen los querys que extraen la informacion de la base
    // de datos
    private static final String nombreCurso = "nombre_CU";
    private static final String contados = "COUNT(*)";
    private static final String tipo = "tipo";
    private static final String requiereSilencio = "requiereSilencio";
    private static final String fechaInicio = "fechaInicio_CU";
    private static final String fechaFin = "fechaFin_CU";
    private static final String edad = "edad";
    private static final String matricula = "matricula";
    private static final String nombreAlumno = "nombre_A";
    private static final String apePatAlumno = "apellidoPaterno_A";
    private static final String apeMatAlumno = "apellidoMaterno_A";
    private static final String medio = "medioPublicidad";
    private static final String danza = "danzaSugerida";
    private static final String cicloEscolar = "nombre_CE";
    private static final String consultaCursos = "SELECT * FROM curso ";
    private static final String estadisticasCursos = "SELECT " + contados + " FROM "
            + "tanzstudiodb.inscrito2 WHERE " + nombreCurso + "=?";
    private static final String estadisticaAlumnos = "SELECT " + edad + ", " + 
            contados + " FROM alumno GROUP BY " + edad;
    private static final String consultaAlumnos = " SELECT * FROM alumno WHERE "
            + "estado='ALTA'";
    private static final String estadisticasMedios = "SELECT P." + medio + ", " 
            + contados + " FROM alumno INNER JOIN ( (SELECT realiza." + matricula 
            + ", encuesta." + medio + " FROM encuesta INNER JOIN realiza ON "
            + "encuesta.id_Encuesta = realiza.id_Encuesta) AS P) ON P." + matricula 
            + " = alumno." + matricula + " AND alumno.estado = 'ALTA' GROUP BY "
            + "P." + medio;
    private static final String estadisticasDanzas = "SELECT P." + danza + ", "
            + contados + " FROM alumno INNER JOIN ( (SELECT realiza." + matricula 
            + ", encuesta." + danza + " FROM encuesta INNER JOIN realiza ON "
            + "encuesta.id_Encuesta = realiza.id_Encuesta) AS P) ON P." + matricula +
            " = alumno." + matricula + " AND alumno.estado = 'ALTA' GROUP BY "
            + "P." + danza;
    private static final String estadisticaEdadEspecifica = " SELECT " + edad 
            + "," + contados + " FROM alumno WHERE estado='ALTA' AND " + edad 
            + ">= ? AND " + edad + " <=? GROUP BY " + edad;
    private static final String existenciaCiclos="SELECT nombre_CE FROM cicloescolar";
    private static final String busquedaCicloPorCurso = "SELECT " + nombreCurso + 
            ",totalAlumnos FROM tanzstudiodb.contenido2 WHERE nombre_CE=?";
    private static final String busquedadCicloPorEdad = "SELECT edad,totalAlumnos"
            + " FROM edades INNER JOIN ( ( SELECT idEdades FROM "
            + "tanzstudiodb.contenido1 WHERE " + cicloEscolar+ " =? ) as P) ON P.idEdades = "
            + "edades.idEdades";
    
    /**
     * Método que obtiene los cursos en los que estan inscritos los alumnos
     * y los retorna al mantenimiento cursos
     * @return
     * @throws SQLException 
     */
    public String[][] obtenerInformacionCursos( ArrayList<Curso> cursos ) throws SQLException {
        ResultSet rs;
        PreparedStatement ps = null;
        int cursosExistentes = cursos.size();
        int contadorCursos = 0;
        
        if( cursosExistentes > 1 ) {
            String[][] cursosConAlumnos = new String[ cursosExistentes ][ 2 ];
            for( Curso curso : cursos ) {
                ps = conexion.prepareStatement( estadisticasCursos );
                ps.setString( 1 , curso.getNombreCurso());
                rs = ps.executeQuery();
                
                while( rs.next() ) {
                    cursosConAlumnos[ contadorCursos ][ 0 ] = curso.getNombreCurso();
                    cursosConAlumnos[ contadorCursos ][ 1 ] = rs.getString( contados );
                    contadorCursos++;
                }
            }
            return cursosConAlumnos;
        } else {
            return null;
        }
    }
    
    /**
     * Método que busca en la base de datos si existen cursos registrados en 
     * la base de datos
     * @return
     * @throws SQLException 
     */
    public ArrayList<Curso> consultaCursos() throws SQLException {
        boolean existencia = false;
        //ArrayList que contendra los cursos existentes
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaCursos );
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
    
    public String[][] alumnosPorEdad( ArrayList<Alumno> alumnos ) throws SQLException {
        ResultSet rs;
        PreparedStatement ps = null;
        int alumnosExistentes = alumnos.size();
        int contadorEdades = 0;
        
        if( alumnosExistentes > 0 ) {
            ps = conexion.prepareStatement( estadisticaAlumnos );
            rs = ps.executeQuery();
            
            while( rs.next() ) {
                contadorEdades++;
            }
            String[][] estadisticasEdades = new String[ contadorEdades ][ 2 ];
            contadorEdades = 0;
            rs = ps.executeQuery();
            while( rs.next() ) {
                estadisticasEdades[ contadorEdades ][ 0 ] = rs.getString( edad );
                String numAlumnosPorEdad = Integer.toString( rs.getInt( contados ) );
                estadisticasEdades[ contadorEdades ][ 1 ] =  numAlumnosPorEdad;
                contadorEdades++;
            }
            
            return estadisticasEdades;
        } else {
            return null;
        }
    }
    
    public ArrayList<Alumno> existenciaAlumnos() throws SQLException {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaAlumnos );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            Alumno alumno = new Alumno();
            alumno.setMatricula( Integer.parseInt( rs.getString( matricula ) ) );
            alumno.setNombre( rs.getString( nombreAlumno ) );
            alumno.setApellidoPaterno( rs.getString( apePatAlumno ) );
            alumno.setApellidoMaterno( rs.getString( apeMatAlumno ) );
            alumnos.add( alumno );
        }
        return alumnos;
    }
    
    public String[][] obtenerInformacionMediosYDanzas( ArrayList<Alumno> alumnos, String tipo ) throws SQLException {
        ResultSet rs;
        PreparedStatement ps = null;
        int alumnosExistentes = alumnos.size();
        int contadorMedios = 0;
        
        if( alumnosExistentes > 0 ) {
            if( tipo.equalsIgnoreCase( "medios" ) ) {
                ps = conexion.prepareStatement( estadisticasMedios );
            } else {
                ps = conexion.prepareStatement( estadisticasDanzas );
            }
            
            rs = ps.executeQuery();
            
            while( rs.next() ) {
                contadorMedios++;
            }
            
            String[][] estadisticas = new String[ contadorMedios ][ 2 ];
            contadorMedios = 0;
            rs = ps.executeQuery();
            
            while( rs.next() ) {
                if( tipo.equalsIgnoreCase( "medios" ) ) {
                    estadisticas[ contadorMedios ][ 0 ] = rs.getString( medio );
                } else {
                    estadisticas[ contadorMedios ][ 0 ] = rs.getString( danza );
                }
                
                String numMedios = Integer.toString( rs.getInt( contados ) );
                estadisticas[ contadorMedios ][ 1 ] =  numMedios;
                contadorMedios++;
            }
            return estadisticas;
        } else {
            return null;
        }
    }
    
    public String[][] alumnosPorRangoEspecifico( int edadInicio, int edadFin ) throws SQLException {
        ResultSet rs;
        PreparedStatement ps = null;
        int contadorEdades = 0;
        
        ps = conexion.prepareStatement( estadisticaEdadEspecifica );
        ps.setInt( 1, edadInicio );
        ps.setInt( 2, edadFin );
        rs = ps.executeQuery();

        while( rs.next() ) {
            contadorEdades++;
        }
        
        String[][] estadisticasEdades = new String[ contadorEdades ][ 2 ];
        contadorEdades = 0;
        rs = ps.executeQuery();
        while( rs.next() ) {
            estadisticasEdades[ contadorEdades ][ 0 ] = rs.getString( edad );
            String numAlumnosPorEdad = Integer.toString( rs.getInt( contados ) );
            estadisticasEdades[ contadorEdades ][ 1 ] =  numAlumnosPorEdad;
            contadorEdades++;
        }

        return estadisticasEdades;
    }
    
    public String[] ciclosEscolares() throws SQLException {
        int contador = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( existenciaCiclos );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            contador++;
        }
        if( contador == 0 ) {
            return null;
        }
        String[] ciclos = new String[ contador ];
        ps = conexion.prepareStatement( existenciaCiclos );
        contador = 0;
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            ciclos[ contador ] = rs.getString( cicloEscolar );
            contador++;
        }
        
        return ciclos;
    }

    public String[][] obtenerCicloEspecifico( String cicloEscolar, int opcion ) throws SQLException {
        int contador = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        if( opcion == 1 ) {
            ps = conexion.prepareStatement( busquedaCicloPorCurso );
        } else {
            ps = conexion.prepareStatement( busquedadCicloPorEdad );
        }
        
        ps.setString( 1, cicloEscolar );
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            contador++;
        }
        
        String[][] ciclos = new String[ contador ][ 2 ];
        if( opcion == 1 ) {
            ps = conexion.prepareStatement( busquedaCicloPorCurso );
        } else {
            ps = conexion.prepareStatement( busquedadCicloPorEdad );
        }
        ps.setString( 1, cicloEscolar );
        contador = 0;
        rs = ps.executeQuery();
        
        while ( rs.next() ) {
            if( opcion == 1 ) {
                ciclos[ contador ][ 0 ] = rs.getString( nombreCurso );
            } else {
                ciclos[ contador ][ 0 ] = rs.getString( "edad" );
            }
            ciclos[ contador ][ 1 ] = Integer.toString( rs.getInt( "totalAlumnos" ) );
            contador++;
        }
        
        return ciclos;
    }
    
}
