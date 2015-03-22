package com.csmancala.core;

import java.util.ArrayList;

public class Goal extends Slot {
	private static final long serialVersionUID = 7482055767542079222L;

	public Goal() {
		stones = new ArrayList<>(Board.STONE_AMOUNT / 2);
	}
	
	@Override
	public String toString() {
		return "Stones in Goal: " + stones.size();
	}
}
