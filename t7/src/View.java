import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {

    private TableView<Model> table = new TableView<>();

    private Controller control = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        final Label label = new Label("Quadro de Ônibus");
        Label total = new Label("Total de veiculos:"+ control.getdatasize());
        Label primeiradata = new Label();
        Label ultimadata = new Label();
        Label horaatual = new Label();

        label.setFont(new Font("Arial", 20));
        total.setFont(new Font("Arial", 15));
        primeiradata.setFont(new Font("Arial", 15));
        ultimadata.setFont(new Font("Arial",15));
        horaatual.setFont(new Font("Arial",15));

        TableColumn<Model,String> dataCol = new TableColumn<>("DataHora");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        dataCol.minWidthProperty().setValue(60);
        TableColumn<Model,String> ordCol = new TableColumn<>("Ordem");
        ordCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());
        TableColumn<Model,String> linhaCol = new TableColumn<>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());
        TableColumn<Model,String> latCol = new TableColumn<>("Latitude");
        latCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        TableColumn<Model,String> longCol = new TableColumn<>("Longitude");
        longCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        TableColumn<Model,String> velCol = new TableColumn<>("Velocidade");
        velCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            table.getColumns().add(dataCol);
            table.getColumns().add(ordCol);
            table.getColumns().add(linhaCol);
            table.getColumns().add(latCol);
            table.getColumns().add(longCol);
            table.getColumns().add(velCol);

        table.setItems(control.getdata());

        Button btnpuxardados = new Button("Puxar dados");
        Button btndadosoff = new Button("Ler de arquivo JSON");

        PieChart Graficotorta = new PieChart();

        final CategoryAxis X = new CategoryAxis();
        final NumberAxis Y = new NumberAxis();
        X.setLabel("Linha");
        Y.setLabel("Õnibus");
        BarChart grafico = new BarChart<>(X,Y);

        btnpuxardados.setOnAction(event -> {
            control.puxardados();
            total.setText("Total de veiculos:"+ control.getdatasize());
            control.atualizapiechart(Graficotorta);
            control.atualizabarchart(grafico);
            primeiradata.setText("Data menos recente : " + control.primeira());
            ultimadata.setText("Data mais recente : " + control.ultima());
            horaatual.setText("Última leitura do servidor : "+ control.gethora());
        });
        btndadosoff.setOnAction(event -> {
            control.puxadadosoff(stage);
            total.setText("Total de veiculos:"+ control.getdatasize());
            control.atualizapiechart(Graficotorta);
            control.atualizabarchart(grafico);
            primeiradata.setText("Data menos recente : " + control.primeira());
            ultimadata.setText("Data mais recente : " + control.ultima());
            horaatual.setText("Última leitura do servidor : "+ control.gethora());
        });
        HBox hBox1 = new HBox();
        VBox vbox1 = new VBox();
        HBox hbox2 = new HBox();
        vbox1.setSpacing(0);
        table.setMinWidth(600);
        table.setMinHeight(400);
        vbox1.setPadding(new Insets(10, 10, 10, 10));
        vbox1.getChildren().addAll(label, table, btnpuxardados, btndadosoff, primeiradata, ultimadata, horaatual);
        vbox1.applyCss();
        vbox1.layout();
        hbox2.getChildren().addAll( Graficotorta, grafico );
        hbox2.setPadding(new Insets(15,10,10,10));
        hbox2.setSpacing(15);
        hBox1.getChildren().addAll(vbox1,hbox2);

        Scene scene = new Scene(hBox1, 1200, 700);
        stage.getIcons().add(new Image("http://www.oddball.com.br/wp-content/uploads/2017/08/bus-icon.png"));
        stage.setScene(scene);
        stage.setTitle("Monitoramento");
        stage.show();

    }
}

