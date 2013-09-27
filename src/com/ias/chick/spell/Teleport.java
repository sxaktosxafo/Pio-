package com.ias.chick.spell;

import com.ias.chick.Game;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public class Teleport extends Spell
{

	public Teleport(int cost,Sprite s,int id)
	{
		super(cost,s,(byte) id);
	}
	
	public void cast(int x,int y,Level level,Player p)
	{
		x=x>>4<<4;
		y=y>>4<<4;
		p.x+=x;
		p.y+=y;
	}
}