package org.sevenhills.liueri19;

public class Table {
	public final int width, height;
	public double velocity = 1;	//in pixels/millisecond? IDK yet...
	//private List<Ball> balls = new ArrayList<Ball>();	may add multiple balls for difficulty (just a thought)
	private Ball ball;
	private int playerLScore = 0;
	private int playerRScore = 0;
	
	public Table() {
		this(800, 600);
		//balls.add(Ball.construct(this, velocity));
		ball = Ball.construct(this, velocity);
		//setup a 800*600 table with a "random" heading ball in the center.
	}
	
	public Table(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void resetBall() {
		//set to center
		ball.setCoordinate(width / 2, height / 2);
	}
	
	public void setBall(double direction) {
		
	}
	
	//accessors & mutators
	public void playerLScore() {
		playerLScore++;
	}
	
	public int getPlayerLScore() {
		return playerLScore;
	}
	
	public void playerRScore() {
		playerRScore++;
	}
	
	public int getPlayerRScore() {
		return playerRScore;
	}
}
