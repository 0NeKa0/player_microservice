package com.playerApp.player.player;

import com.opencsv.exceptions.CsvException;
import com.playerApp.player.player.dao.PlayerDao;
import com.playerApp.player.player.dao.entity.Player;
import com.playerApp.player.player.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerDao playerDao;

    @InjectMocks
    private PlayerService playerService;

    private MultipartFile csvFile;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        csvFile = new MockMultipartFile("players.xlsx", "players.csv", "text/csv", getClass().getResourceAsStream("/players.csv"));
        //TODO: Currently the method is being called so the real file id used, and that is wrong for unit testing. Think of a solution!
        playerService.init();
    }

    @Test
    void testGetPlayersList() throws IOException, CsvException {
        List<Player> expectedPlayersList = new ArrayList<>();
        expectedPlayersList.add(new Player("1", 20, 10, 5, "IL", "Forward", "Team A", 100, 50, 10, "USA", "Active", "2021-01-01", "2022-01-01", "2021-01-01", "2022-01-01", 5, 2, "Team B", "Team C", "Team D", "Team E", "Team F", "Team G"));

        List<Player> actualPlayersList = playerService.getPlayersList();

        assertEquals(expectedPlayersList, actualPlayersList);
    }

    @Test
    void testGetPlayerById() throws IOException, CsvException {
        String playerId = "1";
        Player expectedPlayer = new Player("1", 20, 10, 5, "IL", "Forward", "Team A", 100, 50, 10, "USA", "Active", "2021-01-01", "Netta", "Kaufman", "Netta Kaufman", 5, 2, "Team B", "Team C", "Team D", "Team E", "Team F", "Team G");

        Player actualPlayer = playerService.getPlayerById(playerId);

        assertEquals(expectedPlayer, actualPlayer);
    }
}
