package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	final int OGPanelSize = 16;
	final int scale = 3;
	public final int panelSize = OGPanelSize * scale;
	
	final int screenColumns = 12;
	final int screenRows = 16;
	final int screenWidth = panelSize * screenColumns; // 576 pixels
	final int screenHeight = panelSize * screenRows; // 768 pixels
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	Player player = new Player(this,keyH);
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 5;
	int FPS = 60;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
	}
	
	public void StartGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {	
		
		double interval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + interval;
		
		while(gameThread != null) {
			
			update();
			repaint();
			
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000; // <---Thread.sleep() requires time in milliseconds
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += interval;
				
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public void update() {
		 player.update();
	}
	
	public void paintComponent(Graphics g) {	
		
		super.paintComponent(g);
		
		Graphics2D graphics = (Graphics2D)g;
		
		player.draw(graphics);
		
		graphics.dispose();
		
		
	}
	
	
	
	
}