package com.csmancala.util;

import javax.swing.JButton;

public class MancalaButton extends JButton {
	
	private static final long serialVersionUID = 7085435238719162034L;
	
	private boolean isHovered;
	
	public MancalaButton(String title) {
		super(title);
	}
	
	public MancalaButton() {
		super();
	}
	
	public void setHovered(boolean b) {
		isHovered = b;
	}
	
	public boolean isHovered() {
		return isHovered;
	}
}
