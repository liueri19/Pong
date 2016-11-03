package org.sevenhills.liueri19;

import java.util.Random;

public class Ball {
	private double[] coordinate = new double[2];	//use array to store coordinate: {X, Y} units are pixels
	private double[] vector = new double[2];	//use array to store vector: {magnitude, direction}
	private final double initMagnitude;
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
		initMagnitude = magnitude;
	}
	
	public void update() {
		double deltaX, deltaY, angle, magnitude;
		if (getLeftEdge() <= 0) {	//reaching the left edge of the table
			table.playerRScore();
		}
		else if (getRightEdge() >= table.width) {	//reaching the right edge of the table
			table.playerLScore();
		}
		
		//collision detection
		if (getUpperEdge() <= 0 || getLowerEdge() >= table.height) {	//upper & lower bounds
			bounceVertical();
		}

		//collision detection for paddles
		Paddle leftPaddle = table.getLeftPaddle();
		Paddle rightPaddle = table.getRightPaddle();
		if (((getLeftEdge() <= (leftPaddle.getX() + table.paddleWidth))
				&& (getLowerEdge() >= leftPaddle.getY())
				&& (getUpperEdge() <= leftPaddle.getY() + table.paddleHeight)
				&& (getLeftEdge() > leftPaddle.getX()))
				|| ((getRightEdge() >= rightPaddle.getX())
				&& (getLowerEdge() >= rightPaddle.getY())
				&& (getUpperEdge() <= rightPaddle.getY() + table.paddleHeight)
				&& (getRightEdge() < rightPaddle.getX() + table.paddleWidth))) {
			if (getLeftEdge() <= (leftPaddle.getX() + table.paddleWidth - table.ballRadius)
					|| getRightEdge() >= (rightPaddle.getX() + table.ballRadius))
				bounceVertical();
			else
				bounceHorizontal();
		}
		
		//update coordinate
		angle = getDirection();
		magnitude = getMagnitude();
		deltaX = Math.cos(Math.toRadians(angle)) * magnitude;
		deltaY = Math.sin(Math.toRadians(angle)) * magnitude;
		coordinate[0] += deltaX;
		coordinate[1] -= deltaY;
		
		//print some logs
/*		System.out.printf("Ball Coordinate: %.5f, %.5f; Vector: %f, %.5f;%nDetails: angle = %f, magnitude = %.5f, deltaX = %.5f, deltaY = %.5f;%n%n", 
				getX(), getY(), getMagnitude(), getDirection(), angle, magnitude, deltaX, deltaY);*/
		///
	}
	
	private void bounceVertical() {
		setDirection(-getDirection());
	}
	
	//known bugs exist
	private void bounceHorizontal() {
		Paddle paddle;
		//figure out which paddle is the ball bouncing off
		if (getX() < table.width / 2)
			paddle = table.getLeftPaddle();
		paddle = table.getRightPaddle();
		//bounce the ball
		setDirection(180 - getDirection());
		//if the paddle is moving
		if (paddle.isMovingUp() && !paddle.isMovingDown()) {
			double magnitude = getMagnitude();
			double direction = getDirection();
			//newVelocity = sqrt( (cos(angle)*v)^2 + (sin(a)*v)^2 )
			setMagnitude(Math.sqrt(Math.pow(Math.tan(direction)*magnitude, 2) + Math.pow(Math.sin(direction)*magnitude + table.paddleDisplacement, 2)));
			//newAngle = arctan( sin(angle) / cos(angle) )
			setDirection(Math.atan(Math.sin(direction) / Math.cos(direction)));
		}
		else if (paddle.isMovingDown() && !paddle.isMovingUp()) {
			double magnitude = getMagnitude();
			double direction = getDirection();
			setMagnitude(Math.sqrt(Math.pow(Math.tan(direction)*magnitude, 2) + Math.pow(Math.sin(direction)*magnitude - table.paddleDisplacement, 2)));
			setDirection(Math.atan(Math.sin(direction) / Math.cos(direction)));
		}
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
		setMagnitude(initMagnitude);
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
