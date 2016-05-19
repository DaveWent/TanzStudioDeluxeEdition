/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.ICierraVentana;
import Interfaces.IRecibeDatosUsuario;
import Modelo.CicloEscolar;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cristian
 */
public class CVistaCicloEscolar {

    Interfaces.ICicloEscolar interfaz;
    ICierraVentana interfaceCerrar;

    public CVistaCicloEscolar(Interfaces.ICicloEscolar interfaz, ICierraVentana interfaceCerrar) {
        this.interfaz = interfaz;
        this.interfaceCerrar = interfaceCerrar;
    }

    public void validarFechaInicioCrear(Date fechaInicio, Date fechaFin) 
            throws ParseException {
        Date hoy = new Date();
        String fecha1 = new SimpleDateFormat("dd/MMM/yyyy").format(hoy);
        String fecha2 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaInicio);

        //se hacen estas conversiones para que no exista diferencias de milisegundos
        //con la fecha actual y no cause conficto en el metodo after
        hoy = new SimpleDateFormat("dd/MMM/yyyy").parse(fecha1);
        if (hoy.before(fechaInicio) || fecha1.equalsIgnoreCase(fecha2)) {//si la fecha de hoy no es posterior a la fecha de inicio sera valida
            interfaz.enviarFechaInicio(fechaInicio, true);
        } else if (hoy.after(fechaInicio)) {
            String fecha3 = new SimpleDateFormat("dd/MMM/yyyy").format(hoy);
            JOptionPane.showMessageDialog(null, "la fecha[ " + fecha2 + " ]" 
                    + "no es valida ya que es anterior al dia de hoy");
            interfaz.enviarFechaInicio(hoy, false);
        }
        if (fechaFin != null) {
            String fecha3 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaFin);
            if (!fechaInicio.after(fechaFin) && !fecha3.equalsIgnoreCase(fecha2)) {//si la fecha de hoy no es posterior a la fecha de inicio sera valida
                System.out.println("fehca valida no null");
                interfaz.enviarFechaInicio(fechaInicio,true);
            }else{
                interfaz.enviarFechaInicio(null, false);
                JOptionPane.showMessageDialog(null, "la fecha Inicio [ " + fecha2 
                        + " ]" + "no debe ser mayor  o igual de Fin ");
            }
        }

    }

    public void validarFechaFin(Date fechaInicio, Date fechaFin) {
        String fecha = new SimpleDateFormat("dd/MMM/yyyy").format(fechaFin);
        String fecha2 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaInicio);
        boolean valido = false;
        if (fechaFin.after(fechaInicio)) {//si la fecha de hoy no es posterior a la fecha de inicio sera valida
            valido = true;
        } else {//en caso contrario sera una fecha invalida
            JOptionPane.showMessageDialog(null, "la fechaFin[ " + fecha + " ]" 
                    + "nos es valida ya que es anterior a la fecha de inicio");
            valido = false;
        }
        if (fecha.equalsIgnoreCase(fecha2)) {
            valido = false;
            JOptionPane.showMessageDialog(null, "la fecha Fin [ " + fecha + " ]" 
                    + "no debe ser la misma que la fecha de inicio");

        }
        interfaz.enviarFechaFin(valido);
    }

    public void crearCiclo(Date fechaIncio, Date fechaFin) throws Exception {
        CCicloEscolar controlCE = new CCicloEscolar();
        String nombreCiclo = controlCE.tipoDeCiclo(fechaIncio);
        controlCE.Alta(fechaIncio, fechaFin, nombreCiclo);
        interfaceCerrar.cerrarVentana();

    }

    public void ConsultarCicloEscolar() {
        try {
            CCicloEscolar controlCE = new CCicloEscolar();
            Modelo.CicloEscolar cicloEscolar = new CicloEscolar();
            cicloEscolar = controlCE.obtenerCicloEscolarActual();
            interfaz.enviarCicloEscolar(cicloEscolar.getFechaInicio(), cicloEscolar.getFechaFin(),
                    cicloEscolar.getNombreCicloEscolar());
            if (cicloEscolar.isIniciado()) {
                interfaz.enviarFechaInicio(cicloEscolar.getFechaInicio(), false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validarFechaInicio(Date fechaInicio, Date fechaFin) {
        try {
            CCicloEscolar controlCE = new CCicloEscolar();
            Modelo.CicloEscolar cicloEscolar = new CicloEscolar();
            cicloEscolar = controlCE.obtenerCicloEscolarActual();
            Date hoy = new Date();
            String fecha1 = new SimpleDateFormat("dd/MMM/yyyy").format(hoy);
            String fecha2 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaInicio);
            String fecha3 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaFin);
            hoy = new SimpleDateFormat("dd/MMM/yyyy").parse(fecha1);
            if (!cicloEscolar.isIniciado()) {
                //se hacen estas conversiones para que no exista diferencias de milisegundos
                //con la fecha actual y no cause conficto en el metodo after

                if (hoy.before(fechaInicio) || fecha1.equalsIgnoreCase(fecha2)) {//si la fecha de hoy no es posterior a la fecha de inicio sera valida
                    interfaz.enviarFechaInicio(fechaInicio, true);
                    System.out.println("entre a fecha valida porsterior");
                } else {//en caso contrario sera una fecha invalida
                    System.out.println("entre a fecha valida porsterior");
                    JOptionPane.showMessageDialog(null, "la fecha[ " + fecha2 + " ]" 
                            + "no es valida ya que es anterior al dia de hoy");
                    interfaz.enviarFechaInicio(cicloEscolar.getFechaInicio(), true);
                }
                if (fechaFin.before(fechaInicio)) {
                    System.out.println("entre a fecha valida porsterior");
                    JOptionPane.showMessageDialog(null, "la Inicio[ " + fecha2 + " ]" 
                            + "nos es valida ya que es posterior a la fecha Fin");
                    interfaz.enviarFechaInicio(cicloEscolar.getFechaInicio(), true);
                } else if (fecha2.equalsIgnoreCase(fecha3)) {
                    JOptionPane.showMessageDialog(null, "la fecha Inicio [ " + fecha2 + " ]" 
                            + "no debe ser la misma que la fecha de Fin");
                    interfaz.enviarFechaInicio(cicloEscolar.getFechaInicio(), true);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException pex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, pex);
        }
    }

    public void validarFechaFinM(Date fechaInicio, Date fechaFin) {
        try {
            CCicloEscolar controlCE = new CCicloEscolar();
            Modelo.CicloEscolar cicloEscolar = new CicloEscolar();
            cicloEscolar = controlCE.obtenerCicloEscolarActual();
            Date hoy = new Date();
            String fecha1 = new SimpleDateFormat("dd/MMM/yyyy").format(hoy);
            String fecha2 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaInicio);
            String fecha3 = new SimpleDateFormat("dd/MMM/yyyy").format(fechaFin);
            hoy = new SimpleDateFormat("dd/MMM/yyyy").parse(fecha1);
            if (!cicloEscolar.isIniciado()) {
                //se hacen estas conversiones para que no exista diferencias de milisegundos
                //con la fecha actual y no cause conficto en el metodo after

                if (fechaFin.before(fechaInicio)) {
                    JOptionPane.showMessageDialog(null, "la Fin[ " + fecha3 + " ]" 
                            + "nos es valida ya que es anteior a la fecha Inicio");
                    interfaz.enviarFechaFin(false);
                } else if (fecha2.equalsIgnoreCase(fecha3)) {
                    JOptionPane.showMessageDialog(null, "la fecha Fin [ " + fecha3 
                            + " ]" + "no debe ser la misma que la fecha de Inicio");
                    interfaz.enviarFechaFin(false);
                    //si la fecha de hoy no es posterior a la fecha de inicio sera valida
                } else if (hoy.before(fechaFin) || fecha1.equalsIgnoreCase(fecha3)) {
                    interfaz.enviarFechaFin(true);
                    System.out.println("entre a fecha valida porsterior fecha fin");
                } else {//en caso contrario sera una fecha invalida
                    System.out.println("entre a fecha No valida porsterior fecha fin");
                    JOptionPane.showMessageDialog(null, "la fecha Fin[ " + fecha3 + " ]" 
                            + "no es valida ya que es anterior al dia de hoy");
                    interfaz.enviarFechaFin(false);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException pex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, pex);
        }
    }

    public void Modifica(Date fechaInicio, Date fechaFin) {
        try {
            CCicloEscolar controlCE = new CCicloEscolar();
            String nombreCiclo = controlCE.tipoDeCiclo(fechaInicio);
            Modelo.CicloEscolar cicloEscolar = new CicloEscolar();
            cicloEscolar = controlCE.obtenerCicloEscolarActual();
            controlCE.modificarCiclo(fechaInicio, fechaFin);
            interfaceCerrar.cerrarVentana();
        } catch (SQLException ex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CVistaCicloEscolar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws ParseException {
        Date hoy = new Date();
        CCicloEscolar controlCE = new CCicloEscolar();
        String nombreCiclo = controlCE.tipoDeCiclo(hoy);
    }
}
