package org.sevenhills.liueri19;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingWorker;

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
	
	public void moveUp(double deltaY) {
		yLoc -= deltaY;
	}
	
	public void moveDown(double deltaY) {
		yLoc += deltaY;
	}
}


class KeyHandler implements KeyListener {
	private boolean wKeyReleased,
					sKeyReleased,
					upKeyReleased,
					downKeyReleased;
	private Table table;
	private double deltaY;
	
	public KeyHandler(Table table) {
		this.table = table;
		deltaY = table.paddleVelocity;
	}
	
	//key input handling not completed, paddles move twice as fast after holding key around 1 sec
	@Override
	public void keyPressed(KeyEvent e) {
		//left side player
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wKeyReleased = false;
			(new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
					do {
						table.getLeftPaddle().moveUp(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					while (!wKeyReleased);	//while W is held
					return null;
				}
			}).execute();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			sKeyReleased = false;
			(new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
					do {
						table.getLeftPaddle().moveDown(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					while (!sKeyReleased);	//while S is held
					return null;
				}
			}).execute();
		}
		
		//right side player
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upKeyReleased = false;
			(new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
					do {
						table.getRightPaddle().moveUp(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					while (!upKeyReleased);
					return null;
				}
			}).execute();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downKeyReleased = false;
			(new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
					do {
						table.getRightPaddle().moveDown(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					while (!downKeyReleased);
					return null;
				}
			}).execute();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			wKeyReleased = true;
		else if (e.getKeyCode() == KeyEvent.VK_S)
			sKeyReleased = true;
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			upKeyReleased = true;
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyReleased = true;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
