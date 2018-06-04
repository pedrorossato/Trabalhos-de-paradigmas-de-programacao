import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GrafoMain extends Application {
    private Circle Vertice;
    private Line Aresta;
    private boolean estadoatual;
    private boolean adicionado;
    private Integer arestasSP=0;
    @Override
    public void start(Stage stage) throws Exception{
        BorderPane bp = new BorderPane();
        Pane pane = new Pane();
        Button Novo = new Button("Novo");
        Novo.setStyle("-fx-font: 15 arial; -fx-base: #99CC32;");
        Button Salvar = new Button("Salvar");
        Salvar.setStyle("-fx-font: 15 arial; -fx-base: #FFFF00;");
        Button Sair = new Button("Sair");
        Sair.setStyle("-fx-font: 15 arial; -fx-base: #ff0000;");
        RadioButton aresta = new RadioButton("Aresta");
        aresta.setStyle("-fx-font: 15 arial;");
        RadioButton vertice= new RadioButton("Vértice");
        vertice.setStyle("-fx-font: 15 arial;");
        ToggleGroup opcao = new ToggleGroup();
        opcao.getToggles().addAll(vertice,aresta);
        ToolBar toolBar = new ToolBar();
        ToolBar tbOpcoes = new ToolBar();
        tbOpcoes.setOrientation(Orientation.VERTICAL);
        Label tamanhoV = new Label("Tamanho do raio do vértice:");
        TextField tamanhoVN = new TextField ();
        tamanhoVN.setText("10");
        Label corV = new Label("Cor do vértice:");
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        Label corA = new Label("Cor da aresta:");
        ColorPicker colorPicker2 = new ColorPicker(Color.BLACK);
        Label tamanhoA = new Label("Tamanho da aresta:");
        TextField tamanhoAN = new TextField();
        tamanhoAN.setText("1");
        ArrayList<Circle> qtdV = new ArrayList<Circle>();
        ArrayList<Line> qtdL = new ArrayList<Line>();
        Label quantidadev = new Label();
        quantidadev.setFont(new Font("Cambria", 20));
        quantidadev.setText("Vértices: " + qtdV.size());
        Label quantidadel = new Label();
        quantidadel.setFont(new Font("Cambria", 20));
        quantidadel.setText("Arestas: " + qtdL.size());
        Label arestasobrepostas = new Label();
        arestasobrepostas.setFont(new Font("Cambria", 15));
        arestasobrepostas.setText("Arestas Sobrepostas: " + arestasSP);
        Novo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                qtdL.clear();
                qtdV.clear();
                arestasSP=0;
                arestasobrepostas.setText("Arestas Sobrepostas: " + arestasSP);
                quantidadev.setText("Vértices: 0");
                quantidadel.setText("Arestas: 0");
            }
        });
        Salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileWriter arq= null;
                try {
                    arq = new FileWriter("grafo.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintWriter gravarArq = new PrintWriter(arq);
                for(Circle Vertice : qtdV){
                    Color c = (Color) Vertice.getFill();
                    String hexC = toRGBCode(c);
                    gravarArq.printf("<svg height=\""+pane.getHeight()+ "\" width=\""+pane.getWidth()+"\"> <circle cx=\""+Vertice.getCenterX()+"\" cy=\""+Vertice.getCenterY()+"\" r=\""+Vertice.getRadius()+"\" stroke=\""+Vertice.getStroke()+"\" stroke-width=\""+Vertice.getStrokeWidth()+"\" fill=\""+ hexC +"\"/> </svg>");
                }
                for(Line Aresta : qtdL){
                    Color c = (Color)Aresta.getFill();
                    String hexL = toRGBCode(c);
                    gravarArq.printf("<svg height=\""+pane.getHeight()+"\" width=\""+pane.getWidth()+"\"> <line x1=\""+Aresta.getStartX()+"\" y1="+Aresta.getStartY()+"\" x2=\""+Aresta.getEndX()+"\" y2=\""+Aresta.getEndY()+"\" style=\"stroke:"+hexL+";stroke-width:"+Aresta.getStrokeWidth()+ "px\"/> </svg>");
                }
                try {
                    arq.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                    qtdV.add(Vertice);
                    quantidadev.setText("Vértices: " + qtdV.size());
                    pane.getChildren().add(Vertice);
                }else{
                    Aresta= new Line(e.getX(), e.getY(), e.getX(), e.getY());
                    for(Circle Vertice : qtdV) {
                        if (Math.pow(Aresta.getStartX() - Vertice.getCenterX(), 2) + Math.pow(Aresta.getStartY() - Vertice.getCenterY(), 2) < Math.pow(Vertice.getRadius(), 2)) {
                            Aresta.setStrokeWidth(Double.parseDouble(tamanhoAN.getText()));
                            Aresta.setStroke(colorPicker2.getValue());
                            qtdL.add(Aresta);
                            adicionado=true;
                            quantidadel.setText("Arestas: " + qtdL.size());
                            pane.getChildren().addAll(Aresta);
                        }
                    }
                }
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(!estadoatual){
                    Vertice.setRadius(Double.parseDouble(tamanhoVN.getText()));
                }else {
                    Aresta.setEndX(e.getX());
                    Aresta.setEndY(e.getY());
                }
            }
        });
        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (estadoatual) {
                    if (adicionado) {
                        System.out.println("Adicionado");
                        for (Line aresta : qtdL) {
                            if(aresta==Aresta)continue;
                            Shape intersect = Shape.intersect(Aresta, aresta);
                            if (intersect.getBoundsInLocal().getWidth() != -1) {
                                arestasSP++;
                            }
                        }
                        arestasobrepostas.setText("Arestas Sobrepostas: " + arestasSP);
                    }
                }
            }
        });


        toolBar.getItems().addAll(Novo, Salvar, Sair, new Separator());
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
                tamanhoAN,
                new Separator(),
                quantidadev,
                quantidadel,
                arestasobrepostas
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

    public static String toRGBCode(Color c )
    {
        return String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );


    }
    public static void main(String[] args){
        launch(args);
    }
}
