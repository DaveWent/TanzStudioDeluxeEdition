/**
 * Estadisticas.java
 * Version 1
 * 18/04/2016
 * Copyright (c) Wiscorp Mexicali,BC
 * All rights reserved
 */
package Vista;

import Controlador.CVistaEstadisticas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;
import Interfaces.IEstadisticas;

/**
 * Clase que contiene la interfaz grafica de las estadisticas
 * @author David Omar Cabrera Bernal
 */
public class VEstadisticas extends javax.swing.JFrame implements IEstadisticas{

    CVistaEstadisticas control;
    int[] tabSeleccionado = { 1, 0, 0, 0, 0 };
    boolean seleccionRangoEdad = false;
    
    /**
     * Constructor que inicializa los valores de la ventana
     */
    public VEstadisticas() throws SQLException {
        initComponents();
        setLocationRelativeTo( null );
        control =new CVistaEstadisticas( this );
        control.cargarEstadisticas( 0, null, 0  );
        control.estadisticasPastel( 0 );
        rbPastelCursos.setSelected( true );
        rbPastelEdades.setSelected( true );
        rbPastelMedios.setSelected( true );
        rbPastelDanzas.setSelected( true );
        rbTodasEdades.setSelected( true );
        activarOpcionesEdad( false );
        control.cargarCiclosEscolares(cbPrimerCicloEscolar, cbSegundoCicloEscolar,
                cbPrimerCicloEscolarEdades,cbSegundoCicloEscolarEdades);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgOpcionesCursos = new javax.swing.ButtonGroup();
        bgOpcionesEdades = new javax.swing.ButtonGroup();
        bgOpcionesMedios = new javax.swing.ButtonGroup();
        bgOpcionesDanzas = new javax.swing.ButtonGroup();
        bgFiltroEdades = new javax.swing.ButtonGroup();
        ptGraficos = new javax.swing.JTabbedPane();
        jpTabGraficaCursos = new javax.swing.JPanel();
        jpOpcionesCursos = new javax.swing.JPanel();
        rbPastelCursos = new javax.swing.JRadioButton();
        rbBarrasCursos = new javax.swing.JRadioButton();
        JpGraficaCurso = new javax.swing.JPanel();
        jpTabGraficaEdades = new javax.swing.JPanel();
        jpGraficaEdades = new javax.swing.JPanel();
        jpFiltroEdades = new javax.swing.JPanel();
        rbTodasEdades = new javax.swing.JRadioButton();
        rbRangoEdad = new javax.swing.JRadioButton();
        etRango = new javax.swing.JLabel();
        cbRangoEdad = new javax.swing.JComboBox<>();
        jpOpcionesEdades = new javax.swing.JPanel();
        rbPastelEdades = new javax.swing.JRadioButton();
        rbBarrasEdades = new javax.swing.JRadioButton();
        jpTabGraficaMedios = new javax.swing.JPanel();
        jpGraficaMedios = new javax.swing.JPanel();
        jpOpcionesMedios = new javax.swing.JPanel();
        rbPastelMedios = new javax.swing.JRadioButton();
        rbBarrasMedios = new javax.swing.JRadioButton();
        jpTabGraficaDanzas = new javax.swing.JPanel();
        jpGraficaDanzas = new javax.swing.JPanel();
        jpOpcionesDanzas = new javax.swing.JPanel();
        rbPastelDanzas = new javax.swing.JRadioButton();
        rbBarrasDanzas = new javax.swing.JRadioButton();
        jpTabGraficaComparaciones = new javax.swing.JPanel();
        ptGraficasComparaciones = new javax.swing.JTabbedPane();
        jpComparacionesCursos = new javax.swing.JPanel();
        jpComparacionCurso1 = new javax.swing.JPanel();
        jpComparacionCurso2 = new javax.swing.JPanel();
        cbSegundoCicloEscolar = new javax.swing.JComboBox<>();
        cbPrimerCicloEscolar = new javax.swing.JComboBox<>();
        jpComparacionesEdades = new javax.swing.JPanel();
        jpComparacionEdad1 = new javax.swing.JPanel();
        jpComparacionEdad2 = new javax.swing.JPanel();
        cbPrimerCicloEscolarEdades = new javax.swing.JComboBox<>();
        cbSegundoCicloEscolarEdades = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusableWindowState(false);
        setResizable(false);

        ptGraficos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ptGraficosMouseClicked(evt);
            }
        });

        jpTabGraficaCursos.setLayout(null);

        jpOpcionesCursos.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de grafica"));
        jpOpcionesCursos.setLayout(null);

        bgOpcionesCursos.add(rbPastelCursos);
        rbPastelCursos.setText("De pastel");
        rbPastelCursos.setToolTipText("");
        rbPastelCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPastelCursosActionPerformed(evt);
            }
        });
        jpOpcionesCursos.add(rbPastelCursos);
        rbPastelCursos.setBounds(20, 20, 110, 23);

        bgOpcionesCursos.add(rbBarrasCursos);
        rbBarrasCursos.setText("De barras");
        rbBarrasCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBarrasCursosActionPerformed(evt);
            }
        });
        jpOpcionesCursos.add(rbBarrasCursos);
        rbBarrasCursos.setBounds(20, 50, 110, 23);

        jpTabGraficaCursos.add(jpOpcionesCursos);
        jpOpcionesCursos.setBounds(720, 20, 170, 90);

        JpGraficaCurso.setLayout(null);
        jpTabGraficaCursos.add(JpGraficaCurso);
        JpGraficaCurso.setBounds(10, 10, 690, 550);

        ptGraficos.addTab("Cursos", jpTabGraficaCursos);

        jpTabGraficaEdades.setLayout(null);

        jpGraficaEdades.setLayout(null);
        jpTabGraficaEdades.add(jpGraficaEdades);
        jpGraficaEdades.setBounds(10, 10, 690, 550);

        jpFiltroEdades.setBorder(javax.swing.BorderFactory.createTitledBorder("Especificación de edad"));
        jpFiltroEdades.setLayout(null);

        bgFiltroEdades.add(rbTodasEdades);
        rbTodasEdades.setText("Todas las edades");
        rbTodasEdades.setToolTipText("");
        rbTodasEdades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodasEdadesActionPerformed(evt);
            }
        });
        jpFiltroEdades.add(rbTodasEdades);
        rbTodasEdades.setBounds(20, 20, 130, 23);

        bgFiltroEdades.add(rbRangoEdad);
        rbRangoEdad.setText("Rango de edad");
        rbRangoEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRangoEdadActionPerformed(evt);
            }
        });
        jpFiltroEdades.add(rbRangoEdad);
        rbRangoEdad.setBounds(20, 50, 110, 23);

        etRango.setText("Rango");
        jpFiltroEdades.add(etRango);
        etRango.setBounds(10, 83, 60, 14);

        cbRangoEdad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3-6", "7-10", "11-15", "15-20", "20-30", "30-" }));
        cbRangoEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRangoEdadActionPerformed(evt);
            }
        });
        jpFiltroEdades.add(cbRangoEdad);
        cbRangoEdad.setBounds(70, 80, 90, 20);

        jpTabGraficaEdades.add(jpFiltroEdades);
        jpFiltroEdades.setBounds(720, 130, 170, 190);

        jpOpcionesEdades.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de grafica"));
        jpOpcionesEdades.setLayout(null);

        bgOpcionesEdades.add(rbPastelEdades);
        rbPastelEdades.setText("De pastel");
        rbPastelEdades.setToolTipText("");
        rbPastelEdades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPastelEdadesActionPerformed(evt);
            }
        });
        jpOpcionesEdades.add(rbPastelEdades);
        rbPastelEdades.setBounds(20, 20, 110, 23);

        bgOpcionesEdades.add(rbBarrasEdades);
        rbBarrasEdades.setText("De barras");
        rbBarrasEdades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBarrasEdadesActionPerformed(evt);
            }
        });
        jpOpcionesEdades.add(rbBarrasEdades);
        rbBarrasEdades.setBounds(20, 50, 110, 23);

        jpTabGraficaEdades.add(jpOpcionesEdades);
        jpOpcionesEdades.setBounds(720, 20, 170, 90);

        ptGraficos.addTab("Edades", jpTabGraficaEdades);

        jpTabGraficaMedios.setLayout(null);

        jpGraficaMedios.setLayout(null);
        jpTabGraficaMedios.add(jpGraficaMedios);
        jpGraficaMedios.setBounds(10, 10, 690, 550);

        jpOpcionesMedios.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de grafica"));
        jpOpcionesMedios.setLayout(null);

        bgOpcionesMedios.add(rbPastelMedios);
        rbPastelMedios.setText("De pastel");
        rbPastelMedios.setToolTipText("");
        rbPastelMedios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPastelMediosActionPerformed(evt);
            }
        });
        jpOpcionesMedios.add(rbPastelMedios);
        rbPastelMedios.setBounds(20, 20, 110, 23);

        bgOpcionesMedios.add(rbBarrasMedios);
        rbBarrasMedios.setText("De barras");
        rbBarrasMedios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBarrasMediosActionPerformed(evt);
            }
        });
        jpOpcionesMedios.add(rbBarrasMedios);
        rbBarrasMedios.setBounds(20, 50, 110, 23);

        jpTabGraficaMedios.add(jpOpcionesMedios);
        jpOpcionesMedios.setBounds(720, 20, 170, 90);

        ptGraficos.addTab("Medios publicitarios", jpTabGraficaMedios);

        jpTabGraficaDanzas.setLayout(null);

        jpGraficaDanzas.setLayout(null);
        jpTabGraficaDanzas.add(jpGraficaDanzas);
        jpGraficaDanzas.setBounds(10, 10, 690, 550);

        jpOpcionesDanzas.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de grafica"));
        jpOpcionesDanzas.setLayout(null);

        bgOpcionesDanzas.add(rbPastelDanzas);
        rbPastelDanzas.setText("De pastel");
        rbPastelDanzas.setToolTipText("");
        rbPastelDanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPastelDanzasActionPerformed(evt);
            }
        });
        jpOpcionesDanzas.add(rbPastelDanzas);
        rbPastelDanzas.setBounds(20, 20, 110, 23);

        bgOpcionesDanzas.add(rbBarrasDanzas);
        rbBarrasDanzas.setText("De barras");
        rbBarrasDanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBarrasDanzasActionPerformed(evt);
            }
        });
        jpOpcionesDanzas.add(rbBarrasDanzas);
        rbBarrasDanzas.setBounds(20, 50, 110, 23);

        jpTabGraficaDanzas.add(jpOpcionesDanzas);
        jpOpcionesDanzas.setBounds(720, 20, 170, 90);

        ptGraficos.addTab("Danzas Sugeridas", jpTabGraficaDanzas);

        jpTabGraficaComparaciones.setLayout(null);

        jpComparacionesCursos.setLayout(null);

        javax.swing.GroupLayout jpComparacionCurso1Layout = new javax.swing.GroupLayout(jpComparacionCurso1);
        jpComparacionCurso1.setLayout(jpComparacionCurso1Layout);
        jpComparacionCurso1Layout.setHorizontalGroup(
            jpComparacionCurso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jpComparacionCurso1Layout.setVerticalGroup(
            jpComparacionCurso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jpComparacionesCursos.add(jpComparacionCurso1);
        jpComparacionCurso1.setBounds(0, 50, 450, 490);

        javax.swing.GroupLayout jpComparacionCurso2Layout = new javax.swing.GroupLayout(jpComparacionCurso2);
        jpComparacionCurso2.setLayout(jpComparacionCurso2Layout);
        jpComparacionCurso2Layout.setHorizontalGroup(
            jpComparacionCurso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jpComparacionCurso2Layout.setVerticalGroup(
            jpComparacionCurso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jpComparacionesCursos.add(jpComparacionCurso2);
        jpComparacionCurso2.setBounds(460, 50, 430, 490);

        cbSegundoCicloEscolar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione ciclo escolar" }));
        cbSegundoCicloEscolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSegundoCicloEscolarActionPerformed(evt);
            }
        });
        jpComparacionesCursos.add(cbSegundoCicloEscolar);
        cbSegundoCicloEscolar.setBounds(590, 20, 170, 20);

        cbPrimerCicloEscolar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione ciclo escolar" }));
        cbPrimerCicloEscolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPrimerCicloEscolarActionPerformed(evt);
            }
        });
        jpComparacionesCursos.add(cbPrimerCicloEscolar);
        cbPrimerCicloEscolar.setBounds(120, 20, 180, 20);

        ptGraficasComparaciones.addTab("Comparación por cursos", jpComparacionesCursos);

        jpComparacionesEdades.setLayout(null);

        javax.swing.GroupLayout jpComparacionEdad1Layout = new javax.swing.GroupLayout(jpComparacionEdad1);
        jpComparacionEdad1.setLayout(jpComparacionEdad1Layout);
        jpComparacionEdad1Layout.setHorizontalGroup(
            jpComparacionEdad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jpComparacionEdad1Layout.setVerticalGroup(
            jpComparacionEdad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jpComparacionesEdades.add(jpComparacionEdad1);
        jpComparacionEdad1.setBounds(0, 50, 450, 490);

        javax.swing.GroupLayout jpComparacionEdad2Layout = new javax.swing.GroupLayout(jpComparacionEdad2);
        jpComparacionEdad2.setLayout(jpComparacionEdad2Layout);
        jpComparacionEdad2Layout.setHorizontalGroup(
            jpComparacionEdad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jpComparacionEdad2Layout.setVerticalGroup(
            jpComparacionEdad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jpComparacionesEdades.add(jpComparacionEdad2);
        jpComparacionEdad2.setBounds(460, 50, 430, 490);

        cbPrimerCicloEscolarEdades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione ciclo escolar" }));
        cbPrimerCicloEscolarEdades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPrimerCicloEscolarEdadesActionPerformed(evt);
            }
        });
        jpComparacionesEdades.add(cbPrimerCicloEscolarEdades);
        cbPrimerCicloEscolarEdades.setBounds(120, 20, 180, 20);

        cbSegundoCicloEscolarEdades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione ciclo escolar" }));
        cbSegundoCicloEscolarEdades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSegundoCicloEscolarEdadesActionPerformed(evt);
            }
        });
        jpComparacionesEdades.add(cbSegundoCicloEscolarEdades);
        cbSegundoCicloEscolarEdades.setBounds(590, 20, 170, 20);

        ptGraficasComparaciones.addTab("Comparación por edades", jpComparacionesEdades);

        jpTabGraficaComparaciones.add(ptGraficasComparaciones);
        ptGraficasComparaciones.setBounds(0, 0, 900, 570);

        ptGraficos.addTab("Comparaciones", jpTabGraficaComparaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ptGraficos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ptGraficos, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbPastelCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPastelCursosActionPerformed
        control.estadisticasPastel( 0 );
    }//GEN-LAST:event_rbPastelCursosActionPerformed

    private void rbBarrasCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBarrasCursosActionPerformed
        control.estadisticasBarras( 0 );
    }//GEN-LAST:event_rbBarrasCursosActionPerformed

    private void ptGraficosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ptGraficosMouseClicked
        try {
            int numPanelSeleccionado = ptGraficos.getSelectedIndex();
            
            switch( numPanelSeleccionado ) {
                case 0:
                    control.cargarEstadisticas( 0, null, 0  );
                    break;
                case 1:
                    control.cargarEstadisticas( 1, null, 0  );
                    break;
                case 2:
                    control.cargarEstadisticas( 2, null,0 );
                    break;
                case 3:
                    control.cargarEstadisticas( 3, null, 0 );
                    break;
            }
            if( tabSeleccionado[ numPanelSeleccionado ] == 0 ) {
                tabSeleccionado[ numPanelSeleccionado ] = 1;
                control.estadisticasPastel( numPanelSeleccionado );
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ptGraficosMouseClicked

    private void rbTodasEdadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodasEdadesActionPerformed
        try {
            control.cargarEstadisticas( 1, null, 0 );
            if( rbPastelEdades.isSelected() ) {
                control.estadisticasPastel( 1 );
            }
            if( rbBarrasEdades.isSelected() ) {
                control.estadisticasBarras( 1 );
            }
            activarOpcionesEdad( false );
        } catch (SQLException ex) {
            Logger.getLogger(VEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rbTodasEdadesActionPerformed

    private void rbRangoEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRangoEdadActionPerformed
        activarOpcionesEdad( true );
        try {
            control.actualizarGraficaRangoEdad( 3,6 );
            control.estadisticasPastel(1);
        } catch( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getMessage(), null, 
                    JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_rbRangoEdadActionPerformed

    private void rbPastelEdadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPastelEdadesActionPerformed
        if( rbTodasEdades.isSelected() ) {
            control.estadisticasPastel( 1 );
        }
    }//GEN-LAST:event_rbPastelEdadesActionPerformed

    private void rbBarrasEdadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBarrasEdadesActionPerformed
        if( rbTodasEdades.isSelected() ) {
            control.estadisticasBarras( 1 );
        }
    }//GEN-LAST:event_rbBarrasEdadesActionPerformed

    private void rbPastelMediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPastelMediosActionPerformed
        control.estadisticasPastel( 2 );
    }//GEN-LAST:event_rbPastelMediosActionPerformed

    private void rbBarrasMediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBarrasMediosActionPerformed
        control.estadisticasBarras( 2 );
    }//GEN-LAST:event_rbBarrasMediosActionPerformed

    private void rbPastelDanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPastelDanzasActionPerformed
        control.estadisticasPastel( 3 );
    }//GEN-LAST:event_rbPastelDanzasActionPerformed

    private void rbBarrasDanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBarrasDanzasActionPerformed
        control.estadisticasBarras( 3 );
    }//GEN-LAST:event_rbBarrasDanzasActionPerformed

    private void cbRangoEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRangoEdadActionPerformed
        String rango = (String) cbRangoEdad.getSelectedItem();
        String[] edades = rango.split( "-" );
        int inicio = Integer.parseInt( edades[ 0 ] );
        int fin = Integer.parseInt( edades[ 1 ] );
        control.actualizarGraficaRangoEdad( inicio, fin );
        if( rbPastelEdades.isSelected() == true ) {
            control.estadisticasPastel( 1 );
        } else {
            control.estadisticasBarras( 1 );
        }
    }//GEN-LAST:event_cbRangoEdadActionPerformed

    private void cbPrimerCicloEscolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPrimerCicloEscolarActionPerformed
        int numeroOpcion = cbPrimerCicloEscolar.getSelectedIndex();
        if( numeroOpcion > 0 ) {
            String opcion = (String) cbPrimerCicloEscolar.getSelectedItem();
            try {
                control.cargarEstadisticas( 4 , opcion, 1 );
                control.estadisticasBarras( 4 );
            } catch (SQLException ex) {
                Logger.getLogger(VEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jpComparacionCurso1.removeAll();
            jpComparacionCurso1.updateUI();
        }
        
    }//GEN-LAST:event_cbPrimerCicloEscolarActionPerformed

    private void cbSegundoCicloEscolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSegundoCicloEscolarActionPerformed
        int numeroOpcion = cbSegundoCicloEscolar.getSelectedIndex();
        if( numeroOpcion > 0 ) {
            String opcion = ( String ) cbSegundoCicloEscolar.getSelectedItem();
            try {
                control.cargarEstadisticas( 5 , opcion, 1 );
                control.estadisticasBarras( 5 );
            } catch (SQLException ex) {
                Logger.getLogger(VEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jpComparacionCurso2.removeAll();
            jpComparacionCurso2.updateUI();
        }
        
    }//GEN-LAST:event_cbSegundoCicloEscolarActionPerformed

    private void cbPrimerCicloEscolarEdadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPrimerCicloEscolarEdadesActionPerformed
        int numeroOpcion = cbPrimerCicloEscolarEdades.getSelectedIndex();
        if( numeroOpcion > 0 ) {
            String opcion = ( String ) cbPrimerCicloEscolarEdades.getSelectedItem();
            try {
                control.cargarEstadisticas( 6 , opcion, 2 );
                control.estadisticasBarras( 6 );
            } catch (SQLException ex) {
                Logger.getLogger(VEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jpComparacionEdad1.removeAll();
            jpComparacionEdad1.updateUI();
        }
    }//GEN-LAST:event_cbPrimerCicloEscolarEdadesActionPerformed

    private void cbSegundoCicloEscolarEdadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSegundoCicloEscolarEdadesActionPerformed
        int numeroOpcion = cbSegundoCicloEscolarEdades.getSelectedIndex();
        if( numeroOpcion > 0 ) {
            String opcion = ( String ) cbSegundoCicloEscolarEdades.getSelectedItem();
            try {
                control.cargarEstadisticas( 7 , opcion, 2 );
                control.estadisticasBarras( 7 );
            } catch (SQLException ex) {
                Logger.getLogger(VEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jpComparacionEdad2.removeAll();
            jpComparacionEdad2.updateUI();
        }
    }//GEN-LAST:event_cbSegundoCicloEscolarEdadesActionPerformed

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
            java.util.logging.Logger.getLogger(VEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VEstadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new VEstadisticas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpGraficaCurso;
    private javax.swing.ButtonGroup bgFiltroEdades;
    private javax.swing.ButtonGroup bgOpcionesCursos;
    private javax.swing.ButtonGroup bgOpcionesDanzas;
    private javax.swing.ButtonGroup bgOpcionesEdades;
    private javax.swing.ButtonGroup bgOpcionesMedios;
    private javax.swing.JComboBox<String> cbPrimerCicloEscolar;
    private javax.swing.JComboBox<String> cbPrimerCicloEscolarEdades;
    private javax.swing.JComboBox<String> cbRangoEdad;
    private javax.swing.JComboBox<String> cbSegundoCicloEscolar;
    private javax.swing.JComboBox<String> cbSegundoCicloEscolarEdades;
    private javax.swing.JLabel etRango;
    private javax.swing.JPanel jpComparacionCurso1;
    private javax.swing.JPanel jpComparacionCurso2;
    private javax.swing.JPanel jpComparacionEdad1;
    private javax.swing.JPanel jpComparacionEdad2;
    private javax.swing.JPanel jpComparacionesCursos;
    private javax.swing.JPanel jpComparacionesEdades;
    private javax.swing.JPanel jpFiltroEdades;
    private javax.swing.JPanel jpGraficaDanzas;
    private javax.swing.JPanel jpGraficaEdades;
    private javax.swing.JPanel jpGraficaMedios;
    private javax.swing.JPanel jpOpcionesCursos;
    private javax.swing.JPanel jpOpcionesDanzas;
    private javax.swing.JPanel jpOpcionesEdades;
    private javax.swing.JPanel jpOpcionesMedios;
    private javax.swing.JPanel jpTabGraficaComparaciones;
    private javax.swing.JPanel jpTabGraficaCursos;
    private javax.swing.JPanel jpTabGraficaDanzas;
    private javax.swing.JPanel jpTabGraficaEdades;
    private javax.swing.JPanel jpTabGraficaMedios;
    private javax.swing.JTabbedPane ptGraficasComparaciones;
    private javax.swing.JTabbedPane ptGraficos;
    private javax.swing.JRadioButton rbBarrasCursos;
    private javax.swing.JRadioButton rbBarrasDanzas;
    private javax.swing.JRadioButton rbBarrasEdades;
    private javax.swing.JRadioButton rbBarrasMedios;
    private javax.swing.JRadioButton rbPastelCursos;
    private javax.swing.JRadioButton rbPastelDanzas;
    private javax.swing.JRadioButton rbPastelEdades;
    private javax.swing.JRadioButton rbPastelMedios;
    private javax.swing.JRadioButton rbRangoEdad;
    private javax.swing.JRadioButton rbTodasEdades;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Mètodo que recibe un panel con una grafica incrustada  y los muestra en 
     * la seccion correspondiente
     * @param PanelConGrafica 
     */
    public void recibeGrafica( ChartPanel PanelConGrafica, int numPanelSeleccionado ) {
        switch( numPanelSeleccionado ) {
            case 0:
                JpGraficaCurso.removeAll();
                JpGraficaCurso.add( PanelConGrafica );
                JpGraficaCurso.updateUI();
                break;
            case 1:
                jpGraficaEdades.removeAll();
                jpGraficaEdades.add( PanelConGrafica );
                jpGraficaEdades.updateUI();
                break;
            case 2:
                jpGraficaMedios.removeAll();
                jpGraficaMedios.add( PanelConGrafica );
                jpGraficaMedios.updateUI();
                break;
            case 3:
                jpGraficaDanzas.removeAll();
                jpGraficaDanzas.add( PanelConGrafica );
                jpGraficaDanzas.updateUI();
                break;
            case 4:
                PanelConGrafica.setSize(440,450);
                jpComparacionCurso1.removeAll();
                jpComparacionCurso1.add( PanelConGrafica );
                jpComparacionCurso1.updateUI();
                break;
            case 5:
                PanelConGrafica.setSize(430,450);
                jpComparacionCurso2.removeAll();
                jpComparacionCurso2.add( PanelConGrafica );
                jpComparacionCurso2.updateUI();
                break;
            case 6:
                PanelConGrafica.setSize(430,450);
                jpComparacionEdad1.removeAll();
                jpComparacionEdad1.add( PanelConGrafica );
                jpComparacionEdad1.updateUI();
                break;
            case 7:
                PanelConGrafica.setSize(430,450);
                jpComparacionEdad2.removeAll();
                jpComparacionEdad2.add( PanelConGrafica );
                jpComparacionEdad2.updateUI();
                break;
        }
    }
    
    /**
     * Método que permite cambiar la visibilidad de las opciones del comboBox
     * de la opción de rango de edad
     * @param bandera 
     */
    public void activarOpcionesEdad( boolean bandera ) {
        etRango.setVisible(bandera);
        cbRangoEdad.setVisible(bandera);
    }

    public void recibeComboBox(JComboBox cbPrimerCicloEscolar, JComboBox cbSegundoCicloEscolar,
            JComboBox cbPrimerCicloEscolarEdades, JComboBox cbSegundoCicloEscolarEdades) {
        this.cbPrimerCicloEscolar = cbPrimerCicloEscolar;
        this.cbSegundoCicloEscolar = cbSegundoCicloEscolar;
        this.cbPrimerCicloEscolar = cbPrimerCicloEscolar;
        this.cbSegundoCicloEscolar = cbSegundoCicloEscolar;
    }
    
}