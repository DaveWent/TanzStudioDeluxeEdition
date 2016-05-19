/**
 * MantenimientodeUsuarios.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Interfaces;

import javax.swing.JTable;

/**
 * Clase para mostrar los usuarios en una tabla
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public interface IRecibeDatosUsuario {
    /**
     * Método que recibe los nombres de los usuarios para mostrarlos en la tabla
     * @param consultaUsuariosjTable 
     */
   public void recibeDatos(JTable consultaUsuariosjTable);
    
}
