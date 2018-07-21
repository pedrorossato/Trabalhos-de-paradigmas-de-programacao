import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Ovo extends Shape {
    public static final Color cordoovo = Color.BEIGE;
    private Ellipse Ovo;

    Ovo(double x, double y){
        Ovo = new Ellipse();
        Ovo.setCenterX(x);
        Ovo.setCenterY(y);
        Ovo.setRadiusX(6);
        Ovo.setRadiusY(8);
        Ovo.setFill(cordoovo);
        Ovo.setStroke(Color.BLACK);
    }

    public Ellipse getOvo() {
        return Ovo;
    }

    public void setOvo(Ellipse ovo) {
        Ovo = ovo;
    }

    public void setPosicao(double x, double y) {
        Ovo.setCenterX(x);
        Ovo.setCenterY(y);
    }
}
