package com.ias.chick.level;

import com.ias.chick.entity.mob.monster.Jelly;

public class GrassLevel extends RandomGenLevel
{
	public int grass;
	public int rock;
	public int nRocks;
	public int nJellies;
	
	public GrassLevel(int w, int h, byte id)
	{
		super(w, h, id);
		
		grass=0;
		rock=3;
		nRocks=20;
		nJellies=3;
		this.voidTile=0;
		
		generateLevel();
	}
	
	public GrassLevel(int w, int h, byte id, int nr, int nj)
	{
		super(w, h, id);
		
		grass=0;
		rock=3;
		nRocks=nr;
		nJellies=nj;
		this.voidTile=0;
		
		generateLevel();
	}
	
	protected void generateLevel()
	{
		for (int y=0;y<height;y++)
			for(int x=0;x<width;x++)
				if(x==0 || x==width-1 || y==0 || y==height-1)
					setID(x,y,rock);
				else
					setID(x,y,grass);
		
		int c=0;
		while(c<nRocks)
		{
			int iop=random.nextInt(tilesInt.length);
			if(tilesInt[iop]!=rock)
			{
				c++;
				tilesInt[iop]=rock;
			}
		}
		
		c=0;
		while(c<nJellies)
		{
			int iop=random.nextInt(tilesInt.length);
			if(tilesInt[iop]!=rock)
			{
				c++;
				this.add(new Jelly(iToX(iop)<<4,iToY(iop)<<4));
			}
		}
		
		int xu=0,yu=0;
		while(getID(xu,yu)!=grass)
		{
			xu=random.nextInt(width);
			yu=random.nextInt(height);
		}
		x1=xu;
		y1=yu;
		int xd=0,yd=0;
		while(getID(xd,yd)!=grass && (xd!=xu || yd!=yu))
		{
			xd=random.nextInt(width);
			yd=random.nextInt(height);
		}
		x2=xd;
		y2=yd;
		
		setID(x1,y1,12);
		setID(x2,y2,13);
	}

}
