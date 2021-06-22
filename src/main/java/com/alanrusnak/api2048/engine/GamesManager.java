package com.alanrusnak.api2048.engine;

import com.alanrusnak.api2048.engine.model.Board;
import com.alanrusnak.api2048.engine.model.Game;
import com.alanrusnak.api2048.engine.model.MoveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class GamesManager {

    @Autowired
    private MoveExecutor moveExecutor;

    @Autowired
    private RandomStateGenerator randomStateGenerator;

    @Autowired
    private GameOverDetector gameOverDetector;

    private Map<String, Game> activeGames = new ConcurrentHashMap<String, Game>();
    private final int gameExpiryTimeInHours = 1;

    @PostConstruct
    public void init(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                removeInactiveGames();
            }
        };
        scheduler.scheduleAtFixedRate(runnable, gameExpiryTimeInHours, gameExpiryTimeInHours, TimeUnit.HOURS);
    }

    private void removeInactiveGames(){
        System.out.println("Removing inactive games. Current number of games: " + activeGames.size());
        for (Game game : activeGames.values()) {
            if(game.getLastMoveTime().isBefore(LocalDateTime.now().minusHours(gameExpiryTimeInHours))){
                activeGames.remove(game.getId());
            }
        }
        System.out.println("Removed inactive games. Number of games after removal: " + activeGames.size());
    }

    public Game createNewGame() {
        String id = randomStateGenerator.generateGameId();
        Board board = randomStateGenerator.generateNewBoard();
        Game newGame = new Game(id, board);
        activeGames.put(id, newGame);
        return newGame;
    }

    public Game getGame(String id){
        return activeGames.get(id);
    }

    public Game move(String id, String direction){
        Game game = activeGames.get(id);
        if(game == null){
            return null;
        }

        if(game.isGameOver()){
            return game;
        }
        Board board = game.getBoard();
        MoveResult result = moveExecutor.move(board, Integer.parseInt(direction));
        if(result.wasTileMoved()){
            game.addScore(result.getScore());
            game.setLastMoveTimeToNow();
            randomStateGenerator.generateNextRandomTile(board);
            game.setGameOver(gameOverDetector.isGameOver(board));
        }
        return game;

    }
}
