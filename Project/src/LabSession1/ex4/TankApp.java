package LabSession1.ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TankApp {
    private static final int MAX_RETRIES = 3;
    private static int retries = 0;
    private static int score = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Moving Shapes Game");
            frame.setSize(600, 500);
            frame.setLayout(new FlowLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Square[] squares = new Square[3];
            SquareThread[] threads = new SquareThread[3];

            for (int i = 0; i < 3; i++) {
                squares[i] = new Square(50 * (i + 1), 50, 30);
                SquarePanel squarePanel = new SquarePanel(squares[i]);
                threads[i] = new SquareThread(squares[i], 2, 5, squarePanel);
                frame.add(squarePanel);
            }

            Tank tank = new Tank(250, 400, 40, 20);
            TankController tankController = new TankController(tank, frame);

            frame.addKeyListener(tankController);

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    stopThreads(threads);
                }
            });

            frame.setVisible(true);

            startThreads(threads);

            // Use a Timer for continuous tank movement
            Timer timer = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tank.move();
                    frame.repaint();
                }
            });
            timer.start();
        });
    }

    private static void startThreads(SquareThread[] threads) {
        for (SquareThread thread : threads) {
            thread.start();
        }
    }

    private static void stopThreads(SquareThread[] threads) {
        for (SquareThread thread : threads) {
            thread.stopThread();
        }
    }

    private static class Square {
        private int x;
        private int y;
        private int size;

        public Square(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void move(int speed) {
            y += speed;
            if (y > 500) {
                // Respawn at the top
                y = -size;
            }
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }
    }

    private static class SquarePanel extends JPanel {
        private Square square;

        public SquarePanel(Square square) {
            this.square = square;
            setPreferredSize(new Dimension(100, 100));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (square.getY() < 400) {
                g.drawRect(10, square.getY(), square.getSize(), square.getSize());
            }
        }
    }

    private static class SquareThread extends Thread {
        private Square square;
        private boolean running;
        private int minSpeed;
        private int maxSpeed;
        private SquarePanel squarePanel;

        public SquareThread(Square square, int minSpeed, int maxSpeed, SquarePanel squarePanel) {
            this.square = square;
            this.minSpeed = minSpeed;
            this.maxSpeed = maxSpeed;
            this.running = true;
            this.squarePanel = squarePanel;
        }

        public void run() {
            while (running && square.getY() < 400) {
                square.move((int) (Math.random() * (maxSpeed - minSpeed + 1) + minSpeed));
                try {
                    SwingUtilities.invokeAndWait(() -> squarePanel.repaint());
                    sleep(50);
                } catch (InterruptedException | java.lang.reflect.InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopThread() {
            running = false;
        }
    }

    private static class Tank {
        private int x;
        private int y;
        private int width;
        private int height;

        public Tank(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public void move() {
            // Add logic for tank movement (left, right) based on user input
        }

        public void moveLeft() {
            x -= 5;
            if (x < 0) {
                x = 0;
            }
        }

        public void moveRight() {
            x += 5;
            if (x > 560) {
                x = 560;
            }
        }
    }

    private static class TankController extends KeyAdapter {
        private Tank tank;
        private JFrame frame;

        public TankController(Tank tank, JFrame frame) {
            this.tank = tank;
            this.frame = frame;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                tank.moveLeft();
            } else if (key == KeyEvent.VK_RIGHT) {
                tank.moveRight();
            }
            frame.repaint();
        }
    }
}
