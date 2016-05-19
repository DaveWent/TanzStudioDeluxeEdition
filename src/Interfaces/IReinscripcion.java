/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author cristian
 */
public interface IReinscripcion {
 
    public void enviaNombre(String nombre);
    public void actualizaComboBoxCurso( JComboBox cbCursos);
    public void actualizaComboBoxClases(JComboBox cbClases);
    public void inicializarTabla( JTable tbCurso);
    public void actualizarTabla( JTable tbCurso);
}
