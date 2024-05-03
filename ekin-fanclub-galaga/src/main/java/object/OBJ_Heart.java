package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;

public class OBJ_Heart extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		this.gp = gp;
		name = "Heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/health/full heart"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/health/half heart"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/health/empty heart"));
//			uTool.scaleImage(image,gp.panelSize,gp.panelSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
