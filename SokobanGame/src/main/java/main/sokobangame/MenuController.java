/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.sokobangame;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * FXML Controller class
 *
 * @author fabia
 */
public class MenuController {

    SoundPlayer sound = new SoundPlayer();
    
    @FXML
    private Button btnInformation;
    @FXML
    private Button btnPlay;
    @FXML
    private ImageView imgPlay;
    @FXML
    private ImageView imgInfo;
    
    @FXML
    private AnchorPane ancInfo;
    @FXML
    private Button btnBack;
    
    public void initialize() {
        System.out.println("Game");
        imgPlay.getStyleClass().add("image");
        imgInfo.getStyleClass().add("image");
    }    
    
    @FXML
    private void changePlay(ActionEvent event) throws IOException {
        playButtonSound();
        
        App.setRoot("play");
    }
    
    @FXML
    private void clickBack(ActionEvent event) {
        playButtonSound();
        ancInfo.setVisible(false);
    }

    @FXML
    private void changeInfo(ActionEvent event) {
        playButtonSound();
        ancInfo.setVisible(true);
    }
    
    private void playButtonSound(){
        sound.loadSound("/sounds/buttonSelectedSound.wav");
        sound.playSound();
    }

}
