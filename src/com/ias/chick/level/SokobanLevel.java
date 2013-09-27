package com.ias.chick.level;

import com.ias.chick.Game;
import com.ias.chick.entity.item.Emblem;
import com.ias.chick.graphics.Sprite;

public class SokobanLevel extends Level
{

	public SokobanLevel(String path, byte id) {
		super(path, id);
		this.voidTile=7;
		this.tilesInt=colorToInt(tilesInt);
		this.setStairs(Sprite.stairsUpS, Sprite.stairsDownS);
	}
	
	protected int[] colorToInt(int[] a)
	{
		for (int i=0; i<a.length;i++)
			switch(a[i])
			{
			case 0xFF000000:
				a[i]=7;
				break;
			case 0xFFFFFFFF:
				a[i]=25;
				break;
			case 0xFF808080:
				a[i]=27;
				break;
			case 0xFF800000:
				a[i]=26;
				break;
			case 0xFF0000FF:
				a[i]=25;
				x1=iToX(i);
				y1=iToY(i);
				break;
			case 0xFF00FFFF:
				a[i]=25;
				x2=iToX(i);
				y2=iToY(i);
				this.add(new Emblem(x2<<4,y2<<4));
				break;
			default:
				a[i]=0;
				break;
			}
		
		return a;
	}
	
	public void enter()
	{
		this.remove(Game.game.player);
		if(!visited) this.add(Game.game.players);
		Game.game.players.x=Game.game.player.x;
		Game.game.players.y=Game.game.player.y;
		Game.game.players.setstCD(10);
		visited=true;
	}
}
