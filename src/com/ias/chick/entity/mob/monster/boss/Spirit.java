package com.ias.chick.entity.mob.monster.boss;

import com.ias.chick.entity.item.Scroll;
import com.ias.chick.entity.mob.monster.Ghost;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.spell.Spell;

public class Spirit extends Boss
{
	public Spirit(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.sw1=Sprite.spirit1;
		this.sw2=Sprite.spirit2;
		this.sa=Sprite.spiritA;
		this.maxHp=30;
		this.hp=maxHp;
		this.speed=2;
		this.damage=6;
		this.size=32;
		this.real=false;
	}
	
	public Spirit(int x, int y, int hp)
	{
		this(x,y);
		this.hp=hp;
	}
	
	public void update()
	{
		super.update();
	}
	
	protected void attack()
	{
		super.attack();
		this.sw1=Sprite.spirit1;
		this.sw2=Sprite.spirit2;
		if(this.hp<10)
			this.heal(5);
		else
		{
			switch(random.nextInt(3))
			{
			case 0:
				sw1=sw2=Sprite.bVoidSprite;
				break;
			case 1:
				this.x=player.x+32;
				this.y=player.y+32;
				break;
			case 2:
				level.add(new Ghost(this.x,this.y));
			}
		}
	}
	
	protected void die()
	{
		super.die();
		level.add(new Scroll(x,y,Spell.teleport));
	}
	
	protected void finishAttack()
	{
		super.finishAttack();
		this.speed=2;
	}
}
