package com.ias.chick.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.ias.chick.Game;
import com.ias.chick.entity.weapons.*;
import com.ias.chick.entity.mob.*;
import com.ias.chick.entity.mob.monster.*;
import com.ias.chick.entity.mob.monster.boss.*;
import com.ias.chick.entity.item.*;
import com.ias.chick.level.Level;
import com.ias.chick.level.SavedLoader;
import com.ias.chick.spell.Spell;

public class EntitySaveLoad
{
	private static final Class[] ENT =
		{
			BluePot.class,		//0
			RedPot.class,
			Scroll.class,
			Mine.class,

			Projectile.class,	//4
			Bat.class,
			Devil.class,
			DevilL.class,

			Ghost.class,		//8
			Jelly.class,
			BBat.class,
			BDevil.class,

			BJelly.class,		//12
			Spirit.class,
		};
	
	private static final byte Tlevel=0x3A;	// terminal for level
	private static final byte Tentit=0x3B;	// and entity
	private static final byte Tend=0x3C;
	
	/** Saves entities.ens, a file containing all the levels' entities */
	public static void saveEntities()
	{
		try {

			File path = new File("");
			String savePath = path.getCanonicalPath() + "/res/saves/entities.ens";
			FileOutputStream file = new FileOutputStream(savePath);
			
			for (Level l : Game.game.levels) {
				if(l.id>Game.game.getLevel().id)
					break;
				if(!(l==null))
					for (Entity e : l.entities) {
						if(e instanceof Player) continue;
						file.write(getID(e));
						file.write(e.x>>4);
						file.write(e.y>>4);
						switch(getID(e))
						{
						case 0:
							file.write(((BluePot)e).restoring());
							break;
						case 1:
							file.write(((RedPot)e).hp());
							break;
						case 2:
							file.write(((Scroll)e).spell());
							break;
						/*case 3:
							file.write(((Mine)e).cd>>1);
							break;
						case 4:
							file.write(((Projectile)e).xv);
							break;*/
						case 10:
						case 11:
						case 12:
						case 13:
							file.write(((Boss)e).hp);
							break;
						}
						
						file.write(Tentit);
					}
				file.write(Tlevel);
			}
			
			file.write(Tend);
			
			file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadEntities()
	{
		try
		{
			InputStream file = SavedLoader.class.getResourceAsStream("/saves/entities.ens");
			
			byte l=0;
			int r;
			
			ArrayList <Integer>a=new ArrayList<Integer>();
			while (true)
			{
				r=file.read();
				if(r==Tentit)	// Tentit= end of an entity: time to put it in the level
				{
					Level level=Game.game.getLevel(l);
					
					a.set(1, a.get(1)<<4);	// x and y of an entity are written in tiles
					a.set(2, a.get(2)<<4);	// 
					
					switch(a.get(0))
					{
					case 0:
						level.add(new BluePot(a.get(1),a.get(2),a.get(3)));
						break;
					case 1:
						level.add(new RedPot(a.get(1),a.get(2),a.get(3)));
						break;
					case 2:
						level.add(new Scroll(a.get(1),a.get(2),Spell.getSpell(a.get(3))));
						break;
					case 5:
						level.add(new Bat(a.get(1),a.get(2)));
						break;
					case 6:
						level.add(new Devil(a.get(1),a.get(2)));
						break;
					case 7:
						level.add(new DevilL(a.get(1),a.get(2)));
						break;
					case 8:
						level.add(new Ghost(a.get(1),a.get(2)));
						break;
					case 9:
						level.add(new Jelly(a.get(1),a.get(2)));
						break;
					case 10:
						level.add(new BBat(a.get(1),a.get(2),a.get(3)));
						break;
					case 11:
						level.add(new BDevil(a.get(1),a.get(2),a.get(3)));
						break;
					case 12:
						level.add(new BJelly(a.get(1),a.get(2),a.get(3)));
						break;
					case 13:
						level.add(new Spirit(a.get(1),a.get(2),a.get(3)));
						break;
					}
					a.clear();
				} else if(r==Tlevel)	// TLevel= end of a level, time to set and clear the next level
				{
					l++;
					Game.game.getLevel(l).entities.clear();
				}
				else if(r==Tend)	// Tend= end of the file
					break;
				else				// all the non-terminal values are stored in a
					a.add(r);
			}
			file.close();
		} catch (Exception e)
		{ e.printStackTrace(); }
	}
	
	private static byte getID(Entity e)
	{
		if(e instanceof BluePot) return 0;
		if(e instanceof RedPot) return 1;
		if(e instanceof Scroll) return 2;
		if(e instanceof Mine) return 3;
		
		if(e instanceof Projectile) return 4;
		if(e instanceof Bat) return 5;
		if(e instanceof Devil) return 6;
		if(e instanceof DevilL) return 7;
		
		if(e instanceof Ghost) return 8;
		if(e instanceof Jelly) return 9;
		if(e instanceof BBat) return 10;
		if(e instanceof BDevil) return 11;
		
		if(e instanceof BJelly) return 12;
		if(e instanceof Spirit) return 13;
		
		return 0;
	}
}
