package com.ias.chick.entity.item;

import com.ias.chick.Game;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;

public class Emblem extends Item
{
	public static int embFound=0;
	
	public Emblem(int x, int y)
	{
		super(x, y, Sprite.emblem);
	}
	
	public void touched(Player p)
	{
		embFound++;
		super.touched(p);
		if(Game.game.getLevel()!=Game.game.getLevel(7))
			Game.game.setLevel(Game.game.getLevel(7),25,15);
	}
}
