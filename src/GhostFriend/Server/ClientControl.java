package GhostFriend.Server;

import GhostFriend.Base.Game.Game;
import GhostFriend.Base.Player.Player;
import GhostFriend.Utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientControl implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Game game;
    private Player player;

    @Override
    public void run() {
        Log.printText("Connection accepted");
        boolean isConnected = true;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isConnected) {
            try {
                String commandParam = bufferedReader.readLine();

                if (commandParam.equals(GameParams.JOIN_GAME)) {
                    String playerName = bufferedReader.readLine();
                    player = game.addPlayer(playerName, printWriter);

                    if (player == null) {
                        sendText(GameParams.JOIN_FAIL);
                    } else {
                        sendText(GameParams.JOIN_SUCCESS);
                        game.broadcast(GameParams.JOIN_NEW_PLAYER);

                        if (game.isAllPlayersEntered()) {
                            game.broadcast(GameParams.ALL_PLAYERS_ENTERED);
                            game.startPlaying();
                        }
                    }
                }
                else if (commandParam.equals(GameParams.ASK_PLAYERS_INFO)) {
                    String playersInfo = game.getPlayersInfo(GameParams.PLAYER_INFO_DELIMITER);
                    sendText(playersInfo);
                }
            }
            catch (SocketException e) {
                e.printStackTrace();
                game.removePlayer(player);
                game.broadcast(GameParams.EXIT_PLAYER);
                isConnected = false;
            }
            catch (IOException e) {
                e.printStackTrace();
                isConnected = false;
            }
        }

        Log.printText("Thread terminated");
    }

    private void sendText(String text) {
        printWriter.println(text);
        printWriter.flush();
    }

    public ClientControl(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }
}
