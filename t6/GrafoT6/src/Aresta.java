import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Aresta {
    private Vertice Vinicio;
    private Vertice Vfim;
    private Line linha;

    public Aresta(Vertice vinicio, Vertice vfim) {
        Vinicio = vinicio;
        Vfim = vfim;
        linha = new Line(Vinicio.getX(), Vinicio.getY(), Vfim.getX(), Vfim.getY());
    }

    public Vertice getStart() {
        return Vinicio;
    }

    public Vertice getEnd() {
        return Vfim;
    }

    public Line getLine() {
        return linha;
    }

    public int checarInterseccao(ArrayList<Aresta> arestas) {
        for (Aresta aresta : arestas) {
            if (aresta.getStart() == Vinicio || aresta.getEnd() == Vinicio || aresta.getStart() == Vfim || aresta.getEnd() == Vfim)
                continue;
            Shape intersect = Shape.intersect(linha, aresta.getLine());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                return 1;
            }
        }
        return 0;
    }
}