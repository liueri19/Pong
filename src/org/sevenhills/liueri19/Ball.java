package org.sevenhills.liueri19;

import java.util.Random;

public class Ball {
	private double[] coordinate;	//use array to store coordinate: {X, Y} units are pixels
	private double[] vector;	//use array to store vector: {magnitude, direction}
	/*
	 * direction, in degrees, the ball is going. 0 degrees is straight up.
	 * magnitude of the vector should not change once ball constructed.
	 */
	private Table table;
	private static final Random RANDOM = new Random();
	
	public static Ball construct(Table table, double velocity) {
		double magnitude, direction;
		magnitude = velocity;
		
		//generate random heading
		boolean randBoolean = RANDOM.nextBoolean();
		double randDirection = RANDOM.nextDouble() * 90 + 45;
		if (randBoolean)
			direction = 180 - randDirection;
		else
			direction = 180 + randDirection;
		
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
	
	public void update() {
		double deltaX, deltaY, angle, magnitude;
		angle = getDirection();
		magnitude = getMagnitude();
		//check collision, change direction if needed
		if (coordinate[0] <= 0) {	//reaching the left edge of the table
			table.playerRScore();
		}
		else if (coordinate[0] >= table.width) {	//reaching the right edge of the table
			table.playerLScore();
		}
		if (coordinate[1] <= 0 || coordinate[1] >= table.height) {	//reaching upper or lower bounds
			setDirection(180 + parseDirection(-getDirection()));
		}
		//update coordinate
		deltaX = Math.sin(angle) * magnitude;
		deltaY = Math.cos(angle) * magnitude;
		coordinate[0] += deltaX;
		coordinate[1] += deltaY;
	}
	
	//to make sure direction is in the range 0 inclusive to 360 exclusive.
	public double parseDirection(double d) {
		if (d < 0)
			d = 360 - (d % 360);
		if (d >= 360)	//else if is not used because 360 - (d % 360) could result 360.
			d = d % 360;
		return d;
	}
	
	//accessors and mutators 
	public double getX() {
		return coordinate[0];
	}
	
	/**Note that this method does not check for invalid arguments.*/
	public void setX(double x) {
		coordinate[0] = x;
	}
	
	public double getY() {
		return coordinate[1];
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
