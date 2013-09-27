package com.ias.chick.graphics;

import java.util.Random;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.level.tile.Tile;

public class Screen
{
	private int width,height;
	public int[] pixels;
	public final int MAP_SIZE=64;
	public final int MAP_SIZE_MASK=MAP_SIZE-1;
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	public int xOffset,yOffset;
	
	private Random rand=new Random();
	
	public Screen(int w, int h)
	{
		this.height=h;
		this.width=w;
		pixels=new int[w*h];
		
		for (int i=0;i<MAP_SIZE*MAP_SIZE;i++)
			tiles[i]=rand.nextInt(0xffffff);
	}
	
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public void clear()
	{
		for (int i=0;i<pixels.length;i++)
			pixels[i]=0;
	}
	
	
	
	public void renderTile(int xp,int yp,Tile tile)
	{
		xp-=xOffset;
		yp-=yOffset;
		for (int y=0;y<tile.sprite.SIZE;y++)
		{
			int ya=yp+y;
			for (int x=0;x<tile.sprite.SIZE;x++)
			{
				int xa=xp+x;
				if (xa < -tile.sprite.SIZE || xa >= width || ya< 0 || ya >= height) break;
				if (xa<0) xa=0;
				pixels[xa+ya*width]=tile.sprite.pixels[x+y*tile.sprite.SIZE];
			}
		}
	}
	
	public void renderSprite(int xp,int yp,Sprite sprite)
	{
		xp-=xOffset;
		yp-=yOffset;
		for (int y=0;y<sprite.SIZE;y++)
		{
			int ya=yp+y;
			for (int x=0;x<sprite.SIZE;x++)
			{
				int xa=xp+x;
				if (xa < -sprite.SIZE || xa >= width || ya< 0 || ya >= height) break;
				if (xa<0) xa=0;
				int col=sprite.pixels[x+y*sprite.SIZE];
				if (col!=0xFFFF00FF) pixels[xa+ya*width]=col;
			}
		}
	}
	
	public void renderSprite(int xp,int yp,Sprite sprite, int color, boolean onScreen)
	{
		if (!onScreen) {
			xp-=xOffset;
			yp-=yOffset;
		}
		
		for (int y=0;y<sprite.SIZE;y++)
		{
			int ya=yp+y;
			for (int x=0;x<sprite.SIZE;x++)
			{
				int xa=xp+x;
				if (xa < -sprite.SIZE || xa >= width || ya< 0 || ya >= height) break;
				if (xa<0) xa=0;
				int col=sprite.pixels[x+y*sprite.SIZE];
				if (col!=0xFFFF00FF) pixels[xa+ya*width]=color;
			}
		}
	}
	
	public void renderSpriteOnScreen(int x,int y,Sprite sprite)
	{
		for (int yy=0;yy<sprite.SIZE;yy++)
		{
			for (int xx=0;xx<sprite.SIZE;xx++)
			{
				int col=sprite.pixels[xx+yy*sprite.SIZE];
				if (col!=0xFFFF00FF) pixels[x+xx+(y+yy)*width]=col;
			}
		}
	}
	
	public void renderPlayer(int xp,int yp,Sprite sprite,int flip)
	{
		xp-=xOffset;
		yp-=yOffset;
		for (int y=0;y<sprite.SIZE;y++)
		{
			int ya=yp+y;
			int ys=y;
			if (flip==2 || flip==3) ys=(sprite.SIZE-1)-y;
			for (int x=0;x<sprite.SIZE;x++)
			{
				int xa=xp+x;
				int xs=x;
				if (flip==1 || flip==3) xs=(sprite.SIZE-1)-x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa<0) xa=0;
				int col=sprite.pixels[xs+ys*sprite.SIZE];
				if (col!=0xFFFF00FF) pixels[xa+ya*width]=col;
			}
		}
	}
	
	/** Renders the player in the water (draws only the uper part) */
	public void renderPlayerW(int xp,int yp,Sprite sprite,int flip)
	{
		xp-=xOffset;
		yp-=yOffset;
		for (int y=0;y<sprite.SIZE/2;y++)
		{
			int ya=yp+y;
			int ys=y;
			if (flip==2 || flip==3) ys=(sprite.SIZE/2-1)-y;
			for (int x=0;x<sprite.SIZE;x++)
			{
				int xa=xp+x;
				int xs=x;
				if (flip==1 || flip==3) xs=(sprite.SIZE-1)-x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa<0) xa=0;
				int col=sprite.pixels[xs+ys*sprite.SIZE];
				if (col!=0xFFFF00FF) pixels[xa+ya*width]=col;
			}
		}
	}
	
	public void renderImage(Image im)
	{
		for (int x=0;x<im.WIDTH;x++)
		{
			for (int y=0;y<im.HEIGHT;y++)
			{
				int col=im.pixels[x+y*im.WIDTH];
				if (col!=0xFFFF00FF) this.pixels[x+y*width]=col;
			}
		}
	}
	
	public void renderString(String s, int x, int y, int color, boolean onScreen)
	{
		s.toLowerCase();
		for(int i=0;i<s.length();i++)
		{
			int letter=Character.getNumericValue(s.charAt(i));
			this.renderSprite(x+i*8, y, new Sprite(8, letter, SpriteSheet.letters),color, onScreen);
		}
	}
	
	/**
	 * Renders a bar (like health bar)
	 * @param x the top left x
	 * @param y the top left y
	 * @param w	the width
	 * @param max the height (max health)
	 * @param n	the full part (current health)
	 * @param colb the color of the borders
	 * @param col the color of the full part
	 */
	public void renderBar(int x, int y, int w, int max, int n, int colb, int col)
	{
		for (int yy=y;yy<=y+max;yy++)
			for (int xx=x;xx<=x+w;xx++)
			{
				if(yy==y||yy==max+y||yy-y==max-n)
					pixels[xx+yy*width]=colb;
				else if(yy-y>max-n)
					pixels[xx+yy*width]=col;
			}
	}
	
	public void renderPixel(int x, int y, int col)
	{
		x-=xOffset;
		y-=yOffset;
		if(x<0 || y<0 || x>=width || y>=height) return;
		this.pixels[x+y*width]=col;
	}
	
	public void setOffset(int xO,int yO)
	{
		this.xOffset=xO;
		this.yOffset=yO;
	}
}
