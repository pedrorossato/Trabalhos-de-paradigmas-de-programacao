import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.control.ColorPicker;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;




public class GrafoMain extends Application {
    private Circle Vertice;
    private Line Aresta;
    private boolean estadoatual=false;
    @Override
    public void start(Stage stage) throws Exception{
        BorderPane bp = new BorderPane();
        Pane pane = new Pane();
        Button Novo = new Button("Novo");
        Button Salvar = new Button("Salvar");
        Button Sair = new Button("Sair");
        RadioButton aresta = new RadioButton("Aresta");
        RadioButton vertice= new RadioButton("Vértice");
        ToggleGroup opcao = new ToggleGroup();
        opcao.getToggles().addAll(vertice,aresta);
        ToolBar toolBar = new ToolBar();
        ToolBar tbOpcoes = new ToolBar();
        tbOpcoes.setOrientation(Orientation.VERTICAL);
        Label tamanhoV = new Label("Tamanho do raio do vértice:");
        TextField tamanhoVN = new TextField ();
        tamanhoVN.setText("10");
        Label corV = new Label("Cor do vértice:");
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        Label corA = new Label("Cor da aresta:");
        ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(Color.BLACK);
        Label tamanhoA = new Label("Tamanho da aresta:");
        TextField tamanhoAN = new TextField();
        tamanhoAN.setText("1");
        Novo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
            }
        });
        Sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        aresta.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                estadoatual=true;
            }
        });
        vertice.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                estadoatual=false;
            }
        });
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (!estadoatual) {
                    Vertice = new Circle(e.getX(), e.getY(),Double.parseDouble(tamanhoVN.getText()), colorPicker.getValue());
                    pane.getChildren().add(Vertice);
                }else{
                    Aresta= new Line(e.getX(), e.getY(), e.getX(), e.getY());
                    Aresta.setStrokeWidth(Double.parseDouble(tamanhoAN.getText()));
                    Aresta.setStroke(colorPicker2.getValue());
                    pane.getChildren().addAll(Aresta);
                }
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(!estadoatual){
                    Vertice.setRadius(Double.parseDouble(tamanhoVN.getText()));
                }else{
                    Aresta.setEndX(e.getX());
                    Aresta.setEndY(e.getY());
                }
            }
        });
        toolBar.getItems().addAll( new Separator(), Novo, Salvar, Sair, new Separator());
        tbOpcoes.getItems().addAll(
                new Separator(),
                vertice,
                aresta,
                new Separator(),
                corV,
                colorPicker,
                corA,
                colorPicker2,
                new Separator(),
                tamanhoV,
                tamanhoVN,
                new Separator(),
                tamanhoA,
                tamanhoAN
        );
        bp.setCenter(pane);
        bp.setTop(toolBar);
        bp.setLeft(tbOpcoes);

        Scene scene = new Scene(bp, 800, 600);
        stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Complete-edge-coloring.svg/350px-Complete-edge-coloring.svg.png"));
        stage.setScene(scene);
        stage.setTitle("Editor de Grafos");
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
