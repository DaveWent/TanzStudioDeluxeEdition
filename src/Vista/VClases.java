/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CVistaCurso;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import Interfaces.IClases;

/**
 *
 * @author Dave Went
 */
public class VClases extends javax.swing.JFrame implements IClases{
    CVistaCurso control;
    CVistaCurso controlHorario;
    int fila = -1;
    int numSalones;
    int dia;
    String nombreDelCurso;
    JTable tbHorario;
    boolean seleccion = true;
    /**
     * Creates new form AgregarClase
     */
    public VClases( CVistaCurso controlHorario, String nombreCurso, 
            int columna, int numSalones,JTable tbHorario ) throws Exception {
        initComponents();
        this.controlHorario = controlHorario;
        this.tbHorario = tbHorario;
        control = new CVistaCurso( this );
        control.obtenerClases( nombreCurso, columna, tbClases, cbSalon, numSalones );
        this.numSalones = numSalones;
        dia = columna;
        nombreDelCurso = nombreCurso;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpHorario = new javax.swing.JPanel();
        etHoraFin = new javax.swing.JLabel();
        cbHoraInicio = new javax.swing.JComboBox();
        etSalon = new javax.swing.JLabel();
        cbSalon = new javax.swing.JComboBox();
        cbHoraFin = new javax.swing.JComboBox<>();
        etHoraInicio = new javax.swing.JLabel();
        btQuitar = new javax.swing.JButton();
        btAgregar = new javax.swing.JButton();
        spClases = new javax.swing.JScrollPane();
        tbClases = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Horario clase");
        setMaximumSize(new java.awt.Dimension(500, 310));
        setMinimumSize(new java.awt.Dimension(500, 310));
        setPreferredSize(new java.awt.Dimension(500, 310));
        getContentPane().setLayout(null);

        jpHorario.setBorder(javax.swing.BorderFactory.createTitledBorder("Horario"));
        jpHorario.setLayout(null);

        etHoraFin.setText("Hora de fin");
        jpHorario.add(etHoraFin);
        etHoraFin.setBounds(160, 25, 80, 14);

        cbHoraInicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12:00 AM", "1:00 AM", "2:00 AM","3:00 AM","4:00 AM","5:00 AM",
            "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM",
            "11:00 AM", "12:00 PM", "01:00 PM", "2:00 PM", "3:00 PM",
            "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
            "9:00 PM", "10:00 PM", "11:00 PM",}));
jpHorario.add(cbHoraInicio);
cbHoraInicio.setBounds(20, 45, 120, 20);

etSalon.setText("Salón");
jpHorario.add(etSalon);
etSalon.setBounds(310, 25, 40, 14);

cbSalon.setModel(new javax.swing.DefaultComboBoxModel( ));
jpHorario.add(cbSalon);
cbSalon.setBounds(310, 45, 130, 20);

cbHoraFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12:00 AM", "1:00 AM", "2:00 AM","3:00 AM","4:00 AM","5:00 AM",
    "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM",
    "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM",
    "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
    "9:00 PM", "10:00 PM", "11:00 PM"}));
    jpHorario.add(cbHoraFin);
    cbHoraFin.setBounds(160, 45, 130, 20);

    etHoraInicio.setText("Hora de inicio");
    jpHorario.add(etHoraInicio);
    etHoraInicio.setBounds(20, 25, 80, 14);

    btQuitar.setText("Quitar");
    btQuitar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btQuitarActionPerformed(evt);
        }
    });
    jpHorario.add(btQuitar);
    btQuitar.setBounds(250, 80, 90, 30);

    btAgregar.setText("Agregar");
    btAgregar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btAgregarActionPerformed(evt);
        }
    });
    jpHorario.add(btAgregar);
    btAgregar.setBounds(140, 80, 90, 30);

    getContentPane().add(jpHorario);
    jpHorario.setBounds(10, 160, 460, 130);
    jpHorario.getAccessibleContext().setAccessibleName("Clase");

    spClases.setBorder(javax.swing.BorderFactory.createTitledBorder("Horario"));

    tbClases.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Hora", "Salón"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tbClases.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tbClasesMouseClicked(evt);
        }
    });
    spClases.setViewportView(tbClases);

    getContentPane().add(spClases);
    spClases.setBounds(10, 10, 460, 130);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarActionPerformed
        if( fila != -1 ) {
            try {
                control.quitarClaseDeTabla( tbClases, nombreDelCurso, dia );
                controlHorario.limpiarHorario(tbHorario);
                controlHorario.construirHorario(tbHorario);
            } catch (Exception ex) {
                Logger.getLogger(VClases.class.getName()).log(Level.SEVERE, null, ex);
            }
            fila = -1;
        }
    }//GEN-LAST:event_btQuitarActionPerformed

    private void btAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarActionPerformed
        String horaInicio = (String) cbHoraInicio.getSelectedItem();
        String horaFin = (String) cbHoraFin.getSelectedItem();
        int salon = cbSalon.getSelectedIndex();
        
        try {
            control.agregarClaseATabla( horaInicio, horaFin, salon, numSalones, tbClases, dia, nombreDelCurso );
            controlHorario.limpiarHorario(tbHorario);
            controlHorario.construirHorario(tbHorario);
        } catch (Exception ex) {
            Logger.getLogger(VClases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAgregarActionPerformed

    private void tbClasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClasesMouseClicked
        fila = tbClases.rowAtPoint( evt.getPoint() );
    }//GEN-LAST:event_tbClasesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btQuitar;
    private javax.swing.JComboBox<String> cbHoraFin;
    private javax.swing.JComboBox cbHoraInicio;
    private javax.swing.JComboBox cbSalon;
    private javax.swing.JLabel etHoraFin;
    private javax.swing.JLabel etHoraInicio;
    private javax.swing.JLabel etSalon;
    private javax.swing.JPanel jpHorario;
    private javax.swing.JScrollPane spClases;
    private javax.swing.JTable tbClases;
    // End of variables declaration//GEN-END:variables

    public void recibeClasesActuales( JTable tbClases ) {
        this.tbClases = tbClases;
    }

    public void recibeSalones(JComboBox cbSalon) {
        this.cbSalon = cbSalon;
    }
}