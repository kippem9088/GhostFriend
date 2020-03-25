package GhostFriend.Server;

import GhostFriend.Base.Game.Game;
import GhostFriend.Base.Player.Player;
import GhostFriend.Utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
    private static final int MAX_THREAD = 100;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREAD);

    private static MainServer instance;

    private static Game game;
    private final List<PlayerInfo> playersList;

    private MainServer(Game game) {
        this.playersList = new ArrayList<>();
    }

    public static MainServer getInstance() {
        if (instance == null) {
            instance = new MainServer(MainServer.game);
        }

        return instance;
    }

    public static void registerGame(Game game) {
        MainServer.game = game;
        MainServer.instance = new MainServer(MainServer.game);
    }

    public Player addPlayer(String name, PrintWriter printWriter, BufferedReader bufferedReader) {
        Player player = game.addPlayer(name);

        if (player != null) {
            synchronized (playersList) {
                PlayerInfo newPlayerInfo = new PlayerInfo(player, printWriter, bufferedReader);
                playersList.add(newPlayerInfo);
            }
        }

        return player;
    }

    public void broadcast(String text) throws IOException {
        synchronized (playersList) {
            for (PlayerInfo playerInfo : playersList) {
                playerInfo.printWriter.println(text);
                playerInfo.printWriter.flush();

                Log.printText("Broadcast to " + playerInfo.player.getName() + ": " + text);

                String response = playerInfo.bufferedReader.readLine();

                Log.printText("Broadcast response of " + playerInfo.player.getName());

                while (!response.equals(GameParams.COMPLETE_REQUEST)) {
                    response = playerInfo.bufferedReader.readLine();
                }
            }
        }
    }

    private class PlayerInfo {
        private Player player;
        private PrintWriter printWriter;
        private BufferedReader bufferedReader;

        public PlayerInfo(Player player, PrintWriter printWriter, BufferedReader bufferedReader) {
            this.player = player;
            this.printWriter = printWriter;
            this.bufferedReader = bufferedReader;
        }
    }
}
