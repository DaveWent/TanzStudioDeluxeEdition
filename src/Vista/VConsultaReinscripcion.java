/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CVistaConsulta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author cristian
 */
public class VConsultaReinscripcion extends javax.swing.JFrame implements 
        Interfaces.ICambioConsulta, ActionListener {

    /**
     * Creates new form Consulta1
     */
    ButtonGroup gbBuscar;
    ButtonGroup gbFiltro;
    CVistaConsulta control;
    int fila = -1;
    String estado = "Todo";
    String tipo = "Matricula";

    public void grupos() {
        gbBuscar = new ButtonGroup();
        gbFiltro = new ButtonGroup();
        gbBuscar.add(rbMatricula);
        gbBuscar.add(rbNombre);
        gbFiltro.add(rbBaja1);
        gbFiltro.add(rbAlta);
        gbFiltro.add(rbTodos);
    }

    public VConsultaReinscripcion() {
        initComponents();
        control = new CVistaConsulta(this, tbAlumnos);
        try {
            control.consultaGeneral("Todo");
        } catch (Exception ex) {
            Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        grupos();
        acciones();
        
    }
    public void acciones() {
        rbAlta.addActionListener(this);
        rbBaja1.addActionListener(this);
        rbTodos.addActionListener(this);
        rbNombre.addActionListener(this);
        rbMatricula.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btReinscribir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlumnos = new javax.swing.JTable();
        jpBuscar = new javax.swing.JPanel();
        rbMatricula = new javax.swing.JRadioButton();
        rbNombre = new javax.swing.JRadioButton();
        jpEstadoAlumno = new javax.swing.JPanel();
        rbAlta = new javax.swing.JRadioButton();
        rbTodos = new javax.swing.JRadioButton();
        rbBaja1 = new javax.swing.JRadioButton();
        txBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btReinscribir.setText("Reinscribir");
        btReinscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReinscribirActionPerformed(evt);
            }
        });

        tbAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Cristian Rubén ", "Millán   ", "Ruiz"},
                {"2", "Christian", "Olvera", "Barraza"},
                {"3", "Christian", "Blanco", "León"},
                {null, null, null, null}
            },
            new String [] {
                "Matricula", "Nombre", "Apellido paterno", "Apellido Materno"
            }
        ));
        tbAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAlumnos);

        jpBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por"));
        jpBuscar.setLayout(null);

        rbMatricula.setSelected(true);
        rbMatricula.setText("Matricula");
        jpBuscar.add(rbMatricula);
        rbMatricula.setBounds(10, 30, 90, 23);

        rbNombre.setText("Nombre");
        jpBuscar.add(rbNombre);
        rbNombre.setBounds(110, 30, 80, 23);

        jpEstadoAlumno.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado del alumno"));
        jpEstadoAlumno.setLayout(null);

        rbAlta.setText("Alta");
        jpEstadoAlumno.add(rbAlta);
        rbAlta.setBounds(20, 30, 60, 23);

        rbTodos.setSelected(true);
        rbTodos.setText("Todos");
        jpEstadoAlumno.add(rbTodos);
        rbTodos.setBounds(140, 30, 70, 23);

        rbBaja1.setText("Baja");
        jpEstadoAlumno.add(rbBaja1);
        rbBaja1.setBounds(80, 30, 60, 23);

        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

        jLabel2.setText("Buscar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jpBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpEstadoAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(270, 270, 270)
                            .addComponent(btReinscribir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 50, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpEstadoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 226, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 11, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(6, 6, 6)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(35, 35, 35)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(btReinscribir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        if (tipo == "Nombre") {
            try {
                control.consultaFiltradaNombre(estado, txBuscar.getText());
            } catch (Exception ex) {
                Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (tipo == "Matricula") {
            if (txBuscar.getText().length() != 0) {
                control.consultaFiltradaMatricula(estado, txBuscar.getText());
            } else {
                try {
                    control.consultaGeneral(estado);
                } catch (Exception ex) {
                    Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_txBuscarKeyReleased

    private void tbAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlumnosMouseClicked

        fila = tbAlumnos.rowAtPoint(evt.getPoint());
       
    }//GEN-LAST:event_tbAlumnosMouseClicked

    private void btReinscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReinscribirActionPerformed

        if (fila != -1) {
            String mat = (String) tbAlumnos.getValueAt(fila, 0);
            int matricula=Integer.parseInt(mat);          
                try {
                    VReiscripcion reinscripcion = new VReiscripcion( matricula );
                    reinscripcion.setVisible(true);
                    reinscripcion.setLocationRelativeTo(null);
                } catch ( SQLException ex ) {
                    JOptionPane.showMessageDialog(null, "Ya esta inscrito en todos los cursos", 
                            null, JOptionPane.ERROR_MESSAGE, null);
                } catch ( Exception ex ) {
                    Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fila = -1;      
    }//GEN-LAST:event_btReinscribirActionPerformed

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
            java.util.logging.Logger.getLogger(VConsultaReinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VConsultaReinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VConsultaReinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VConsultaReinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VConsultaReinscripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReinscribir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpBuscar;
    private javax.swing.JPanel jpEstadoAlumno;
    private javax.swing.JRadioButton rbAlta;
    private javax.swing.JRadioButton rbBaja1;
    private javax.swing.JRadioButton rbMatricula;
    private javax.swing.JRadioButton rbNombre;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tbAlumnos;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void recibeDatos(JTable ConsultasJTable) {
        this.tbAlumnos = ConsultasJTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rbTodos) {
            estado = "Todo";
            txBuscar.setText("");
            try {
                control.consultaGeneral(estado);
            } catch (Exception ex) {
                Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == rbAlta) {
          
            estado = "Alta";
            txBuscar.setText("");
            try {
                control.consultaGeneral(estado);
            } catch (Exception ex) {
                Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == rbBaja1) {
           
            estado = "Baja";
            txBuscar.setText("");
            try {
                control.consultaGeneral(estado);
            } catch (Exception ex) {
                Logger.getLogger(VConsultaReinscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == rbNombre) {
            tipo = "Nombre";
        }
        if (e.getSource() == rbMatricula) {
            tipo = "Matricula";
        }
    }
}
