/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Observer.TableroObserved;
import Modelo.Observer.TableroObserver;

/**
 *
 * @author sofiagit
 */
public final class Tablero extends Thread implements TableroObserved {

    private final int SIZE = 12;
    private final int MINAS = 30;
    private Celda[][] tablero;
    private boolean gameOver;
    private int minasMarcadas;
    List<TableroObserver> observers;

    public Tablero() {
        reiniciarJuego();
    }

    @Override
    public void addObserver(TableroObserver observer) {
        observers.add((TableroObserver) observer);
    }

    @Override
    public void removeObserver(TableroObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Tablero tablero) {
         for (TableroObserver observer : observers) {
            observer.update(tablero); 
        }
    }

    private void notificarCambios() {
        notifyObservers(this);
    }

    // Método para inicializar el tablero y distribuir las minas
    public void reiniciarJuego() {
        tablero = new Celda[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tablero[i][j] = new Celda();
            }
        }
        colocarMinas();
        calcularMinasAdyacentes();
        gameOver = false;
        minasMarcadas = 0;
    }

    // Método para colocar minas aleatoriamente
    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < MINAS) {
            int fila = rand.nextInt(SIZE);
            int columna = rand.nextInt(SIZE);
            if (!tablero[fila][columna].isEsMina()) {
                tablero[fila][columna].setEsMina(true);
                minasColocadas++;
            }
        }
    }

    // Método para calcular las minas adyacentes a cada celda
    private void calcularMinasAdyacentes() {
        for (int fila = 0; fila < SIZE; fila++) {
            for (int columna = 0; columna < SIZE; columna++) {
                if (!tablero[fila][columna].isEsMina()) {
                    tablero[fila][columna].setMinasAdyacentes(contarMinasAdyacentes(fila, columna));
                }
            }
        }
    }

    // Método para contar las minas alrededor de una celda
    private int contarMinasAdyacentes(int fila, int columna) {
        int conteo = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if (posicionValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna].isEsMina()) {
                    conteo++;
                }
            }
        }
        return conteo;
    }

    // Método para verificar si una posición es válida en el tablero
    private boolean posicionValida(int fila, int columna) {
        return fila >= 0 && fila < SIZE && columna >= 0 && columna < SIZE;
    }

    // Método para descubrir una celda
    public void descubrirCelda(int fila, int columna) {
        if (!posicionValida(fila, columna) || tablero[fila][columna].isEstaRevelada() || tablero[fila][columna].isEstaMarcada() || gameOver) {
            return;
        }
        tablero[fila][columna].setEstaRevelada(true);

        // Si la celda tiene una mina, el juego termina
        if (tablero[fila][columna].isEsMina()) {
            gameOver = true;
            descubrirTodasLasCeldas();
            return;
        }

        // Si no tiene minas adyacentes, descubrir recursivamente
        if (tablero[fila][columna].getMinasAdyacentes() == 0) {
            descubrirCeldasAdyacentes(fila, columna);
        }
    }

    // Método recursivo para descubrir celdas sin minas adyacentes
    private void descubrirCeldasAdyacentes(int fila, int columna) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if (posicionValida(nuevaFila, nuevaColumna) && !tablero[nuevaFila][nuevaColumna].isEstaRevelada()) {
                    descubrirCelda(nuevaFila, nuevaColumna); //llamada recursiva
                }
            }
        }
    }

    // Método para descubrir todas las celdas cuando el juego termina
    private void descubrirTodasLasCeldas() {
        for (int fila = 0; fila < SIZE; fila++) {
            for (int columna = 0; columna < SIZE; columna++) {
                tablero[fila][columna].setEstaRevelada(true);
            }

        }
    }

    // Método para marcar una celda como sospechosa de tener una mina
    public void marcarCelda(int fila, int columna) {
        if (!posicionValida(fila, columna) || tablero[fila][columna].isEstaRevelada() || gameOver) {
            return;
        }
        tablero[fila][columna].setEstaMarcada(!tablero[fila][columna].isEstaMarcada());
        if (tablero[fila][columna].isEstaMarcada()) {
            minasMarcadas++;
        } else {
            minasMarcadas--;
        }
        notificarCambios();
    }

    // Otros métodos para acceder a información del modelo
    public boolean isGameOver() {
        return gameOver;
    }

    public int getMinasMarcadas() {
        return minasMarcadas;
    }

    public Celda getCelda(int fila, int columna) {
        return tablero[fila][columna];
    }

    @Override
    public void run() {
        while (!gameOver) {
            notificarCambios();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
