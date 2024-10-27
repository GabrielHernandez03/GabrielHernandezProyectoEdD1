/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author 
 */
public class Lista {
    NodoEstacion primero;
    NodoEstacion ultimo;
    
    public Lista(){
        this.primero = this.ultimo = null;
      
    }
    
    public void insertar(VerticeEstacion v){
        NodoEstacion nuevo = new NodoEstacion(v);
        if(this.primero == null){
            this.primero = this.ultimo = nuevo;
        }else{
            this.ultimo.sig = nuevo;
            this.ultimo = nuevo;
        }
    }
    
    public VerticeEstacion buscar(String n){
        String r[] = n.split("/");
        
        if(this.primero != null){
            NodoEstacion temp = this.primero;
            
            while(temp != null && (!temp.estacion.nombreEstacion.trim().equals(r[0].trim()) && (!temp.estacion.nombreEstacion.trim().contains(r[0].trim())))){
                
//                System.out.println("\n\n"+r[0] + " --> "+ temp.estacion.nombreEstacion+ "     " +temp.estacion.nombreEstacion.trim().contains("/")  + "   " + temp.estacion.nombreEstacion.trim().contains(r[0].trim()));
                
                temp = temp.sig;
            }
            if(temp != null){
                return temp.estacion;
            }
        }
            return null;
        
    }
    
    public String imprimir(){
        String a = "";
        NodoEstacion temp = this.primero;
            while(temp != null){
                a += temp.estacion.nombreEstacion + "\n";
                temp = temp.sig;
            }
        return a;
    }
}
