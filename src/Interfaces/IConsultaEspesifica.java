/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cristian
 */
public interface IConsultaEspesifica {
    
    /**
     *Este metdo permite sobre escribir un array con los datos del alumno
     * @param padre
     */
    public void escribirAlumno(ArrayList alumno);
    /***
     * Este metdo permite sobre escribir un array con los datos de el estado de salud
     * @param salud 
     */
    public void escribirSalud(ArrayList salud);
    /***
     * Este metdo permite sobre escribir un array con los datos de la amdre
     * @param padre 
     */
    public void escribirMadre(ArrayList padre);
    /***
     * Este metdo permite sobre escribir un array con los datos de el padre
     * @param madre 
     */
    public void escribirPadre(ArrayList madre);
    /***
     * Este metdo permite sobre escribir un array con los datos de la encuesta 
     * @param Encuesta 
     */
    public void escribirEncuesta(ArrayList Encuesta);
    public void escribirEscolaridad(ArrayList escolaridad);
    public void escribirCursos(String[] cursos);
    public void escribirClases(String[] clases);
    public void enviarTabla(DefaultTableModel modelo);
}
