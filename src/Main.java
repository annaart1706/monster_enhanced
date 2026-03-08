import cells.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выбери сложность игры(от 1 до 5):");
        int difficultGame = 1;//sc.nextInt();
        System.out.println("Выбранная сложность:\t" + difficultGame);

        int sizeBoard = difficultGame + 5;
        Board board = new Board(sizeBoard);
        Person person = board.person;


        while (true) {
            board.outputBoard(person.getLive());

            int x = person.getX(), y = person.getY();
            String direction = sc.nextLine().toLowerCase();
            switch (direction) {
                case "w", "ц" -> y--;
                case "s", "ы" -> y++;
                case "d", "в" -> x++;
                case "a", "ф" -> x--;
            }

            Cell next = board.getCell(x, y);

            // проверка
            if (next != null) {
                if (next.isEmpty()) {
                    board.movePerson(person, x, y);
                } else if (next instanceof Castle) {
                    System.out.println("Вы прошли игру! \uD83D\uDE80 \uD83D\uDCAA");
                    break;
                } else if (next instanceof Monster monster) {
                    if (monster.task(difficultGame)) {
                        board.movePerson(person, x, y);
                    } else {
                        person.downLive();
                        if (person.getLive() == 0) {
                            System.out.println("Game over \uD83D\uDC80");
                            return;
                        }
                    }
                }
            } else {
                System.out.println("Некорректный ход \uD83D\uDC40");
            }
        }
    }
}