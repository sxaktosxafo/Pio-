package com.ias.chick.entity.weapons;

import com.ias.chick.entity.Entity;
import com.ias.chick.entity.Particle;
import com.ias.chick.entity.mob.Mob;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.sound.Sound;

public class Projectile extends Entity
{
	private Sprite sprite;
	int speed=3;
	double xv,yv;
	double x,y;
	double direction;
	int range;
	private Mob mob;
	private int damage;
	
	public Projectile(int sx, int sy, double dir, Mob mob)
	{
		this.x=sx;
		this.y=sy;
		this.direction=dir;
		this.xv=Math.cos(dir)*speed;
		this.yv=Math.sin(dir)*speed;
		this.level=mob.level;
		this.mob=mob;
		this.size=8;
		this.sprite=Sprite.proj;
		this.damage=1;
		this.range=100;
	}
	
	public Projectile(int sx, int sy, double dir, Mob mob, Sprite s)
	{
		this.x=sx;
		this.y=sy;
		this.direction=dir;
		this.xv=Math.cos(dir)*speed;
		this.yv=Math.sin(dir)*speed;
		this.init(mob.level);
		this.mob=mob;
		this.size=8;
		this.sprite=s;
		this.damage=5;
		this.range=200;
	}
	
	public void render(Screen screen)
	{
		screen.renderSprite((int)x, (int)y, sprite);
	}
	
	public void update()
	{
		if(level.getTile((int)x+4>>4, (int)y+4>>4).solid())
			this.remove();
		
		for(Entity e: level.entities)
		{
			if (e.equals(this) || e.equals(mob) || !(e instanceof Mob))
				continue;
			
			if (this.collides(e))
			{
				((Mob) e).hit(damage);
				this.remove();
				break;
			}
		}
			
		range--;
		if (range<0) mob.level.remove(this);
		this.x+=xv;
		this.y+=yv;
	}
	
	public boolean collides(Entity e)
	{
		int xe=(int)e.x;
		int ye=(int)e.y;
		if (x>xe && x< xe+e.size && y>ye && y< ye+e.size)
			return true;
		return false;
	}
	
	public void remove()
	{
		super.remove();
		super.x=(int)x;
		super.y=(int)y;
		particles(sprite);
		Sound.exp.play();
	}
}
