/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author cristian
 */
public class jpSeleccionMaterias extends javax.swing.JPanel {
    
    int fila = -1;

    /**
     * Creates new form PanelSeleccionMaterias
     */
    public jpSeleccionMaterias() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCurso = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCurso = new javax.swing.JTable();
        jpSeleccionDeCurso = new javax.swing.JPanel();
        cbCurso = new javax.swing.JComboBox();
        etCurso = new javax.swing.JLabel();
        btAgregar = new javax.swing.JButton();
        btQuitar = new javax.swing.JButton();
        cbClase = new javax.swing.JComboBox();
        etHorario = new javax.swing.JLabel();

        setLayout(null);

        jpCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección de curso(s)"));
        jpCurso.setLayout(null);

        tbCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso", "Dia", "Hora", "Salon"
            }
        ));
        tbCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCursoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCurso);

        jpCurso.add(jScrollPane2);
        jScrollPane2.setBounds(30, 40, 590, 130);

        add(jpCurso);
        jpCurso.setBounds(10, 120, 640, 220);

        jpSeleccionDeCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección De curso"));
        jpSeleccionDeCurso.setLayout(null);

        cbCurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ballet" }));
        jpSeleccionDeCurso.add(cbCurso);
        cbCurso.setBounds(10, 40, 130, 27);

        etCurso.setText("Cursos");
        jpSeleccionDeCurso.add(etCurso);
        etCurso.setBounds(10, 20, 80, 14);

        btAgregar.setText("Agregar");
        jpSeleccionDeCurso.add(btAgregar);
        btAgregar.setBounds(450, 30, 90, 30);

        btQuitar.setText("Quitar");
        btQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarActionPerformed(evt);
            }
        });
        jpSeleccionDeCurso.add(btQuitar);
        btQuitar.setBounds(550, 30, 90, 30);

        cbClase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Horario" }));
        jpSeleccionDeCurso.add(cbClase);
        cbClase.setBounds(160, 40, 260, 27);

        etHorario.setText("Horario");
        jpSeleccionDeCurso.add(etHorario);
        etHorario.setBounds(160, 20, 80, 14);

        add(jpSeleccionDeCurso);
        jpSeleccionDeCurso.setBounds(10, 10, 650, 100);
    }// </editor-fold>//GEN-END:initComponents

    private void tbCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCursoMouseClicked
        fila = tbCurso.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tbCursoMouseClicked

    private void btQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarActionPerformed
        
    }//GEN-LAST:event_btQuitarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btQuitar;
    private javax.swing.JComboBox cbClase;
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JLabel etCurso;
    private javax.swing.JLabel etHorario;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpCurso;
    private javax.swing.JPanel jpSeleccionDeCurso;
    private javax.swing.JTable tbCurso;
    // End of variables declaration//GEN-END:variables

    public int getFila() {
        return fila;
    }
    
    public void setFila( int fila ) {
        this.fila = fila;
    }
    
    public JTable getTbCursos() {
        return tbCurso;
    }

    public void setTbCursos(JTable Tabla2) {
        this.tbCurso = Tabla2;
    }

    public JButton getBtAgregar() {
        return btAgregar;
    }

    public void setBtAgregar(JButton btAgregar) {
        this.btAgregar = btAgregar;
    }

    public JButton getBtQuitar() {
        return btQuitar;
    }

    public void setBtQuitar(JButton btQuitar) {
        this.btQuitar = btQuitar;
    }

    public JComboBox getCbCurso() {
        return cbCurso;
    }

    public void setCbCurso(JComboBox cbBallet) {
        this.cbCurso = cbBallet;
    }

    public JComboBox getCbClase() {
        return cbClase;
    }

    public void setCbClase(JComboBox cbHorario) {
        this.cbClase = cbHorario;
    }

    public JPanel getjPanel1() {
        return jpCurso;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jpCurso = jPanel1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JPanel getJpSeleccionDeCurso() {
        return jpSeleccionDeCurso;
    }

    public void setJpSeleccionDeCurso(JPanel jpSeleccionDeCurso) {
        this.jpSeleccionDeCurso = jpSeleccionDeCurso;
    }
}
