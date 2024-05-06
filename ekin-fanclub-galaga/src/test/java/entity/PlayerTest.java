package entity;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;
import com.oop_final_project.ekin_fanclub_galaga.KeyHandler;

import junit.framework.TestCase;

public class PlayerTest extends Player {

	
	//private Graphics2D graphics = new Graphics2D();
	private static GamePanel gp = new GamePanel();
	private static KeyHandler keyH = new KeyHandler(gp);
	
	private PlayerTest player;
	
	public PlayerTest() {
		super(gp, keyH);
		// TODO Auto-generated constructor stub
	}
	
//	@BeforeEach
//	void setup() {
//		gp = new GamePanel();
//		keyH = new KeyHandler(gp);
//	}
	
	@Test
	public void playerTest() {
		//player = new Player(gp,keyH);
		// TODO Auto-generated constructor stub
	}

	//@ParameterizedTest
	@Test
	void testDefaultValues() {
		this.setDefaultValues();
		assertEquals(x,5 * gp.panelSize + gp.panelSize / 2);
	}
	
	@Test
	void testUpdate() {
		this.collisonON=false;
		this.direction = "left";
		keyH.left = true;
		int posX = this.x-speed;
		this.update();
		System.out.println("posX"+posX+" x"+x);
		assertEquals(posX,x);
		
		this.direction = "right";
		keyH.left=false;
		keyH.right = true;
		posX = posX+speed;
		System.out.println("posX"+posX+" x"+x);
		this.update();
		System.out.println("posX"+posX+" x"+x);
		assertEquals(posX,x);
		
		keyH.up = true;
		int posY = this.y-speed;
		this.update();
		assertEquals(posY,y);
		
		keyH.up=false;
		keyH.down = true;
		posY = posY+speed;
		this.update();
		assertEquals(posY,y);
	}
	
//	@Test
//	void testDraw() {
//		this.draw(null);
//	}
}
