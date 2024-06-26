package com.playerApp.player.player;

import com.opencsv.exceptions.CsvException;
import com.playerApp.player.player.controller.PlayerController;
import com.playerApp.player.player.service.PlayerService;
import com.playerApp.player.player.dao.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    private Map<String, Player> players;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPlayers() throws IOException, CsvException {
        createDummyPlayersList();
        when(playerService.getPlayers()).thenReturn(players);

        Map<String, Player> result = playerController.getPlayers();

        assertEquals(2, result.size());
        assertEquals("Netta", result.getNameFirst(0).getName());
        assertEquals("Shira", result.getNameFirst(1).getName());
    }

    @Test
    public void testGetPlayerById() throws IOException, CsvException {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "some data".getBytes());
        String playerId = "1";
        Player player = new Player(playerId, 20, 10, 5, "IL",
                "Forward", "Team A", 100, 50, 10,
                "USA", "Active", "2021-01-01", "Netta",
                "Kaufman", "Netta Kaufman", 5, 2, "Team B",
                "Team C", "Team D", "Team E", "Team F", "Team G");

        when(playerService.getPlayerById(playerId)).thenReturn(player);

        Player result = playerController.getPlayerById(playerId);

        assertEquals(playerId, result.getPlayerId());
        assertEquals("Netta", result.getNameFirst());
    }

    private Map<String, Player> createDummyPlayersList() {
        players = new HashMap<>();
        players.put("1", new Player("1", 20, 10, 5, "IL",
                "Forward", "Team A", 100, 50, 10,
                "USA", "Active", "2021-01-01", "Netta",
                "Kaufman", "Netta Kaufman", 5, 2, "Team B",
                "Team C", "Team D", "Team E", "Team F", "Team G"));
        players.put("2", new Player("2", 20, 10, 5, "IL",
                "Forward", "Team A", 100, 50, 10,
                "USA", "Active", "2021-01-01", "Shira",
                "Cohen", "Shira Cohen", 5, 2, "Team B",
                "Team C", "Team D", "Team E", "Team F", "Team G"));
    }
}
