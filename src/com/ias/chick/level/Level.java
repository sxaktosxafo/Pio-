package com.ias.chick.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.ias.chick.Game;
import com.ias.chick.entity.*;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.tile.Tile;
import com.ias.chick.sound.Sound;

public class Level
{
	protected int width;	// in Tiles
	protected int height;
	protected int[] tilesInt;
	
	public int counter;
	
	public int voidTile;
	
	public Level levUp;
	public Level levDown;
	
	protected Sprite stairsUp;
	protected Sprite stairsDown;
	
	private boolean mustLoop; // when the level has a sound that mus be looped
	
	protected Sound sound; 
	
	protected boolean visited=false;
	
	// Stairs up
	protected int x1,y1;
	
	//Stairs down
	protected int x2,y2;
	
	public byte id;
	
	public Vector<Entity> entities=new Vector<Entity>();
	//public Vector<Entity> pr=new Vector<Entity>();
	
	public Level(int w,int h,byte id)
	{
		this.width=w;
		this.height=h;
		tilesInt=new int[w*h];
		this.voidTile=17;
		this.stairsDown=Sprite.stairsDown;
		this.stairsUp=Sprite.stairsUp;
		this.sound=Sound.powUp;
		this.id=id;
		Game.game.levels[id]=this;
		//System.out.println(Game.game.levels[id].id);
	}
	
	public Level(String path,byte id)
	{
		loadLevel(path);
		generateLevel();
		this.voidTile=17;
		this.stairsDown=Sprite.stairsDown;
		this.stairsUp=Sprite.stairsUp;
		this.sound=Sound.powUp;
		this.id=id;
		Game.game.levels[id]=this;
		//System.out.println(Game.game.levels[id].id);
	}
	
	public void setSound(Sound s)
	{
		this.sound=s;
	}
	
	public void setSound(Sound s,boolean b)
	{
		this.sound=s;
		this.mustLoop=b;
	}
	
	public void setLevels(Level u, Level d)
	{
		this.levUp=u;
		this.levDown=d;
	}
	
	public void setLevels(int u, int d)
	{
		this.levUp=Game.game.getLevel(u);
		this.levDown=Game.game.getLevel(d);
	}
	
	public void setLevels()
	{
		this.levUp=Game.game.getLevel(this.id-1);
		this.levDown=Game.game.getLevel(this.id+1);
	}
	
	protected void generateLevel()
	{
		
	}
	
	protected void loadLevel(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(ImageLevel.class.getResource(path));
			int w=width=image.getWidth();
			int h=height=image.getHeight();
			tilesInt=new int[w*h];
			image.getRGB(0, 0, w, h, tilesInt, 0, w);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("nooooo!");
		}
	}
	
	public void update()
	{
		counter++;
		//Game.game.player.update();
		for (int i=0; i<entities.size();i++)
			entities.get(i).update();
		if(counter>=90)
			counter=0;
	}
	
	public void time()
	{
		
	}
	
	public void render(int xScroll,int yScroll,Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		// xScroll / 16. 16  la larghezza del tile
		int x0 = xScroll >> 4;
		int x1 = (xScroll+screen.getWidth()+16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll+screen.getHeight()+16) >> 4;
		
		for (int y=y0;y<y1;y++)
		{
			for (int x=x0;x<x1;x++)
			{
				if(getTile(x,y).equals(Tile.stairsUp))
					screen.renderSprite(x<<4, y<<4, stairsUp);
				else if(getTile(x,y).equals(Tile.stairsDown))
					screen.renderSprite(x<<4, y<<4, stairsDown);
				else getTile(x,y).render(x, y, screen);
			}
		}
		
		for (int i=entities.size()-1; i>=0;i--)
			entities.get(i).render(screen);
	}
	
	protected int iToX(int i)
	{
		while(i>=getWidth())
			i-=getWidth();
		return i;
	}
	
	protected int iToY(int i)
	{
		int c=0;
		while(i>=getWidth())
		{
			i-=getWidth();
			c++;
		}
		return c;
	}
	
	// vedi tiles.txt
	/** Returns the tile */
	public Tile getTile(int x,int y)
	{
		if(x<0||y<0||x>=width||y>=height) return Tile.tiles[voidTile];
		return Tile.tiles[tilesInt[x+y*width]];
	}
	
	/** Returns the tile */
	public Tile getTile(int pos)
	{
		if(pos<0||pos>=getLenght()) return Tile.tiles[voidTile];
		return Tile.tiles[tilesInt[pos]];
	}
	
	public void setStairs(Sprite su,Sprite sd)
	{
		this.stairsUp=su;
		this.stairsDown=sd;
	}
	
	public void add(Entity e)
	{
		e.init(this);
		this.entities.add(e);
	}
	
	public void remove(Entity e)
	{
		this.entities.remove(e);
	}
	
	public void addPlayer(Player p, boolean up)
	{
		//this.entities.add(p);
		if (up)
		{
			p.x=(x2<<4);
			p.y=(y2<<4);
		}
		else
		{
			p.x=(x1<<4);
			p.y=(y1<<4);
		}
		this.add(p);
		enter();
	}
	
	public void addPlayer(Player p, int x,int y)
	{
		p.x=(x<<4);
		p.y=(y<<4);
		this.add(p);
		enter();
	}
	
	public int x1() { return this.x1; }
	public int x2() { return this.x2; }
	public int y1() { return this.y1; }
	public int y2() { return this.y2; }
	
	/** Things done when the level is entered */
	public void enter()
	{
		if(!visited)
		{
			if (mustLoop)
			{
				sound.playLoop();
				visited=true;
				return;
			}
			
			try
			{
				sound.play();
			} catch (Exception e)
			{
				System.out.println("Manca il suono (potrebbe essere voluto)");
			}
			visited=true;
		}
	}
	
	public void setTile(int x, int y,int id)
	{
		this.tilesInt[x+y*width]=id;
	}
	
	public void setTile(int pos,int id)
	{
		this.tilesInt[pos]=id;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getLenght() {
		return tilesInt.length;
	}
}
