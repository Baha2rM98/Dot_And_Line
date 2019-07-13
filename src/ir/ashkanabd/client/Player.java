package ir.ashkanabd.client;

import java.util.*;

public class Player implements OnReceiveListener {

    private int myScore, oppScore;
    private Node[][] map;
    private int X, Y, myID, oppID;
    private Scanner scn;
    private OnFillListener onFillListener;

    public Player(OnFillListener onFillListener) {
        this.onFillListener = onFillListener;
    }

    void update(String map[][], int myScore, int oppScore, int myID, int oppID) {
        this.map = buildMap(map);
        this.myScore = myScore;
        this.oppScore = oppScore;
        this.myID = myID;
        this.oppID = oppID;
        X = -2;
        Y = -2;
    }

    public int getRowCount() {
        return 9;
    }

    public int getColumnCount() {
        return 9;
    }

    Node[][] buildMap(String map[][]) {
        Node nodes[][] = new Node[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nodes[i][j] = new Node(i, j);
                if (map[i][j].equalsIgnoreCase("*"))
                    nodes[i][j].setSpot();
                else if (map[i][j].equalsIgnoreCase("#"))
                    nodes[i][j].setBlock();
                else if (map[i][j].equalsIgnoreCase("0")) {
                    nodes[i][j].setFree();
                } else if ((map[i][j].equalsIgnoreCase("1"))) {
                    nodes[i][j].setMarked();
                    nodes[i][j].setMark(1);
                } else {
                    nodes[i][j].setMarked();
                    nodes[i][j].setMark(2);
                }

            }
        }
        return nodes;
    }

    String getXY() {
        if (X >= 0 && Y >= 0) {
            if (!map[X][Y].isFree()) {
                X = -2;
                Y = -2;
            }
        }
        return X + "_" + Y;
    }

    public void fill(int x, int y) {
        this.X = x;
        this.Y = y;
        onFillListener.onFill();
    }

    public void fill(Node node) {
        this.X = node.getX();
        this.Y = node.getY();
        onFillListener.onFill();
    }

    public int getOppScore() {
        return oppScore;
    }

    public int getMyScore() {
        return myScore;
    }

    public Node[][] getMap() {
        return map;
    }

    @Override
    public void onReceive(String msg) {
        msg = MySocket.fromBase64(msg);
        scn = new Scanner(msg);
        int myScore = Integer.parseInt(scn.nextLine());
        int oppScore = Integer.parseInt(scn.nextLine());
        int myID = Integer.parseInt(scn.nextLine());
        int oppID = Integer.parseInt(scn.nextLine());
        update(MySocket.rebuildMap(scn.nextLine()), myScore, oppScore, myID, oppID);
    }

    public int getMyID() {
        return myID;
    }

    public int getOppID() {
        return oppID;
    }
}
