package org.sevenhills.liueri19;

import java.util.Random;

public class Ball {
	private double[] coordinate = new double[2];	//use array to store coordinate: {X, Y} units are pixels
	private double[] vector = new double[2];	//use array to store vector: {magnitude, direction}
	/*
	 * direction, in degrees, the ball is going. 0 degrees is horizontal right.
	 * magnitude of the vector should not change once ball constructed.
	 */
	private Table table;
	private static final Random RANDOM = new Random();
	
	public static Ball construct(Table table, double velocity) {
		double magnitude, direction;
		magnitude = velocity;
		//generate random heading
		direction = randomDirection();
		
		//create new ball at center, with random heading
		return new Ball(table,
				new double[] {table.width / 2.0, table.height / 2.0}, 
				magnitude, direction);
	}
	
	public Ball(Table table, double[] coordinate, double magnitude, double direction) {
		this.table = table;
		this.coordinate = coordinate;
		vector[0] = magnitude;
		vector[1] = direction;
	}
	
	//does not bounce properly, unknown reason
	public void update() {
		double deltaX, deltaY, angle, magnitude;
		//check collision, change direction if needed
		if (getLeftEdge() <= 0) {	//reaching the left edge of the table
			table.playerRScore();
		}
		else if (getRightEdge() >= table.width) {	//reaching the right edge of the table
			table.playerLScore();
		}
		if (getUpperEdge() < 0) {
			setDirection(-getDirection());
			setY(table.ballRadius);
		}
		else if (getLowerEdge() > table.height) {
			setDirection(-getDirection());
			setY(table.height - table.ballRadius);
		}
		
		//update coordinate
		angle = getDirection();
		magnitude = getMagnitude();
		deltaX = Math.cos(Math.toRadians(angle)) * magnitude;	//didn't realize angle was measured in radians
		deltaY = Math.sin(Math.toRadians(angle)) * magnitude;
		coordinate[0] += deltaX;
		coordinate[1] -= deltaY;
		
		//print some logs
/*		System.out.printf("Ball Coordinate: %.5f, %.5f; Vector: %f, %.5f;%nDetails: angle = %f, magnitude = %.5f, deltaX = %.5f, deltaY = %.5f;%n%n", 
				getX(), getY(), getMagnitude(), getDirection(), angle, magnitude, deltaX, deltaY);*/
		///
	}
	
	//to make sure direction is in the range 0 inclusive to 360 exclusive.
	public static double parseDirection(double d) {
		if (d < 0)
			d = 360 + (d % 360);
		if (d >= 360)	//else if is not used because 360 - (d % 360) could result 360.
			d = d % 360;
		return d;
	}
	
	public static double randomDirection() {
		double randDirection = RANDOM.nextDouble() * 90 - 45;
		if (RANDOM.nextBoolean())
			return parseDirection(randDirection);
		return 180 - randDirection;
	}
	
	public void reset() {
		setX(table.width / 2.0);
		setY(table.height / 2.0);
		setDirection(randomDirection());
	}
	
	//accessors and mutators 
	public double getX() {
		return coordinate[0];
	}
	
	public double getLeftEdge() {
		return getX() - table.ballRadius;
	}
	
	public double getRightEdge() {
		return getX() + table.ballRadius;
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setX(double x) {
		coordinate[0] = x;
	}
	
	public double getY() {
		return coordinate[1];
	}
	
	public double getUpperEdge() {
		return getY() - table.ballRadius;
	}
	
	public double getLowerEdge() {
		return getY() + table.ballRadius;
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setY(double y) {
		coordinate[1] = y;
	}
	
	public double[] getCoordinate() {
		return coordinate;
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setCoordinate(double[] coordinate) {
		this.coordinate = coordinate;
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setCoordinate(double x, double y) {
		this.coordinate = new double[] {x, y};
	}
	
	public double getDirection() {
		return vector[1];
	}
	
	/**Negative value for parameter d is allowed.*/
	public void setDirection(double d) {
		vector[1] = parseDirection(d);
	}
	
	public double getMagnitude() {
		return vector[0];
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setMagnitude(double magnitude) {
		vector[0] = magnitude;
	}
	
	public double[] getVector() {
		return vector;
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setVector(double[] vector) {
		this.vector = vector;
	}
}
