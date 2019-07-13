package ir.ashkanabd.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Networking extends Thread {

    private OnReceiveListener onReceive;
    private Scanner scn;
    private PrintWriter writer;
    private Socket socket;

    public Networking(OnReceiveListener onReceive, Socket socket) throws IOException {
        this.socket = socket;
        this.onReceive = onReceive;
        scn = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
        start();
    }

    void send(String data) {
        writer.println(MySocket.toBase64(data));
        writer.flush();
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        while (scn.hasNext()) {
            onReceive.onReceive(scn.nextLine());
        }
        System.exit(0);
    }
}
