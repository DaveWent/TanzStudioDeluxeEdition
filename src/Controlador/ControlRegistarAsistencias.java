/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.RenglonAlumno;
import static java.lang.Math.abs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author cristian
 */
public class ControlRegistarAsistencias extends ConexionBD {

    /*  private String SQL_AlumnosDeClase = "select P.matricula,P.nombre_A,P.apellidoPaterno_A,P.apellido from clase inner join "
     + "((select distinct alumno.matricula,alumno.nombre_A,apellidoPaterno_A "
     + "from alumno"
     + "inner join inscrito2 "
     + "ON alumno.matricula=inscrito2.matricula) as P)"
     + "ON clase.id_Clase=?;";
     */
    private String SQL_AlumnosDeClase = "select alumno.matricula,nombre_A,apellidoPaterno_A,apellidoMaterno_A FROM alumno \n"
            + "inner join inscrito3 on (inscrito3.Matricula=alumno.matricula) where id_Clase=?";

    /***
     * Este metodo se utiliza para obtener matricula,nombre,apellido paterno, apellido materno
     * @param idclase
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList ConsultarDatosAlumno(int idclase) throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_AlumnosDeClase);
        ps.setInt(1, idclase);
        System.out.println(ps);
        rs = ps.executeQuery();
        while (rs.next()) {
            result.add(getObject(rs));
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    final String SQL_SELECT_FECHA_AS = "select fecha_AS from inscrito2 where id_Clase=? and matricula=?";

    /***
     * este metodo se utilza para obtener todas las asistencias de los alumnos en
     * determinada clase la cual es identificada por su idClase
     * @param idClase
     * @param matricula
     * @return
     * @throws SQLException 
     */
    public String[] asistenciasAlumno(int idClase, int matricula) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs;
        String[] arrayAsistencias;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_SELECT_FECHA_AS);
        ps.setInt(1, idClase);
        ps.setInt(2, matricula);
        rs = ps.executeQuery();
        System.out.println(ps);
        while (rs.next()) {
            result.add(rs.getDate("fecha_AS"));
        }
        arrayAsistencias = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arrayAsistencias[i] = new SimpleDateFormat("dd/MM/yyyy").format(result.get(i));
        }
        return arrayAsistencias;
    }

    /***
     * Metodo que obtienes los datos geerales de un alumno y retorna un obto tipo
     * renglon alumno
     * @param rs
     * @return
     * @throws SQLException 
     */
    public RenglonAlumno getObject(ResultSet rs) throws SQLException {
        RenglonAlumno rgAlumno = new RenglonAlumno();
        String nombre = "";
        rgAlumno.setMatricula(rs.getInt("matricula"));
        nombre += (rs.getString("nombre_A"));
        nombre += " " + (rs.getString("apellidoPaterno_A"));
        nombre += " " + (rs.getString("apellidoMaterno_A"));
        rgAlumno.setNombre(nombre);
        return rgAlumno;
    }

    String sqlObterID = "SELECT id_Clase FROM tanzstudiodb.clase where dia=? "
            + "and salon=? and horaInicio=? and horaFin=?";

    /***
     * 
     * @param dia
     * @param salon
     * @param horaI
     * @param horaF
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public int obtenerIdClase(String dia, String salon, String horaI, String horaF) throws SQLException, Exception {
        int result = -1;
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(sqlObterID);
        ps.setString(1, dia);
        ps.setString(2, salon);
        ps.setString(3, horaI);
        ps.setString(4, horaF);
        ResultSet rs = null;
        rs = ps.executeQuery();
        ps.setString(1, dia);
        ps.setString(2, salon);
        ps.setString(3, horaI);
        ps.setString(4, horaF);
        System.out.println(ps);
        if (rs.next()) {
            result = rs.getInt("id_Clase");
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    final String SQL_FECHAS = "SELECT fechaInicio,fechaFin FROM tanzstudiodb.cicloescolar where fechaFin=(select MAX(fechaFin) from cicloescolar)";
/***
 * Metodo que obtiene las fechas de inicio y fin del ciclo escolar
 * @return
 * @throws SQLException
 * @throws Exception 
 */
    public ArrayList obtenerCicloActual() throws SQLException, Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList result = new ArrayList();
        ps = conexion.prepareStatement(SQL_FECHAS);
        rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("entre");
            result.add(rs.getDate("fechaInicio"));
            result.add(rs.getDate("fechaFin"));
        }
        cerrar(ps);
        cerrar(rs);
        return result;
    }

    /***
     * Este metodo se utiliza para genera todas las fechas entre la
     * fecha de inicio y la fecha de fin  que sean el dia de la semana enviado
     * las fechas de inicio y fin tiene que ser con formato dd/MM/YY
     * @param fechaInicio
     * @param fechaFin
     * @param dia
     * @return
     * @throws ParseException 
     */
    public String[] generarFechas(String fechaInicio, String fechaFin, String dia) throws ParseException {
        String todasFechas = "";
        int diaSemana = obtenerDia(dia);
        long diasInicio = 0;
        long diasFinal = 0;
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        //Se tranforman a date las fechas de inicio y fin con formato dd/MM/YY
        Date dateInic = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicio);
        Date dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
        
       //se transforman las fechas de inicio y fin ha milisegundos 
        diasInicio = dateInic.getTime() / 24 / 60 / 60 / 1000;
        diasFinal = dateFin.getTime() / 24 / 60 / 60 / 1000;
        //se inicializan variables  calendar con los milisegundos de inicio y fin
        cal1.setTime(dateInic);
        cal2.setTime(dateFin);
        //se optienen lso dias de la semana de fecha inicio y fecha fin
        int numInicio = cal1.get(Calendar.DAY_OF_WEEK);
        int numFin = cal2.get(Calendar.DAY_OF_WEEK);
        // se optiene la diferencia entre la fecha de inicio y el dia de la semana
        int diferencia = distancia(numInicio, diaSemana);
        diasInicio += (diferencia + 1);//se suma uno porque en la conversion se pierte un dia
        while (diasFinal >= diasInicio) {
            todasFechas += new SimpleDateFormat("dd/MM/yyyy").format(new Date(diasInicio * 24 * 60 * 60 * 1000));
            diasInicio += 7;
            todasFechas += "@";
            System.out.println("dias final" + diasFinal + "/t dias inicio" + diasInicio);
        }
        return todasFechas.split("@");
    }

    /***
     * metodo que retorna un valor entero dependiendo el dia de la semana que se 
     * se le envie tomando el domingo como 1 y los demsa dias secuenciales a este
     * @param diaSemana
     * @return 
     */
    public int obtenerDia(String diaSemana) {

        if (diaSemana.equalsIgnoreCase("domingo")) {
            return 1;

        } else if (diaSemana.equalsIgnoreCase("lunes")) {
            return 2;
        } else if (diaSemana.equalsIgnoreCase("Martes")) {
            return 3;
        } else if (diaSemana.equalsIgnoreCase("miercoles")) {
            return 4;
        } else if (diaSemana.equalsIgnoreCase("jueves")) {
            return 5;
        } else if (diaSemana.equalsIgnoreCase("viernes")) {
            return 6;
        } else if (diaSemana.equalsIgnoreCase("sabado")) {
            return 7;
        } else {
            return -1;
        }
    }

    /***
     * Metodo utilizado para obtener la diferencia de dias entre un dia y otro
     * @param dia1
     * @param dia2
     * @return 
     */
    public int distancia(int dia1, int dia2) {
        int diferencia = 0;
        while (dia1 != dia2) {
            if (dia1 == 7) {//esto se hace porque del sabado sigue el domingo
                dia1 = 1;
            } else {
                dia1++;
            }
            diferencia++;//se cuentan el total de dias 
        }
        return diferencia;
    }

    /**
     * *
     * Este metodo recibe un array de String con fechas que tienen el formato
     * dd/mm/yyyy y retorna la fecha mas sercana a la actualidad o la mas grande
     *
     * @param arrayfecha
     * @return
     */
    public String obtenerMayor(String[] arrayFecha) throws ParseException {
        String mayor = "1/1/2000";
        for (int i = 0; i < arrayFecha.length; i++) {
            Date dateInic = new SimpleDateFormat("dd/MM/yyyy").parse(mayor);
            Date dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(arrayFecha[i]);
            if (dateInic.after(dateFin)) {
                mayor = arrayFecha[i];
            } else {
                mayor = arrayFecha[i];
            }
        }
        return mayor;
    }

    /***
     * Este metodo evalua cuantas asistencias a tenido el alumno en el ciclo escolar
     * por medio de un vector que es fechas totales que son todas las fechas que
     * hay clases del semestre y fechas alumno que son las asistencias que el tiene 
     * compara si los elementos de la fechaTotal concuerdan con fechasAlumno y  retorna
     * un vector de booleanos con las dimensiones de fechasTotal doden los valores true
     * son fechas que concordaron y las fechas false son fechas que no concordaron.
     * @param fechaTotal
     * @param fechasAlumno
     * @return 
     */
    public boolean[] asistenciasTotales(String[] fechaTotal, String[] fechasAlumno) {
        boolean[] asistencia = new boolean[fechaTotal.length];
        Z:
        for (int i = 0; i < fechaTotal.length; i++) {
            for (int j = 0; j < fechasAlumno.length; j++) {
                if (fechaTotal[i].equalsIgnoreCase(fechasAlumno[j])) {
                    asistencia[i] = true;
                    continue Z;
                }
            }
            asistencia[i] = false;
        }
        return asistencia;
    }
    private static String SQL_INSERT = "insert into inscrito2 (matricula,nombre_CU,fecha_AS,id_clase) values(?,?,?,?)";

    /***
     * Metodo utilizado para ingresar un registro en la tabla CicloEscolar, recibe
     * todos los datos necesarios para ingresar en la base de datos 
     * @param matricula
     * @param curso
     * @param fecha
     * @param idClase
     * @throws SQLException
     * @throws Exception 
     */
    public void alta(int matricula, String curso, Date fecha, int idClase) throws SQLException, Exception {
        System.out.println("emtre a alta");
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_INSERT);
        ps.setInt(1, matricula);
        ps.setString(2, curso);
        ps.setDate(3, (java.sql.Date) fecha);
        ps.setInt(4, idClase);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }
    static String SQL_DELETE = "delete from inscrito2 where matricula=? and fecha_AS=? and id_Clase=?";

    /**
     * Metodo utilizado para eliminar un registro de la tabla CicloEscolar
     * @param matricula
     * @param fecha
     * @param idClase
     * @throws SQLException
     * @throws Exception 
     */
    public void baja(int matricula, Date fecha, int idClase) throws SQLException, Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement(SQL_DELETE);
        ps.setInt(1, matricula);
        ps.setDate(2, (java.sql.Date) fecha);
        ps.setInt(3, idClase);
        System.out.println(ps);
        ps.executeUpdate();
        cerrar(ps);
    }

    public static void main(String[] args) throws ParseException, SQLException, Exception {
        ControlRegistarAsistencias cra = new ControlRegistarAsistencias();
        /*    Date fecha= new SimpleDateFormat("dd/MM/yyyy").parse("19/8/2016");
         System.out.println(fecha);
         java.sql.Date fecha2 = new java.sql.Date(fecha.getTime());
         cra.baja(27, fecha2, 4);
         */
        Date inicioMes=cra.getPrimerDiaDelMes();
        Date UltimoDia= cra.getUltimoDiaDelMes();
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(inicioMes));
         System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(UltimoDia));
        // Date fechaInicio=new SimpleDateFormat("dd/MM/yyyy").format();
        // cra.generarFechas(SQL_INSERT, SQL_INSERT, SQL_INSERT);
    }

    /***
     * Metodo utilizado para obtener el primer dia del mes actual, est metodo
     * ccrea un objeto tipo calendario obteniendo el primer dia del mes y regresa
     * un Date
     * @return 
     */
    public  Date getPrimerDiaDelMes() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    /***
     * Metodo utilizado para obtener el ultimo dia del mes actual, este medtodo
     * crea un objeto tipo calendario obteniendo el ultimo dia del mes y regresa
     * un date 
     * @return 
     */
    public  Date getUltimoDiaDelMes() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }
}
