/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import Interfaces.IEdad;

/**
 *
 * @author cristian
 */
public class jpDatosAlumno extends javax.swing.JPanel implements ActionListener {

    private Integer anioSelect = 0;
    private boolean rbsi;
    private boolean rbno;
    private String mesSelect = null;
    private Integer mesSelectNum = 0;
    private Integer diaSelect = 0;
    private IEdad interfaceEdad=null;
    /**
     * Creates new form panel1
     */
    private ButtonGroup grupoSalud = new ButtonGroup();
    private ButtonGroup grupoControlada = new ButtonGroup();
    private int contador;

    public jpDatosAlumno() {
        initComponents();
        initValues();
        grupoSalud.add(rbSiProblema);
        grupoSalud.add(rbNoProblema);
        grupoControlada.add(rbSiControlado);
        grupoControlada.add(rbNoControlado);
        rbNoProblema.setSelected(true);
        rbSiProblema.setSelected(false);
        mostrarProblema();
        Acciones();

    }
    public jpDatosAlumno(IEdad interfaceEdad) {
        initComponents();
        initValues();
        grupoSalud.add(rbSiProblema);
        grupoSalud.add(rbNoProblema);
        grupoControlada.add(rbSiControlado);
        grupoControlada.add(rbNoControlado);
        rbNoProblema.setSelected(true);
        rbSiProblema.setSelected(false);
        mostrarProblema();
        Acciones();
        this.interfaceEdad=interfaceEdad;
    }

    public void mostrarProblema() {
        if (rbSiProblema.isSelected()) {
            jpTieneProblema.setVisible(true);
            rbsi = true;
            rbno = false;
        }
        if (rbNoProblema.isSelected()) {
            jpTieneProblema.setVisible(false);
            rbsi = false;
            rbno = true;
        }
        rbNoProblema.addActionListener(this);
        rbSiProblema.addActionListener(this);

    }

    public boolean getRbsi() {
        return rbsi;
    }

    public boolean getRbNo() {
        return rbno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mostrarProblema();
        if (e.getSource() == rbSiProblema) {
            jpTieneProblema.setVisible(true);
        }
        if (e.getSource() == rbNoProblema) {
            jpTieneProblema.setVisible(false);
        }

    }

    /**
     * *
     * metodo que inicializa todos los valores de los combobox
     */
    private void initValues() {
        //Obtener la fecha de hoy
        java.util.Calendar fecha = java.util.Calendar.getInstance();
        Integer diaMes = fecha.get(Calendar.DAY_OF_MONTH);
        Integer Mes = fecha.get(Calendar.MONTH);
        Integer Anio = fecha.get(Calendar.YEAR);
        String fechaLabel = diaMes.toString() + "/" + Mes.toString() + "/"
                + Anio.toString();

        //Obtener los valores para el campo de "Año"
        cbAño.removeAllItems();
        cbAño.addItem("Año");
        for (int i = 0; i < 70; i++) {
            this.cbAño.addItem(Anio.toString());
            Anio = Anio - 1;
        }
        cbAño.setSelectedIndex(0);
        Anio = fecha.get(Calendar.YEAR); //Por si acaso lo necesitara de nuevo

        //Obtener los valores temporales para el campo de "Mes"
        cbMes.removeAllItems();
        cbMes.addItem("Mes");
        cbMes.addItem("Enero");
        cbMes.addItem("Febrero");
        cbMes.addItem("Marzo");
        cbMes.addItem("Abril");
        cbMes.addItem("Mayo");
        cbMes.addItem("Junio");
        cbMes.addItem("Julio");
        cbMes.addItem("Agosto");
        cbMes.addItem("Septiembre");
        cbMes.addItem("Octubre");
        cbMes.addItem("Noviembre");
        cbMes.addItem("Diciembre");
        cbMes.setSelectedIndex(0);

        //Obtener los valores temporales para el campo de "Dia"
        cbDia.removeAllItems();
        cbDia.addItem("Dia");
        Integer i;
        for (i = 1; i <= 31; i++) {
            cbDia.addItem(i.toString());
        }
        cbDia.setSelectedIndex(0);

        //conveniencia
        cbDia.setEnabled(false);
        cbMes.setEnabled(false);

        //visibilidad
        this.setVisible(true);

    }

    public void Acciones() {
        cbAño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAñoItemStateChanged(evt);
            }
        });
        cbMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMesItemStateChanged(evt);
            }
        });
        cbDia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDiaItemStateChanged(evt);
            }
        });
    }

    private void cbAñoItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            try {
                this.anioSelect = Integer.parseInt(select);

                cbMes.setSelectedIndex(0);
                cbMes.setEnabled(true);
                this.mesSelect = null;

                cbDia.setSelectedIndex(0);
                cbDia.setEnabled(false);
                this.diaSelect = 0;

            } catch (NumberFormatException ex) {
                this.anioSelect = 0;

                cbMes.setSelectedIndex(0);
                cbMes.setEnabled(false);
                this.mesSelect = null;

                cbDia.setSelectedIndex(0);
                cbDia.setEnabled(false);
                this.diaSelect = 0;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }
    

    private void cbDiaItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            try {
                this.diaSelect = Integer.parseInt(select);
            } catch (NumberFormatException ex) {
                this.diaSelect = 0;

            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
        if (diaSelect != 0) {
            System.out.println("entre");
            valida();
        }

    }

    private void cbMesItemStateChanged(java.awt.event.ItemEvent evt) {

        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            this.mesSelectNum = cbMes.getSelectedIndex();
            switch (select) {
                case "Enero":
                case "Marzo":
                case "Mayo":
                case "Julio":
                case "Agosto":
                case "Octubre":
                case "Diciembre":
                    cbDia.removeAllItems();
                    cbDia.addItem("Dia");
                    for (int i = 1; i <= 31; i++) {
                        Integer dia = i;
                        cbDia.addItem(dia.toString());
                    }
                    cbDia.setSelectedIndex(0);
                    cbDia.setEnabled(true);
                    this.mesSelect = select;
                    this.diaSelect = 0;
                    break;

                case "Febrero":
                    cbDia.removeAllItems();
                    cbDia.addItem("Dia");
                    int feb = 28;
                    if (anioSelect % 4 == 0) {
                        feb = 29;
                    }
                    for (int i = 1; i <= feb; i++) {
                        Integer dia = i;
                        cbDia.addItem(dia.toString());
                    }
                    cbDia.setSelectedIndex(0);
                    cbDia.setEnabled(true);
                    this.mesSelect = select;
                    this.diaSelect = 0;
                    break;

                case "Abril":
                case "Junio":
                case "Septiembre":
                case "Noviembre":
                    cbDia.removeAllItems();
                    cbDia.addItem("Dia");
                    for (int i = 1; i <= 30; i++) {
                        Integer dia = i;
                        cbDia.addItem(dia.toString());
                    }
                    cbDia.setSelectedIndex(0);
                    cbDia.setEnabled(true);
                    this.mesSelect = select;
                    this.diaSelect = 0;
                    break;

                default:
                    this.mesSelect = null;
                    cbDia.setSelectedIndex(0);
                    cbDia.setEnabled(false);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }

    public void valida() {
        System.out.println("estoy validando los dias");
        if (anioSelect != 0 && mesSelect != null && diaSelect != 0) {
            //CREACION DEL STRING DE LA FECHA DE NACIMIENTO
            String Dia = diaSelect.toString();
            String Mes = mesSelect;
            String Anio = anioSelect.toString();

            //CALCULO DE LA EDAD
            java.util.Calendar fecha = java.util.Calendar.getInstance();
            //EDAD-1 PORQUE FALTA VERIFICAR CUMPLEAÑOS
            int edad = fecha.get(Calendar.YEAR) - anioSelect - 1;
            //MES+1 PORQUE EMPIEZA EN 0
            if ((fecha.get(Calendar.MONTH) + 1) >= mesSelectNum) {
                if ((fecha.get(Calendar.MONTH) + 1) == mesSelectNum) {
                    if (fecha.get(Calendar.DAY_OF_MONTH) >= diaSelect) {
                        edad = edad + 1;
                    }
                } else {
                    edad = edad + 1;
                }                
               
            }
             txEdad.setText(edad + "");
                if(interfaceEdad !=null){
                interfaceEdad.enviarEdad(edad);
                }
            //ULTIMA VERIFICACION
            if (edad < 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error, ésta persona no ha nacido aún");
            } else {

            }
        } else {
            //MENSAJE DE ERROR
            javax.swing.JOptionPane.showMessageDialog(null, "Fecha invalida. Favor de seleccionar los valores faltantes.");
        }
    }

    /**
     * Este metodo retorna una lista con todos los generales del alumno
     * nombre,apellido paterno,sexo,apellido maternom, dia mes año de nacimiento,
     * 
     * @return 
     */
    public ArrayList getAlumno() {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(txNombre.getText().toUpperCase());
        lista.add(txApellidoPaterno.getText().toUpperCase());
        lista.add(txApellidoMaterno.getText().toUpperCase());
        lista.add((String) cbSexo.getSelectedItem());
        lista.add((String) cbDia.getSelectedItem());
        lista.add((String) cbMes.getSelectedItem());
        lista.add((String) cbAño.getSelectedItem());
        lista.add(txEdad.getText());
        lista.add(txEstado.getText().toUpperCase());
        lista.add(txCiudad.getText().toUpperCase());
        lista.add(txCalle.getText().toUpperCase());
        lista.add(txColonia.getText().toUpperCase());
        lista.add(txNumero.getText());
        lista.add(txProfesion.getText());
        lista.add(txEmail.getText());
        lista.add(txFacebook.getText());
        lista.add(txTelefonoCasa.getText());
        lista.add(txTelefonoCelular.getText());
        
        return lista;
    }
   public ArrayList getAlumnoNombre(){
       ArrayList alNombre = new ArrayList();
       alNombre.add("nombre");
       alNombre.add("apellido paterno");
       alNombre.add("apellido materno");
       alNombre.add("sexo");
       alNombre.add("dia de acimiento del alumno");
       alNombre.add("mes de nacimiento del alumno");
       alNombre.add("año de nacimiento del alumno");
       alNombre.add("edad");
       alNombre.add("estado");
       alNombre.add("ciudad");
       alNombre.add("calle");
       alNombre.add("colonia");
       alNombre.add("numero");
       alNombre.add("profesion");
       alNombre.add("correo electronico");
       alNombre.add("facebook");
       alNombre.add("telefono casa");
       alNombre.add("telefono celular");
       
      return alNombre; 
   }
   public ArrayList getAlumnoTipo(){
       ArrayList alTipo = new ArrayList();
       alTipo.add("texto");//nombre
       alTipo.add("texto");//apellido paterno
       alTipo.add("texto");//apellido materno
       alTipo.add("combo");// sexo
       alTipo.add("combo");//dia nac
       alTipo.add("combo");//mes nac
       alTipo.add("combo");//año nac
       alTipo.add("edad");//edad
       alTipo.add("texto");//estado
       alTipo.add("texto");//ciudad
       alTipo.add("no");//calle
       alTipo.add("texto");//colonia
       alTipo.add("numero");//numero
       alTipo.add("texto");//profesion
       alTipo.add("Noobligatorio");//correo electronico
       alTipo.add("Noobligatorio");//facebook
       alTipo.add("telefono");//telefono casa
       alTipo.add("telefono");//telefono celular
       return alTipo;
   }
   public ArrayList getSalud(){
       ArrayList lista= new ArrayList();
       lista.add(txCual.getText());
       if(rbSiControlado.isSelected()){
           lista.add("SI");
       }else{
          lista.add("NO"); 
       }
       return lista;
   }
   public ArrayList getSaludNombre(){
       ArrayList alNombre = new ArrayList();
       alNombre.add("Problema de salud");
       alNombre.add("Controlado");
       return alNombre;
   }
   public ArrayList getSaludTipo(){
       ArrayList alTipo = new ArrayList();
       alTipo.add("texto");
       alTipo.add("no");
       return alTipo;
   }

    public void CargarAlumno(ArrayList datos) {
        
        txMatricula.setText((String) (datos.get(0)));
        txNombre.setText((String) (datos.get(1)));
        txApellidoPaterno.setText((String) (datos.get(2)));
        txApellidoMaterno.setText((String) (datos.get(3)));
        String sexo = ((String) (datos.get(4)));
        cbSexo.setSelectedIndex(metodoCombo(sexo, cbSexo));
        
        String año = (String) datos.get(5);
        cbAño.setSelectedIndex(metodoCombo(año, cbAño));
        String mes = (String) datos.get(6);
        cbMes.setSelectedIndex(metodoCombo(mes, cbMes));
        String dia = (String) datos.get(7);
        cbDia.setSelectedIndex(metodoCombo(dia, cbDia));
        
        txEstado.setText((String) (datos.get(8)));
        txCiudad.setText((String) (datos.get(9)));
        txCalle.setText((String) (datos.get(10)));
        txColonia.setText((String) (datos.get(11)));
        txNumero.setText((String) (datos.get(12)));
        txProfesion.setText((String) (datos.get(13)));
        txEmail.setText((String) (datos.get(14)));
        txFacebook.setText((String) (datos.get(15)));
        txTelefonoCasa.setText((String) (datos.get(16)));
        txTelefonoCelular.setText((String) (datos.get(17)));
    }

    /***
     * Este metodo  busca la cadena en el comboBox y retorna su posicion, en 
     * caso de no ser encontrada se regresara un -1
     * @param cadena
     * @param box
     * @return 
     */
    public int metodoCombo(String cadena, JComboBox box) {
        int retorno = -1;
        int index = box.getItemCount();
        //    System.out.println(index);
        for (int i = 0; i < index; i++) {
            String o1 = box.getItemAt(i).toString();

            if (o1.compareTo(cadena) == 0) {
                retorno = i;
                //        System.out.println(retorno);
            }
        }
        return retorno;
    }

    public void cargarSalud(ArrayList datos) {
        String estado = (String) datos.get(0);
        String controlado = (String) datos.get(1);
        txCual.setText(estado);
        if (controlado.equals("SI")) {
            rbSiControlado.setSelected(true);
        } else {
            rbNoControlado.setSelected(true);
        }
    }

    /***
     * Este metodo hace que todos los componentes del panel sean o no accesibles
     * dependiendo de el parametro que se envie
     * @param modif 
     */
    public void Modificable(boolean modif) {
        txNombre.setEditable(modif);
        txApellidoPaterno.setEditable(modif);
        txApellidoMaterno.setEditable(modif);
        cbAño.setEnabled(modif);
        cbMes.setEnabled(modif);
        cbDia.setEnabled(modif);
        txEdad.setEditable(modif);
        txCiudad.setEditable(modif);
        txEstado.setEditable(modif);
        txCalle.setEditable(modif);
        txColonia.setEditable(modif);
        txNumero.setEditable(modif);
        txProfesion.setEditable(modif);
        txEmail.setEditable(modif);
        txFacebook.setEditable(modif);
        txTelefonoCasa.setEditable(modif);
        txTelefonoCelular.setEditable(modif);
        rbSiProblema.setEnabled(modif);
        rbNoProblema.setEnabled(modif);
        txCual.setEditable(modif);
        rbSiControlado.setEnabled(modif);
        rbNoControlado.setEnabled(modif);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpProblema = new javax.swing.JPanel();
        rbSiProblema = new javax.swing.JRadioButton();
        rbNoProblema = new javax.swing.JRadioButton();
        jpTieneProblema = new javax.swing.JPanel();
        rbNoControlado = new javax.swing.JRadioButton();
        rbSiControlado = new javax.swing.JRadioButton();
        etCual = new javax.swing.JLabel();
        etControlada = new javax.swing.JLabel();
        txCual = new javax.swing.JTextField();
        jpContacto = new javax.swing.JPanel();
        etEmail = new javax.swing.JLabel();
        txEmail = new javax.swing.JTextField();
        etFacebook = new javax.swing.JLabel();
        txFacebook = new javax.swing.JTextField();
        txTelefonoCasa = new javax.swing.JTextField();
        etTelefonoCasa = new javax.swing.JLabel();
        txTelefonoCelular = new javax.swing.JTextField();
        etTelefonoCelular = new javax.swing.JLabel();
        jpDireccion = new javax.swing.JPanel();
        etCalle = new javax.swing.JLabel();
        etColonia = new javax.swing.JLabel();
        txCalle = new javax.swing.JTextField();
        txColonia = new javax.swing.JTextField();
        txNumero = new javax.swing.JTextField();
        etNumero = new javax.swing.JLabel();
        etProfesion = new javax.swing.JLabel();
        txProfesion = new javax.swing.JTextField();
        jpLugarNac = new javax.swing.JPanel();
        etCiudad = new javax.swing.JLabel();
        etEstado = new javax.swing.JLabel();
        txCiudad = new javax.swing.JTextField();
        txEstado = new javax.swing.JTextField();
        jpFechaDeNac = new javax.swing.JPanel();
        cbDia = new javax.swing.JComboBox();
        cbMes = new javax.swing.JComboBox();
        cbAño = new javax.swing.JComboBox();
        txEdad = new javax.swing.JTextField();
        etEdad = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txNombre = new javax.swing.JTextField();
        etMatricula = new javax.swing.JLabel();
        etApellidoPaterno = new javax.swing.JLabel();
        txApellidoPaterno = new javax.swing.JTextField();
        txApellidoMaterno = new javax.swing.JTextField();
        etApellidoMaterno = new javax.swing.JLabel();
        etNombre = new javax.swing.JLabel();
        txMatricula = new javax.swing.JTextField();
        cbSexo = new javax.swing.JComboBox();
        etSexo = new javax.swing.JLabel();

        setLayout(null);

        jpProblema.setBorder(javax.swing.BorderFactory.createTitledBorder("¿Ha tenido problemas de salud?"));
        jpProblema.setLayout(null);

        rbSiProblema.setText("Si");
        rbSiProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSiProblemaActionPerformed(evt);
            }
        });
        jpProblema.add(rbSiProblema);
        rbSiProblema.setBounds(12, 20, 50, 23);

        rbNoProblema.setSelected(true);
        rbNoProblema.setText("No");
        rbNoProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoProblemaActionPerformed(evt);
            }
        });
        jpProblema.add(rbNoProblema);
        rbNoProblema.setBounds(90, 20, 50, 23);

        jpTieneProblema.setLayout(null);

        rbNoControlado.setText("NO");
        jpTieneProblema.add(rbNoControlado);
        rbNoControlado.setBounds(280, 30, 50, 23);

        rbSiControlado.setSelected(true);
        rbSiControlado.setText("SI");
        jpTieneProblema.add(rbSiControlado);
        rbSiControlado.setBounds(220, 30, 50, 23);

        etCual.setText("¿Cuál? ");
        jpTieneProblema.add(etCual);
        etCual.setBounds(10, 0, 60, 14);

        etControlada.setText("¿La tiene controlada? ");
        jpTieneProblema.add(etControlada);
        etControlada.setBounds(220, 0, 150, 14);
        jpTieneProblema.add(txCual);
        txCual.setBounds(10, 30, 140, 25);

        jpProblema.add(jpTieneProblema);
        jpTieneProblema.setBounds(270, 20, 370, 60);

        add(jpProblema);
        jpProblema.setBounds(10, 360, 660, 100);

        jpContacto.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacto"));
        jpContacto.setLayout(null);

        etEmail.setText("Correo electrónico ");
        jpContacto.add(etEmail);
        etEmail.setBounds(10, 20, 130, 14);

        txEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEmailActionPerformed(evt);
            }
        });
        jpContacto.add(txEmail);
        txEmail.setBounds(10, 40, 160, 27);

        etFacebook.setText("Facebook");
        jpContacto.add(etFacebook);
        etFacebook.setBounds(180, 20, 90, 14);

        txFacebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFacebookActionPerformed(evt);
            }
        });
        jpContacto.add(txFacebook);
        txFacebook.setBounds(180, 40, 133, 27);

        txTelefonoCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTelefonoCasaActionPerformed(evt);
            }
        });
        jpContacto.add(txTelefonoCasa);
        txTelefonoCasa.setBounds(330, 40, 130, 27);

        etTelefonoCasa.setText("Teléfono de casa");
        jpContacto.add(etTelefonoCasa);
        etTelefonoCasa.setBounds(330, 20, 110, 14);

        txTelefonoCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTelefonoCelularActionPerformed(evt);
            }
        });
        jpContacto.add(txTelefonoCelular);
        txTelefonoCelular.setBounds(470, 40, 130, 27);

        etTelefonoCelular.setText("Teléfono  celular");
        jpContacto.add(etTelefonoCelular);
        etTelefonoCelular.setBounds(470, 20, 110, 14);

        add(jpContacto);
        jpContacto.setBounds(10, 260, 650, 80);

        jpDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder("Dirección "));
        jpDireccion.setLayout(null);

        etCalle.setText("Calle ");
        jpDireccion.add(etCalle);
        etCalle.setBounds(10, 20, 50, 14);

        etColonia.setText("colonia ");
        jpDireccion.add(etColonia);
        etColonia.setBounds(140, 20, 50, 14);
        jpDireccion.add(txCalle);
        txCalle.setBounds(10, 40, 110, 27);
        jpDireccion.add(txColonia);
        txColonia.setBounds(140, 40, 110, 27);
        jpDireccion.add(txNumero);
        txNumero.setBounds(270, 40, 90, 27);

        etNumero.setText("Número ");
        jpDireccion.add(etNumero);
        etNumero.setBounds(270, 20, 50, 14);

        add(jpDireccion);
        jpDireccion.setBounds(10, 180, 400, 75);

        etProfesion.setText("Profesión ");
        add(etProfesion);
        etProfesion.setBounds(440, 190, 70, 20);
        add(txProfesion);
        txProfesion.setBounds(440, 220, 140, 27);

        jpLugarNac.setBorder(javax.swing.BorderFactory.createTitledBorder("Lugar de nacimiento "));
        jpLugarNac.setLayout(null);

        etCiudad.setText("Ciudad");
        jpLugarNac.add(etCiudad);
        etCiudad.setBounds(150, 20, 70, 14);

        etEstado.setText("Estado");
        jpLugarNac.add(etEstado);
        etEstado.setBounds(20, 20, 80, 14);
        jpLugarNac.add(txCiudad);
        txCiudad.setBounds(140, 40, 110, 27);
        jpLugarNac.add(txEstado);
        txEstado.setBounds(10, 40, 110, 27);

        add(jpLugarNac);
        jpLugarNac.setBounds(400, 100, 260, 75);

        jpFechaDeNac.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de nacimiento  "));
        jpFechaDeNac.setLayout(null);

        cbDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dia", " " }));
        jpFechaDeNac.add(cbDia);
        cbDia.setBounds(200, 30, 80, 25);

        cbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mes" }));
        cbMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMesActionPerformed(evt);
            }
        });
        jpFechaDeNac.add(cbMes);
        cbMes.setBounds(110, 30, 80, 25);

        cbAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "año" }));
        jpFechaDeNac.add(cbAño);
        cbAño.setBounds(10, 30, 80, 25);

        txEdad.setEditable(false);
        txEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEdadActionPerformed(evt);
            }
        });
        jpFechaDeNac.add(txEdad);
        txEdad.setBounds(300, 30, 50, 27);

        etEdad.setText("Edad");
        jpFechaDeNac.add(etEdad);
        etEdad.setBounds(310, 10, 60, 30);

        add(jpFechaDeNac);
        jpFechaDeNac.setBounds(10, 100, 380, 75);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del alumno"));
        jPanel7.setToolTipText("");
        jPanel7.setLayout(null);

        txNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombreActionPerformed(evt);
            }
        });
        jPanel7.add(txNombre);
        txNombre.setBounds(90, 40, 150, 27);

        etMatricula.setText("Matricula");
        etMatricula.setName("lanombre"); // NOI18N
        jPanel7.add(etMatricula);
        etMatricula.setBounds(10, 20, 70, 14);

        etApellidoPaterno.setText("Apellido Paterno");
        etApellidoPaterno.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel7.add(etApellidoPaterno);
        etApellidoPaterno.setBounds(270, 20, 100, 14);

        txApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoPaternoActionPerformed(evt);
            }
        });
        jPanel7.add(txApellidoPaterno);
        txApellidoPaterno.setBounds(270, 40, 100, 27);

        txApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoMaternoActionPerformed(evt);
            }
        });
        jPanel7.add(txApellidoMaterno);
        txApellidoMaterno.setBounds(400, 40, 100, 27);

        etApellidoMaterno.setText("Apellido Materno");
        jPanel7.add(etApellidoMaterno);
        etApellidoMaterno.setBounds(400, 20, 110, 14);

        etNombre.setText("Nombre(s)");
        etNombre.setName("lanombre"); // NOI18N
        jPanel7.add(etNombre);
        etNombre.setBounds(90, 20, 100, 14);

        txMatricula.setEditable(false);
        jPanel7.add(txMatricula);
        txMatricula.setBounds(10, 40, 50, 25);

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sexo", "Femenino", "Masculino" }));
        cbSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexoActionPerformed(evt);
            }
        });
        jPanel7.add(cbSexo);
        cbSexo.setBounds(530, 40, 100, 25);

        etSexo.setText("Sexo");
        jPanel7.add(etSexo);
        etSexo.setBounds(530, 20, 50, 17);

        add(jPanel7);
        jPanel7.setBounds(10, 10, 650, 80);
    }// </editor-fold>//GEN-END:initComponents

    private void txEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txEmailActionPerformed

    private void txFacebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFacebookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFacebookActionPerformed

    private void txTelefonoCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTelefonoCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTelefonoCasaActionPerformed

    private void txTelefonoCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTelefonoCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTelefonoCelularActionPerformed

    private void txEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txEdadActionPerformed

    private void txNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNombreActionPerformed

    private void txApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoPaternoActionPerformed

    private void txApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoMaternoActionPerformed

    private void cbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSexoActionPerformed

    private void cbMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMesActionPerformed

    private void rbNoProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoProblemaActionPerformed
        rbsi = false;
        rbno = true;
    }//GEN-LAST:event_rbNoProblemaActionPerformed

    private void rbSiProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSiProblemaActionPerformed
        rbsi = true;
        rbno = false;
    }//GEN-LAST:event_rbSiProblemaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbAño;
    private javax.swing.JComboBox cbDia;
    private javax.swing.JComboBox cbMes;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JLabel etApellidoMaterno;
    private javax.swing.JLabel etApellidoPaterno;
    private javax.swing.JLabel etCalle;
    private javax.swing.JLabel etCiudad;
    private javax.swing.JLabel etColonia;
    private javax.swing.JLabel etControlada;
    private javax.swing.JLabel etCual;
    private javax.swing.JLabel etEdad;
    private javax.swing.JLabel etEmail;
    private javax.swing.JLabel etEstado;
    private javax.swing.JLabel etFacebook;
    private javax.swing.JLabel etMatricula;
    private javax.swing.JLabel etNombre;
    private javax.swing.JLabel etNumero;
    private javax.swing.JLabel etProfesion;
    private javax.swing.JLabel etSexo;
    private javax.swing.JLabel etTelefonoCasa;
    private javax.swing.JLabel etTelefonoCelular;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jpContacto;
    private javax.swing.JPanel jpDireccion;
    private javax.swing.JPanel jpFechaDeNac;
    private javax.swing.JPanel jpLugarNac;
    private javax.swing.JPanel jpProblema;
    private javax.swing.JPanel jpTieneProblema;
    private javax.swing.JRadioButton rbNoControlado;
    private javax.swing.JRadioButton rbNoProblema;
    private javax.swing.JRadioButton rbSiControlado;
    private javax.swing.JRadioButton rbSiProblema;
    private javax.swing.JTextField txApellidoMaterno;
    private javax.swing.JTextField txApellidoPaterno;
    private javax.swing.JTextField txCalle;
    private javax.swing.JTextField txCiudad;
    private javax.swing.JTextField txColonia;
    private javax.swing.JTextField txCual;
    private javax.swing.JTextField txEdad;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txFacebook;
    private javax.swing.JTextField txMatricula;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNumero;
    private javax.swing.JTextField txProfesion;
    private javax.swing.JTextField txTelefonoCasa;
    private javax.swing.JTextField txTelefonoCelular;
    // End of variables declaration//GEN-END:variables

    public Integer getAnioSelect() {
        return anioSelect;
    }

    public boolean isRbsi() {
        return rbsi;
    }

    public boolean isRbno() {
        return rbno;
    }

    public String getMesSelect() {
        return mesSelect;
    }

    public Integer getMesSelectNum() {
        return mesSelectNum;
    }

    public Integer getDiaSelect() {
        return diaSelect;
    }


    public ButtonGroup getGrupoSalud() {
        return grupoSalud;
    }

    public ButtonGroup getGrupoControlada() {
        return grupoControlada;
    }

    public int getContador() {
        return contador;
    }

    public JComboBox getCbAño() {
        return cbAño;
    }

    public JComboBox getCbDia() {
        return cbDia;
    }

    public JComboBox getCbMes() {
        return cbMes;
    }

    public JComboBox getCbSexo() {
        return cbSexo;
    }
    public JPanel getJpContacto() {
        return jpContacto;
    }

    public JPanel getJpDireccion() {
        return jpDireccion;
    }

    public JPanel getJpFechaDeNac() {
        return jpFechaDeNac;
    }

    public JPanel getJpLugarNac() {
        return jpLugarNac;
    }

    public JPanel getJpProblema() {
        return jpProblema;
    }

    public JPanel getJpTieneProblema() {
        return jpTieneProblema;
    }

    public JRadioButton getRbNoControlado() {
        return rbNoControlado;
    }

    public JRadioButton getRbNoProblema() {
        return rbNoProblema;
    }

    public JRadioButton getRbSiControlado() {
        return rbSiControlado;
    }

    public JRadioButton getRbSiProblema() {
        return rbSiProblema;
    }

    public JTextField getTxApellidoMaterno() {
        return txApellidoMaterno;
    }

    public JTextField getTxApellidoPaterno() {
        return txApellidoPaterno;
    }

    public JTextField getTxCalle() {
        return txCalle;
    }

    public JTextField getTxCiudad() {
        return txCiudad;
    }

    public JTextField getTxColonia() {
        return txColonia;
    }

    public JTextField getTxCual() {
        return txCual;
    }

    public JTextField getTxEdad() {
        return txEdad;
    }

    public JTextField getTxEmail() {
        return txEmail;
    }

    public JTextField getTxEstado() {
        return txEstado;
    }

    public JTextField getTxFacebook() {
        return txFacebook;
    }

    public JTextField getTxMatricula() {
        return txMatricula;
    }

    public JTextField getTxNombre() {
        return txNombre;
    }

    public JTextField getTxNumero() {
        return txNumero;
    }

    public JTextField getTxProfesion() {
        return txProfesion;
    }

    public JTextField getTxTelefonoCasa() {
        return txTelefonoCasa;
    }

    public JTextField getTxTelefonoCelular() {
        return txTelefonoCelular;
    }

   

}
