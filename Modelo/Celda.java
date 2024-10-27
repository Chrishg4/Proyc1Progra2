/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author sofia
 */
public class Celda {
    private boolean esMina;
    private boolean estaRevelada;
    private boolean estaMarcada;
    private int minasAdyacentes;

    public Celda() {
        this.esMina = false;
        this.estaRevelada = false;
        this.estaMarcada = false;
        this.minasAdyacentes = 0;
    }

    public boolean isEsMina() {
        return esMina;
    }

    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    public boolean isEstaRevelada() {
        return estaRevelada;
    }

    public void setEstaRevelada(boolean estaRevelada) {
        this.estaRevelada = estaRevelada;
    }

    public boolean isEstaMarcada() {
        return estaMarcada;
    }

    public void setEstaMarcada(boolean estaMarcada) {
        this.estaMarcada = estaMarcada;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    
  
    
    
}
