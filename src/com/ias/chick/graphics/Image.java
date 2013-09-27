package com.ias.chick.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image
{
	private String path;
	public final int WIDTH;
	public final int HEIGHT;
	public int[] pixels;
	
	public static Image die=new Image("/images/die.png",400,225);
	//public static Image start=new Image("/images/start.png",400,225);
	public static Image pause=new Image("/images/pause.png",87,16);
	public static Image newSpell=new Image("/images/newSpell.png",400,225);
	
	public Image(String path,int w, int h)
	{
		this.WIDTH=w;
		this.HEIGHT=h;
		this.path=path;
		pixels=new int[WIDTH*HEIGHT];
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
	
	public void render(Screen screen)
	{
		screen.renderImage(this);
	}
}
