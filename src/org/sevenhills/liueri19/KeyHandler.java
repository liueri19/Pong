package org.sevenhills.liueri19;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingWorker;

public class KeyHandler implements KeyListener {
	private boolean wKeyPressed = false,
					sKeyPressed = false,
					upKeyPressed = false,
					downKeyPressed = false,
					paused = false;	//TODO: unfixed: 'paused' flag is inconsistent with the actual state after the game end.
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
				while (true) {
					while (wKeyPressed && !paused) {
						table.getLeftPaddle().moveUp(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
		
		(new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				while (true) {
					while (sKeyPressed && !paused) {	//while S is held
						table.getLeftPaddle().moveDown(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
		
		(new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				while (true) {
					while (upKeyPressed && !paused) {
						table.getRightPaddle().moveUp(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
		
		(new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				while (true) {
					while (downKeyPressed && !paused) {
						table.getRightPaddle().moveDown(deltaY);
						try {
							Thread.sleep(16);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}).execute();
	}
	
	//key input handling not completed, paddles move twice as fast after holding key around 1 sec
	//key input treated as typing characters. seems keyPressed() is invoked twice
	@Override
	public void keyPressed(KeyEvent e) {
		//left side player
		if (e.getKeyCode() == KeyEvent.VK_W)
			wKeyPressed = true;
		
		else if (e.getKeyCode() == KeyEvent.VK_S)
			sKeyPressed = true;
		
		//right side player
		if (e.getKeyCode() == KeyEvent.VK_UP)
			upKeyPressed = true;
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			wKeyPressed = false;
		
		else if (e.getKeyCode() == KeyEvent.VK_S)
			sKeyPressed = false;
		
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			upKeyPressed = false;
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		if (keyChar == 'p' || keyChar == 'P') {
			synchronized (this) {
				if (paused)
					table.resumeGame();
				else
					table.pauseGame();
				paused = !paused;
			}
		}
	}
}
