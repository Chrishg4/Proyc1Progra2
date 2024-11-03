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
    private boolean tempIniciado;  // Nueva bandera para saber si el temporizador ha comenzado

    public Controlador(Tablero modelo, FrmBuscaMinas vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

     public Tablero getModelo() {
        return modelo;
    }
    
    public void iniciarJuego() {
          if (temp != null && temp.isAlive()) {
            temp.detener();  // Detenemos el temporizador anterior si está corriendo
        }
        tempIniciado = false;  // Reiniciamos la bandera cuando se reinicia el juego
        modelo.reiniciarJuego();  // Reiniciar el modelo (Tablero)
        vista.actualizarTablero();  // Actualizar la vista para mostrar el tablero limpio
        vista.actualizarContadorMinas(modelo.getMinasMarcadas());  // Reiniciar contador de minas marcadas
    }

    public void celdaClickIzquierdo(int fila, int columna) {
          IniTemp();  // Inicia el temporizador si no ha sido iniciado
        modelo.descubrirCelda(fila, columna);
        vista.actualizarTablero();
        if (modelo.isGameOver()) {
            temp.detener();
            vista.mostrarMensajeFinJuego("¡Has perdido!");
        }
    }

    public void celdaClickDerecho(int fila, int columna) {
         IniTemp();  // Inicia el temporizador si no ha sido iniciado
        modelo.marcarCelda(fila, columna);
        vista.actualizarTablero();
        vista.actualizarContadorMinas(modelo.getMinasMarcadas());
    }
    //inicia el temporizador solo si no se ha iniciado
    private void IniTemp() {
        if (!tempIniciado) {
            temp = new Temporizador();
            temp.start();  // Iniciamos el temporizador
            tempIniciado = true;  // Marcamos que el temporizador ya ha comenzado
            new Thread(() -> {
                while (temp.isAlive()) {
                    vista.actualizarTiempo(temp.obtenerTiempo());  // Actualiza el tiempo en la vista
                    try {
                        Thread.sleep(1000);  // Actualizar cada segundo
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
    
    
}

