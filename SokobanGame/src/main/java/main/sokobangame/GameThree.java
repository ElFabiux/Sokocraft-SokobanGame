/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.sokobangame;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.sokobangame.model.Players;
import main.sokobangame.modelDao.PlayersDao;
import main.sokobangame.modelDto.PlayersDto;

/**
 * FXML Controller class
 *
 * @author fabia
 */
public class GameThree {
        
    SoundPlayer sound = new SoundPlayer();
    SoundPlayer music = new SoundPlayer();
    
    //LOGIC
    private final int ROW = 10;
    private final int COLUMN = 20;
    
    private char[][] lvlThreeMatrix = new char[ROW][COLUMN];
    
    private char[][] curMatrix = new char[ROW][COLUMN];
    private char[][] preMatrix = new char[ROW][COLUMN];
    private int playerRow;
    private int playerCol;
    private int stepCounter = 0;
    private boolean noKey = false;
    Map<Character, String> imageMap = new HashMap<>();
    
    private String playerName;
    private String playerId;
    private String strPlayerMatrix;
    private int typeOfLoad = Play.playInstance.getTypeOfLoad();
    
    private boolean gameMatrixAffected;
            
    //GREAPHIC
    @FXML
    private Button btnPreviousMove;
    @FXML
    private Button btnResetLevel;
    @FXML
    private AnchorPane ancLevel;
    @FXML
    private GridPane grpLevel;
    @FXML
    private Label lblStepCounter;
    @FXML
    private Button btnNextLevel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnBack;
    
    @FXML
    private Label lblPlayerName;
    
    //METHODS...
    public void initialize() {
        System.out.println("GameThree");
        playMusicSound();
        
        btnPreviousMove.setDisable(true);
        btnPreviousMove.setFocusTraversable(false);
        btnResetLevel.setFocusTraversable(false);
        btnNextLevel.setVisible(false);
        ancLevel.setFocusTraversable(true);
        
        loadPlayerInfo();
        loadImages();
        initGraphics();
        loadFontLabel();
    }
    
    private void loadFontLabel(){
        Font fontLabel = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 28);
        
        lblPlayerName.setFont(fontLabel);
        lblStepCounter.setFont(fontLabel);
    }
    
    private void loadPlayerInfo(){
        if(typeOfLoad == 1){
            //New game
            playerName = Play.playInstance.getName();
            playerId = Play.playInstance.getId();
            lblPlayerName.setText(playerName);
            chargeOriginalLevel();
            updateLvlToCurMatrix();
            updatePlayerPosition();
            
            gameMatrixAffected = false;
        }
        else{
            //Loaded game
            System.out.println("CARGOOOO EL JUEGOOOO!!!");
            
            playerName = Play.playInstance.getName();
            playerId = Play.playInstance.getId();
            stepCounter = Integer.parseInt(Play.playInstance.getPlayerSteps());
            strPlayerMatrix = Play.playInstance.getPlayerMatrix();
            
            lblPlayerName.setText(playerName);
            
            chargeSavedLevel();
            updateLvlToCurMatrix();
            updatePlayerPosition();
            
            lblStepCounter.setText(String.valueOf(stepCounter));
            Play.playInstance.setTypeOfLoad(1);
            
            gameMatrixAffected = true;
        }
    }
    
    private void chargeOriginalLevel(){
        String[][] lvlMatrix = {
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", "1", "+", "+", "o", ".", ".", "#", "#", ".", "@", "#", "#", "#", ".", ".", ".", ".", "#", "3"},
            {"#", "+", ",", ",", "+", "#", ".", "#", "#", ".", "#", "#", "#", "#", ".", "1", "#", ".", "#", "#"},
            {"#", "+", ",", ",", "+", "#", ".", ".", ".", ".", ".", ".", ".", ".", ".", "#", "#", "o", "#", "#"},
            {"#", ".", "+", "+", ".", "#", "#", "#", "o", "#", "#", "#", ".", "#", ".", "#", "#", ".", "#", "#"},
            {"#", ".", ".", ".", ".", ".", "o", ".", ".", ".", "#", ".", ".", "#", ".", "#", "#", ".", "1", "#"},
            {"#", ".", ".", ".", ".", "#", "#", "#", "#", ".", "#", ".", "#", "#", ".", ".", ".", ".", ".", "#"},
            {"#", "#", "o", "#", ".", ".", ".", "o", ".", ".", ".", "o", ".", ".", ".", "o", "#", "#", ".", "#"},
            {"#", "1", ".", ".", ".", ".", "#", "#", "#", ".", ".", ".", "#", "#", "#", ".", ".", ".", ".", "#"},
            {"2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"}
        };

        
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COLUMN; col++) {
                lvlThreeMatrix[row][col] = lvlMatrix[row][col].charAt(0);
            }
        }
    }
    
    private void chargeSavedLevel(){
        int index = 0;
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COLUMN; col++) {
                lvlThreeMatrix[row][col] = strPlayerMatrix.charAt(index++);
            }
        }
    }
    
    private void updateLvlToCurMatrix(){
        for(int row = 0; row < ROW; row++){
            System.arraycopy(lvlThreeMatrix[row], 0, curMatrix[row], 0, COLUMN);
        }
    }
    
    private void loadImages(){
        imageMap.put('#', "/imagesLvlThree/wall.jpg");
        imageMap.put('@', "/imagesLvlThree/player.jpg");
        imageMap.put('.', "/imagesLvlThree/floor.jpg");
        imageMap.put('o', "/imagesLvlThree/eye.jpg");
        imageMap.put('+', "/imagesLvlThree/frame.jpg");
        imageMap.put('0', "/imagesLvlThree/frameAndEye.jpg"); //Cuando una caja esta arriba de un punto
        imageMap.put('a', "/imagesLvlThree/playerAndFrame.jpg"); //Cuando el jugador esta arriba de un punto
        imageMap.put(',', "/imagesLvlThree/lava.jpg"); 
        
        //DECORATE
        imageMap.put('1', "/imagesLvlThree/spiderweb.jpg"); 
        imageMap.put('2', "/imagesLvlThree/rail.jpg");
        imageMap.put('3', "/imagesLvlThree/zombie.jpg");
    }
    
    private void initGraphics() {
        grpLevel.getChildren().clear();
        
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COLUMN; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(56);
                imageView.setFitHeight(48);

                char element = curMatrix[row][col];
                if(imageMap.containsKey(element)) {
                    String imagePath = imageMap.get(element);
                    Image image = new Image(getClass().getResource(imagePath).toExternalForm());
                    imageView.setImage(image);
                }

                grpLevel.add(imageView, col, row);
            }
        }
    }
    
    private void updateGraphics() {
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COLUMN; col++) {
                if(curMatrix[row][col] != preMatrix[row][col]){
                    ImageView imageView = new ImageView();
                    imageView.setFitWidth(56);
                    imageView.setFitHeight(48);

                    char element = curMatrix[row][col];
                    if(imageMap.containsKey(element)) {
                        String imagePath = imageMap.get(element);
                        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
                        imageView.setImage(image);
                    }

                    grpLevel.add(imageView, col, row);
                }
            }
        }
    }

    @FXML
    private void keyPressedMap(KeyEvent event) {
        if(noKey != true){
            KeyCode keyCode = event.getCode();

            keyPressedUp(keyCode);
            keyPressedDown(keyCode);
            keyPressedRight(keyCode);
            keyPressedLeft(keyCode);

            checkWonMap();
        }
    }
    
    private void updateCurToPreMatrix(){
        for (int row = 0; row < ROW; row++) {
            System.arraycopy(curMatrix[row], 0, preMatrix[row], 0, COLUMN);
        }
    }
    
    private void checkWonMap(){
        int cellCounter = 0;
        for(int row = 0; row < ROW; row++){
            for(int col = 0; col < COLUMN; col++){
                if(curMatrix[row][col] == '0'){
                    cellCounter++;
                }
            }
        }
        if(cellCounter == 8){
            System.out.println("GANOOOO!!");
            finishLevel();
            music.stopSound();
            playEndSound();
        }
    }
    
    private void finishLevel(){
        grpLevel.setVisible(false);
        btnNextLevel.setVisible(true);
        ancLevel.setFocusTraversable(false);
        btnPreviousMove.setDisable(true);
        btnResetLevel.setDisable(true);
        noKey = true;
    }
    
    private void keyPressedUp(KeyCode keyCode){
        if(keyCode == KeyCode.UP){
            int rowMinusOne = playerRow - 1;
            int rowMinusTwo = playerRow - 2;
            
            checkRowMovementEmptyCells(rowMinusOne);
            checkRowMovementGoalPoints(rowMinusOne);
            checkRowMovementBoxes(rowMinusOne, rowMinusTwo);
            checkRowMovementFilledCells(rowMinusOne, rowMinusTwo);
        }
    }
    
    private void keyPressedDown(KeyCode keyCode){
        if(keyCode == KeyCode.DOWN){
            int rowPlusOne = playerRow + 1;
            int rowPlusTwo = playerRow + 2;
            
            checkRowMovementEmptyCells(rowPlusOne);
            checkRowMovementGoalPoints(rowPlusOne);
            checkRowMovementBoxes(rowPlusOne, rowPlusTwo);
            checkRowMovementFilledCells(rowPlusOne, rowPlusTwo);
        }
    }
    
    private void keyPressedLeft(KeyCode keyCode){
        if(keyCode == KeyCode.LEFT){
            int colMinusOne = playerCol - 1;
            int colMinusTwo = playerCol - 2;

            checkColumnMovementEmptyCells(colMinusOne);
            checkColumnMovementGoalPoints(colMinusOne);
            checkColumnMovementBoxes(colMinusOne, colMinusTwo);
            checkColumnMovementFilledCells(colMinusOne, colMinusTwo);
        }
    }
    
    private void keyPressedRight(KeyCode keyCode){
        if(keyCode == KeyCode.RIGHT){
            int colPlusOne = playerCol + 1;
            int colPlusTwo = playerCol + 2;

            checkColumnMovementEmptyCells(colPlusOne);
            checkColumnMovementGoalPoints(colPlusOne);
            checkColumnMovementBoxes(colPlusOne, colPlusTwo);
            checkColumnMovementFilledCells(colPlusOne, colPlusTwo);
        }
    }
    
    private void checkRowMovementEmptyCells(int rowOne){
        if(curMatrix[rowOne][playerCol] == '.'){ //Casillas vacias
            if(curMatrix[playerRow][playerCol] == 'a'){
                movePlayer('+', '@', rowOne, playerCol);
            }
            else{
                movePlayer('.', '@', rowOne, playerCol);
            }
        }
    }
    
    private void checkColumnMovementEmptyCells(int colOne){
        if(curMatrix[playerRow][colOne] == '.'){
            if(curMatrix[playerRow][playerCol] == 'a'){
                movePlayer('+', '@', playerRow, colOne);
            }
            else{
                movePlayer('.', '@', playerRow, colOne);
            }
        }
    }
    
    private void checkRowMovementGoalPoints(int rowOne){
        if(curMatrix[rowOne][playerCol] == '+'){ //Puntos
            if(curMatrix[playerRow][playerCol] == 'a'){
                movePlayer('+', 'a', rowOne, playerCol);
            }
            else{
                movePlayer('.', 'a', rowOne, playerCol);
            }
        }
    }
    
    private void checkColumnMovementGoalPoints(int colOne){
        if(curMatrix[playerRow][colOne] == '+'){ //Puntos
            if(curMatrix[playerRow][playerCol] == 'a'){
                movePlayer('+', 'a', playerRow, colOne);
            }
            else{
                movePlayer('.', 'a', playerRow, colOne);
            }
        }
    }
    
    private void checkRowMovementBoxes(int rowOne, int rowTwo){
        if(curMatrix[rowOne][playerCol] == 'o'){ //Cajas
            if(curMatrix[rowTwo][playerCol] == '.'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', '@', 'o', rowOne, rowTwo, playerCol, -1);
                }
                else{
                    movePlayerObjects('.', '@', 'o', rowOne, rowTwo, playerCol, -1);
                }
            }
            else if(curMatrix[rowTwo][playerCol] == '+'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', '@', '0', rowOne, rowTwo, playerCol, -1);
                }
                else{
                    movePlayerObjects('.', '@', '0', rowOne, rowTwo, playerCol, -1);
                }
            }
        }
    }
    
    private void checkColumnMovementBoxes(int colOne, int colTwo){
        if(curMatrix[playerRow][colOne] == 'o'){ //Cajas
            if(curMatrix[playerRow][colTwo] == '.'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', '@', 'o', playerRow, -1, colOne, colTwo);
                }
                else{
                    movePlayerObjects('.', '@', 'o', playerRow, -1, colOne, colTwo);
                }
            }
            else if(curMatrix[playerRow][colTwo] == '+'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', '@', '0', playerRow, -1, colOne, colTwo);
                }
                else{  
                    movePlayerObjects('.', '@', '0', playerRow, -1, colOne, colTwo);
                }
            }
        }
    }
    
    private void checkRowMovementFilledCells(int rowOne, int rowTwo){
        if(curMatrix[rowOne][playerCol] == '0'){ //Cajas, casillas y jugador
            if(curMatrix[rowTwo][playerCol] == '+'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', 'a', '0', rowOne, rowTwo, playerCol, -1);
                }
                else{
                    movePlayerObjects('.', 'a', '0', rowOne, rowTwo, playerCol, -1);
                }
            }
            else if(curMatrix[rowTwo][playerCol] == '.'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', 'a', 'o', rowOne, rowTwo, playerCol, -1);
                }
                else{
                    movePlayerObjects('.', 'a', 'o', rowOne, rowTwo, playerCol, -1);
                }
            }
        }
    }
    
    private void checkColumnMovementFilledCells(int colOne, int colTwo){
        if(curMatrix[playerRow][colOne] == '0'){ //Cajas, casillas y jugador
            if(curMatrix[playerRow][colTwo] == '+'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', 'a', '0', playerRow, -1, colOne, colTwo);
                }
                else{
                    movePlayerObjects('.', 'a', '0', playerRow, -1, colOne, colTwo);
                }
            }
            else if(curMatrix[playerRow][colTwo] == '.'){
                if(curMatrix[playerRow][playerCol] == 'a'){
                    movePlayerObjects('+', 'a', 'o', playerRow, -1, colOne, colTwo);
                }
                else{
                    movePlayerObjects('.', 'a', 'o', playerRow, -1, colOne, colTwo);
                }
            }
            
        }
    }
    
    private void movePlayer(char oldChar, char newChar, int newRow, int newCol){
        updateCurToPreMatrix();
        btnPreviousMove.setDisable(false);
        curMatrix[playerRow][playerCol] = oldChar;
        curMatrix[newRow][newCol] = newChar;
        
        playerRow = newRow;
        playerCol = newCol;
        
        stepCounter += 1;
        updateGraphics();
        printStepLabel();
        
        playWalkingSound();
        playEyeSound();
        alertMessage();
    }
    
    private void movePlayerObjects(char oldChar, char newCharOne, char newCharTwo, int newRowOne, int rowTwo, int newColOne, int colTwo){
        updateCurToPreMatrix();
        btnPreviousMove.setDisable(false);
        curMatrix[playerRow][playerCol] = oldChar;
        curMatrix[newRowOne][newColOne] = newCharOne;
        if(rowTwo != -1){
            curMatrix[rowTwo][playerCol] = newCharTwo;
        }
        else{
            curMatrix[playerRow][colTwo] = newCharTwo;
        }
        playerRow = newRowOne;
        playerCol = newColOne;

        stepCounter += 1;
        updateGraphics();
        printStepLabel();
        
        playWalkingSound();
        playEyeSound();
        alertMessage();
    }

    private void printStepLabel(){
        lblStepCounter.setText(String.valueOf(stepCounter));
    }
    
    //FUNCTIONS
    @FXML
    private void clickPreviousMove(ActionEvent event) {
        if(event.getSource() == btnPreviousMove){
            playButtonSound();
            
            updatePreToCurMatrix();
            updatePlayerPosition();
            stepCounter -= 1;
            btnPreviousMove.setDisable(true);
            initGraphics();
            printStepLabel();
        }
    }
    
    private void updatePlayerPosition(){
        for(int row = 0; row < ROW; row++){
            for(int col = 0; col < COLUMN; col++){
                if(curMatrix[row][col] == '@' || curMatrix[row][col] == 'a'){
                    playerRow = row;
                    playerCol = col;
                }
            }
        }
    }
    
    private void updatePreToCurMatrix(){
        for (int row = 0; row < ROW; row++) {
            System.arraycopy(preMatrix[row], 0, curMatrix[row], 0, COLUMN);
        }
    }

    @FXML
    private void clickResetLevel(ActionEvent event) {
        if(event.getSource() == btnResetLevel){
            playButtonSound();
            
            if(gameMatrixAffected == true){
                chargeOriginalLevel();
                gameMatrixAffected = false;
            }
                        
            updateLvlToCurMatrix();
            updateCurToPreMatrix();
            updatePlayerPosition();
            btnPreviousMove.setDisable(true);
            stepCounter = 0;
            initGraphics();
            printStepLabel();
        }
    }

    @FXML
    private void clickNextLevel(ActionEvent event) throws IOException {
        playButtonSound();
        
        App.setRoot("gameFour");
    }
    
    //DATA BASE........................

    @FXML
    private void clickSaveLevel(ActionEvent event) {
        playButtonSound();
        if(checkPlayerExists()){
            modifyPlayer();
        }
        else{
            savePlayer();
        }
    }
    
    private boolean checkPlayerExists(){
        PlayersDao plaDao = new PlayersDao();
        Players pla;
        pla = plaDao.getPlayer(Integer.parseInt(playerId));
        
        return pla.getPlaId() != null && Objects.equals(pla.getPlaId(), Integer.valueOf(playerId));
    }
    
    private void savePlayer(){
        Players pla = new Players();
        pla.setPlaId(Integer.valueOf(playerId));
        pla.setPlaName(playerName);
        pla.setPlaLevel("3"); //Depende del nivel
        pla.setPlaGame(convertMatrixToString());
        pla.setPlaSteps(String.valueOf(stepCounter));

        PlayersDto plaDto = new PlayersDto(pla);
        PlayersDao plaDao = new PlayersDao();
        plaDao.savePlayer(plaDto);
        plaDao.closeConnection();
    }
    
    private void modifyPlayer(){
        Players pla = new Players();
        pla.setPlaId(Integer.valueOf(playerId));
        pla.setPlaName(playerName);
        pla.setPlaLevel("3"); //Depende del nivel
        pla.setPlaGame(convertMatrixToString());
        pla.setPlaSteps(String.valueOf(stepCounter));

        PlayersDto plaDto = new PlayersDto(pla);
        PlayersDao plaDao = new PlayersDao();
        plaDao.modifyPlayer(plaDto);
        plaDao.closeConnection();
    }
    
    private String convertMatrixToString(){
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COLUMN; col++) {
                stringBuilder.append(curMatrix[row][col]);
            }
        }
        
        return stringBuilder.toString();
    }

    @FXML
    private void changeMenu(ActionEvent event) throws IOException {
        playButtonSound();
        music.stopSound();
        
        App.setRoot("menu");
    }
    
    private void playButtonSound(){
        sound.loadSound("/sounds/buttonSelectedSound.wav");
        sound.playSound();
    }
    
    Random rand = new Random();
    
    private void playWalkingSound(){
        
        int randNumber = rand.nextInt(4)+1;
        
        switch(randNumber){
            case 1:
                sound.loadSound("/sounds/walkingRockSound1.wav");
                break;
            case 2:
                sound.loadSound("/sounds/walkingRockSound2.wav");
                break;
            case 3:
                sound.loadSound("/sounds/walkingRockSound3.wav");
                break;
            case 4:
                sound.loadSound("/sounds/walkingRockSound4.wav");
                break;
            default:
                break;
        }
        sound.playSound();
    }
    
    private void playMusicSound(){
        music.loadSound("/sounds/levelThreeMusic.wav");
        music.playSound();
    }
    
    private void playWinSound(){
        sound.loadSound("/sounds/experienceSound.wav");
        sound.playSound();
    }
    
    private boolean eye1 = false;
    private boolean eye2 = false;
    private boolean eye3 = false;
    private boolean eye4 = false;
    private boolean eye5 = false;
    private boolean eye6 = false;
    private boolean eye7 = false;
    private boolean eye8 = false;
    
    private void playEyeSound(){
        int randNumber = rand.nextInt(3)+1;
        
        switch(randNumber){
            case 1:
                sound.loadSound("/sounds/eyeSound1.wav");
                break;
            case 2:
                sound.loadSound("/sounds/eyeSound2.wav");
                break;
            case 3:
                sound.loadSound("/sounds/eyeSound3.wav");
                break;
            default:
                break;
        }
        
        //arriba
        if(curMatrix[1][2] == '0' && eye1 == false){
            sound.playSound();
            eye1 = true;
        }
        else if(curMatrix[1][2] != '0'){
            eye1 = false;
        }
        
        if(curMatrix[1][3] == '0' && eye2 == false){
            sound.playSound();
            eye2 = true;
        }
        else if(curMatrix[1][3] != '0'){
            eye2 = false;
        }
        
        //izquierda
        if(curMatrix[2][1] == '0' && eye3 == false){
            sound.playSound();
            eye3 = true;
        }
        else if(curMatrix[2][1] != '0'){
            eye3 = false;
        }
        
        if(curMatrix[3][1] == '0' && eye4 == false){
            sound.playSound();
            eye4 = true;
        }
        else if(curMatrix[3][1] != '0'){
            eye4 = false;
        }
        
        //derecha
        if(curMatrix[2][4] == '0' && eye5 == false){
            sound.playSound();
            eye5 = true;
        }
        else if(curMatrix[2][4] != '0'){
            eye5 = false;
        }
        
        if(curMatrix[3][4] == '0' && eye6 == false){
            sound.playSound();
            eye6 = true;
        }
        else if(curMatrix[3][4] != '0'){
            eye6 = false;
        }
        
        //abajo
        if(curMatrix[4][2] == '0' && eye7 == false){
            sound.playSound();
            eye7 = true;
        }
        else if(curMatrix[4][2] != '0'){
            eye7 = false;
        }
        
        if(curMatrix[4][3] == '0' && eye8 == false){
            sound.playSound();
            eye8 = true;
        }
        else if(curMatrix[4][3] != '0'){
            eye8 = false;
        }
    }
    
    private void playEndSound(){
        sound.loadSound("/sounds/endSound.wav");
        sound.playSound();
    }
    
    private void alertMessage(){
        if(curMatrix[6][1] == 'o' || curMatrix[8][2] == 'o' || curMatrix[8][5] == 'o' || curMatrix[3][6] == 'o' || curMatrix[5][9] == 'o' || curMatrix[8][9] == 'o' || curMatrix[8][11] == 'o' || curMatrix[5][11] == 'o' || curMatrix[5][12] == 'o' || curMatrix[1][14] == 'o' || curMatrix[1][17] == 'o' || curMatrix[8][15] == 'o' || curMatrix[6][18] == 'o'){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sokocraft Alert");
            alert.setHeaderText(null);
            alert.setContentText("Oh No!! The game cannot continue, start again");

            DialogPane dialogPane = alert.getDialogPane();

            Label contentLabel = new Label(alert.getContentText());
            Font fontLabel = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 15);
            contentLabel.setFont(fontLabel);

            dialogPane.setContent(contentLabel);

            
            Image iconAlert = new Image(getClass().getResourceAsStream("/imagesBackground/alert.PNG"));
            ImageView iconImageView = new ImageView(iconAlert);
            dialogPane.setGraphic(iconImageView);
            
            Stage stage = (Stage) dialogPane.getScene().getWindow();
            Image iconPoint = new Image(getClass().getResourceAsStream("/imagesBackground/redPoint.PNG"));
            stage.getIcons().add(iconPoint);


            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ActionEvent event = new ActionEvent(btnResetLevel, null);
                    clickResetLevel(event);
                    
                    iconImageView.setImage(null);
                }
            });
        }
    }
}
