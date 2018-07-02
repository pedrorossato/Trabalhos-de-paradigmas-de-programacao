import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class Controller {
    private final HttpJSONService http = new HttpJSONService();
    private final ObservableList<Model> data =
            FXCollections.observableArrayList();
    private LinkedList<Model> listaobj = new LinkedList<>();

    public void atualizabarchart(BarChart grafico){
        XYChart.Series<String,Number> barras = new XYChart.Series<>();
        ArrayList<String> linha = new ArrayList<>();
        for (Model d : data) {
            if (!linha.contains(d.getlinha()))
                linha.add(d.getlinha());
        }
        linha.remove("");
        for (String l : linha) {
            int onibus = busporlinha(l);
            if (onibus > 0)
                barras.getData().add(new XYChart.Data<>(l, onibus));
        }
        grafico.getData().clear();
        grafico.getData().add(barras);
    }
    public int busporlinha(String linha){
        int circulando = 0;
        for(Model l : data){
            if (l.getlinha().equals(linha) && l.getvelocidade() != 0)
                circulando++;
        }
        return circulando;
    }
    public void atualizapiechart(PieChart pie){
        int parados = busparados();
        int circulando = buscirculando();
        pie.setTitle(getdatasize() + " ônibus lidos na ultima leitura");
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Ônibus parados :" + parados, parados),
                        new PieChart.Data("Ônibus circulando :" + circulando,circulando));
        pie.setData(pieChartData);
    }

    public void puxardados(){
        cleardata();
        Map json = null;
        try {
            json = http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Conexão falhou");
            alert.setContentText("Verifiue sua conexão com a Internet");
            alert.showAndWait();
        }
        if (json != null)
            for(Object l : (List)json.get("DATA")){
                data.add(new Model((List)l));
                listaobj.add(new Model((List)l));
            }
    }
    public void puxadadosoff(Stage stage) {
        File jsonarq = getarq(stage);
        Map json =null;
        try {
            FileReader fileReader = new FileReader(jsonarq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            JSONParsing jsonParsing = new JSONParsing();
            json = jsonParsing.parseJSON(bufferedReader.readLine());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta");
            alert.setHeaderText("Não foi possível abrir o arquivo");
            alert.setContentText("Por favor, selecione um arquivo .json");
            alert.showAndWait();
        }
        if (json != null)
            for(Object l : (List)json.get("DATA")){
                data.add(new Model((List)l));
                listaobj.add(new Model((List)l));
            }
    }
    public File getarq(Stage stage){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecione um arquivo");
            File jsonFile = fileChooser.showOpenDialog(stage);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
            return jsonFile;
        }

    public String primeira(){
        return listaobj.getFirst().getdata();
    }
    public String ultima(){
        return  listaobj.getLast().getdata();
    }
    public String gethora(){
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        String str = (data + hora);
        return str;
    }

    public int busparados(){
        int bp = 0;
        for(Model bt : data){
            if(bt.getvelocidade()==0.0)
                bp++;
        }
        return bp;
    }
    public int buscirculando(){
        int bc = 0;
        for(Model bt: data){
            if(bt.getvelocidade()>0)
                bc++;
        }
        return bc;
    }
    public ObservableList<Model> getdata(){
        return data;
    }
    public void cleardata(){
        data.clear();
    }
    public int getdatasize(){
        return data.size();
    }
}
class HttpJSONService {
    private final String USER_AGENT = "Mozilla/5.0";
    private final JSONParsing engine = new JSONParsing();
    // HTTP GET request
    public Map sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\n'GET' request sent to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // Print result
        //System.out.println(response.toString());
        // Parse JSON result
        JSONParsing engine = new JSONParsing();
        return engine.parseJSON(response.toString());
        }
    }
    class JSONParsing {
        private final ScriptEngine engine;

        public JSONParsing() {
            ScriptEngineManager sem = new ScriptEngineManager();
            this.engine = sem.getEngineByName("javascript");
        }

        public Map parseJSON(String json) throws ScriptException {
            String script = "Java.asJSONCompatible(" + json + ")";
            Object result = this.engine.eval(script);
            Map contents = (Map) result;
            return contents;
        }
    }
