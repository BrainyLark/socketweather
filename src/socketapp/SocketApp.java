/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import socketapp.view.ServerController;

/**
 *
 * @author archer
 */
public class SocketApp extends Application {
    private Stage privateStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.privateStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SocketApp.class.getResource("view/server.fxml"));
        ScrollPane root = (ScrollPane) loader.load();
        
        ServerController serverController = loader.getController();
        
        privateStage.setScene(new Scene(root));
        privateStage.setTitle("Server Application");
        privateStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
