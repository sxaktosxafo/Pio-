package com.ias.chick.level;

import com.ias.chick.entity.mob.monster.boss.Boss;
import com.ias.chick.level.tile.LockTile;
import com.ias.chick.level.tile.Tile;

public class BossLevel extends Level
{
	
	private Boss boss;
	private int floor;
	private int wall;
	private int lock;

	public BossLevel(String path, Boss boss, int floor, int wall, int lock, int voidT, byte id) {
		super(path, id);
		this.boss=boss;
		this.floor=floor;
		this.wall=wall;
		this.lock=lock;
		this.voidTile=voidT;
		this.add(boss);
		this.boss.x=15<<4;
		this.boss.y=15<<4;
		tilesToInt();
	}
	
	public void tilesToInt()
	{
		for (int i=0;i<tilesInt.length;i++)
			switch(tilesInt[i])
			{
			case 0xFFFF00FF:
				tilesInt[i]=voidTile;
				break;
			case 0xFFFF8000:
				tilesInt[i]=lock;
				break;
			case 0xFF808080:
				tilesInt[i]=floor;
				break;
			case 0xFF000000:
				tilesInt[i]=wall;
				break;
			case 0xFF0000FF:
				tilesInt[i]=12;
				x1=iToX(i);
				y1=iToY(i);
				break;
			case 0xFF00FFFF:
				tilesInt[i]=13;
				x2=iToX(i);
				y2=iToY(i);
				break;
			}
	}
	
	public void died()
	{
		((LockTile)Tile.tiles[lock]).open();
	}

}
