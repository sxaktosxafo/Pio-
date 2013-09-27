package com.ias.chick.graphics;

public class Sprite
{
	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;

// TILES
	
	public static Sprite grass=new Sprite(16,1,0,SpriteSheet.tiles);
	public static Sprite flowerY=new Sprite(16,1,1,SpriteSheet.tiles);
	public static Sprite flowerR=new Sprite(16,1,2,SpriteSheet.tiles);
	public static Sprite flowerP=new Sprite(16,1,3,SpriteSheet.tiles);
	public static Sprite grave=new Sprite(16,1,4,SpriteSheet.tiles);
	
	public static Sprite floor1=new Sprite(16,0,0,SpriteSheet.tiles);
	public static Sprite floor2=new Sprite(16,0,1,SpriteSheet.tiles);
	public static Sprite floor3=new Sprite(16,0,2,SpriteSheet.tiles);
	
	public static Sprite wall=new Sprite(16,2,0,SpriteSheet.tiles);
	public static Sprite wallM=new Sprite(16,2,1,SpriteSheet.tiles);	// mossy
	public static Sprite wallD=new Sprite(16,2,2,SpriteSheet.tiles);	// dark
	public static Sprite wallR=new Sprite(16,2,3,SpriteSheet.tiles);	// bricks
	
	public static Sprite rock=new Sprite(16,3,0,SpriteSheet.tiles);
	public static Sprite grassWall=new Sprite(16,3,1,SpriteSheet.tiles);
	
	public static Sprite caveFloor=new Sprite(16,5,0,SpriteSheet.tiles);
	public static Sprite caveWall=new Sprite(16,5,1,SpriteSheet.tiles);
	public static Sprite caveFloorH=new Sprite(16,5,2,SpriteSheet.tiles);
	public static Sprite caveWallH=new Sprite(16,5,3,SpriteSheet.tiles);
	
	public static Sprite stairsUp=new Sprite(16,4,1,SpriteSheet.tiles);
	public static Sprite stairsDown=new Sprite(16,4,0,SpriteSheet.tiles);
	public static Sprite stairsUpC=new Sprite(16,4,3,SpriteSheet.tiles);	// Cave
	public static Sprite stairsDownC=new Sprite(16,4,2,SpriteSheet.tiles);
	public static Sprite stairsUpH=new Sprite(16,4,5,SpriteSheet.tiles);	// Hell
	public static Sprite stairsDownH=new Sprite(16,4,4,SpriteSheet.tiles);
	public static Sprite stairsUpS=new Sprite(16,4,7,SpriteSheet.tiles);	// Sokoban
	public static Sprite stairsDownS=new Sprite(16,4,6,SpriteSheet.tiles);
	
	public static Sprite sokobanFloor=new Sprite(16,9,0,SpriteSheet.tiles);
	public static Sprite sokobanRock=new Sprite(16,9,1,SpriteSheet.tiles);
	public static Sprite sokobanHole=new Sprite(16,9,2,SpriteSheet.tiles);
	
	public static Sprite blueLock=new Sprite(16,6,0,SpriteSheet.tiles);
	public static Sprite blueLockO=new Sprite(16,6,1,SpriteSheet.tiles);
	
	public static Sprite redLock=new Sprite(16,6,2,SpriteSheet.tiles);
	public static Sprite redLockO=new Sprite(16,6,3,SpriteSheet.tiles);
	
	public static Sprite brownLock=new Sprite(16,6,4,SpriteSheet.tiles);
	public static Sprite brownLockO=new Sprite(16,6,5,SpriteSheet.tiles);
	
	public static Sprite water1=new Sprite(16,7,0,SpriteSheet.tiles);
	public static Sprite water2=new Sprite(16,7,1,SpriteSheet.tiles);
	
	public static Sprite lava1=new Sprite(16,8,0,SpriteSheet.tiles);
	public static Sprite lava2=new Sprite(16,8,1,SpriteSheet.tiles);
	
	public static Sprite portalBlue=new Sprite(16,10,0,SpriteSheet.tiles);
	public static Sprite portalRed=new Sprite(16,10,1,SpriteSheet.tiles);
	public static Sprite portalRedOff=new Sprite(16,10,2,SpriteSheet.tiles);
	
	public static Sprite voidSprite=new Sprite(16,0x1B87E0);
	
// MOBS
	
	/* BIG player
	public static Sprite player_forwardb=new Sprite(32,1,0,SpriteSheet.mobs);
	public static Sprite player_backb=new Sprite(32,0,0,SpriteSheet.mobs);
	public static Sprite player_sideb=new Sprite(32,2,0,SpriteSheet.mobs);
	
	public static Sprite player_forward_1b=new Sprite(32,1,1,SpriteSheet.mobs);
	public static Sprite player_back_1b=new Sprite(32,0,1,SpriteSheet.mobs);
	public static Sprite player_side_1b=new Sprite(32,2,1,SpriteSheet.mobs);
	*/
	
	public static Sprite player_forward=new Sprite(16,1,0,SpriteSheet.mobss);
	public static Sprite player_back=new Sprite(16,0,0,SpriteSheet.mobss);
	public static Sprite player_side=new Sprite(16,2,0,SpriteSheet.mobss);
	
	public static Sprite player_forward_1=new Sprite(16,1,1,SpriteSheet.mobss);
	public static Sprite player_back_1=new Sprite(16,0,1,SpriteSheet.mobss);
	public static Sprite player_side_1=new Sprite(16,2,1,SpriteSheet.mobss);
	
	public static Sprite player_forwardA=new Sprite(16,1,5,SpriteSheet.mobss);
	public static Sprite player_backA=new Sprite(16,0,5,SpriteSheet.mobss);
	public static Sprite player_sideA=new Sprite(16,2,5,SpriteSheet.mobss);
	
	public static Sprite blueJ1=new Sprite(16,0,2,SpriteSheet.mobss);
	public static Sprite blueJ2=new Sprite(16,1,2,SpriteSheet.mobss);
	
	public static Sprite ghost1=new Sprite(16,2,2,SpriteSheet.mobss);
	public static Sprite ghost2=new Sprite(16,3,2,SpriteSheet.mobss);
	
	public static Sprite bat1=new Sprite(16,4,2,SpriteSheet.mobss);
	public static Sprite bat2=new Sprite(16,5,2,SpriteSheet.mobss);
	
	public static Sprite devil1=new Sprite(16,2,3,SpriteSheet.mobss);
	public static Sprite devil2=new Sprite(16,3,3,SpriteSheet.mobss);
	
	public static Sprite devilL1=new Sprite(16,0,3,SpriteSheet.mobss);
	public static Sprite devilL2=new Sprite(16,1,3,SpriteSheet.mobss);

// BOSSES
	public static Sprite bVoidSprite=new Sprite(32,0xFFFF00FF);
	
	public static Sprite guy1=new Sprite(32,3,0,SpriteSheet.mobs);
	public static Sprite guy2=new Sprite(32,4,0,SpriteSheet.mobs);
	public static Sprite guyA=new Sprite(32,5,0,SpriteSheet.mobs);
	
	public static Sprite bbat1=new Sprite(32,0,0,SpriteSheet.boss);
	public static Sprite bbat2=new Sprite(32,1,0,SpriteSheet.boss);
	public static Sprite bbatA=new Sprite(32,2,0,SpriteSheet.boss);
	
	public static Sprite bjelly1=new Sprite(32,3,0,SpriteSheet.boss);
	public static Sprite bjelly2=new Sprite(32,4,0,SpriteSheet.boss);
	public static Sprite bjellyA=new Sprite(32,5,0,SpriteSheet.boss);
	
	public static Sprite spirit1=new Sprite(32,0,1,SpriteSheet.boss);
	public static Sprite spirit2=new Sprite(32,1,1,SpriteSheet.boss);
	public static Sprite spiritA=new Sprite(32,2,1,SpriteSheet.boss);
	
	public static Sprite BDevil1=new Sprite(32,3,1,SpriteSheet.boss);
	public static Sprite BDevil2=new Sprite(32,4,1,SpriteSheet.boss);
	public static Sprite BDevilA=new Sprite(32,5,1,SpriteSheet.boss);
	
	public static Sprite Priest1=new Sprite(32,0,2,SpriteSheet.boss);
	public static Sprite Priest2=new Sprite(32,1,2,SpriteSheet.boss);
	public static Sprite PriestA=new Sprite(32,2,2,SpriteSheet.boss);
	
	
// ITEMS
	public static Sprite redPot=new Sprite(8,0,0,SpriteSheet.items);
	public static Sprite bluePot=new Sprite(8,1,0,SpriteSheet.items);
	public static Sprite redPotB=new Sprite(8,0,1,SpriteSheet.items);
	public static Sprite bluePotB=new Sprite(8,1,1,SpriteSheet.items);
	public static Sprite scroll=new Sprite(8,2,0,SpriteSheet.items);
	public static Sprite book=new Sprite(8,3,0,SpriteSheet.items);
	public static Sprite egg=new Sprite(8,5,0,SpriteSheet.items);
	public static Sprite emblem=new Sprite(8,2,1,SpriteSheet.items);
	public static Sprite levelUp=new Sprite(8,3,1,SpriteSheet.items);

// WEAPONS
	public static Sprite mine1=new Sprite(8,0,0,SpriteSheet.weapons);
	public static Sprite mine2=new Sprite(8,1,0,SpriteSheet.weapons);
	public static Sprite proj=new Sprite(8,3,0,SpriteSheet.weapons);
	public static Sprite projB=new Sprite(8,4,0,SpriteSheet.weapons);
	public static Sprite projR=new Sprite(8,0,1,SpriteSheet.weapons);
	
// SPELLS
	public static Sprite mineSpell=new Sprite(16,0,0,SpriteSheet.spells);
	public static Sprite healSpell=new Sprite(16,1,0,SpriteSheet.spells);
	public static Sprite shieldSpell=new Sprite(16,2,0,SpriteSheet.spells);
	public static Sprite tpSpell=new Sprite(16,3,0,SpriteSheet.spells);
	public static Sprite shoot=new Sprite(16,4,0,SpriteSheet.spells);
	public static Sprite speed=new Sprite(16,0,1,SpriteSheet.spells);
	
	public Sprite(int size, int x,int y, SpriteSheet sheet)
	{
		this.SIZE=size;
		pixels=new int[SIZE*SIZE];
		this.x=x*size;
		this.y=y*size;
		this.sheet=sheet;
		load();
	}
	
	public Sprite(int size, int p, SpriteSheet sheet)
	{
		this.SIZE=size;
		pixels=new int[SIZE*SIZE];
		this.x=(p%(sheet.SIZE/this.SIZE))*this.SIZE;
		this.y=(p/(sheet.SIZE/this.SIZE))*this.SIZE;
		this.sheet=sheet;
		load();
	}
	
	public Sprite(int size,int color)
	{
		this.SIZE=size;
		pixels=new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color)
	{
			for(int i=0;i<SIZE*SIZE;i++)
				pixels[i]=color;
	}

	private void load()
	{
		for (int y=0;y<SIZE;y++)
		{
			for (int x=0;x<SIZE;x++)
			{
				pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
			}
		}
	}
}
