import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Snake {
    private static final Color CabecaCor = Color.BLACK;
    private static final Color CorpoCor = Color.GREEN;
    public int tamanho;
    public char direcao = 'd';
    public Animacao fps;
    ArrayList<Circle> cobra = new ArrayList<>();


    Snake(int xcabeca, int ycabeca, int tam) {
        tamanho=tam;
        for (int i = 0; i < tamanho; i++) {
            Circle c = new Circle();
            cobra.add(c);
            if (cobra.get(0) == c) {
                c.setCenterX(xcabeca);
                c.setCenterY(ycabeca);
                c.setFill(CabecaCor);
                c.setRadius(10);
                } else {
                c.setCenterX(cobra.get(i - 1).getCenterX() - 20);
                c.setCenterY(cobra.get(i - 1).getCenterY());
                c.setFill(CorpoCor);
                c.setRadius(10);
            }
        }
    }
    public Circle addCircle (){
        Circle c = new Circle();
        c.setFill(CorpoCor);
        c.setRadius(10);
        cobra.add(c);
        return c;
    }

    public void setDirecao(char direcao) {
        this.direcao = direcao;
    }
    public char getDirecao(){
        return this.direcao;
    }


    public void limpacobra(){
        cobra.clear();
    }
    public ArrayList<Circle> getCobra() {
        return cobra;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
