/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketapp.view;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import socketapp.model.Weather;

/**
 * FXML Controller class
 *
 * @author archer
 */
public class ServerController implements Initializable {
    
    @FXML
    private TextArea ta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Thread(() -> {
            try {
                startSocket();
            } catch (IOException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }    
    
    public void startSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8001);
        
        Platform.runLater(() -> ta.setText("Server started at: " + new Date() + "\n"));
        
        Socket socket = serverSocket.accept();
        
        DataInputStream fromClient = new DataInputStream(socket.getInputStream()); 
        ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
        
        while(true) {
            String utfInputData = fromClient.readUTF();
            String[] inputSplitted = utfInputData.split(", ");
            
            Weather weather = getWeatherData(inputSplitted[0], inputSplitted[1]);
            
            toClient.writeObject(weather);
            
            Platform.runLater( () -> {
                ta.appendText("Клиентээс ирсэн өгөгдөл: " + utfInputData + "\n");
                String responseText = weather.getCity() + "\n" + weather.getCountry() + "\n" + weather.getTemp() +
                weather.getPressure() + "\n" + weather.getWindSpeed() + "\n" + weather.getDescription() + "\n";
                ta.appendText("API-аас боловсруулсан серверийн хариулт: \n" + responseText + "\n");
            });
        }
    }
    
    public Weather getWeatherData(String city, String country) throws MalformedURLException, IOException {
        String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" +city+ "," +country+ "&appid"
                + "=009c727066d47943d2c3f4182d5fc3d3";
        JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL(urlStr).openStream())).nextValue();
        
        double temp = jo.getJSONObject("main").getDouble("temp");
        double humidity = jo.getJSONObject("main").getDouble("humidity");
        double pressure = jo.getJSONObject("main").getDouble("pressure");
        double wind_speed = jo.getJSONObject("wind").getDouble("speed");
        String description = (String) jo.getJSONArray("weather").getJSONObject(0).get("description");
        return new Weather(city, country, temp, humidity, pressure, wind_speed, description);
    }
    
}
