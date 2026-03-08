package cells;

import java.util.Random;
import java.util.Scanner;

public class Monster extends Cell {
    protected final Random r = new Random();

    public Monster() {
        super();
        image = "\uD83E\uDDDF\u200D";
    }

    public boolean isEmpty() {
        return false;
    }


    public boolean task(int difficultGame) {
        int x = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
        int y = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
        int trueAnswer = x + y;
        System.out.println("Реши пример: " + x + " + " + y + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        return false;
    }
}