/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author
 */
public class Grafo {

    VerticeEstacion[] vertices;
    int max;
    int actual;
    int t;

    public Grafo(int max, int t) {
        this.max = max;
        this.actual = 0;
        this.vertices = new VerticeEstacion[this.max];
        for (int i = 0; i < this.max; i++) {
            vertices[i] = new VerticeEstacion("");
        }
        this.t = t;
    }

    public void añadirVertice(String v) {
        for (int i = 0; i < this.max; i++) {
            if (this.vertices[i].nombreEstacion.equals("")) {
                this.vertices[i].nombreEstacion = v;
                break;
            }
        }
    }

    public void añadirArista(String n1, String n2) {
        VerticeEstacion v1 = null;
        VerticeEstacion v2 = null;
        v1 = this.buscarVertice(n1);
        v2 = this.buscarVertice(n2);
        if (v1 != null && v2 != null) {
            v1.adyacentes.insertar(v2);
            v2.adyacentes.insertar(v1);
        } else {

        }
    }

    public VerticeEstacion buscarVertice(String n) {
        String[] r = n.split("/");
        for (int i = 0; i < this.max; i++) {
            if (this.vertices[i].nombreEstacion.equals(r[0]) || (this.vertices[i].nombreEstacion.contains("/") && this.vertices[i].nombreEstacion.contains(r[0]))) {
                return this.vertices[i];
            }
        }
        return null;
    }

    public boolean conectados(int v1, int v2) {
        return this.vertices[v1].adyacentes.buscar(this.vertices[v2].nombreEstacion) != null;
    }

    public int buscarIndex(String n) {
        String[] r = n.split("/");

        for (int i = 0; i < this.max; i++) {
//            System.out.println(this.vertices[i].nombreEstacion + "   ->   " + n + "     " + this.vertices[i].nombreEstacion.trim().contains("/") + " " + this.vertices[i].nombreEstacion.trim().contains(n.trim()));
            if (this.vertices[i].nombreEstacion.equals(r[0]) || (this.vertices[i].nombreEstacion.trim().contains("/") && this.vertices[i].nombreEstacion.trim().contains(r[0].trim()))) {
                return i;
            }
        }
        return -1;
    }

    public String print() {
        String r = "";
        for (int i = 0; i < this.max; i++) {
            if (!this.vertices[i].nombreEstacion.equals("")) {
                r += this.vertices[i].nombreEstacion + " --> " + this.vertices[i].adyacentes.imprimir() + "\n";
            }
        }
        return r;
    }

}
