/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.IReinscripcion;

/**
 *
 * @author cristian
 */
public class CVistaReinscripcion {

    private int matricula;
    private IReinscripcion interfaz;
    private MAlumno mantenimiento;
    private int filasPorDeafult = 6;
    private int filasAgregadas = 0;
    private ArrayList alcursosConClase = new ArrayList();

    public CVistaReinscripcion(IReinscripcion interfaz) {
        this.interfaz = interfaz;
    }

    /**
     * *
     * Metodo que inicializa la ventana
     *
     * @param matricula
     * @param interfaz
     */
    public CVistaReinscripcion(int matricula, IReinscripcion interfaz) {
        try {
            mantenimiento = new MAlumno();
            this.matricula = matricula;
            this.interfaz = interfaz;
            interfaz.enviaNombre(mantenimiento.obtenerNombre(matricula));
        } catch (Exception ex) {
            System.out.println("error");
        }
    }

    /**
     * metodo que actualiza los combobox con los cursos a los que no pertenece
     * un alumno
     *
     * @param cbCursos
     * @param matricula
     * @throws Exception
     */
    public void actualizaComboBoxCurso(JComboBox cbCursos, int matricula) throws Exception {
        mantenimiento = new MAlumno();
        int contadorCursos = 0;
        //obtiene los cursos del alumno a los que no esta inscrito
        ArrayList<String> cursosRecibidos = mantenimiento.obtenerCursosNoInscrito(matricula);
        String[] cursosArray = new String[cursosRecibidos.size()];

        for (String curso : cursosRecibidos) {
            cursosArray[contadorCursos] = curso;
            contadorCursos++;
        }

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < cursosArray.length; x++) {
            modelo.addElement(cursosArray[x]);
        }
        cbCursos.setModel(modelo);
        interfaz.actualizaComboBoxCurso(cbCursos);
    }

    /**
     * *
     * Metodo que actualiza el nombre de las clases dependiendo de el nombre del
     * curso que se envia
     *
     * @param cbClases
     * @param nombre
     * @throws Exception
     */
    public void actualizaComboBoxClases(JComboBox cbClases, String nombre) throws Exception {
        String[][] clases = mantenimiento.obtenerClases(nombre);
        String[] clasesComboBox = new String[clases.length];

        for (int x = 0; x < clases.length; x++) {
            clasesComboBox[x] = clases[x][0] + " de " + clases[x][2] + " a " + clases[x][3] + " Salon " + clases[x][1];
        }

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < clasesComboBox.length; x++) {
            modelo.addElement(clasesComboBox[x]);
        }
        cbClases.setModel(modelo);
        interfaz.actualizaComboBoxClases(cbClases);
    }

    /**
     * metodo que inicializa la tabla y la hace no editable
     *
     * @param tbCurso
     */
    public void inicializarTabla(JTable tbCurso) {
        String[] campovacio = {"", "", "", ""};
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        modelo.addColumn("Curso ");
        modelo.addColumn("Día");
        modelo.addColumn("Hora");
        modelo.addColumn("Salón");
        modelo.addRow(campovacio);
        modelo.addRow(campovacio);
        modelo.addRow(campovacio);
        modelo.addRow(campovacio);
        modelo.addRow(campovacio);
        modelo.addRow(campovacio);
        tbCurso.setModel(modelo);
        interfaz.inicializarTabla(tbCurso);
    }

    /**
     * *
     * metodo que actualiza la tabla cuando se agregan o se quitan clases
     *
     * @param curso
     * @param clase
     * @param tbCurso
     */
    public void actualizaTabla(String curso, String clase, JTable tbCurso) {
        if (filasAgregadas < filasPorDeafult) {
            String[] clases = clase.split(" ");
            if (validarTabla(curso, clase, tbCurso)) {

                tbCurso.setValueAt(curso, filasAgregadas, 0);
                tbCurso.setValueAt(clases[0], filasAgregadas, 1);
                tbCurso.setValueAt(clases[2] + " " + clases[3] + " a " + clases[5]
                        + " " + clases[6], filasAgregadas, 2);
                tbCurso.setValueAt(clases[8], filasAgregadas, 3);
                filasAgregadas++;
            }

        } else {
            if (validarTabla(curso, clase, tbCurso)) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo = (DefaultTableModel) tbCurso.getModel();
                String[] claseArray = new String[4];
                String[] clases = clase.split(" ");
                claseArray[0] = curso;
                claseArray[1] = clases[0];
                claseArray[2] = clases[2] + " a " + clases[4];
                claseArray[3] = clases[6];
                modelo.addRow(claseArray);
                tbCurso.setModel(modelo);
                filasAgregadas++;
            }
        }
        interfaz.actualizarTabla(tbCurso);
    }

    /**
     * **
     * Metodo que elimina las clases seleccionadas
     *
     * @param fila
     * @param tbCurso
     */
    public void eliminarFila(int fila, JTable tbCurso) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tbCurso.getModel();
        String columna;

        JTable cursosJTable = tbCurso;
        for (int x = 0; x < filasAgregadas; x++) {
            if (x < fila) {
                for (int y = 0; y < 4; y++) {
                    columna = (String) tbCurso.getValueAt(x, y);
                    cursosJTable.setValueAt(columna, x, y);
                }
            }
            if (x >= fila) {
                for (int y = 0; y < 4; y++) {
                    columna = (String) tbCurso.getValueAt(x + 1, y);
                    cursosJTable.setValueAt(columna, x + 1, y);
                }
            }

            if (x == filasAgregadas - 1) {
                cursosJTable.setValueAt("", x, 0);
                cursosJTable.setValueAt("", x, 1);
                cursosJTable.setValueAt("", x, 2);
                cursosJTable.setValueAt("", x, 3);
            }
        }
        filasAgregadas--;
        interfaz.actualizarTabla(cursosJTable);
    }

    /**
     * *
     * metodo que valida si no se reptien cursos en la tabla
     *
     * @param curso
     * @param clase
     * @param tbCurso
     * @return
     */
        public boolean validarTabla(String curso, String clase, JTable tbCurso) {
        String cursoTabla;
        String dia;
        String hora;
        String salon;
        String[] clases = clase.split(" ");
        String horaCompleta = "";
        System.out.println("lo que contiene la hora  coompleta es");
        for (int i = 2; i < 7; i++) {
            horaCompleta += clases[i] + " ";

        }
        for (int x = 0; x < filasAgregadas; x++) {
            cursoTabla = (String) tbCurso.getValueAt(x, 0);
            dia = (String) tbCurso.getValueAt(x, 1);
            hora = (String) tbCurso.getValueAt(x, 2);
            salon = (String) tbCurso.getValueAt(x, 3);
            System.out.println(horaCompleta);
            hora += " ";
            System.out.println(hora);
            System.out.println("su tamaño" + hora.length() + "a" + horaCompleta.length());
            System.out.println("son iguales?" + hora.equalsIgnoreCase(horaCompleta));
            System.out.println("diatabla = " + dia + " dia enviado=" + clases[0]);
            System.out.println("son iguales??" + dia.equalsIgnoreCase(clases[0]));
            System.out.println("salon enviado = " + salon + " salon tala = " + clases[8]);
            System.out.println("son iguales???" + salon.equalsIgnoreCase(clases[8]));
            System.out.println("curso enviado = " + curso + "curso enviado" + "curso tabala = " + cursoTabla);
            System.out.println("son iguales????" + curso.equals(cursoTabla));

            if (curso.equals(cursoTabla) && dia.equals(clases[0]) && hora.equals(horaCompleta)
                    && salon.equals(clases[8])) {
                JOptionPane.showMessageDialog(null, "Ya agregaste esa clase",
                        null, JOptionPane.ERROR_MESSAGE, null);
                return false;
            }
        }
        return true;
    }


    /***
     * metodo utilizado para validar la seleccion de materias en donde si los datos
     * estan correctos  se agregan a alCursos con clase
     * @param btCursos
     * @throws SQLException 
     */
    public void validarPanelSeleccionMaterias(JTable btCursos) throws SQLException {
        alcursosConClase.clear();
        if (filasAgregadas > 0) {
            for (int x = 0; x < filasAgregadas; x++) {
                String curso = (String) btCursos.getValueAt(x, 0);
                alcursosConClase.add(curso);//se agrega el nombre del curso
                String dia = (String) btCursos.getValueAt(x, 1);
                String hora = (String) btCursos.getValueAt(x, 2);
                String[] horas = hora.split(" ");
                String horaInicio = horas[0] + " " + horas[1];
                String horaFin = horas[3] + " " + horas[4];
                String salon = (String) btCursos.getValueAt(x, 3);
                //se agrega el id de la clase 
                alcursosConClase.add(mantenimiento.obtenerId(dia, salon, horaInicio, horaFin));
                System.out.println("aregue");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene clases asignadas al alumno",
                    null, JOptionPane.ERROR_MESSAGE, null);
        }
    }

    public void Reinscribir() {
        try{
        mantenimiento.reinscripcion(matricula,alcursosConClase);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error en la reinscripcion",
                    null, JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
