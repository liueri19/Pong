package org.sevenhills.liueri19;

public class Paddle {
	private double yLoc;
	private final double xLoc;
	private final Table table;
	private final double paddleVelocity;
	private boolean isMovingUp, isMovingDown;
	private boolean isAIEnabled;
	
	public Paddle(Table table, double x, boolean AIState) {
		this(table, x, table.height / 2, AIState);
	}
	
	public Paddle(Table table, double x, double y, boolean AIState) {
		this.table = table;
		paddleVelocity = table.paddleVelocity;
		xLoc = x;
		yLoc = y;
		this.isAIEnabled = AIState;
	}
	
	//AI: follow the ball's position
	public void update() {
		if (isAIEnabled) {
			double ballY = table.getBall().getY();
			if (getY() + table.paddleHeight / 2 > ballY)
				moveUp();
			else if (getY()  + table.paddleHeight / 2 < ballY)
				moveDown();
		}
		else {
			if (isMovingUp && !isMovingDown)
				moveUp();
			else if (isMovingDown && !isMovingUp)
				moveDown();
		}
	}
	
	@Override
	public String toString() {
		if (xLoc < table.width / 2)
			return "LeftPaddle";
		return "RightPaddle";
	}
	
	//accessors and mutators
	public double getX() {
		return xLoc;
	}
	
	public double getY() {
		return yLoc;
	}
	
	public void setY(double y) {
		yLoc = y;
	}
	
	public void moveUp() {
		if (getY() > 0)
			yLoc -= paddleVelocity;
	}
	
	public void moveDown() {
		if (getY() + table.paddleHeight < table.height)
			yLoc += paddleVelocity;
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}
	
	public boolean isAIEnabled() {
		return isAIEnabled;
	}
	
	public void setAIEnabled(boolean AIState) {
		isAIEnabled = AIState;
	}
}
