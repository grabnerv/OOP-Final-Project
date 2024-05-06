package com.oop_final_project.ekin_fanclub_galaga;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.Button;

import object.OBJ_Heart;
import object.SuperObject;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, funfont_80B;
    BufferedImage Fullheart, halfheart, emptyheart;
    


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
        arial_80B = new Font("Arial", Font.BOLD, 40);
        funfont_80B = new Font("American Typewriter", Font.BOLD, 80);
        buttons.add(new Button(160, 300, 250, 50, "Start Game"));
        buttons.add(new Button(160, 400, 250, 50, " Exit Game"));
        buttons.add(new Button(120, 500, 350, 50, "Color Customizer"));
        
        //Create heart object
        SuperObject heart = new OBJ_Heart(gp);
        Fullheart = heart.image;
        halfheart = heart.image2;
        emptyheart = heart.image3;
        
    }


    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;
        System.out.println(gp.gameState);

        

        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState) {
        	drawPlayerLife();
            drawPauseScreen();
        }
    } 
    public void drawPlayerLife() {
    	int x = gp.panelSize/2;
    	int y = gp.panelSize/2;
    	int i = 0;
    	
    	//Draw blank heart
    	while(i < gp.player.maxLife/2) {
    		g2.drawImage(emptyheart, x, y, null);
    		i++;
    		x += gp.panelSize;
    	}
    	//reset the variables
    	 x = gp.panelSize/2;
    	 y = gp.panelSize/2;
    	 i = 0;
    	 //Draw current life
    	 while(i < gp.player.life) {
    		 g2.drawImage(halfheart,x,y,null);
    		 i++;
    		 if(i < gp.player.life) {
    			 g2.drawImage(Fullheart, x, y,null);
    		 }
    		 i++;
    		 x += gp.panelSize;
    	 }
    }
    public void drawTitleScreen() {
        String title = "Galaga Game"; 
        g2.setFont(arial_80B); // Set the font to something larger for the title
        g2.setColor(Color.white);
        int x = getXforCenteredText(title);
        int y = 250; // Center vertically, adjust as needed
        g2.drawString(title, x, y);
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        Color darkBlue = new Color(9,18,68);
        for (Button button : buttons) {
            g2.setColor(darkBlue);
            g2.fillRect(button.x, button.y, button.width, button.height);
            g2.setColor(Color.BLACK);
            g2.drawString(button.label, button.x + 20, button.y + 40); // we can adjust later.
        }
        
    } 
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x;
        g2.setFont(funfont_80B);
        g2.setColor(Color.BLUE);
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;

        int y = gp.screenHeight/2 - 150;

        g2.drawString(text, x, y);
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        Color darkBlue = new Color(9,18,68);
        for (Button button : buttons) {
            g2.setColor(darkBlue);
            g2.fillRect(button.x, button.y, button.width, button.height);
            g2.setColor(Color.BLACK);
            g2.drawString(button.label, button.x + 20, button.y + 40); // we can adjust later.
        }


    }
      public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;

      }
    }

