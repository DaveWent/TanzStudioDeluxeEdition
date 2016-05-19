/**
 * ControlVistaEstadisticas.java
 * Version 1
 * 19/04/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Controlador;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import Interfaces.IEstadisticas;

/**
 * Clase que contiene los metodos necesarios para llevar el control de las acciones
 * que ocurren en la interfaz grafica
 * @author David Omar Cabrera Bernal
 */
public class CVistaEstadisticas {
    
    IEstadisticas interfazEstadisticas;
    MantenimientoEstadisticas mantenimiento;
    String[][] atributosGraficas = new String[ 5 ][ 3 ];
    String[][] datos;
    
    /**
     * Constructor vacio
     */
    public CVistaEstadisticas() {
    }
    
    /**
     * Constructor que inicializa la interfaz de estadisticas
     * @param interfazEstadisticas 
     */
    public CVistaEstadisticas( IEstadisticas interfazEstadisticas ) {
        this.interfazEstadisticas = interfazEstadisticas;
    }
    
    /**
     * Constructor que obtiene los cursos existentes junto con su cantidad de alumnos
     * @throws SQLException 
     */
    public void cargarEstadisticas( int numPanel, String nombreCiclo, int opcion ) throws SQLException {
        mantenimiento = new MantenimientoEstadisticas();
        switch( numPanel ) {
            case 0:
                datos = mantenimiento.obtenerInformacionCursos();
                break;
            case 1:
                datos = mantenimiento.ObtenerInformacionEdades();
                break;
            case 2:
                datos = mantenimiento.obtenerInformacionMedios();
                break;
            case 3:
                datos = mantenimiento.obtenerInformacionDanzas();
                break;
            case 4:
                datos = mantenimiento.obtenerInformacionCiclo( nombreCiclo, opcion );
                break;
            case 5:
                datos = mantenimiento.obtenerInformacionCiclo( nombreCiclo, opcion );
                break;
            case 6:
                datos = mantenimiento.obtenerInformacionCiclo( nombreCiclo, opcion );
                break;
            case 7:
                datos = mantenimiento.obtenerInformacionCiclo( nombreCiclo, opcion );
                break;
        }
    }
    /**
     * Metodo que recibe los datos estadisticos de los cursos y los ingresa
     * dentro de un vector DefaultPieDataset
     * @param tipo
     * @throws SQLException 
     */
    public void estadisticasPastel( int numPanelSeleccionado )  {
        DefaultPieDataset estadisticas = new DefaultPieDataset();
        for( int x = 0 ; x < datos.length ; x++) {
            String nombreCurso = datos[ x ][ 0 ];
            int cantidad = Integer.parseInt( datos[ x ][ 1 ] );
            estadisticas.setValue( nombreCurso, cantidad );
        }
        creacionGraficaPastel( numPanelSeleccionado, estadisticas );
    }
    
    /**
     * Método que recibe los datos estadisticos de los cursos y los ingresa
     * dentro de un vector DefaultCategoryDataset 
     * @param numPanelSeleccionado 
     */
    public void estadisticasBarras( int numPanelSeleccionado ) {
        DefaultCategoryDataset estadisticas = new DefaultCategoryDataset();
        for( int x = 0 ; x < datos.length ; x++) {
            String nombreCurso = datos[ x ][ 0 ];
            int cantidad = Integer.parseInt( datos[ x ][ 1 ] );
            estadisticas.setValue(cantidad, nombreCurso, "");
        }
        creacionGraficaBarras( numPanelSeleccionado, estadisticas );
    }
    
    /**
     * Método que recibe el panel seleccionado por el usuario y un vector de
     * datos estadisticos para ingresarlo en una grafica de pastel que posteriormente
     * sera enviado a la interfaz gráfica
     * @param numPanelSeleccionado
     * @param estadisticas 
     */
    public void creacionGraficaPastel( int numPanelSeleccionado, DefaultPieDataset estadisticas ) {
        JFreeChart grafica =null;
        ChartPanel panelGrafica;
        switch( numPanelSeleccionado ) {
            case 0:
                grafica = ChartFactory.createPieChart("Estadistica de alumnos por cursos", 
                        estadisticas, true, true, false);
            break;
            case 1:
                grafica = ChartFactory.createPieChart("Estadisticas de edades de alumnos inscritos", 
                        estadisticas, true, true, false);
            break;
            case 2:
                grafica = ChartFactory.createPieChart("Estadisticas de medios publicitarios", 
                        estadisticas, true, true, false);
                break;
            case 3:
                grafica = ChartFactory.createPieChart("Estadisticas de danzas sugeridas", 
                        estadisticas, true, true, false);
                break;
        }
        panelGrafica = new ChartPanel( grafica );
        panelGrafica.setSize( 700,500 );
        interfazEstadisticas.recibeGrafica( panelGrafica, numPanelSeleccionado );
    }
    /**
     * Método que recibe el panel seleccionado por el usuario y un vector de
     * datos estadisticos para ingresarlo en una grafica de barras que posteriormente
     * sera enviado a la interfaz gráfica
     * @param numPanelSeleccionado
     * @param estadisticas 
     */
    public void creacionGraficaBarras( int numPanelSeleccionado, DefaultCategoryDataset estadisticas ) {
        JFreeChart grafica =null;
        ChartPanel panelGrafica;
        switch( numPanelSeleccionado ) {
            case 0:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de alumnos por curso", 
                "Cursos", "Alumnos", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 1:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de edades de alumnos inscritos", 
                "Edades", "Número de personas", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 2:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de medios publicitarios", 
                "Medios publicitarios", "Número de personas", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 3:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de danzas sugeridas", 
                "Danzas sugeridas", "Número de personas", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 4:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de alumnos por curso", 
                "Cursos", "Alumnos", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 5:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de alumnos por curso", 
                "Cursos", "Alumnos", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 6:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de edades de alumnos inscritos", 
                "Edades", "Número de personas", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
            case 7:
                grafica = ChartFactory.createBarChart3D( "Estadisticas de edades de alumnos inscritos", 
                "Edades", "Número de personas", estadisticas ,PlotOrientation.VERTICAL,
                true, true, true);
                break;
        }
        panelGrafica = new ChartPanel( grafica );
        panelGrafica.setSize( 700,500 );
        interfazEstadisticas.recibeGrafica( panelGrafica, numPanelSeleccionado );
    }
    
    public void actualizarGraficaRangoEdad( int inicio, int fin) {
        try {
            mantenimiento = new MantenimientoEstadisticas();
            datos = mantenimiento.ObtenerInformacionRangoEdad(inicio, fin);
            for( int x = 0 ; x < datos.length ; x++ ) {
                System.out.println(datos[x][0] + " " + datos[x][1]);
            }
        } catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, 
                    JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void cargarCiclosEscolares(JComboBox cbPrimerCiclo, JComboBox cbSegundoCiclo,
            JComboBox cbCicloEdad1, JComboBox cbCicloEdad2) throws SQLException {
        try {
            mantenimiento = new MantenimientoEstadisticas();
            String[] ciclos = mantenimiento.obtenerCiclos();
            if( ciclos == null ) { 
                throw new Exception("No hay ciclos escolares");
            }
            DefaultComboBoxModel modeloPrimeroCiclo = (DefaultComboBoxModel) cbPrimerCiclo.getModel();
            DefaultComboBoxModel modeloSegundoCiclo = (DefaultComboBoxModel) cbSegundoCiclo.getModel();
            DefaultComboBoxModel modeloCicloEdad1 = (DefaultComboBoxModel) cbCicloEdad1.getModel();
            DefaultComboBoxModel modeloCicloEdad2 = (DefaultComboBoxModel) cbCicloEdad2.getModel();
            for( int x = 0 ; x < ciclos.length ; x++ ) {
                modeloPrimeroCiclo.addElement( ciclos[ x ] );
                modeloSegundoCiclo.addElement( ciclos[ x ] );
                modeloCicloEdad1.addElement( ciclos[ x ] );
                modeloCicloEdad2.addElement( ciclos[ x ] );
            }
            interfazEstadisticas.recibeComboBox(cbPrimerCiclo, cbSegundoCiclo, 
                    cbCicloEdad1, cbCicloEdad2);
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, 
                    JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
