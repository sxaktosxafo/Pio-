package com.ias.chick.entity;

import com.ias.chick.graphics.Screen;

public class Particle extends Entity
{
	private int color;
	private int xspeed,yspeed;
	private int life;
	
	public Particle(int x, int y, int xspeed, int yspeed, int color, int life)
	{
		this.x=x;
		this.y=y;
		this.xspeed=xspeed;
		this.yspeed=yspeed;
		this.color=color;
		this.life=life;
	}
	
	public Particle(int x, int y, int color)
	{
		this.x=x;
		this.y=y;
		this.xspeed=random.nextInt(5)-2;
		this.yspeed=random.nextInt(5)-2;
		this.color=color;
		this.life=random.nextInt(15)+15;
	}
	
	public void update()
	{
		if(life<=0) this.remove();
		this.x+=xspeed;
		this.y+=yspeed;
		life--;
	}
	
	public void render(Screen screen)
	{
		screen.renderPixel(x, y, color);
	}
}
