package org.sevenhills.liueri19;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Table extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	//graphics timer
	private final Timer displayTimer = new Timer(17, this);	//approximately 60 fps
	//game clock
	public final int gameClockInterval = 16;
	private final GameClock gameClock = new GameClock(this);
	//ball
	//private List<Ball> balls = new ArrayList<Ball>();	may add multiple balls for difficulty
	private Ball ball;
	public final double ballVelocity = 5;	//unit: pixels / GameClock's Delay 
	public final int ballRadius = 10;
	//player
	private int playerLScore = 0;
	private int playerRScore = 0;
	public static final int MAX_SCORE = 10;
	//paddles
	//private List<Paddle> paddles = new ArrayList<Paddle>(2);
	private Paddle leftPaddle, rightPaddle;
	public final int paddleWidth = 20;
	public final int paddleHeight = 80;
	public final int paddleEdgeDistance = 30;
	public final double paddleVelocity = 4;
	public final double paddleDisplacement = paddleVelocity / 4;	//the displacement a moving paddle would cause on the ball bouncing off it
	
	public final int width, height;
	private boolean paused = false;
	private boolean AIState = true;
	
	public Table() {
		this(800, 600);
		//balls.add(Ball.construct(this, velocity));
		ball = Ball.construct(this, ballVelocity);
		//setup a 800*600 table with a "random" heading ball in the center.
	}
	
	public Table(int width, int height) {
		super(true);
		this.width = width;
		this.height = height;
		leftPaddle = new Paddle(this, paddleEdgeDistance, AIState);
		rightPaddle = new Paddle(this, width - paddleEdgeDistance - paddleWidth, false);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				(new Table()).setUp();
			}
		});
	}
	
	public void setUp() {
		JFrame frame = new JFrame("Pong Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyHandler(this));
        frame.add(this);
        frame.pack();
		frame.setVisible(true);
		displayTimer.start();
		gameClock.execute();
	}
	
	public void updateAll() {
		ball.update();
		leftPaddle.update();
		rightPaddle.update();
	}
	
	public void resetBall() {
		//construct new ball
		ball.reset();
	}
	
	public synchronized void pauseGame() {
			gameClock.pause();
			paused = true;
	}
	
	public synchronized void resumeGame() {
			this.notifyAll();
			paused = false;
	}
	
	public synchronized void restart() {
		playerLScore = 0;
		playerRScore = 0;
		getLeftPaddle().setY(height / 2);
		getRightPaddle().setY(height / 2);
		resetBall();
		resumeGame();
	}
	
	//invoked on every repaint()
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		//paint ball
		int d = 2 * ballRadius;
		g.fillOval((int) ball.getX() - ballRadius, (int) ball.getY() - ballRadius, d, d);
		//paint left paddle
		g.fillRect(paddleEdgeDistance, (int) leftPaddle.getY(), paddleWidth, paddleHeight);
		//paint right paddle
		g.fillRect(width - paddleEdgeDistance - paddleWidth, (int) rightPaddle.getY(), paddleWidth, paddleHeight);
		//paint scores
		int pointSize = 32;
		boolean endGame = (playerLScore == MAX_SCORE || playerRScore == MAX_SCORE);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, pointSize));
		FontMetrics fm = g.getFontMetrics();
		if (endGame)
			g.setColor(Color.YELLOW);
		g.drawString(
				String.format("%02d : %02d", getPlayerLScore(), getPlayerRScore()),	// format string to "XX : XX"
				(int) (width / 2 - fm.charWidth('0') * 3.5), fm.getHeight());
		if (endGame)
			pauseGame();
	}
	
	//will be invoked by the timer with 20 milliseconds intervals
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
	
	public void playerLScore() {
		playerLScore++;
		resetBall();
	}
	
	public void playerRScore() {
		playerRScore++;
		resetBall();
	}
	
	//accessors & mutators
	public Ball getBall() {
		return ball;
	}
	
	public Paddle getLeftPaddle() {
		return leftPaddle;
	}
	
	public Paddle getRightPaddle() {
		return rightPaddle;
	}
	
	public int getPlayerLScore() {
		return playerLScore;
	}
	
	public int getPlayerRScore() {
		return playerRScore;
	}

	public boolean isPaused() {
		return paused;
	}
	
	public void setAIEnabled(boolean AIState) {
		this.AIState = AIState;
	}
	
	public boolean isAIEnabled() {
		return AIState;
	}
}
