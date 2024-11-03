/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author chris
 */
public class Temporizador extends Thread {
      private int segundos;
    private boolean enEjecucion;

    public Temporizador() {
        segundos = 0;
        enEjecucion = false;
    }

    @Override
    public void run() {
        enEjecucion = true;
        while (enEjecucion) {
            try {
                Thread.sleep(1000); // Espera 1 segundo
                segundos++;
                System.out.println("Tiempo: " + segundos + " segundos");
                // Aquí puedes actualizar la interfaz gráfica llamando a un método específico para mostrar el tiempo
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("Temporizador interrumpido");
            }
        }
    }

    public void iniciar() {
        if (!enEjecucion) {
            this.start(); // Inicia el hilo del temporizador
        }
    }

    public void detener() {
        enEjecucion = false;
    }

    public int obtenerTiempo() {
        return segundos;        
}
}
