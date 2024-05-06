package enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import com.oop_final_project.ekin_fanclub_galaga.UtilityTool;

import entity.Entity;


public class HordeEnemy extends Entity{
	
	GamePanel gp;
	public String direction;
 
	public HordeEnemy(GamePanel gp) {
		super(gp);
		this.gp = gp;
		String name = "Horde Enemy";
		int speed = 1;
		int maxLife = 1;
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32; // none of these values are final and should be tweaked in debugging
		getImage();
		setDefaultValues();
	
	}
	
	public void setDefaultValues() {
		speed = 4;
		direction = "left";
	}
	
	public void getImage() {
		up1 = setup("enemy");
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/enemies/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.panelSize, gp.panelSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
