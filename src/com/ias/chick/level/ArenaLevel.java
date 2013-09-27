package com.ias.chick.level;

import com.ias.chick.entity.mob.monster.boss.*;

public class ArenaLevel extends ImageLevel
{
	private int counter;
	private int sx=23<<4,sy=23<<4;
	
	public ArenaLevel(String path, byte id)
	{
		super(path, id);
		counter=0;
		createMonster();
	}
	
	public void monsterDied(Boss boss)
	{
		if(boss instanceof BJelly) this.counter=1;
		else if(boss instanceof BBat) this.counter=2;
		else if(boss instanceof Spirit) this.counter=3;
		else if(boss instanceof BDevil) this.counter=4;
		
		createMonster();
		//Game.game.player.heal(50);
		//Game.game.player.restoreMana(50);
	}
 
	private void createMonster()
	{
		switch(counter)
		{
		case 0:
			this.add(new BJelly(sx,sy));
			break;
		case 1:
			this.add(new BBat(sx,sy));
			break;
		case 2:
			this.add(new Spirit(sx,sy));
			break;
		case 3:
			this.add(new BDevil(sx,sy));
			break;
		}
	}
}
