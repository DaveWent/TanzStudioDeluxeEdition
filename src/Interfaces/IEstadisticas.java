/**
 * InterfaceDatosEstadisticos.java
 * Version 1
 * 19/04/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JComboBox;
import org.jfree.chart.ChartPanel;

/**
 * Clase que contiene los metodos necesarios para que lainterfaz grafica
 * reciba los datos estadisticos que debe mostrar 
 * @author David Omar Cabrera Bernal
 */
public interface IEstadisticas {
    /**
     * Mètodo que recibe los datos para una grafica de pastel y los muestra en 
     * la grafica correspondiente
     * @param PanelConGrafica 
     */
    public void recibeGrafica( ChartPanel PanelConGrafica, int numPanelSeleccionado );
    public void recibeComboBox( JComboBox cbPrimerCicloEscolar, 
            JComboBox cbSegundoCicloEscolar, JComboBox cbCicloEdad1, JComboBox cbCicloEdad2 );
}
