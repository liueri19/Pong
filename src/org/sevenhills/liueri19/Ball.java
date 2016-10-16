package org.sevenhills.liueri19;

import java.util.Random;

public class Ball {
	private double[] coordinate;	//use array to store coordinate: {X, Y}
	private double[] vector;	//use array to store vector: {magnitude, direction}
	/*
	 * direction, in degrees, the ball is going. 0 degrees is straight up.
	 * magnitude of the vector should never change.
	 */
	private Table table;
	private Random random;
	
	public static Ball constructBall(Table table) {
		double magnitude, direction;
		magnitude = 1.0;
		boolean randBoolean = table.RAND.nextBoolean();
		double randDirection = table.RAND
		return new Ball(table, new double[] {table.width / 2.0, table.height / 2.0});
		//create new ball at center, with heading 0
	}
	
	private Ball(Table table, double[] coordinate) {
		this.coordinate = coordinate;
		this.table = table;
	}
	
	public Ball(Table table, double[] coordinate, double magnitude, double direction) {
		this.coordinate = coordinate;
		vector[0] = magnitude;
		vector[1] = direction;
		this.table = table;
		random = table.RAND;
	}
	
	//we may never want the direction to be set outside this class
	private void setDirection(double d) {
		if (d < 0)
			d = 360 - (d % 360);
		if (d >= 360)
			d = d % 360;
		//to make sure direction is in the range 0 inclusive to 360 exclusive.
		//else if is not used because 360 - (d % 360) could result 360.
		vector[0] = d;
	}
	
	public double getMagnitude() {
		return vector[0];
	}
	
	public double getDirection() {
		return vector[1];
	}
	
	public double[] getVector() {
		return vector;
	}
	
	public void update() {
		
		//check collision, change direction if needed
		
		//update coordinate
	}
}
