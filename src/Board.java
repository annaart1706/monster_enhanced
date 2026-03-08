import monsters.Monster;

import java.util.Objects;

public class Board {
    private final String castle = "\uD83C\uDFF0";
    private final String empty = "  ";

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

    public void setValue(int x, int y, String value) {
        board[y - 1][x - 1] = value;
    }

    public void movePerson(Person person, int x, int y) {
        this.setValue(person.getX(), person.getY(), empty);
        person.move(x, y);
        this.setPerson(person);
    }

    public boolean isEmpty(int x, int y) {
        return Objects.equals(board[y - 1][x - 1], "  ");
    }

    public boolean isCastle(int x, int y) {
        return Objects.equals(board[y - 1][x - 1], castle);
    }


    void outputBoard(int live) {
        String leftBlock = "  ";
        String rightBlock = "|";
        String wall = "+" + " —— +".repeat(board.length);
        System.out.println(wall);
        for (String[] raw : board) {
            StringBuilder rawVisual = new StringBuilder("|");
            for (String cell : raw) {
                rawVisual.append(leftBlock).append(cell).append(" ");
            }
            rawVisual.append(rightBlock);
            System.out.println(rawVisual);
        }
        System.out.println(wall);


        System.out.println("Количество жизней:\t" + live);
    }
}
