/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;


import Controlador.Controlador;
import Modelo.Celda;
import Modelo.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author chris
 */
public class FrmBuscaMinas extends javax.swing.JFrame {

     
     private static final int SIZE = 12;
    private JButton[][] botones;
    private Controlador controlador;
    private final Color COLOR_BOTON_NORMAL = new Color(200, 200, 200);
    
    /**
     * Creates new form FrmBuscaMinas
     * 
     * 
     */
//     public FrmBuscaMinas(ControladorBuscaminas controlador) {
//        this.controlador = controlador; 
//       
//     }
    public FrmBuscaMinas() {
        initComponents();
        
        quitarFondoBoton(Reiniciar);
           
        
        

        // Inicializa componentes personalizados
        inicializarComponentesPersonalizados();
        
    }
    
    
    public void quitarFondoBoton(JButton boton) {
    // Quitar el fondo
    boton.setContentAreaFilled(false);
    
    // Quitar el borde (opcional)
    boton.setBorderPainted(false);
   
}
    
     private void inicializarComponentesPersonalizados() {
          // Configura el panel de botones (el tablero del juego)
    PanelBotones.setLayout(new GridLayout(SIZE, SIZE));
    botones = new JButton[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(40, 40));
            boton.setBackground(COLOR_BOTON_NORMAL);
            final int fila = i;
            final int columna = j;

            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        controlador.celdaClickIzquierdo(fila, columna);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        controlador.celdaClickDerecho(fila, columna);
                    }
                }
            });

            botones[i][j] = boton;
            PanelBotones.add(boton);
        }
    }
    // Revalidar y repintar el panel para asegurarnos de que todos los botones se generen correctamente
    PanelBotones.revalidate();
    PanelBotones.repaint();
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
        // Asigna la acción al botón Reiniciar
        Reiniciar.addActionListener(e -> controlador.iniciarJuego());
    }

    public void actualizarTablero() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Celda celda = controlador.getModelo().getCelda(i, j);
                JButton boton = botones[i][j];

                if (celda.isEstaRevelada()) {
                    boton.setEnabled(false);
                    if (celda.isEsMina()) {
                        boton.setBackground(Color.RED);
                        boton.setText("💣");
                    } else {
                        boton.setBackground(Color.LIGHT_GRAY);
                        int minasAdyacentes = celda.getMinasAdyacentes();
                        if (minasAdyacentes > 0) {
                            boton.setText(String.valueOf(minasAdyacentes));
                            switch (minasAdyacentes) {
                                case 1:
                                    boton.setForeground(Color.BLUE);
                                    break;
                                case 2:
                                    boton.setForeground(new Color(0, 100, 0));
                                    break;
                                case 3:
                                    boton.setForeground(Color.RED);
                                    break;
                                default:
                                    boton.setForeground(Color.DARK_GRAY);
                                    break;
                            }
                        }
                    }
                } else if (celda.isEstaMarcada()) {
                    boton.setText("🚩");
                } else {
                    boton.setText("");
                    boton.setBackground(COLOR_BOTON_NORMAL);
                }
            }
        }
    }

    public void actualizarContadorMinas(int minasMarcadas) {
        numMinas.setText("Minas marcadas: " + minasMarcadas);
    }

    public void mostrarMensajeFinJuego(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Reiniciar = new javax.swing.JButton();
        numMinas = new javax.swing.JTextField();
        tiempo = new javax.swing.JTextField();
        PanelBotones = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 5));

        Reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/sonreir (1).png"))); // NOI18N
        Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(Reiniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(numMinas, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(329, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numMinas)
                    .addComponent(Reiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout PanelBotonesLayout = new javax.swing.GroupLayout(PanelBotones);
        PanelBotones.setLayout(PanelBotonesLayout);
        PanelBotonesLayout.setHorizontalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelBotonesLayout.setVerticalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReiniciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        SwingUtilities.invokeLater(() -> {
            Tablero modelo = new Tablero();
            FrmBuscaMinas vista = new FrmBuscaMinas();
            Controlador controlador = new Controlador(modelo, vista);

            // Asigna el controlador
            vista.setControlador(controlador);

            // Iniciar el juego
            vista.setVisible(true);
            controlador.iniciarJuego();
        });
        
        
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
            java.util.logging.Logger.getLogger(FrmBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuscaMinas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JButton Reiniciar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField numMinas;
    private javax.swing.JTextField tiempo;
    // End of variables declaration//GEN-END:variables
}
