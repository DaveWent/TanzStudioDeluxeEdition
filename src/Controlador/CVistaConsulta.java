/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.ICambioConsulta;

/**
 *
 * @author cristian
 */
public class CVistaConsulta {

    ICambioConsulta interfaz;
    MAlumno mantenimiento;
    JTable ConsultasJTable;

    public CVistaConsulta(ICambioConsulta interfaz, JTable ConsultasJTable) {
        this.interfaz = interfaz;
        this.ConsultasJTable = ConsultasJTable;
    }

    /**
     * *
     * Este metodo regresa un matriz con los datos de todos los alumnos que
     * esten dados de alta, baja o ambos dependiendo que tipo de busqueda se
     * mande ALTA BAJA TODO
     *
     * @param tipo
     * @throws Exception
     */
    public void consultaGeneral(String tipo) throws Exception {
        //   JTable ConsultasJTable = new JTable();
        mantenimiento = new MAlumno();
        String[][] alumnos;
        alumnos = mantenimiento.constulaGeneralAlumno(tipo);

        //se toma el modelo de l tabla
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        //se agregan los titulos
        modelo.addColumn("Matricula");
        modelo.addColumn("Nombre completo");
        modelo.addColumn("Apellido paterno");
        modelo.addColumn("Apellido materno");
        String[] registros = new String[4];

        for (int x = 0; x < alumnos.length; x++) {
            for (int y = 0; y < 4; y++) {
                registros[y] = alumnos[x][y];
            }
            modelo.addRow(registros);
        }

        ConsultasJTable.setModel(modelo);
        ConsultasJTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        ConsultasJTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        ConsultasJTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        ConsultasJTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        ConsultasJTable.setRowHeight(30);

        interfaz.recibeDatos(ConsultasJTable);
    }

    /***
     * Este metodo se encarga de colocar los resultados de la busqueda en una
     * tabla
     * @param estado
     * @param cadena
     * @throws Exception 
     */
    public void consultaFiltradaNombre(String estado, String cadena) throws Exception {
        //JTable ConsultasJTable = new JTable();
        mantenimiento = new MAlumno();
        String[][] alumnos;
        alumnos = mantenimiento.filtrarNombre(estado, cadena);
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        //Se colocan los titulos a la tabla 
        modelo.addColumn("Matricula");
        modelo.addColumn("Nombre completo");
        modelo.addColumn("Apellido paterno");
        modelo.addColumn("Apellido materno");
        String[] registros = new String[4];

        for (int x = 0; x < alumnos.length; x++) {
            for (int y = 0; y < 4; y++) {
                registros[y] = alumnos[x][y];
            }
            modelo.addRow(registros);
        }

        //se le asigna el modelo a la tabla
        ConsultasJTable.setModel(modelo);
        ConsultasJTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        ConsultasJTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        ConsultasJTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        ConsultasJTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        ConsultasJTable.setRowHeight(30);

        interfaz.recibeDatos(ConsultasJTable);
    }

    /***
     * Este metodo coloca el resultado de la busqueda en  la tabla
     * @param estado
     * @param matricula 
     */
    public void consultaFiltradaMatricula(String estado, String matricula) {
        try {
            mantenimiento = new MAlumno();
            String[][] alumnos;
            if (estado == "Todo") {
                alumnos = mantenimiento.buscarMatricula(Integer.parseInt(matricula));
            } else {
                alumnos = mantenimiento.filtrarMatricula(estado, matricula);
            }
            DefaultTableModel modelo = new DefaultTableModel() {
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            modelo.addColumn("Matricula");
            modelo.addColumn("Nombre completo");
            modelo.addColumn("Apellido paterno");
            modelo.addColumn("Apellido materno");
            String[] registros = new String[4];
            
            if( alumnos != null ) {
                for (int x = 0; x < alumnos.length; x++) {
                    for (int y = 0; y < 4; y++) {
                        registros[y] = alumnos[x][y];
                    }
                    modelo.addRow(registros);
                }
            }
            

            ConsultasJTable.setModel(modelo);
            ConsultasJTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            ConsultasJTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            ConsultasJTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            ConsultasJTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            ConsultasJTable.setRowHeight(30);

            interfaz.recibeDatos(ConsultasJTable);
        } catch (NumberFormatException nfex) {
            JOptionPane.showMessageDialog(null, "ERROR matricula", null, 0, null);
        }
    }
    /***
     * Este metodo se encarga de dar de baja a un alumno en la base de datos 
     * @param matricula
     * @param estado 
     */
    public void baja(int matricula,String estado){
         MAlumno matenimiento = new MAlumno();
         mantenimiento.modifEstado(estado, matricula);
    }
}
