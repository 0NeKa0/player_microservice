package com.playerApp.player.player.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
//@Builder(toBuilder = true)
//TODO: Create a TO Entity, put the @Builder annotation there and keep this Entity with @Data annotation
public class Player {

    @Id
    private String playerId;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private int deathYear;
    private int deathMonth;
    private int deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private int weight;
    private int height;
    private String bats;
    private String throwsLetter;
    //TODO: Change type to Date
    private String debut;
    //TODO: Change type to Date
    private String finalGame;
    private String retroId;
    private String bbrefId;

    public Player(String playerId, int birthYear, int birthMonth,
                  int birthDay, String birthCountry, String birthState,
                  String birthCity, int deathYear, int deathMonth,
                  int deathDay, String deathCountry, String deathState,
                  String deathCity, String nameFirst, String nameLast,
                  String nameGiven, int weight, int height,
                  String bats, String throwsLetter, String debut,
                  String finalGame, String retroId, String bbrefId) {
    }

    public Player(){
    }
}
