package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interfaces.ICierraVentana;
import Interfaces.ICargaCursos;
import Interfaces.IModificacionCurso;
import Interfaces.ICargaProfesores;
import Interfaces.IHorario;
import Interfaces.IClases;

/**
 *
 * @author Dave Went
 */
public class CVistaCurso {
    IModificacionCurso interfazRecibeDatosModificacion;
    ICargaProfesores interfazRecibeProfesores;
    ICargaCursos interfazCargaCursos;
    IHorario interfazHorario;
    IClases interfazClases;
    ICierraVentana interfazCerrar;
    MCurso mantenimientoCurso;
    MClase mantenimientoClase;
    MProfesor mantenimientoProfesor;
    CValidacionCurso controlValidacionCurso;
    CValidacionClase controlValidacionClase;
    String[][] profesores;
    String[][] clasesDelCurso;
    String nombreOriginalCurso;
    ArrayList<String> profesoresHoy = new ArrayList<String>();
    int filasPorDefault = 4;
    int filasAgregadas = 0;
    
    public CVistaCurso(  ) {
        
    }
    
    public CVistaCurso( ICargaProfesores interfazRecibeProfesores ) {
        this.interfazRecibeProfesores = interfazRecibeProfesores;
    }
    
    public CVistaCurso( ICargaProfesores interfazRecibeProfesores, 
            ICierraVentana interfazCerrar ) {
        this.interfazRecibeProfesores = interfazRecibeProfesores;
        this.interfazCerrar = interfazCerrar;
    }
    
    public CVistaCurso( ICargaCursos interfazCargaCursos ) {
        this.interfazCargaCursos = interfazCargaCursos;
    }
    
    public CVistaCurso( ICargaCursos interfazCargaCursos, ICierraVentana interfazCerrar ) {
        this.interfazCargaCursos = interfazCargaCursos;
        this.interfazCerrar = interfazCerrar;
    }
    
    public CVistaCurso( IModificacionCurso interfazRecibeDatosModificacion, 
            ICierraVentana interfazCerrar ) {
        this.interfazRecibeDatosModificacion = interfazRecibeDatosModificacion;
        this.interfazCerrar = interfazCerrar;
    }
    
    public CVistaCurso( IHorario interfazHorario ) {
        this.interfazHorario = interfazHorario;
    }
    
    public CVistaCurso( IClases interfazClases ) {
        this.interfazClases = interfazClases;
    }
    
    public CVistaCurso( ICierraVentana interfazCerrar ) {
        this.interfazCerrar = interfazCerrar;
    }
    
    public void obtenerProfesores( JComboBox cbProfesor, String proveniente ) 
            throws SQLException, Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        mantenimientoCurso = new MCurso();
        profesores = mantenimientoCurso.obtenerProfesores();
        
        int cantidadProfesores = profesores.length;
        modelo.addElement( "Seleccione un profesor" );
        for( int x = 0 ; x < cantidadProfesores ; x++ ) {
            String profesor;
            profesor = profesores[ x ][ 0 ] +" ";
            profesor = profesor + profesores[ x ][ 1 ] +" ";
            profesor = profesor + profesores[ x ][ 2 ] +" ";
            profesor = profesor + profesores[ x ][ 3 ];
            modelo.addElement( profesor );
        }
        cbProfesor.setModel( modelo );
        if( proveniente.equalsIgnoreCase( "Modificacion" ) ) {
            interfazRecibeDatosModificacion.recibeProfesoresEnComboBox( cbProfesor );
        } else {
            interfazRecibeProfesores.actualizaProfesores( cbProfesor );
        }
    }
    
    public void altaCurso( String nombreCurso, String tipoCurso,
            String requiereSilencio, String formato, Date fechaInicioDate, 
            Date fechaFinDate ) {
        String fechaInicio;
        String fechaFin;
        try {
            controlValidacionCurso = new CValidacionCurso();
            mantenimientoCurso = new MCurso();
            controlValidacionCurso.validarCamposVacios( nombreCurso );
            controlValidacionCurso.validarRepeticionCurso( nombreCurso );
            if( tipoCurso.equalsIgnoreCase( "esporadico" ) ) {
                String[] fechas = controlValidacionCurso.validarFechas( formato, 
                        fechaInicioDate, fechaFinDate );
                fechaInicio = fechas[ 0 ];
                fechaFin = fechas[ 1 ];
                mantenimientoCurso.altaCurso( nombreCurso, tipoCurso, 
                    requiereSilencio, fechaInicio, fechaFin );
                interfazCerrar.cerrarVentana();
            }else {
                fechaInicio = "";
                fechaFin = "";
                mantenimientoCurso.altaCurso( nombreCurso, tipoCurso, 
                    requiereSilencio,  fechaInicio, fechaFin );
                interfazCerrar.cerrarVentana();
            }
        }catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void bajaCurso( String nombreCurso, JComboBox cbCursos ) {
        try {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea "
                    + "borrar ese curso?", "Borrado de profesor", 
                    JOptionPane.YES_NO_OPTION);
            if( opcion == 0 ) {
                controlValidacionCurso = new CValidacionCurso();
                mantenimientoCurso = new MCurso();
                controlValidacionCurso.validarNombreCurso( nombreCurso );
                boolean paso = mantenimientoCurso.verificarAlumnosDeCurso( nombreCurso );
                if( paso == true ) {
                    String mensaje = "No se puede eliminar ese curso,\n";
                    mensaje = mensaje + "posee alumnos";
                    throw new Exception( mensaje );
                } else {
                    mantenimientoCurso.bajaCurso( nombreCurso );
                    interfazCerrar.cerrarVentana();
                    obtenerCursos( cbCursos );
                }
            }
        }catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void obtenerCursos( JComboBox cbCursos ) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        mantenimientoCurso = new MCurso();
        String[] cursos = mantenimientoCurso.obtenerCursos();
        int cantidadCursos = cursos.length;
        modelo.addElement( "Seleccione un curso" );
        for( int x = 0 ; x < cantidadCursos ; x++ ) {
            modelo.addElement( cursos[ x ] );
        }
        cbCursos.setModel( modelo );
        interfazCargaCursos.recibeCursos( cbCursos );
    }
    
    public void crearVentana( String nombreCurso ) {
        try {
            nombreOriginalCurso = nombreCurso;
            mantenimientoCurso = new MCurso();
            controlValidacionCurso = new CValidacionCurso();
            controlValidacionCurso.validarNombreCurso( nombreCurso );
            String[] curso = mantenimientoCurso.busquedaCurso( nombreCurso );
            interfazRecibeDatosModificacion.creaVentanaModificacion( curso );
        }catch( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void eliminarFilasATablaProfesores( JTable tbProfesores ) {
        DefaultTableModel modelo = (DefaultTableModel) tbProfesores.getModel(); 
        int numProfesor = tbProfesores.getSelectedRow();
        String nombreProfesor = (String) tbProfesores.getValueAt( numProfesor, 0 );
        int contador = 0;
        for( String profesor : profesoresHoy ) {
            String[] profesorArray = profesor.split( "/" );
            String nombreCompleto = profesorArray[ 1 ] + " ";
            nombreCompleto = nombreCompleto + profesorArray[ 2 ] + " ";
            nombreCompleto = nombreCompleto + profesorArray[ 3 ];
            if( nombreCompleto.equalsIgnoreCase( nombreProfesor ) ) {
                break;
            }
            contador++;
        }
        profesoresHoy.remove( contador );
        modelo.removeRow( tbProfesores.getSelectedRow() ); 
        interfazRecibeDatosModificacion.actualizaTabla( tbProfesores );
    }
    
    public void agregarFilasATablaProfesores( JTable tbProfesores, 
            String nombreProfesor, int numProfesor ) {
        try {
            controlValidacionCurso = new CValidacionCurso();
            controlValidacionCurso.validacionProfesor( numProfesor );
            controlValidacionCurso.validarProfesorRepetido( nombreProfesor, profesoresHoy );
            agregarAProfesoresActuales( nombreProfesor );
            DefaultTableModel modelo = (DefaultTableModel) tbProfesores.getModel();
            String[] fila = new String[ 1 ];
            fila[ 0 ] = nombreProfesor;
            modelo.addRow( fila );
            tbProfesores.setModel( modelo );
            interfazRecibeDatosModificacion.actualizaTabla( tbProfesores );
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void insertarProfesoresEnTabla( JTable tbProfesores, String nombreCurso ) 
            throws SQLException {
        mantenimientoCurso = new MCurso();
        String[][] profesoresRecibidos = 
                mantenimientoCurso.obtenerProfesoresPorCurso( nombreCurso );
        DefaultTableModel modelo = ( DefaultTableModel ) tbProfesores.getModel(); 
        int cantidadProfesores = profesoresRecibidos.length;
        
        if( cantidadProfesores > 0 ) {
            for( int x = 0 ; x < cantidadProfesores ; x++ ) {
                String fila = profesoresRecibidos[ x ][ 0 ] + "/";
                fila = fila + profesoresRecibidos[ x ][ 1 ] + "/";
                fila = fila + profesoresRecibidos[ x ][ 2 ] + "/";
                fila = fila + profesoresRecibidos[ x ][ 3 ];
                profesoresHoy.add( fila );
            }
        }
        
        for( int x = 0 ; x < cantidadProfesores ; x++ ) {
            String[] fila = new String[ 1 ];
            fila[ 0 ] = profesoresRecibidos[ x ][ 0 ] + " ";
            fila[ 0 ] = fila[ 0 ] + profesoresRecibidos[ x ][ 1 ] + " ";
            fila[ 0 ] = fila[ 0 ] + profesoresRecibidos[ x ][ 2 ] + " ";
            fila[ 0 ] = fila[ 0 ] + profesoresRecibidos[ x ][ 3 ];
            modelo.addRow( fila );
        }
        tbProfesores.setModel( modelo );
        interfazRecibeDatosModificacion.recibeProfesoresEnTabla( tbProfesores );
    }
    
    public void agregarAProfesoresActuales( String nombreProfesor ) {
        int cantidadProfesores = profesores.length;
        String nombreCompleto;
        for( int x = 0 ; x < cantidadProfesores ; x++ ) {
            nombreCompleto = profesores[ x ][ 1 ] + " ";
            nombreCompleto = nombreCompleto + profesores[ x ][ 2 ] + " ";
            nombreCompleto = nombreCompleto + profesores[ x ][ 3 ];
            if( nombreCompleto.equalsIgnoreCase( nombreProfesor ) ) {
                String profesor = profesores[ x ][ 0 ] + "/";
                profesor = profesor + profesores[ x ][ 1 ] + "/";
                profesor = profesor + profesores[ x ][ 2 ] + "/";
                profesor = profesor + profesores[ x ][ 3 ];
                profesoresHoy.add( profesor );
            }
        }
    }
    
    public void modificacion ( String nombreCurso, String tipoCurso,
            String requiereSilencio, String formato, Date fechaInicioDate, 
            Date fechaFinDate ) {
        try {
            String fechaInicio;
            String fechaFin;
            controlValidacionCurso = new CValidacionCurso();
            mantenimientoCurso = new MCurso();
            controlValidacionCurso.validarCamposVacios( nombreCurso );
            controlValidacionCurso.validarRepeticionCursoAModificar( nombreCurso, 
                    nombreOriginalCurso );
            if( tipoCurso.equalsIgnoreCase( "esporadico" ) ) {
                String[] fechas = controlValidacionCurso.validarFechas( formato, 
                        fechaInicioDate, fechaFinDate );
                fechaInicio = fechas[ 0 ];
                fechaFin = fechas[ 1 ];
                mantenimientoCurso.modificacion( nombreOriginalCurso, nombreCurso, 
                        tipoCurso, requiereSilencio, fechaInicio, fechaFin, 
                        profesoresHoy );
            }else {
//                String[] fechas = mantenimientoCurso.obtenerFechaCicloEscolarActual();
//                fechaInicio = fechas[ 0 ];
//                fechaFin = fechas[ 1 ];
                fechaInicio = "";
                fechaFin = "";
                mantenimientoCurso.modificacion( nombreOriginalCurso, nombreCurso, 
                        tipoCurso, requiereSilencio, fechaInicio, fechaFin, 
                        profesoresHoy );
                interfazCerrar.cerrarVentana();
            }
        }catch( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void construirHorario( JTable tbHorario ) throws Exception {
        try {
            mantenimientoCurso = new MCurso();
            mantenimientoClase = new MClase();
            controlValidacionCurso = new CValidacionCurso();
            String[] cursos = mantenimientoCurso.obtenerCursos();
            controlValidacionCurso.validarExistenciaCursos( cursos );
            DefaultTableModel modelo = ( DefaultTableModel ) tbHorario.getModel();
            int cantidadCursos = cursos.length;
            String[] fila = new String[ 8 ];
            String[] contenidoFila = new String[ 7 ];
            int[] contadoresClases = { 0, 0, 0, 0, 0, 0, 0 };
            int numClasesMayor = 1;
            for( int x = 0 ; x < cantidadCursos ; x++ ) {
                int numClases = 1;
                fila[ 0 ] = cursos[ x ];
                
                for( int z = 1 ; z < 8 ; z++ ) {
                    fila[ z ] = " ";
                }
                for( int z = 0 ; z < 7 ; z++ ) {
                        contenidoFila[ z ] = " ";
                }
                
                String[][] clases = mantenimientoClase.obtenerClasesDeUnCurso( cursos[ x ] );
                int cantidadClases = clases.length;
                
                for( int y = 0 ; y < cantidadClases ; y++ ) {
                    String dia = clases[ y ][ 1 ];
                    String horaInicio = clases[ y ][ 3 ];
                    String horaFin = clases[ y ][ 4 ];
                    
                    for( int z = 0 ; z < 7 ; z++ ) {
                        contenidoFila[ z ] = " ";
                    }
                    
                    if( dia.equalsIgnoreCase( "lunes" ) ) {
                        contadoresClases[ 0 ]++;
                        contenidoFila[ 0 ] = contenidoFila[ 0 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 0 ] = contenidoFila[ 0 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 1 ] = fila[ 1 ] + contenidoFila[ 0 ];
                    }
                    
                    if( dia.equalsIgnoreCase( "martes" ) ) {
                        contadoresClases[ 1 ]++;
                        contenidoFila[ 1 ] = contenidoFila[ 1 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 1 ] = contenidoFila[ 1 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 2 ] = fila[ 2 ] + contenidoFila[ 1 ];
                    }
                    
                    if( dia.equalsIgnoreCase( "miercoles" ) ) {
                        contadoresClases[ 3 ]++;
                        contenidoFila[ 2 ] = contenidoFila[ 2 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 2 ] = contenidoFila[ 2 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 3 ] = fila[ 3 ] + contenidoFila[ 2 ];
                    }
                    
                    if( dia.equalsIgnoreCase( "jueves" ) ) {
                        contadoresClases[ 4 ]++;
                        contenidoFila[ 3 ] = contenidoFila[ 3 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 3 ] = contenidoFila[ 3 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 4 ] = fila[ 4 ] + contenidoFila[ 3 ];
                    }
                    
                    if( dia.equalsIgnoreCase( "viernes" ) ) {
                        contadoresClases[ 5 ]++;
                        contenidoFila[ 4 ] = contenidoFila[ 4 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 4 ] = contenidoFila[ 4 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 5 ] = fila[ 5 ] + contenidoFila[ 4 ];
                    }
                    
                    if( dia.equalsIgnoreCase( "sabado" ) ) {
                        contadoresClases[ 6 ]++;
                        contenidoFila[ 5 ] = contenidoFila[ 5 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 5 ] = contenidoFila[ 5 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 6 ] = fila[ 6 ] + contenidoFila[ 5 ];
                    }
                    
                    if( dia.equalsIgnoreCase( "domingo" ) ) {
                        contadoresClases[ 7 ]++;
                        contenidoFila[ 6 ] = contenidoFila[ 4 ] + horaInicio + " a " + horaFin + "<br>";
                        contenidoFila[ 6 ] = contenidoFila[ 4 ] + "Salon " +clases[ y ][ 2 ] + "<br>";
                        fila[ 7 ] = fila[ 7 ] + contenidoFila[ 6 ];
                    }
                }
                
                for( int z = 1 ; z < 8 ; z++ ) {
                    String comienzoFila = "<html>" + fila[ z ];
                    fila[ z ] = comienzoFila;
                    fila[ z ] = fila[ z ] + "</html>";
                }
                
                for( int z = 0 ; z < 7 ; z++ ) {
                    if( contadoresClases[ z ] > numClases) {
                        numClases = contadoresClases[ z ];
                    }
                    contadoresClases[ z ] = 0;
                }
                
                if( numClases > numClasesMayor ) {
                    numClasesMayor = numClases;
                }
                modelo.addRow( fila );
            }
            tbHorario.setRowHeight( 35 * numClasesMayor);
            tbHorario.setModel( modelo );
            interfazHorario.recibeCursosYClases( tbHorario );
            
        } catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void obtenerClases( String nombreCurso, int columna, JTable tbClases, 
            JComboBox cbSalon, int numSalones ) throws Exception {
        mantenimientoClase = new MClase();
        String dia = null;
        switch( columna ) {
            case 1:
                dia = "lunes";
            break;
            case 2:
                dia = "martes";
            break;
            case 3:
                dia = "miercoles";
            break;
            case 4:
                dia = "jueves";
            break;
            case 5:
                dia = "viernes";
            break;
            case 6:
                dia = "sabado";
            break;
            case 7:
                dia = "domingo";
            break;
        }
        clasesDelCurso = mantenimientoClase.obtenerClasesPorDia( 
                nombreCurso, dia );
        int cantidadClases = clasesDelCurso.length;
        DefaultTableModel modelo = ( DefaultTableModel ) tbClases.getModel();
        String[] fila = new String[ 2 ];
        for( int x = 0 ; x < cantidadClases ; x++ ) {
            
            String horaInicio = clasesDelCurso[ x ][ 2 ];
            String horaFin = clasesDelCurso[ x ][ 3 ];
            fila[ 0 ] = horaInicio + " a " + horaFin; 
            fila[ 1 ] = "Salon " + clasesDelCurso[ x ][ 1 ]; 
            modelo.addRow( fila );
        }
        tbClases.setModel( modelo );
        interfazClases.recibeClasesActuales( tbClases );
        DefaultComboBoxModel modeloComboBox = ( DefaultComboBoxModel ) cbSalon.getModel();
        for( int x = 0 ; x  < numSalones ; x++ ) {
            int num = x + 1;
            modeloComboBox.addElement("Salon " + num );
        }
        cbSalon.setModel( modeloComboBox );
        interfazClases.recibeSalones( cbSalon );
    }
    
    public void actualizaSalones( int nuevoNumSalones, int viejoNumSalones ) throws Exception  {
        mantenimientoCurso = new MCurso();
        mantenimientoCurso.actualizaSalones( nuevoNumSalones, viejoNumSalones );
        interfazHorario.recibeSalones( nuevoNumSalones );
    }
    
    public void consultaNumSalones() throws Exception {
        mantenimientoCurso = new MCurso();
        int numeroSalones = mantenimientoCurso.obtieneSalones();
        if( numeroSalones == 0 ) {
            mantenimientoCurso.agregaSalonesPorDefault();
            numeroSalones = mantenimientoCurso.obtieneSalones();
            interfazHorario.recibeSalones( numeroSalones );
        } else {
            interfazHorario.recibeSalones( numeroSalones );
        }
    }
    
    public void agregarClaseATabla( String horaInicio, String horaFin, 
            int salon, int salonesDisponibles, JTable tbClases, int numDia, 
            String nombreDelCurso ) {
        try {
            mantenimientoClase = new MClase();
            controlValidacionClase = new CValidacionClase();
            String dia = obtenerDia( numDia );
            String[][] clases = mantenimientoClase.consultaGeneralEspecifica( dia );
            String[][] clasesConSilencio = 
                    mantenimientoClase.obtenerClasesConRequisitoDeSilencio( dia );
            salon = salon + 1;
            controlValidacionClase.validarHorarioClases(horaInicio, horaFin);
            controlValidacionClase.validarDisponibilidadClases( horaInicio, horaFin,
                    salon, clases, salonesDisponibles, clasesConSilencio );
            controlValidacionClase.valdarClasesDeTabla( horaInicio, horaFin, salon, tbClases );
            mantenimientoClase.agregarClase(nombreDelCurso, dia, salon, horaInicio, horaFin);
            actualizarClases( nombreDelCurso, numDia, tbClases, "alta" );
        }catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void quitarClaseDeTabla( JTable tbClases, String nombreDelCurso, int dia ) throws Exception {
        int opcion = JOptionPane.showConfirmDialog( null, 
                "¿Estas seguro que deseas quitar esa clase?","Borrado de clases", 
                JOptionPane.YES_NO_OPTION);
        if( opcion == 0 ) {
            mantenimientoClase = new MClase();
            int filaAEliminar = tbClases.getSelectedRow();
            String horaClase = (String) tbClases.getValueAt( filaAEliminar , 0 ) ;
            String salon = (String) tbClases.getValueAt( filaAEliminar , 1 ) ;
            int longitudClases = clasesDelCurso.length;
            String clase;
            int idClase = 0;
            for( int x = 0 ; x < longitudClases ; x++ ) {
                clase = clasesDelCurso[ x ][ 2 ] + " a " + clasesDelCurso[ x ][ 3 ];
                if( clase.equalsIgnoreCase( horaClase ) ) {
                    idClase = Integer.parseInt( clasesDelCurso[ x ][ 0 ] );
                    break;
                } 
            }
            mantenimientoClase.eliminarClase(nombreDelCurso, idClase);
            actualizarClases( nombreDelCurso, dia, tbClases, "baja" );
        }
        
    }
    
    private String obtenerDia( int dia ) {
        String diaFinal = null;
        switch( dia ) {
            case 1:
                diaFinal = "lunes";
            break;
            case 2:
                diaFinal = "martes";
            break;
            case 3:
                diaFinal = "miercoles";
            break;
            case 4:
                diaFinal = "jueves";
            break;
            case 5:
                diaFinal = "viernes";
            break;
            case 6:
                diaFinal = "sabado";
            break;
            case 7:
                diaFinal = "domingo";
            break;
        }
        return diaFinal;
    }
    
    public void actualizarClases( String nombreCurso, int dia, JTable tbClases, 
            String proveniente ) throws Exception {
        tbClases.setModel(new javax.swing.table.DefaultTableModel( new Object [][] {
            },new String [] { "Hora", "Salón" } ) { boolean[] canEdit = new boolean [] {
            false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DefaultTableModel modelo = ( DefaultTableModel ) tbClases.getModel();
        
        mantenimientoClase = new MClase();
        String diaFinal = obtenerDia( dia );
        clasesDelCurso = mantenimientoClase.obtenerClasesPorDia( 
                nombreCurso, diaFinal );
        int cantidadClases = clasesDelCurso.length;
        
        String[] fila = new String[ 2 ];
        for( int x = 0 ; x < cantidadClases ; x++ ) {
            
            String horaInicio = clasesDelCurso[ x ][ 2 ];
            String horaFin = clasesDelCurso[ x ][ 3 ];
            fila[ 0 ] = horaInicio + " a " + horaFin; 
            fila[ 1 ] = "Salon " + clasesDelCurso[ x ][ 1 ]; 
            modelo.addRow( fila );
        }
        tbClases.setModel( modelo );
        interfazClases.recibeClasesActuales( tbClases );
    }
    
    public void limpiarHorario( JTable tbHorario ) {
        tbHorario.setModel(new javax.swing.table.DefaultTableModel( new Object [][] {
            }, new String [] { "Curso", "Lunes", "Martes", "Miércoles", "Jueves",
            "Viernes", "Sábado", "Domingo" } ) { boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
    }
    
    public void obtenerProfesoresPorCurso( JTable tbProfesores, String nombre ) 
            throws SQLException {
        mantenimientoCurso = new MCurso();
        String[][] profesores = mantenimientoCurso.obtenerProfesoresPorCurso( nombre );
        if( profesores.length != 0 ) {
            DefaultTableModel modelo = ( DefaultTableModel ) tbProfesores.getModel();
            for( int x = 0 ; x < profesores.length ; x++ ) {
                String[] datosProfesor = new String[ 4 ];
                datosProfesor[ 0 ] = profesores[ x ][ 0 ];
                datosProfesor[ 1 ] = profesores[ x ][ 1 ];
                datosProfesor[ 2 ] = profesores[ x ][ 2 ];
                datosProfesor[ 3 ] = profesores[ x ][ 3 ];
                modelo.addRow( datosProfesor );
            }
            tbProfesores.setModel( modelo );
            interfazRecibeProfesores.insertarProfesoresEnTabla( tbProfesores );
        }
        
    }
    
    public void agregarProfesoresEnAsignacion( JTable tbProfesores, 
            String nombreProfesor, int numProfesor ) throws SQLException {
        try {
            controlValidacionCurso = new CValidacionCurso();
            controlValidacionCurso.validacionProfesor( numProfesor );
            int cantidadProfesores = tbProfesores.getRowCount();
            String[] profesoresDeTabla = new String[ cantidadProfesores ];
            for( int x = 0 ; x < cantidadProfesores ; x++ ) {
                profesoresDeTabla[ x ] = tbProfesores.getValueAt( x, 0 ) + " ";
                profesoresDeTabla[ x ] = profesoresDeTabla[ x ] + tbProfesores.getValueAt( x, 1 ) + " ";
                profesoresDeTabla[ x ] = profesoresDeTabla[ x ] + tbProfesores.getValueAt( x, 2 ) + " ";
                profesoresDeTabla[ x ] = profesoresDeTabla[ x ] + tbProfesores.getValueAt( x, 3 );
            }
            controlValidacionCurso.validarProfesorPorId( profesoresDeTabla, nombreProfesor);
            
            DefaultTableModel modelo = (DefaultTableModel) tbProfesores.getModel();
            String[] fila = new String[ 4 ];
            fila[ 0 ] = profesores[ numProfesor - 1 ][ 0 ];
            fila[ 1 ] = profesores[ numProfesor - 1 ][ 1 ];
            fila[ 2 ] = profesores[ numProfesor - 1 ][ 2 ];
            fila[ 3 ] = profesores[ numProfesor - 1 ][ 3 ];
            modelo.addRow( fila );
            tbProfesores.setModel( modelo );
        }catch( Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public void quitarProfesoresEnAsignacion( JTable tbProfesores, int fila  ) {
        DefaultTableModel modelo = (DefaultTableModel) tbProfesores.getModel();
        modelo.removeRow( fila );
        tbProfesores.setModel( modelo );
    }
    
    public void relacionarProfesorYCurso( JTable tbProfesores, String curso ) throws Exception {
        mantenimientoCurso = new MCurso();
        int cantidadProfesores = tbProfesores.getRowCount();
        if( cantidadProfesores != 0 ) {
            int[] idProfesores = new int[ cantidadProfesores ];
            for( int x = 0 ; x < cantidadProfesores ; x++ ) {
                idProfesores[ x ] = Integer.parseInt( ( String ) tbProfesores.getValueAt( x, 0 ) );
            }
            mantenimientoCurso.relacionarCursoYProfesor( idProfesores, curso );
            interfazCerrar.cerrarVentana();
        } else {
            mantenimientoCurso.eliminarRelacionCurso(curso);
            interfazCerrar.cerrarVentana();
        }
    }
}
