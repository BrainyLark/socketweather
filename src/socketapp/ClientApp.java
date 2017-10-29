/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketapp;

import socketapp.view.ClientController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author archer
 */
public class ClientApp extends Application {
    
    private Stage privateStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.privateStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClientApp.class.getResource("view/client.fxml"));
        BorderPane root = (BorderPane) loader.load();
        
        ClientController serverController = loader.getController();
        
        privateStage.setScene(new Scene(root));
        privateStage.setTitle("Client Application");
        privateStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
