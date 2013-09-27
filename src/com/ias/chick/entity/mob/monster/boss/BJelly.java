package com.ias.chick.entity.mob.monster.boss;

import com.ias.chick.entity.item.Scroll;
import com.ias.chick.entity.weapons.Projectile;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.spell.Spell;

public class BJelly extends Boss
{
	public BJelly(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.sw1=Sprite.bjelly1;
		this.sw2=Sprite.bjelly2;
		this.sa=Sprite.bjellyA;
		this.maxHp=30;
		this.hp=maxHp;
		this.speed=1;
		this.damage=7;
		this.size=32;
	}
	
	public BJelly(int x, int y, int hp)
	{
		this.x=x;
		this.y=y;
		this.sw1=Sprite.bjelly1;
		this.sw2=Sprite.bjelly2;
		this.sa=Sprite.bjellyA;
		this.maxHp=40;
		this.hp=hp;
		this.speed=1;
		this.damage=7;
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
				this.speed=4;
				break;
			case 1:
				level.add(new Projectile(x,y,0,this,Sprite.projB));
				level.add(new Projectile(x,y,0.79,this,Sprite.projB));
				
				level.add(new Projectile(x,y,1.57,this,Sprite.projB));
				level.add(new Projectile(x,y,2.36,this,Sprite.projB));
				
				level.add(new Projectile(x,y,3.14,this,Sprite.projB));
				level.add(new Projectile(x,y,3.93,this,Sprite.projB));
				
				level.add(new Projectile(x,y,4.71,this,Sprite.projB));
				level.add(new Projectile(x,y,5.40,this,Sprite.projB));
				
				break;
			}
		}
	}
	
	
	protected void die()
	{
		super.die();
		level.add(new Scroll(x,y,Spell.speed));
	}
	
	protected void finishAttack()
	{
		super.finishAttack();
	}
}
