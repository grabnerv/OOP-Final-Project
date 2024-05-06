package com.oop_final_project.ekin_fanclub_galaga;

import enemy.HordeEnemy;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 9 * gp.panelSize;
		gp.obj[0].worldY = 6 *gp.panelSize;
		
//		gp.obj[1] = new OBJ_Key();
//		gp.obj[1].worldX = 5* gp.panelSize;
//		gp.obj[1].worldY = 2* gp.panelSize;
	}
	public void setEnemy() {
		gp.enemy[0] = new HordeEnemy(gp);
		gp.enemy[0].worldX = gp.panelSize * 8;
		gp.enemy[0].worldY = gp.panelSize * 5;
		
		gp.enemy[1] = new HordeEnemy(gp);
		gp.enemy[1].worldX = gp.panelSize * 9;
		gp.enemy[1].worldY = gp.panelSize * 5;
	}
} 
