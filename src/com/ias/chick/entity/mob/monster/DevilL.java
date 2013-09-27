package com.ias.chick.entity.mob.monster;

import com.ias.chick.entity.item.RedPot;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.sound.Sound;

public class DevilL extends Monster
{
	public DevilL(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.speed=2;
		this.s1=Sprite.devilL1;
		this.s2=Sprite.devilL2;
		this.maxHp=3;
		this.hp=maxHp;
		this.damage=3;
	}
	
	public void die()
	{
		super.die();
	}
}
