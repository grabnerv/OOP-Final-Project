package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.screenColumns][gp.screenRows];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spaceTile.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/enemies/boss_enemy.png"));
			tile[1].collision = true;

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col< gp.screenColumns && row< gp.screenRows) {
				String line = br.readLine();
				
				while(col<gp.screenColumns) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.screenColumns) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}
	public void draw(Graphics2D graphics) {
//		graphics.drawImage(tile[0].image,0,0,gp.panelSize,gp.panelSize,null); // manual tiles
//		graphics.drawImage(tile[0].image,70,100,gp.panelSize,gp.panelSize,null);
//		graphics.drawImage(tile[0].image,200,300,gp.panelSize,gp.panelSize,null);
//		graphics.drawImage(tile[0].image,400,120,gp.panelSize,gp.panelSize,null);
//		graphics.drawImage(tile[0].image,10,420,gp.panelSize,gp.panelSize,null);
//		graphics.drawImage(tile[0].image,200,620,gp.panelSize,gp.panelSize,null);
//		graphics.drawImage(tile[0].image,325,450,gp.panelSize,gp.panelSize,null);
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col<gp.screenColumns && row < gp.screenRows) {
			int tileNum = mapTileNum[col][row];
			graphics.drawImage(tile[tileNum].image, x,y,gp.panelSize,gp.panelSize,null);
			col++;
			x += gp.panelSize;
			
			if(col==gp.screenColumns) {
				col = 0;
				x = 0;
				row++;
				y+=gp.panelSize;
			}
		}
	}
}