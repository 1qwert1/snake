public class SnakePoint {

    int x;
    int y;


    public SnakePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }





    public boolean isEatTAil(SnakePoint snakePoint) {
        return (x == snakePoint.x && y == snakePoint.y);

    }


    public SnakePoint move(Directions directions) {
        if (directions == Directions.RIGHT) {

            x++;

        } else if (directions == Directions.LEFT) {

            x--;

        } else if (directions == Directions.UP) {

            y--;

        } else

            y++;
        return new SnakePoint(x, y);

    }

}
