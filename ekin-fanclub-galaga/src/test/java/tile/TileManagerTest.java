package tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class TileManagerTest extends TileManager {

	private static GamePanel gp = new GamePanel();
	private TileManager tManager;

	public TileManagerTest() {
		super(gp);
	}
	
	@Test
	public void testgetTileImage() {
		this.getTileImage();
		assertEquals(tile[0].collision,false);
		assertEquals(tile[1].collision,true);
	}
	
//	public void testDraw() {
//		
//	}

}
