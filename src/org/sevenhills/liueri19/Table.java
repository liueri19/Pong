package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Table {
	public final int width, height;
	public static final Random RAND = new Random();
	private List<Ball> balls = new ArrayList<Ball>();
	
	public Table() {
		this(800, 600);
	}
	
	public Table(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void replaceBall() {
		
	}
	
	public void setBall(double direction) {
		balls.add(new Ball(this, direction));
	}
}
