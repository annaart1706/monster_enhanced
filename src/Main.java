import monsters.BigMonster;
import monsters.DeadMonster;
import monsters.Monster;
import monsters.SmallMonster;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int sizeBoard = 6;

        Board board = new Board(sizeBoard);

        Person person = new Person(sizeBoard);

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();

        // для работы с большим количеством монстров воспользуемся массивом
        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test = null;
        while (count <= countMonster) {
            switch (r.nextInt(4)) {
                case 0 -> {
                    test = new Monster(sizeBoard);
                }
                case 1 -> {
                    test = new BigMonster(sizeBoard);
                }
                case 2 -> {
                    test = new SmallMonster(sizeBoard);
                }
                case 3 -> {
                    test = new DeadMonster(sizeBoard);
                }
            }

            if (board.setMonster(test)) {
                arrMonster[count++] = test;
            }

        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;
        board.setCastle(castleX, castleY);
//        board[castleY][castleX] = castle;

//        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА(+) или НЕТ(-))");


//        String answer = sc.nextLine().toUpperCase();
        String answer = "+";
//                System.out.println("Ваш ответ:\t" + answer);


        switch (answer) {
            case "ДА", "+" -> {
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);
                while (true) {
                    board.setPerson(person);
                    board.outputBoard(person.getLive());
//                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
//                    outputBoard(sizeBoard, board, person.getLive());

//                    int x = sc.nextInt();
//                    int y = sc.nextInt();
                    int x = person.getX(), y = person.getY();
                    String direction = sc.nextLine().toLowerCase();
                    switch (direction) {
                        case "w", "ц" -> {
                            y--;
                        }
                        case "s", "ы" -> {
                            y++;
                        }
                        case "d", "в" -> {
                            x++;
                        }
                        case "a", "ф" -> {
                            x--;
                        }
                    }
                    clearConsole();

                    // проверка
                    if (person.moveCorrect(x, y)) {
                        String next = board.getValue(x, y);
                        if (board.isEmpty(x, y)) {
                            board.setValue(person.getX(), person.getY(), "  ");
//                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                        } else if (board.isCastle(x, y)) {
                            System.out.println("Вы прошли игру! \uD83D\uDE80 \uD83D\uDCAA");
                            break;
                        } else {
                            for (Monster monster : arrMonster) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board.setValue(person.getX(), person.getY(), "  ");
//                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);

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
            case "НЕТ", "-" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены некорректно");
        }

    }



    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Executes the Windows 'cls' command via the command interpreter
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Executes the Unix 'clear' command
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}