import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Panel extends JPanel implements Runnable,  KeyListener{


    static final int WIDTH_FRAME = 30;

    static final int HEIGHT_FRAME = 20;

    static final int SIZE = 25;

    Snake snake;

    Directions directions;



    public Panel() {
        snake = new Snake(10, 5 ,3);
        directions = Directions.RIGHT;
        addKeyListener(this);
        setFocusable(true);
        (new Thread(this)).start();

    }

    public void paint(Graphics g) {

        g.setColor(new Color(50,180,120));
        g.fillRect(0,0,WIDTH_FRAME*SIZE, HEIGHT_FRAME*SIZE);
        g.setColor(Color.GRAY);
        for (int i = SIZE; i <= WIDTH_FRAME*SIZE-SIZE; i+=SIZE) {
            g.drawLine(i, 0, i, HEIGHT_FRAME*SIZE);
        }
        for (int i = SIZE; i <= HEIGHT_FRAME*SIZE-SIZE; i+=SIZE) {
            g.drawLine(0, i, WIDTH_FRAME*SIZE, i);
        }

        g.setColor(Color.LIGHT_GRAY);
        for (SnakePoint p: snake.snake
             ) {
            g.fill3DRect(p.x*SIZE, p.y*SIZE, SIZE, SIZE, true);
        }
        g.setColor(Color.yellow);
        for (Food p: snake.food
                ) {
            g.fillOval(p.x * SIZE, p.y * SIZE, SIZE, SIZE);
        }

    }



    public static void startGame() {
        JFrame frame = new JFrame();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(new Dimension(WIDTH_FRAME*SIZE, HEIGHT_FRAME*SIZE+28));
        frame.setLocationRelativeTo(null);

        frame.add(new Panel());
        frame.setVisible(true);
    }





    @Override
    public void keyTyped(KeyEvent e) {

    }
    boolean pause = true;
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP && directions != Directions.DOWN) {
            directions = Directions.UP;

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && directions != Directions.UP) {
            directions = Directions.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && directions != Directions.LEFT) {
            directions = Directions.RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && directions != Directions.RIGHT) {
            directions = Directions.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_P && pause) {
            pause = false;
        } else if (e.getKeyCode() == KeyEvent.VK_P && !pause){
            pause = true;
            (new Thread(this)).start();
        }else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        startGame();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void run() {
        while (snake.gameOver() && snake.isEatSnake() && pause) {
            try {
                snake.moveSnake(directions);
                repaint();
                Thread.sleep(120);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
