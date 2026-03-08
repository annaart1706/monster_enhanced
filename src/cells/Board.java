package cells;

import java.util.Random;

public class Board {
    private final Cell[][] board;
    public Person person;

    private final Random r = new Random();

    public Board(int size) {
        board = new Cell[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = y == size - 1 ? new Cell() : this._getCell();
            }
        }
        board[0][r.nextInt(size)] = new Castle();
        person = new Person(r.nextInt(size), size - 1);
        board[person.getY()][person.getX()] = person;
    }

    private Cell _getCell() {
        switch (r.nextInt(5)) {
//            case 0 -> new Cell();
            case 1 -> {
                return new Monster();
            }
            case 2 -> {
                return new BigMonster();
            }
            case 3 -> {
                return new SmallMonster();
            }
            case 4 -> {
                return new DeadMonster();
            }
            default -> {
                return new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        System.out.println(x + " " + y);
        if (x >= 0 && x < this.board.length && y >= 0 && y < this.board.length) {
            return board[y][x];
        }
        return null;
    }


    public void movePerson(Person person, int x, int y) {
        board[person.getY()][person.getX()] = new Cell();
        person.move(x, y);
        board[y][x] = person;
    }


    public void outputBoard(int live) {
        String leftBlock = "  ";
        String rightBlock = "|";
        String wall = "+" + " —— +".repeat(board.length);
        System.out.println(wall);
        for (Cell[] raw : board) {
            StringBuilder rawVisual = new StringBuilder("|");
            for (Cell cell : raw) {
                rawVisual.append(leftBlock).append(cell.getImage()).append(" ");
            }
            rawVisual.append(rightBlock);
            System.out.println(rawVisual);
        }
        System.out.println(wall);


        System.out.println("Количество жизней:\t" + live);
    }
}
