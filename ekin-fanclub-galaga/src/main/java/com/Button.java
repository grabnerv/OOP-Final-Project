package com;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {
    public int x;
	public int y;
    public int width;
	public int height;
    public String label;

    public Button(int x, int y, int width, int height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
    }

    public boolean isMouseOver(int mx, int my) {
        return mx > x && mx < x + width && my > y && my < y + height;
    }
    public void draw(Graphics g) {
        // Draw the button background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);

        // Draw the text label
        g.setColor(Color.BLACK);
        g.drawString(label, x + (width / 2) - (label.length() * 3), y + (height / 2) + 5);  // for Alignment can change later
    }

    // Getters if needed for the label or position for more specific UI controls
    public String getLabel() {
        return label;
    }
}
