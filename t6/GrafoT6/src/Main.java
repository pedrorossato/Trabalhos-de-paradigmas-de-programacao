import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.util.Collection;

public class Main extends Application {
    private Vertice vertice;
    private Aresta aresta;
    private Grafo grafo;
    private int nivel=1;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp= new BorderPane();
        Pane pane= new Pane();
        Grafo grafo = new Grafo();
        ToolBar toolBar = new ToolBar();
        Label level=new Label();
        level.setText("Nível:"+ nivel);

        if(nivel==1){
            grafo.adicionavertice(200,60);
            grafo.adicionavertice(500,60);
            grafo.adicionavertice(50,300);
            grafo.adicionavertice(200,520);
            grafo.adicionavertice(500,520);
            grafo.adicionavertice(650,300);
            for(Vertice v: grafo.vertices){
                pane.getChildren().add(v.returnshape());
            }

        }
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vertice=grafo.selecionado(event.getX(),event.getY());
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouse) {
                for(Vertice vertice : grafo.vertices) {
                    vertice.setX(mouse.getX());
                    vertice.setY(mouse.getY());
                }
            }
        });

        bp.setCenter(pane);
        bp.setLeft(toolBar);
        toolBar.getItems().add(level);

        Scene scene = new Scene(bp, 800, 600);
        stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Complete-edge-coloring.svg/350px-Complete-edge-coloring.svg.png"));
        stage.setScene(scene);
        stage.setTitle("Planarity Puzzle");
        stage.show();
    }

    public static void main(String[]args){
        launch(args);
    }
}
