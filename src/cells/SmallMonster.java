package cells;

import java.util.Scanner;

public class SmallMonster extends Monster {
    public SmallMonster() {
        super();
        image = "\uD83D\uDE08";
    }

    @Override
    public boolean task(int difficultGame) {
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        int trueAnswer = x * y;
        System.out.println("Реши пример: " + x + " * " + y + " = ?");
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
