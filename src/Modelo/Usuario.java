/**
 * Usuario.java
 * Version 1 Noviembre 30
 * GNU GENERAL PUBLIC LICENSE
 * Copyright (c) Christian Blanco León Mexicali,BC. Inc. 
 * All rights reserved
 */
package Modelo;

/**
 * Clase modelo 
 * @version 1 30/11/15
 * @author Christian Blanco León
 */
public class Usuario {
    
    private String nombreDeUsuario;
    private String contraseña;
    private String tipoDeUsuario;
    private String pregunta;
    private String respuesta;
 
    /**
     * Creación del constructor para inicializar Usuario
     */
    public Usuario(){     
    }

    /**
     * Constructor que inicializa todas las variables de Usuario
     * @param nombreDeUsuario
     * @param contraseña
     * @param tipoDeUsuario
     * @param pregunta
     * @param respuesta 
     */
    public Usuario(String nombreDeUsuario, String contraseña, 
            String tipoDeUsuario, String pregunta, String respuesta) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contraseña = contraseña;
        this.tipoDeUsuario = tipoDeUsuario;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
    
    /**
     * Metodo que retorna el nombre de usuario
     * @return 
     */
    public String getNombreDeUsuario(){
        return nombreDeUsuario;
    }
    
    /**
    *Metodo que almacena el nombre de usuario
    * @
    */        
    public void setNombreDeUsuario(String nombreDeUsuario){
        this.nombreDeUsuario = nombreDeUsuario;
    }
    
    /**
    *Metodo que retorna Contraseña
    * @
    */        
    public String getContraseña(){        
        return contraseña;
    }
    /**
    *Metodo que almacena Contraseña
    * @
    */        
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;            
    }
    /**
    *Metodo que retorna el tipo de usuario
    * @
    */        
    public String getTipoDeUsuario(){            
        return tipoDeUsuario;
    }
    /**
    *Metodo que almacena el tipo de usuario
    * @
    */       
    public void setTipoDeUsuario(String tipoDeUsuario){    
        this.tipoDeUsuario = tipoDeUsuario;            
    }
    /**
    *Metodo que retorna Pregunta
    * @
    */    
    public String getPregunta(){            
        return pregunta;        
    }
    /**
    *Metodo que almacena Pregunta
    * @
    */        
    public void setPregunta(String pregunta){        
        this.pregunta = pregunta;            
    }
    /**
    *Metodo que retorna Respuesta
    * @
    */       
    public String getRespuesta(){            
        return respuesta;        
    }
    /**
    *Metodo que almacena Respuesta
    * @
    */        
    public void setRespuesta(String respuesta){        
        this.respuesta = respuesta;            
    }
    
}
