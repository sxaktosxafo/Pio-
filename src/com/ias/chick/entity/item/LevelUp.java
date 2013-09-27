package com.ias.chick.entity.item;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;

public class LevelUp extends Item
{

	public LevelUp(int x, int y) {
		super(x, y, Sprite.levelUp);
	}
	
	public void touched(Player p)
	{
		p.levelUp();
		super.touched(p);
	}
}
