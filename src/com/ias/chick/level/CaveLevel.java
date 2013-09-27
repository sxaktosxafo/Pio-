package com.ias.chick.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.ias.chick.entity.mob.monster.Bat;
import com.ias.chick.entity.mob.monster.Ghost;
import com.ias.chick.graphics.Sprite;

public class CaveLevel extends RandomGenLevel
{
	//private Random random=new Random();
	
	public int wall;
	public int floor;
	
	ArrayList <Integer> caveTile=new ArrayList <Integer>(); 
	ArrayList <ArrayList <Integer>> caves=new ArrayList <ArrayList <Integer>>();
	
	protected int[] tilesP;
	
	public CaveLevel(int w, int h,byte id)
	{
		super(w, h,id);
		this.floor=14;
		this.wall=15;
		tilesP=new int[w*h];
		this.voidTile=wall;
		this.stairsDown=Sprite.stairsDownC;
		this.stairsUp=Sprite.stairsUpC;
		generateLevel();
	}
	
	public CaveLevel(int w, int h, int floor, int wall, byte id)
	{
		super(w, h,id);
		this.floor=floor;
		this.wall=wall;
		tilesP=new int[w*h];
		this.voidTile=wall;
		this.stairsDown=Sprite.stairsDownC;
		this.stairsUp=Sprite.stairsUpC;
		generateLevel();
		
	}
	
	protected void generateLevel()
	{
		// 1st Fill the room with random floor or wall tiles
		// 2nd do thing to make it all look like a cave
		// 3rd find the main cave and put the stairs in there
		
		// FIRST PART
		
		for (int i=0;i<tilesInt.length;i++)
			tilesInt[i]=wall;
		
		int c=0;
		while (c<tilesInt.length*67/100)
		{
			int h=random.nextInt(tilesInt.length);
			if (tilesInt[h]!=floor)
			{
				tilesInt[h]=floor;
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
			case 0: setID(x,y,getID(x,y),tilesP); break;
			case 1: setID(x,y,wall,tilesP); break;
			case 2: setID(x,y,floor,tilesP); break;
			}
			} }
			
			for (int ii=0;ii<tilesInt.length;ii++)
				tilesInt[ii]=tilesP[ii];
		}
		
		// THIRD PART
		
		// there is an arraylist of arraylists, each arraylist is a sub-cave
		
		for(int t=0;t<tilesInt.length;t++)
		{
			if (ok(t))
			{
				caves.add(new ArrayList <Integer>());
				sortCaves(t);
			}
		}
		
		// find the biggest cave
		int[] lung=new int[caves.size()];
		int max=0,maxi=0;	//the max number and the index of it
		for (int o=0;o<caves.size();o++)
		{
			int s=caves.get(o).size();
			if (s>max)
			{
				max=s;
				maxi=o;
			}
			lung[o]=s;
		}
		
		/* Debugging things
		for (int h:lung)
			System.out.println(h);
		System.out.println("max:  "+ max);
		System.out.println("maxi: "+ maxi);
		//*/
		
		// two poins (gatto and cane), the stairs
		int gatto=randFromAL(caves.get(maxi));
		int cane=gatto;
		while(cane==gatto)
			cane=randFromAL(caves.get(maxi));
		
		tilesInt[gatto]=12;
		tilesInt[cane]=13;
		
		setStairs(gatto,cane);
		
		// 10 bats
		for (int i=0;i<10;i++)
		{
			int g1=randFromAL(caves.get(maxi));
			this.add(new Bat(iToX(g1)<<4,iToY(g1)<<4));
		}
		
	}
	
	/**Given a tile position in the tilesInt, adds to the arrayList of the cave
	 * it belongs to itself and his adiacents tiles*/
	protected void sortCaves(int t)
	{
		if (ok(t))
		{
			caves.get(caves.size()-1).add(t);
			
			if ((t+1)%width != 0)	sortCaves(t+1);
			if (t>=width)			sortCaves(t-width);
			if (t%width != 0)		sortCaves(t-1);;
			if (t/width < height-1)	sortCaves(t+width);
		}
	}
	
	/**Returns true if the tile is an unvisited floor tile */
	protected boolean ok(int t)
	{
		if (tilesInt[t]!=floor) return false;
		for (ArrayList <Integer> a:caves)
			for (int i:a)
				if (i==t) return false;
		return true;
	}
	
	/**Returns 0 if it doesn't change, 1 if becomes a wall, 2 if becomes a floor */
	protected int mayDies(int x,int y)
	{
		int adWalls=0; // number of adiacent walls
		
		if (getID(x-1,y-1)!=floor) adWalls++;
		if (getID(x,y-1)!=floor)   adWalls++;
		if (getID(x+1,y-1)!=floor) adWalls++;
		
		if (getID(x-1,y)!=floor)   adWalls++;
		if (getID(x,y)!=floor)     adWalls++;
		if (getID(x+1,y)!=floor)   adWalls++;
		
		if (getID(x-1,y+1)!=floor) adWalls++;
		if (getID(x,y+1)!=floor)   adWalls++;
		if (getID(x+1,y+1)!=floor) adWalls++;
		
		if (adWalls>4) return 1;
		if (adWalls<3) return 2;
		
		return 0;
	}
}
