package com.ias.chick.entity.item;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;

public class BluePot extends Item
{
	int restoring;
	
	public BluePot(int x, int y, Sprite s,int healing)
	{
		super(x, y, s);
		this.restoring=healing;
	}
	
	public BluePot(int x, int y, int healing)
	{
		super(x, y, Sprite.bluePot);
		this.restoring=healing;
	}
	
	public void touched(Player p)
	{
		p.restoreMana(restoring);
		super.touched(p);
	}
	
	public int restoring()
	{ return restoring; }
}
