package org.sevenhills.liueri19;

import javax.swing.SwingWorker;

public class GameClock extends SwingWorker<Void, Void> {
	private Table table;
	private final int interval;
	
	@Override
	protected Void doInBackground() {
		boolean proceed = true;
		while(proceed) {
			table.updateAll();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		return null;
	}
	
	public GameClock(Table table) {
		super();
		this.table = table;
		interval = table.gameClockInterval;
	}
}
