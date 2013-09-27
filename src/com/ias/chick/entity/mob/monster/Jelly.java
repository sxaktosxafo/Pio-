package com.ias.chick.entity.mob.monster;

import com.ias.chick.Game;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.tile.LockTile;
import com.ias.chick.level.tile.Tile;

public class Jelly extends Monster
{
	public Jelly(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.speed=1;
		this.s1=Sprite.blueJ1;
		this.s2=Sprite.blueJ2;
		this.maxHp=3;
		this.hp=maxHp;
		this.damage=1;
	}
	
	protected void die()
	{
		super.die();
	}
}
