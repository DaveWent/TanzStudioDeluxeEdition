package Controlador;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author cristian
 */
public class CValidacion {

    public boolean isNulo(ArrayList alDatos) {
        boolean retorno = true;
        z:
        for (Object alDato : alDatos) {
            if (alDato.toString().length()<1||Objects.isNull(alDato) || alDato.equals("Dia") ||
                    alDato.equals("Mes") || alDato.equals("Año") || alDato.equals("PADRE")
                    || alDato.equals("MADRE")) {
                  System.out.println("true "+alDato);
            } else {  
                   System.out.println("error"+alDato);
               retorno=false; 
               break z;
            }
        }
        return retorno;
    }

    public void validaDato(ArrayList dato, ArrayList nombre, ArrayList tipo,String titulo) throws Exception {
        //  int errores=0;
        int telefono = 0;//contador para los datos obligatorios
        boolean bandera = false;
        
        //  System.out.println("-----------------------------------------");
        for (int i = 0; i < dato.size(); i++) {
            String dato1 = dato.get(i).toString();//variable que contiene el valor de dato en esta posicion
            String nombre1 = nombre.get(i).toString();//varible que contiene el valor del nombre en esa posicion
            String tipo1 = tipo.get(i).toString();//variable que contiene  el valor de el tipo en esta pposicion
            //     System.out.println("dato="+dato1+"nombre="+nombre1+"Tipo="+tipo1);

            if (tipo1.equals("texto")) {
                //en caso de que se encuentre vacio o no contenga caracteres aceptables se arrojara un error
                if (!validarCamposVacios(dato1, nombre1,titulo) || !validarPalabras(nombre1, dato1,titulo)) {
                    throw new Exception();
                }

            }
            if (tipo1.equals("numero")) {
                //en caso de que el dato1 se ecuetre vacio o contenga letras se arrojara una excepcion 
                if (!validarCamposVacios(dato1, nombre1,titulo) || !validarNoLetras(dato1, nombre1,titulo)) {
                    throw new Exception();
                }
            }
            if (tipo1.equals("mixto")) {
                if (!validarCamposVacios(dato1, nombre1,titulo)) {
                    throw new Exception();
                }
            }
            if (tipo1.equals("telefono")) {
                bandera = true;
                if (dato1.length() == 0) {//si dato1 esta vacio
                    //System.out.println("obligatorio=" + obligatorio);
                } else {
                    telefono++;
                    if (!validarNoLetras(dato1, nombre1,titulo)) {
                        throw new Exception();
                    }
                }
            }
            if (tipo1.equals("Noobligatorio")) {
                if (dato1.length() != 0) {//si el dato no esta vacio
                    if (nombre1.equals("correo electronico")) {//si es un correo electronico
                        if (!validarEmail(dato1, tipo1,titulo)) {
                            throw new Exception();
                        }
                    } else {
                        if (!validarCamposVacios(dato1, tipo1,titulo)) {
                            throw new Exception();
                        }
                    }
                }
            }
            if (tipo1.equals("combo")) {
                if (dato1.equalsIgnoreCase("Sexo")) {
                    JOptionPane.showMessageDialog(null, "Error, Seleccione el sexo",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
                if (dato1.equalsIgnoreCase("Danza")) {
                    JOptionPane.showMessageDialog(null, "Error, Seleccione danza",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
                if (dato1.equalsIgnoreCase("Medio")) {
                    JOptionPane.showMessageDialog(null, "Error, Seleccione el Medio de publicidad",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
                if (dato1.equalsIgnoreCase("Año")) {
                    JOptionPane.showMessageDialog(null, "Error," + nombre1 + " faltante",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
                if (dato1.equalsIgnoreCase("Mes")) {
                    JOptionPane.showMessageDialog(null, "Error," + nombre1 + " faltante",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
                if (dato1.equalsIgnoreCase("Dia")) {
                    JOptionPane.showMessageDialog(null, "Error," + nombre1 + " faltante",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
                if (!validarCamposVacios(dato1, nombre1,titulo)) {
                    throw new Exception();
                }

            }
            if (tipo1.equalsIgnoreCase("edad")) {
                int edad = Integer.parseInt(dato1);
                if (edad < 3) {
                    JOptionPane.showMessageDialog(null, "Error," + nombre1 + " no puede ser menor a los 3 años",
                            null, JOptionPane.ERROR_MESSAGE, null);
                    throw new Exception();
                }
            }
        }
        //valida si porlomenos uno de los obligatorios fue regristrados
        if (telefono == 0 && bandera == true) {
            JOptionPane.showMessageDialog(null, "Error numero telefonico faltante",
                    null, JOptionPane.ERROR_MESSAGE, null);
            throw new Exception();
        }
    }
    /*
     * metodo utilizado para validar todo los campos del  alumno, retornara
     * 0 si no se encuentran errores pero si hay errores se retornara la cantidad
     * de errores 
     */

    private boolean validarPalabras(String cadena, String palabra,String titulo) {
        boolean booleano = true;
        for (int x = 0; x < palabra.length(); x++) {
            if (palabra.charAt(x) > 64 && palabra.charAt(x) < 91) {
                booleano = true;
            } else {
                if (palabra.charAt(x) > 96 && palabra.charAt(x) < 123 || palabra.charAt(x) == ' '||
                         palabra.charAt(x) == 'ñ'|| palabra.charAt(x) == 'Ñ') {
                    booleano = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error el campo" + cadena + "de "+titulo
                            + " no ingrese numeros o simbolos", null, JOptionPane.ERROR_MESSAGE, null);
                    booleano = false;
                    return booleano;
                }
            }
        }
        return booleano;
    }

    /**
     * *
     * Este metodo valida si la cadena este vacia, si retorna false esta vacia y
     * si retorna true es que contiene datos
     *
     * @param cadena
     * @param palabra
     * @return
     */
    private boolean validarCamposVacios(String palabra, String cadena,String titulo) {
        if (palabra.length() == 0) {
            JOptionPane.showMessageDialog(null, "Error en " + cadena + "de "+titulo
                    + " se encuentra vacio", null, JOptionPane.ERROR_MESSAGE, null);

            return false;
        } else {
            return true;
        }
    }

    /**
     * *
     * Este metodo valida que una cadena este en formato de email
     * "correo"@"dominio"
     *
     * @param cadena
     * @param email
     * @return
     */
    private boolean validarEmail(String email, String cadena,String titulo) {
        String emailArray[] = email.split("@");

        if (emailArray.length == 2) {
            if (emailArray[1].equalsIgnoreCase("hotmail.com")
                    || emailArray[1].equalsIgnoreCase("gmail.com")
                    || emailArray[1].equalsIgnoreCase("outlook.com")
                    || emailArray[1].equalsIgnoreCase("yahoo.com")
                    || emailArray[1].equalsIgnoreCase("uabc.edu.mx")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error el " + cadena + "  de "+titulo
                        + "error dominio no existente", null, JOptionPane.ERROR_MESSAGE, null);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error el " + cadena + " de "+titulo
                    + "correo incompleto", null, JOptionPane.ERROR_MESSAGE, null);
            return false;
        }
    }

    /**
     * *
     * metodo utlizado para validar si una cadena contiene numeros
     *
     * @param tipo
     * @param cadena
     * @return
     */
    private boolean validarNoLetras(String cadena, String tipo,String titulo) {
        //es numero
        if (cadena.matches("[0-9]*")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Eroro el " + tipo + " de "+titulo
                    + " no debe contener letras", null, JOptionPane.ERROR_MESSAGE, null);
            return false;
        }
    }
}
