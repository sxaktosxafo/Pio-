package com.ias.chick.level.tile;

import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;

public class FloorTile extends Tile
{
	public FloorTile(Sprite s, int id)
	{
		super(s,id);
	}
	
	public void render (int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
}
