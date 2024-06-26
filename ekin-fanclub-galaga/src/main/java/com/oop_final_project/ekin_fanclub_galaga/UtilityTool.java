package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for handling image scaling
 */
public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original,0,0,width,height,null);
		g2.dispose();
		
		return scaledImage;
	}
}
