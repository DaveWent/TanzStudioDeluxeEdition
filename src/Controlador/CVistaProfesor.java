package Controlador;

import Interfaces.ICierraVentana;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Interfaces.IAltaProfesor;
import Interfaces.IConsultaProfesores;
import Interfaces.IModificacionProfesor;

public class CVistaProfesor {
    ICierraVentana interfazCerrar;
    IAltaProfesor iAltaProfesor;
    IConsultaProfesores interfaz;
    IModificacionProfesor interfaz2;
    MProfesor mantenimiento;
    JTable tbConsultas;
    int filasAgregadas = 0 ;
    int filasPorDeafult = 5 ;
    String nombreProfesor;
    String apellidoPaternoProfesor;
    String apellidoMaternoProfesor;
    ArrayList<String> cursos = new ArrayList<String>();
    
    public CVistaProfesor( ) {
        
    }
    
    public CVistaProfesor( IConsultaProfesores interfaz, JTable tbConsultas ) {
        this.interfaz = interfaz;
        this.tbConsultas = tbConsultas;
    }
    
    public CVistaProfesor( IModificacionProfesor interfaz ) {
        interfaz2 = interfaz;
    }
    
    public CVistaProfesor( IAltaProfesor iAltaProfesor ) {
        this.iAltaProfesor = iAltaProfesor;
    }
    
    public boolean alta( String nombre, String apellidoPaterno, String apellidoMaterno, 
        String telefonoCasa, String telefonoCelular, String email, 
        String facebook, ArrayList<String> danzas) throws Exception {
        boolean booleano = false;
        
        if( validarNombre( nombre, apellidoPaterno, apellidoMaterno ) ) {
            try {
                
                if( validarCamposVacios( telefonoCasa ) ) {
                    if( !validarNumero( telefonoCasa )) {
                        throw new Exception("Ingresa solo numeros en los numeros telefonicos");
                    }
                }
                if( validarCamposVacios( telefonoCelular ) ) {
                    if( !validarNumero( telefonoCelular )) {
                        throw new Exception("Ingresa solo numeros en los numeros telefonicos");
                    }
                } 
                
                if( validarCamposVacios( email ) ) {
                    if( !validarEmail( email ) ) {
                        throw new Exception("Ingresa un correo valido");
                    }
                }
                
                for( String danza: danzas ) {
                    if( !validarPalabras(danza) ) {
                        throw new Exception("Las danzas no llevan numeros");
                    }
                }
                
                mantenimiento = new MProfesor( nombre, apellidoPaterno,
                apellidoMaterno, telefonoCasa, telefonoCelular, email, facebook, 
                danzas );
                mantenimiento.altaInformacionProfesor();
                booleano = true;
                return booleano;
                
            }catch( Exception e ) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
            }
        }
        return booleano;
    }
    
    private boolean validarPalabras( String palabra ) {
        boolean booleano = true;
        for ( int x = 0 ; x < palabra.length() ; x++) {
            if( ( palabra.charAt(x) > 64  && palabra.charAt(x)< 91) || palabra.charAt(x) == 32) {
                booleano = true;
            } else {
                if( ( palabra.charAt(x) > 96  && palabra.charAt(x)< 123) || palabra.charAt(x) == 32) {
                    booleano = true;
                } else {
                    booleano = false;
                    return booleano;
                }
            }
        }
        return booleano;
    }
    
    private boolean validarCamposVacios( String palabra ) {
        if( palabra.trim().length() == 0 ) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarNombre( String nombre, String apellidoPaterno, 
        String apellidoMaterno) {
        boolean booleano = true;
        if ( !validarCamposVacios( nombre ) || !validarCamposVacios( apellidoPaterno )
            || !validarCamposVacios( apellidoMaterno )) {
            JOptionPane.showMessageDialog(null, "Ingresa el nombre completo "
                + "sin espacio al comienzo",null, JOptionPane.ERROR_MESSAGE, null);
            booleano = false;
        } 
        
        if( booleano == true ) {
            if( !validarPalabras( nombre ) || !validarPalabras( apellidoPaterno )
            || !validarPalabras( apellidoMaterno ) ) {
            JOptionPane.showMessageDialog(null, "El nombre no lleva numeros",
                null, JOptionPane.ERROR_MESSAGE, null);
            booleano = false;
            }
        }
        return booleano;
    }
    
    private boolean validarEmail( String email ) {
        String emailArray[] = email.split("@");
        
        if( emailArray.length == 2 ) {
            if( emailArray[1].equalsIgnoreCase("hotmail.com") ||
                emailArray[1].equalsIgnoreCase("gmail.com") || 
                emailArray[1].equalsIgnoreCase("outlook.com") || 
                emailArray[1].equalsIgnoreCase("yahoo.com") ||
                emailArray[1].equalsIgnoreCase("uabc.edu.mx")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void consulta( String proveniente, String dato ) throws Exception {
        mantenimiento = new MProfesor();
        String[][] profesores = mantenimiento.consulta( proveniente, dato );
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        modelo.addColumn("Núm. profesor");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido paterno");
        modelo.addColumn("Apellido materno");
        modelo.addColumn("Curso que imparte");
        modelo.addColumn("Teléfono de casa");
        modelo.addColumn("Teléfono celular");
        String[] registros = new String[ 7 ];
        int numeroFilas = 1;
        for( int x = 0 ; x < profesores.length ; x++  ) {
            for( int y = 0 ; y < 7 ; y++ ) {
                registros[ y ] = profesores[ x ][ y ];
                if( y == 4 ) {
                    String curso = profesores[ x ][ y ];
                    if( curso != null ) {
                        if( curso.charAt(0) == 60 ) {
                            String[] cursos = profesores[ x ][ y ].split("<br>");
                            int longitudCursos = cursos.length;
                            if( longitudCursos > numeroFilas ) {
                                numeroFilas = longitudCursos - 1;
                            }
                        }
                    }
                }
            }
            modelo.addRow( registros );
        }
        
        tbConsultas.setModel( modelo );
        tbConsultas.getColumnModel().getColumn( 0 ).setPreferredWidth( 80 );
        tbConsultas.getColumnModel().getColumn( 1 ).setPreferredWidth( 110 );
        tbConsultas.getColumnModel().getColumn( 2 ).setPreferredWidth( 100 );
        tbConsultas.getColumnModel().getColumn( 3 ).setPreferredWidth( 110 );
        tbConsultas.getColumnModel().getColumn( 4 ).setPreferredWidth( 140 );
        tbConsultas.getColumnModel().getColumn( 5 ).setPreferredWidth( 140 );
        tbConsultas.getColumnModel().getColumn( 6 ).setPreferredWidth( 140 );
        tbConsultas.setRowHeight( numeroFilas * 16 );
        interfaz.recibeDatos( tbConsultas );
    }
    
    public boolean validarNumero( String numero ) {
        boolean verificacion = true;
        for( int x = 0 ; x < numero.length() ; x++ ) {
            if( numero.charAt(x) < 48 || numero.charAt(x) > 57) {
                return false;
            }
        }
        return verificacion;
    }
    
    public void borrarProfesor( String nombre, String apellidoPaterno, String apellidoMaterno ) throws Exception {
        mantenimiento = new MProfesor();
        mantenimiento.bajaProfesor( nombre, apellidoPaterno, apellidoMaterno );
    }
    
    public boolean confirmacionBorradoProfesor( String nombre, String apellidoPaterno, String apellidoMaterno ) throws Exception {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar ese profesor?", 
            "Borrado de profesor", JOptionPane.YES_NO_OPTION);
        if( opcion == 0) {
            borrarProfesor(nombre, apellidoPaterno, apellidoMaterno);
            consulta("consulta", null);
            return true;
        } else {
            return false;
        }
    }
    
    public void insertarEnVentana( int numProfesor, String[] datos, 
            JComboBox cbCursos) throws Exception{
        int numeroProfesor = numProfesor;
        String nombre = datos[0];
        String apellidoPaterno = datos[1];
        String apellidoMaterno = datos[2];
        
        mantenimiento = new MProfesor();
        String[] datosProfesor = mantenimiento.busquedaEspecificaProfesor(numeroProfesor);
        String[] danzasConocidas = mantenimiento.busquedaEspecificaDeDanzas( 
                nombre, apellidoPaterno, apellidoMaterno );
        String[] cursos = mantenimiento.busquedaEspecificaDeCursos(nombre, 
                apellidoPaterno, apellidoMaterno);
        filasAgregadas = cursos.length;
        String[] cursosParaComboBox = mantenimiento.obtenerCursos();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        for( int x = 0 ; x < cursosParaComboBox.length; x++ ) {
            modelo.addElement(cursosParaComboBox[x]);
        }
        cbCursos.setModel(modelo);
        interfaz2.recibeDatosParaModificacion( datosProfesor, danzasConocidas, 
                cursos, cbCursos );
    }
    
    public void agregarFilaATablaCursosModificacion( String curso, JTable cursosJTable ) {
        if( filasAgregadas < filasPorDeafult ) {
            if( validarRepeticionCursos( curso, cursosJTable ) ) {
                cursosJTable.setValueAt( curso, filasAgregadas, 0 );
                filasAgregadas++; 
            } else {
                JOptionPane.showMessageDialog(null, "Ya esta ese curso", 
                    null, JOptionPane.ERROR_MESSAGE, null);
            }   
        } else {
            if( validarRepeticionCursos( curso, cursosJTable ) ) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo = ( DefaultTableModel ) cursosJTable.getModel();
                String[] fila = new String[ 1 ];
                fila[ 0 ] = curso;
                modelo.addRow( fila );
                cursosJTable.setModel( modelo );
                filasAgregadas++; 
            } else {
                JOptionPane.showMessageDialog(null, "Ya esta ese curso", 
                    null, JOptionPane.ERROR_MESSAGE, null);
            }

        }
        interfaz2.recibeTablaCursos( cursosJTable );
    }
    
    public boolean validarRepeticionCursos( String curso, JTable cursosJTable ) {
        boolean igual = true;
        for( int x = 0 ; x < filasAgregadas ; x++ ) {
            String fila = ( String ) cursosJTable.getValueAt( x, 0 );
            if( fila.equals( curso ) ) {
                igual = false;
            }
        }
        return igual;
    }
    
    public void eliminaFilaTablaCursosModificacion( int fila, String contenidoFila, JTable cursosJTable ) {
        if( contenidoFila != null  ) {
            System.out.println("-------" +contenidoFila);
            DefaultTableModel modelo = new DefaultTableModel();
            modelo = ( DefaultTableModel ) cursosJTable.getModel();
            String columna;
            System.out.println(filasAgregadas);

            JTable tbCurso = cursosJTable;
            for( int x = 0 ; x < filasAgregadas ; x++ ) {
                if( x <  fila ) {
                    columna = ( String ) cursosJTable.getValueAt( x, 0 );
                    tbCurso.setValueAt( columna , x, 0 );
                } 

                if( x >= fila )  {
                    columna = ( String ) cursosJTable.getValueAt( x + 1, 0 );
                    tbCurso.setValueAt( columna , x + 1, 0 );
                }

                if( x == filasAgregadas -1 ) {
                    tbCurso.setValueAt( "" , x , 0 );
                }
            }
            filasAgregadas--;
            interfaz2.recibeTablaCursos( tbCurso );
        }
    }
    
    public boolean validarCadenas( String cadena ) {
        if( cadena.charAt(0) == 32) {
            return false;
        } else {
            boolean bandera = true;
            for( int x = 0 ; x < cadena.length() ; x++ ) {
                if( ( cadena.charAt( x ) > 64 && cadena.charAt( x ) < 91 ) 
                    || ( cadena.charAt( x ) > 96 && cadena.charAt( x ) < 123 ) 
                    || cadena.charAt( x ) == 32 ) {
                    
                } else {
                    bandera = false;
                }
            }
            
            return bandera;
        }
    }
    
    public boolean espaciosVacios(String cadena) {
        if( cadena.charAt(0) == 32) {
            return true;
        } else {
            return false;
        }
    }
    
    public void modificacion( String[] nombreCompleto, String nombre, 
            String apellidoPaterno, String apellidoMaterno, String telefonoCasa, 
            String telefonoCelular, String email, String facebook, 
            ArrayList<String> danzas, int contadorDanzas, JTable tbCursos ) {
        cursos.clear();
        boolean booleano = false;
        try {
            if( !validarCadenas( nombre ) || !validarCadenas( apellidoPaterno ) 
                || !validarCadenas( apellidoMaterno )) {
                throw new Exception("El nombre y apellidos no lleva espacio al comienzo"
                    + " ni numeros u otro caracter que no sea letra");
            } 
            
             if( validarCamposVacios( telefonoCasa ) ) {
                if( !validarNumero( telefonoCasa ) ) {
                    throw new Exception("Ingresa solo numeros en los numeros telefonicos");
                }
            } 
            
            if( validarCamposVacios( telefonoCelular ) ) {
                if( !validarNumero( telefonoCelular ) ) {
                    throw new Exception("Ingresa solo numeros en los numeros telefonicos");
                }
            } 
            
            if( validarCamposVacios( email ) ) {
                if( !validarEmail( email ) || espaciosVacios( email )) {
                    throw new Exception("Ingresa un correo valido y sin espacios al comienzo");
                }
            }
            
            if( validarCamposVacios( facebook ) ) {
                if( espaciosVacios( facebook ) ) {
                    throw new Exception("No espacios vacios al comienzo del facebook");
                }
            }
            for( String danza: danzas ) {
                if( danza.trim().length() > 0 ) {
                    if( !validarCadenas(danza) ) {
                        throw new Exception("Las danzas no llevan numeros");
                    }
                }
                
            }
            int longitudCursos = tbCursos.getRowCount();
            String[] cursos = null;
            int contadorCursos = 0;
            if( longitudCursos != 0 ) {
                String curso;
                cursos = new String[ longitudCursos ];
                for( int x = 0 ; x < longitudCursos ; x++ ) {
                    curso = (String) tbCursos.getValueAt( x, 0 );
                    if( curso != null ) {
                        cursos[ contadorCursos ] = (String) tbCursos.getValueAt( x, 0 );
                        contadorCursos++;
                    }
                }
            }
            
            mantenimiento = new MProfesor();
            mantenimiento.modificacion( nombreCompleto, nombre, apellidoPaterno, 
                    apellidoMaterno, telefonoCasa, telefonoCelular, email, 
                    facebook, cursos, danzas);
        
        }catch( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage() , 
                    null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void agregarFilasTablaDanzas( JTable tbDanzas, String danza ) {
        try {
            if( !validarCamposVacios( danza ) ) {
                
            } else {
                if( !validarPalabras( danza ) ) {
                    throw new Exception("Las danzas no llevan numeros o simbolos");
                }
                int contadorDanzas = tbDanzas.getRowCount();
                for( int x = 0 ; x < contadorDanzas ; x++ ) {
                    String danzaExistente = (String) tbDanzas.getValueAt( x, 0 );
                    if( danza.equalsIgnoreCase( danzaExistente ) ) {
                        throw new Exception( "Ya ingresaste esa danza" );
                    }
                }
                DefaultTableModel modelo = ( DefaultTableModel ) tbDanzas.getModel();
                String[] fila = new String[ 1 ];
                fila[ 0 ] = danza;
                modelo.addRow( fila );
                iAltaProfesor.actualizaTablaDanzas( tbDanzas );
            }
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, 
                    JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void eliminarFilasTablaDanzas( JTable tbDanzas, int danza ) {
        DefaultTableModel modelo = ( DefaultTableModel ) tbDanzas.getModel();
        modelo.removeRow(danza);
    }
    
    public void obtenerNumeroProfesor() throws SQLException, Exception {
        mantenimiento = new MProfesor();
        int numero = mantenimiento.obtenerNumero();
        iAltaProfesor.cargaNuevoNumero( numero );
    }
    
    public void busquedaCoincidencia( String proveniente, String dato, JTextField txBuscar ) {
        try {
            if( dato.trim().length() == 0 ) {
                consulta( "consulta", null );
            } else {
            
                if( proveniente.equalsIgnoreCase( "matricula" ) && validarNumero(dato) ) {
                    consulta( proveniente, dato );
                }

                if( proveniente.equalsIgnoreCase( "matricula" ) && validarNumero(dato) == false ) {
                    txBuscar.setText("");
                   throw new Exception( "Solo numeros" );
                }

                if( proveniente.equalsIgnoreCase( "nombre" ) && validarCadenas( dato ) ) {
                    consulta( proveniente, dato );
                }

                if( proveniente.equalsIgnoreCase( "nombre" ) && validarCadenas( dato ) == false ) {
                    txBuscar.setText("");
                    throw new Exception( "Solo letras" );
                }
            }
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
