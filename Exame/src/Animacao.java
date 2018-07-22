import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Animacao extends AnimationTimer {
    public Snake snake;
    public Ovo ovo;
    public Pane pane;

    public Animacao(Snake s, Ovo o, Pane p) {
        snake = s;
        ovo = o;
        pane = p;
    }

    @Override
    public void handle(long now) {
        Random random = new Random();

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        for (int i = snake.cobra.size()-1; i > 0; i--) {
            snake.cobra.get(i).setCenterX(snake.cobra.get(i-1).getCenterX());
            snake.cobra.get(i).setCenterY(snake.cobra.get(i-1).getCenterY());
        }
        if(snake.direcao=='d') {
            snake.cobra.get(0).setCenterX(snake.cobra.get(0).getCenterX()+20);
        }
        if(snake.direcao=='e'){
            snake.cobra.get(0).setCenterX(snake.cobra.get(0).getCenterX()-20);
        }
        if(snake.direcao=='c'){
            snake.cobra.get(0).setCenterY(snake.cobra.get(0).getCenterY()-20);
        }
        if(snake.direcao=='b'){
            snake.cobra.get(0).setCenterY(snake.cobra.get(0).getCenterY()+20);
        }
        Shape intersect = Shape.intersect(ovo.getOvo(),snake.getCobra().get(0));
        if(intersect.getBoundsInLocal().getWidth() != -1){
            pane.getChildren().add(snake.addCircle());
            pane.getChildren().remove(ovo.getOvo());
            int Xovo = random.nextInt(759)+10;
            int Yovo = random.nextInt(559)+10;
            ovo = new Ovo(Xovo,Yovo);
            pane.getChildren().add(ovo.getOvo());
        }
        if(snake.cobra.get(0).getCenterX()>799 || snake.cobra.get(0).getCenterY()>599 || snake.cobra.get(0).getCenterX()<0 || snake.cobra.get(0).getCenterY()<0 ){
            this.stop();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Snake Game");
            alerta.setHeaderText("Você perdeu o jogo pois ultrapassou os limites");
            alerta.setContentText("Tente reiniciar o jogo");
            alerta.show();
        }
        for(int i=1;i<snake.getCobra().size();i++){
            Shape intersect1 =  Shape.intersect(snake.getCobra().get(0),snake.getCobra().get(i));
            if(intersect1.getBoundsInLocal().getWidth() != -1){
                this.stop();
                snake.getCobra().get(i).setFill(Color.RED);
                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
                alerta1.setTitle("Snake Game");
                alerta1.setHeaderText("Você perdeu o jogo pois bateu contra si mesma");
                alerta1.setContentText("Tente reiniciar o jogo");
                alerta1.show();
            }
        }
    }
}