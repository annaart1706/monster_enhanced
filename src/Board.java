import monsters.Monster;

import java.util.Objects;

public class Board {
    private final String castle = "\uD83C\uDFF0";
    String[][] board;

    public Board(int size) {
        board = new String[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = "  ";
            }
        }
    }

    public boolean setMonster(Monster monster) {
        if (board[monster.getY()][monster.getX()].equals("  ")) {
            board[monster.getY()][monster.getX()] = monster.getImage();
            return true;
        }
        return false;
    }

    public void setCastle(int x, int y) {
        board[y][x] = castle;
    }

    public void setPerson(Person person) {
        board[person.getY() - 1][person.getX() - 1] = person.getImage();
    }

    public String getValue(int x, int y) {
        return board[y - 1][x - 1];
    }

    public void setValue(int x, int y, String value) {
        board[y - 1][x - 1] = value;
    }

    public boolean isEmpty(int x, int y) {
        return Objects.equals(board[y - 1][x - 1], "  ");
    }

    public boolean isCastle(int x, int y) {
        return Objects.equals(board[y - 1][x - 1], castle);
    }


    void outputBoard(int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        StringBuilder wall = new StringBuilder("+");
        wall.append(" —— +".repeat(board.length));

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);


        System.out.println("Количество жизней:\t" + live + "\n");
    }
}
