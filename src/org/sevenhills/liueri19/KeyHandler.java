package org.sevenhills.liueri19;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	private Table table;
	private Paddle leftPaddle;
	
	public KeyHandler(Table table) {
		this.table = table;
		leftPaddle = table.getLeftPaddle();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//left side player
		if (e.getKeyCode() == KeyEvent.VK_W && !leftPaddle.isAIEnabled())
			table.getLeftPaddle().setMovingUp(true);
		
		else if (e.getKeyCode() == KeyEvent.VK_S && !leftPaddle.isAIEnabled())
			table.getLeftPaddle().setMovingDown(true);
		
		//right side player
		if (e.getKeyCode() == KeyEvent.VK_UP)
			table.getRightPaddle().setMovingUp(true);
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			table.getRightPaddle().setMovingDown(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W && !leftPaddle.isAIEnabled())
			table.getLeftPaddle().setMovingUp(false);
		
		else if (e.getKeyCode() == KeyEvent.VK_S && !leftPaddle.isAIEnabled())
			table.getLeftPaddle().setMovingDown(false);
		
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			table.getRightPaddle().setMovingUp(false);
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			table.getRightPaddle().setMovingDown(false);
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
			leftPaddle.setAIEnabled(!leftPaddle.isAIEnabled());
		}
	}
}
