package com.ias.chick.level;

import java.util.ArrayList;

import com.ias.chick.entity.mob.monster.DevilL;
import com.ias.chick.graphics.Sprite;

public class HellLevel extends CaveLevel
{
	private int fluid;
	
	public HellLevel(int w, int h, byte id)
	{
		super(w, h, 23, 24,id);
		this.stairsDown=Sprite.stairsDownH;
		this.stairsUp=Sprite.stairsUpH;
	}
	
	protected void generateLevel()
	{
		// 1st Fill the room with random floor, wall or lava tiles
		// 2nd do thing to make it all look like a cave
		// 3rd find the main cave and put the stairs in there
		
		fluid=21;
		
		// fills with walls
		for (int i=0;i<tilesInt.length;i++)
			tilesInt[i]=wall;
		
		// puts floor
		int c=0;
		while (c<tilesInt.length*35/100)
		{
			int h=random.nextInt(tilesInt.length);
			if (tilesInt[h]==wall)
			{
				tilesInt[h]=floor;
				c++;
			}
		}
		
		// puts lava
		c=0;
		while (c<tilesInt.length*25/100)
		{
			int h=random.nextInt(tilesInt.length);
			if (tilesInt[h]==wall)
			{
				tilesInt[h]=fluid;
				c++;
			}
		}
		
		// SECOND PART
		for (int i=0;i<10;i++)
		{
			for (int y=0;y<height;y++)
			{ for (int x=0;x<width;x++)
			{
			switch(mayDies(x,y))
			{
			case 1: setID(x,y,wall,tilesP); break;
			case 2: setID(x,y,floor,tilesP); break;
			case 3: setID(x,y,fluid,tilesP); break;
			}
			} }
			
			for (int ii=0;ii<tilesInt.length;ii++)
				tilesInt[ii]=tilesP[ii];
		}
		
		// THIRD PART
		
		// there is an arraylist of arraylists, each arraylist is a sub-cave

		for (int t = 0; t < tilesInt.length; t++) {
			if (ok(t)) {
				caves.add(new ArrayList<Integer>());
				sortCaves(t);
			}
		}

		// find the biggest cave
		int[] lung = new int[caves.size()];
		int max = 0, maxi = 0; // the max number and the index of it
		for (int o = 0; o < caves.size(); o++)
		{
			int s = caves.get(o).size();
			if (s > max)
			{
				max = s;
				maxi = o;
			}
			lung[o] = s;
		}

		/*
		 * Debugging things for (int h:lung) System.out.println(h);
		 * System.out.println("max:  "+ max); System.out.println("maxi: "+
		 * maxi); //
		 */

		// two poins (gatto and cane), the stairs
		int gatto = randFromAL(caves.get(maxi));
		int cane = gatto;
		while (cane == gatto)
			cane = randFromAL(caves.get(maxi));

		//tilesInt[gatto] = 12;
		tilesInt[cane] = 13;

		setStairs(gatto, cane);
		
		for (int i=0;i<10;i++)
		{
			int g1=randFromAL(caves.get(maxi));
			this.add(new DevilL(iToX(g1)<<4,iToY(g1)<<4));
		}
	}
	
	/**Returns 1 if becomes a wall, 2 if becomes a floor, 3 if becomes lava */
	protected int mayDies(int x, int y)
	{
		int adLava=0; // number of adiacent lava
		
		if (getID(x-1,y-1)==fluid) adLava++;
		if (getID(x,y-1)==fluid)   adLava++;
		if (getID(x+1,y-1)==fluid) adLava++;
		
		if (getID(x-1,y)==fluid)   adLava++;
		if (getID(x,y)==fluid)     adLava++;
		if (getID(x+1,y)==fluid)   adLava++;
		
		if (getID(x-1,y+1)==fluid) adLava++;
		if (getID(x,y+1)==fluid)   adLava++;
		if (getID(x+1,y+1)==fluid) adLava++;
		
		int adWalls=0; // number of adiacent walls
		
		if (getID(x-1,y-1)==wall) adWalls++;
		if (getID(x,y-1)==wall)   adWalls++;
		if (getID(x+1,y-1)==wall) adWalls++;
		
		if (getID(x-1,y)==wall)   adWalls++;
		if (getID(x,y)==wall)     adWalls++;
		if (getID(x+1,y)==wall)   adWalls++;
		
		if (getID(x-1,y+1)==wall) adWalls++;
		if (getID(x,y+1)==wall)   adWalls++;
		if (getID(x+1,y+1)==wall) adWalls++;
		
		if (adWalls>4) return 1;
		if (adLava>3) return 3;
		else return 2;
	}

}
