package com.ias.chick.entity;

import com.ias.chick.Game;
import com.ias.chick.entity.item.Emblem;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public class Portal extends Entity
{
	private Level levelT;
	private int xt,yt;
	private Sprite sprite;
	private int reqEggs=0;
	
	public Portal(int x, int y, Sprite s,Level l)
	{
		this.levelT=l;
		this.sprite=s;
		this.x=x;
		this.y=y;
		this.xt=levelT.x1();
		this.yt=levelT.y1();
	}
	
	public Portal(int x, int y, Sprite s,int xt, int yt,Level l)
	{
		this.levelT=l;
		this.sprite=s;
		this.x=x;
		this.y=y;
		this.xt=xt;
		this.yt=yt;
	}

	public Portal(int x, int y, Sprite s,int reqEggs, Level l)
	{
		this.levelT=l;
		this.sprite=s;
		this.x=x;
		this.y=y;
		this.reqEggs=reqEggs;
		this.xt=levelT.x1();
		this.yt=levelT.y1();
	}
	
	public void update ()
	{
		if(reqEggs<=Emblem.embFound && this.collides(Game.game.player))
		{
			Game.game.setLevel(levelT,xt,yt);
			remove();
		}
	}
	
	public void render(Screen screen)
	{
		if(reqEggs<=Emblem.embFound)
			screen.renderSprite(x, y, sprite);
		else
			screen.renderSprite(x, y, Sprite.portalRedOff);
	}
}
