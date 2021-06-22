package com.alanrusnak.api2048.controller;

import com.alanrusnak.api2048.engine.GamesManager;
import com.alanrusnak.api2048.engine.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameRestController {

    @Autowired
    private GamesManager gamesManager;

    @RequestMapping(value="/2048/new")
    public @ResponseBody Game newGame() {
        return gamesManager.createNewGame();
    }

    @RequestMapping(value="/2048/{gameId}")
    public @ResponseBody Game getGame(@PathVariable(value="gameId") String gameId) {
        return gamesManager.getGame(gameId);
    }

    @RequestMapping(value="/2048/{gameId}/{direction}")
    public @ResponseBody Game move(@PathVariable(value="gameId") String gameId, @PathVariable(value="direction") String direction) {
        return gamesManager.move(gameId, direction);
    }
}
