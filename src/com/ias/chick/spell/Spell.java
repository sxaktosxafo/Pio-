package com.ias.chick.spell;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public abstract class Spell
{
	public int cost;
	public String name;
	public Sprite sprite;
	public byte id;
	
	private static Spell[] spells=new Spell[20];
	
	public static Spell createMine=new MineSpell(30,Sprite.mineSpell,1);
	public static Spell heal=new HealSpell(15,Sprite.healSpell,2);
	public static Spell teleport=new Teleport(40,Sprite.tpSpell,3);
	public static Spell shoot=new Shoot(20,Sprite.shoot,4);
	public static Spell speed=new Speed(40,Sprite.speed,5);
	public static Spell shield=new Shield(30,Sprite.shieldSpell,6);
	
	public Spell(int cost,Sprite s,byte id)
	{
		this.cost=cost;
		this.sprite=s;
		this.id=id;
		spells[id]=this;
	}
	
	static public Spell getSpell(int id)
	{
		return spells[id];
	}
	
	public void cast(int x,int y,Level level,Player p)
	{
		
	}
}