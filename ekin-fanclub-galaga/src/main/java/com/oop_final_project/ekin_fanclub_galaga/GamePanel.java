package com.oop_final_project.ekin_fanclub_galaga;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
import projectile.Projectile;
import tile.TileManager;

/**
 * Game panel uses JPanel and implements Runnable
 * Very large class, used for rendering the game and updating everything each frame.
 * Also handles setting the game menu.
 */
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
	
	public HordeEnemy hordeEnemyArray[] = new HordeEnemy[5];
	public Projectile projectileArray[] = new Projectile[10000000];
	
	
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
	
	/**
	 * Setup game starts the game thread and called aSetter.java to set objects and enemies
	 * into their respective arrays.
	 */
	public void setupGame() {
		aSetter.setObject();
		gameState = titleState; //this is what starts the game now
		gameThread = new Thread(this);
		gameThread.start();
		aSetter.setHordeEnemy();

	}
	
	public void StartGameThread() { //This might need to be deleted, unused
		gameState = playState; // this will need to be reworked or removed.
		gameThread = new Thread(this);
		gameThread.start(); 
	}

	/**
	 * Method that handles frame rendering
	 */
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
	

	/**
	 * Checks for collisions and that the game is running.
	 */
	public void update() {

		if(gameState == playState) {
           player.update();
           checkMonsterCollision(player);
		}
		 if(gameState == pauseState) {

		 }
	}
	
	/**
	 * Function that handles the rendering of all enemy, projectile, and player movements,
	 * as well as pausing the game when P is clicked.
	 */
	public void paintComponent(Graphics g) {	
		
			super.paintComponent(g);
			Graphics2D graphics = (Graphics2D)g;
			if (gameState == playState || gameState == pauseState) {
				// tiles
				tileM.draw(graphics);
				//object
				for(int i = 0; i < obj.length; i++) {
					if(obj[i] != null) {
						obj[i].draw(graphics, this);
					}
				}
				
				for(int i = 0; i < projectileArray.length; i++ ) {
					if(projectileArray[i] != null) {
						if(i % 15 == 0) {
							projectileArray[i].draw(graphics, this);
							projectileArray[i].worldY -= 10;
						}			
					}
				}
				
				for(int i = 0; i < hordeEnemyArray.length; i ++) {
					if(hordeEnemyArray[i] != null) {
						hordeEnemyArray[i].draw(graphics, this);
						
						String currentDirection = setAction();
						
						
						if(currentDirection == "right") {
							hordeEnemyArray[i].worldX --;
							
						} else if(currentDirection == "left") {
							hordeEnemyArray[i].worldX ++;
							
						}
						
						if(gameState == pauseState) {
							currentDirection = "pause";
							hordeEnemyArray[i].worldX += 0;
						}
					}

				}
				
				if(keyH.space) {
					aSetter.setProjectile();
				}
				
			}
				
				//player
				player.draw(graphics);
			
			//ui

			if (gameState == titleState || gameState == pauseState || gameState == playState) {
				ui.draw(graphics);					
			} 
			if (gameState == customState) {
				for (ColorButton button : colorButtons) {
					button.draw(graphics);
			}
				
		}
	}
	
	/**
	 * Sets the direction of enemy movement based off of their X location.
	 */
	public String setAction() {
			
			if(hordeEnemyArray[0].worldX == 60) {
				direction = "left";
				
			} else if (hordeEnemyArray[(hordeEnemyArray.length) - 1].worldX == 500 || direction == null){
				direction = "right";

			}

		return direction;
		
	}

	/**
	 * Changes the color of the ship
	 * @param originalImage original look of ship
	 * @param newColor color to be set on the ship
	 */
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
	
	/**
	 * Checks for enemy collisions with player
	 * @param player current instance of player ship
	 */
	public boolean checkMonsterCollision(Player player) {
		Rectangle playerBounds = new Rectangle(player.x, player.y, player.solidArea.width, player.solidArea.height);
        for (int i = 0; i < hordeEnemyArray.length; i++) {
            if (hordeEnemyArray[i] != null && playerBounds.intersects(hordeEnemyArray[i].solidArea)) {
            	System.out.println("true");
            	player.takeDamage(1);
            	return true; //Collision detected
            }
        }
        return false;
	}
}
