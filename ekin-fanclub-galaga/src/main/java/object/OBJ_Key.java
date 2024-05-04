package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;

public class OBJ_Key extends SuperObject {
	GamePanel gp;
	public OBJ_Key(GamePanel gp) {
		this.gp = gp;
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));//path to the file
			uTool.scaleImage(image, gp.panelSize,gp.panelSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
