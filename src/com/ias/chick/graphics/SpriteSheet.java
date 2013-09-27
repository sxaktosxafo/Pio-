package com.ias.chick.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/sheets/spritesheet.png",256);
	public static SpriteSheet mobs = new SpriteSheet("/sheets/mobs.png",256);
	public static SpriteSheet mobss = new SpriteSheet("/sheets/mobss.png",96);
	public static SpriteSheet items = new SpriteSheet("/sheets/items.png",64);
	public static SpriteSheet weapons = new SpriteSheet("/sheets/weapons.png",40);
	public static SpriteSheet spells = new SpriteSheet("/sheets/spells.png",80);
	public static SpriteSheet boss = new SpriteSheet("/sheets/BOSS.png",192);
	public static SpriteSheet letters = new SpriteSheet("/sheets/letters.png",80);
	
	public SpriteSheet(String path,int size)
	{
		this.path=path;
		this.SIZE=size;
		pixels=new int[SIZE*SIZE];
		load();
	}
	
	private void load()
	{
		try
		{
			BufferedImage image=ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) { e.printStackTrace(); }
	}
}
