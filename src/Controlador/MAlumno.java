package Controlador;

import Modelo.Alumno;
import Modelo.Encuesta;
import Modelo.EstadoCuenta;
import Modelo.Progenitor;
import Modelo.Salud;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cristian
 */
public class MAlumno {

    /**
     * *
     * Metodo que ingresa al alumno en la base de datos dependiendo de los datos
     * enviados en alDatos seran en las tablas que se insertaran los datos
     *
     * @param alDatos
     * @param alCursosConClase
     * @throws Exception
     */
    public void inscripcion(ArrayList alDatos, ArrayList alCursosConClase) throws Exception {

        CAlumno controlAlumno = new CAlumno();
        CProgenitor controlProgenitor = new CProgenitor();
        CSalud controlSalud = new CSalud();
        CEncuesta controlEncuesta = new CEncuesta();
        ControlEstadoCuenta controlEstadoCuenta = new ControlEstadoCuenta();
        CCostos controlCostos = new CCostos();
        CCurso controlCurso = new CCurso();
        int matricula = 0;

        for (Object lista1 : alDatos) {
            if (lista1 instanceof Alumno) {
                controlAlumno.alta(((Alumno) lista1), alCursosConClase);
                matricula = controlAlumno.obtenerMatricula();
            }
            if (lista1 instanceof Progenitor) {
                controlProgenitor.alta(((Progenitor) lista1), matricula);
            }
            if (lista1 instanceof Salud) {
                controlSalud.alta(((Salud) lista1), matricula);

            }
            if (lista1 instanceof Encuesta) {
                controlEncuesta.Alta(((Encuesta) lista1), matricula);
            }
        }
        //Crear estado cuenta con importe de inscripcion
        EstadoCuenta estadoCuenta = new EstadoCuenta();
        String inscripcionString = "inscripcion";
        double saldo = 0;
        estadoCuenta.setTipo(inscripcionString);
        estadoCuenta.setSaldo(saldo);
        double precioInscripcion = controlCostos.obtPrecio(inscripcionString);
//        String precio = Double.toString(precioInscripcion);
        estadoCuenta.setCosto_EC( precioInscripcion );
        controlEstadoCuenta.alta(estadoCuenta);
        int idCuenta = controlEstadoCuenta.ultimoEstadoCuenta();
        controlEstadoCuenta.agregarRelacion(matricula, idCuenta);
        System.out.println("matricula" + " | " + idCuenta + " | " + precioInscripcion);
        for (int i = 0; i < alCursosConClase.size(); i++) {
            if (alCursosConClase.get(i) instanceof String) {
                String nombreCurso = alCursosConClase.get(i).toString();
                String tipo = controlCurso.obtenerTipo(nombreCurso);
                if (tipo.equalsIgnoreCase("normal")) {
                    //costo del normal
                } else {
                    //costo del esporadico 
                }
            }

        }
    }

    /**
     * *
     * Metodo que reinscribe a un alumno distinguiendo si este esta dado de alta
     * o baja para cobrar la inscripcion.
     *
     * @param matricula
     * @throws Exception
     */
    public void reinscripcion(int matricula, ArrayList alcursosConClase) throws Exception {
        System.out.println("entre al metodo");
        CAlumno controlAlumno = new CAlumno();
        ControlEstadoCuenta controlEstadoCuenta = new ControlEstadoCuenta();
        CCostos controlCostos = new CCostos();

        Alumno alumno = new Alumno();
        alumno = controlAlumno.leer(matricula);
        System.out.println("leei el alumno");
        EstadoCuenta estadoCuenta = new EstadoCuenta();
        if (alumno.getEstado().equalsIgnoreCase("BAJA")) {//se les cobrara inscripcion

            System.out.println("esto esta dado de baja");
            String inscripcion = "inscripcion";
            double saldo = 0;
            estadoCuenta.setTipo(inscripcion);
            estadoCuenta.setSaldo(saldo);
            double precioInscripcion = controlCostos.obtPrecio(inscripcion);
            String precio = Double.toString(precioInscripcion);
//            estadoCuenta.setCosto_EC(precio);
            estadoCuenta.setCosto_EC( precioInscripcion );

            controlEstadoCuenta.alta(estadoCuenta);
            int idCuenta = controlEstadoCuenta.ultimoEstadoCuenta();
            controlEstadoCuenta.agregarRelacion(matricula, idCuenta);
            alumno.setEstado("ALTA");
            controlAlumno.ModifEstado(alumno);
        }
        for (int i = 0; i < alcursosConClase.size(); i++) {
            System.out.println(alcursosConClase.get(i).toString());
        }
        System.out.println(alcursosConClase.size());
        System.out.println("estoy aqui");
        controlAlumno.alta(matricula, alcursosConClase);
        System.out.println("sali del metodo");

    }

    /**
     * Metodo que modifica los registros,elimina o los agrega.si se envia datos
     * extra como lo pueden ser progenitores y estado de salud se crearan los
     * registros en sus tablas correspondientes pero en el caso de que salud no
     * sea enviado y este registrada anteriormente esta se elimninara.
     *
     * @param alDatos
     * @throws Exception
     */
    public void modificacion(ArrayList alDatos, ArrayList cursosConCLase) throws Exception {
        CAlumno controlAlumno = new CAlumno();
        CSalud controlSalud = new CSalud();
        CProgenitor controlProgenitor = new CProgenitor();
        CEncuesta controlEncuesta = new CEncuesta();
        int matricula = 0;
        int idSalud = 0;
        boolean bandera = false;
        for (int i = 0; i < alDatos.size(); i++) {
            if (alDatos.get(i) instanceof Alumno) {
                Alumno alumno = (Alumno) alDatos.get(i);
                matricula = alumno.getMatricula();
                controlAlumno.modificar(alumno);

            }
            if (alDatos.get(i) instanceof Salud) {
                System.out.println("saluddd");
                bandera = true;
                Salud salud = (Salud) alDatos.get(i);
                //que si tenia problema de salud ya que el id no es 0 por lo tanto existe en la base de datos
                if (salud.getId() != 0) {
                    controlSalud.modificar(salud);
                } else {//no tiene problema por lo tanto no existe en la base de datos
                    controlSalud.alta(salud, matricula);
                }
            }
            if (alDatos.get(i) instanceof Progenitor) {
                Progenitor progenitor = (Progenitor) alDatos.get(i);
                //que si tenia problema de salud ya que el id no es 0 por lo tanto existe en la base de datos
                if (progenitor.getId() != 0) {
                    controlProgenitor.modificar(progenitor);
                } else {
                    controlProgenitor.alta(progenitor, matricula);
                }
            }
            if (alDatos.get(i) instanceof Encuesta) {
                Encuesta encuesta = (Encuesta) alDatos.get(i);
                controlEncuesta.modificar(encuesta);
            }
        }
        //si tiene salud registrada pero no se mando una instancia se dara de baja
        if (controlSalud.SaludDe(matricula) != -1 && !bandera) {
            int id = controlSalud.SaludDe(matricula);
            controlSalud.baja(id);

        }
        if (cursosConCLase != null) {
            controlAlumno.bajaInscrito3(matricula);
            String nomCurso="";
            for (int i = 0; i < cursosConCLase.size(); i++) {
                if (cursosConCLase.get(i) instanceof String) {
                    nomCurso = (String) cursosConCLase.get(i);
                } else {
                    controlAlumno.inscrito3(matricula, (int) cursosConCLase.get(i), nomCurso);
                }
            }
        }

    }

    /**
     * Metodo que regresa la consulta de todos los alumnos existentes ordenados
     * en una matriz
     *
     * @return
     */
    public String[][] constulaGeneralAlumno(String estado) {
        CAlumno controlAlumno = new CAlumno();
        ArrayList arreglo = new ArrayList();
        String[][] matriz = null;
        try {
            if (estado == "Todo") {
                arreglo = controlAlumno.leerTodo();
            } else {
                arreglo = controlAlumno.leer(estado);
            }
            matriz = new String[arreglo.size()][4];
            for (int i = 0; i < arreglo.size(); i++) {
                Alumno a = ((Alumno) arreglo.get(i));
                matriz[i][0] = a.getMatricula() + "";
                matriz[i][1] = a.getNombre();
                matriz[i][2] = a.getApellidoPaterno();
                matriz[i][3] = a.getApellidoMaterno();
            }
        } catch (Exception ex) {
            Logger.getLogger(MAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriz;
    }

    /**
     * Este metodo regresa a los alumnos dependie el patron que se busque una
     * matriz con los alumnos ordenados
     *
     * @param tipo
     * @param cadena
     * @return
     */
    public String[][] filtrarNombre(String tipo, String cadena) {
        CAlumno controlAlumno = new CAlumno();

        ArrayList arreglo = new ArrayList();
        String[][] matriz = null;
        try {
            if (tipo == "Todo") {
                arreglo = controlAlumno.ConsultaConcidencia(cadena);
            } else {
                arreglo = controlAlumno.ConsultaEstadoConcidencia(tipo, cadena);
            }
            matriz = new String[arreglo.size()][4];
            for (int i = 0; i < arreglo.size(); i++) {
                Alumno a = ((Alumno) arreglo.get(i));
                matriz[i][0] = a.getMatricula() + "";
                matriz[i][1] = a.getNombre();
                matriz[i][2] = a.getApellidoPaterno();
                matriz[i][3] = a.getApellidoMaterno();
            }
        } catch (Exception ex) {
            Logger.getLogger(MAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriz;
    }

    public String[][] filtrarMatricula(String tipo, String cadena) {
        CAlumno controlAlumno = new CAlumno();
        Alumno alumno = new Alumno();
        ArrayList arreglo = new ArrayList();
        String[][] matriz = new String[1][4];
        try {
            alumno = controlAlumno.consultaConcidencia(Integer.parseInt(cadena), tipo);
            matriz[0][0] = alumno.getMatricula() + "";
            matriz[0][1] = alumno.getNombre();
            matriz[0][2] = alumno.getApellidoPaterno();
            matriz[0][3] = alumno.getApellidoMaterno();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR en matricula", null, 0, null);
        }
        return matriz;
    }

    public String[][] buscarMatricula(int matricula) {
        String[][] matriz = new String[1][4];
        try {
            CAlumno alumnoDao = new CAlumno();
            Alumno alumno = alumnoDao.leer(matricula);
            if( alumno != null ) {
                matriz[0][0] = alumno.getMatricula() + "";
                matriz[0][1] = alumno.getNombre();
                matriz[0][2] = alumno.getApellidoPaterno();
                matriz[0][3] = alumno.getApellidoMaterno();
            } else{
                matriz= null;
            }
            

        } catch (Exception ex) {
            Logger.getLogger(MAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriz;

    }

    /**
     * metodo utilizado para consultar todos los datos de los que se dispone del
     * alumno para la consulta espesifica Alumno,progenitores,estado de salud y
     * encuesta
     *
     * @param matricula
     * @return
     */
    public ArrayList consultaEspesifica(int matricula) throws Exception {
        ArrayList todo = new ArrayList();

        boolean enfermo = false;
        boolean menor = false;

        int edad = 0;
        int id_sa = 0;

        CAlumno controlAlumno = new CAlumno();
        CClase controlClase = new CClase();
        CSalud controlSalud = new CSalud();
        CProgenitor controlProgenitor = new CProgenitor();
        CEncuesta controlEncuesta = new CEncuesta();

        Alumno alumno = new Alumno();
        Progenitor padre = new Progenitor();
        Progenitor madre = new Progenitor();
        Salud salud = new Salud();
        Encuesta encuesta = new Encuesta();

        alumno = controlAlumno.leer(matricula);
        todo.add(alumno);

        String[] cursos = controlAlumno.leerCursoInscrito(matricula);
        todo.add(cursos);

        String[] clases = controlClase.ClasesDeAlumno(matricula);
        todo.add(clases);

        id_sa = (controlSalud.SaludDe(matricula));
        if (id_sa != -1) {
            enfermo = true;
            salud = controlSalud.leer(id_sa);
            todo.add(salud);
        }

        try {
            int[] idPro = controlProgenitor.padresDe(matricula);

            padre = controlProgenitor.leer(idPro[0]);
            madre = controlProgenitor.leer(idPro[1]);
            todo.add(madre);
            todo.add(padre);
        } catch (Exception ex) {

        }

        encuesta = controlEncuesta.leer(controlEncuesta.encuestaDe(matricula));
        todo.add(encuesta);
        todo.add(tipo(enfermo, menor));

        return todo;
    }

    /**
     * *
     * metodo utilizao para identificar que tipo de consulta de alumno se
     * aplicara si se retorna 1 el alumno es mayor y no esta enfermo, si se
     * retorna 2 el alumno es mayor de edad y no esta enfermo, si se retorna 3
     * el alumno es menor y no esta enfermo si se retorna 4 el alumno es menor y
     * esta enfermo
     *
     * @param enfermo
     * @param menor
     * @return
     */
    public int tipo(boolean enfermo, boolean menor) {
        int tipo = 0;
        if (enfermo && menor) {
            tipo = 4;
        } else if (!enfermo && menor) {
            tipo = 3;
        } else if (enfermo && !menor) {
            tipo = 2;
        } else if (!enfermo && !menor) {
            tipo = 1;
        }
        return tipo;
    }

    /**
     * Este metodo regresa la matricula mayor que existe
     *
     * @return
     */
    public int matriculaAlta() {
        int matricula = 0;
        CAlumno alumnoDao = new CAlumno();
        try {
            matricula = alumnoDao.obtenerMatricula() + 1;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos",
                    null, JOptionPane.ERROR_MESSAGE, null);
        }
        return matricula;
    }

    /**
     * *
     * Este metodo modifica el estado de un alumno en especifico
     *
     * @param estado
     * @param matricula
     */
    public void modifEstado(String estado, int matricula) {
        try {
            CAlumno alumnoDao = new CAlumno();
            Alumno alumno = new Alumno();
            alumno.setMatricula(matricula);
            alumno.setEstado(estado);
            alumnoDao.ModifEstado(alumno);
        } catch (Exception ex) {
            Logger.getLogger(MAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> obtenerCursos() throws Exception {
        CCurso controlCurso = new CCurso();
        ArrayList<String> cursos = controlCurso.busquedaDeCursos();
        return cursos;
    }

    public String[][] obtenerClases(String nombre) throws Exception {
        CCurso controlCurso = new CCurso();
        String[][] clases = controlCurso.busquedaDeClases(nombre);
        return clases;
    }

    public int obtenerId(String dia, String salon, String horaInicio, String horaFin) throws SQLException {

        CCurso controlCurso = new CCurso();
        int salonAsignado = Integer.parseInt(salon);
        String idClase = controlCurso.busquedaId(dia, salonAsignado, horaInicio, horaFin);
        System.out.println(idClase);
        int id = Integer.parseInt(idClase);

        return id;

    }

    /**
     * *
     * metodo que elimina la relacion de un alumno y un curso
     *
     * @param matricula
     * @param nombreCurso
     * @throws Exception
     */
    public void baja(int matricula, String nombreCurso) throws Exception {
        CAlumno controlAlumno = new CAlumno();
        controlAlumno.EliminaIscrito(matricula, nombreCurso);
        ArrayList alCursos = controlAlumno.consultaCursosInscrito(matricula);
        if (alCursos.size() == 0) {
            Alumno alumno = new Alumno();
            alumno.setEstado("BAJA");
            alumno.setMatricula(matricula);
            controlAlumno.ModifEstado(alumno);
        }
    }

    /**
     * *
     * Metodo que busca los nombre de el cursos a los que esta inscrito el
     * alumno
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public ArrayList consultaCursosAlumno(int matricula) throws Exception {
        CAlumno controlAlumno = new CAlumno();
        return controlAlumno.consultaCursosInscrito(matricula);
    }

    /**
     * *
     * Metodo que retorna el nombre completo de un alumno dependiendo la
     * metricula que se envie
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public String obtenerNombre(int matricula) throws Exception {
        CAlumno controlAlumno = new CAlumno();
        return controlAlumno.obtenerNombre(matricula);
    }

    /**
     * *
     * obtiene los cursos a los que no esta inscrito el alumno
     *
     * @param matricula
     * @return
     * @throws Exception
     */
    public ArrayList<String> obtenerCursosNoInscrito(int matricula) throws Exception {
        CAlumno controlAlumno = new CAlumno();
        ArrayList<String> cursos = controlAlumno.consultaNoIscrito(matricula);
        return cursos;
    }
}
