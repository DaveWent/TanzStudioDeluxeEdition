package Interfaces;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author Dave Went
 */
public interface ICambio {
    
    public void cambioPanelDatosAlumno(); 
    public void cambioPanelDatosProgenitores() ;
    public void cambioPanelSeleccionMaterias() ;
    public void cambioPanelDatosEncuesta();//cambiar nombre despues
    public void actualizaComboBoxCurso( JComboBox comboBoxCursos );
    public void actualizaComboBoxClases( JComboBox comboBoxClases );
    public void inicializarTabla( JTable tbCurso );
    public void actualizarTabla( JTable tbCurso );
    public void enviarMatricula(String matriula);
}
