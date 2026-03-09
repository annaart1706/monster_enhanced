package cells;

public class DeadMonster extends Monster {
    private boolean active = true;

    public DeadMonster() {
        super();
        image = "\uD83D\uDC80";
    }

    @Override
    public boolean task(int difficultGame) {
//        if (active) {
//            System.out.println("Ты проиграл эту битву!");
//
//        }
        active = !active;
        return true;
    }

    public void damage(Person person) {
        person.downLive();
    }

}
