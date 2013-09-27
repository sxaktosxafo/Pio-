package com.ias.chick.level;

import java.util.Random;

public class RandomLevel extends Level
{
	
	private static final Random random=new Random();

	public RandomLevel(int w, int h,byte id)
	{
		super(w, h,id);
		generateLevel();
	}
	
	protected void generateLevel()
	{
		for (int y=0;y<height;y++)
		{
			for (int x=0;x<getWidth();x++)
			{
				tilesInt[x+y*getWidth()] = random.nextInt(3)+4;
			}
		}
	}
	
}
