import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Model {
    private final StringProperty datahora;
    private final StringProperty ordem;
    private final StringProperty linha;
    private final StringProperty latitude;
    private final StringProperty longitude;
    private final StringProperty velocidade;
    public Model(List list){
        this.datahora = new SimpleStringProperty((String)list.get(0));
        this.ordem = new SimpleStringProperty((String)list.get(1));
        this.linha = new SimpleStringProperty(String.valueOf(list.get(2)));
        this.latitude = new SimpleStringProperty(String.valueOf(list.get(3)));
        this.longitude = new SimpleStringProperty(String.valueOf(list.get(4)));
        this.velocidade = new SimpleStringProperty(String.valueOf(list.get(5)));
    }

    public String getdata() {
        return datahora.get();
    }

    public StringProperty dataProperty() {
        return datahora;
    }

    public StringProperty ordemProperty() {
        return ordem;
    }
    public String getlinha(){
        return linha.get();
    }
    public void setlinha(String linha){
        this.linha.set(linha);
    }
    public StringProperty linhaProperty(){
        return linha;
    }
    public String getlatitude(){ //usat para o mapa
        return latitude.get();
    }
    public StringProperty latitudeProperty(){
        return latitude;
    }
    public String getlongitude(){ //usar para o mapa
        return longitude.get();
    }

    public StringProperty longitudeProperty(){
        return longitude;
    }
    public double getvelocidade(){
        return Double.parseDouble(velocidade.get());
    }
    public StringProperty velocidadeProperty(){
        return velocidade;
    }
}
