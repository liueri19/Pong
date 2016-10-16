package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Table extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public final int width, height;
	public double velocity;
	private List<Ball> balls = new ArrayList<Ball>();	//may add multiple balls for difficulty
	
	public Table() {
		this(800, 600);
		balls.add(Ball.construct(this, velocity));
		//setup a 800*600 table with a random heading ball in the center.
	}
	
	public Table(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void resetBall() {
		
	}
	
	public void setBall(double direction) {
		
	}
}
