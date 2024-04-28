package entity;

import java.awt.Color;
import java.awt.Graphics2D;
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
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_img1.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_img2.png"));
			explode = ImageIO.read(getClass().getResourceAsStream("/player/player_img3.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		
		if(keyH.up == true || keyH.down == true || keyH.left == true || keyH.right == true) { // makes character not animated while in place if update code is placed in here
			
		}
		if(keyH.up == true) {
			direction = "up";
			y -= speed;
		} 
		else if(keyH.down == true) {
			direction = "down";
			y += speed;
		}
		else if(keyH.left == true) {
			direction = "left";
			x -= speed;
		}
		else if(keyH.right == true) {
			direction = "right";
			x += speed;
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
