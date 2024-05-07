package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Handles associating keyboard button with in-game functionality
 */
public class KeyHandler implements KeyListener {
    GamePanel gp;
	public boolean up, down, left, right, space;
	
	/**
	 * Initiates a new KeyHandler
	 * @param gp current instance of game
	 */
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Detects when a key is presses
	 * @param event key being pressed
	 */
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
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
		if(code == KeyEvent.VK_SPACE) {
			space = true;
		}
	}
	
	/**
	 * Detects when a key is released
	 * @param event key being pressed
	 */
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
		if(code == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

	/**
	 * Unused, but necessary for KeyHandler
	 */
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
