import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Snake {
    public static Circle Cabeca;
    public static final Color CabecaCor = Color.BLACK;
    public static final Color CorpoCor = Color.WHITE;
    int tamanho = 1;
    String direcao;
    ArrayList<Circle> corpo = new ArrayList<>();

    public static Color getCabeca() {
        return CabecaCor;
    }

    public static Color getCorpo() {
        return CorpoCor;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
}
