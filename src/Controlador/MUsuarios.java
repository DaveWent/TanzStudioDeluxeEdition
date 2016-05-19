/**
 * MantenimientodeUsuarios.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Controlador;

import Modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que utiliza los metodos de los controladores
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class MUsuarios {
    CUsuario control = new CUsuario();
    
    /**
     * Método que registra un usuario llamando el método alta de la clase ControlVista
     * @param usuario
     * @throws Exception 
     */
    public void alta(Usuario usuario) throws Exception{
        control.alta(usuario);
    }
    
    /**
     * Método que consulta los usuarios llamando el método Consultas de la clase ControlVista
     * @return
     * @throws Exception 
     */
    public String[] consulta() throws Exception{
        int contador = 0;  
        ArrayList<Usuario> resultado = control.Consultas();
        String[] usuario = new String[resultado.size()];
        for(Usuario nombreUsuario: resultado){
            usuario[contador] = nombreUsuario.getNombreDeUsuario();   
            contador++;
        } 
        return usuario;
    }
    /**
     * Método que elimina los usuarios del sistema llamando al método baja de la clase ControlVista
     * @param nombreDeUsuario
     * @throws SQLException
     * @throws Exception 
     */   
    public void EliminarUsuario( String nombreDeUsuario ) throws SQLException, Exception {   
        boolean tipo = control.validarAdminPrincipal( nombreDeUsuario );
        if( tipo ) {
            throw new Exception( "No puedes borrar ese usuario" );
        } else {
            control.baja(nombreDeUsuario);
        }
    }
    
    public boolean iniciarSesion( String nombreUsuario, String contraseña ) throws SQLException, Exception {
        ArrayList<Usuario> usuarios = control.Consultas();
        
        for( Usuario usuario : usuarios ) {
            control.limpiarEjecuccionUsuario( usuario.getNombreDeUsuario() );
        }
        String cadena = control.iniciarSesion( nombreUsuario, contraseña);
        
        if( cadena.length() == 0 ) {
            return false;
        } else {
            control.asignarEjecuccionUsuario( nombreUsuario );
            return true;
        }
    }
}
