package com.oop_final_project.ekin_fanclub_galaga;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import com.Button;
import com.oop_final_project.ColorButton;

import enemy.HordeEnemy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entity.Entity;
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
	public Player player = new Player(this, keyH);
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public UI ui = new UI(this);
	public String direction;
	public int actionTimer = 0;
	
	int FPS = 60;
	public SuperObject obj[] = new SuperObject[10]; // can display up to 10 objects at same time, might change
	List<Button> buttons = new ArrayList<>();
	List<ColorButton> colorButtons = new ArrayList<>();
	Color selectedColor = Color.WHITE;


	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int customState = 3;
	
	public HordeEnemy enemy[] = new HordeEnemy[5];
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
		buttons.add(new Button(200, 300, 200, 50, "Start Game"));
        buttons.add(new Button(200, 400, 200, 50, "Exit Game"));
		buttons.add(new Button(200, 500, 200, 50, "Colorcustom"  ));
		colorButtons.add(new ColorButton(100, 100, 50, 50, "Yellow", Color.YELLOW));
		colorButtons.add(new ColorButton(100, 160, 50, 50, "Blue", Color.BLUE));
		colorButtons.add(new ColorButton(100, 220, 50, 50, "Green", Color.GREEN));
		colorButtons.add(new ColorButton(100, 280, 50, 50, "Barbie", Color.PINK));
		colorButtons.add(new ColorButton(100, 340, 50, 50, "Orange", Color.ORANGE));
	
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
                        } if (button.label.equals("Colorcustom")) {
							gameState = customState;
							
						}
						 else if (button.label.equals("Exit Game") && (gameState == titleState || gameState == pauseState) ) {
                            System.exit(0); // Exit the game
                        }
						 
					
                    }
                }
				for(ColorButton button: colorButtons) {
					if (button.isMouseOver(mx, my) && gameState == customState) {
						BufferedImage newShipImage = colorizeShip(player.up1, button.getColor());
						player.up1 = newShipImage;
						player.down1 = newShipImage;
						player.explode = newShipImage;
						gameState = titleState;
						
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
		aSetter.setEnemy();
		
	}
	
	public void StartGameThread() { //This might need to be deleted, unused
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
	}
	
	public void paintComponent(Graphics g) {	
		
			super.paintComponent(g);
			Graphics2D graphics = (Graphics2D)g;
			if (gameState == playState) {
				// tiles
				tileM.draw(graphics);
				//object
				for(int i = 0; i < obj.length; i++) {
					if(obj[i] != null) {
						obj[i].draw(graphics, this);
					}
				}
				
				for(int i = 0; i < enemy.length; i ++) {
					if(enemy[i] != null) {
						enemy[i].draw(graphics, this);
						
						String currentDirection = setAction();
						
						if(currentDirection == "right") {
							enemy[i].worldX --;
							
						} else if(currentDirection == "left") {
							enemy[i].worldX ++;
							
						}
						
						if(gameState == pauseState) {
							currentDirection = "pause";
							enemy[i].worldX += 0;
						}
					}
				}
			}
				
				//player
				player.draw(graphics);
			
			//ui

				if (gameState == titleState || gameState == pauseState) {

					ui.draw(graphics);
				}		
				if (gameState == customState) {
					System.out.println("Should be drawing buttons");
					for (ColorButton button : colorButtons) {
						button.draw(graphics);
				}
			
			}
	}

	
	public String setAction() {
		
		actionTimer ++;
		
		if(actionTimer == 600 || direction == null) {
			
			if(direction == "right") {
				direction = "left";
				actionTimer = 0;
				
			} else if (direction == "left" || direction == null){
				direction = "right";
				actionTimer = 0;
			} 
		}
		
		return direction;
		
	}


	public BufferedImage colorizeShip(BufferedImage originalImage, Color newColor) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		BufferedImage coloredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = coloredImage.createGraphics();
		
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.setComposite(AlphaComposite.SrcAtop);
		g2d.setColor(newColor);
		g2d.fillRect(0, 0, width, height);
		g2d.dispose();
		
		return coloredImage;
	}
		
}
