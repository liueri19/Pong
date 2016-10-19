package org.sevenhills.liueri19;

import javax.swing.SwingWorker;

public class GameClock extends SwingWorker<Void, Void> {
	private Table table;
	
	@Override
	protected Void doInBackground() {
		boolean proceed = true;
		while(proceed) {
			table.updateAll();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public GameClock(Table table) {
		super();
		this.table = table;
	}
}
