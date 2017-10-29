/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketapp.view;

import socketapp.model.Weather;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author archer
 */
public class ClientController implements Initializable {
    
    @FXML
    TextField textField;
    
    @FXML
    Label label;
    
    @FXML
    TextArea textArea;
    
    private ObjectInputStream fromServer;
    private DataOutputStream toServer;
    
    public void setSocketConnection() throws IOException {
        Socket socket = new Socket("localhost", 8001);
        
        fromServer = new ObjectInputStream(socket.getInputStream());
        toServer = new DataOutputStream(socket.getOutputStream());
        
        textField.setOnAction((e) -> {
            String sendData = textField.getText();
            try {
                toServer.writeUTF(sendData);
                Weather weather = (Weather) fromServer.readObject();
                Platform.runLater(() -> {
                    String responseText = "\nХот: " + weather.getCity() + "\n Улс: " + weather.getCountry() + "\n Температур: " + weather.getTemp() +
                        "\n Агаарын даралт: " + weather.getPressure() + "\n Салхины хурд: " + weather.getWindSpeed() + "\n Төлөв: " + weather.getDescription() + "\n";
                    textArea.appendText("Серверээс ирсэн: " + responseText + "\n");
                });
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setSocketConnection();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }   

    
    
}
