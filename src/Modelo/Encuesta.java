package Modelo;

import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Encuesta  {
   
    public int id;
    private String medioPublicidad;
    private String danzaSugerida;
    private String lugarDanza;
    private String sugerencia;
    
    public Encuesta(){
        
    }
    public Encuesta(ArrayList datos){
        this.medioPublicidad=datos.get(0).toString();
        this.danzaSugerida=datos.get(1).toString();
        this.lugarDanza=datos.get(2).toString();
        this.sugerencia=datos.get(3).toString();
    }
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getMedioPublicidad() {
        return medioPublicidad;
    }

    public void setMedioPublicidad( String medioPublicidad ) {
        this.medioPublicidad = medioPublicidad;
    }

    public String getDanzaSugerida() {
        return danzaSugerida;
    }

    public void setDanzaSugerida( String danzaSugerida ) {
        this.danzaSugerida = danzaSugerida;
    }


    public String getLugarDanza() {
        return lugarDanza;
    }

    public void setLugarDanza( String lugarDanza ) {
        this.lugarDanza = lugarDanza;
    }

    public String getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia( String sugerencia ) {
        this.sugerencia = sugerencia;
    }

    public String toString() {
        return "EncuestaDTO{" + "id=" + id + ", medioPublicidad=" + medioPublicidad 
                + ", danzaSugerida=" + danzaSugerida + ", lugarDanza=" + lugarDanza 
                + ", sugerencia=" + sugerencia + '}';
    }




    
}
