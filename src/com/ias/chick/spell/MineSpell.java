package com.ias.chick.spell;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.entity.weapons.Mine;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

//** Creates a mine that explodes after 2 seconds*/
public class MineSpell extends Spell
{
	public MineSpell(int cost,Sprite s,int id)
	{
		super(cost,s,(byte) id);
		this.cost=30;
	}
	
	public void cast(int x,int y,Level level,Player p)
	{
		level.add(new Mine(p.x,p.y,2));
	}
}
