package com.ias.chick.entity.item;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;

public class RedPot extends Item
{
	int healing;
	
	public RedPot(int x, int y, Sprite s,int healing)
	{
		super(x, y, s);
		this.healing=healing;
	}
	
	public RedPot(int x, int y, int h) {
		super(x, y, Sprite.redPot);
		this.healing=h;
	}

	public void touched(Player p)
	{
		p.heal(healing);
		super.touched(p);
	}
	
	public int hp() { return this.healing; }

}
