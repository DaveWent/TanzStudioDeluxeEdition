/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CVistaCurso;
import Interfaces.ICargaCursos;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author DaveWent
 */
public class VSeleccionCurso extends javax.swing.JFrame implements ICargaCursos{
    ICargaCursos interfaz;
    CVistaCurso control;
    
    public VSeleccionCurso() throws Exception {
        initComponents();
        setResizable( false );
        setLocationRelativeTo( null );
        control = new CVistaCurso( this );
        control.obtenerCursos(cbCursos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCursos = new javax.swing.JPanel();
        etCursoASeleccionar = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        btAceptar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selección de curso");
        setMaximumSize(new java.awt.Dimension(350, 165));
        setMinimumSize(new java.awt.Dimension(350, 165));
        setPreferredSize(new java.awt.Dimension(350, 165));
        getContentPane().setLayout(null);

        jpCursos.setBorder(javax.swing.BorderFactory.createTitledBorder("Curso"));
        jpCursos.setLayout(null);

        etCursoASeleccionar.setText("Seleccione el curso que desea asignarle un profesor");
        jpCursos.add(etCursoASeleccionar);
        etCursoASeleccionar.setBounds(10, 20, 300, 14);

        cbCursos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yoga", "Ballet", "Jazz", "Ritmos latinos" }));
        cbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursosActionPerformed(evt);
            }
        });
        jpCursos.add(cbCursos);
        cbCursos.setBounds(20, 40, 280, 20);

        getContentPane().add(jpCursos);
        jpCursos.setBounds(10, 10, 320, 80);

        btAceptar.setText("Aceptar");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btAceptar);
        btAceptar.setBounds(180, 100, 90, 30);

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btCancelar);
        btCancelar.setBounds(80, 100, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursosActionPerformed

    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed
        if( cbCursos.getSelectedIndex() != 0 ) {
            try {
                String curso = (String) cbCursos.getSelectedItem();
                VAsignarProfesorACurso asignacion = new VAsignarProfesorACurso( curso );
                asignacion.setVisible( true );
                asignacion.setLocationRelativeTo( null );
                asignacion.setResizable( false );
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(VSeleccionCurso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VSeleccionCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un curso ",
                null, JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btAceptarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", 
            "Borrado de profesor", JOptionPane.YES_NO_OPTION);
        if( opcion == 0 ) {
            dispose();
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VSeleccionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VSeleccionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VSeleccionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VSeleccionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VSeleccionCurso().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(VSeleccionCurso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JLabel etCursoASeleccionar;
    private javax.swing.JPanel jpCursos;
    // End of variables declaration//GEN-END:variables

    public void recibeCursos(JComboBox cbCursos) {
        this.cbCursos = cbCursos;
    }

}