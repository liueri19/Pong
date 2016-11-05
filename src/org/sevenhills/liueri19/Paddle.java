package org.sevenhills.liueri19;

public class Paddle {
	private double yLoc;
	private final double xLoc;
	private final Table table;
	private boolean isMovingUp, isMovingDown;
	
	public Paddle(Table table, double x) {
		this(table, x, table.height / 2);
	}
	
	public Paddle(Table table, double x, double y) {
		this.table = table;
		xLoc = x;
		yLoc = y;
	}
	
	//AI: follow the ball's position
	public void updatePaddle() {
		double ballY = table.getBall().getY();
		if (getY() + table.paddleHeight / 2 > ballY)
			moveUp(table.paddleVelocity);
		else if (getY()  + table.paddleHeight / 2 < ballY)
			moveDown(table.paddleVelocity);
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
	
	public void moveUp(double deltaY) {
		if (getY() > 0)
			yLoc -= deltaY;
	}
	
	public void moveDown(double deltaY) {
		if (getY() + table.paddleHeight < table.height)
			yLoc += deltaY;
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
}
