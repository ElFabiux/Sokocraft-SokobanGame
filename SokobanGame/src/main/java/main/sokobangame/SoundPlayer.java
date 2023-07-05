/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.sokobangame;

/**
 *
 * @author fabia
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class SoundPlayer {
    private MediaPlayer mediaPlayer;

    public SoundPlayer() {
        
    }
    
    public void loadSound(String soundFile){
        Media sound = new Media(getClass().getResource(soundFile).toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    public void playSound() {
        mediaPlayer.play();
    }

    public void stopSound() {
        mediaPlayer.stop();
    }
}



