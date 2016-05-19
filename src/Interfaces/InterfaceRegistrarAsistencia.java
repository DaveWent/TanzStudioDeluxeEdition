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
public interface InterfaceRegistrarAsistencia {
 
    public void cargarComboBoxCurso(JComboBox cbCurso);
    public void cargarComboBoxClase(JComboBox cbClase);
    public void cargarTableAsistencia(JTable tbAsistencia);
    public void validarAsistencia(boolean fechaValida,int col,int row);
}
