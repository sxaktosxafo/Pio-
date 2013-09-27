package com.ias.chick.entity.mob.monster.boss;

import com.ias.chick.Game;
import com.ias.chick.entity.Portal;
import com.ias.chick.entity.item.BluePot;
import com.ias.chick.entity.item.LevelUp;
import com.ias.chick.entity.item.Scroll;
import com.ias.chick.entity.mob.monster.DevilL;
import com.ias.chick.entity.weapons.Projectile;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.ArenaLevel;
import com.ias.chick.level.tile.Tile;
import com.ias.chick.sound.Sound;
import com.ias.chick.spell.Spell;

public class BDevil extends Boss
{
	
	private boolean shoots;
	
	public BDevil(int x, int y)
	{
		this(x,y,50);
	}
	
	public BDevil(int x, int y, int hp)
	{
		this.x=x;
		this.y=y;
		this.sw1=Sprite.BDevil1;
		this.sw2=Sprite.BDevil2;
		this.sa=Sprite.BDevilA;
		this.maxHp=50;
		this.hp=hp;
		this.speed=1;
		this.damage=10;
		this.size=32;
		this.real=true;
	}

	public void update()
	{
		super.update();
		if(shoots)
			level.add(new Projectile(x,y,tick,this,Sprite.projR));
	}
	
	protected void attack()
	{
		super.attack();
		if(this.hp<10)
			this.heal(5);
		else
		{
			switch(random.nextInt(3))
			{
			case 0:
				int x=random.nextInt(5)+(player.x>>4)-2;
				int y=random.nextInt(5)+(player.y>>4)-2;
				while (level.getTile(x, y)==Tile.lava)
				{
					x=random.nextInt(5)+(player.x<<4)-2;
					y=random.nextInt(5)+(player.y<<4)-2;
				}
				level.setTile(x,y, Tile.lava.ID);
				break;
			case 1:
				level.add(new DevilL((this.x+player.x)/2,(this.y+player.y)/2));
				break;
			case 2:
				shoots=true;
				break;
			}
		}
	}
	
	protected void die()
	{
		super.die();
		level.add(new Scroll(x,y,Spell.shield));
		
		if (!(this.level instanceof ArenaLevel))
			level.add(new Portal(29<<4,16<<4, Sprite.portalRed, 10,15,Game.game.getLevel(7)));
		
		level.add(new LevelUp(x, y+16));
		Sound.evil.stop();;
	}
	
	protected void finishAttack()
	{
		super.finishAttack();
		shoots=false;
	}
}
