import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.Random;


public class ViewMain extends Application {
    private Snake snake;
    private Ovo ovo;
    private Animacao fps;
    private Random random = new Random();

    @Override
    public void start(Stage stage) throws Exception{
        Pane pane = new Pane();
        BorderPane bp = new BorderPane();
        //Menu
        Menu Jogo = new Menu("Jogo");
        //Botões menu
        MenuItem Reiniciar= new MenuItem("Reiniciar");
        Reiniciar.setOnAction(event ->
                reiniciar(pane)
        );
        MenuItem Fechar = new MenuItem("Fechar");
        Fechar.setOnAction(event ->  System.exit(1));
        Jogo.getItems().addAll(Reiniciar,Fechar);
        //Bar
        MenuBar menubar = new MenuBar();
        menubar.getMenus().add(Jogo);

        bp.setCenter(pane);
        bp.setTop(menubar);
        Scene scene = new Scene(bp, 800, 600);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                    if(snake.getDirecao()=='b')
                        break;
                    snake.setDirecao('c');
                    break;
                case DOWN:
                    if(snake.getDirecao()=='c')
                        break;
                    snake.setDirecao('b');
                    break;
                case LEFT:
                    if(snake.getDirecao()=='d')
                        break;
                    snake.setDirecao('e');
                    break;
                case RIGHT:
                    if(snake.getDirecao()=='e')
                        break;
                    snake.setDirecao('d');
                    break;
            }
        });
        stage.getIcons().add(new Image("https://images.cdn2.stockunlimited.net/illustration/pixel-snake_2021048.png"));
        stage.setScene(scene);
        stage.setTitle("Jogo Snake JavaFX");
        stage.show();
        Alert gamestart = new Alert(Alert.AlertType.INFORMATION);
        gamestart.setTitle("Snake Game");
        gamestart.setHeaderText("Bem vindo ao jogo");
        gamestart.setContentText("Use as setas do teclado para jogar");
        gamestart.show();
        gamestart.setOnHidden(event ->
                reiniciar(pane)
        );
    }
    public void reiniciar(Pane pane) {
        System.out.println("Iniciando jogo...");
        pane.getChildren().clear();
        int Xovo = random.nextInt(759)+10;
        int Yovo = random.nextInt(559)+10;
        ovo = new Ovo(Xovo,Yovo);
        snake= new Snake(400,300,3);
        fps = new Animacao(snake,ovo,pane);
        for(Circle c : snake.getCobra()){
            pane.getChildren().add(c);
        }
        pane.getChildren().add(ovo.getOvo());
        fps.start();
    }
    public static void main (String[] args){
        launch(args);
    }

}
