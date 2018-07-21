import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


public class ViewMain extends Application {
    private Snake snake;
    private Ovo ovo;
    Random random = new Random();
    private int XSnake=400, YSnake=300;

    @Override
    public void start(Stage stage) throws Exception{
        Pane pane = new Pane();
        BorderPane bp = new BorderPane();
        //Gera o ovo em uma posição aleatória
        int Xovo = random.nextInt(799)+1;
        int Yovo = random.nextInt(599)+1;
        ovo = new Ovo(Xovo,Yovo);
        snake= new Snake(XSnake,YSnake,5);

        AnimationTimer fps = new AnimationTimer() {
            @Override
            public void handle(long now) {
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
                    snake.cobra.get(0).setCenterY(snake.cobra.get(0).getCenterY()+20);
                }
                if(snake.direcao=='b'){
                    snake.cobra.get(0).setCenterY(snake.cobra.get(0).getCenterY()-20);
                }
            }
        };
        fps.start();
        for(Circle c : snake.getCobra()){
            pane.getChildren().add(c);
        }
        pane.getChildren().add(ovo.getOvo());
        ToolBar tb = new ToolBar();
        bp.setCenter(pane);
        Scene scene = new Scene(bp, 800, 600);
        stage.getIcons().add(new Image("https://images.cdn2.stockunlimited.net/illustration/pixel-snake_2021048.png"));
        stage.setScene(scene);
        stage.setTitle("Jogo Snake JavaFX");
        stage.show();
    }
    public static void main (String[] args){
        launch(args);
    }

}
