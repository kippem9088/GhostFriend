package GhostFriend.Server;

import GhostFriend.Base.Game.Game;
import GhostFriend.Base.Player.Player;
import GhostFriend.Utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientControl implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Game game;
    private Player player;

    @Override
    public void run() {
        try {
            Log.printText("Connection accepted");
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isConnected = true;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isConnected) {
            try {
                String clientCommand = bufferedReader.readLine();
                String[] commandList = clientCommand.split(GameParams.COMMAND_DELIMITER);

                for (String command : commandList) {
                    if (player != null) {
                        Log.printText("Receive from " + player.getName() + ": " + command);
                    }

                    handleCommand(command);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                game.removePlayer(player);

                MainServer.getInstance().broadcast(GameParams.EXIT_PLAYER, game.getPlayersInfo(GameParams.DATA_DELIMITER));
                isConnected = false;
            }
        }

        try {
            Log.printText("Thread terminated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCommand(String inputCommand) throws IOException {
        String[ ] commandStructure = inputCommand.split(GameParams.COMMAND_DATA_DELIMITER);
        String command, data;

        if (commandStructure.length == 1) {
            command = commandStructure[0];
            data = "";
        }
        else if (commandStructure.length == 2) {
            command = commandStructure[0];
            data = commandStructure[1];
        }
        else {
            command = "";
            data = "";
        }

        if (command.equals(GameParams.JOIN_GAME)) {
            player = MainServer.getInstance().addPlayer(data, printWriter, bufferedReader);

            if (player == null) {
                sendCommand(GameParams.JOIN_FAIL, "");
            } else {
                MainServer.getInstance().broadcast(GameParams.JOIN_NEW_PLAYER, game.getPlayersInfo(GameParams.DATA_DELIMITER));

                if (game.isAllPlayersEntered()) {
                    MainServer.getInstance().startPlaying();
                }
            }
        }
        else if (command.equals(GameParams.REPLY_DEAL_MISS)) {
            player.checkDealMiss(Boolean.parseBoolean(data));
            MainServer.getInstance().checkDealMissDeclared();
        }
        else if (command.equals(GameParams.DECLARE_CONTRACT)) {
            MainServer.getInstance().declareContract(player, data);
        }
        else if (command.equals(GameParams.PASS_CONTRACT_DECLARATION)) {
            MainServer.getInstance().passContractDeclaration(player);
        }
        else if (command.equals(GameParams.DISCARD_CARD)) {
            game.discardCard(player, data);
        }
        else if (command.equals(GameParams.PASS_GIRU_CHANGE)) {
            game.confirmGiru();
        }
        else if (command.equals(GameParams.CHANGE_GIRU)) {
            game.confirmGiru(data);
        }
        else if (command.equals(GameParams.DETERMINE_FRIEND)) {
            game.determineFriend(data);
        }
        else if (command.equals(GameParams.SUBMIT_CARD)) {
            game.submitCard(player, data);
        }
    }

    private void sendCommand(String command, String data) throws IOException {
        printWriter.println(command + GameParams.COMMAND_DATA_DELIMITER + data + GameParams.COMMAND_DELIMITER);
        printWriter.flush();

        Log.printText("Send to " + player.getName() + ": " + command);
    }

    public ClientControl(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }
}
