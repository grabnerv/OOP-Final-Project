package object;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import com.oop_final_project.ekin_fanclub_galaga.UtilityTool;


public class SuperObject {
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	

	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX;
		int screenY = worldY;
		g2.drawImage(image, screenX, screenY, gp.panelSize, gp.panelSize, null);
	}
}
