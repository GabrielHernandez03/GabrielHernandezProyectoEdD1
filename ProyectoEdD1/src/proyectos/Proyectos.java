/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectos;

import Estructuras.FuncionesSucursal;
import Estructuras.Grafo;
import Estructuras.NodoEstacion;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import Estructuras.MenuInicial;
/**
 *
 * @author 
 */
public class Proyectos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Grafo g = new Grafo(100, 3);
//
//        g.añadirVertice("A");
//        g.añadirVertice("B");
//        g.añadirVertice("C");
//        g.añadirVertice("D");
//        g.añadirVertice("E");
//        g.añadirVertice("F");
//        g.añadirVertice("G");
//        g.añadirVertice("H");
//        g.añadirVertice("I");
//        
//        g.añadirArista("A", "B");
//        g.añadirArista("B", "C");
//        g.añadirArista("C", "D");
//        g.añadirArista("D", "E");
//        g.añadirArista("E", "F");
//        g.añadirArista("F", "G");
//        g.añadirArista("G", "H");
//        g.añadirArista("H", "I");
//        g.buscarVertice("E").esSucursal = true;
//        System.out.println(g.print());






FuncionesSucursal f = new FuncionesSucursal(null);

//        Grafo g = f.leerArchivo("");
//        System.out.println(g.print());
        MenuInicial m = new MenuInicial(f.leerArchivo("Caracas.json"));

        
        // TODO code application logic here
    }

}
