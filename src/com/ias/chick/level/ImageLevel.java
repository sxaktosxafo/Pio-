package com.ias.chick.level;

public class ImageLevel extends Level
{
	public ImageLevel(String path,byte id)
	{
		super(path,id);
		this.voidTile=0;
		tilesInt=colorToInt(tilesInt);
	}

	protected void generateLevel()
	{
		
	}
	
	// vedi tiles.txt
	protected int[] colorToInt(int[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			switch (a[i])
			{
			case 0xFF00FF00: a[i]=0; break;
			case 0xFFFF0000: a[i]=2; break;
			case 0xFF408080: a[i]=4; break;
			case 0xFF400000: a[i]=5; break;
			case 0xFF808080: a[i]=6; break;
			case 0xFFFFFF00: a[i]=1; break;
			case 0xFF000000: a[i]=9; break;
			case 0xFFCC3300: a[i]=11; break;
			case 0xFF000064: a[i]=16; break;
			case 0xFFFF8000: a[i]=14; break;
			case 0xFF0080FF: a[i]=20; break;
			case 0xFF800000: a[i]=21; break;
			case 0xFFFF4000: a[i]=22; break;
			case 0xFF202020: a[i]=7; break;
			case 0xFF0000FF:
				a[i]=12;
				x1=iToX(i);
				y1=iToY(i);
				break;
			case 0xFF00FFFF:
				a[i]=13;
				x2=iToX(i);
				y2=iToY(i);
				break;
			}
		}
		return a;
	}
	
	
}