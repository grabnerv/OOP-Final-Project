package entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.oop_final_project.ekin_fanclub_galaga.GamePanel;

import junit.framework.TestCase;

public class EntityTest extends Entity {

	private static GamePanel gp = new GamePanel();
	
	public EntityTest() {
		super(gp);
		// TODO Auto-generated constructor stub
		
	}
	
	
	@Test
	public void testCollision() {
		boolean value = this.collisonON;
		assertEquals(false,value);
		
	}

}
