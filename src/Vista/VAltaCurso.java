/**
 * AltaCurso.java
 * Version 2
 * 05/01/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Vista;

import Controlador.CVistaCurso;
import Interfaces.ICierraVentana;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase que contiene el entorno gráfico de la alta de un curso
 * @author David Omar Cabrera Bernal
 */
public class VAltaCurso extends javax.swing.JFrame implements ICierraVentana{
    CVistaCurso control;  //Instancia de ControlVistaCurso
    String activaFecha = "No";  //Variable que declara cuando un curso es esporádico

    /**
     * Constructor que incializa el entorno gráfico
     * @throws SQLException 
     */
    public VAltaCurso( ) throws SQLException {
        initComponents( );
        setLocationRelativeTo( null );
        control = new CVistaCurso( this );
        String proveniente = "alta";
        jpFecha.setVisible( false );
    }

    /**
     * Metodo que inicializa todas las variables del entorno gráfico
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCurso = new javax.swing.JPanel();
        etNombre = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        chTipoCurso = new javax.swing.JCheckBox();
        chSilencio = new javax.swing.JCheckBox();
        btAceptar = new javax.swing.JButton();
        jpFecha = new javax.swing.JPanel();
        etFechaInicio = new javax.swing.JLabel();
        etFechaFin = new javax.swing.JLabel();
        clFechaFin = new com.toedter.calendar.JDateChooser();
        clFechaInicio = new com.toedter.calendar.JDateChooser();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar curso");
        setMaximumSize(new java.awt.Dimension(485, 260));
        setMinimumSize(new java.awt.Dimension(485, 260));
        setPreferredSize(new java.awt.Dimension(485, 260));
        getContentPane().setLayout(null);

        jpCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("Curso"));
        jpCurso.setLayout(null);

        etNombre.setText("Nombre del curso");
        jpCurso.add(etNombre);
        etNombre.setBounds(20, 20, 110, 14);

        txNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombreActionPerformed(evt);
            }
        });
        jpCurso.add(txNombre);
        txNombre.setBounds(20, 40, 180, 25);

        chTipoCurso.setText("Es un curso esporádico");
        chTipoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chTipoCursoActionPerformed(evt);
            }
        });
        jpCurso.add(chTipoCurso);
        chTipoCurso.setBounds(230, 45, 180, 23);

        chSilencio.setText("Requiere de total silencio");
        chSilencio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chSilencioActionPerformed(evt);
            }
        });
        jpCurso.add(chSilencio);
        chSilencio.setBounds(230, 25, 170, 23);

        getContentPane().add(jpCurso);
        jpCurso.setBounds(10, 11, 450, 80);

        btAceptar.setText("Aceptar");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btAceptar);
        btAceptar.setBounds(240, 190, 90, 30);

        jpFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Duracion"));
        jpFecha.setEnabled(true);
        jpFecha.setLayout(null);

        etFechaInicio.setText("Fecha de inicio");
        jpFecha.add(etFechaInicio);
        etFechaInicio.setBounds(20, 20, 100, 14);

        etFechaFin.setText("Fecha de fin");
        jpFecha.add(etFechaFin);
        etFechaFin.setBounds(230, 20, 100, 14);
        jpFecha.add(clFechaFin);
        clFechaFin.setBounds(230, 40, 190, 25);
        jpFecha.add(clFechaInicio);
        clFechaInicio.setBounds(20, 40, 190, 25);

        getContentPane().add(jpFecha);
        jpFecha.setBounds(10, 100, 450, 80);

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btCancelar);
        btCancelar.setBounds(140, 190, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombreActionPerformed
        
    }//GEN-LAST:event_txNombreActionPerformed

    /**
     * Método que al presionar un boton obtiene todos los datos de un curso para
     * enviarlos en un método que realiza una alta del curso
     * @param evt 
     */
    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed
        String nombreCurso = txNombre.getText();
        String tipoCurso;
        if( chTipoCurso.isSelected() == true ) {
            tipoCurso = "esporadico";
        } else {
            tipoCurso = "normal";
        }
        String silencio;
        if( chSilencio.isSelected() == true ) {
            silencio = "SI";
        } else {
            silencio = "NO";
        }
        String formato = clFechaInicio.getDateFormatString();
        Date fechaInicio = clFechaInicio.getDate();
        Date fechaFin = clFechaFin.getDate();
        control.altaCurso(nombreCurso, tipoCurso, silencio, formato, fechaInicio, fechaFin );
    }//GEN-LAST:event_btAceptarActionPerformed

    private void chTipoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chTipoCursoActionPerformed
        if( activaFecha.equals( "No" ) ) {
            activaFecha = "Si";
            jpFecha.setVisible( true );
        } else {
            activaFecha = "No";
            jpFecha.setVisible( false );
        }
    }//GEN-LAST:event_chTipoCursoActionPerformed

    private void chSilencioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chSilencioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chSilencioActionPerformed

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
            java.util.logging.Logger.getLogger(VAltaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VAltaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VAltaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VAltaCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VAltaCurso().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VAltaCurso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JCheckBox chSilencio;
    private javax.swing.JCheckBox chTipoCurso;
    private com.toedter.calendar.JDateChooser clFechaFin;
    private com.toedter.calendar.JDateChooser clFechaInicio;
    private javax.swing.JLabel etFechaFin;
    private javax.swing.JLabel etFechaInicio;
    private javax.swing.JLabel etNombre;
    private javax.swing.JPanel jpCurso;
    private javax.swing.JPanel jpFecha;
    private javax.swing.JTextField txNombre;
    // End of variables declaration//GEN-END:variables

    public void cerrarVentana() {
        dispose();
    }
}
