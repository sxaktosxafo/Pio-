package com.ias.chick.entity.mob.monster;

import com.ias.chick.entity.item.Emblem;
import com.ias.chick.entity.item.Scroll;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.tile.Tile;
import com.ias.chick.sound.Sound;
import com.ias.chick.spell.Spell;

public class Devil extends Monster
{
	private int puCD=20; //powerup countDown;
	
	public Devil(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.speed=1;
		this.real=true;
		this.s1=Sprite.devil1;
		this.s2=Sprite.devil2;
		this.maxHp=15;
		this.hp=maxHp;
		this.damage=7;
	}
	
	public void update()
	{
		super.update();
		if (puCD>0)
			puCD--;
		else
		{
			switch(random.nextInt(5))
			{
			case 0:
				puCD=30*1;
				this.speed=0;
				this.damage=10;
				int xs=(x>>4)+(random.nextInt(3)-1);
				int ys=(y>>4)+(random.nextInt(3)-1);
				while (level.getTile(xs, ys).solid())
				{
					xs=(random.nextInt(3)-1);
					ys=(random.nextInt(3)-1);
				}
				level.add(new DevilL(xs<<4,ys<<4));
				break;
			case 1:
				puCD=30*1;
				this.speed=3;
				this.damage=7;
				break;
			default:
				puCD=30*3;
				this.speed=1;
				this.damage=10;
				break;
			}
		}
	}
	
	protected void die()
	{
		super.die();
		level.add(new Emblem(x, y));
		Tile.redLock.open();
	}
}
