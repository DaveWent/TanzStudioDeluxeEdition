/**
 * InterfaceRecibeDatosEnClases.java
 * Version 1
 * 31/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Interfaces;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * Clase que contiene los metodos utilizados por la interface en la vista
 * de las clases
 * @author David Omar Cabrera Bernal
 */
public interface IClases {
    /**
     * Método que recibe las clases de un curso en específico almacenados en la 
     * tabla, para despues almacenar dicha tabla
     * @param tbClases 
     */
    public void recibeClasesActuales( JTable tbClases );
    /**
     * Método que recibe los salones disponibles en un JComboBox, para almacenarlo
     * en la vista de clases
     * @param cbSalon 
     */
    public void recibeSalones( JComboBox cbSalon );
}
