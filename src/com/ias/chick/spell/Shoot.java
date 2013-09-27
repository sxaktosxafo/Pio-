package com.ias.chick.spell;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.entity.weapons.Projectile;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public class Shoot extends Spell
{
	public Shoot(int cost,Sprite s,int id)
	{
		super(cost,s,(byte) id);
	}
	
	public void cast(int x,int y,Level level,Player p)
	{
		double dir=Math.atan2(y-8, x-8);
		Projectile pr=new Projectile(p.x+8,p.y+8,dir,p);
		level.add(pr);
	}
}
