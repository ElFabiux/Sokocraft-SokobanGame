/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.sokobangame.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabia
 */
@Entity
@Table(name = "TBL_PLAYERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Players.findAll", query = "SELECT p FROM Players p"),
    @NamedQuery(name = "Players.findByPlaId", query = "SELECT p FROM Players p WHERE p.plaId = :plaId"),
    @NamedQuery(name = "Players.findByPlaName", query = "SELECT p FROM Players p WHERE p.plaName = :plaName"),
    @NamedQuery(name = "Players.findByPlaLevel", query = "SELECT p FROM Players p WHERE p.plaLevel = :plaLevel"),
    @NamedQuery(name = "Players.findByPlaGame", query = "SELECT p FROM Players p WHERE p.plaGame = :plaGame"),
    @NamedQuery(name = "Players.findByPlaSteps", query = "SELECT p FROM Players p WHERE p.plaSteps = :plaSteps")})
public class Players implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLA_ID")
    private Integer plaId;
    @Column(name = "PLA_NAME")
    private String plaName;
    @Column(name = "PLA_LEVEL")
    private String plaLevel;
    @Column(name = "PLA_GAME")
    private String plaGame;
    @Column(name = "PLA_STEPS")
    private String plaSteps;

    public Players() {
    }

    public Players(Integer plaId) {
        this.plaId = plaId;
    }

    public Integer getPlaId() {
        return plaId;
    }

    public void setPlaId(Integer plaId) {
        this.plaId = plaId;
    }

    public String getPlaName() {
        return plaName;
    }

    public void setPlaName(String plaName) {
        this.plaName = plaName;
    }

    public String getPlaLevel() {
        return plaLevel;
    }

    public void setPlaLevel(String plaLevel) {
        this.plaLevel = plaLevel;
    }

    public String getPlaGame() {
        return plaGame;
    }

    public void setPlaGame(String plaGame) {
        this.plaGame = plaGame;
    }

    public String getPlaSteps() {
        return plaSteps;
    }

    public void setPlaSteps(String plaSteps) {
        this.plaSteps = plaSteps;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plaId != null ? plaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Players)) {
            return false;
        }
        Players other = (Players) object;
        if ((this.plaId == null && other.plaId != null) || (this.plaId != null && !this.plaId.equals(other.plaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.sokobangame.model.Players[ plaId=" + plaId + " ]";
    }
    
}
