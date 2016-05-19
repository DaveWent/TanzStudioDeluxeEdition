/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author cristian
 */
public class jpDatosEncuesta extends javax.swing.JPanel implements ActionListener, ItemListener {

    /**
     * Creates new form AlgunasPreguntas
     */
    private ButtonGroup grupoTomoClases = new ButtonGroup();

    public jpDatosEncuesta() {
        initComponents();
        grupoTomoClases.add(rbNoClases);
        grupoTomoClases.add(rbSiClases);
        cbDanzaSugerida.addItemListener(this);
        cbMedio.addItemListener(this);
        TomoClases();
        txDanzaSugerida.setVisible(false);
        txMedioPublicidad.setVisible(false);
        etOtra.setVisible(false);
        etOtros.setVisible(false);
    }

    public void TomoClases() {
        if (rbSiClases.isSelected()) {
            etDonde.setVisible(true);
            txLugarDanza.setVisible(true);
        }
        if (rbNoClases.isSelected()) {
            etDonde.setVisible(false);
            txLugarDanza.setVisible(false);
        }
        rbSiClases.addActionListener(this);
        rbNoClases.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rbSiClases) {
            etDonde.setVisible(true);
            txLugarDanza.setVisible(true);
        }
        if (e.getSource() == rbNoClases) {
            etDonde.setVisible(false);
            txLugarDanza.setVisible(false);
        }

    }

    public ArrayList getEncuesta() {
        ArrayList lista = new ArrayList();
        if (((String) cbMedio.getSelectedItem()).equals("Otro")) {
            lista.add(txMedioPublicidad.getText());
        } else {
            lista.add((String) cbMedio.getSelectedItem());

        }
        if (((String) cbDanzaSugerida.getSelectedItem()).equals("Otro")) {
            lista.add(txDanzaSugerida.getText());
        } else {
            lista.add((String) cbDanzaSugerida.getSelectedItem());
        }
        if (rbSiClases.isSelected()) {
            lista.add(txLugarDanza.getText());
        } else {
            lista.add("Ninguna");
        }
        lista.add(spSugerencia.getText());
        return lista;
    }

    public ArrayList getEncuestaNombre() {
        ArrayList alNombre = new ArrayList();
        alNombre.add("medio publicitario");
        alNombre.add("danza sugerida");
        alNombre.add("lugar danza");
        alNombre.add("Sugerencia");

        return alNombre;
    }

    public ArrayList getEncuestaTipo() {
        ArrayList alTipo = new ArrayList();
        alTipo.add("combo");
        alTipo.add("combo");
        alTipo.add("texto");
        alTipo.add("texto");
        return alTipo;
    }

    public void Modificable(boolean modif) {
        cbMedio.setEnabled(modif);
        txMedioPublicidad.setEditable(modif);
        cbDanzaSugerida.setEnabled(modif);
        txDanzaSugerida.setEditable(modif);
        txLugarDanza.setEditable(modif);
        spSugerencia.setEditable(modif);
    }

    public void cargarEncuesta(ArrayList datos) {
        System.out.println(datos);
        String medio = (String) datos.get(0);
        int imedio = metodoCombo(medio, cbMedio);
        if (imedio == -1) {
            etOtros.setVisible(true);
            txMedioPublicidad.setVisible(true);
            txMedioPublicidad.setText(medio);
        } else {
            cbMedio.setSelectedIndex(imedio);
        }

        String danza = (String) datos.get(1);
        int idanza = metodoCombo(danza, cbDanzaSugerida);
        if (imedio == -1) {
            etOtra.setVisible(true);
            txDanzaSugerida.setVisible(true);
            txDanzaSugerida.setText(danza);
        } else {
            cbDanzaSugerida.setSelectedIndex(idanza);
        }
        if (((String) datos.get(2)).compareTo("Ninguno") != 0) {
            rbSiClases.setSelected(true);
            etDonde.setVisible(true);
            txLugarDanza.setVisible(true);
            txLugarDanza.setText((String) datos.get(2));
        }
        spSugerencia.setText((String) datos.get(3));

    }

    public int metodoCombo(String cadena, JComboBox box) {
        int retorno = -1;
        int index = box.getItemCount();
        for (int i = 0; i < index; i++) {
            String o1 = box.getItemAt(i).toString();
            System.out.println(o1 + "   " + cadena);
            if (o1.compareTo(cadena) == 0) {
                retorno = i;
            }
        }
        return retorno;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        spSugerencia = new javax.swing.JTextArea();
        etSugerencia = new javax.swing.JLabel();
        jpAlgunasPreguntas = new javax.swing.JPanel();
        txLugarDanza = new javax.swing.JTextField();
        txMedioPublicidad = new javax.swing.JTextField();
        etOtra = new javax.swing.JLabel();
        txDanzaSugerida = new javax.swing.JTextField();
        etDonde = new javax.swing.JLabel();
        cbDanzaSugerida = new javax.swing.JComboBox();
        cbMedio = new javax.swing.JComboBox();
        rbNoClases = new javax.swing.JRadioButton();
        etClasesAnteriores = new javax.swing.JLabel();
        etOtros = new javax.swing.JLabel();
        etDanzaSugerida = new javax.swing.JLabel();
        rbSiClases = new javax.swing.JRadioButton();
        etMedio = new javax.swing.JLabel();

        setLayout(null);

        spSugerencia.setColumns(20);
        spSugerencia.setRows(5);
        jScrollPane3.setViewportView(spSugerencia);

        add(jScrollPane3);
        jScrollPane3.setBounds(20, 300, 750, 120);

        etSugerencia.setText("Sugerencias");
        add(etSugerencia);
        etSugerencia.setBounds(20, 270, 120, 14);

        jpAlgunasPreguntas.setBorder(javax.swing.BorderFactory.createTitledBorder("Algunas preguntas"));
        jpAlgunasPreguntas.setLayout(null);
        jpAlgunasPreguntas.add(txLugarDanza);
        txLugarDanza.setBounds(400, 210, 150, 27);
        jpAlgunasPreguntas.add(txMedioPublicidad);
        txMedioPublicidad.setBounds(400, 40, 150, 27);

        etOtra.setText("otra:");
        jpAlgunasPreguntas.add(etOtra);
        etOtra.setBounds(350, 140, 50, 14);
        jpAlgunasPreguntas.add(txDanzaSugerida);
        txDanzaSugerida.setBounds(400, 140, 150, 27);

        etDonde.setText("¿Dónde?");
        jpAlgunasPreguntas.add(etDonde);
        etDonde.setBounds(340, 210, 60, 14);

        cbDanzaSugerida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Danza","Tango","Slam","Cueca","Samba","Otro" }));
        cbDanzaSugerida.setLightWeightPopupEnabled(false);
        jpAlgunasPreguntas.add(cbDanzaSugerida);
        cbDanzaSugerida.setBounds(30, 140, 160, 25);

        cbMedio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Medio", "Cartel", "Internet", "Alguien me recomendo", "Volante", "Otro" }));
        jpAlgunasPreguntas.add(cbMedio);
        cbMedio.setBounds(30, 40, 160, 25);

        rbNoClases.setSelected(true);
        rbNoClases.setText("no");
        jpAlgunasPreguntas.add(rbNoClases);
        rbNoClases.setBounds(70, 220, 90, 23);

        etClasesAnteriores.setText("¿Has tomado clases anteriormente?");
        jpAlgunasPreguntas.add(etClasesAnteriores);
        etClasesAnteriores.setBounds(20, 190, 210, 14);

        etOtros.setText("Otros");
        jpAlgunasPreguntas.add(etOtros);
        etOtros.setBounds(350, 40, 50, 14);

        etDanzaSugerida.setText("¿Que otra danza te gustaría que no tenemos en Danco? ");
        jpAlgunasPreguntas.add(etDanzaSugerida);
        etDanzaSugerida.setBounds(20, 90, 390, 14);

        rbSiClases.setText("Si");
        jpAlgunasPreguntas.add(rbSiClases);
        rbSiClases.setBounds(10, 220, 60, 23);

        etMedio.setText("¿Por qué medio se enteró de Danco dance Studio?");
        jpAlgunasPreguntas.add(etMedio);
        etMedio.setBounds(20, 14, 360, 20);

        add(jpAlgunasPreguntas);
        jpAlgunasPreguntas.setBounds(10, 10, 770, 250);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbDanzaSugerida;
    private javax.swing.JComboBox cbMedio;
    private javax.swing.JLabel etClasesAnteriores;
    private javax.swing.JLabel etDanzaSugerida;
    private javax.swing.JLabel etDonde;
    private javax.swing.JLabel etMedio;
    private javax.swing.JLabel etOtra;
    private javax.swing.JLabel etOtros;
    private javax.swing.JLabel etSugerencia;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpAlgunasPreguntas;
    private javax.swing.JRadioButton rbNoClases;
    private javax.swing.JRadioButton rbSiClases;
    private javax.swing.JTextArea spSugerencia;
    private javax.swing.JTextField txDanzaSugerida;
    private javax.swing.JTextField txLugarDanza;
    private javax.swing.JTextField txMedioPublicidad;
    // End of variables declaration//GEN-END:variables


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cbMedio) {
            System.out.println("se presiono este item medio");
            if (((String) cbMedio.getSelectedItem()).equals("Otro")) {
                txMedioPublicidad.setVisible(true);
                etOtros.setVisible(true);
            } else {
                txMedioPublicidad.setVisible(false);
                etOtros.setVisible(false);
            }
        } else {
            if (e.getSource() == cbDanzaSugerida) {
                System.out.println("se presiono este item danza");
                if (((String) cbDanzaSugerida.getSelectedItem()).equals("Otro")) {
                    txDanzaSugerida.setVisible(true);
                    etOtra.setVisible(true);
                } else {
                    txDanzaSugerida.setVisible(false);
                    etOtra.setVisible(false);
                }
            }
        }

    }

}
