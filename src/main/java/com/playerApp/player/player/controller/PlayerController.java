package com.playerApp.player.player.controller;

import com.opencsv.exceptions.CsvException;
import com.playerApp.player.player.service.PlayerService;
import com.playerApp.player.player.dao.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerService service;


    @GetMapping("/players")
    public Map<String, Player> getPlayers() throws IOException, CsvException {
        log.info("Fetching players list");
        return service.getPlayers();
    }


    @GetMapping("/players/{playerId}")
    public Player getPlayerById(@PathVariable String playerId) throws IOException, CsvException {
        log.info("Fetching player by ID: {}", playerId);
        return service.getPlayerById(playerId);
    }
}
