package com.ias.chick.entity;

import java.util.Random;

import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public abstract class Entity
{
	public int x,y;
	public Level level;
	protected final Random random=new Random();
	
	public int size=16;
	
	public void update()
	{
		
	}
	
	public void render(Screen screen)
	{
		
	}
	
	public void remove()
	{
		level.remove(this);
	}
	
	public void particles(Sprite sprite)
	{
		int part=0;
		while(part<sprite.SIZE)
		{
			int color=sprite.pixels[random.nextInt(sprite.pixels.length)];
			if(color != 0xFFFF00FF)
			{
				level.add(new Particle(x,y,color));
				part++;
			}
		}
	}
	
	public void init(Level level)
	{
		this.level=level;
	}

	public boolean collides(Entity e)
	{
		int ts = this.size;
		int es = e.size;
		if (ts <= 0 || es <= 0)
			return false;
		int tx = this.x;
		int ty = this.y;
		int ex = e.x;
		int ey = e.y;
		int ew = es+ex;
		int eh = es+ey;
		int tw = ts+tx;
		int th = ts+ty;
		//      overflow || intersect
		return ((ew < ex || ew > tx) &&
		       (eh < ey || eh > ty) &&
		       (tw < tx || tw > ex) &&
		       (th < ty || th > ey));
	}
}
