package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;

public class Entity {

	GamePanel gp;
	
	public int x,y;
	public int speed;
	
	public BufferedImage up1, down1, explode;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int worldX, worldY;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisonON = false;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
			
			int screenX = worldX;
			int screenY = worldY;
			g2.drawImage(up1, screenX, screenY, gp.panelSize, gp.panelSize, null);
		}

	//Character Status
	public int maxLife;
	public int life;
	//These two ints will be shared by player and monsters
}

