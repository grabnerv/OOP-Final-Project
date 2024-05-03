package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import com.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	final int OGPanelSize = 16;
	final int scale = 3;
	public final int panelSize = OGPanelSize * scale;
	
	public final int screenColumns = 12;
	public final int screenRows = 16;
	public final int screenWidth = panelSize * screenColumns; // 576 pixels
	public final int screenHeight = panelSize * screenRows; // 768 pixels
	
	Thread gameThread;
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	public Player player = new Player(this,keyH);
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	int FPS = 60;
	public SuperObject obj[] = new SuperObject[10]; // can display up to 10 objects at same time, might change
	List<Button> buttons = new ArrayList<>();
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
		buttons.add(new Button(100, 100, 200, 50, "Start Game"));
        buttons.add(new Button(100, 200, 200, 50, "Exit Game"));

		addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int mx = e.getX();
                int my = e.getY();
                for (Button button : buttons) {
                    if (button.isMouseOver(mx, my)) {
                        if (button.label.equals("Start Game")) {
                            gameState = playState;
                        } else if (button.label.equals("Exit Game")) {
                            System.exit(0); // Exit the game
                        }
                    }
                }
            }
		});

		}
	

	public void setupGame() {
		aSetter.setObject();
		gameState = titleState; //this is what starts the game now
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void StartGameThread() {
		gameState = playState; // this will need to be reworked or removed.
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

		if(gameState == playState) {
           player.update();
		}
		 if(gameState == pauseState) {

		 }
	}
	
	public void paintComponent(Graphics g) {	
		
			super.paintComponent(g);
			Graphics2D graphics = (Graphics2D)g;
			// tiles
			tileM.draw(graphics);
			
			//object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(graphics, this);
				}
			}
			
			//player
			player.draw(graphics);
		
			if (gameState == titleState) {
				for(Button button : buttons) {
					button.draw(graphics);
				}
			}
		
		}
		
		
	}

