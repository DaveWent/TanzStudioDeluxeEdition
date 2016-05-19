package Modelo;

import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Salud{
    private int id;
    private String problema;
    private String controlada;

    public Salud() {
        
    }
    public Salud(ArrayList datos){
        this.problema=datos.get(0).toString();
        this.controlada=datos.get(1).toString();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema( String problema ) {
        this.problema = problema;
    }

    public String getControlada() {
        return controlada;
    }

    public void setControlada( String controlada ) {
        this.controlada = controlada;
    }

    public String toString() {
        return "SaludDTO{" + "id=" + id + ", problema=" + problema + ", controlada=" + controlada + '}';
    }
    
}
