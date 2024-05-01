package com.oop_final_project.ekin_fanclub_galaga;

import entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    
}
public void checkTile(Entity entity) {

    int entityLeftWorldX = entity.x + entity.solidArea.x;
    int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.y + entity.solidArea.y;
    int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeftWorldX/gp.panelSize;
    int entityRightCol = entityRightWorldX/gp.panelSize;
    int entityTopRow = entityTopWorldY/gp.panelSize;
    int entityBottomRow =entityBottomWorldY/gp.panelSize;

    int panelNum1, panelNum2;

    switch(entity.direction) {
        case"up":
        entityTopRow = (entityTopWorldY - entity.speed)/gp.panelSize;
        if(entityTopRow< gp.screenRows && entityTopRow > 0) {
	        panelNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
	        panelNum2 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
	        if(gp.tileM.tile[panelNum1].collision == true || gp.tileM.tile[panelNum2].collision == true) {
	            entity.collisonON = true;
	        }
        } else {
        	entity.collisonON = true;
        }
        break;
        case "down":
        entityBottomRow = (entityBottomWorldY + entity.speed)/gp.panelSize;
        if(entityBottomRow< gp.screenRows && entityBottomRow > 0) {
	        panelNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
	        panelNum2 = gp.tileM.mapTileNum[entityRightCol] [entityBottomRow];
	        if(gp.tileM.tile[panelNum1].collision == true || gp.tileM.tile[panelNum2].collision == true) {
	            entity.collisonON = true;
	        }
        } else {
        	entity.collisonON = true;
        }
        break;
        case "left":
        entityLeftCol = (entityLeftWorldX - entity.speed)/gp.panelSize;
        if(entityLeftCol< gp.screenColumns && entityLeftCol > 0) {
	        panelNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
	        panelNum2 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
	        if(gp.tileM.tile[panelNum1].collision == true || gp.tileM.tile[panelNum2].collision == true) {
	            entity.collisonON = true;
	        }
        } else {
        	entity.collisonON = true;
        }
        break;
        case "right":
        entityRightCol = (entityRightWorldX + entity.speed)/gp.panelSize;
        if(entityRightCol< gp.screenColumns && entityRightCol > 0) {
        	 panelNum1 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
             panelNum2 = gp.tileM.mapTileNum[entityRightCol] [entityBottomRow];
             if(gp.tileM.tile[panelNum1].collision == true || gp.tileM.tile[panelNum2].collision == true) {
                 entity.collisonON = true;
             }
        } else {
        	entity.collisonON = true;
        }
        break;
    }

}
}  
