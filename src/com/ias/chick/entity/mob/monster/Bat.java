package com.ias.chick.entity.mob.monster;

import com.ias.chick.graphics.Sprite;
import com.ias.chick.sound.Sound;

public class Bat extends Monster
{
	public Bat(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.speed=2;
		this.s1=Sprite.bat1;
		this.s2=Sprite.bat2;
		this.maxHp=2;
		this.hp=maxHp;
		this.damage=2;
	}
	
	public void die()
	{
		super.die();
	}
}
