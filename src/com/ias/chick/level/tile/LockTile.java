package com.ias.chick.level.tile;

import com.ias.chick.Game;
import com.ias.chick.graphics.Sprite;

public class LockTile extends Tile
{
	private int key;
	public Sprite spriteOpen;

	public LockTile(Sprite s, Sprite s2,int id, int key)
	{
		super(s, id);
		this.spriteOpen=s2;
		this.key=key;
	}
	
	public void open()
	{
		Game.game.keys[key]=true;
		this.sprite=spriteOpen;
	}

	public boolean solid()
	{
		return !Game.game.keys[key];
	}
}
