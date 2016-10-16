package org.sevenhills.liueri19;

import java.util.Random;

public class Ball {
	private double[] coordinate;	//use array to store coordinate: {X, Y}
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
		
		boolean randBoolean = RANDOM.nextBoolean();
		double randDirection = RANDOM.nextDouble() * 90 + 45;
		if (randBoolean)
			direction = 180 - randDirection;
		else
			direction = 180 + randDirection;
		//generate random heading
		return new Ball(table, 
				new double[] {table.width / 2.0, table.height / 2.0}, 
				new double[] {magnitude, direction});
		//create new ball at center, with random heading
	}
	
	private Ball(Table table, double[] coordinate, double[] vector) {
		this.table = table;
		this.coordinate = coordinate;
		this.vector = vector;
	}
	
	public Ball(Table table, double[] coordinate, double magnitude, double direction) {
		this.table = table;
		this.coordinate = coordinate;
		vector[0] = magnitude;
		vector[1] = direction;
	}
	
	public void setDirection(double d) {
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
