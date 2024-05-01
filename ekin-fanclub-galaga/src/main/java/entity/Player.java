package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import com.oop_final_project.ekin_fanclub_galaga.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
		
        solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 16;
		solidArea.width = 36;
		solidArea.height = 36; //none of these values are final and should be tweaked in debugging

		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/galaga_v2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/galaga_v2.png")); //placeholder until more fitting sprite is made
			explode = ImageIO.read(getClass().getResourceAsStream("/player/galaga_v2.png")); //placeholder until more fitting sprite is made
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
	
		if(keyH.up) {
			direction = "up";
		} 
		else if(keyH.down) {
			direction = "down";
		}
		else if(keyH.left) {
			direction = "left";
		}
		else if(keyH.right) {
			direction = "right";
		} else {
			direction = "";
		}
		
        collisonON = false;
		gp.cChecker.checkTile(this);

		if(collisonON == false) {
			switch(direction) {
				case"up":
				y -= speed;
					break;
				case "down":
				y += speed;
					break;
				case "left":
				x -= speed;
					break;
				case "right":
				x += speed;
					break;
			}
		}

		spriteCounter++;
		if(spriteCounter>10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum ==2) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}
	public void draw(Graphics2D graphics) {
//		graphics.setColor(Color.white);
//		graphics.fillRect(x, y, gp.panelSize, gp.panelSize);
		
		BufferedImage image = null;
		if (direction == "up") {
			if(spriteNum == 1) { // for animating movement and direction this stuff is all temporary and should be changed
				image = up1;
			}
			if(spriteNum == 2) {
				image=down1;
			}
		} else if (direction == "down") {
			if(spriteNum == 1) { // for animating movement and direction this stuff is all temporary and should be changed
				image = down1;
			}
			if(spriteNum == 2) {
				image=up1;
			}
		} else {
			if(spriteNum == 1) { // for animating movement and direction this stuff is all temporary and should be changed
				image = explode;
			}
			if(spriteNum == 2) {
				image=down1;
			}
		}
		graphics.drawImage(image, x, y, gp.panelSize, gp.panelSize,null);
	}
}
