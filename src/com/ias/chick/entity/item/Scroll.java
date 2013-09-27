package com.ias.chick.entity.item;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.spell.Spell;

/** Something that make the player learn a new spell */
public class Scroll extends Item
{
	private Spell spell;
	
	public Scroll(int x, int y, Spell s)
	{
		super(x, y, Sprite.scroll);
		this.spell=s;
	}
	
	public void touched(Player p)
	{
		p.learn(spell.id);
		super.touched(p);
	}
	
	public int spell()
	{
		return this.spell.id;
	}

}
