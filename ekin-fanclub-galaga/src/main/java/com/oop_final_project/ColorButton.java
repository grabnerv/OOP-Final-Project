package com.oop_final_project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.Button;

public class ColorButton extends Button {
    private Color color;

    public ColorButton(int x, int y, int width, int height, String label, Color color) {
        super(x, y, width, height, label);
        this.color = color;
    }

    @Override
    public void draw(Graphics g2) {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.BLACK); // Ensure text is visible
        g2.drawString(label, x + 20, y + height / 2 + 5);
    }

    public Color getColor() {
        return color;
    }
}
