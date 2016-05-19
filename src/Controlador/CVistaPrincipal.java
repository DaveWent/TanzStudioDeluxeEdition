/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.MUsuarios;

/**
 *
 * @author Dave Went
 */
public class CVistaPrincipal {
    
    MUsuarios mantenimientoUsuario;
    
    public CVistaPrincipal() {
        
    }
    
    public boolean verificarUsuariosExistentes() throws Exception {
        mantenimientoUsuario = new MUsuarios();
        String[] usuarios = mantenimientoUsuario.consulta();
        int cantidadUsuarios = usuarios.length;
        if( cantidadUsuarios == 0 ) {
            return false;
        } else {
            return true;
        }
    }
}
