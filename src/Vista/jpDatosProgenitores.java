/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cristian
 */
public class jpDatosProgenitores extends javax.swing.JPanel {

    /**
     * Creates new form PanelDatos_progenitores
     */
    private Integer anioSelectMa = 0;
    private String mesSelectMa = null;
    private Integer mesSelectNumMa = 0;
    private Integer diaSelectMa = 0;

    private Integer anioSelectPa = 0;
    private String mesSelectPa = null;
    private Integer mesSelectNumPa = 0;
    private Integer diaSelectPa = 0;

    public jpDatosProgenitores() {
        initComponents();
        initValues(cbAñoMadre, cbMesMadre, cbDiaMadre);
        initValues(cbAñoPadre, cbMesPadre, cbDiaPadre);
        Acciones();

    }

    public void Acciones() {
        cbAñoMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAñoMaItemStateChanged(evt);
            }
        });
        cbMesMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMesMaItemStateChanged(evt);
            }
        });
        cbDiaMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDiaMaItemStateChanged(evt);
            }
        });
        cbAñoPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAñoPaItemStateChanged(evt);
            }
        });
        cbMesPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMesPaItemStateChanged(evt);
            }
        });
        cbDiaPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDiaPaItemStateChanged(evt);
            }
        });
    }

    private void cbAñoMaItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            try {
                this.anioSelectMa = Integer.parseInt(select);

                cbMesMadre.setSelectedIndex(0);
                cbMesMadre.setEnabled(true);
                this.mesSelectMa = null;

                cbDiaMadre.setSelectedIndex(0);
                cbDiaMadre.setEnabled(false);
                this.diaSelectMa = 0;

            } catch (NumberFormatException ex) {
                this.anioSelectMa = 0;

                cbMesMadre.setSelectedIndex(0);
                cbMesMadre.setEnabled(false);
                this.mesSelectMa = null;

                cbDiaMadre.setSelectedIndex(0);
                cbDiaMadre.setEnabled(false);
                this.diaSelectMa = 0;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }

    private void initValues(JComboBox cbAño, JComboBox cbMes, JComboBox cbDia) {
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
            cbAño.addItem(Anio.toString());
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

    private void cbDiaMaItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            try {
                this.diaSelectMa = Integer.parseInt(select);
            } catch (NumberFormatException ex) {
                this.diaSelectMa = 0;

            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
        if (diaSelectMa != 0) {
            validaMadre();
        }

    }

    private void cbMesMaItemStateChanged(java.awt.event.ItemEvent evt) {

        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            this.mesSelectNumMa = cbMesMadre.getSelectedIndex();
            switch (select) {
                case "Enero":
                case "Marzo":
                case "Mayo":
                case "Julio":
                case "Agosto":
                case "Octubre":
                case "Diciembre":
                    cbDiaMadre.removeAllItems();
                    cbDiaMadre.addItem("Dia");
                    for (int i = 1; i <= 31; i++) {
                        Integer dia = i;
                        cbDiaMadre.addItem(dia.toString());
                    }
                    cbDiaMadre.setSelectedIndex(0);
                    cbDiaMadre.setEnabled(true);
                    this.mesSelectMa = select;
                    this.diaSelectMa = 0;
                    break;

                case "Febrero":
                    cbDiaMadre.removeAllItems();
                    cbDiaMadre.addItem("Dia");
                    int feb = 28;
                    if (anioSelectMa % 4 == 0) {
                        feb = 29;
                    }
                    for (int i = 1; i <= feb; i++) {
                        Integer dia = i;
                        cbDiaMadre.addItem(dia.toString());
                    }
                    cbDiaMadre.setSelectedIndex(0);
                    cbDiaMadre.setEnabled(true);
                    this.mesSelectMa = select;
                    this.diaSelectMa = 0;
                    break;

                case "Abril":
                case "Junio":
                case "Septiembre":
                case "Noviembre":
                    cbDiaMadre.removeAllItems();
                    cbDiaMadre.addItem("Dia");
                    for (int i = 1; i <= 30; i++) {
                        Integer dia = i;
                        cbDiaMadre.addItem(dia.toString());
                    }
                    cbDiaMadre.setSelectedIndex(0);
                    cbDiaMadre.setEnabled(true);
                    this.mesSelectMa = select;
                    this.diaSelectMa = 0;
                    break;

                default:
                    this.mesSelectMa = null;
                    cbDiaMadre.setSelectedIndex(0);
                    cbDiaMadre.setEnabled(false);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }

    private void cbAñoPaItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        System.out.println("PAPA");
        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            try {
                System.out.println("entre a los meses");
                this.anioSelectPa = Integer.parseInt(select);

                cbMesPadre.setSelectedIndex(0);
                cbMesPadre.setEnabled(true);
                this.mesSelectPa = null;

                cbDiaPadre.setSelectedIndex(0);
                cbDiaPadre.setEnabled(false);
                this.diaSelectPa = 0;

            } catch (NumberFormatException ex) {
                this.anioSelectPa = 0;

                cbMesPadre.setSelectedIndex(0);
                cbMesPadre.setEnabled(false);
                this.mesSelectPa = null;

                cbDiaPadre.setSelectedIndex(0);
                cbDiaPadre.setEnabled(false);
                this.diaSelectPa = 0;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }

    private void cbMesPaItemStateChanged(java.awt.event.ItemEvent evt) {

        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            this.mesSelectNumPa = cbMesPadre.getSelectedIndex();
            switch (select) {
                case "Enero":
                case "Marzo":
                case "Mayo":
                case "Julio":
                case "Agosto":
                case "Octubre":
                case "Diciembre":
                    cbDiaPadre.removeAllItems();
                    cbDiaPadre.addItem("Dia");
                    for (int i = 1; i <= 31; i++) {
                        Integer dia = i;
                        cbDiaPadre.addItem(dia.toString());
                    }
                    cbDiaPadre.setSelectedIndex(0);
                    cbDiaPadre.setEnabled(true);
                    this.mesSelectPa = select;
                    this.diaSelectPa = 0;
                    break;

                case "Febrero":
                    cbDiaPadre.removeAllItems();
                    cbDiaPadre.addItem("Dia");
                    int feb = 28;
                    if (anioSelectPa % 4 == 0) {
                        feb = 29;
                    }
                    for (int i = 1; i <= feb; i++) {
                        Integer dia = i;
                        cbDiaPadre.addItem(dia.toString());
                    }
                    cbDiaPadre.setSelectedIndex(0);
                    cbDiaPadre.setEnabled(true);
                    this.mesSelectPa = select;
                    this.diaSelectPa = 0;
                    break;

                case "Abril":
                case "Junio":
                case "Septiembre":
                case "Noviembre":
                    cbDiaPadre.removeAllItems();
                    cbDiaPadre.addItem("Dia");
                    for (int i = 1; i <= 30; i++) {
                        Integer dia = i;
                        cbDiaPadre.addItem(dia.toString());
                    }
                    cbDiaPadre.setSelectedIndex(0);
                    cbDiaPadre.setEnabled(true);
                    this.mesSelectPa = select;
                    this.diaSelectPa = 0;
                    break;

                default:
                    this.mesSelectPa = null;
                    cbDiaPadre.setSelectedIndex(0);
                    cbDiaPadre.setEnabled(false);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }

    private void cbDiaPaItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        try {
            Object[] obj = evt.getItemSelectable().getSelectedObjects();
            String select = obj[0].toString();
            try {
                this.diaSelectPa = Integer.parseInt(select);
            } catch (NumberFormatException ex) {
                this.diaSelectPa = 0;

            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
        if (diaSelectPa != 0) {
            validaPadre();
        }

    }

    public void validaMadre() {
        if (anioSelectMa != 0 && mesSelectMa != null && diaSelectMa != 0) {
            //CREACION DEL STRING DE LA FECHA DE NACIMIENTO
            String Dia = diaSelectMa.toString();
            String Mes = mesSelectMa;
            String Anio = anioSelectMa.toString();
            System.out.println("Dia" + Dia + "Mes " + Mes + " anio=" + Anio);
            //CALCULO DE LA EDAD
            java.util.Calendar fecha = java.util.Calendar.getInstance();
            //EDAD-1 PORQUE FALTA VERIFICAR CUMPLEAÑOS
            int edad = fecha.get(Calendar.YEAR) - anioSelectMa - 1;
            //MES+1 PORQUE EMPIEZA EN 0
            if ((fecha.get(Calendar.MONTH) + 1) >= mesSelectNumMa) {
                if ((fecha.get(Calendar.MONTH) + 1) == mesSelectNumMa) {
                    if (fecha.get(Calendar.DAY_OF_MONTH) >= diaSelectMa) {
                        edad = edad + 1;
                    }
                } else {
                    edad = edad + 1;
                }
                txEdadMadre.setText(edad + "");
            }
             txEdadMadre.setText(edad + "");
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

    public void validaPadre() {
        if (anioSelectPa != 0 && mesSelectPa != null && diaSelectPa != 0) {
            //CREACION DEL STRING DE LA FECHA DE NACIMIENTO
            String Dia = diaSelectPa.toString();
            String Mes = mesSelectPa;
            String Anio = anioSelectPa.toString();

            //CALCULO DE LA EDAD
            java.util.Calendar fecha = java.util.Calendar.getInstance();
            //EDAD-1 PORQUE FALTA VERIFICAR CUMPLEAÑOS
            int edad = fecha.get(Calendar.YEAR) - anioSelectPa - 1;
            //MES+1 PORQUE EMPIEZA EN 0
            if ((fecha.get(Calendar.MONTH) + 1) >= mesSelectNumPa) {
                if ((fecha.get(Calendar.MONTH) + 1) == mesSelectNumPa) {
                    if (fecha.get(Calendar.DAY_OF_MONTH) >= diaSelectPa) {
                        edad = edad + 1;
                    }
                } else {
                    edad = edad + 1;
                }
                txEdadPadre.setText(edad + "");
            }
            txEdadPadre.setText(edad + "");
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

    public ArrayList getMadre(){
        ArrayList lista = new ArrayList();
        lista.add(txNombreMadre.getText().toUpperCase());
        lista.add(txApellidoPaternoMadre.getText().toUpperCase());
        lista.add(txApellidoMaternoMadre.getText().toUpperCase());
        lista.add((String) cbDiaMadre.getSelectedItem());
        lista.add((String) cbMesMadre.getSelectedItem());
        lista.add((String) cbAñoMadre.getSelectedItem());
        lista.add(txProfesionMadre.getText());
        lista.add(txTelefonoCelularMadre.getText());
        lista.add(txPersonaConfianzaMadre.getText());
        lista.add("MADRE");
        return lista;
    }
    public ArrayList getPadre(){
        ArrayList lista = new ArrayList();
        lista.add(txNombrePadre.getText().toUpperCase());
        lista.add(txApellidoPaternoPadre.getText().toUpperCase());
        lista.add(txApellidoMaternoPadre.getText().toUpperCase());
        lista.add((String) cbDiaPadre.getSelectedItem());
        lista.add((String) cbMesPadre.getSelectedItem());
        lista.add((String) cbAñoPadre.getSelectedItem());
        lista.add(txProfesionPadre.getText());
        lista.add(txTelefonoCelularPadre.getText());
        lista.add(txPersonaConfianzaPadre.getText());
        lista.add("PADRE");
        return lista;
    }
    
    public ArrayList getProgenitorNombre() {

        ArrayList alTexto = new ArrayList();
        alTexto.add("nombre");
        alTexto.add("apellido paterno");
        alTexto.add("apellido materno");
        alTexto.add("dia nacimiento");
        alTexto.add("mes nacimiento");
        alTexto.add("año nacimiento");
        alTexto.add("profesion");
        alTexto.add("telefono celular");
        alTexto.add("perona de confianza");
        alTexto.add("tipo");
        return alTexto;                               
    }
    public ArrayList getProgenitorTipo(){
        ArrayList alTipo = new ArrayList();
        alTipo.add("texto");//nombre
        alTipo.add("texto");//apellido paterno
        alTipo.add("texto");//apellido materno
        alTipo.add("combo");//dia nacimiento
        alTipo.add("combo");//mes nacimiento
        alTipo.add("combo");//año de nacimiento
        alTipo.add("texto");//profesion
        alTipo.add("telefono");//telefono celular
        alTipo.add("texto");//persona de confianza
        alTipo.add("no");//tipo
        return alTipo;
    }
    public ArrayList getEscolaridad(){
        ArrayList lista = new ArrayList();
        lista.add(txEscuela.getText());
        lista.add(txGrado.getText());
        return lista;
    }
    public ArrayList getEscolaridadNombre(){
        ArrayList alNombre = new ArrayList();
        alNombre.add("escuela");
        alNombre.add("Grado");   
        return alNombre;
    }
    public ArrayList getEscolaridadTipo(){
        ArrayList alTipo = new ArrayList();
        alTipo.add("Noobligatorio");//Escuela
        alTipo.add("Noobligatorio");//Escolaridad
        return alTipo;
    }
    /**
     * *
     * Este metodo cargar todos los componentes con los datos ingresardo
     *
     * @param datos
     */
    public void cargarDatosMadre(ArrayList datos) {
        txNombreMadre.setText((String) (datos.get(0)));
        txApellidoPaternoMadre.setText((String) (datos.get(1)));
        txApellidoMaternoMadre.setText((String) (datos.get(2)));
        String año = (String) datos.get(3);
        int a = metodoCombo(año, cbAñoMadre);
        cbAñoMadre.setSelectedIndex(a);
        String mes = (String) datos.get(4);
        int m = metodoCombo(mes, cbMesMadre);
        cbMesMadre.setSelectedIndex(m);
        String dia = (String) datos.get(5);
        cbDiaMadre.setSelectedIndex(metodoCombo(dia, cbDiaMadre));
        txProfesionMadre.setText((String) (datos.get(6)));
        txTelefonoCelularMadre.setText((String) (datos.get(7)));
        txPersonaConfianzaMadre.setText((String) (datos.get(8)));
    }

    public void cargarDatosPadre(ArrayList datos) {
        System.out.println("-------------------------------------------------");
        txNombrePadre.setText((String) (datos.get(0)));
        txApellidoPaternoPadre.setText((String) (datos.get(1)));
        txApellidoMaternoPadre.setText((String) (datos.get(2)));
        String año = (String) datos.get(3);
        cbAñoPadre.setSelectedIndex(metodoCombo(año, cbAñoPadre));
        String mes = (String) datos.get(4);
        int m = metodoCombo(mes, cbMesPadre);
        cbMesPadre.setSelectedIndex(metodoCombo(mes, cbMesPadre));
        String dia = (String) datos.get(5);
        cbDiaPadre.setSelectedIndex(metodoCombo(dia, cbDiaPadre));

        txProfesionPadre.setText((String) (datos.get(6)));
        txTelefonoCelularPadre.setText((String) (datos.get(7)));
        txPersonaConfianzaPadre.setText((String) (datos.get(8)));
        System.out.println("A" + datos.get(3) + "posicion" + metodoCombo(año, cbAñoPadre)
                + "Mes=" + datos.get(4) + "posicion" + metodoCombo(mes, cbMesPadre)
                + "Dia=" + datos.get(5) + "posicion" + metodoCombo(dia, cbDiaPadre));
        System.out.println("-------------------------------------------------");
    }

    public void cargarDatosEscolaridad(ArrayList datos) {
        txEscuela.setText((String) datos.get(0));
        txGrado.setText((String) datos.get(1));
    }

    public int metodoCombo(String cadena, JComboBox box) {
        int retorno = -1;
        int index = box.getItemCount();
        System.out.println(index);
        for (int i = 0; i < index; i++) {
            String o1 = box.getItemAt(i).toString();
            //System.out.println(o1);
            if (o1.compareTo(cadena) == 0) {
                retorno = i;
                System.out.println(retorno + "entreee");
            }
        }
        return retorno;
    }

    public void modificable(boolean modif) {
        txNombreMadre.setEditable(modif);
        txApellidoPaternoMadre.setEditable(modif);
        txApellidoMaternoMadre.setEditable(modif);
        cbAñoMadre.setEnabled(modif);
        cbMesMadre.setEnabled(modif);
        cbDiaMadre.setEnabled(modif);
        txEdadMadre.setEditable(modif);
        txProfesionMadre.setEditable(modif);
        txTelefonoCelularMadre.setEditable(modif);
        txPersonaConfianzaMadre.setEditable(modif);

        txNombrePadre.setEditable(modif);
        txApellidoPaternoPadre.setEditable(modif);
        txApellidoMaternoPadre.setEditable(modif);
        cbAñoPadre.setEnabled(modif);
        cbMesPadre.setEnabled(modif);
        cbDiaPadre.setEnabled(modif);
        txEdadPadre.setEditable(modif);
        txProfesionPadre.setEditable(modif);
        txTelefonoCelularPadre.setEditable(modif);
        txPersonaConfianzaPadre.setEditable(modif);

        txEscuela.setEditable(modif);
        txGrado.setEditable(modif);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDatosMadre = new javax.swing.JPanel();
        jpFechaNacMadre = new javax.swing.JPanel();
        cbDiaMadre = new javax.swing.JComboBox();
        cbMesMadre = new javax.swing.JComboBox();
        cbAñoMadre = new javax.swing.JComboBox();
        txEdadMadre = new javax.swing.JTextField();
        etEdadMadre = new javax.swing.JLabel();
        etNombreMadre = new javax.swing.JLabel();
        txNombreMadre = new javax.swing.JTextField();
        etApellidoPaternoMadre = new javax.swing.JLabel();
        txApellidoPaternoMadre = new javax.swing.JTextField();
        txApellidoMaternoMadre = new javax.swing.JTextField();
        etApellidoMaternoMadre = new javax.swing.JLabel();
        etProfesionMadre = new javax.swing.JLabel();
        txProfesionMadre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txTelefonoCelularMadre = new javax.swing.JTextField();
        etTelefonoCelularMadre = new javax.swing.JLabel();
        txPersonaConfianzaMadre = new javax.swing.JTextField();
        etPersonaDeConfianzaMadre = new javax.swing.JLabel();
        jpEscolaridad = new javax.swing.JPanel();
        etEscuela = new javax.swing.JLabel();
        etGrado = new javax.swing.JLabel();
        txEscuela = new javax.swing.JTextField();
        txGrado = new javax.swing.JTextField();
        jpPadre = new javax.swing.JPanel();
        joFechaNacPadre = new javax.swing.JPanel();
        cbDiaPadre = new javax.swing.JComboBox();
        cbMesPadre = new javax.swing.JComboBox();
        cbAñoPadre = new javax.swing.JComboBox();
        txEdadPadre = new javax.swing.JTextField();
        etEdadPadre = new javax.swing.JLabel();
        etNombrePadre = new javax.swing.JLabel();
        txNombrePadre = new javax.swing.JTextField();
        etApellidoPaternoPadre = new javax.swing.JLabel();
        txApellidoPaternoPadre = new javax.swing.JTextField();
        txApellidoMaternoPadre = new javax.swing.JTextField();
        etApellidoMaternoPadre = new javax.swing.JLabel();
        etProfesionPadre = new javax.swing.JLabel();
        txProfesionPadre = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txTelefonoCelularPadre = new javax.swing.JTextField();
        etTelefonoCelular = new javax.swing.JLabel();
        txPersonaConfianzaPadre = new javax.swing.JTextField();
        etPersonaConfianzaPadre = new javax.swing.JLabel();

        setLayout(null);

        jpDatosMadre.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Madre"));
        jpDatosMadre.setLayout(null);

        jpFechaNacMadre.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de nacimiento  "));
        jpFechaNacMadre.setLayout(null);

        cbDiaMadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dia" }));
        cbDiaMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiaMadreActionPerformed(evt);
            }
        });
        jpFechaNacMadre.add(cbDiaMadre);
        cbDiaMadre.setBounds(190, 40, 80, 25);

        cbMesMadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mes", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE", " " }));
        jpFechaNacMadre.add(cbMesMadre);
        cbMesMadre.setBounds(100, 40, 80, 25);

        cbAñoMadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "año" }));
        jpFechaNacMadre.add(cbAñoMadre);
        cbAñoMadre.setBounds(10, 40, 80, 25);

        txEdadMadre.setEditable(false);
        txEdadMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEdadMadreActionPerformed(evt);
            }
        });
        jpFechaNacMadre.add(txEdadMadre);
        txEdadMadre.setBounds(280, 40, 80, 27);

        etEdadMadre.setText("Edad");
        jpFechaNacMadre.add(etEdadMadre);
        etEdadMadre.setBounds(280, 20, 60, 14);

        jpDatosMadre.add(jpFechaNacMadre);
        jpFechaNacMadre.setBounds(10, 80, 380, 77);

        etNombreMadre.setText("Nombre");
        etNombreMadre.setName("lanombre"); // NOI18N
        jpDatosMadre.add(etNombreMadre);
        etNombreMadre.setBounds(20, 20, 100, 14);

        txNombreMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombreMadreActionPerformed(evt);
            }
        });
        jpDatosMadre.add(txNombreMadre);
        txNombreMadre.setBounds(20, 40, 162, 27);

        etApellidoPaternoMadre.setText("Apellido Paterno");
        etApellidoPaternoMadre.setPreferredSize(new java.awt.Dimension(80, 14));
        jpDatosMadre.add(etApellidoPaternoMadre);
        etApellidoPaternoMadre.setBounds(200, 20, 100, 14);

        txApellidoPaternoMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoPaternoMadreActionPerformed(evt);
            }
        });
        jpDatosMadre.add(txApellidoPaternoMadre);
        txApellidoPaternoMadre.setBounds(200, 40, 160, 27);

        txApellidoMaternoMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoMaternoMadreActionPerformed(evt);
            }
        });
        jpDatosMadre.add(txApellidoMaternoMadre);
        txApellidoMaternoMadre.setBounds(390, 40, 149, 27);

        etApellidoMaternoMadre.setText("Apellido Materno");
        jpDatosMadre.add(etApellidoMaternoMadre);
        etApellidoMaternoMadre.setBounds(390, 20, 110, 14);

        etProfesionMadre.setText("Profesión ");
        jpDatosMadre.add(etProfesionMadre);
        etProfesionMadre.setBounds(400, 90, 60, 14);

        txProfesionMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txProfesionMadreActionPerformed(evt);
            }
        });
        jpDatosMadre.add(txProfesionMadre);
        txProfesionMadre.setBounds(400, 110, 140, 27);
        jpDatosMadre.add(jLabel5);
        jLabel5.setBounds(440, 90, 60, 0);

        txTelefonoCelularMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTelefonoCelularMadreActionPerformed(evt);
            }
        });
        jpDatosMadre.add(txTelefonoCelularMadre);
        txTelefonoCelularMadre.setBounds(570, 40, 160, 27);

        etTelefonoCelularMadre.setText("Teléfono celular ");
        jpDatosMadre.add(etTelefonoCelularMadre);
        etTelefonoCelularMadre.setBounds(570, 20, 100, 14);
        jpDatosMadre.add(txPersonaConfianzaMadre);
        txPersonaConfianzaMadre.setBounds(570, 110, 160, 27);

        etPersonaDeConfianzaMadre.setText("Persona de confianza");
        jpDatosMadre.add(etPersonaDeConfianzaMadre);
        etPersonaDeConfianzaMadre.setBounds(570, 90, 103, 14);

        add(jpDatosMadre);
        jpDatosMadre.setBounds(10, 10, 750, 170);

        jpEscolaridad.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolaridad del alumno"));
        jpEscolaridad.setLayout(null);

        etEscuela.setText("Escuela ");
        jpEscolaridad.add(etEscuela);
        etEscuela.setBounds(10, 20, 80, 14);

        etGrado.setText("Grado");
        jpEscolaridad.add(etGrado);
        etGrado.setBounds(260, 20, 60, 14);
        jpEscolaridad.add(txEscuela);
        txEscuela.setBounds(10, 40, 170, 27);
        jpEscolaridad.add(txGrado);
        txGrado.setBounds(260, 40, 180, 27);

        add(jpEscolaridad);
        jpEscolaridad.setBounds(10, 370, 750, 80);

        jpPadre.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Padre"));
        jpPadre.setLayout(null);

        joFechaNacPadre.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de nacimiento  "));
        joFechaNacPadre.setLayout(null);

        cbDiaPadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dia" }));
        joFechaNacPadre.add(cbDiaPadre);
        cbDiaPadre.setBounds(190, 40, 80, 25);

        cbMesPadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "mes" }));
        joFechaNacPadre.add(cbMesPadre);
        cbMesPadre.setBounds(100, 40, 80, 25);

        cbAñoPadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "año" }));
        joFechaNacPadre.add(cbAñoPadre);
        cbAñoPadre.setBounds(10, 40, 80, 25);

        txEdadPadre.setEditable(false);
        txEdadPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEdadPadreActionPerformed(evt);
            }
        });
        joFechaNacPadre.add(txEdadPadre);
        txEdadPadre.setBounds(290, 40, 80, 27);

        etEdadPadre.setText("Edad");
        joFechaNacPadre.add(etEdadPadre);
        etEdadPadre.setBounds(290, 20, 60, 14);

        jpPadre.add(joFechaNacPadre);
        joFechaNacPadre.setBounds(10, 80, 380, 77);

        etNombrePadre.setText("Nombre");
        etNombrePadre.setName("lanombre"); // NOI18N
        jpPadre.add(etNombrePadre);
        etNombrePadre.setBounds(20, 20, 100, 14);

        txNombrePadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombrePadreActionPerformed(evt);
            }
        });
        jpPadre.add(txNombrePadre);
        txNombrePadre.setBounds(20, 40, 162, 27);

        etApellidoPaternoPadre.setText("Apellido Paterno");
        etApellidoPaternoPadre.setPreferredSize(new java.awt.Dimension(80, 14));
        jpPadre.add(etApellidoPaternoPadre);
        etApellidoPaternoPadre.setBounds(190, 20, 100, 14);

        txApellidoPaternoPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoPaternoPadreActionPerformed(evt);
            }
        });
        jpPadre.add(txApellidoPaternoPadre);
        txApellidoPaternoPadre.setBounds(200, 40, 160, 27);

        txApellidoMaternoPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txApellidoMaternoPadreActionPerformed(evt);
            }
        });
        jpPadre.add(txApellidoMaternoPadre);
        txApellidoMaternoPadre.setBounds(400, 40, 149, 27);

        etApellidoMaternoPadre.setText("Apellido Materno");
        jpPadre.add(etApellidoMaternoPadre);
        etApellidoMaternoPadre.setBounds(400, 20, 110, 14);

        etProfesionPadre.setText("Profesión ");
        jpPadre.add(etProfesionPadre);
        etProfesionPadre.setBounds(400, 90, 60, 14);

        txProfesionPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txProfesionPadreActionPerformed(evt);
            }
        });
        jpPadre.add(txProfesionPadre);
        txProfesionPadre.setBounds(400, 110, 150, 27);
        jpPadre.add(jLabel24);
        jLabel24.setBounds(440, 90, 60, 0);
        jpPadre.add(txTelefonoCelularPadre);
        txTelefonoCelularPadre.setBounds(570, 40, 160, 27);

        etTelefonoCelular.setText("Teléfono celular ");
        jpPadre.add(etTelefonoCelular);
        etTelefonoCelular.setBounds(570, 20, 100, 14);
        jpPadre.add(txPersonaConfianzaPadre);
        txPersonaConfianzaPadre.setBounds(570, 110, 160, 27);

        etPersonaConfianzaPadre.setText("Persona de confianza");
        jpPadre.add(etPersonaConfianzaPadre);
        etPersonaConfianzaPadre.setBounds(570, 90, 120, 14);

        add(jpPadre);
        jpPadre.setBounds(10, 190, 750, 170);
    }// </editor-fold>//GEN-END:initComponents

    private void txNombreMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombreMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNombreMadreActionPerformed

    private void txApellidoPaternoMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoPaternoMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoPaternoMadreActionPerformed

    private void txApellidoMaternoMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoMaternoMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoMaternoMadreActionPerformed

    private void txEdadMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txEdadMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txEdadMadreActionPerformed

    private void txProfesionMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txProfesionMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txProfesionMadreActionPerformed

    private void txEdadPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txEdadPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txEdadPadreActionPerformed

    private void txNombrePadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombrePadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNombrePadreActionPerformed

    private void txApellidoPaternoPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoPaternoPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoPaternoPadreActionPerformed

    private void txApellidoMaternoPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txApellidoMaternoPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txApellidoMaternoPadreActionPerformed

    private void txProfesionPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txProfesionPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txProfesionPadreActionPerformed

    private void cbDiaMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiaMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDiaMadreActionPerformed

    private void txTelefonoCelularMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTelefonoCelularMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTelefonoCelularMadreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbAñoMadre;
    private javax.swing.JComboBox cbAñoPadre;
    private javax.swing.JComboBox cbDiaMadre;
    private javax.swing.JComboBox cbDiaPadre;
    private javax.swing.JComboBox cbMesMadre;
    private javax.swing.JComboBox cbMesPadre;
    private javax.swing.JLabel etApellidoMaternoMadre;
    private javax.swing.JLabel etApellidoMaternoPadre;
    private javax.swing.JLabel etApellidoPaternoMadre;
    private javax.swing.JLabel etApellidoPaternoPadre;
    private javax.swing.JLabel etEdadMadre;
    private javax.swing.JLabel etEdadPadre;
    private javax.swing.JLabel etEscuela;
    private javax.swing.JLabel etGrado;
    private javax.swing.JLabel etNombreMadre;
    private javax.swing.JLabel etNombrePadre;
    private javax.swing.JLabel etPersonaConfianzaPadre;
    private javax.swing.JLabel etPersonaDeConfianzaMadre;
    private javax.swing.JLabel etProfesionMadre;
    private javax.swing.JLabel etProfesionPadre;
    private javax.swing.JLabel etTelefonoCelular;
    private javax.swing.JLabel etTelefonoCelularMadre;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel joFechaNacPadre;
    private javax.swing.JPanel jpDatosMadre;
    private javax.swing.JPanel jpEscolaridad;
    private javax.swing.JPanel jpFechaNacMadre;
    private javax.swing.JPanel jpPadre;
    private javax.swing.JTextField txApellidoMaternoMadre;
    private javax.swing.JTextField txApellidoMaternoPadre;
    private javax.swing.JTextField txApellidoPaternoMadre;
    private javax.swing.JTextField txApellidoPaternoPadre;
    private javax.swing.JTextField txEdadMadre;
    private javax.swing.JTextField txEdadPadre;
    private javax.swing.JTextField txEscuela;
    private javax.swing.JTextField txGrado;
    private javax.swing.JTextField txNombreMadre;
    private javax.swing.JTextField txNombrePadre;
    private javax.swing.JTextField txPersonaConfianzaMadre;
    private javax.swing.JTextField txPersonaConfianzaPadre;
    private javax.swing.JTextField txProfesionMadre;
    private javax.swing.JTextField txProfesionPadre;
    private javax.swing.JTextField txTelefonoCelularMadre;
    private javax.swing.JTextField txTelefonoCelularPadre;
    // End of variables declaration//GEN-END:variables


}
