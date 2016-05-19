/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EstadoCuenta;
import Modelo.Pagos;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class MantenimientodePagos {
    ControlPagos controlPago = new ControlPagos();
    
    public ArrayList<String> busquedaPorMatricula(int matricula) throws SQLException{
        ArrayList<String> alumno = controlPago.consultaAlumno(matricula);
        return alumno;
    }
      public ArrayList<String> busquedaConNombreCompleto(String nombre_A, String apellidoPaterno_A, String apellidoMaterno_A)
              throws SQLException{
      ArrayList<String> nombreAlumno = controlPago.consultaNombreAlumno(nombre_A,apellidoPaterno_A,apellidoMaterno_A);
      return nombreAlumno;
     }
    
    public ArrayList<String> busquedaPorId(int matricula, String tipo) throws Exception{
        ArrayList<String> ids = controlPago.consultarId(matricula);
        ArrayList<String> estadoCuenta = new ArrayList<String>();
        for( String id : ids) {
           ArrayList<String> auxiliar = controlPago.busquedaEstadoDeCuenta(Integer.parseInt(id), tipo);
           if( auxiliar.size() != 0 ) {
               estadoCuenta = controlPago.busquedaEstadoDeCuenta(Integer.parseInt(id), tipo);
               break;
           }
        }
        return estadoCuenta;
    } 
    
    public ArrayList<String> busquedaPorIdMensualidad( int matricula ) throws Exception {
        ArrayList<String> ids = controlPago.consultarId( matricula );
        double total = 0;
        double pagado = 0;
        double multa = 0;
        int idCuenta = 0;
        for( String id : ids) {
            EstadoCuenta auxiliar = controlPago.busquedaEstadoDeCuentaMensualidad(Integer.parseInt(id));
            if( auxiliar != null) {
                idCuenta = auxiliar.getIdCuenta();
                total = total + auxiliar.getCosto_EC();
                pagado = pagado + auxiliar.getSaldo();
                multa = multa + auxiliar.getMulta();
           }
        }
        double adeudo = total - pagado;
        ArrayList<String> Costos = new ArrayList<String>();
        System.out.println(total + " " + pagado);
        Costos.add(Integer.toString(idCuenta));
        Costos.add( Integer.toString( (int ) pagado ) );
        Costos.add(Integer.toString( (int ) total ));
        Costos.add(Integer.toString( (int ) multa ));
        return Costos;
    }
    
    public ArrayList<String> busquedaPorIdNombreMensualidad( String nombre_A, String apellidoPaterno_A, String apellidoMaterno_A ) throws Exception {
        ArrayList<String> ids = controlPago.consultarIdPorNombre(nombre_A, apellidoPaterno_A, apellidoMaterno_A);
        double total = 0;
        double pagado = 0;
        double multa = 0;
        int idCuenta = 0;
        for( String id : ids) {
            EstadoCuenta auxiliar = controlPago.busquedaEstadoDeCuentaMensualidad(Integer.parseInt(id));
            if( auxiliar != null) {
                idCuenta = auxiliar.getIdCuenta();
                total = total + auxiliar.getCosto_EC();
                pagado = pagado + auxiliar.getSaldo();
                multa = multa + auxiliar.getMulta();
           }
        }
        double adeudo = total - pagado;
        ArrayList<String> Costos = new ArrayList<String>();
        System.out.println(total + " " + pagado);
        Costos.add(Integer.toString(idCuenta));
        Costos.add( Integer.toString( (int ) pagado ) );
        Costos.add(Integer.toString( (int ) total ));
        Costos.add(Integer.toString( (int ) multa ));
        return Costos;
    }
    
    public void registrarPago(int nuevoSaldo,String Tipo, Double abono, int matricula, 
            int id_Cuenta) throws Exception{
        Pagos pago = new Pagos();
        pago.setTipo(Tipo);
        pago.setAbono(abono);
        if(Tipo.equalsIgnoreCase("Inscripcion") ){
            controlPago.alta( pago );
            int nuevoId = controlPago.obtenerId();
            controlPago.altaHacer2(matricula, nuevoId);
            controlPago.altaHacer3(nuevoId, id_Cuenta);
            controlPago.actualizarSaldo(Integer.toString(nuevoSaldo), id_Cuenta);
        }else{
            if( Tipo.equalsIgnoreCase( "mensualidad" ) ) {
                    ArrayList<String> ids = controlPago.consultarId( matricula );
                //ArrayList<EstadoCuenta> cuenta = new ArrayList<EstadoCuenta>();
                double abonoTemporal = abono;
                for( String id : ids) {
                    EstadoCuenta auxiliar = controlPago.busquedaEstadoDeCuentaMensualidad(Integer.parseInt(id));
                    if( auxiliar != null) {
                        double adeudo = auxiliar.getCosto_EC() - auxiliar.getSaldo();
                        if( adeudo != 0 ) {
                            if( abonoTemporal == adeudo || abonoTemporal < adeudo ) {
                                Pagos pagoMensualidad = new Pagos();
                                pago.setTipo( auxiliar.getTipo() );
                                pago.setAbono( abonoTemporal );

                                controlPago.alta( pago );
                                int nuevoId = controlPago.obtenerId();
                                controlPago.altaHacer2(matricula, nuevoId);
                                controlPago.altaHacer3(nuevoId, Integer.parseInt(id));
                                int saldo = (int) auxiliar.getSaldo() + (int) abonoTemporal ;
                                abonoTemporal = 0;
                                controlPago.actualizarSaldo(Integer.toString(saldo), Integer.parseInt(id));
                                break;
                            } else {
                                if( abonoTemporal > adeudo ) {
                                    double nuevoAbono = adeudo;
                                    pago.setTipo( auxiliar.getTipo() );
                                    pago.setAbono( nuevoAbono );
                                    abonoTemporal = abonoTemporal - nuevoAbono;
                                    controlPago.alta( pago );
                                    int nuevoId = controlPago.obtenerId();
                                    controlPago.altaHacer2(matricula, nuevoId);
                                    controlPago.altaHacer3(nuevoId, Integer.parseInt(id));
                                    int saldo = (int) auxiliar.getSaldo() + (int) nuevoAbono ;
                                    controlPago.actualizarSaldo(Integer.toString(saldo), Integer.parseInt(id));
                                }
                            }
                        } 
                    }
                }
            } else {
                Pagos nuevoPago = new Pagos();
                nuevoPago.setTipo(Tipo);
                nuevoPago.setAbono(abono);
                controlPago.alta( nuevoPago );
                int nuevoId = controlPago.obtenerId();
                controlPago.altaHacer2(matricula, nuevoId);
                controlPago.altaHacer3(nuevoId, id_Cuenta);
                controlPago.actualizarSaldo(Integer.toString(nuevoSaldo), id_Cuenta);
            }
        }
    }
    
    public String[][] consultaPagos() throws SQLException {
        String[][] pagos = controlPago.consultaDePagos();
        return pagos;
    }
    public String[][] consultaAdeudo() throws SQLException {
        String[][] adeudo = controlPago.consultaDeAdeudos();
        
        return adeudo;
    }
    
    public String[][] coincidencias( String dato, String proveniente ) throws SQLException {
        String[][] datos;
        if( proveniente.equalsIgnoreCase("matricula") ) {
            datos = controlPago.consultaDePagosPorMatricula(dato);
        } else {
            datos = controlPago.consultaDePagosPorNombre(dato);
        }
        return datos;
    }
        public String[][] coincidenciasAdeudos( String dato, String proveniente ) throws SQLException {
        String[][] datos;
        if( proveniente.equalsIgnoreCase("matricula") ) {
            datos = controlPago.consultaDeAdeudoPorMatricula(dato);
        } else {
            datos = controlPago.consultaDeAdeudoPorNombre(dato);
        }
        return datos;
    }
        
    public ArrayList<String> obtenerCursosEsporadicosPorMatricula( int matricula ) 
            throws Exception {
        ArrayList<String> cursos = controlPago.consultaEsporadicosPorMatricula(matricula);
        return cursos;
    }
    
    public ArrayList<String> obtenerCostosCursos( int matricula, String curso ) 
            throws Exception {
        ArrayList<String> ids = controlPago.consultarId( matricula );
        ArrayList<String> datos = new ArrayList<String>();
        for( String id : ids ) {
            EstadoCuenta auxiliar = controlPago.busquedaEspecificaEstadoCuenta( id );
            if( auxiliar != null ) {
                if( auxiliar.getTipo().equalsIgnoreCase( curso ) ) {
                    datos.add( Integer.toString( auxiliar.getIdCuenta() ) );
                    datos.add( Integer.toString( ( int ) auxiliar.getSaldo() ) );
                    datos.add( Integer.toString( ( int ) auxiliar.getCosto_EC() ) );
                    datos.add( Integer.toString( ( int ) auxiliar.getMulta() ) );
                }
            }
        }
        return datos;
    }   

    public String obtenerMatriculaPorNombre( String nombre, String apellidoPaterno, 
            String apellidoMaterno ) throws Exception {
        String matricula = controlPago.BusquedaEdadoDeCuentaNombre(nombre, apellidoPaterno, apellidoMaterno);
        return matricula;
    }
}