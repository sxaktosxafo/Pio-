package com.ias.chick.level.tile;

import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;

public class Tile
{
	public int x,y;
	public Sprite sprite;
	public final int ID;
	
	public static Tile[] tiles=new Tile[250];
	
	public static Tile grass=new GrassTile(Sprite.grass,0);
	public static Tile flowerY=new GrassTile(Sprite.flowerY,1);
	public static Tile flowerR=new GrassTile(Sprite.flowerR,2);
	public static Tile flowerP=new GrassTile(Sprite.flowerP,18);
	public static Tile grave=new GrassTile(Sprite.grave,19);
	public static Tile rock=new RockTile(Sprite.rock,3);
	
	public static Tile floor1=new FloorTile(Sprite.floor1,4);
	public static Tile floor2=new FloorTile(Sprite.floor2,5);
	public static Tile floor3=new FloorTile(Sprite.floor3,6);
	
	public static Tile wall_=new WallTile(Sprite.wall,7);
	public static Tile wallM=new WallTile(Sprite.wallM,8);
	public static Tile wallD=new WallTile(Sprite.wallD,9);
	public static Tile bricks=new WallTile(Sprite.wallR,11);
	
	public static Tile grassWall=new GrassTile(Sprite.grassWall,10);
	
	public static Tile caveFloor=new GrassTile(Sprite.caveFloor,14);
	public static Tile caveWall=new RockTile(Sprite.caveWall,15);
	public static Tile caveFloorH=new GrassTile(Sprite.caveFloorH,23);
	public static Tile caveWallH=new RockTile(Sprite.caveWallH,24);
	
	public static Tile sokobanFloor=new FloorTile(Sprite.sokobanFloor,25);
	public static Tile sokobanRock=new RockTile(Sprite.sokobanRock,26);
	public static Tile sokobanHole=new RockTile(Sprite.sokobanHole,27);
	
	public static Tile stairsUp= new PortalTile(Sprite.stairsUp,12);
	public static Tile stairsDown= new PortalTile(Sprite.stairsDown,13);
	
	public static LockTile blueLock=new LockTile(Sprite.blueLock,Sprite.blueLockO,16,0);
	public static LockTile redLock=new LockTile(Sprite.redLock,Sprite.redLockO,22,1);
	public static LockTile brownLock=new LockTile(Sprite.brownLock,Sprite.brownLockO,28,2);
	
	public static Tile water=new FluidTile(Sprite.water1,Sprite.water2,20);
	public static Tile lava=new FluidTile(Sprite.lava1,Sprite.lava2,21);
	
	
	public static Tile voidTile=new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite s, int id)
	{
		this.sprite=s;
		this.ID=id;
		if (tiles[id]==null)
			tiles[id]=this;
		else
			System.out.println("AARGH! 2 tiles with the same id!");
	}
	
	public void render (int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
	
	public boolean solid()
	{
		return false;
	}
}
