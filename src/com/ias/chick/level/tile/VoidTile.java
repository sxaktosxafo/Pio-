package com.ias.chick.level.tile;

import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;

public class VoidTile extends Tile
{

	public VoidTile(Sprite s)
	{
		super(s,17);
	}
	
	public void render (int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
	
	public boolean solid()
	{
		return false;
	}
}
