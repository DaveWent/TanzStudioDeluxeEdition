/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Costos;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Interfaces.IRecibeDatosCostos;


/**
 *
 * @author Christian
 */
public class CVistaCostos {
    IRecibeDatosCostos interfaz;
    MCostos mantenimiento = new MCostos();
    
    public CVistaCostos(IRecibeDatosCostos interfaz) {
        this.interfaz = interfaz;
    }
    public void actualizacionVentana() throws Exception{
    double precioInscripcion= mantenimiento.obtenerCostos("Inscripción");
    String precio;
    String valor;
    if(precioInscripcion!=0){
    precio = Double.toString(precioInscripcion);
    }else{
    precio="0.0";
    }
     double precioMensualidad= mantenimiento.obtenerCostos("Mensualidad");
     if(precioMensualidad!=0){
     valor = Double.toString(precioMensualidad);
    }else{
         valor="0.0";
     }
     interfaz.RecibeDatos(precio,valor);
    }
    public void obtenerCurso(JComboBox cbCursos) throws Exception{
     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        String[] cursos = mantenimiento.obtenerCursos();
        int cantidadCursos = cursos.length;
        modelo.addElement( "Seleccione un curso" );
        for( int x = 0 ; x < cantidadCursos ; x++ ) {
            modelo.addElement( cursos[ x ] );
        }
        cbCursos.setModel( modelo );
        interfaz.RecibeCursos(cbCursos);
        
    }
    
    public void obtenerCostoEsporadico( String nombreCursoEsporadico ) throws SQLException{
    double costosEsporadicos = mantenimiento.obtenerCostosEsporadicos(nombreCursoEsporadico);
    String costoEsporadico=Double.toString(costosEsporadicos);
    interfaz.RecibeCostoEsporadico(costoEsporadico);
    }
    
    public void ActualizarCostos(String nombreCos, String costos, String nuevoCosto) throws Exception{
        try {
            for( int x = 0 ; x <nuevoCosto.length() ; x++ ) {
                if( nuevoCosto.charAt( x ) < 48 || nuevoCosto.charAt(x) > 57 ) {
                    throw new Exception("ingresa solo numeros");
                }
            }
            double viejoCosto = Double.parseDouble(costos);
            double nuevoValor = Double.parseDouble(nuevoCosto);
            mantenimiento.actualizarCostos(nombreCos, viejoCosto, nuevoValor);
            actualizacionVentana();
            if( !nombreCos.equalsIgnoreCase("inscripcion") && !nombreCos.equalsIgnoreCase("mensualidad") ) {
                obtenerCostoEsporadico(nombreCos);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, 
                    JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
    
   
 
}

