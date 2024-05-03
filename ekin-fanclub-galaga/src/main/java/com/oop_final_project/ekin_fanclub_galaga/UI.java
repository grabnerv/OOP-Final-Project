package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.Button;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    ArrayList<Button> buttons = new ArrayList<>();

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp) {
        this.gp =gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        buttons.add(new Button(100, 100, 200, 50, "Start Game"));
        buttons.add(new Button(100, 200, 200, 50, "Exit Game"));
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        for (Button button : buttons) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(button.x, button.y, button.width, button.height);
            g2.setColor(Color.BLACK);
            g2.drawString(button.label, button.x + 20, button.y + 30); // we can adjust later.
        }

        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState) {
            // Playstate stuff goes in here
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    } 
    public void drawTitleScreen() {
        String title = "Galaga Game"; 
        g2.setFont(arial_80B); // Set the font to something larger for the title
        int x = getXforCenteredText(title);
        int y = gp.screenHeight / 2; // Center vertically, adjust as needed
        g2.drawString(title, x, y);
        
    } 
    public void drawPauseScreen() {

        String text = "HOLD UP";
        int x;

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;

        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);


    }
      public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;

      }
    }

