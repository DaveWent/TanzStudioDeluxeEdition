/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CVistaCostos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Christian
 */
public class jpModificacionCostoEsporadico extends javax.swing.JPanel {
    /**
     * Creates new form EsporadicoJPanel
     */
    public jpModificacionCostoEsporadico() {
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

        jpVentanaCostosEsporadicos = new javax.swing.JPanel();
        jpModificacionCostoEsporadico = new javax.swing.JPanel();
        etModificarCosto = new javax.swing.JLabel();
        etCostoActual = new javax.swing.JLabel();
        txCostoEsporadico = new javax.swing.JTextField();
        etCosto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btAceptar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        etCursosEsporadicos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();

        jpVentanaCostosEsporadicos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jpVentanaCostosEsporadicos.setLayout(null);

        jpModificacionCostoEsporadico.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Costo de curso esporádico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpModificacionCostoEsporadico.setLayout(null);

        etModificarCosto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        etModificarCosto.setText("Modificar Costo:");
        jpModificacionCostoEsporadico.add(etModificarCosto);
        etModificarCosto.setBounds(60, 110, 100, 20);

        etCostoActual.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        etCostoActual.setText("Costo total de cursos esporádicos : ");
        jpModificacionCostoEsporadico.add(etCostoActual);
        etCostoActual.setBounds(20, 50, 230, 20);

        txCostoEsporadico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCostoEsporadicoActionPerformed(evt);
            }
        });
        jpModificacionCostoEsporadico.add(txCostoEsporadico);
        txCostoEsporadico.setBounds(160, 110, 80, 30);

        etCosto.setText("0.0");
        jpModificacionCostoEsporadico.add(etCosto);
        etCosto.setBounds(270, 50, 70, 20);
        jpModificacionCostoEsporadico.add(jLabel5);
        jLabel5.setBounds(220, 50, 60, 30);

        jpVentanaCostosEsporadicos.add(jpModificacionCostoEsporadico);
        jpModificacionCostoEsporadico.setBounds(80, 100, 370, 180);

        btAceptar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btAceptar.setText("Aceptar");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });
        jpVentanaCostosEsporadicos.add(btAceptar);
        btAceptar.setBounds(300, 310, 100, 30);

        btCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btCancelar.setText("Cancelar");
        jpVentanaCostosEsporadicos.add(btCancelar);
        btCancelar.setBounds(140, 310, 100, 30);

        etCursosEsporadicos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etCursosEsporadicos.setText("Seleccionar curso para modificar:");
        jpVentanaCostosEsporadicos.add(etCursosEsporadicos);
        etCursosEsporadicos.setBounds(80, 30, 230, 30);

        cbCursos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Curso", "Item 2", "Item 3", "Item 4" }));
        cbCursos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursosItemStateChanged(evt);
            }
        });
        cbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursosActionPerformed(evt);
            }
        });
        jpVentanaCostosEsporadicos.add(cbCursos);
        cbCursos.setBounds(300, 30, 180, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpVentanaCostosEsporadicos, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpVentanaCostosEsporadicos, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txCostoEsporadicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCostoEsporadicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCostoEsporadicoActionPerformed

    private void cbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursosActionPerformed

    private void cbCursosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursosItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursosItemStateChanged

    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed


        // TODO add your handling code here:
    }//GEN-LAST:event_btAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JLabel etCosto;
    private javax.swing.JLabel etCostoActual;
    private javax.swing.JLabel etCursosEsporadicos;
    private javax.swing.JLabel etModificarCosto;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jpModificacionCostoEsporadico;
    private javax.swing.JPanel jpVentanaCostosEsporadicos;
    private javax.swing.JTextField txCostoEsporadico;
    // End of variables declaration//GEN-END:variables

    public JButton getBtAceptar() {
        return btAceptar;
    }

    public JButton getBtCancelar() {
        return btCancelar;
    }

    public JComboBox getCbCursos() {
        return cbCursos;
    }

    public JLabel getEtCosto() {
        return etCosto;
    }

    public JLabel getEtCostoActual() {
        return etCostoActual;
    }

    public JLabel getEtCursosEsporadicos() {
        return etCursosEsporadicos;
    }

    public JLabel getEtModificarCosto() {
        return etModificarCosto;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public JPanel getJpModificacionCostoEsporadico() {
        return jpModificacionCostoEsporadico;
    }

    public JPanel getJpVentanaCostosEsporadicos() {
        return jpVentanaCostosEsporadicos;
    }

    public JTextField getTxCostoEsporadico() {
        return txCostoEsporadico;
    }

    public void setBtAceptar(JButton btAceptar) {
        this.btAceptar = btAceptar;
    }

    public void setBtCancelar(JButton btCancelar) {
        this.btCancelar = btCancelar;
    }

    public void setCbCursos(JComboBox cbCursos) {
        this.cbCursos = cbCursos;
    }

    public void setEtCosto(JLabel etCosto) {
        this.etCosto = etCosto;
    }

    public void setEtCostoActual(JLabel etCostoActual) {
        this.etCostoActual = etCostoActual;
    }

    public void setEtCursosEsporadicos(JLabel etCursosEsporadicos) {
        this.etCursosEsporadicos = etCursosEsporadicos;
    }

    public void setEtModificarCosto(JLabel etModificarCosto) {
        this.etModificarCosto = etModificarCosto;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }

    public void setJpModificacionCostoEsporadico(JPanel jpModificacionCostoEsporadico) {
        this.jpModificacionCostoEsporadico = jpModificacionCostoEsporadico;
    }

    public void setJpVentanaCostosEsporadicos(JPanel jpVentanaCostosEsporadicos) {
        this.jpVentanaCostosEsporadicos = jpVentanaCostosEsporadicos;
    }

    public void setTxCostoEsporadico(JTextField txCostoEsporadico) {
        this.txCostoEsporadico = txCostoEsporadico;
    }

   
    
    

}