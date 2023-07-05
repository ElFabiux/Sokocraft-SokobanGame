/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.sokobangame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.sokobangame.model.Players;
import main.sokobangame.modelDao.PlayersDao;
import main.sokobangame.modelDto.PlayersDto;

/**
 * FXML Controller class
 *
 * @author fabia
 */
public class Play {
    
    SoundPlayer sound = new SoundPlayer();
    
    //LOGIC
    private int numLevel = 0;
    public static Play playInstance;
    
    private String id;
    private String name;
    private String level;
    private String steps;
    private String strPlayerMatrix;
    
    private int typeOfLoad = 1;
    
    //GRAPHIC
    @FXML
    private Button btnLvlOne;
    @FXML
    private Button btnLvlTwo;
    @FXML
    private Button btnLvlThree;
    @FXML
    private Button btnLvlFour;
    @FXML
    private Button btnLvlFive;
    @FXML
    private Button btnContinue;
    @FXML
    private Label lblLevel;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;
    
    @FXML
    private AnchorPane ancNewGame;
    @FXML
    private AnchorPane ancLoadGame;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnBack;
    
    //Table
    @FXML
    private TableView<PlayersDto> tblPlayers;
    private ObservableList<PlayersDto> obsPlayers = FXCollections.observableArrayList();
    @FXML
    private TableColumn<PlayersDto, Integer> colPlaId;
    @FXML
    private TableColumn<PlayersDto, String> colPlaName;
    @FXML
    private TableColumn<PlayersDto, String> colPlaLevel;
    @FXML
    private TableColumn<PlayersDto, String> colPlaSteps;
    @FXML
    private Button btnDelete;
    
    
    @FXML
    private Label lblPlayerName;
    @FXML
    private Label lblLevelSelected;
    @FXML
    private Label lblHouse;
    @FXML
    private Label lblHell;
    @FXML
    private Label lblMine;
    @FXML
    private Label lblEnd;
    @FXML
    private Label lblDragon;
    

    public void initialize() {
        btnContinue.setDisable(true);
        btnDelete.setDisable(true);
        playInstance = this;
        updatePlayersTableView();
        
        loadLabelFonts();
        initNextPlayerId();
    }
    
    private void loadLabelFonts(){
        Font fontLabel = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 24);
        Font fontTextField = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 20);
        Font fontColumns = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 14);
        
        lblPlayerName.setFont(fontLabel);
        lblLevelSelected.setFont(fontLabel);
        lblLevel.setFont(fontLabel);
        lblHouse.setFont(fontLabel);
        lblHell.setFont(fontLabel);
        lblMine.setFont(fontLabel);
        lblEnd.setFont(fontLabel);
        lblDragon.setFont(fontLabel);
        
        txtName.setFont(fontTextField);
        txtId.setFont(fontTextField);
        
        colPlaId.setStyle("-fx-font: " + fontColumns.getSize() + "px \"" + fontColumns.getFamily() + "\";");
        colPlaName.setStyle("-fx-font: " + fontColumns.getSize() + "px \"" + fontColumns.getFamily() + "\";");
        colPlaLevel.setStyle("-fx-font: " + fontColumns.getSize() + "px \"" + fontColumns.getFamily() + "\";");
        colPlaSteps.setStyle("-fx-font: " + fontColumns.getSize() + "px \"" + fontColumns.getFamily() + "\";");
    }
    
    private void initNextPlayerId(){
        PlayersDao plaDao = new PlayersDao();
        txtId.setText(String.valueOf(plaDao.getMaxId()));
    }
    
    @FXML
    private void clickLevelOne(ActionEvent event) {
        playDogSound();
        levelClicked(1, "House");
    }

    @FXML
    private void clickLevelTwo(ActionEvent event) {
        playHellSound();
        levelClicked(2, "Hell");
    }

    @FXML
    private void clickLevelThree(ActionEvent event) {
        playMineSound();
        levelClicked(3, "Mine");
    }

    @FXML
    private void clickLevelFour(ActionEvent event) {
        playEndSound();
        levelClicked(4, "End");
    }

    @FXML
    private void clickLevelFive(ActionEvent event) {
        playDragonSound();
        levelClicked(5, "Dragon");
    }
    
    private void levelClicked(int level, String lvlName){
        if(!txtId.getText().isEmpty() && !txtName.getText().isEmpty()){
            btnContinue.setDisable(false);
        }
        lblLevel.setText(lvlName);
        numLevel = level;
    }
    
    @FXML
    private void clickBack(ActionEvent event) throws IOException {
        playButtonSound();
        
        App.setRoot("menu");
    }
    
    
    @FXML
    private void clickContinue(ActionEvent event) throws IOException {
        if(event.getSource() == btnContinue){
            playButtonSound();
            
            if(typeOfLoad == 1){
                name = txtName.getText();
                id = txtId.getText();
                
                switch (numLevel) {
                    case 1:
                        App.setRoot("gameOne");
                        break;
                    case 2:
                        App.setRoot("gameTwo");
                        break;
                    case 3:
                        App.setRoot("gameThree");
                        break;
                    case 4:
                        App.setRoot("gameFour");
                        break;
                    case 5:
                        App.setRoot("gameFive");
                        break;
                    default:
                        break;
                }
            }
            else{
                switch (Integer.parseInt(level)) {
                    case 1:
                        App.setRoot("gameOne");
                        break;
                    case 2:
                        App.setRoot("gameTwo");
                        break;
                    case 3:
                        App.setRoot("gameThree");
                        break;
                    case 4:
                        App.setRoot("gameFour");
                        break;
                    case 5:
                        App.setRoot("gameFive");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @FXML
    private void changeLoadAnchor(ActionEvent event) {
        playButtonSound();
        
        ancNewGame.setVisible(false);
        ancLoadGame.setVisible(true);
        btnContinue.setDisable(true);
        lblLevel.setText("...");
        txtName.setText("");
        typeOfLoad = 2;
    }

    @FXML
    private void changeNewAnchor(ActionEvent event) {
        playButtonSound();
        
        ancLoadGame.setVisible(false);
        ancNewGame.setVisible(true);
        btnContinue.setDisable(true);
        btnDelete.setDisable(true);
        typeOfLoad = 1;
    }
    
    public String getName(){
        return name;
    }
    
    public String getId(){
        return id;
    }
    
    public String getPlayerMatrix(){
        return strPlayerMatrix;
    }
    
    public String getPlayerSteps(){
        return steps;
    }
    
    public int getTypeOfLoad(){
        return typeOfLoad;
    }
    
    public void setTypeOfLoad(int typeOfLoad){
        this.typeOfLoad = typeOfLoad;
    }
    
    //DATA BASE
    @FXML
    private void tblPlayersClicked(MouseEvent event) {
        PlayersDto plaDto = tblPlayers.getSelectionModel().getSelectedItem();
        if(plaDto != null){
            playButtonSound();
            
            id = plaDto.getPlaId().toString();
            name = plaDto.getPlaName();
            level = plaDto.getPlaLevel();
            strPlayerMatrix = plaDto.getPlaGame();
            steps = plaDto.getPlaSteps();
            
            btnDelete.setDisable(false);
            btnContinue.setDisable(false);
        }
    }
    
    private void initPlayersTableView(){
        PlayersDao plaDao = new PlayersDao();
        obsPlayers = plaDao.getPlayerObservableList();
        plaDao.closeConnection();
        tblPlayers.getItems().clear();
        tblPlayers.setItems(obsPlayers);
    }
    
    private void initPlayersColumns(){
        colPlaId.setCellValueFactory(new PropertyValueFactory<>("plaId"));
        colPlaName.setCellValueFactory(new PropertyValueFactory<>("plaName"));
        colPlaLevel.setCellValueFactory(new PropertyValueFactory<>("plaLevel"));
        colPlaSteps.setCellValueFactory(new PropertyValueFactory<>("plaSteps"));
    }
    
    private void updatePlayersTableView(){
        initPlayersColumns();
        initPlayersTableView();
    }

    @FXML
    private void clickDelete(ActionEvent event) {
        if(id != null || !"".equals(id)){
            playButtonSound();
            
            PlayersDao plaDao = new PlayersDao();
            plaDao.deletePlayer(Integer.valueOf(id));
            txtId.setText(String.valueOf(plaDao.getMaxId()));
            plaDao.closeConnection();
            
            updatePlayersTableView();
            tblPlayers.refresh();
            
            btnContinue.setDisable(true);
            btnDelete.setDisable(true);
        }
    }
    
    private void playButtonSound(){
        sound.loadSound("/sounds/buttonSelectedSound.wav");
        sound.playSound();
    }
    
    private void playDogSound(){
        sound.loadSound("/sounds/dogSound.wav");
        sound.playSound();
    }
    
    private void playHellSound(){
        sound.loadSound("/sounds/hellSound.wav");
        sound.playSound();
    }
    
    private void playMineSound(){
        sound.loadSound("/sounds/mineSound.wav");
        sound.playSound();
    }
    
    
    private void playEndSound(){
        sound.loadSound("/sounds/endSound.wav");
        sound.playSound();
    }
    
    private void playDragonSound(){
        sound.loadSound("/sounds/dragonSound.wav");
        sound.playSound();
    }
}
