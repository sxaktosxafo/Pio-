package com.ias.chick.level;

import com.ias.chick.entity.mob.monster.Ghost;


// generated using the algorithm used in mujahid (7drl 2012)

public class GraveyardLevel extends RandomGenLevel
{
	int emp=7;		// parte incamminabile 7
	int floor=0;	// pavimento 0
	int supp=4;		// pavimento momentaneo delle stanze

	int mxw=10, mxh=10;	// max and min width and height
	int mnw=6, mnh=6;
	
	int nRooms; 	// nRooms
	
	private int px,py;	// x and y of the previous room's center
	
	public GraveyardLevel(int w, int h, int nr,byte id)
	{
		super(w,h,id);
		this.x1=random.nextInt(w-10)+5;
		this.y1=random.nextInt(h-10)+5;
		this.nRooms=nr;
		generateLevel();
		setID(x1,y1,12);
		setID(x2,y2,13);
		this.voidTile=emp;
	}
	
	protected void generateLevel()
	{
		// riempie tutto 
		for (int i=0; i<tilesInt.length;i++)
			tilesInt[i]=emp;
		
		makeRoom(x1-4,y1-2,8,4);	// stanza che contiene il player
		px=x1;
		py=y1;
		nRooms--;
		
		while(nRooms>0)
		{
			int x,y,w,h,cx,cy;
			x=randBet(0,getWidth());
			y=randBet(0,height);
			w=randBet(mnw,mxw);
			h=randBet(mnh,mxh);
			// center x and y
			cx=w/2+x;
			cy=h/2+y;
			
			if(makeRoom(x,y,w,h))	// make the room
			{
				// make corridor
				if (random.nextBoolean())
				{
					makeHCorr(py,px,cx);
					makeVCorr(cx,py,cy);
				}
				else
				{
					makeVCorr(px,py,cy);
					makeHCorr(cy,px,cx);
				}
				
				px=cx;
				py=cy;
				nRooms--;
			}
		}
		
		x2=px;
		y2=py;
		
		refine();
	}
	
	// NB i muri della stanza sono uguali a tutto il resto
	private boolean makeRoom(int x, int y, int w, int h)
	{
		if(x<=0 || y<=0 || x+w>=getWidth()-1 || y+h>=height-1) return false;	// se è fuori dalla mappa...
		for(int yy=y-1;yy<=h+y+1;yy++)
		{
			for(int xx=x-1;xx<=w+x+1;xx++)
				if(getID(xx,yy) == supp) return false;		// ... o se c'è già una stanza ritona F
		}
		
		// se va tutto bene costruisce la stanza
		for(int yy=y;yy<=h+y;yy++)
		{
			for(int xx=x;xx<=w+x;xx++)
				setID(xx,yy,supp);
		}
		
		// adds a grave and a ghost
		if (random.nextBoolean())
		{
			int gx=randBet(x, w+x);
			int gy=randBet(y, h+y);
			setID(gx,gy,19);
			this.add(new Ghost(gx<<4,gy<<4));
		}
		
		// e ritorna T
		return true;
	}
	
	/** makes a vertical corridor */
	private void makeVCorr(int x,int y0,int y1)
	{
		for(int y=Math.min(y0, y1); y<=Math.max(y0, y1); y++)
			setID(x,y,floor);
	}
	
	/** makes an horizontal corridor */
	private void makeHCorr(int y,int x0,int x1)
	{
		for(int x=Math.min(x0, x1); x<=Math.max(x0, x1); x++)
			setID(x,y,floor);
	}
	
	/** gives the dungeon a bettere apparence */
	private void refine()
	{
		for(int i=0;i<tilesInt.length;i++)
		{
			// supp sono le stanze
			if (tilesInt[i]==supp)
			{
				switch (random.nextInt(200))
				{
				case 0: tilesInt[i]=18; break;	// PFlower
				case 1: tilesInt[i]=18; break;
				default: tilesInt[i]=floor; break;
				}
			}
			else if (tilesInt[i]==emp)
			{
				if (random.nextInt(200)==0)
					tilesInt[i]=8;
			}
		}
	}
}
