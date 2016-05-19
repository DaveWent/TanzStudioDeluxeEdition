/**
 * ControlPagos.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Controlador;

import Modelo.EstadoCuenta;
import Modelo.Pagos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase donde se crean y utilizan los Querys para la base de datos
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class ControlPagos extends ConexionBD {
    
    private static final String modificacion = "UPDATE costos SET costos = ? "
            + "WHERE id_Costos = ?";
    private static final String busqueda = "SELECT matricula, nombre_A, "
            + "apellidoPaterno_A, apellidoMaterno_A from alumno where matricula = ?";
    private static final String busquedaPorMatricula = "SELECT id_Cuenta FROM "
            + "hace1 where matricula = ?";
    private static final String busquedaNombreCompleto = "SELECT matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A"
            + " from alumno where nombre_A = ? and apellidoPaterno_A = ? and apellidoMaterno_A = ?";
    private static final String busquedaDeEstadoDeCuenta = "SELECT id_Cuenta, "
            + "saldo, Costo_EC, multa FROM estadocuenta where id_Cuenta = ? AND tipo_EC=?";
    private static final String busquedaParaMensualidad = "SELECT id_Cuenta, saldo, Costo_EC,tipo_EC,"
            + " multa FROM estadocuenta where id_Cuenta = ? AND tipo_EC like '%mensualidad%'";
    private static final String registrarPago = "insert into pagos (tipo_Pa,"
            + "fecha_Pa,abono,hora_Pa) values (?,curdate(),?,curtime())";
    private static final String insertanHace2 = "insert into hace2(matricula, "
            + "id_Pago) values (?,?)";
    private static final String insertanHace3 = "insert into hace3(id_Cuenta, "
            + "id_Pago) values (?,?)";
    private static final String obtenerId = "SELECT max(id_Pago) FROM pagos";
    private static final String actualizaSaldo = "update estadocuenta set saldo = ? where id_Cuenta = ?";
    private static final String consultaDePagos = "SELECT matricula, nombre_A, apellidoPaterno_A, "
            + "apellidoMaterno_A,tipo_Pa, abono, fecha_Pa FROM pagos INNER JOIN\n" +
"( ( SELECT alumno.matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A,hace2.id_Pago FROM "
            + "hace2 INNER JOIN alumno ON hace2.matricula = alumno.matricula) as P)\n" +
"ON P.id_Pago = pagos.id_Pago";
    private static final String consultaDeAdeudos ="SELECT matricula, nombre_A, apellidoPaterno_A,"
            + " apellidoMaterno_A,tipo_EC, saldo,Costo_EC FROM estadocuenta INNER JOIN\n" +
"( ( SELECT alumno.matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A,hace1.id_Cuenta FROM hace1 "
            + "INNER JOIN alumno ON hace1.matricula = alumno.matricula) as P)\n" +
"ON P.id_Cuenta = estadocuenta.id_Cuenta";
    private static final String consultaDePagosPorMatricula = "SELECT matricula, nombre_A, "
            + "apellidoPaterno_A, apellidoMaterno_A,tipo_Pa, abono, fecha_Pa FROM pagos INNER JOIN\n" +
"( ( SELECT alumno.matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A,"
            + "hace2.id_Pago FROM hace2 INNER JOIN alumno ON hace2.matricula = alumno.matricula) as P)\n" +
"ON P.id_Pago = pagos.id_Pago AND matricula like ?";
    private static final String consultaDePagosPorNombre = "SELECT matricula, nombre_A, "
            + "apellidoPaterno_A, apellidoMaterno_A,tipo_Pa, abono, fecha_Pa FROM pagos INNER JOIN\n" +
"( ( SELECT alumno.matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A,"
            + "hace2.id_Pago FROM hace2 INNER JOIN alumno ON hace2.matricula = alumno.matricula) as P)\n" +
"ON P.id_Pago = pagos.id_Pago AND nombre_A  like ? ";
    private static final String consultaDeAdeudosPorNombre = "SELECT matricula, nombre_A, "
            + "apellidoPaterno_A, apellidoMaterno_A,tipo_EC, saldo,Costo_EC FROM estadocuenta INNER JOIN\n" +
"( ( SELECT alumno.matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A,hace1.id_Cuenta "
            + "FROM hace1 INNER JOIN alumno ON hace1.matricula = alumno.matricula) as P)\n" +
"ON P.id_Cuenta = estadocuenta.id_Cuenta AND nombre_A like ? ";
    private static final String consultaDeAdeudosPorMatricula = "SELECT matricula, nombre_A, "
            + "apellidoPaterno_A, apellidoMaterno_A,tipo_EC, saldo,Costo_EC FROM estadocuenta INNER JOIN\n" +
"( ( SELECT alumno.matricula, nombre_A, apellidoPaterno_A, apellidoMaterno_A,hace1.id_Cuenta "
            + "FROM hace1 INNER JOIN alumno ON hace1.matricula = alumno.matricula) as P)\n" +
"ON P.id_Cuenta = estadocuenta.id_Cuenta AND matricula like ? ";
    private static final String busquenaPorNombreCompletoMensualidad = "SELECT id_Cuenta FROM hace1 where matricula = "
    + "(select matricula from alumno where nombre_A = ? and apellidoPaterno_A = ? and apellidoMaterno_A = ?)";
    private static final String BusquedaPorMatriculaEsporadico = "SELECT inscrito2.nombre_CU "
            + "FROM tanzstudiodb.inscrito2 INNER JOIN  "
            + "curso ON curso.nombre_CU = inscrito2.nombre_CU AND tipo = 'esporadico' AND matricula= ? ";
    private static final String busquedaEstadoCuenta = "SELECT * FROM estadocuenta  WHERE id_Cuenta=?";
    private static final String busquedaEstadoCuentaNombre = "select matricula from alumno WHERE nombre_A= ? "
            + "AND apellidoPaterno_A = ? AND apellidoMaterno_A= ?";
    
    public ControlPagos(){
        super();
    }
    /**
    * Método que registra el abono en la base de datos
    * @param pago
    * @throws Exception 
    */
        public void alta(Pagos pago) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( registrarPago );
            ps.setString( 1, pago.getTipo() );
            ps.setString( 2, Double.toString( pago.getAbono()  ));
            System.out.println(ps);
        //ejecuta la actualizacion     
            ps.executeUpdate();        
        cerrar(ps);
        }
    /**
    * Método que registra el abono en la base de datos
    * @param matricula id_Pago
    * @throws Exception 
    */
      
        public void altaHacer2(int matricula, int id_Pago) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( insertanHace2 );
            ps.setInt( 1, matricula );
            ps.setInt( 2, id_Pago );
            System.out.println(ps);
        //ejecuta la actualizacion     
            ps.executeUpdate();        
        cerrar(ps);
        }
        
        public void altaHacer3(int id_Pago, int id_Cuenta) throws Exception {
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( insertanHace3 );
            ps.setInt( 1, id_Cuenta );
            ps.setInt( 2, id_Pago );
            System.out.println(ps);
        //ejecuta la actualizacion     
            ps.executeUpdate();        
        cerrar(ps);
        }
        
        public ArrayList<String> consultaAlumno(int matricula) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( busqueda );
        ps.setInt(1, matricula );
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                array.add(Integer.toString(rs.getInt("matricula")));
                array.add(rs.getString("nombre_A"));
                array.add(rs.getString("apellidoPaterno_A"));
                array.add(rs.getString("apellidoMaterno_A"));           
            }
            return array;       
        }
      
        public ArrayList<String> consultaNombreAlumno(String nombre_A, 
                String apellidoPaterno_A, String apellidoMaterno_A )
               throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( busquedaNombreCompleto );
        ps.setString(1, nombre_A );
        ps.setString(2, apellidoPaterno_A );
        ps.setString(3, apellidoMaterno_A );
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
             while (rs.next()) {
                 array.add(Integer.toString(rs.getInt("matricula")));
                 array.add(rs.getString("nombre_A"));
                 array.add(rs.getString("apellidoPaterno_A"));
                 array.add(rs.getString("apellidoMaterno_A"));           
                }
                return array;       
        }
      
        public ArrayList<String> consultarId(int matricula ) throws SQLException, Exception{
          PreparedStatement ps = null;
           ps = conexion.prepareStatement( busquedaPorMatricula );
           ps.setInt(1, matricula );
              ResultSet rs = ps.executeQuery();
              ArrayList<String> array = new ArrayList<String>();
                while (rs.next()) {
                    array.add(Integer.toString(rs.getInt("id_Cuenta")));
                }
                cerrar(ps);
                cerrar(rs);
                return array;
        }
      
        public ArrayList<String> consultarIdPorNombre(String nombre_A, 
                String apellidoPaterno_A, String apellidoMaterno_A) 
                throws SQLException, Exception{
          PreparedStatement ps = null;
          ps = conexion.prepareStatement( busquenaPorNombreCompletoMensualidad );
            ps.setString(1, nombre_A );
            ps.setString(2, apellidoPaterno_A );
            ps.setString(3, apellidoMaterno_A );
              ResultSet rs = ps.executeQuery();
              ArrayList<String> array = new ArrayList<String>();
                while (rs.next()) {
                   array.add(Integer.toString(rs.getInt("id_Cuenta")));
                }
                cerrar(ps);
                cerrar(rs);
                return array;
        }
            
        public ArrayList<String> consultaEsporadicosPorMatricula( int matricula ) 
                  throws SQLException, Exception{
            PreparedStatement ps = null;
            ps = conexion.prepareStatement( BusquedaPorMatriculaEsporadico );
            ps.setInt(1, matricula );
            ResultSet rs = ps.executeQuery();
            ArrayList<String> cursos = new ArrayList<String>();            
                while (rs.next()) {
                    cursos.add( rs.getString( "nombre_CU" ) );
                }
                cerrar(ps);
                cerrar(rs);
                return cursos;
        }
      
      
      
        public int obtenerId() throws SQLException, Exception{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( obtenerId );      
        ResultSet rs = ps.executeQuery();
        int id = 0;
            while (rs.next()) {
                id = rs.getInt("max(id_Pago)");
            }
            cerrar(ps);
            cerrar(rs);
            return id;
        }
    
        public String BusquedaEdadoDeCuentaNombre(String nombre_A, 
            String apellidoPaterno_A, String apellidoMaterno_A)
            throws SQLException, Exception{
            PreparedStatement ps = null;
            ps = conexion.prepareStatement( busquedaEstadoCuentaNombre );
                ps.setString(1, nombre_A );
                ps.setString(2, apellidoPaterno_A );
                ps.setString(3, apellidoMaterno_A );
                    ResultSet rs = ps.executeQuery();
                    String matricula = null;
                    while (rs.next()) {
                        matricula = Integer.toString( rs.getInt("matricula") );
                    }
                    cerrar(ps);
                    cerrar(rs);
                    return matricula; 
        }
            
        public ArrayList<String> busquedaEstadoDeCuenta(int id_Cuenta, 
            String tipo) throws SQLException, Exception{
            PreparedStatement ps = null;
            ps = conexion.prepareStatement( busquedaDeEstadoDeCuenta );
                ps.setInt(1, id_Cuenta );
                ps.setString(2, tipo );
                System.out.println("------------------------------11111 " + ps);
                ResultSet rs = ps.executeQuery();
                ArrayList<String> array = new ArrayList<String>();
                    while (rs.next()) {
                        array.add(rs.getString("id_Cuenta"));
                        array.add(Integer.toString(rs.getInt("saldo")));
                        array.add(rs.getString("Costo_EC"));
                        array.add(rs.getString("multa"));
                    }        
                    cerrar(ps);
                    cerrar(rs);
                    return array;
        }
    
        public EstadoCuenta busquedaEstadoDeCuentaMensualidad(int id_Cuenta) 
                throws SQLException, Exception{
//    ArrayList<EstadoCuenta> mensualidad = new ArrayList<EstadoCuenta>();
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( busquedaParaMensualidad );
            ps.setInt(1, id_Cuenta );
            ResultSet rs = ps.executeQuery();
            EstadoCuenta cuenta = new EstadoCuenta();
            while (rs.next()) {
                cuenta.setIdCuenta(Integer.parseInt(rs.getString("id_Cuenta")));
                cuenta.setSaldo(Integer.parseInt(rs.getString("saldo")));
                cuenta.setCosto_EC(Double.parseDouble(rs.getString("Costo_EC")));
                cuenta.setMulta(Double.parseDouble(rs.getString("multa")));
                cuenta.setTipo( rs.getString("tipo_EC") );
//           mensualidad.add(cuenta);
            }
            cerrar(ps);
            cerrar(rs);
            return cuenta;
        } 
    
        public void actualizarSaldo(String nuevoSaldo, int id_Cuenta) throws SQLException, Exception{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( actualizaSaldo );
            ps.setString( 1, nuevoSaldo );
            ps.setInt( 2, id_Cuenta );
            System.out.println(ps);
        //ejecuta la actualizacion     
            ps.executeUpdate();        
         cerrar(ps);
        }
        
        public String [][] consultaDePagos( ) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaDePagos );
        int cont=0;
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                cont++;           
            }
            String [][] matrizConsulta = new String [cont][5];
            cont=0;
            rs = ps.executeQuery();
                while(rs.next()){
                    matrizConsulta [cont][0]= Integer.toString(rs.getInt("matricula"));
                    matrizConsulta [cont][1]= rs.getString("nombre_A") + " " + 
                    rs.getString("apellidoPaterno_A") + " " + rs.getString("apellidoMaterno_A");
                    matrizConsulta [cont][2]= rs.getString("tipo_Pa");
                    matrizConsulta [cont][3]= Float.toString(rs.getFloat("abono"));
                    matrizConsulta [cont][4]= rs.getString("fecha_Pa");
                    cont++;
                }
                return matrizConsulta;       
        }
    
        public String [][] consultaDeAdeudos( ) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaDeAdeudos );
        int cont=0;
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                cont++;           
            }
            String [][] matrizConsulta = new String [cont][4];
            cont=0;
            rs = ps.executeQuery();        
                while(rs.next()){
                    Float saldo = rs.getFloat("saldo");
                    Float costo = rs.getFloat("Costo_EC");
                    Float adeudo = (costo - saldo);            
                        if(adeudo > 0){
                            matrizConsulta [cont][0]= Integer.toString(rs.getInt("matricula"));
                            matrizConsulta [cont][1]= rs.getString("nombre_A") + " " + 
                            rs.getString("apellidoPaterno_A") + " " + rs.getString("apellidoMaterno_A");
                            matrizConsulta [cont][2]= rs.getString("tipo_EC");
                            matrizConsulta [cont][3]= Float.toString(adeudo);
                            cont++;
                        }
                }
                return matrizConsulta;       
        }
        
        public String [][] consultaDePagosPorMatricula( String matricula) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaDePagosPorMatricula );
        int cont=0;      
          ps.setString(1, "%" + matricula + "%");
          System.out.println(ps);
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                cont++;           
            }
            String [][] matrizConsulta = new String [cont][5];
            cont=0;
            ps.setString(1, "%" + matricula + "%");
            rs = ps.executeQuery();
                while(rs.next()){
                    matrizConsulta [cont][0]= Integer.toString(rs.getInt("matricula"));
                    matrizConsulta [cont][1]= rs.getString("nombre_A") + " " + 
                    rs.getString("apellidoPaterno_A") + " " + rs.getString("apellidoMaterno_A");
                    matrizConsulta [cont][2]= rs.getString("tipo_Pa");
                    matrizConsulta [cont][3]= Float.toString(rs.getFloat("abono"));
                    matrizConsulta [cont][4]= rs.getString("fecha_Pa");
                    cont++;
                }
                return matrizConsulta;       
        }
    
        public String [][] consultaDePagosPorNombre( String nombre) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaDePagosPorNombre );
        int cont=0;
          ps.setString(1, "%" + nombre + "%");
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                cont++;           
            }
            String [][] matrizConsulta = new String [cont][5];
            cont=0;
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
                while(rs.next()){
                    matrizConsulta [cont][0]= Integer.toString(rs.getInt("matricula"));
                    matrizConsulta [cont][1]= rs.getString("nombre_A") + " " + 
                    rs.getString("apellidoPaterno_A") + " " + rs.getString("apellidoMaterno_A");
                    matrizConsulta [cont][2]= rs.getString("tipo_Pa");
                    matrizConsulta [cont][3]= Float.toString(rs.getFloat("abono"));
                    matrizConsulta [cont][4]= rs.getString("fecha_Pa");
                    cont++;
                }
                return matrizConsulta;       
        }
        
        public String [][] consultaDeAdeudoPorNombre( String nombre) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaDeAdeudosPorNombre );
        int cont=0;
          ps.setString(1, "%" + nombre + "%");
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                cont++;    
            }
            String [][] matrizConsulta = new String [cont][4];
            cont=0;
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
                while(rs.next()){
                    Float saldo = rs.getFloat("saldo");
                    Float costo = rs.getFloat("Costo_EC");
                    Float adeudo = (costo - saldo);
                        if(adeudo > 0){
                            matrizConsulta [cont][0]= Integer.toString(rs.getInt("matricula"));
                            matrizConsulta [cont][1]= rs.getString("nombre_A") + " " + 
                            rs.getString("apellidoPaterno_A") + " " + rs.getString("apellidoMaterno_A");
                            matrizConsulta [cont][2]= rs.getString("tipo_EC");
                            matrizConsulta [cont][3]= Float.toString(adeudo);
                            cont++;
                        }
                }
                return matrizConsulta;       
        }
        
        public String [][] consultaDeAdeudoPorMatricula( String matricula) throws SQLException{
        PreparedStatement ps = null;
        ps = conexion.prepareStatement( consultaDeAdeudosPorMatricula );
        int cont=0;
        System.out.println(ps);
          ps.setString(1, "%" + matricula + "%");
          ResultSet rs = ps.executeQuery();
          ArrayList<String> array = new ArrayList<String>();
            while (rs.next()) {
                cont++;    
            }
            String [][] matrizConsulta = new String [cont][4];
            cont=0;
            ps.setString(1, "%" + matricula + "%");
            rs = ps.executeQuery();
                while(rs.next()){
                    Float saldo = rs.getFloat("saldo");
                    Float costo = rs.getFloat("Costo_EC");
                    Float adeudo = (costo - saldo);
                        if(adeudo > 0){
                            matrizConsulta [cont][0]= Integer.toString(rs.getInt("matricula"));
                            matrizConsulta [cont][1]= rs.getString("nombre_A") + " " + 
                            rs.getString("apellidoPaterno_A") + " " + rs.getString("apellidoMaterno_A");
                            matrizConsulta [cont][2]= rs.getString("tipo_EC");
                            matrizConsulta [cont][3]= Float.toString(adeudo);
                            cont++;
                        }
                }
                return matrizConsulta;       
        }
       
        public EstadoCuenta busquedaEspecificaEstadoCuenta( String id ) throws SQLException {
           PreparedStatement ps = null;
           ps = conexion.prepareStatement( busquedaEstadoCuenta );
           ps.setInt(1, Integer.parseInt( id ) );
           ResultSet rs = ps.executeQuery();
           EstadoCuenta cuenta = new EstadoCuenta();
            while( rs.next() ) {
               cuenta.setIdCuenta( rs.getInt( "id_Cuenta" ) );
               cuenta.setTipo( rs.getString( "tipo_EC" ) );
               cuenta.setSaldo( rs.getInt( "saldo" ) );
               cuenta.setCosto_EC( rs.getDouble("Costo_EC") );
               cuenta.setMulta( rs.getDouble("multa") );
            }
            return cuenta;
        }
}