package org.sevenhills.liueri19;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingWorker;

public class KeyHandler implements KeyListener {
	private boolean wKeyPressed = false,
					sKeyPressed = false,
					upKeyPressed = false,
					downKeyPressed = false;
	private Table table;
	private double deltaY;
	
	public KeyHandler(Table table) {
		this.table = table;
		deltaY = table.paddleVelocity;
		startSwingWorkers();
	}
	
	private void startSwingWorkers() {
		(new SwingWorker<Void, Void>() {	//while W is held
			@Override
			protected Void doInBackground() {
				Paddle paddle = table.getLeftPaddle();
				while (true) {
					while (wKeyPressed && !table.isPaused()) {
						paddle.moveUp(deltaY);
						paddle.setMovingUp(true);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					paddle.setMovingUp(false);
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
		
		(new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				Paddle paddle = table.getLeftPaddle();
				while (true) {
					while (sKeyPressed && !table.isPaused()) {	//while S is held
						paddle.moveDown(deltaY);
						paddle.setMovingDown(true);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					paddle.setMovingDown(false);
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
		
		(new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				Paddle paddle = table.getRightPaddle();
				while (true) {
					while (upKeyPressed && !table.isPaused()) {
						paddle.moveUp(deltaY);
						paddle.setMovingUp(true);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					paddle.setMovingUp(false);
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
		
		(new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				Paddle paddle = table.getRightPaddle();
				while (true) {
					while (downKeyPressed && !table.isPaused()) {
						paddle.moveDown(deltaY);
						paddle.setMovingDown(true);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					paddle.setMovingDown(false);
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//left side player
		if (e.getKeyCode() == KeyEvent.VK_W && !table.isAIEnabled())
			wKeyPressed = true;
		
		else if (e.getKeyCode() == KeyEvent.VK_S && !table.isAIEnabled())
			sKeyPressed = true;
		
		//right side player
		if (e.getKeyCode() == KeyEvent.VK_UP)
			upKeyPressed = true;
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W && !table.isAIEnabled())
			wKeyPressed = false;
		
		else if (e.getKeyCode() == KeyEvent.VK_S && !table.isAIEnabled())
			sKeyPressed = false;
		
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			upKeyPressed = false;
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		if (keyChar == 'p' || keyChar == 'P') {	//pause/resume game
			if (table.isPaused())
				table.resumeGame();
			else
				table.pauseGame();
		}
		else if (keyChar == 'r' || keyChar == 'R') {	//restart game
			table.restart();
		}
		else if (keyChar == '1') {	//enable/disable AI
				table.setAIEnabled(!table.isAIEnabled());
		}
	}
}
