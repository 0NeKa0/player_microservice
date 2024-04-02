package com.playerApp.player.player.controller;

import ch.qos.logback.classic.Logger;
import com.opencsv.exceptions.CsvException;
import com.playerApp.player.player.service.PlayerService;
import com.playerApp.player.player.dao.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PlayerController {

    private PlayerService service;
    private Logger log;


    @GetMapping("/players")
    public List<Player> getPlayersList() throws IOException, CsvException {
        log.info("Fetching players list");
        return service.getPlayersList();
    }


    @GetMapping("/players/{playerId}")
    public Player getPlayerById(
            @RequestPart("file") MultipartFile file,
            @PathVariable String playerId) throws IOException, CsvException {
        log.info("Fetching player by ID: {}", playerId);
        return service.getPlayerById(file, playerId);
    }
}
