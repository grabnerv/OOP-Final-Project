package com.oop_final_project.ekin_fanclub_galaga;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.junit.jupiter.api.Test;

import entity.Player;
import entity.PlayerTest;

public class GamePanelTest extends GamePanel {


	public GamePanelTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void defaultValuesTest() {
		
		assertEquals(this.getPreferredSize(),new Dimension(screenWidth, screenHeight));
		assertEquals(this.getBackground(),Color.black);
	}
	
	public void mouseClickedTest() {
		//MOUSE_CLICKED,(287,329),absolute(1063,405),button=1,modifiers=Button1,clickCount=1
		
		this.addMouseListener(null);
	}
	@Test
	public void setupTest() {
		this.setupGame();
		assertEquals(gameState,titleState);
	}
	@Test
	public void setActionTest() {
		actionTimer = 599;
		direction = "left";
		this.setAction();
		assertEquals(direction,"right");
		assertEquals(actionTimer,0);
		actionTimer = 599;
		direction = "right";
		this.setAction();
		assertEquals(direction,"left");
		
	}
}
