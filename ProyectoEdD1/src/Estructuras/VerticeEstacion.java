/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author 
 */
public class VerticeEstacion {
    String nombreEstacion;
    public boolean esSucursal;
    Lista adyacentes;

    public VerticeEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
        this.esSucursal = false;
        this.adyacentes = new Lista();
    }
    
}
