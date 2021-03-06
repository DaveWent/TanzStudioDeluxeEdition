/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CVistaUsuario;
import Interfaces.ICierraVentana;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Interfaces.IRecibeDatosUsuario;

/**
 *
 * @author Christian
 */
public class VPrimerRegistroUsuario extends javax.swing.JFrame implements ICierraVentana{

    /**
     * Creates new form Grafico1
     */
    public VPrimerRegistroUsuario() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etRegistrarUsuario = new javax.swing.JLabel();
        etUsuario = new javax.swing.JLabel();
        etContraseña = new javax.swing.JLabel();
        etContraseñaConfirmacion = new javax.swing.JLabel();
        txUsuario = new javax.swing.JTextField();
        txContraseña = new javax.swing.JPasswordField();
        txContraseñaConfirmacion = new javax.swing.JPasswordField();
        btAceptar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        cbPregunta = new javax.swing.JComboBox();
        etPreguntaDeSeguridad = new javax.swing.JLabel();
        etRespuesta = new javax.swing.JLabel();
        txRespuesta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Nuevo Usuario");
        setPreferredSize(new java.awt.Dimension(438, 325));
        getContentPane().setLayout(null);

        etRegistrarUsuario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        etRegistrarUsuario.setText("Registrar Usuario");
        getContentPane().add(etRegistrarUsuario);
        etRegistrarUsuario.setBounds(140, 20, 186, 22);

        etUsuario.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        etUsuario.setText("Usuario:");
        getContentPane().add(etUsuario);
        etUsuario.setBounds(105, 59, 41, 14);

        etContraseña.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        etContraseña.setText("Contraseña:");
        getContentPane().add(etContraseña);
        etContraseña.setBounds(90, 90, 57, 14);

        etContraseñaConfirmacion.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        etContraseñaConfirmacion.setText("Contraseña:");
        getContentPane().add(etContraseñaConfirmacion);
        etContraseñaConfirmacion.setBounds(90, 130, 57, 14);

        txUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txUsuario);
        txUsuario.setBounds(160, 50, 152, 30);

        txContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txContraseñaActionPerformed(evt);
            }
        });
        getContentPane().add(txContraseña);
        txContraseña.setBounds(160, 90, 152, 30);
        getContentPane().add(txContraseñaConfirmacion);
        txContraseñaConfirmacion.setBounds(160, 130, 152, 30);

        btAceptar.setText("Aceptar");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btAceptar);
        btAceptar.setBounds(230, 250, 80, 30);

        btCancelar.setText("Cancelar");
        getContentPane().add(btCancelar);
        btCancelar.setBounds(120, 250, 80, 30);

        cbPregunta.setModel(new javax.swing.DefaultComboBoxModel());
        cbPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPreguntaActionPerformed(evt);
            }
        });
        getContentPane().add(cbPregunta);
        cbPregunta.setBounds(160, 170, 230, 30);
        cbPregunta.addItem("Elige una pregunta");
        cbPregunta.addItem("¿Como se llama tu primera mascota?");
        cbPregunta.addItem("¿Quien es el amor de tu vida?");
        cbPregunta.addItem("¿Cual es tu pastel favorito?");

        etPreguntaDeSeguridad.setText("Pregunta de seguridad:");
        getContentPane().add(etPreguntaDeSeguridad);
        etPreguntaDeSeguridad.setBounds(20, 170, 140, 30);

        etRespuesta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        etRespuesta.setText("Respuesta:");
        getContentPane().add(etRespuesta);
        etRespuesta.setBounds(80, 210, 60, 20);

        txRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txRespuestaActionPerformed(evt);
            }
        });
        getContentPane().add(txRespuesta);
        txRespuesta.setBounds(160, 210, 230, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txUsuarioActionPerformed

    private void txRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txRespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txRespuestaActionPerformed

    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed

    }//GEN-LAST:event_btAceptarActionPerformed

    private void txContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txContraseñaActionPerformed

    private void cbPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPreguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPreguntaActionPerformed

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
            java.util.logging.Logger.getLogger(VPrimerRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrimerRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrimerRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrimerRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPrimerRegistroUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JComboBox cbPregunta;
    private javax.swing.JLabel etContraseña;
    private javax.swing.JLabel etContraseñaConfirmacion;
    private javax.swing.JLabel etPreguntaDeSeguridad;
    private javax.swing.JLabel etRegistrarUsuario;
    private javax.swing.JLabel etRespuesta;
    private javax.swing.JLabel etUsuario;
    private javax.swing.JPasswordField txContraseña;
    private javax.swing.JPasswordField txContraseñaConfirmacion;
    private javax.swing.JTextField txRespuesta;
    private javax.swing.JTextField txUsuario;
    // End of variables declaration//GEN-END:variables

    public JButton getBtAceptar() {
        return btAceptar;
    }

    public JButton getBtCancelar() {
        return btCancelar;
    }

    public JComboBox getCbPregunta() {
        return cbPregunta;
    }

    public JPasswordField getTxContraseña() {
        return txContraseña;
    }

    public JPasswordField getTxContraseñaConfirmacion() {
        return txContraseñaConfirmacion;
    }

    public JTextField getTxRespuesta() {
        return txRespuesta;
    }

    public JTextField getTxUsuario() {
        return txUsuario;
    }

    public void cerrarVentana() {
        dispose();
    }

   
}
