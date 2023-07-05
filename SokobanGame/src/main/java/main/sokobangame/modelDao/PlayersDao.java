/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.sokobangame.modelDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.sokobangame.model.Players;
import main.sokobangame.modelDto.PlayersDto;

/**
 *
 * @author fabia
 */
public class PlayersDao {
    
    private Connection connection;

    public PlayersDao() {
        //Initialize the database connection
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "FArguedas";
            String password = "zierda_33";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayer(PlayersDto plaDto) {
        String sql = "INSERT INTO TBL_PLAYERS (PLA_ID, PLA_NAME, PLA_LEVEL, PLA_GAME, PLA_STEPS)" +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, plaDto.getPlaId());
            statement.setString(2, plaDto.getPlaName());
            statement.setString(3, plaDto.getPlaLevel());
            statement.setString(4, plaDto.getPlaGame());
            statement.setString(5, plaDto.getPlaSteps());
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void modifyPlayer(PlayersDto plaDto) {
        String sql = "UPDATE TBL_PLAYERS SET PLA_NAME = ?, PLA_LEVEL = ?, PLA_GAME = ?, PLA_STEPS = ?" +
                "WHERE PLA_ID = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, plaDto.getPlaName());
            statement.setString(2, plaDto.getPlaLevel());
            statement.setString(3, plaDto.getPlaGame());
            statement.setString(4, plaDto.getPlaSteps());
            statement.setInt(5, plaDto.getPlaId());
        
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
     
    public void deletePlayer(Integer id) {
        String sql = "DELETE FROM TBL_PLAYERS WHERE PLA_ID = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, id);
        
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Players getPlayer(int id) {
        String sql = "SELECT * FROM TBL_PLAYERS WHERE PLA_ID = ?";
        Players pla = new Players();
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                pla.setPlaId(rs.getInt("PLA_ID"));
                pla.setPlaName(rs.getString("PLA_NAME"));
                pla.setPlaLevel(rs.getString("PLA_LEVEL"));
                pla.setPlaGame(rs.getString("PLA_GAME"));
                pla.setPlaSteps(rs.getString("PLA_STEPS"));
            }

            rs.close();
            statement.close();
            
            return pla;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public ObservableList<PlayersDto> getPlayerObservableList() {
        String sql = "SELECT * FROM TBL_PLAYERS";
        ObservableList<PlayersDto> playerList = FXCollections.observableArrayList();
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                PlayersDto plaDto = new PlayersDto();
                
                plaDto.setPlaId(rs.getInt("PLA_ID"));
                plaDto.setPlaName(rs.getString("PLA_NAME"));
                plaDto.setPlaLevel(rs.getString("PLA_LEVEL"));
                plaDto.setPlaGame(rs.getString("PLA_GAME"));
                plaDto.setPlaSteps(rs.getString("PLA_STEPS"));

                playerList.add(plaDto);
            }

            rs.close();
            statement.close();
            
            return playerList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    } 
    
    public int getMaxId() {
        int id = 0;
        String sql = "SELECT MAX(PLA_ID)+1 as id FROM TBL_PLAYERS";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                id = rs.getInt(1);
            }
        
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
}
