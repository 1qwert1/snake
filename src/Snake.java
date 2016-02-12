import java.util.LinkedList;

public class Snake {

    LinkedList<SnakePoint> snake;
    LinkedList<Food> food;
    Food f;
    public Snake(int x, int y, int length) {
        SnakePoint head = new SnakePoint(x, y);
        f = new Food();
        food = new LinkedList<>();
        food.add(f);
        snake = new LinkedList<>();
        snake.add(head);

//добавляяем точки к змее в колличестве length;
        for (int i = 1; i <= length; i++) {
            snake.add(snake.getLast().move(Directions.DOWN));
        }

    }

    public boolean isEatSnake() {
        for (int i = 0; i < snake.size()-2; i++) {
             if(snake.get(i).isEatTAil(snake.getLast()))
                 return false;
        }

        return true;

    }

    public boolean gameOver() {

            return !(snake.getLast().x >= Panel.WIDTH_FRAME ||
                    snake.getLast().y >= Panel.HEIGHT_FRAME || snake.getLast().x < 0 || snake.getLast().y < 0 ) ;

    }

    public void moveSnake(Directions d) {
        snake.add(snake.getLast().move(d));
        if(snake.getLast().x != food.getLast().x ||  snake.getLast().y != food.getLast().y) {
            snake.removeFirst();
        }
        else {
            food.removeFirst();
            f = new Food();
            food.add(f);
        }


    }




}
