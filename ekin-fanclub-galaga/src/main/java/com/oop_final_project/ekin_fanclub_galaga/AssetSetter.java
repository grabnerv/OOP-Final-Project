package com.oop_final_project.ekin_fanclub_galaga;

import enemy.HordeEnemy;
import object.OBJ_Key;
import projectile.Projectile;

public class AssetSetter {

	GamePanel gp;
	int projectileNumber = 0;
	
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
	public void setHordeEnemy() {
		gp.hordeEnemyArray[0] = new HordeEnemy(gp);
		gp.hordeEnemyArray[0].worldX = gp.panelSize * 10;
		gp.hordeEnemyArray[0].worldY = gp.panelSize * 5;
		
		gp.hordeEnemyArray[1] = new HordeEnemy(gp);
		gp.hordeEnemyArray[1].worldX = gp.panelSize * 12;
		gp.hordeEnemyArray[1].worldY = gp.panelSize * 5;
		
		gp.hordeEnemyArray[2] = new HordeEnemy(gp);
		gp.hordeEnemyArray[2].worldX = gp.panelSize * 9;
		gp.hordeEnemyArray[2].worldY = gp.panelSize * 7;
		
		gp.hordeEnemyArray[3] = new HordeEnemy(gp);
		gp.hordeEnemyArray[3].worldX = gp.panelSize * 11;
		gp.hordeEnemyArray[3].worldY = gp.panelSize * 7;
		
		gp.hordeEnemyArray[4] = new HordeEnemy(gp);
		gp.hordeEnemyArray[4].worldX = gp.panelSize * 13;
		gp.hordeEnemyArray[4].worldY = gp.panelSize * 7;
	}
	
	public void setProjectile() {
		gp.projectileArray[projectileNumber] = new Projectile(gp);
		gp.projectileArray[projectileNumber].worldX = gp.player.worldX;
		gp.projectileArray[projectileNumber].worldY = gp.player.worldY - 20;
		projectileNumber++;
	}
} 
