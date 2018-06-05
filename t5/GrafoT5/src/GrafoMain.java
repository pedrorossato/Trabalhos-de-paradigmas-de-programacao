import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GrafoMain extends Application {
    private Circle Vertice;
    private Line Aresta;
    private boolean ehAresta;
    private boolean adicionado;
    private Integer arestasSP = 0;
    private double r;
    private double g;
    private double b;

    @Override
    public void start(Stage stage) throws Exception {
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
        RadioButton vertice = new RadioButton("Vértice");
        vertice.setStyle("-fx-font: 15 arial;");
        ToggleGroup opcao = new ToggleGroup();
        opcao.getToggles().addAll(vertice, aresta);
        ToolBar toolBar = new ToolBar();
        ToolBar tbOpcoes = new ToolBar();
        tbOpcoes.setOrientation(Orientation.VERTICAL);
        Label tamanhoV = new Label("Tamanho do raio do vértice:");
        TextField tamanhoVN = new TextField();
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
        ArrayList<String>coreshexV = new ArrayList<String>();
        ArrayList<String>coreshexA = new ArrayList<String>();
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
                coreshexA.clear();
                coreshexV.clear();
                arestasSP = 0;
                arestasobrepostas.setText("Arestas Sobrepostas: " + arestasSP);
                quantidadev.setText("Vértices: 0");
                quantidadel.setText("Arestas: 0");
            }
        });
        Salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileWriter arq = null;
                try {
                    arq = new FileWriter("grafo.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintWriter gravarArq = new PrintWriter(arq);
                for (int i=0;i<qtdV.size();i++) {
                        gravarArq.printf("<svg height=\"" + pane.getHeight() + "\" width=\"" + pane.getWidth() + "\"> <circle cx=\"" + Vertice.getCenterX() + "\" cy=\"" + Vertice.getCenterY() + "\" r=\"" + Vertice.getRadius() + "\" stroke=\"" + Vertice.getStroke() + "\" stroke-width=\"" + Vertice.getStrokeWidth() + "\" fill=\"" +coreshexV.get(i)+"\"/> </svg>");
                }
                for (int i=0;i<qtdL.size();i++) {
                    gravarArq.printf("<svg height=\"" + pane.getHeight() + "\" width=\"" + pane.getWidth() + "\"> <line x1=\"" + Aresta.getStartX() + "\" y1=" + Aresta.getStartY() + "\" x2=\"" + Aresta.getEndX() + "\" y2=\"" + Aresta.getEndY() + "\" style=\"stroke:" + coreshexA.get(i) + ";stroke-width:" + Aresta.getStrokeWidth() + "px\"/> </svg>");
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
                ehAresta = true;
            }
        });
        vertice.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ehAresta = false;
            }
        });
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (!ehAresta) {
                    Vertice = new Circle(e.getX(), e.getY(), Double.parseDouble(tamanhoVN.getText()), colorPicker.getValue());
                    r = colorPicker.getValue().getRed();
                    g = colorPicker.getValue().getGreen();
                    b = colorPicker.getValue().getBlue();
                    coreshexV.add(toRGBCode(r,g,b));
                    qtdV.add(Vertice);
                    quantidadev.setText("Vértices: " + qtdV.size());
                    pane.getChildren().add(Vertice);
                } else {
                    Aresta = new Line(e.getX(), e.getY(), e.getX(), e.getY());
                    for (Circle Vertice : qtdV) {
                        if (Math.pow(Aresta.getStartX() - Vertice.getCenterX(), 2) + Math.pow(Aresta.getStartY() - Vertice.getCenterY(), 2) < Math.pow(Vertice.getRadius(), 2)) {
                            Aresta.setStartX(Vertice.getCenterX());
                            Aresta.setStartY(Vertice.getCenterY());
                            Aresta.setStrokeWidth(Double.parseDouble(tamanhoAN.getText()));
                            Aresta.setStroke(colorPicker2.getValue());
                            pane.getChildren().addAll(Aresta);
                        }
                    }
                }
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (!ehAresta) {
                    Vertice.setRadius(Double.parseDouble(tamanhoVN.getText()));
                } else {
                    Aresta.setEndX(e.getX());
                    Aresta.setEndY(e.getY());
                }
            }
        });
        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ehAresta) {
                    adicionado = false;
                    for (Circle vertice : qtdV) {
                        if (Math.pow(Aresta.getEndX() - vertice.getCenterX(), 2) + Math.pow(Aresta.getEndY() - vertice.getCenterY(), 2) < Math.pow(vertice.getRadius(), 2)) {
                            Aresta.setEndX(vertice.getCenterX());
                            Aresta.setEndY(vertice.getCenterY());
                            qtdL.add(Aresta);
                            quantidadel.setText("Arestas: " + qtdL.size());
                            adicionado = true;
                        }
                    }
                    if (adicionado) {
                        r = colorPicker2.getValue().getRed();
                        g = colorPicker2.getValue().getGreen();
                        b = colorPicker2.getValue().getBlue();
                        coreshexA.add(toRGBCode(r,g,b));
                        for (Line aresta : qtdL) {
                            if (Aresta==aresta
                                    || (Aresta.getStartX() == aresta.getStartX()
                                    || Aresta.getStartX() == aresta.getEndX())
                                    || (Aresta.getEndY() == aresta.getStartY()
                                    || Aresta.getEndY() == aresta.getEndY()))
                                continue;
                            Shape intersect = Shape.intersect(Aresta, aresta);
                            if (intersect.getBoundsInLocal().getWidth() != -1) {
                                arestasSP++;
                            }
                        }
                        arestasobrepostas.setText("Arestas Sobrepostas: " + arestasSP);
                    } else {
                        pane.getChildren().remove(Aresta);
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

    private static String toRGBCode(double r, double g, double b) {
        return String.format("#%02X%02X%02X",
                (int) (r * 255),
                (int) (g * 255),
                (int) (b * 255));
    }
    private static void main (String[] args){
        launch(args);
    }
}
