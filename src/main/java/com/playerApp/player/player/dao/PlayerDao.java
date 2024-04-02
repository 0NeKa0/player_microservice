package com.playerApp.player.player.dao;

import com.playerApp.player.player.dao.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDao extends JpaRepository<Player, String> {

    //TODO: Data layer built this way because I planned to use h2 in-memory db for this microservice
    // properties are initialized in `application.properties` and the db ran in http://localhost:8080/h2-console

}
