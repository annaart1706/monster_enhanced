package monsters;

public class DeadMonster extends Monster {
    private boolean active = true;

    private String image = "\uD83D\uDC80";

    public DeadMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
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
