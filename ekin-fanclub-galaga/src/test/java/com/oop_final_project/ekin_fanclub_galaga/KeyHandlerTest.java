package com.oop_final_project.ekin_fanclub_galaga;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Button;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;


class KeyHandlerTest extends KeyHandler{
	private static GamePanel gp = new GamePanel();
	
	public KeyHandlerTest() {
		super(gp);
	}
	
	@Test
	public void testKeyPressed() {
		Button button1 = new Button("click");
	    KeyEvent w = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_W, 'W');
		this.keyPressed(w);
		assertEquals(up,true);
	    KeyEvent s = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_S, 'S');
		this.keyPressed(s);
		assertEquals(down,true);
	    KeyEvent a = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_A, 'A');
		this.keyPressed(a);
		assertEquals(left,true);
	    KeyEvent d = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_D, 'D');
		this.keyPressed(d);
		assertEquals(right,true);
		gp.gameState = gp.playState;
		KeyEvent p = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_P, 'P');
		this.keyPressed(p);
		assertEquals(gp.gameState,gp.pauseState);
		KeyEvent p2 = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_P, 'P');
		this.keyPressed(p2);
		assertEquals(gp.gameState,gp.playState);
	}
	
	@Test
	public void testKeyReleased() {
		Button button1 = new Button("click");
		KeyEvent w = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_W, 'W');
		this.keyReleased(w);
		assertEquals(up,false);
	    KeyEvent s = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_S, 'S');
		this.keyReleased(s);
		assertEquals(down,false);
	    KeyEvent a = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_A, 'A');
		this.keyReleased(a);
		assertEquals(left,false);
	    KeyEvent d = new KeyEvent(button1, 1, 20, 87, KeyEvent.VK_D, 'D');
		this.keyReleased(d);
		assertEquals(right,false);
	}
	

}
