import monsters.BigMonster;
import monsters.DeadMonster;
import monsters.Monster;
import monsters.SmallMonster;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выбери сложность игры(от 1 до 5):");
        int difficultGame = sc.nextInt();
        System.out.println("Выбранная сложность:\t" + difficultGame);

        int sizeBoard = difficultGame + 6;

        Board board = new Board(sizeBoard);

        Person person = new Person(sizeBoard);

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();

        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test = null;
        while (count <= countMonster) {
            switch (r.nextInt(4)) {
                case 0 -> test = new Monster(sizeBoard);
                case 1 -> test = new BigMonster(sizeBoard);
                case 2 -> test = new SmallMonster(sizeBoard);
                case 3 -> test = new DeadMonster(sizeBoard);
            }

            if (board.setMonster(test)) {
                arrMonster[count++] = test;
            }

        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;
        board.setCastle(castleX, castleY);


        while (true) {
            board.setPerson(person);
            board.outputBoard(person.getLive());

            int x = person.getX(), y = person.getY();
            String direction = sc.nextLine().toLowerCase();
            switch (direction) {
                case "w", "ц" -> y--;
                case "s", "ы" -> y++;
                case "d", "в" -> x++;
                case "a", "ф" -> x--;
            }
//            clearConsole();

            // проверка
            if (person.moveCorrect(x, y)) {
                if (board.isEmpty(x, y)) {
                    board.movePerson(person, x, y);
                } else if (board.isCastle(x, y)) {
                    System.out.println("Вы прошли игру! \uD83D\uDE80 \uD83D\uDCAA");
                    break;
                } else {
                    for (Monster monster : arrMonster) {
                        if (monster.conflictPerson(x, y)) {
                            if (monster.taskMonster(difficultGame)) {
                                board.movePerson(person, x, y);
                            } else {
                                person.downLive();
                                if (person.getLive() == 0) {
                                    System.out.println("Game over \uD83D\uDC80");
                                    return;
                                }
                            }
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Некорректный ход \uD83D\uDC40");
            }
        }
    }
}