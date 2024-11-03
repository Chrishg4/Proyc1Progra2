/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Tablero;
import Modelo.Temporizador;
import Vista.FrmBuscaMinas;


/**
 *
 * @author sofia
 */
public class Controlador {
    private Tablero modelo;
    private FrmBuscaMinas vista;
    private Temporizador temp;

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
          temp = new Temporizador(); // Reiniciar el temporizador
        temp.start(); // Iniciar el temporizador
        new Thread(() -> {
            while (temp.isAlive()) {
                vista.actualizarTiempo(temp.obtenerTiempo()); // Actualiza el tiempo en la vista
                try {
                    Thread.sleep(1000); // Actualizar cada segundo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    public void celdaClickIzquierdo(int fila, int columna) {
        modelo.descubrirCelda(fila, columna);
        vista.actualizarTablero();
        if (modelo.isGameOver()) {
            temp.detener();
            vista.mostrarMensajeFinJuego("¡Has perdido!");
        }
    }

    public void celdaClickDerecho(int fila, int columna) {
        modelo.marcarCelda(fila, columna);
        vista.actualizarTablero();
        vista.actualizarContadorMinas(modelo.getMinasMarcadas());
    }
    
    
}

