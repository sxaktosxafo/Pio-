package com.ias.chick.level;
// Ispirato da Solarnus (roguebasin)


import java.util.Random;

public class DunLevel extends Level
{
	Random random=new Random();
	int sx, sy;
	int emp=6;		// vuoto
	int wall=10;	// muro delle stanze
	int floor=0;	// pavimento delle stanze
	int corr=4;		// pavimento dei corridoi
	int door=1;
	int mxw=10, mxh=10;
	int mnw=6, mnh=6;
	
	public DunLevel(int w, int h, int sx, int sy,byte id)
	{
		super(w,h,id);
		this.sx=sx;
		this.sy=sy;
		generateLevel();
	}
	
	protected void generateLevel()
	{
		for (int i=0; i<tilesInt.length;i++)	// rende tutto vuoto
			tilesInt[i]=emp;
		
		while (makeRoom(sx-5,sy-3,10,6));
		
		
		int co=0;
		while (co<10)
		{
			int x,y,w,h;
			x=random.nextInt(getWidth());
			y=random.nextInt(height);
			w=randBet(mnw,mxw);
			h=randBet(mnh,mxh);
			if(makeRoom(x,y,w,h)) co++;
		}
	}
	
	//  1
	// 2 0
	//  3
	
	private boolean makeRoom(int x, int y, int w, int h)
	{
		if(x<0 || y<0 || x+w>=getWidth() || y+h>=height) return false;	// se è fuori dalla mappa...
		for(int yy=y;yy<=h+y;yy++)
		{
			for(int xx=x;xx<=w+x;xx++)
			{
				if(getID(xx,yy) != emp) return false;		// ... o se c'è già roba ritona F
			}
		}
		
		// se va tutto bene costruisce la stanza
		for(int yy=y;yy<=h+y;yy++)
		{
			for(int xx=x;xx<=w+x;xx++)
			{
				if(yy==y || yy==h+y || xx==x || xx==w+x) setID(xx,yy,wall);	//di muro l'estremita
				else setID(xx,yy,floor);	// di floor la parte interna
			}
		}
		
		int nDoors;
		int xx,yy;
		if (w*h<50) nDoors=1;	//quante porte?
		else if (w*h<85) nDoors=2;
		else nDoors=3;
		
		
		// mette le porte: piglia una parete a random e agisce di conseguenza
		
		// 1
		//2 0
		// 3
		
		while(nDoors>0)
		{
			boolean ok=false;	// controlla la casella davanti alla porta
			
			switch(random.nextInt(3))
			{
			case 0:
				xx=w+x;
				yy=randBet(y+1, h+y-1);
				if (getID(xx+1,yy)==emp)
				{
					ok= true;
					setID(xx+1,yy,corr);
				}
				break;
			case 1:
				xx=randBet(x+1,w+x-1);
				yy=y;
				if (getID(xx,yy-1)==emp) {
					ok= true;
					setID(xx,yy-1,corr);
				}
				break;
			case 2:
				xx=x;
				yy=randBet(y+1, h+y-1);
				if (getID(xx-1,yy)==emp) {
					ok= true;
					setID(xx-1,yy,corr);
				}
				break;
			case 3:
				xx=randBet(x+1,w+x-1);
				yy=h+y;
				if (getID(xx,yy+1)==emp) {
					ok= true;
					setID(xx,yy+1,corr);
				}
				break;
			default:
				xx=x+1;
				yy=y;
				break;
			}
			
			if (getID(xx,yy)==wall && ok)	// vede se e' un muro
			{
				setID(xx,yy,door);
				nDoors--;
			}
		}
		
		
		
		// e ritorna T
		return true;
	}
	
	private int getID(int x, int y)
	{
		if (x<0||y<0||x>=getWidth()||y>=height) return 27;	// per evitare che ritorni il tile dall'altra parte dello schermo
		return tilesInt[x+y*getWidth()];
	}
	
	private void setID(int x, int y, int a)
	{
		if (x<0||y<0||x>=getWidth()||y>=height) return;	// per evitare che venga scritto il tile dall'altra parte dello schermo
		tilesInt[x+y*getWidth()]=a;
	}
	
	// inclusi gli estremi
	private int randBet(int mn, int mx)
	{
		return random.nextInt(mx-mn+1)+mn;
	}
}
