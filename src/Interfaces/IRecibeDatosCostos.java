/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.JComboBox;

/**
 *
 * @author Christian
 */
public interface IRecibeDatosCostos {
   public void RecibeDatos(String precio,String valor);
   public void RecibeCursos(JComboBox cbCursos);
   public void RecibeCostoEsporadico(String costoEsporadico);
}
