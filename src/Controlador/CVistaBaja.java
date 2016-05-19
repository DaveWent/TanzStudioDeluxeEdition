/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.ICambioBaja;

/**
 *
 * @author cristian
 */
public class CVistaBaja {

    private ICambioBaja interfazBaja;
    private MAlumno mantenimiento;
    private JTable tbCursos;

    public CVistaBaja(ICambioBaja interfazBaja, JTable tbCurso) {
        this.interfazBaja = interfazBaja;
        tbCursos = new JTable();
        mantenimiento= new MAlumno();
        this.tbCursos=tbCurso;

    }

    public void baja() {

    }

    public void actualizarTabla(int matricula) {
        try {
            
            String nombre=mantenimiento.obtenerNombre(matricula);
            System.out.println("nombre"+nombre);
            this.interfazBaja.escribeNombre(mantenimiento.obtenerNombre(matricula));
            System.out.println("vivo");
            ArrayList alCursos = mantenimiento.consultaCursosAlumno(matricula);
            DefaultTableModel modelo = new DefaultTableModel() {
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //Se colocan los titulos a la tabla 
            modelo.addColumn("Curso");
            String[] registros = new String[1];
            for (int x = 0; x < alCursos.size(); x++) {
                registros[0] = alCursos.get(x).toString();
                modelo.addRow(registros);
            }
            System.out.println("despues del for");
            //se le asigna el modelo a la tabla
            tbCursos.setModel(modelo);
            tbCursos.getColumnModel().getColumn(0).setPreferredWidth(70);
            //tbCursos.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbCursos.setRowHeight(30);
            System.out.println("antes de utilzar la interface");
            interfazBaja.recibeDatos(tbCursos);
            System.out.println("despues de utilizar la interface");
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
    public void baja(int matricula,String nombreCurso){
        try{
        mantenimiento.baja(matricula,nombreCurso);
        actualizarTabla(matricula);
        }catch(Exception ex){
            System.out.println("error baja");
        }
    }
}
