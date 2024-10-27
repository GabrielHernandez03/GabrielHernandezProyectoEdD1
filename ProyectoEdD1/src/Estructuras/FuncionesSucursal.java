/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author
 */
public class FuncionesSucursal {

    Grafo g;

    public FuncionesSucursal(Grafo g) {
        this.g = g;
    }

    //procedimiento recursivo
    public Lista recorrerProfundidad(int v, boolean[] visitados, int tActual, Lista cubiertas) {
//se marca el vértice v como visitado
        visitados[v] = true;
//el tratamiento del vértice consiste únicamente en imprimirlo en pantalla
//        System.out.println(this.g.vertices[v].nombreEstacion);
        cubiertas.insertar(this.g.vertices[v]);

        if (tActual != this.g.t) {
//se examinan los vértices adyacentes a v para continuar el recorrido
            for (int i = 0; i < this.g.max; i++) {
//                System.out.println(this.g.vertices[v].nombreEstacion + "-" + this.g.vertices[i].nombreEstacion + "   " + this.g.conectados(v, i));
                if ((v != i) && (!visitados[i]) && this.g.conectados(v, i) && !this.g.vertices[i].nombreEstacion.equals("")) {
                    cubiertas = recorrerProfundidad(i, visitados, tActual + 1, cubiertas);
                }
            }
        }
        return cubiertas;
    }
//procedimiento no recursivo

    public Lista profundidad(String n) {
        boolean visitados[] = new boolean[this.g.max];
        Lista cubiertas = new Lista();
        for (int i = 0; i < this.g.max; i++) //inicializar vector con campos false
        {
            visitados[i] = false;
        }
        int i = this.g.buscarIndex(n);
        return recorrerProfundidad(i, visitados, 0, cubiertas);
    }

    public Lista amplitud(String n) {
        Cola cola = new Cola();
        Lista cubiertos = new Lista();
        boolean visitados[] = new boolean[g.max];
        int v; //vértice actual
        int nivel = 0; // Nivel actual en el BFS
        int nodosEnNivelActual = 1; // Nodos en el nivel actual
        int nodosEnNivelSiguiente = 0; // Nodos en el siguiente nivel

        // Se inicializa el vector visitados [] a false
        for (int i = 0; i < g.max; i++) {
            visitados[i] = false;
        }

        // El recorrido en amplitud se inicia en el vértice indicado
        // Se pone en la cola el vértice de partida y se marca como visitado
        int i = g.buscarIndex(n);
        cola.encolar(g.vertices[i]);
        visitados[i] = true;
//        System.out.println("645555555555555555555555555555555555555555555555555555555555555555555555555");

        while (!cola.isEmpty() && nivel <= this.g.t) {
            VerticeEstacion d = cola.desencolar(); // Desencolar y tratar el vértice
//            System.out.println("-" + d.nombreEstacion);
            v = g.buscarIndex(d.nombreEstacion);
            cubiertos.insertar(d);

            // Procesamos los nodos adyacentes
            for (int j = 0; j < g.max; j++) {
                if ((v != j) && g.conectados(v, j) && (!visitados[j]) && !this.g.vertices[j].nombreEstacion.equals("")) {
                    System.out.println("Nombre " + this.g.vertices[j].nombreEstacion);
                    cola.encolar(this.g.vertices[j]);
                    visitados[j] = true;
                    nodosEnNivelSiguiente++; // Contamos los nodos en el siguiente nivel
                }
            }

            nodosEnNivelActual--; // Disminuimos el contador de nodos en el nivel actual

            // Si hemos procesado todos los nodos en el nivel actual, aumentamos el nivel
            if (nodosEnNivelActual == 0) {
                nivel++; // Aumentamos el nivel
                nodosEnNivelActual = nodosEnNivelSiguiente; // Actualizamos el contador de nodos en el nivel actual
                nodosEnNivelSiguiente = 0; // Reiniciamos el contador de nodos en el siguiente nivel
            }
        }

        return cubiertos;
    }

    public Lista estacionesSinCubrir() {
        boolean visitados[] = new boolean[g.max];
        boolean cubiertos[] = new boolean[g.max];
        for (int i = 0; i < g.max; i++) {
            if (this.g.vertices[i].esSucursal) {
                Lista recorrido = this.profundidad(this.g.vertices[i].nombreEstacion);
//                System.out.println(recorrido.imprimir());
                NodoEstacion aux = recorrido.primero;
                while (aux != null) {
                    int j = g.buscarIndex(aux.estacion.nombreEstacion);
                    cubiertos[j] = true;
                    aux = aux.sig;
                }
            }
        }
        Lista sinCubrir = new Lista();
        for (int i = 0; i < g.max; i++) {
            if (!cubiertos[i] && !g.vertices[i].nombreEstacion.equals("")) {
                sinCubrir.insertar(g.vertices[i]);
            }
        }
//        System.out.println(sinCubrir.imprimir());
        return sinCubrir;
    }

    public Lista sugerir(Lista sinCubrir) {
        NodoEstacion aux = sinCubrir.primero;
        Lista sugeridos = new Lista();
        while (sinCubrir.primero != null) {
            aux = sinCubrir.primero;
            aux.estacion.esSucursal = true;
            sugeridos.insertar(aux.estacion);
            sinCubrir = this.estacionesSinCubrir();
        }
        NodoEstacion temp = sugeridos.primero;

        while (temp != null) {
            temp.estacion.esSucursal = false;
            temp = temp.sig;
        }
//        System.out.println(sugeridos.imprimir());
        for (int i = 0; i < this.g.max; i++) {
//            if (this.g.vertices[i].esSucursal) {
//                System.out.println(this.g.vertices[i].nombreEstacion);
//            }
        }
        return sugeridos;
    }

    public Grafo leerArchivo(String ruta) {
        String filePath = ruta;
        String jsonContent = readJsonFile(filePath);
        if (jsonContent != null) {
            JsonElement jsonElement = JsonParser.parseString(jsonContent);
            return printLineasYEstaciones(jsonElement);
        }
        return null;
    }

    public static String readJsonFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            return jsonContent.toString();
        } catch (IOException e) {
            System.out.println("Error leyendo archivo JSON: " + e.getMessage()); // 
            return null;
        }
    }

    public static Grafo printLineasYEstaciones(JsonElement jsonElement) {
        Grafo grafo = new Grafo(100, 3);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (String key : jsonObject.keySet()) {
                JsonArray lineas = jsonObject.getAsJsonArray(key);

                for (JsonElement lineaElement : lineas) {
                    JsonObject linea = lineaElement.getAsJsonObject();
                    for (String nombreLinea : linea.keySet()) {
//                    System.out.println("Línea: " + nombreLinea);
                        JsonArray estaciones = linea.getAsJsonArray(nombreLinea);
                        String[] e = new String[estaciones.size()];
                        int i = 0;
                        for (JsonElement estacionElement : estaciones) {
                            if (estacionElement.isJsonObject()) {
                                // Si es un objeto, imprimimos ambos nombres
                                JsonObject estacionObj = estacionElement.getAsJsonObject(); // Convertimos el elemento a objeto
                                for (String estacionNombre : estacionObj.keySet()) {
                                    // Imprimimos el nombre de ambas estaciones
                                    e[i] = estacionNombre + "/" + estacionObj.get(estacionNombre).getAsString();
                                }
                            } else {
                                // Si es una cadena, simplemente imprimimos el nombre de la estación

                                String nombreEstacion = estacionElement.getAsString();
                                e[i] = nombreEstacion;
//                            System.out.println("Estación: " + nombreEstacion);
                            }
                            i++;
                        }
                        for (String nombre : e) {
                            if (!nombre.contains("/")) {
                                VerticeEstacion v = grafo.buscarVertice(nombre);
                                if (v == null) {
                                    grafo.añadirVertice(nombre);
                                }
                            } else {
                                String[] n = nombre.split("/");
//                            System.out.println("+-+-+---++--------------++--++-+-+-+--++--++-+-+-+-+-+-");
//                            System.out.println(nombre);
                                boolean found = false;
                                for (int j = 0; j < grafo.max; j++) {
                                    if (grafo.vertices[j].nombreEstacion.trim().contains("/")) {
//                                    System.out.println(n[0] + ",,," + n[1]);
//                                    System.out.println(grafo.vertices[i].nombreEstacion);
                                    }
                                    if (grafo.vertices[j].nombreEstacion.trim().contains(n[0].trim()) && grafo.vertices[j].nombreEstacion.trim().contains(n[1].trim())) {
                                        found = true;
                                        break;
                                    }
                                }
//                            System.out.println(n[0] + n[1]);
//                            System.out.println("******************************************************");
                                if (!found) {
                                    grafo.añadirVertice(nombre);
                                }
                            }
                        }
//                    System.out.println("\n\nARISTAAAAAS");
                        int j = 0;
                        for (int k = 1; k < e.length; k++) {
                            grafo.añadirArista(e[j], e[k]);
//                            System.out.println(e[j] + "    --->    " + e[k] + "  -> " + grafo.conectados(grafo.buscarIndex(e[j]), grafo.buscarIndex(e[k])));

                            j++;
                        }
//                        System.out.println("\n\n");
                    }
                }
            }
        } else {
            // Mensaje de error si el contenido no es un objeto JSON válido
            System.out.println("El contenido no es un objeto JSON válido.");
        }
        return grafo;
    }
}
