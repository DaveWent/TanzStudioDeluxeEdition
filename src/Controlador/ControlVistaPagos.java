/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.InterfaceRecibeDatosConsultaAdeudos;
import Interfaces.InterfaceRecibeDatosConsultaPagos;
import Interfaces.InterfaceRecibeDatosPagos;
import java.awt.TextField;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christian
 */
public class ControlVistaPagos {
    MantenimientodePagos mantenimiento = new MantenimientodePagos();
    InterfaceRecibeDatosPagos interfazPagos;
    InterfaceRecibeDatosConsultaPagos interfazConsulta;
    InterfaceRecibeDatosConsultaAdeudos interfazAdeudos;

    public ControlVistaPagos() {
    }
    
    public ControlVistaPagos( InterfaceRecibeDatosPagos interfazPagos ) {
        this.interfazPagos = interfazPagos;
    }
    
    public ControlVistaPagos( InterfaceRecibeDatosConsultaPagos interfazConsulta) {
        this.interfazConsulta = interfazConsulta;
    }
    
    public ControlVistaPagos( InterfaceRecibeDatosConsultaAdeudos interfazAdeudos) {
        this.interfazAdeudos = interfazAdeudos;
    }
    
    public void busquedaConMatricula(String sMatricula, String tipo) {
        try {
            
            if (sMatricula.equalsIgnoreCase("")){
                throw new Exception("No escribiste nada");
            }
            for( int x = 0 ; x < sMatricula.length() ; x++ ) {
                if( sMatricula.charAt( x ) < 48 || sMatricula.charAt( x ) > 57 ) {
                   throw new Exception("Escribe una matrícula valida"); 
                }
            }
            
            int matricula = Integer.parseInt(sMatricula);
            ArrayList<String> matriculaAlumno = mantenimiento.busquedaPorMatricula(matricula);
            int longitudAlumno = matriculaAlumno.size();
            
            if( longitudAlumno == 0 ) {
                throw new Exception("No existe ese alumno");
            } else {
                String[] datos = new String[ longitudAlumno ];
                int contador = 0 ;
                for( String cadena : matriculaAlumno ) {
                    datos[ contador ] = cadena;
                    contador++;
                }
                System.out.println( "-------------------" );
                ArrayList<String> cuenta = mantenimiento.busquedaPorId(matricula, tipo);
                for( String palabra : cuenta ) {
                    System.out.println( palabra);
                }
                interfazPagos.BusquedaPorMatricula(datos, cuenta);
            }
        }catch( Exception e  ){
            e.printStackTrace();
            if (e.getMessage().equalsIgnoreCase("")){
                
            } else {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
            }
        }
        
        
    }
    public void busquedaConMatriculaMensualidad(String sMatricula, String tipo) {
        try {
            
            if (sMatricula.equalsIgnoreCase("")){
                throw new Exception("No escribiste nada");
            }
            for( int x = 0 ; x < sMatricula.length() ; x++ ) {
                if( sMatricula.charAt( x ) < 48 || sMatricula.charAt( x ) > 57 ) {
                   throw new Exception("Escribe una matrícula valida"); 
                }
            }
            
            int matricula = Integer.parseInt(sMatricula);
            ArrayList<String> matriculaAlumno = mantenimiento.busquedaPorMatricula(matricula);
            int longitudAlumno = matriculaAlumno.size();
            String[] datos = new String[ longitudAlumno ];
            if( longitudAlumno == 0 ) {
                throw new Exception("No existe ese alumno");
            } else {
                int contador = 0 ;
                for( String cadena : matriculaAlumno ) {
                    datos[ contador ] = cadena;
                    contador++;
                }
                System.out.println( "-------------------" );
                ArrayList<String> cuenta = mantenimiento.busquedaPorIdMensualidad(matricula);
                for( String palabra : cuenta ) {
                    System.out.println( palabra);
                }
                interfazPagos.BusquedaPorMatricula(datos, cuenta);
            }
        }catch( Exception e  ){
            e.printStackTrace();
            if (e.getMessage().equalsIgnoreCase("")){
                
            } else {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
            }
        }
        
        
    }
    
    public void busquedaConNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno,String tipo){
    
                try {
            
            if (nombre.equalsIgnoreCase("") || apellidoPaterno.equalsIgnoreCase("") 
                    || apellidoMaterno.equalsIgnoreCase("")){
                throw new Exception("Escribe el nombre completo porfavor");
            }
            if(validarPalabras(nombre) == false) {
                throw new Exception("Escribe solo con letras"); 
            }
            
            if(validarPalabras(apellidoPaterno) == false) {
                throw new Exception("Escribe solo con letras"); 
            }
            
            if(validarPalabras(apellidoMaterno) == false) {
                throw new Exception("Escribe solo con letras"); 
            }
          
            ArrayList<String> alumno = mantenimiento.busquedaConNombreCompleto(nombre, apellidoPaterno, apellidoMaterno);
            
            int longitudAlumno = alumno.size();
            String[] datos = new String[ longitudAlumno ];
            if( longitudAlumno == 0 ) {
                throw new Exception("No existe ese alumno");
            } else {
                ArrayList<String> cuenta = null;
                        
                int contador = 0 ;
                for( String cadena : alumno ) {
                    datos[ contador ] = cadena;
                    contador++;
                }
                System.out.println( "-------------------" );
                if( tipo.equalsIgnoreCase("inscripcion") ) {
                    cuenta = mantenimiento.busquedaPorId(Integer.parseInt(datos[0]), tipo);
                } else {
                    cuenta = mantenimiento.busquedaPorIdNombreMensualidad(nombre, apellidoPaterno, apellidoMaterno);
                }
                
                for( String palabra : cuenta ) {
                    System.out.println( palabra);
                }
                interfazPagos.BusquedaPorMatricula(datos, cuenta);
            }
            
        }catch( Exception e  ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
            
        }
                
        
    }
    
    public void guardarRecibo(String tipo, String numeroCuenta, String matricula,
            String nombreCompleto,String pagado,String adeudo,String multa,String abono){
        try{
            if (abono.equalsIgnoreCase("")){
                throw new Exception("Ingresa un abono");
            }
             for( int x = 0 ; x < abono.length() ; x++ ) {
                if( abono.charAt( x ) < 48 || abono.charAt( x ) > 57 ) {
                   throw new Exception("Escribe una cantidad valida"); 
                }
            }
            double nuevoAbono = Double.parseDouble(abono);
            double nuevoAdeudo = Double.parseDouble(adeudo);
            if( nuevoAbono > nuevoAdeudo ) {
               throw new Exception("Excediste la cantidad del adeudo"); 
            }
            int numCuenta = Integer.parseInt( numeroCuenta );
            int numMatricula = Integer.parseInt( matricula );
            int nuevoPagado = Integer.parseInt(pagado);
            int nuevoSaldo = (int) (nuevoAbono + nuevoPagado);
            mantenimiento.registrarPago( nuevoSaldo,tipo, nuevoAbono, numMatricula, 
                    numCuenta );
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
            
        }
    }
    
    
    
    public boolean validarPalabras(String palabra) {
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
    
    public void consultaPagos( JTable tbConsultas ) {
        try{
            String[][] pagos = mantenimiento.consultaPagos();
            if( pagos.length == 0 ) {
                throw new Exception("No hay alumnos");
            }
            DefaultTableModel Consultas = (DefaultTableModel) tbConsultas.getModel();
            String[] registros = new String[5];
            
            for(int x = 0 ; x < pagos.length ; x++){
             for(int y = 0 ; y < 5 ; y++){
             registros[y] = pagos[x][y];
             }
             Consultas.addRow(registros);
            }
            tbConsultas.setModel(Consultas);
            interfazConsulta.recibeaConsultaPagos(tbConsultas);
                    
        }catch(Exception e) {
                
        }
        
    }
    
    public void consultaAdeudos( JTable tbConsultas ) {
        try{
            String[][] adeudos = mantenimiento.consultaAdeudo();
            if( adeudos.length == 0 ) {
                throw new Exception("No hay alumnos");
            }
            DefaultTableModel Consultas = (DefaultTableModel) tbConsultas.getModel();
            String[] registros = new String[4];
            for(int x = 0 ; x < adeudos.length ; x++){
                for(int y = 0 ; y < 4 ; y++){
                    registros[y] = adeudos[x][y];
                }
                Consultas.addRow(registros);
            }
            tbConsultas.setModel(Consultas);
            interfazAdeudos.recibeaConsultaAdeudo(tbConsultas);
                    
        }catch(Exception e) {
                
        }
        
    }
    public void buscarCoincidencias(String dato, String proveniente, JTextField buscar,
            JTable tbConsultas){
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        modelo.addColumn("Matricula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo de pago");
        modelo.addColumn("Pago");
        modelo.addColumn("fecha");
        
        String[][] pagos;
        try {
            if( dato.trim().length() == 0 ) {
                tbConsultas.setModel(modelo);
                consultaPagos(tbConsultas);
            } else {
                    if( proveniente.equalsIgnoreCase("matricula") ) {
                    for( int x = 0 ; x < dato.length() ; x++ ) {
                        if( dato.charAt( x ) < 48 || dato.charAt( x ) > 57 ) {
                            throw new Exception("Escribe una matrícula valida"); 
                        }
                    }
                    pagos = mantenimiento.coincidencias(dato, proveniente);
                } else {
                    if(validarPalabras(dato) == false) {
                        throw new Exception("Escribe solo con letras"); 
                    }
                    pagos = mantenimiento.coincidencias(dato, proveniente);
                }

                String[] registros = new String[5];

                for(int x = 0 ; x < pagos.length ; x++){
                    for(int y = 0 ; y < 5 ; y++){
                        registros[y] = pagos[x][y];
                    }
                modelo.addRow(registros);
                }
                tbConsultas.setModel(modelo);
                interfazConsulta.recibeaConsultaPagos(tbConsultas);
                }

            
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null); 
            e.printStackTrace();
        }
    }
    public void buscarCoincidenciasAdeudos(String dato, String proveniente, JTextField buscar,
            JTable tbConsultas){
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        modelo.addColumn("Matricula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo de adeudo");
        modelo.addColumn("Adeudo");
        
        String[][] Adeudos;
        try {
            if( dato.trim().length() == 0 ) {
                tbConsultas.setModel(modelo);
                consultaAdeudos(tbConsultas);
            } else {
                    if( proveniente.equalsIgnoreCase("matricula") ) {
                    for( int x = 0 ; x < dato.length() ; x++ ) {
                        if( dato.charAt( x ) < 48 || dato.charAt( x ) > 57 ) {
                            throw new Exception("Escribe una matrícula valida"); 
                        }
                    }
                    Adeudos = mantenimiento.coincidenciasAdeudos(dato, proveniente);
                } else {
                    if(validarPalabras(dato) == false) {
                        throw new Exception("Escribe solo con letras"); 
                    }
                    Adeudos = mantenimiento.coincidenciasAdeudos(dato, proveniente);
                }

                String[] registros = new String[4];

                for(int x = 0 ; x < Adeudos.length ; x++){
                    for(int y = 0 ; y < 4 ; y++){
                        registros[y] = Adeudos[x][y];
                    }
                modelo.addRow(registros);
                }
                tbConsultas.setModel(modelo);
                interfazAdeudos.recibeaConsultaAdeudo(tbConsultas);
                }

            
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null); 
                    }
    }
    
    public void busquedaEsporadicoPorMatricula( String sMatricula, JComboBox cbCursos ) {
        try {
            if (sMatricula.equalsIgnoreCase("")){
                throw new Exception("No escribiste nada");
            }
            for( int x = 0 ; x < sMatricula.length() ; x++ ) {
                if( sMatricula.charAt( x ) < 48 || sMatricula.charAt( x ) > 57 ) {
                   throw new Exception("Escribe una matrícula valida"); 
                }
            }
            int matricula = Integer.parseInt(sMatricula);
            ArrayList<String> matriculaAlumno = mantenimiento.busquedaPorMatricula(matricula);
            int longitudAlumno = matriculaAlumno.size();
            String[] datos = new String[ longitudAlumno ];
                if( longitudAlumno == 0 ) {
                    throw new Exception("No existe ese alumno");
                } else {
                    int contador = 0 ;
                    for( String cadena : matriculaAlumno ) {
                        datos[ contador ] = cadena;
                        contador++;
                    }
                ArrayList<String> cursos = mantenimiento.obtenerCursosEsporadicosPorMatricula(matricula);
                    if( cursos.size() == 0  ) { 
                        throw new Exception("Ese alumno no tiene cursos esporadicos");
                    }
                DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                modelo.addElement( "Seleccione un curso" );
                    for( String curso : cursos ) {
                        modelo.addElement( curso );
                    }
                cbCursos.setModel( modelo );
                interfazPagos.actualizaDatosAlumnoEnEsporadico( datos, cbCursos );
            }
            
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void busquedaEsporadicoPorNombre( String nombre, String apellidoPaterno, String apellidoMaterno, JComboBox cbCursos ) {
        try {
      
          if (nombre.equalsIgnoreCase("") || apellidoPaterno.equalsIgnoreCase("") 
                    || apellidoMaterno.equalsIgnoreCase("")){
                throw new Exception("Escribe el nombre completo porfavor");
            }
            if(validarPalabras(nombre) == false) {
                throw new Exception("Escribe solo con letras"); 
            }
            
            if(validarPalabras(apellidoPaterno) == false) {
                throw new Exception("Escribe solo con letras"); 
            }
            
            if(validarPalabras(apellidoMaterno) == false) {
                throw new Exception("Escribe solo con letras"); 
            }
            
            String matricula = mantenimiento.obtenerMatriculaPorNombre(nombre, apellidoPaterno, apellidoMaterno);
            String[] datos = new String[ 4 ];
            if( matricula.equalsIgnoreCase("")) {
                throw new Exception("No existe ese alumno");
            } else {
                datos [0] = matricula;
                datos [1] = nombre;
                datos [2] = apellidoPaterno;
                datos [3] = apellidoMaterno;
                int  pMatricula = Integer.parseInt(matricula);
                ArrayList<String> cursos = mantenimiento.obtenerCursosEsporadicosPorMatricula(pMatricula);
                    if( cursos.size() == 0  ) { 
                        throw new Exception("Ese alumno no tiene cursos esporadicos");
                    }
                DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                modelo.addElement( "Seleccione un curso" );
                    for( String curso : cursos ) {
                        modelo.addElement( curso );
                    }
                cbCursos.setModel( modelo );
                interfazPagos.actualizaDatosAlumnoEnEsporadico( datos, cbCursos );
            }
            
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void obtenerCostosCurso( String curso, int matricula ) throws Exception {
        ArrayList<String> datos = mantenimiento.obtenerCostosCursos( matricula, curso);
        interfazPagos.actualizarCostos(datos);
    }
}
