/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.sokobangame.modelDto;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import main.sokobangame.model.Players;

/**
 *
 * @author fabia
 */
public class PlayersDto {
    
    private SimpleStringProperty plaId;
    private SimpleStringProperty plaName;
    private SimpleStringProperty plaLevel;
    private SimpleStringProperty plaGame;
    private SimpleStringProperty plaSteps;

    
    private Boolean modified;

    public PlayersDto() {
        this.modified = false;
        this.plaId = new SimpleStringProperty();
        this.plaName = new SimpleStringProperty();
        this.plaLevel = new SimpleStringProperty();
        this.plaGame = new SimpleStringProperty();
        this.plaSteps = new SimpleStringProperty();
    }

    public PlayersDto(Players player) {
        this();
        this.plaId.set(player.getPlaId().toString());
        this.plaName.set(player.getPlaName());
        this.plaLevel.set(player.getPlaLevel());
        this.plaGame.set(player.getPlaGame());
        this.plaSteps.set(player.getPlaSteps());
    }

    public Integer getPlaId() {
        if (plaId.get() != null && !plaId.get().isEmpty())
            return Integer.valueOf(plaId.get());
        else
            return null;
    }

    public void setPlaId(Integer plaId) {
        this.plaId.set(plaId.toString());
    }

    public String getPlaName() {
        return plaName.get();
    }

    public void setPlaName(String plaName) {
        this.plaName.set(plaName);
    }

    public String getPlaLevel() {
        return plaLevel.get();
    }

    public void setPlaLevel(String plaLevel) {
        this.plaLevel.set(plaLevel);
    }

    public String getPlaGame() {
        return plaGame.get();
    }

    public void setPlaGame(String plaGame) {
        this.plaGame.set(plaGame);
    }

    public String getPlaSteps() {
        return plaSteps.get();
    }

    public void setPlaSteps(String plaSteps) {
        this.plaSteps.set(plaSteps);
    }
    
    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }
}
