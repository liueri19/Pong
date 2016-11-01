package org.sevenhills.liueri19;

import javax.swing.SwingWorker;

public class GameClock extends SwingWorker<Void, Void> {
	private Table table;
	private final int interval;
	private boolean pause = false;
	
	public GameClock(Table table) {
		super();
		this.table = table;
		interval = table.gameClockInterval;
	}
	
	@Override
	protected Void doInBackground() {
		boolean proceed = true;
		while(proceed) {
			if (pause) {
				synchronized (table) {
					try { table.wait(); }
					catch (Exception e) {}
				}
				pause = false;
			}
			
			table.updateAll();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		return null;
	}
	
	public void pause() {
		pause = true;
	}
}
