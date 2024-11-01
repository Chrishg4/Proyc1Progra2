/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo.Observer;

import Modelo.Tablero;

/**
 *
 * @author sofia
 */
public interface TableroObserved {
    public void addObserver(TableroObserver observer);

    public void removeObserver(TableroObserver observer);

    public void notifyObservers(Tablero tablero);
}
