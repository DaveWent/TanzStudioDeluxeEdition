/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Date;

/**
 *
 * @author cristian
 */
public interface ICicloEscolar {

    public void enviarFechaInicio(Date hoy, boolean valida);

    public void enviarFechaFin(Date hoy, boolean valida);
    
    public void enviarFechaFin(boolean valida);

    public void enviarCicloEscolar(Date fechaInicio, Date fechaFin, String nombre);
}
