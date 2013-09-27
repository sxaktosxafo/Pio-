package com.ias.chick.entity.mob.monster.boss;

import com.ias.chick.entity.item.Scroll;
import com.ias.chick.entity.mob.monster.Bat;
import com.ias.chick.entity.weapons.Mine;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.spell.Spell;

public class BBat extends Boss
{
	public BBat(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.sw1=Sprite.bbat1;
		this.sw2=Sprite.bbat2;
		this.sa=Sprite.bbatA;
		this.maxHp=50;
		this.hp=maxHp;
		this.speed=1;
		this.damage=10;
		this.size=32;
	}
	
	public BBat(int x, int y, int hp)
	{
		this.x=x;
		this.y=y;
		this.sw1=Sprite.bbat1;
		this.sw2=Sprite.bbat2;
		this.sa=Sprite.bbatA;
		this.maxHp=50;
		this.hp=hp;
		this.speed=1;
		this.damage=10;
		this.size=32;
	}
	
	public void update()
	{
		super.update();
	}
	
	protected void attack()
	{
		super.attack();
		if(this.hp<10)
			this.heal(5);
		else
		{
			switch(random.nextInt(2))
			{
			case 0:
				level.add(new Mine(player.x,player.y,30,20));
				break;
			case 1:
				level.add(new Bat(this.x-16,this.y));
				level.add(new Bat(this.x+48,this.y));
				break;
			}
		}
	}
	
	protected void die()
	{
		super.die();
		level.add(new Scroll(x,y,Spell.createMine));
	}
	
	protected void finishAttack()
	{
		super.finishAttack();
	}
}
