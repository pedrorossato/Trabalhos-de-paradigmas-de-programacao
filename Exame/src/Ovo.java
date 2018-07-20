import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Ovo extends Ellipse {
    public static final Color cordoovo = Color.BEIGE;
    private Ellipse Ovo;

    Ovo(double x, double y){
        Ovo = new Ellipse();
        Ovo.setCenterX(x);
        Ovo.setCenterY(y);
        Ovo.setRadiusX(20);
        Ovo.setRadiusY(40);
        Ovo.setFill(cordoovo);
    }

    public void setPosicao(double x, double y) {
        Ovo.setCenterX(x);
        Ovo.setCenterY(y);
    }
}
