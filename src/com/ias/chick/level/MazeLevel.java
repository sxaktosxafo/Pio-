package com.ias.chick.level;

import java.util.Vector;

import com.ias.chick.entity.item.Emblem;
import com.ias.chick.graphics.Sprite;

public class MazeLevel extends RandomGenLevel
{
	
	private int wall=3;
	private int corr=0;

	private int[] cells;	// 0=empty; 1=checked or ok;
	private int cw,ch;		// width and height of cells

	Vector<Integer> checked = new Vector<Integer>();

	public MazeLevel(int w, int h,byte id)
	{
		super(w*2+1, h*2+1,id);
		cells=new int[w*h];
		cw=w;
		ch=h;
		generateLevel();
		this.voidTile=0;
	}
	
	public MazeLevel(int w, int h,int floor, int wall,byte id)
	{
		super(w*2+1, h*2+1,id);
		cells=new int[w*h];
		cw=w;
		ch=h;
		this.wall=wall;
		this.corr=floor;
		generateLevel();
		this.voidTile=wall;
	}
	
	protected void generateLevel()
	{
		// riempio tutto di muro
		for (int i=0;i<tilesInt.length;i++)
			tilesInt[i]=wall;
		
		makeMaze();
		addThings();	
	}
	
	protected void makeMaze()
	{
		// tutto vuoto!
		for (int i=0;i<cells.length;i++)
			cells[i]=0;

		
		// scelgo una casella accaso, la metto vista
		Integer a=random.nextInt(cells.length);
		cells[a]=1;
		checked.add(a);
		
		while(checked.size()>0)
		{
			a=randFromV(checked);
			
			// 2
			//4 1
			// 8

			byte gh=0;
			
			// we check inside the grid
			if ((a+1)%cw != 0)	if(cells[a+1]==0)  gh+=1;
			if (a>=cw)			if(cells[a-cw]==0) gh+=2;
			if (a%cw != 0)		if(cells[a-1]==0)  gh+=4;
			if (a/cw < ch-1)	if(cells[a+cw]==0) gh+=8;
			
			
			if (gh==0)
				checked.remove(a);
			else
			{
				int b=0;
				
				switch(randB(gh))
				{
				case 1: b=a+1;  break;
				case 2: b=a-cw; break;
				case 4: b=a-1;  break;
				case 8: b=a+cw; break;
				}
				checked.add(b);
				cells[b]=1;
				
				// link a e b
				
				link(a%cw,a/cw,b%cw,b/cw);
			}
		}
		
		System.out.println("bella!");
		
		for (int yy=0;yy<ch;yy++)
		{
			for (int xx=0;xx<cw;xx++)
				setID(xx*2+1,yy*2+1,corr,tilesInt);
		}
	}
	
	private void addThings()
	{
		// the stairs!
		x2=cw*2-1;
		y2=ch*2-1;
		
		x1=0*2+1;
		y1=0*2+1;
		
		this.add(new Emblem(x2<<4,y2<<4));
		
		//setID(x1,y1,12,tilesInt);		
		//setID(x2,y2,13,tilesInt);
	}
	
	
	
	/** Returns a random one in the 1s of the byte (4 bits)*/
	private byte randB(byte b)
	{
		byte a=0;
		while ((a&b)==0)
		{
			switch(random.nextInt(4))
			{
			case 0: a=1; break;
			case 1: a=2; break;
			case 2: a=4; break;
			case 3: a=8; break;
			}
		}
		return a;
	}
	
	/** Given x and y of 2 adiacent cell in the small graph, connects them in the big one */
	private void link (int x1, int y1, int x2, int y2)
	{
		x1=x1*2+1;	// Converts in big graph
		y1=y1*2+1;
		x2=x2*2+1;
		y2=y2*2+1;
		
		int xf=(x1+x2)/2;	// x and y to fill
		int yf=(y1+y2)/2;
		
		setID(xf,yf,corr,tilesInt);
	}
}