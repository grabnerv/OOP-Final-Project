package com.oop_final_project.ekin_fanclub_galaga;



import java.awt.Rectangle;
import java.awt.geom.Area;

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
	        if(entityTopRow< gp.screenRows && entity.y >-4) {
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
	        if(entityBottomRow< gp.screenRows && entity.y >-10) {
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
	        if(entityLeftCol< gp.screenColumns && entity.x>-4) {
		        panelNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
		        panelNum2 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
		        if(gp.tileM.tile[panelNum1].collision == true || gp.tileM.tile[panelNum2].collision == true || gp.tileM.tile[panelNum1] == null || gp.tileM.tile[panelNum2] == null) {
		            entity.collisonON = true;
		        }
	        } else {
	        	entity.collisonON = true;
	        }
	        break;
	        case "right":
	        entityRightCol = (entityRightWorldX + entity.speed)/gp.panelSize;
	        if(entityRightCol< gp.screenColumns && entityRightCol >= 0) {
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
	public int checkObject(Entity entity, boolean player) {
		int index =999;
		
		for(int i =0; i< gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// get entity's solid area position
				entity.solidArea.x = entity.x + entity.solidArea.x; // removed the world stuff here prob should be deleted later on
				entity.solidArea.y = entity.y + entity.solidArea.y;
				// get objects solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisonON = true;
//							System.out.println("Colliding up");
//							System.out.println("Entity solid area:"+entity.solidArea);
//							System.out.println("Object solid area:"+gp.obj[i].solidArea);
//							System.out.println("X,Y coords: "+ gp.obj[i].worldX + " "+ gp.obj[i].worldY);
//							System.out.println("Entity X,Y coords:"+entity.x+" "+entity.y);
//							//Rectangle intersection = entity.solidArea.intersection(gp.obj[i].solidArea);
//							Rectangle intersection = gp.obj[i].solidArea.intersection(entity.solidArea);
//							System.out.println("Intersection:"+intersection);
//							System.out.println("X,Y: "+entity.solidArea.x +entity.solidArea.y);
//							System.out.println("X,Y: "+gp.obj[i].solidArea.x+gp.obj[i].solidArea.y);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisonON = true;
							//System.out.println("Colliding down");
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(gp.obj[i].solidArea.getBounds2D().intersects(entity.solidArea)) {
					//	System.out.println(gp.obj[i].solidArea.getBounds2D());
						if(gp.obj[i].collision == true) {
							//System.out.println("Colliding left");
							entity.collisonON = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisonON = true;
						//	System.out.println("Colliding right");
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX; // this may mess with moving objects like enemies later on
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}  
