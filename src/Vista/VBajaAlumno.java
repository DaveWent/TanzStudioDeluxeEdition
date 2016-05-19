/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CVistaBaja;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author cristian
 */
public class VBajaAlumno extends javax.swing.JFrame implements Interfaces.ICambioBaja {

    private int fila = -1;
    private CVistaBaja control;
    private int matricula;

    public VBajaAlumno(int matricula) {
        initComponents();
        setLocationRelativeTo( null );
        control = new CVistaBaja(this, tbCursos);
        control.actualizarTabla(matricula);
        this.matricula = matricula;
        lbMatricula.setText("MATRICULA:  " + matricula);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbCursos = new javax.swing.JTable();
        lbMatricula = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        btBaja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Baja de alumno en curso");

        tbCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Curso"
            }
        ));
        tbCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCursos);

        lbMatricula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMatricula.setText("Matricula:");

        lbNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNombre.setText("Nombre:");

        btBaja.setText("Baja");
        btBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBajaActionPerformed

        int renglones = tbCursos.getRowCount();
        String mensaje = "";
        if (renglones == 1) {
            mensaje = "¿Está seguro de quiere darlo de baja en este curso";
        } else {
            mensaje = "¿Está seguro de querer dar de baja a este alumno?";
        }
        if (fila != -1) {
            String curso = (String) tbCursos.getValueAt(fila, 0);
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de querer dar de baja a este alumno?",
                    "Borrado de profesor", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                control.baja(matricula, curso);
            }
            fila = -1;
        } else {
            JOptionPane.showMessageDialog(null, "Error Seleccione curso", null, JOptionPane.ERROR_MESSAGE, null);

        }
    }//GEN-LAST:event_btBajaActionPerformed

    private void tbCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCursosMouseClicked
        fila = tbCursos.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tbCursosMouseClicked

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
            java.util.logging.Logger.getLogger(VBajaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VBajaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VBajaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VBajaAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VBajaAlumno(27).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBaja;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMatricula;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JTable tbCursos;
    // End of variables declaration//GEN-END:variables

    public void recibeDatos(JTable tbConsultas) {
        this.tbCursos = tbConsultas;
    }

    public void escribeNombre(String nombre) {
        System.out.println("entre");
        lbNombre.setText("NOMBRE: " + nombre);
    }
}
