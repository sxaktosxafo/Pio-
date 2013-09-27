package com.ias.chick.entity.mob.monster;

import com.ias.chick.Game;
import com.ias.chick.entity.mob.monster.boss.Spirit;
import com.ias.chick.graphics.Image;
import com.ias.chick.graphics.Sprite;

public class Ghost extends Monster
{
	public Ghost(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.speed=1;
		this.real=false;
		this.s1=Sprite.ghost1;
		this.s2=Sprite.ghost2;
		this.maxHp=5;
		this.hp=maxHp;
		this.damage=3;
	}
	
	protected void die()
	{
		super.die();
	}
}
