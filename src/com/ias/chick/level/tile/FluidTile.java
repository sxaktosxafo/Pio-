package com.ias.chick.level.tile;

import com.ias.chick.Game;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;

public class FluidTile extends Tile
{
	Sprite sprite1;
	Sprite spriteF;
	
	public FluidTile(Sprite s1, Sprite s2, int id) {
		super(s1, id);
		this.sprite=s1;
		this.sprite1=s2;
	}
	
	public void render (int x, int y, Screen screen)
	{
		if (Game.game.getLevel().counter%30>=15){
			if(Math.abs(x%2)==Math.abs(y%2))
				spriteF=sprite;
			else
				spriteF=sprite1;
		}else{
			if(Math.abs(x%2)==Math.abs(y%2))
				spriteF=sprite1;
			else
				spriteF=sprite;
		}
		
		screen.renderSprite(x<<4, y<<4, this.spriteF);
	}
	
	public boolean solid()
	{
		return false;
	}
}
