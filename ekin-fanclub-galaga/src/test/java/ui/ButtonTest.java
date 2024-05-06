package ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Button;

class ButtonTest extends Button {

	private static int x = 0;
	private static int y = 0;
    private static int width = 500;
    private static int height = 500;
    private static String label = "button name";

	public ButtonTest() {
		super(x,y,width,height,label);
	}
	
	@Test
	public void testMouseOver() {
		int mx = 50;
		int my = 50;
		assertEquals(mx > x && mx < x + width && my > y && my < y + height,this.isMouseOver(mx, my));
	}
	
	@Test
	public void testGetLabel() {
		assertEquals("button name",this.getLabel());
	}

}
