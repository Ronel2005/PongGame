import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
	

    String player1Name;
    String player2Name;

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.55555) );
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	// constructor for game panel
	GamePanel(String player1Name, String player2Name) {
		 
		newPaddle();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		this.player1Name = player1Name;
        this.player2Name = player2Name;
	}
	
	// declaring classes 
	public void newBall() {
		//random = new Random();
		// places the ball in the center of  screen
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2),(GAME_HEIGHT/2) - (BALL_DIAMETER/2),BALL_DIAMETER, BALL_DIAMETER );
		
	}
	
	public void newPaddle(){
		// centers paddles on screen
		paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	public void paint(Graphics g) {
		
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0,0,this);
	}
	
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.updateHighestScore();
        score.draw(g);

        // Display player names
        g.setColor(Color.white);
        g.drawString(player1Name, 50, 50);
        g.drawString(player2Name, GAME_WIDTH - 150, 50);

        // Display highest score beneath Player 2 name
        g.setFont(new Font("IMPACT", Font.BOLD, 15));
        g.drawString("High Score: " + score.highestScore, GAME_WIDTH - 150, 70);
    }

	public void move() {
		//makes the movement of paddles and ball smoother
		paddle1.move();
		paddle2.move();	
		ball.move();
	}
	
	public void checkCollision(){
		// allows the ball to bounce off the window edges
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		// allow ball to bounce of paddles
		
		// --------------- paddle 1 ----------------
		if(ball.intersects(paddle1)) {
			// math method that inverses a value
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity += 0.5; //when ball collides with a paddle the speed increases by 0.5
			if(ball.yVelocity > 0)
				ball.yVelocity += 0.5;
			else
				ball.yVelocity -= 0.5;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		// --------------- paddle 2 ----------------
		if(ball.intersects(paddle2)) {
			// math method that inverses a value
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity += 1; //when ball collides with a paddle the speed increases by 0.5
			if(ball.yVelocity > 0)
				ball.yVelocity += 1;
			else
				ball.yVelocity -= 1;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		
		
		// stops the paddle from moving beyond the bounds of the window edges
		if(paddle1.y<=0)
			paddle1.y =0;
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		if(paddle2.y<=0)
			paddle2.y =0;
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		// give the winning player 1 point and create a new round;
		// ------- player 2 ---------
		if(ball.x <= 0) {
			score.player2++;
			newPaddle();
			newBall();
			System.out.println("p2 score: " + score.player2);
		}
		
		// ----- player 1 ------
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddle();
			newBall();
			System.out.println("p1 score: " + score.player1);
		}
	}
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta --;
				
			}
		}
	}
	
	// action Listener for capturing key press and key releases
	public class AL extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
