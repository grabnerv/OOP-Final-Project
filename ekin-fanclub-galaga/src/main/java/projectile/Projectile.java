package projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import com.oop_final_project.ekin_fanclub_galaga.UtilityTool;

import object.SuperObject;


/**
 * Class that extends SuperObject for creating instances of projectiles
 */
public class Projectile extends SuperObject {
	
	GamePanel gp;
	
	/**
	 * Initiates the image for the projectile
	 * @param gp current instance of the game
	 */
	public Projectile(GamePanel gp) {
		this.gp = gp;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/projectile/Projectile.png"));
			uTool.scaleImage(image, gp.panelSize, gp.panelSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = false;
	}

}
	

