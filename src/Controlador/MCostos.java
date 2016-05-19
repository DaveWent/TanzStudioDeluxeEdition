/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Costos;
import Modelo.Curso;
import Modelo.Pagos;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class MCostos {
   CCostos control = new CCostos();
   
   public void actualizarCostos(String nombreCos, Double costos, Double nuevoCosto) throws Exception{
       int id=control.Busqueda( nombreCos, costos);
       System.out.println(id);
       if(id==0){
           Costos modelo = new Costos(nombreCos,nuevoCosto);
           control.alta(modelo);
       }else{
           Costos modelo = new Costos(id,nuevoCosto);
           control.modificacion(modelo);
       }
   
   }
   public double obtenerCostos(String nombre) throws SQLException, Exception{
   if(control.Busqueda(nombre)!=0){
       double precioMensualidad = control.obtPrecio(nombre);
       return precioMensualidad;
   }else{
      double variable=0;
      return variable;
   }
   
   }
   public String[] obtenerCursos() throws SQLException, Exception {
        CCurso controlCurso = new CCurso();
        ArrayList<Curso> cursos = controlCurso.obtenerCursosEsporadicos();
        int longitudCursoArray = cursos.size();
        String[] cursosArray = new String[ longitudCursoArray ];
        int contadorCursos = 0;
        
        for ( Curso curso : cursos ) {
            cursosArray[ contadorCursos ] = curso.getNombreCurso();
            contadorCursos++;
        }
        
        return cursosArray;
    
   }
   public double obtenerCostosEsporadicos( String nombreCurso ) throws SQLException{
   Costos esporadico = control.ObtenerCostosEsporadicos(nombreCurso);
   double costoEsporadico;
   if( esporadico == null ) {
       costoEsporadico = 0;
   } else {
   costoEsporadico = esporadico.getCostos();
   }
   return costoEsporadico;
   }
}
