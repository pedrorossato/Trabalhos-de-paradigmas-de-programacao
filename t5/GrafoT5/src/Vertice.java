import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Vertice {
    private Circle circulo;
    private Double centerx, centery;
    private Double raio;
    private Color cor;
    private double tamanho;
    public Vertice(double x, double y, double r, Color c ){
        circulo= new Circle(x,y,r,c);
        centerx= x;
        centery=y;
        raio=r;
        cor=c;
    }
    public Color getFill() {
        return cor;
    }
    public double getCenterX(){
        return centerx ;
    }
    public double getCenterY(){
        return centery;
    }
    public double getRadius() {
        return raio;
    }
    public void setRadius(double r){
        raio=r;
    }
}
