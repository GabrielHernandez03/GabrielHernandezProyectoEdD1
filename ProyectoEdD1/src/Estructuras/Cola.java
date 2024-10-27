/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author
 */
public class Cola {

    NodoEstacion cabeza;
    NodoEstacion cola;

    public Cola() {
        this.cabeza = this.cola = null;
    }
    
    public boolean isEmpty(){
        return this.cabeza == null;
    }
    public void encolar(VerticeEstacion n) {
        if (this.cabeza == null) {
            this.cabeza = this.cola = new NodoEstacion(n);
        } else {
            this.cola.sig = new NodoEstacion(n);
            this.cola = this.cola.sig;
        }
    }

    public VerticeEstacion desencolar() {
        if (this.cabeza == null) {
            return null;
        } else {
            NodoEstacion aux = this.cabeza;
            this.cabeza = this.cabeza.sig;
            return aux.estacion;
        }
    }
}
