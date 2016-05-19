/**
 * ControlVista.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Controlador;

import Interfaces.ICierraVentana;
import Modelo.Usuario;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import Interfaces.IRecibeDatosUsuario;

/**
 * Clase donde se controlan las acciones de la vista
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class CVistaUsuario {
    IRecibeDatosUsuario interfaz;
    ICierraVentana interfaceCerrar;
    MUsuarios mantenimiento = new MUsuarios();
    JTable consultaUsuariosjTable;    
    /**
    * Cracion del constructor para inicializar ControlVista
    * @
    */
    public CVistaUsuario() {
    }
    
    public CVistaUsuario( ICierraVentana interfaceCerrar ) {
        this.interfaceCerrar = interfaceCerrar;
    }

    /**
     * Constructor que inicializa las interfaces que modifican elementos de la vista 
     * @param interfaz
     * @param consultaUsuariosjTable 
     */
    public CVistaUsuario(IRecibeDatosUsuario interfaz, JTable consultaUsuariosjTable){
        this.interfaz = interfaz;
        this.consultaUsuariosjTable = consultaUsuariosjTable;
    }

    /**
     * Método que sirve para dar de alta en el sistema a los usuarios
     * @param usuario
     * @param contraseña
     * @param confirmacion
     * @param pregunta
     * @param respuesta
     * @throws Exception 
     */
    public boolean altas(String usuario, String contraseña, String confirmacion, 
            String pregunta, String respuesta, String tipo, String proveniente) throws Exception{
        boolean pase = false;
        if(usuario.length()!=0 && contraseña.length()!=0 && confirmacion.length()!=0 
           && pregunta.length()!=0 && respuesta.length()!=0){
            
           if(contraseña.equals(confirmacion)){
               
                if(!pregunta.equals("Elige una pregunta") ){
                    String[] resultado = mantenimiento.consulta();
                    boolean usuarioYaExistente = false;
                    
                    for(int x = 0 ; x < resultado.length ; x++){
                        if( resultado[x].equalsIgnoreCase(usuario) ){
                            usuarioYaExistente = true;
                            break;
                        }
                    }
                    
                    if(  usuarioYaExistente == false){
                        Usuario controlador = new Usuario();
                        //Se crea el objeto para llamar los metodos de Usuario y almacenar las variables
                        controlador.setNombreDeUsuario( usuario );
                        controlador.setContraseña( contraseña );
                        controlador.setPregunta( pregunta );
                        controlador.setRespuesta( respuesta );
                        controlador.setTipoDeUsuario( tipo );
                        mantenimiento.alta( controlador );
                        pase = true;
                        if( !proveniente.equalsIgnoreCase("primeraVez") ) {
                            interfaceCerrar.cerrarVentana();
                        }
                    }else{
                    JOptionPane.showMessageDialog( null, "Ya existe este nombre de usuario",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    }       
                }                
            }else{
                JOptionPane.showMessageDialog(null, "Las contraseñas no concuerdan",
                        null, JOptionPane.ERROR_MESSAGE, null);
            }
        }else {
            JOptionPane.showMessageDialog(null, "No dejes espacios vacios",
                    null, JOptionPane.ERROR_MESSAGE, null);
        }     
        return pase;
    }
    /**
     * Método para consultar los usuarios del sistema
     * @throws Exception 
     */
    public void consulta() throws Exception{
        mantenimiento = new MUsuarios();
        String[] usuario = mantenimiento.consulta();
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        modelo.addColumn("Nombre de Usuarios");

        String[] registros = new String[1];

        for( int x = 0 ; x < usuario.length ; x++  ) {
            registros[0] = usuario[x];
            modelo.addRow(registros);
        }

        consultaUsuariosjTable.setModel(modelo);
        consultaUsuariosjTable.getColumnModel().getColumn(0).setPreferredWidth(220);
        interfaz.recibeDatos( consultaUsuariosjTable );

    }
    /**
     * Método para eliminar los usuarios en el sistema
     * @param nombreDeUsuario
     * @throws Exception 
     */
    public void EliminarUsuario( String nombreDeUsuario ) throws Exception {
        mantenimiento = new MUsuarios();
        mantenimiento.EliminarUsuario( nombreDeUsuario );
    }
    
    public boolean confirmacionBorradoUsuario( String nombreDeUsuario ) 
            throws Exception {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar este usuario?", 
            "Borrado de usuario", JOptionPane.YES_NO_OPTION);
        if( opcion == 0) {
            try {
                EliminarUsuario(nombreDeUsuario);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
            }
            
            consulta();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean iniciarSesion(String nombre, String contraseña) 
            throws SQLException, Exception {
        mantenimiento = new MUsuarios();
        boolean pase = mantenimiento.iniciarSesion(nombre, contraseña);
        return pase;
    }
}
