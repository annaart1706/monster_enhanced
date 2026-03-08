package cells;

public class Person extends Cell {
    private int x, y;
    private int live = 3;

    Person(int x, int y) {
        this.image = "\uD83E\uDDD9\u200D";
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLive() {
        return live;
    }

    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void downLive() {
        live--;
    }
}