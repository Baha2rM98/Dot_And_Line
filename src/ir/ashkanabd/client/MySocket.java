package ir.ashkanabd.client;

import ir.ashkanabd.player.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class MySocket implements OnReceiveListener, OnFillListener {

    public static String HOST = "127.0.0.1";
    public static int PORT = 1709;

    private Socket socket;
    private Client client;
    private Player player;
    private Timer sendTimer, sender;
    private Networking networking;

    public MySocket() throws IOException {
        socket = new Socket(HOST, PORT);
        networking = new Networking(this, socket);
        client = new Team();
        sendTimer = new Timer();
        System.out.println("Team " + client.teamName() + " connected!!!");
        networking.send(client.teamName());
        player = new Player(this);
    }

    public static String toBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String fromBase64(String input) {
        return new String(Base64.getDecoder().decode(input));
    }

    public static String[][] rebuildMap(String map) {
        String result[][] = new String[9][9];
        String rows[] = map.split("_");
        String columns[];
        for (int i = 0; i < rows.length; i++) {
            columns = rows[i].split(",");
            System.arraycopy(columns, 0, result[i], 0, columns.length);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (result[i][j].equals("@"))
                    result[i][j] = "#";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            new MySocket();
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public void onReceive(String msg) {
        player.onReceive(msg);
        sendTimer = new Timer();
        sender = new Timer();
        sender.schedule(new TimerTask() {
            @Override
            public void run() {
                networking.send(player.getXY());
                sendTimer.cancel();
            }
        }, 60000);
        sendTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                client.think(player);
            }
        }, 0);
    }

    @Override
    public void onFill() {
        networking.send(player.getXY());
        sender.cancel();
        sendTimer.cancel();
    }
}
