import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Vertice extends Node {
    private double x, y;
    private ArrayList<Aresta> arestasconectadas;
    private Circle vertice;

    public Vertice(double X, double Y) {
        x = X;
        y = Y;
        arestasconectadas = new ArrayList<Aresta>();
        vertice = new Circle(X, Y, 16);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double X) {
        x=X;
        vertice.setCenterX(x);

        for (Aresta aresta : arestasconectadas) {
            if (aresta.getStart() == this)
                aresta.getLine().setStartX(X);
            else
                aresta.getLine().setEndX(X);
        }
    }

    public void setY(double Y) {
        y=Y;
        vertice.setCenterY(y);
        for (Aresta aresta : arestasconectadas) {
            if (aresta.getStart() == this)
                aresta.getLine().setStartY(Y);
            else
                aresta.getLine().setEndY(Y);
        }
    }

    public Circle returnshape() {
        return vertice;
    }

    public void conectararesta(Aresta aresta) {
        arestasconectadas.add(aresta);
    }
}
