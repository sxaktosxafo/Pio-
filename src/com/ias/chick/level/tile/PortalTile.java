package com.ias.chick.level.tile;

import com.ias.chick.Game;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public class PortalTile extends Tile
{
	public PortalTile(Sprite s,int id)
	{
		super(s,id);
	}
	
	public void render (int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
	
	public void activate()
	{
		
	}
}
