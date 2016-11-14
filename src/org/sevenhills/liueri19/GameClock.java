package org.sevenhills.liueri19;

import javax.swing.Timer;

public class GameClock extends Timer {
	private Table table;
	
	public GameClock(Table table) {
		super(table.gameClockInterval, table);
		this.table = table;
	}
}
