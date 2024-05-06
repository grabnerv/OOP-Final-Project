package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import com.oop_final_project.ekin_fanclub_galaga.KeyHandler;
import com.oop_final_project.ekin_fanclub_galaga.UtilityTool;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;


	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32; // none of these values are final and should be tweaked in debugging

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = 5 * gp.panelSize + gp.panelSize / 2;
		y = 12 * gp.panelSize;
		this.worldX = x;
		this.worldY = y;
		speed = 4;
		direction = "down";
		
		//Player status
		maxLife = 6;
		life = maxLife;
		
		
	}

	public void getPlayerImage() {
		up1 = setup("galaga_v2");
		down1 = setup("galaga_v2");
		explode = setup("galaga_v2");
	}

	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.panelSize, gp.panelSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void saveModifiedImage(BufferedImage image, String savePath) {
		try {
			File outputFile = new File(savePath);
			ImageIO.write(image, "PNG", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void update() {
		//this if is new might have to delete
		if (gp.gameState == gp.playState) {
			direction = ""; //Reset direction
		if (keyH.up) {
			direction = "up";
		} else if (keyH.down) {
			direction = "down";
		} else if (keyH.left) {
			direction = "left";
		} else if (keyH.right) {
			direction = "right";
		} else {
			direction = "";
		}
		}

		//check for monster collision
		//this is new might delete
		if(gp.checkMonsterCollision(this)) {
			takeDamage(1);
		}
		// check tile collision
		collisonON = false;
		gp.cChecker.checkTile(this);

		// check object collision
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		

		if (collisonON == false) {
			switch (direction) {
			case "up":
				y -= speed;
				this.worldY = y;
				break;
			case "down":
				y += speed;
				this.worldY = y;
				break;
			case "left":
				x -= speed;
				this.worldX = x;
				break;
			case "right":
				x += speed;
				this.worldX = x;
				break;
			}
		}
		

		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	public void pickUpObject(int i) { // I don't think this is necessary

		if (i != 999) {
			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "Key":
				gp.obj[i] = null;
				break;
			case "Something else":

				break;
			}
		}
	}

	public void draw(Graphics2D graphics) {
//		graphics.setColor(Color.white);
//		graphics.fillRect(x, y, gp.panelSize, gp.panelSize);

		BufferedImage image = null;
		if (direction == "up") {
			if (spriteNum == 1) { // for animating movement and direction this stuff is all temporary and should
									// be changed
				image = up1;
			}
			if (spriteNum == 2) {
				image = down1;
			}
		} else if (direction == "down") {
			if (spriteNum == 1) { // for animating movement and direction this stuff is all temporary and should
									// be changed
				image = down1;
			}
			if (spriteNum == 2) {
				image = up1;
			}
		} else {
			if (spriteNum == 1) { // for animating movement and direction this stuff is all temporary and should
									// be changed
				image = explode;
			}
			if (spriteNum == 2) {
				image = down1;
			}
		}
		graphics.drawImage(image, x, y, null);
	}
	//This should cause the player to take damage
	public void takeDamage(int damage) {
        life -= damage;
        if (life <= 0) {
            // Player's dead, implement game over or respawn
            // reset player's position for now 
            x = 5 * gp.panelSize + gp.panelSize / 2;
            y = 12 * gp.panelSize;
            life = maxLife; // Reset player's life
        }
    }

}
