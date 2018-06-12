import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Grafo {
    public ArrayList<Vertice> vertices;
    public ArrayList<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();

    }
    public void adicionavertice(int x, int y){
        Vertice vertice = new Vertice(x,y);
        vertices.add(vertice);
    }
    public void adicionaaresta(Vertice vinicio, Vertice vfim){
        Aresta aresta = new Aresta(vinicio,vfim);
        arestas.add(aresta);
    }
    public Vertice selecionado(double x, double y){
        for(Vertice v: vertices){
            Shape intersect = Shape.intersect(v.returnshape(), new Circle(x, y,1));
            if (intersect.getBoundsInLocal().getWidth() != -1)
                return v;
        }
        return null;
    }
    public int interseccoes() {
        int intersec = 0;
        for (Aresta e : arestas)
            intersec += e.checarInterseccao(arestas);
        return intersec;
    }
}
