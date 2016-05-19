/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Christian
 */
public interface InterfaceRecibeDatosPagos {
    public void BusquedaPorMatricula(String[] alumnoMatricula, ArrayList<String> cuenta);
    public void actualizaDatosAlumnoEnEsporadico( String[] datos, JComboBox cbCursos );
    public void actualizarCostos( ArrayList<String> cuenta );
}
