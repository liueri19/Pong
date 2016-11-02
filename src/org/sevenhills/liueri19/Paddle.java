package org.sevenhills.liueri19;

public class Paddle {
	private double yLoc;
	private final double xLoc;
	private final Table table;
	
	public Paddle(Table table, double x) {
		this(table, x, table.height / 2);
	}
	
	public Paddle(Table table, double x, double y) {
		this.table = table;
		xLoc = x;
		yLoc = y;
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
		if (!(getY() <= 0))
			yLoc -= deltaY;
	}
	
	public void moveDown(double deltaY) {
		if (!(getY() + table.paddleHeight >= table.height))
			yLoc += deltaY;
	}
}
