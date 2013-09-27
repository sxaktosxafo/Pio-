package com.ias.chick.entity.weapons;

import com.ias.chick.entity.Entity;
import com.ias.chick.entity.mob.Mob;
import com.ias.chick.entity.mob.monster.Monster;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.sound.Sound;

public class Mine extends Entity
{
	public int cd;
	private int damage;
	private Sprite s1=Sprite.mine1;
	private Sprite s2=Sprite.mine2;
	
	public Mine(int x, int y, int seconds)
	{
		this.x=x;
		this.y=y;
		this.cd=seconds*30;
		this.damage=5;
	}
	
	public Mine(int x, int y, int ticks, int d)
	{
		this.x=x;
		this.y=y;
		this.cd=ticks;
		this.damage=d;
	}
	
	public void update()
	{
		cd--;
		if (cd<=0)
			this.explode();
	}
	
	public void render(Screen screen)
	{
		if (cd%30<25)
			screen.renderSprite(x, y, s2);
		else
			screen.renderSprite(x, y, s1);
	}
	
	public void explode()
	{
		remove();
		particles(s1);
		for(Entity e:level.entities)
		{
			if(!(e instanceof Mob)) continue;
			int xaa=e.x+8-x;
			int yaa=e.y+8-y;
			if (xaa*xaa+yaa*yaa<=40*40)
				((Mob) e).hit(damage);
			Sound.explosion.play();
		}
	}
}
