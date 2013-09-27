package com.ias.chick.entity.mob;

import com.ias.chick.entity.Entity;
import com.ias.chick.entity.Particle;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.tile.Tile;

public abstract class Mob extends Entity
{
	int xdir,ydir;
	
	protected boolean real=true;	// a ghost isn't real, so he can pass through walls
	
	protected Sprite sprite;
	protected int dir=0;
	protected int speed;
	protected boolean mooving=false;
	
	
	
	public int hp;
	public int maxHp;
	
	/** mob moves of xa,ya pixels */ 
	public void move(int xa,int ya)
	{
		if (xa!=0 && ya!=0)
		{
			move(xa,0);
			move(0,ya);
			return;
		}
		
		if (xa>0) dir=1;
		if (xa<0) dir=3;
		if (ya>0) dir=2;
		if (ya<0) dir=0;
		
		if (!collision(xa,ya))
		{
			x += xa;
			y += ya;
		}
		else
		{
			// if the mob moves for more than 1 pixel, it may be 1-pixel away from the wall and detect collision anyway
			byte a=0;
			if (xa>+1) {xa--; a=1;}
			if (ya>+1) {ya--; a=1;}
			if (xa<-1) {xa++; a=1;}
			if (ya<-1) {ya++; a=1;}
			
			if (a==1) move(xa,ya);
		}
	}
	
	public void update()
	{
		
	}
	
	/** Sees if any of the four corners of the mob is in a solid block*/
	private boolean collision(int xa,int ya)
	{
		boolean solid=false;
		
		// analizes each angle
		
		// 0 1
		// 2 3
		for(int c=0;c<4;c++)
		{
			//              *width +- thing / tile
			
			if (x+xa<0 || y+ya<0)
				solid=Tile.tiles[level.voidTile].solid();
			
			int xt= ((x+xa) + (c % 2)*(size-1))/16;
			int yt= ((y+ya) + (c / 2)*(size-1))/16;
			if(level.getTile(xt, yt).solid()) solid=real;
		}
			
		return solid;
	}
	
	/** Mob is hit */
	public void hit (int dam)
	{
		this.hp-=dam;
	}
	
	/** Mob is healed */
	public void heal(int h)
	{
		while(h>0)
		{
			if (this.hp==this.maxHp)
				return;
			this.hp++;
			h--;
		}
	}
	
	public void setSize(int a)
	{
		this.size=a;
	}
	
	public void render()
	{
		
	}
	
	protected void die()
	{
		particles(sprite);
		remove();
	}
}
