import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SquareGame extends JFrame {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int SQUARE_SIZE = 50;
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 5;

    private SquarePanel squarePanel;

    public SquareGame() {
        setTitle("Square Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        squarePanel = new SquarePanel();
        add(squarePanel);

        setVisible(true);

        squarePanel.start(); // Start repainting
    }

    private class SquarePanel extends JPanel {
        private Square[] squares;
        private volatile boolean running; // voltatile because it's values are modified for different threads

        public SquarePanel() {
            setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
            setBackground(Color.GRAY);

            squares = new Square[3];
            Random random = new Random();
            for (int i = 0; i < squares.length; i++) {
                int x = 50 + i * 200;
                int y = 0;
                int speed = random.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED;
                squares[i] = new Square(x, y, speed); // the squares represent the actual threads
                new Thread(squares[i]).start();
            }
            running = true; // the threads are still running in parallel
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for (Square square : squares) { //for each square
                g.fillRect(square.getX(), square.getY(), SQUARE_SIZE, SQUARE_SIZE); //found on the internet
            }
        }

        public void start() {
            new Thread(() -> {
                while (running) { // while the squares are running
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // handling interruptions
                    }
                }
            }).start();
        }

        public void stopSquares() {
            running = false;
        }
    }

    private class Square implements Runnable {
        private int x;
        private int y;
        private int speed;

        public Square(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public void run() {
            while (squarePanel.running) {
                y += speed;
                if (y > WINDOW_HEIGHT) {
                    return; // exit the thread if the square exits the window
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return; // thread interrupted?, then exit the loop
                }
            }
        }
    }

    public static void main(String[] args) {
        new SquareGame();
    }
}
