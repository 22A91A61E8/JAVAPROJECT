import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth;
    int boardHeight;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int birdX, birdY, birdWidth, birdHeight;
    int pipeWidth, pipeHeight;
    int pipeY = 0;

    class Bird {
        int x, y, width, height;
        Image img;
        Bird(Image img) {
            this.x = birdX;
            this.y = birdY;
            this.width = birdWidth;
            this.height = birdHeight;
            this.img = img;
        }
    }

    class Pipe {
        int x, y, width, height;
        Image img;
        boolean passed = false;

        Pipe(Image img, int x, int y) {
            this.x = x;
            this.y = y;
            this.width = pipeWidth;
            this.height = pipeHeight;
            this.img = img;
        }
    }

    Bird bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipeTimer;
    boolean gameOver = false;
    double score = 0;

    int lastPipeX = 0;
    final int minHorizontalGap = 200;
    final int maxExtraGap = 150;

    public FlappyBird(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Size calculations
        birdWidth = boardWidth / 14;
        birdHeight = boardHeight / 25;
        birdX = boardWidth / 8;
        birdY = boardHeight / 2;

        pipeWidth = boardWidth / 10;
        pipeHeight = boardHeight * 4 / 5;

        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage()
                .getScaledInstance(boardWidth, boardHeight, Image.SCALE_SMOOTH);
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage()
                .getScaledInstance(birdWidth, birdHeight, Image.SCALE_SMOOTH);
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage()
                .getScaledInstance(pipeWidth, pipeHeight, Image.SCALE_SMOOTH);
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage()
                .getScaledInstance(pipeWidth, pipeHeight, Image.SCALE_SMOOTH);

        bird = new Bird(birdImg);
        pipes = new ArrayList<>();

        // Place first pipe immediately
        lastPipeX = boardWidth + 100; // off screen
        placePipes();

        // Start pipe timer
        placePipeTimer = new Timer(1000, e -> placePipes());
        placePipeTimer.start();

        // Game loop
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    void placePipes() {
        int openingSpace = boardHeight / 4;

        int randomY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int horizontalGap = minHorizontalGap + random.nextInt(maxExtraGap);

        int pipeX = lastPipeX + horizontalGap;

        Pipe topPipe = new Pipe(topPipeImg, pipeX, randomY);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg, pipeX, randomY + pipeHeight + openingSpace);
        pipes.add(bottomPipe);

        lastPipeX = pipeX;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + (int) score, 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;

            if (gameOver) {
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                lastPipeX = boardWidth + 100;
                placePipes();
                gameOver = false;
                score = 0;
                gameLoop.start();
                placePipeTimer.start();
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
