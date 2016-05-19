package Controlador;

import Interfaces.ICierraVentana;
import Modelo.Alumno;
import Modelo.Encuesta;
import Modelo.Progenitor;
import Modelo.Salud;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.ICambio;

/**
 *
 * @author Cristian Millan Ruiz
 */
public class CVistaInscripcion {

    private ICambio interfaz;
    private ICierraVentana interfaceCerrar;
    private CValidacion validacion = new CValidacion();
    private MAlumno mantenimiento = new MAlumno();
    private Alumno alumnodto = null;
    private Progenitor madre = null;
    private Progenitor padre = null;
    private Salud salud = null;
    private Encuesta encuesta = null;
    int edad;
    int filasPorDeafult = 6;
    int filasAgregadas = 0;
    ArrayList alcursosConClase = new ArrayList();
    String problemaSeleccionado;
    boolean ban;

    public CVistaInscripcion(ICambio interfaz, ICierraVentana interfaceCerrar) {
        this.interfaz = interfaz;
        this.interfaceCerrar = interfaceCerrar;
        int matricula = mantenimiento.matriculaAlta();
        interfaz.enviarMatricula(matricula + "");
    }

    public String convertirMayusculas(String cadena) {
        return cadena.toUpperCase();
    }

    /**
     * *
     * Metodo que valida todos los datos del panelDatosAlumno, y inicializa los
     * objetos alumno,salud y cambia de panel
     *
     * @param alAlumnoDatos
     * @param alAlumnoNombre
     * @param alAlumnoTipo
     * @param alSaludDatos
     * @param alSaludNombre
     * @param alSaludTipo
     * @param tieneProblema
     */
    public void validaPanelDatosAlumno(ArrayList alAlumnoDatos, ArrayList alAlumnoNombre,
            ArrayList alAlumnoTipo, ArrayList alSaludDatos, ArrayList alSaludNombre,
            ArrayList alSaludTipo, boolean tieneProblema) {
        try {
            validacion.validaDato(alAlumnoDatos, alAlumnoNombre, alAlumnoTipo,"Alumno");
            alumnodto = new Alumno(alAlumnoDatos);
            this.edad = alumnodto.getEdad();
            if (tieneProblema) {
                validacion.validaDato(alSaludDatos, alSaludNombre, alSaludTipo,"Alumno");
                salud = new Salud(alSaludDatos);
                System.out.println("salud");
                interfaz.cambioPanelSeleccionMaterias();
                System.out.println("llegue");
            }
            interfaz.cambioPanelSeleccionMaterias();
            System.out.println("llegue");

        } catch (Exception e) {

        }
    }

    /**
     * *
     * Metodo que valida todos los datos del panelDatosProgenitor, y inicializa
     * los objetos madre y padre, cambia de panel
     *
     * @param alDatosMadre
     * @param alDatosPadre
     * @param alNombreProgenitor
     * @param alTipoProgenitor
     * @param alDatosEscolaridad
     * @param alNombreEscolaridad
     * @param alTipoEscolaridad
     */
    public void validaPanelDatosProgenitores(ArrayList alDatosMadre, ArrayList alDatosPadre,
            ArrayList alNombreProgenitor, ArrayList alTipoProgenitor, ArrayList alDatosEscolaridad,
            ArrayList alNombreEscolaridad, ArrayList alTipoEscolaridad) {
        try {
            boolean nullosPadre = false;
            boolean nullosMadre = false;
            nullosPadre = validacion.isNulo(alDatosPadre);
            nullosMadre = validacion.isNulo(alDatosMadre);
            System.out.println("---------------------------------");
            System.out.println(nullosPadre);
            System.out.println(nullosMadre);
            System.out.println("----------------------");
            if (nullosPadre && nullosMadre) {
                JOptionPane.showMessageDialog(null, "Debe llenar los registros de porlomenos un progenitor");
                throw new Exception();
            } else if (nullosMadre && !nullosPadre) {
                validacion.validaDato(alDatosPadre, alNombreProgenitor, alTipoProgenitor,"Padre");
                System.out.println("ruben"+ alDatosPadre );
                padre = new Progenitor(alDatosPadre);

            } else if (!nullosMadre && nullosPadre) {
                validacion.validaDato(alDatosMadre, alNombreProgenitor, alTipoProgenitor,"Madre");
                System.out.println("cristian"+alDatosMadre);
                madre = new Progenitor(alDatosMadre);
            } else {
                validacion.validaDato(alDatosMadre, alNombreProgenitor, alTipoProgenitor,"Madre");
                validacion.validaDato(alDatosPadre, alNombreProgenitor, alTipoProgenitor,"Padre");
                madre = new Progenitor(alDatosMadre);
                padre = new Progenitor(alDatosPadre);
            }
            System.out.println("-----------------------");
            validacion.validaDato(alDatosEscolaridad, alNombreEscolaridad, alTipoEscolaridad,"Escolaridad");
            alumnodto.setEscuela(alDatosEscolaridad.get(0).toString());
            alumnodto.setGrado(alDatosEscolaridad.get(1).toString());
            interfaz.cambioPanelDatosEncuesta();
        } catch (Exception ex) {
            //System.out.println("exception");
            //  Logger.getLogger(ControlVistaInscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     *
     * @param numPanel
     */
    public void cambioPanel(int numPanel) {
        if (numPanel == 4 && edad > 18) {
            numPanel = 2;
        } else {
            numPanel = numPanel - 1;
        }

        switch (numPanel) {
            case 1:
                interfaz.cambioPanelDatosAlumno();
                break;
            case 2:
                interfaz.cambioPanelSeleccionMaterias();
                break;
            case 3:
                interfaz.cambioPanelDatosProgenitores();
                break;
        }
    }

    public void actualizaComboBoxCurso(JComboBox cursosJComboBox) throws Exception {

        int contadorCursos = 0;
        ArrayList<String> cursosRecibidos = mantenimiento.obtenerCursos();
        String[] cursosArray = new String[cursosRecibidos.size()];

        for (String wolf : cursosRecibidos) {
            cursosArray[contadorCursos] = wolf;
            contadorCursos++;
        }

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < cursosArray.length; x++) {
            modelo.addElement(cursosArray[x]);
        }
        cursosJComboBox.setModel(modelo);
        interfaz.actualizaComboBoxCurso(cursosJComboBox);
    }

    public void actualizaComboBoxClases(JComboBox clasesJComboBox, String nombre) throws Exception {
        String[][] clases = mantenimiento.obtenerClases(nombre);
        String[] clasesComboBox = new String[clases.length];

        for (int x = 0; x < clases.length; x++) {
            clasesComboBox[x] = clases[x][0] + " de " + clases[x][2] + " a " + clases[x][3] + " Salon " + clases[x][1];
        }

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < clasesComboBox.length; x++) {
            modelo.addElement(clasesComboBox[x]);
        }
        clasesJComboBox.setModel(modelo);
        interfaz.actualizaComboBoxClases(clasesJComboBox);
    }

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

    public void actualizaTabla(String curso, String clase, JTable tbCurso) {
        if (filasAgregadas < filasPorDeafult) {
            System.out.println("Hola");
            String[] clases = clase.split(" ");
            if (validarTabla(curso, clase, tbCurso)) {
                System.out.println("entre aqui");
                tbCurso.setValueAt(curso, filasAgregadas, 0);
                tbCurso.setValueAt(clases[0], filasAgregadas, 1);
                tbCurso.setValueAt(clases[2] + " " + clases[3] + " a " + clases[5]
                        + " " + clases[6], filasAgregadas, 2);
                tbCurso.setValueAt(clases[8], filasAgregadas, 3);
                filasAgregadas++;
                System.out.println("filas agregadas=" + filasAgregadas);
                System.out.println("fulas");
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

    public void validarPanelSeleccionCursos(JTable btCursos) throws SQLException {
        alcursosConClase.clear();
        if (filasAgregadas > 0) {
            for (int x = 0; x < filasAgregadas; x++) {
                String curso = (String) btCursos.getValueAt(x, 0);
                alcursosConClase.add(curso);
                String dia = (String) btCursos.getValueAt(x, 1);
                String hora = (String) btCursos.getValueAt(x, 2);
                String[] horas = hora.split(" ");
                String horaInicio = horas[0] + " " + horas[1];
                String horaFin = horas[3] + " " + horas[4];
                String salon = (String) btCursos.getValueAt(x, 3);
                alcursosConClase.add(mantenimiento.obtenerId(dia, salon, horaInicio, horaFin));
            }
            if (edad < 18) {
                interfaz.cambioPanelDatosProgenitores();
            } else {
                interfaz.cambioPanelDatosEncuesta();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene clases asignadas al alumno",
                    null, JOptionPane.ERROR_MESSAGE, null);
        }
    }

    public void validaPaneDatoslEncuesta(ArrayList alDatosEncuesta, ArrayList alNombreEnceusta, ArrayList alTipoEncuesta) {
        try {
            validacion.validaDato(alDatosEncuesta, alNombreEnceusta, alTipoEncuesta,"Encuesta");
            encuesta = new Encuesta(alDatosEncuesta);
            inscripcion();

        } catch (Exception e) {

        }
    }

    /**
     * *
     * Metodo que se encarga de generar un arrayList con todos los objetos
     * diferentes de null y manda llamar el metodo alta
     */
    public void inscripcion() {
        ArrayList aldDatos = new ArrayList();
        aldDatos.add(alumnodto);
        if (salud != null) {
            aldDatos.add(salud);
        }
        if (madre != null) {
            aldDatos.add(madre);
            
        }
        if(padre !=null){
            aldDatos.add(padre);
        }
        aldDatos.add(encuesta);
        try {
            mantenimiento.inscripcion(aldDatos, alcursosConClase);
            interfaceCerrar.cerrarVentana();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, en la inscripcion",
                    null, JOptionPane.ERROR_MESSAGE, null);
        }

    }
}
