package com.ias.chick.level;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public abstract class RandomGenLevel extends Level
{
	protected Random random=new Random();

	public RandomGenLevel(int w, int h,byte id)
	{
		super(w, h, id);
	}
	
	/** Returns the id in this position*/
	protected int getID(int x, int y)
	{
		if (x<0||y<0||x>=getWidth()||y>=height) return voidTile;	// per evitare che ritorni il tile dall'altra parte dello schermo
		return tilesInt[x+y*getWidth()];
	}
	
	/** Sets the id in this position to the given one */
	protected void setID(int x, int y, int id)
	{
		if (x<0||y<0||x>=getWidth()||y>=height) return;	// per evitare che venga scritto il tile dall'altra parte dello schermo
		tilesInt[x+y*getWidth()]=id;
	}
	
	/** Sets the id in this position in the given array to the given one */
	protected void setID(int x, int y, int a, int[] til)
	{
		if (x<0||y<0||x>=width||y>=height) return;	// per evitare che venga scritto il tile dall'altra parte dello schermo
		til[x+y*width]=a;
	}
	
	protected int getID(int x, int y, int[] til)
	{
		if (x<0||y<0||x>=getWidth()||y>=height) return 27;	// per evitare che ritorni il tile dall'altra parte dello schermo
		return til[x+y*getWidth()];
	}
	
	/**Set x1,x2,y1,y2 given the array position of the stairs */
	protected void setStairs(int su,int sd)
	{
		int y=0;
		while (su>getWidth())
		{ su-=getWidth(); y++; }
		x1=su;
		y1=y;
		
		y=0;
		while (sd>getWidth())
		{ sd-=getWidth(); y++; }
		x2=sd;
		y2=y;
	}
	
	/** Returns an int between mn and mx (including the extremes) */
	protected int randBet(int mn, int mx)
	{
		return random.nextInt(mx-mn+1)+mn;
	}
	
	/** returns a random element form a vector*/
	protected int randFromV(Vector<Integer> v)
	{
		return v.get(random.nextInt(v.size()));
	}
	
	/** returns a random element form an arrayList*/
	protected int randFromAL(ArrayList<Integer> a)
	{
		return a.get(random.nextInt(a.size()));
	}
	
	/** Returns a random int from an array*/
	protected int randFromArr(int[] a)
	{
		return a[random.nextInt(a.length)];
	}
}
