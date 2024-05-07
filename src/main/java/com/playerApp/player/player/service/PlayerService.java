package com.playerApp.player.player.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.playerApp.player.player.dao.PlayerDao;
import com.playerApp.player.player.dao.entity.Player;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PlayerService {

    private PlayerDao playerDao;

    private MultipartFile csvFile;

    private Map<String, Player> players;
    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void init() throws IOException {
        csvFile = new MockMultipartFile("players.xlsx", new FileInputStream(new File("C:/Users/User/Desktop/Open Source Projects/player/player/player.csv")));
        players = new HashMap<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(csvFile.getInputStream()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // line[0] represents playerId
                players.put(line[0], createPlayer(line));
            }
        }
        catch (IOException | CsvException e) {
            log.error("Error adding player to playerList", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Map<String, Player> getPlayers() {
        log.info("Getting players list from file: {}", csvFile.getName());
        return players;
    }

    public Player getPlayerById(String playerId) throws IOException, CsvException {
        log.info("Getting player by ID: {}", playerId);
        try {
            Player player = players.get(playerId);
            log.info("Found player with ID: {}", playerId);
            return player;
            }
        }
        catch (IOException | CsvException e) {
            log.error("Error getting player by ID: {}", playerId, e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    private Player createPlayer(String[] line) {
        log.debug("Creating player from line: {}", Arrays.toString(line));
        /* TODO: Change Player object creation to use a PlayerBuilder such as:
        return Player.builder()
                .playerID(line[0])
                etc.
         */
        return new Player(line[0],
                Integer.valueOf(line[1]),
                Integer.valueOf(line[2]),
                Integer.valueOf(line[3]),
                line[4], line[5], line[6],
                Integer.valueOf(line[7]),
                Integer.valueOf(line[8]),
                Integer.valueOf(line[9]),
                line[10], line[11], line[12],
                line[13], line[14], line[15],
                Integer.valueOf(line[16]),
                Integer.valueOf(line[17]),
                line[18], line[19], line[20],
                line[21], line[21], line[23]);
    }
}
