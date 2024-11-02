/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Tablero;
import Vista.FrmBuscaMinas;


/**
 *
 * @author sofia
 */
public class Controlador {
    private Tablero modelo;
    private FrmBuscaMinas vista;

    public Controlador(Tablero modelo, FrmBuscaMinas vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

     public Tablero getModelo() {
        return modelo;
    }
    
    public void iniciarJuego() {
           modelo.reiniciarJuego(); // Reinicia el modelo (Tablero)
        vista.actualizarTablero(); // Actualiza la vista para que muestre el tablero limpio
        vista.actualizarContadorMinas(modelo.getMinasMarcadas()); // Reiniciar contador de minas marcadas
    }

    public void celdaClickIzquierdo(int fila, int columna) {
        modelo.descubrirCelda(fila, columna);
        vista.actualizarTablero();
        if (modelo.isGameOver()) {
            vista.mostrarMensajeFinJuego("¡Has perdido!");
        }
    }

    public void celdaClickDerecho(int fila, int columna) {
        modelo.marcarCelda(fila, columna);
        vista.actualizarTablero();
        vista.actualizarContadorMinas(modelo.getMinasMarcadas());
    }
    
    
}

