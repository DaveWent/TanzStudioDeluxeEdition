/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.table.TableModel;
import Interfaces.IConsultaEspesifica;
import Interfaces.ICambioModificacion;

/**
 *
 * @author cristian
 */
public class CVistaConsultaEspesifica {

    private MAlumno mantenimiento;
    private ArrayList todo = new ArrayList();
    private CValidacion validacion = new CValidacion();
    private IConsultaEspesifica interfazConsulta;
    private ICambioModificacion interfazModificacion;
    ICierraVentana interfazCerrar;
    private Alumno alumno;
    private Salud salud;
    String[] cursos;
    String[] clase;
    private Encuesta encuesta;
    private Progenitor madre;
    private Progenitor padre;
    private int matricula = 0;
    private int idSalud = 0;
    private int idPadre = 0;
    private int idMadre = 0;
    private int idEncuesta = 0;
    ArrayList alcursosConClase = new ArrayList();

    public CVistaConsultaEspesifica(int matricula, IConsultaEspesifica interfazConsulta,
            ICambioModificacion interfazModificacion, ICierraVentana interfazCerrar) throws Exception {
        mantenimiento = new MAlumno();
        todo = mantenimiento.consultaEspesifica(matricula);
        this.matricula = matricula;
        this.interfazConsulta = interfazConsulta;
        this.interfazModificacion = interfazModificacion;
        this.interfazCerrar = interfazCerrar;
        System.out.println("tamaño de todo " + todo.size());
        for (int i = 0; i < todo.size(); i++) {
            if (todo.get(i) instanceof Alumno) {
                System.out.println("todo" + i + "es de tip alumno");
            }
            if (todo.get(i) instanceof Encuesta) {
                System.out.println("todo" + i + "es de tip alumno");
            }
            if (todo.get(i) instanceof Salud) {
                System.out.println("todo" + i + "es de tip alumno");
            }
            if (todo.get(i) instanceof Progenitor) {
                System.out.println("todo" + i + "es de tip alumno");
            }
            if (todo.get(i) instanceof String[]) {
                System.out.println("todo" + i + "es de tip alumno");

            }

        }
    }

    public void cargarDatos() {
        //esta variable se utiliza para saber el estado de un alumno  true alta, false baja
        boolean isClase = false;
        boolean estado = false;
        for (int i = 0; i < todo.size(); i++) {

            if (todo.get(i) instanceof Alumno) {
                cargarAlumno(i);

            }
            if (todo.get(i) instanceof String[]) {
                System.out.println("entre a instacia de String curso Aqui!!!");

                if (!isClase) {
                    cargarCursos(i);
                } else {
                    cargarClase(i);
                }
                isClase = true;
            }
            if (todo.get(i) instanceof Salud) {
                CargarSalud(i);
            }
            if (todo.get(i) instanceof Progenitor) {
                cargarProgenitor(i);
            }
            if (todo.get(i) instanceof Encuesta) {
                System.out.println("existe una encuesta");
                cargarEncuesta(i);
            }
        }
    }

    public void cargarAlumno(int posicion) {
        Alumno alumno = (Alumno) todo.get(posicion);
        this.matricula = alumno.getMatricula();
        ArrayList alAlumnos = new ArrayList();
        String matricula = alumno.getMatricula() + "";

        alAlumnos.add(matricula);
        alAlumnos.add(alumno.getNombre());
        alAlumnos.add(alumno.getApellidoPaterno());
        alAlumnos.add(alumno.getApellidoMaterno());
        alAlumnos.add(alumno.getSexo());

        String año = alumno.getAñoNac() + "";
        String dia = alumno.getDiaNac() + "";
        alAlumnos.add(año);
        alAlumnos.add(alumno.getMesNac());
        alAlumnos.add(dia);
        alAlumnos.add(alumno.getEstadoNac());
        alAlumnos.add(alumno.getCiudadNac());
        alAlumnos.add(alumno.getCalle());
        alAlumnos.add(alumno.getColonia());
        String numero = alumno.getNumero() + "";
        alAlumnos.add(numero);
        alAlumnos.add(alumno.getProfesion());
        alAlumnos.add(alumno.getEmail());
        alAlumnos.add(alumno.getFacebook());
        alAlumnos.add(alumno.getTelefonoCasa());
        alAlumnos.add(alumno.getTelefonoCelular());
        interfazConsulta.escribirAlumno(alAlumnos);
        if (alumno.getEscuela() != null) {
            ArrayList alEscolaridad = new ArrayList();
            alEscolaridad.add((String) alumno.getEscuela());
            alEscolaridad.add((String) alumno.getGrado());
            interfazConsulta.escribirEscolaridad(alEscolaridad);
        }
    }

    public void CargarSalud(int posicion) {
        Salud salud = (Salud) todo.get(posicion);
        this.idSalud = salud.getId();
        ArrayList alSalud = new ArrayList();
        if (salud != null) {
            alSalud.add(salud.getProblema());
            alSalud.add(salud.getControlada());
            interfazConsulta.escribirSalud(alSalud);
        } else {
            System.out.println("no tiene salud");
        }

    }

    public void cargarCursos(int posicion) {
        System.out.println("entre al metdo cargar cursos");
        cursos = (String[]) todo.get(posicion);
        if (cursos != null) {
            interfazConsulta.escribirCursos(cursos);
        }
        for (Object curso : cursos) {
            System.out.println(curso);
        }
    }

    public void cargarClase(int posicion) {
        String[] clases = (String[]) todo.get(posicion);
        if (clases != null) {
            interfazConsulta.escribirClases(clases);
        }
        for (String clase : clases) {
            System.out.println("clase");
        }
    }

    public void cargarProgenitor(int posicion) {
        ArrayList alProgenitor = new ArrayList();
        Progenitor progenitor = (Progenitor) todo.get(posicion);
        if (progenitor != null) {
            alProgenitor.add(progenitor.getNombre());
            alProgenitor.add(progenitor.getApellidoPaterno());
            alProgenitor.add(progenitor.getApellidoMaterno());
            String año = progenitor.getAñoNac() + "";
            alProgenitor.add(año);
            alProgenitor.add(progenitor.getMesNac());
            String dia = progenitor.getDiaNac() + "";
            alProgenitor.add(dia);
            alProgenitor.add(progenitor.getProfesion());
            alProgenitor.add(progenitor.getTelefonoCelular());
            alProgenitor.add(progenitor.getPersonaConfianza());
        }
        if (progenitor.getTipo().equals("PADRE")) {
            interfazConsulta.escribirPadre(alProgenitor);
            idPadre = progenitor.getId();
        } else {
            idMadre = progenitor.getId();
            interfazConsulta.escribirMadre(alProgenitor);
        }
    }

    public void cargarEncuesta(int posicion) {
        Encuesta encuesta = (Encuesta) todo.get(posicion);
        ArrayList alEncuesta = new ArrayList();
        if (encuesta != null) {
            alEncuesta.add(encuesta.getMedioPublicidad());
            alEncuesta.add(encuesta.getDanzaSugerida());
            alEncuesta.add(encuesta.getLugarDanza());
            alEncuesta.add(encuesta.getSugerencia());
            interfazConsulta.escribirEncuesta(alEncuesta);
        } else {
            System.out.println("no tiene encuesta");
        }
    }

    public void actualizaComboBoxCurso(JComboBox cbCurso) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (int x = 0; x < cursos.length; x++) {
            modelo.addElement(cursos[x]);
        }
        cbCurso.setModel(modelo);
    }

    public void actualizaComboBoxClase(JComboBox cbClase, String curso) {
        try {
            MAlumno mantenimiento = new MAlumno();
            String[][] clases = mantenimiento.obtenerClases(curso);
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
            cbClase.setModel(modelo);
        } catch (Exception ex) {
            System.out.println("aaa eror");
        }
    }

    public void InicializaTabla(String[] clases) {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }

        };
        modelo.addColumn("Curso");
        modelo.addColumn("Dia");
        modelo.addColumn("Hora");
        modelo.addColumn("Salon");
        for (int i = 0; i < clases.length; i++) {
            String[] datos = clases[i].split("@");
            modelo.addRow(datos);
        }
        interfazConsulta.enviarTabla(modelo);
    }

    public void validaPanelDatosAlumno(ArrayList alAlumnoDatos, ArrayList alAlumnoNombre,
            ArrayList alAlumnoTipo, ArrayList alSaludDatos, ArrayList alSaludNombre,
            ArrayList alSaludTipo, boolean tieneProblema) {
        try {
            validacion.validaDato(alAlumnoDatos, alAlumnoNombre, alAlumnoTipo, "Alumno");
            alumno = new Alumno(alAlumnoDatos);
            if (tieneProblema) {
                validacion.validaDato(alSaludDatos, alSaludNombre, alSaludTipo, "Alumno");
                salud = new Salud(alSaludDatos);
            }
        } catch (Exception e) {
            interfazModificacion.seleccionaPanelDatosAlumno();
        }
    }

    public void validaPaneDatoslEncuesta(ArrayList alDatosEncuesta, ArrayList alNombreEnceusta, ArrayList alTipoEncuesta) {
        try {
            validacion.validaDato(alDatosEncuesta, alNombreEnceusta, alTipoEncuesta, "Encuesta");
            encuesta = new Encuesta(alDatosEncuesta);
        } catch (Exception e) {
            interfazModificacion.seleccionaPanelDatosEnceusta();
        }
    }

    public void validaPanelDatosProgenitores(ArrayList alDatosMadre, ArrayList alDatosPadre,
            ArrayList alNombreProgenitor, ArrayList alTipoProgenitor, ArrayList alDatosEscolaridad,
            ArrayList alNombreEscolaridad, ArrayList alTipoEscolaridad) {
        try {
            validacion.validaDato(alDatosMadre, alNombreProgenitor, alTipoProgenitor, "Madre");
            validacion.validaDato(alDatosPadre, alNombreProgenitor, alTipoProgenitor, "Padre");
            validacion.validaDato(alDatosEscolaridad, alNombreEscolaridad, alTipoEscolaridad, "Escolaridad");
            madre = new Progenitor(alDatosMadre);
            padre = new Progenitor(alDatosPadre);
            alumno.setEscuela(alDatosEscolaridad.get(0).toString());
            alumno.setGrado(alDatosEscolaridad.get(1).toString());
        } catch (Exception ex) {
            interfazModificacion.seleccionaPanelDatosProgenitores();
        }
    }

    public void validaTabla(DefaultTableModel modelo) {
        alcursosConClase.clear();
        if (modelo.getRowCount() > 0) {
            for (int x = 0; x < modelo.getRowCount(); x++) {
                String curso = (String) modelo.getValueAt(x, 0);
                alcursosConClase.add(curso);
                String dia = (String) modelo.getValueAt(x, 1);
                String hora = (String) modelo.getValueAt(x, 2);
                String[] horas = hora.split(" ");
                String horaInicio = horas[0] + " " + horas[1];
                String horaFin = horas[3] + " " + horas[4];
                String salon = (String) modelo.getValueAt(x, 3);
                try {
                    alcursosConClase.add(mantenimiento.obtenerId(dia, salon, horaInicio, horaFin));
                } catch (SQLException ex) {
                    System.out.println("error al obtener el id de la clase control vista");
                }
            }
        }
    }

    public void modificacion() {
        ArrayList alDatos = new ArrayList();
        alumno.setMatricula(matricula);
        encuesta.setId(idEncuesta);

        alDatos.add(alumno);
        alDatos.add(encuesta);
        if (madre != null) {
            madre.setId(idMadre);
            alDatos.add(madre);
        }
        if (padre != null) {
            padre.setId(idPadre);
            alDatos.add(padre);
        }
        if (salud != null) {
            salud.setId(idSalud);
            alDatos.add(salud);
        }
        try {

            mantenimiento.modificacion(alDatos, alcursosConClase);
            interfazCerrar.cerrarVentana();
        } catch (Exception ex) {
            System.out.println("Erroo------=-=-=-=-=-==--==-=--==-=-===---=-=");
        }
    }

    /***
     * Este metodo agrega un renglon a la tabla de la vista donde se valida si la clase
     * esta en un horario libre que no existan traslapes y que no se repitan dos clases
     * iguales.
     * @param curso
     * @param clase
     * @param modelo 
     */
    public void AgregarRenglon(String curso, String clase, DefaultTableModel modelo) {
        if (!renglonExistente(curso, clase, modelo)) {
            if (horaLibre(curso, clase, modelo)) {
                String[] registro = new String[4];
                String[] datosClase = clase.split(" ");
                registro[0] = curso;
                registro[1] = datosClase[0];
                registro[2] = datosClase[2] + " " + datosClase[3] + " a " + datosClase[5] + " " + datosClase[6];
                registro[3] = datosClase[8];
                modelo.addRow(registro);
            }else{
                  JOptionPane.showMessageDialog(null, "No se puede agregar  esta clase,"
                    + "porque se traslapa con el horario de otra clase ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede agregar  esta clase,"
                    + "porque el alumno ya esta inscrito en ella");
        }
    }

    /**
     * *
     * Este metodo se utiliza para eliminar renglones de la tabla cursos del
     * panel seleccion de materias donde la fila es un entero que contiene la
     * posicion de la seleccionada actualmente y modelo es el defaultTableModel
     * de la tabla cursos.
     *
     * @param fila
     * @param modelo
     */
    public void eliminarRenglon(int fila, DefaultTableModel modelo) {
        if (isRenglonValido(fila, modelo)) {
            modelo.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(null, "No se puede eliminar esta clase,"
                    + "porque debe de tener por lo menos una clase de cada curso");
        }

    }

    /**
     * *
     * Este metodo valida que existan mas Clases del mismo curso para poder
     * eliminar este renglon
     */
    public boolean isRenglonValido(int fila, DefaultTableModel modelo) {
        boolean bandera = false;
        z:
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (i != fila) {
                if (modelo.getValueAt(i, 0).equals(modelo.getValueAt(fila, 0))) {
                    bandera = true;
                    break z;
                }
            }
        }
        return bandera;
    }

    /***
     * Valida que no exista ese renglon en la modelo y si el renglon no existe manda un false
     * y si existe manda un true
     * @param curso
     * @param clase
     * @param modelo
     * @return 
     */
    public boolean renglonExistente(String curso, String clase, DefaultTableModel modelo) {
        boolean bandera = false;
        String cursoTabla;
        String dia;
        String hora;
        String salon;
        String[] datosClase = clase.split(" ");
        String horaCompleta = "";
        System.out.println("lo que contiene la hora  coompleta es");
        for (int i = 2; i < 7; i++) {
            horaCompleta += datosClase[i] + " ";

        }
        z:
        for (int x = 0; x < modelo.getRowCount(); x++) {
            cursoTabla = (String) modelo.getValueAt(x, 0);
            dia = (String) modelo.getValueAt(x, 1);
            hora = (String) modelo.getValueAt(x, 2);
            salon = (String) modelo.getValueAt(x, 3);
            System.out.println(horaCompleta);
            hora += " ";
            System.out.println("su tamaño" + hora.length() + "a" + horaCompleta.length());
            System.out.println("son iguales?" + hora.equalsIgnoreCase(horaCompleta));
            System.out.println("diatabla = " + dia + " dia enviado=" + datosClase[0]);
            System.out.println("son iguales??" + dia.equalsIgnoreCase(datosClase[0]));
            System.out.println("salon enviado = " + salon + " salon tala = " + datosClase[8]);
            System.out.println("son iguales???" + salon.equalsIgnoreCase(datosClase[8]));
            System.out.println("curso enviado = " + curso + "curso enviado" + "curso tabala = " + cursoTabla);
            System.out.println("son iguales????" + curso.equals(cursoTabla));
            if (curso.equals(cursoTabla) && dia.equals(datosClase[0]) && hora.equals(horaCompleta)
                    && salon.equals(datosClase[8])) {
                bandera = true;
                break z;
            }
        }
        return bandera;
    }

    /***
     * Este metodo valida el traslape de clases en el modelo si existen dos clases
     * con el mismo dia y hora (inicio y fin ) 
     * @param curso
     * @param clase
     * @param modelo
     * @return 
     */
    public boolean horaLibre(String curso, String clase, DefaultTableModel modelo) {
        boolean bandera = true;
        String cursoTabla;
        String dia;
        String hora;
        String salon;
        String[] datosClase = clase.split(" ");
        String horaCompleta = "";
        System.out.println("lo que contiene la hora  coompleta es");
        for (int i = 2; i < 7; i++) {
            horaCompleta += datosClase[i] + " ";

        }
        z:
        for (int x = 0; x < modelo.getRowCount(); x++) {
            cursoTabla = (String) modelo.getValueAt(x, 0);
            dia = (String) modelo.getValueAt(x, 1);
            hora = (String) modelo.getValueAt(x, 2);
            salon = (String) modelo.getValueAt(x, 3);
            System.out.println(horaCompleta);
            hora += " ";
            System.out.println("su tamaño" + hora.length() + "a" + horaCompleta.length());
            System.out.println("son iguales?" + hora.equalsIgnoreCase(horaCompleta));
            System.out.println("diatabla = " + dia + " dia enviado=" + datosClase[0]);
            System.out.println("son iguales??" + dia.equalsIgnoreCase(datosClase[0]));
            System.out.println("salon enviado = " + salon + " salon tala = " + datosClase[8]);
            System.out.println("son iguales???" + salon.equalsIgnoreCase(datosClase[8]));
            System.out.println("curso enviado = " + curso + "curso enviado" + "curso tabala = " + cursoTabla);
            System.out.println("son iguales????" + curso.equals(cursoTabla));
            if (dia.equals(datosClase[0]) && hora.equals(horaCompleta)) {
                bandera = false;
                break z;
            }
        }
        return bandera;

    }
}
