package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	final int OGPanelSize = 16;
	final int scale = 3;
	final int panelSize = OGPanelSize * scale;
	
	final int screenColumns = 12;
	final int screenRows = 16;
	final int screenWidth = panelSize * screenColumns; // 576 pixels
	final int screenHeight = panelSize * screenRows; // 768 pixels
	
	Thread gameThread;
	
	
	public GamePanel () {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
	}
	
	public void GameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
