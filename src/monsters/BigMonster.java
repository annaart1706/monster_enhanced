package monsters;

import java.util.Scanner;

public class BigMonster extends Monster {
    public BigMonster(int sizeBoard) {
        super(sizeBoard);
        image = "\uD83D\uDC79";
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        if (difficultGame == 1) {
            return taskMonster(0);
        } else {
            int x = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int y = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int z = r.nextInt(100 * (difficultGame - 1), 100 * difficultGame);
            int trueAnswer = x * y - z;
            System.out.println("Реши пример: " + x + " * " + y + " - " + z + " = ?");
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

}
