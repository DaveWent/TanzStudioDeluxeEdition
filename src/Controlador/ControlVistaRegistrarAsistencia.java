/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.InterfaceRegistrarAsistencia;
import Modelo.Alumno;
import Modelo.RenglonAlumno;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author cristian
 */
public class ControlVistaRegistrarAsistencia {

    private InterfaceRegistrarAsistencia interfaz;
    private String curso;
    private String clase;
    private boolean modo;

    public ControlVistaRegistrarAsistencia(InterfaceRegistrarAsistencia interfaz, boolean modo) {
        this.interfaz = interfaz;
        this.modo = modo;
    }

    public void actualizaComboBoxCurso(JComboBox cbCursos) throws Exception {

        MAlumno mantenimiento = new MAlumno();
        int contadorCursos = 0;
        //obtiene los cursos del alumno a los que no esta inscrito
        ArrayList<String> cursosRecibidos = mantenimiento.obtenerCursos();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < cursosRecibidos.size(); x++) {
            modelo.addElement(cursosRecibidos.get(x));
        }
        cbCursos.setModel(modelo);
        interfaz.cargarComboBoxCurso(cbCursos);

    }

    public void actualizaComboBoxClase(JComboBox cbClases, String nombre) throws Exception {

        MAlumno mantenimiento = new MAlumno();
        String[][] clases = mantenimiento.obtenerClases(nombre);
        String[] clasesComboBox = new String[clases.length];

        for (int x = 0; x < clases.length; x++) {
            clasesComboBox[x] = clases[x][0] + " de " + clases[x][2] + " a " + clases[x][3] + " Salon " + clases[x][1];
        }

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < clasesComboBox.length; x++) {
            modelo.addElement(clasesComboBox[x]);
            System.out.println(clasesComboBox[x]);
        }
        System.out.println("aasas");
        cbClases.setModel(modelo);
        interfaz.cargarComboBoxClase(cbClases);

    }

    /**
     * *
     *
     * @param tbLista
     * @param clase
     * @throws Exception
     */
    public void actualizaTabla(JTable tbAsistencia, String clase) throws Exception {

        ControlRegistarAsistencias control = new ControlRegistarAsistencias();
        ArrayList alAlumnos = new ArrayList();
        ArrayList fechas = new ArrayList();
        String fechaInicio = null;
        String fechaFin = null;
        String[] datos = clase.split(" ");
        for (String dato : datos) {
            System.out.println("dato:" + dato);
        }
        String dia = datos[0];
        String horaI = datos[2] + " " + datos[3];
        String horaF = datos[5] + " " + datos[6];
        String salon = "" + datos[8];
        int idClase = control.obtenerIdClase(dia, salon, horaI, horaF);//se obtiene el id de la clase
        alAlumnos = control.ConsultarDatosAlumno(idClase); //Se consultan todos los alumnos que Aiisten a esa clase
        if (!modo) {
            fechas = control.obtenerCicloActual();
            fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format((Date) fechas.get(0));
            fechaFin = new SimpleDateFormat("dd/MM/yyyy").format((Date) fechas.get(1));
        } else {
            fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format(control.getPrimerDiaDelMes());
            fechaFin = new SimpleDateFormat("dd/MM/yyyy").format(control.getUltimoDiaDelMes());
        }
        String[] Arrayfechas = control.generarFechas(fechaInicio, fechaFin, dia);
        for (int i = 0; i < alAlumnos.size(); i++) {
            String[] asistenciaAlumno = control.asistenciasAlumno(idClase, ((RenglonAlumno) alAlumnos.get(i)).getMatricula());
            boolean[] asistenciasTotales = control.asistenciasTotales(Arrayfechas, asistenciaAlumno);
            ((RenglonAlumno) alAlumnos.get(i)).setAsistencia(asistenciasTotales);
        }
        System.out.println(alAlumnos.size());
        inicializartabla(Arrayfechas, alAlumnos.size(), tbAsistencia);
        tbAsistencia = llenarTabla(alAlumnos, tbAsistencia);
        interfaz.cargarTableAsistencia(tbAsistencia);
    }

    public void inicializartabla(String[] fechas, int numAlumno, JTable tbAsistencia) {
        DefaultTableModel modelo1 = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int col) {
                String fecha = getColumnName(col);
                Date hoy = new Date();
                String sHoy = new SimpleDateFormat("dd/MM/yyyy").format(hoy);
                try {
                    hoy = new SimpleDateFormat("dd/MM/yyyy").parse(sHoy);
                    Date fecha1=  new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                    if (hoy.after(fecha1)) {
                        return true;
                    } else if (hoy.before(fecha1)) {
                       return false;
                    } else {
                        return true;
                    }

                } catch (ParseException ex) {
                //    Logger.getLogger(ControlVistaRegistrarAsistencia.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }

            }
        };
        String[] registros = new String[2 + fechas.length];
        modelo1.addColumn("Matricula");
        modelo1.addColumn("Nombre");
        for (int i = 0; i < fechas.length; i++) {
            modelo1.addColumn(fechas[i]);
        }
        for (int i = 0; i < registros.length; i++) {
            registros[i] = null;
        }
        for (int i = 0; i < numAlumno; i++) {
            modelo1.addRow(registros);

        }
        tbAsistencia.setModel(modelo1);

        for (int i = 2; i < tbAsistencia.getColumnCount(); i++) {
            ingresarCheckBox(i, tbAsistencia);
        }
    }

    public void ingresarCheckBox(int num, JTable tbAsistencia) {
        TableColumn tc = tbAsistencia.getColumnModel().getColumn(num);
        tc.setCellEditor(tbAsistencia.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tbAsistencia.getDefaultRenderer(Boolean.class));
        tc = tbAsistencia.getColumnModel().getColumn(0);
        //   tbAsistencia.getColumnModel().removeColumn(tc);
        tbAsistencia.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    public JTable llenarTabla(ArrayList DatosAlumno, JTable tbAsistencia) {
        TableModel modelo1 = tbAsistencia.getModel();
        for (int i = 0; i < DatosAlumno.size(); i++) {
            for (int j = 1; j < modelo1.getColumnCount(); j++) {
                if (j < 2) {
                    modelo1.setValueAt(((RenglonAlumno) DatosAlumno.get(i)).getMatricula(), i, 0);
                    modelo1.setValueAt(((RenglonAlumno) DatosAlumno.get(i)).getNombre(), i, 1);
                } else {
                    System.out.println("imprimo" + j);
                    System.out.println("imprimo2" + (j - 2));
                    System.out.println(((RenglonAlumno) DatosAlumno.get(i)).getAsistencia());
                    boolean[] vec22 = ((RenglonAlumno) DatosAlumno.get(i)).getAsistencia();

                    System.out.println(vec22.length);
                    System.out.println();
                    modelo1.setValueAt(((RenglonAlumno) DatosAlumno.get(i)).getAsistencia()[j - 2], i, j);
                }
            }
        }
        tbAsistencia.setModel(modelo1);
        return tbAsistencia;
    }

    public void validaAsistencia(String fecha, int col, int row) throws ParseException {
        boolean fechaValida = false;
        java.util.Date hoy = new Date();
        java.util.Date fecha1 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        if (hoy.after(fecha1)) {
            fechaValida = true;
        }
        interfaz.validarAsistencia(fechaValida, col, row);
    }

    public void asistencia(String fecha, String curso, String clase, int matricula, boolean a) throws Exception {
        ControlRegistarAsistencias control = new ControlRegistarAsistencias();

        String[] datos = clase.split(" ");
        for (String dato : datos) {
            System.out.println("dato:" + dato);
        }
        String dia = datos[0];
        String horaI = datos[2] + " " + datos[3];
        String horaF = datos[5] + " " + datos[6];
        String salon = "" + datos[8];
        int idClase = control.obtenerIdClase(dia, salon, horaI, horaF);//se obtiene el id de la clase
        java.util.Date fecha1 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        java.sql.Date fecha2 = new java.sql.Date(fecha1.getTime());
        if (a) {
            System.out.println("entre a hacer la alta");
            control.alta(matricula, curso, fecha2, idClase);
            System.out.println("realice la alta");
        } else {
            control.baja(matricula, fecha2, idClase);
        }
    }

    public void validarTabla(JTable tbAsistencias) {
        try {
            int colum = tbAsistencias.getColumnCount();
            int row = tbAsistencias.getRowCount();

            Date hoy = new Date();
            String Sfecha1 = new SimpleDateFormat("dd/MM/yyyy").format(hoy);
            hoy = new SimpleDateFormat("dd/MM/yyyy").parse(Sfecha1);

            for (int i = 2; i < tbAsistencias.getColumnCount(); i++) {
                String fechaColumName = tbAsistencias.getColumnName(i);
                Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaColumName);
                if (hoy.after(fecha)) {
//                    tbAsistencias.getcol
                } else if (hoy.before(fecha)) {
                    System.out.println("hoy es menor");
                } else {
                    System.out.println("son iguales");
                }
            }
        } catch (ParseException PEX) {

        }
    }
}
