package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean up, down, left, right;
	
	
	@Override
	public void keyPressed(KeyEvent event) {
		
		int code = event.getKeyCode();
				
		if(code == KeyEvent.VK_W) {
			up = true;
		}
		if(code == KeyEvent.VK_A) {
			left = true;
		}
		if(code == KeyEvent.VK_S) {
			down = true;
		}
		if(code == KeyEvent.VK_D) {
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		
		int code = event.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			up = false;
		}
		if(code == KeyEvent.VK_A) {
			left = false;
		}
		if(code == KeyEvent.VK_S) {
			down = false;
		}
		if(code == KeyEvent.VK_D) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
