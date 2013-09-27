package com.ias.chick.spell;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public class HealSpell extends Spell
{
	public HealSpell(int cost,Sprite s, int id)
	{
		super(cost,s,(byte)id);
	}
	
	public void cast(int x,int y,Level level,Player p)
	{
		p.heal(5);
	}
}
