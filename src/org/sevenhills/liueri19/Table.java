package org.sevenhills.liueri19;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Table extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final Timer displayTimer = new Timer(17, this);	//approximately 60 fps
	public final int gameClockInterval = 16;
	private double ballVelocity = 3.5;	//unit: pixels / GameClock's Delay 
	public final double paddleVelocity = 2.5;
	public final int width, height;
	//private List<Ball> balls = new ArrayList<Ball>();	may add multiple balls for difficulty
	private Ball ball;
	public final int ballRadius = 10;
	private int playerLScore = 0;
	private int playerRScore = 0;
	private List<Paddle> paddles = new ArrayList<Paddle>(2);
	public final int paddleWidth = 20;
	public final int paddleHeight = 80;
	public final int paddleEdgeDistance = 30;
	
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
		this.addPaddle(new Paddle(this, paddleEdgeDistance));
		this.addPaddle(new Paddle(this, width - paddleEdgeDistance));
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
		(new GameClock(this)).execute();
	}
	
	public void updateAll() {
		ball.update();
		//paddles have nothing to update (surprisingly)
	}
	
	public void resetBall() {
		//construct new ball
		ball.reset();
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
		g.fillRect(paddleEdgeDistance, (int) paddles.get(0).getY(), paddleWidth, paddleHeight);
		//paint right paddle
		g.fillRect(width - paddleEdgeDistance - paddleWidth, (int) paddles.get(1).getY(), paddleWidth, paddleHeight);
		//paint scores
		int pointSize = 32;
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, pointSize));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(
				String.format("%02d : %02d", getPlayerLScore(), getPlayerRScore()),	// format string to "XX : XX"
				(int) (width / 2 - fm.charWidth('0') * 3.5), fm.getHeight());
	}
	
	//will be invoked by the timer with 20 milliseconds intervals
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
	
	//accessors & mutators
	public void addPaddle(Paddle paddle) {
		paddles.add(paddle);
	}
	
	public List<Paddle> getPaddles() {
		return paddles;
	}
	
	public Paddle getLeftPaddle() {
		return paddles.get(0);
	}
	
	public Paddle getRightPaddle() {
		return paddles.get(1);
	}
	
	public void playerLScore() {
		playerLScore++;
		resetBall();
	}
	
	public int getPlayerLScore() {
		return playerLScore;
	}
	
	public void playerRScore() {
		playerRScore++;
		resetBall();
	}
	
	public int getPlayerRScore() {
		return playerRScore;
	}
}
