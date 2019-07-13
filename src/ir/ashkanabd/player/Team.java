package ir.ashkanabd.player;

import ir.ashkanabd.client.*;

import java.util.*;

public class Team extends Client {
    @Override
    public String teamName() {
        return "Baha2r";
    }

   private boolean check = false;
   private boolean hor = false;
   private boolean ver = false;

    @Override
    public void think(Player player) {

        boolean fill = false;
        int full;
        Node[][] map = player.getMap();
        HashMap<Node, Integer> hashMap = new HashMap<>();
        try {
            for (int i = 0; i < player.getRowCount(); i++) {
                for (int j = 0; j < player.getColumnCount(); j++) {
                    full = 0;
                    if (map[i][j].isBlock()) {
                        if (map[i - 1][j].isMarked()) {
                            full++;
                        }
                        if (map[i][j - 1].isMarked()) {
                            full++;
                        }
                        if (map[i + 1][j].isMarked()) {
                            full++;
                        }
                        if (map[i][j + 1].isMarked()) {
                            full++;
                        }
                        hashMap.put(map[i][j], full);
                    }
                }
            }


            for (int i = 0; i < player.getRowCount(); i++) {
                for (int j = 0; j < player.getColumnCount(); j++) {
                    hashMap.putIfAbsent(map[i][j], -1);
                }
            }
            if (!check) {
                for (int i = 0; i < player.getRowCount(); i++) {
                    for (int j = 0; j < player.getColumnCount(); j++) {
                        if (hashMap.get(map[i][j]) != 0 && hashMap.get(map[i][j]) != -1) {
                            if (i == 1 && j == 1) {
                                if (map[i][j + 1].isMarked()) {
                                    ver = true;
                                    check = true;
                                    break;
                                } else if (map[i + 1][j].isMarked()) {
                                    hor = true;
                                    check = true;
                                    break;
                                } else if (map[i - 1][j].isMarked()) {
                                    hor = true;
                                    check = true;
                                    break;
                                }
                            } else {
                                if (map[i - 1][j].isMarked()) {
                                    hor = true;
                                    check = true;
                                    break;
                                } else if (map[i][j - 1].isMarked()) {
                                    ver = true;
                                    check = true;
                                    break;
                                } else if (map[i + 1][j].isMarked()) {
                                    hor = true;
                                    check = true;
                                    break;
                                } else if (map[i][j + 1].isMarked()) {
                                    ver = true;
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < player.getRowCount(); i++) {
                for (int j = 0; j < player.getColumnCount(); j++) {
                    if (hashMap.get(map[i][j]) == 3 && !fill) {
                        if (!map[i - 1][j].isMarked()) {
                            player.fill(i - 1, j);
                            fill = true;
                        } else if (!map[i][j - 1].isMarked()) {
                            player.fill(i, j - 1);
                            fill = true;
                        } else if (!map[i + 1][j].isMarked()) {
                            player.fill(i + 1, j);
                            fill = true;
                        } else if (!map[i][j + 1].isMarked()) {
                            player.fill(i, j + 1);
                            fill = true;
                        }
                    }
                }
            }
            for (int i = 0; i < player.getRowCount(); i++) {
                for (int j = 0; j < player.getColumnCount(); j++) {
                    if (hashMap.get(map[i][j]) == 0 || hashMap.get(map[i][j]) == 1 && !fill) {
                        if (!check || hor) {
                            if (!map[i][j - 1].isMarked() && !fill) {
                                if (j != 1) {
                                    if (hashMap.get(map[i][j - 2]) != 2) {
                                        player.fill(i, j - 1);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i, j - 1);
                                    fill = true;
                                }
                            }

                            if (!map[i][j + 1].isMarked() && !fill) {
                                if (j != 7) {
                                    if (hashMap.get(map[i][j + 2]) != 2) {
                                        player.fill(i, j + 1);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i, j + 1);
                                    fill = true;
                                }
                            }
                            if (!map[i + 1][j].isMarked() && !fill) {
                                if (i != 7) {
                                    if (hashMap.get(map[i + 2][j]) != 2) {
                                        player.fill(i + 1, j);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i + 1, j);
                                    fill = true;
                                }
                            }
                            if (!map[i - 1][j].isMarked() && !fill) {
                                if (i != 1) {
                                    if (hashMap.get(map[i - 2][j]) != 2) {
                                        player.fill(i - 1, j);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i - 1, j);
                                    fill = true;
                                }
                            }
                        } else if (ver) {
                            if (!map[i + 1][j].isMarked() && !fill) {
                                if (i != 7) {
                                    if (hashMap.get(map[i + 2][j]) != 2) {
                                        player.fill(i + 1, j);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i + 1, j);
                                    fill = true;
                                }
                            }
                            if (!map[i - 1][j].isMarked() && !fill) {
                                if (i != 1) {
                                    if (hashMap.get(map[i - 2][j]) != 2) {
                                        player.fill(i - 1, j);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i - 1, j);
                                    fill = true;
                                }
                            }
                            if (!map[i][j - 1].isMarked() && !fill) {
                                if (j != 1) {
                                    if (hashMap.get(map[i][j - 2]) != 2) {
                                        player.fill(i, j - 1);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i, j - 1);
                                    fill = true;
                                }
                            }

                            if (!map[i][j + 1].isMarked() && !fill) {
                                if (j != 7) {
                                    if (hashMap.get(map[i][j + 2]) != 2) {
                                        player.fill(i, j + 1);
                                        fill = true;
                                    }
                                } else {
                                    player.fill(i, j + 1);
                                    fill = true;
                                }
                            }

                        }
                    }

                }
            }
            for (int i = 0; i < player.getRowCount(); i++) {
                for (int j = 0; j < player.getColumnCount(); j++) {
                    if (hashMap.get(map[i][j]) == 2 && !fill) {
                        if (i == 1 && j == 1) {
                            if (!map[i - 1][j].isMarked() && !map[i][j - 1].isMarked()) {
                                player.fill(i, j - 1);
                                fill = true;
                            }
                        }
                        if (i == 1 && j == 7) {
                            if (!map[i - 1][j].isMarked() && !map[i][j + 1].isMarked()) {
                                player.fill(i, j + 1);
                                fill = true;
                            }
                        }
                        if (i == 7 && j == 1) {
                            if (!map[i][j - 1].isMarked() && !map[i + 1][j].isMarked()) {
                                player.fill(i + 1, j);
                                fill = true;
                            }
                        }
                        if (i == 7 && j == 7) {
                            if (!map[i][j + 1].isMarked() && !map[i + 1][j].isMarked()) {
                                player.fill(i + 1, j);
                                fill = true;
                            }
                        }
                    }
                }
            }
            for (int i = player.getRowCount() - 1; i >= 0; i--) {
                for (int j = player.getColumnCount() - 1; j >= 0; j--) {
                    if (hashMap.get(map[i][j]) == 2 && !fill) {
                        if (!map[i - 1][j].isMarked()) {
                            if (i != 1) {
                                if (hashMap.get(map[i - 2][j]) != 2) {
                                    player.fill(i - 1, j);
                                    fill = true;
                                }
                            } else {
                                player.fill(i - 1, j);
                                fill = true;
                            }
                        }
                        if (!map[i][j - 1].isMarked() && !fill) {
                            if (j != 1) {
                                if (hashMap.get(map[i][j - 2]) != 2) {
                                    player.fill(i, j - 1);
                                    fill = true;
                                }
                            } else {
                                player.fill(i, j - 1);
                                fill = true;
                            }
                        }
                        if (!map[i + 1][j].isMarked() && !fill) {
                            if (i != 7) {
                                if (hashMap.get(map[i + 2][j]) != 2) {
                                    player.fill(i + 1, j);
                                    fill = true;
                                }
                            } else {
                                player.fill(i + 1, j);
                                fill = true;
                            }
                        }
                        if (!map[i][j + 1].isMarked() && !fill) {
                            if (j != 7) {
                                if (hashMap.get(map[i][j + 2]) != 2) {
                                    player.fill(i, j + 1);
                                    fill = true;
                                }
                            } else {
                                player.fill(i, j + 1);
                                fill = true;
                            }
                        }
                    }
                }
            }
            if (!fill) {
                for (int i = 0; i < player.getRowCount(); i++) {
                    for (int j = 0; j < player.getColumnCount(); j++) {
                        if (hashMap.get(map[i][j]) == 0 || hashMap.get(map[i][j]) == 1) {
                            if (!map[i - 1][j].isMarked() && !fill) {
                                player.fill(i - 1, j);
                                fill = true;
                            } else if (!map[i][j - 1].isMarked() && !fill) {
                                player.fill(i, j - 1);
                                fill = true;
                            } else if (!map[i + 1][j].isMarked() && !fill) {
                                player.fill(i + 1, j);
                                fill = true;
                            } else if (!map[i][j + 1].isMarked() && !fill) {
                                player.fill(i, j + 1);
                                fill = true;
                            }
                        }
                        if (hashMap.get(map[i][j]) == 2 && !fill) {
                            if (!map[i - 1][j].isMarked()) {
                                player.fill(i - 1, j);
                                fill = true;
                            } else if (!map[i][j - 1].isMarked()) {
                                player.fill(i, j - 1);
                                fill = true;

                            } else if (!map[i + 1][j].isMarked()) {
                                player.fill(i + 1, j);
                                fill = true;

                            } else if (!map[i][j + 1].isMarked()) {
                                player.fill(i, j + 1);
                                fill = true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
