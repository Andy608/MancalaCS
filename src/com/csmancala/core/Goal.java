package com.csmancala.core;

import java.util.ArrayList;

public class Goal extends Pit {
	private static final long serialVersionUID = 7482055767542079222L;

	public Goal() {
		stones = new ArrayList<>(Board.STONE_AMOUNT / 2);
	}
}
