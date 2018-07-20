import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Random;


public class ViewMain extends Application {
    private Snake snake;
    private Ovo ovo;
    Random random = new Random();
    @Override
    public void start(Stage stage) throws Exception{
        Pane pane = new Pane();
        BorderPane bp = new BorderPane();

        int Xovo = random.nextInt(799)+1;
        int Yovo = random.nextInt(599)+1;
        ovo= new Ovo(Xovo, Yovo);
        pane.getChildren().add(ovo);
        bp.setCenter(pane);
        Scene scene = new Scene(bp, 800, 600);
        stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Complete-edge-coloring.svg/350px-Complete-edge-coloring.svg.png"));
        stage.setScene(scene);
        stage.setTitle("Jogo Snake JavaFX");
        stage.show();
    }
    public static void main (String[] args){
        launch(args);
    }

}
