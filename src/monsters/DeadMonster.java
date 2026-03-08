package monsters;

public class DeadMonster extends Monster {
    private boolean active = true;

    public DeadMonster(int sizeBoard) {
        super(sizeBoard);
        image = "\uD83D\uDC80";
    }
    @Override
    public boolean taskMonster(int difficultGame) {
        if (active) {
            System.out.println("Ты проиграл эту битву!");

        }
        active = !active;
        return active;
    }

}
