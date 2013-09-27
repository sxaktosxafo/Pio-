package com.ias.chick;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.ias.chick.entity.Entity;
import com.ias.chick.entity.Portal;
import com.ias.chick.entity.item.LevelUp;
import com.ias.chick.entity.item.Scroll;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.entity.mob.PlayerSokoban;
import com.ias.chick.entity.mob.monster.Devil;
import com.ias.chick.entity.mob.monster.boss.BBat;
import com.ias.chick.entity.mob.monster.boss.BDevil;
import com.ias.chick.entity.mob.monster.boss.BJelly;
import com.ias.chick.entity.mob.monster.boss.Spirit;
import com.ias.chick.graphics.Image;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.SequenzaInizio;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.input.Keyboard;
import com.ias.chick.input.Mouse;
import com.ias.chick.level.ArenaLevel;
import com.ias.chick.level.BossLevel;
import com.ias.chick.level.CaveLevel;
import com.ias.chick.level.CurrentLevelSaver;
import com.ias.chick.level.GrassLevel;
import com.ias.chick.level.GraveyardLevel;
import com.ias.chick.level.HellLevel;
import com.ias.chick.level.ImageLevel;
import com.ias.chick.level.Level;
import com.ias.chick.level.MazeLevel;
import com.ias.chick.level.SokobanLevel;
import com.ias.chick.level.tile.Tile;
import com.ias.chick.sound.Sound;
import com.ias.chick.spell.Spell;

// Welcome to the source code of Pio!

public class Game extends Canvas implements Runnable
{
	public static Game game;
	
	private static final long serialVersionUID = 1L;
	
	public static int width = 400;
	public static int height = width / 16 * 9;
	public static int scale = 2;
	
	private Thread thread;
	public JFrame frame;
	public Keyboard key;
	public Player player;
	public Player players;	// player in sokoban
	private boolean running = false;
	
	private boolean imageDisplayed;	// is an image being displayed?
	public int imageCD;
	private int imageTime; 	// 30=1 sec

	private Level level;//=new Level(2,2,(byte) 127);
	
	public Level[] levels;	// array containing all the levels
	
	SequenzaInizio s;
	
	public boolean paused=false;
	private int pauseCD;

	public boolean[] keys=new boolean[]{false,false,false,false,false};	// keys to the various locktiles
	
	private int psx=10, psy=20;		//player spawn! (tiles) 10,20
	
	public Screen screen;
	
	private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);	// image being displyed every frame
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();		// array of integers that goes in image
	
	private Random random=new Random();
	
	private long time;
	
	// the frame containing the game is initialized
	public Game ()
	{
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		screen=new Screen(width,height);
		frame=new JFrame();
		
		key=new Keyboard();
		player=new Player(psx<<4,psy<<4,key);
		players=new PlayerSokoban(psx<<4,psy<<4,key);

		addKeyListener(key);
		
		Mouse mouse=new Mouse();
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		
		//s=new SequenzaInizio(key);
	}
	
	/** Creates all the levels and all the levels' monsters */
	@SuppressWarnings("unused")
	private void levelsInit()
	{
		
		levels=new Level[20];
		
		Level paradise=new ImageLevel("/levels/endUp.png",(byte)0);
		Level cathedral=new ImageLevel("/levels/cathedral.png",(byte)1);
		
		Level grass=new GrassLevel(20,25,(byte)2);
		Level bossJelly=new BossLevel("/levels/bossL.png", new BJelly(10,10), 0, 7, 16, 20,(byte)3);
		
		Level graveyard=new GraveyardLevel(64,48,10,(byte)4);
		
		Level cave=new CaveLevel(48,48,(byte)5);
		Level bossBat=new BossLevel("/levels/bossL.png", new BBat(10,10), Tile.caveFloor.ID, Tile.caveWall.ID, 28, Tile.caveWall.ID,(byte)6);
		
		Level preHell=new ImageLevel("/levels/switch.png",(byte) 7); 
		
		Level hell=new HellLevel(40,40,(byte) 8);
		Level endD=new ImageLevel("/levels/arenCool.png",(byte)9);
		
		Level sok1=new SokobanLevel("/levels/sok1.png",(byte)10);
		Level maze=new MazeLevel(10,10,5,24,(byte)11);
		
		Level arena=new ArenaLevel("/levels/ar.png",(byte)19);
		
		// non-random levels mobs/items
		cathedral.add(new Scroll(10<<4,10<<4,Spell.shoot));
		//cathedral.add(new Portal(11<<4,10<<4,Sprite.portalBlue,preHell));
		
		//hell.add(new Scroll(hell.x2()<<4,hell.y2()<<4,Spell.createMine));
		
		preHell.add(new Devil(15<<4,15<<4));
		preHell.add(new Portal(28<<4,13<<4,Sprite.portalBlue,sok1));
		preHell.add(new Portal(28<<4,17<<4,Sprite.portalBlue,maze));
		preHell.add(new Portal(28<<4,15<<4,Sprite.portalRed,3,hell));
		
		endD.add(new BDevil(20<<4, 16<<4));//29,16
		
		arena.add(new Scroll(2+psx<<4,2+psy<<4,Spell.shoot));
		
		//* levels are bound
		for (Level l: levels)
		{
			if(l==null)
				break;
			l.setLevels();
		}//*/
		
		// levels sound
		cathedral.setSound(null);
		paradise.setSound(Sound.win);
		endD.setSound(Sound.evil, true);
				
		// stairs
		endD.setStairs(Sprite.stairsUpC, Sprite.stairsDownC);
		cathedral.setStairs(Sprite.stairsUp, Sprite.stairsDownC);
		bossBat.setStairs(Sprite.stairsUpC, Sprite.stairsDownC);
		preHell.setStairs(Sprite.stairsUpC, null);
		
		// void tiles
		endD.voidTile=21;
		paradise.voidTile=20;
		preHell.voidTile=21;
		
		// set level
		setLevel(cathedral);
	}
	
	/** Returns the width of the window (used for mouse) */
	public static int getWW() { return width*scale; }
	
	/** Returns the height of the window (used for mouse) */
	public static int getWH() { return height*scale; }
	
	public synchronized void start ()
	{
		running = true;
		thread=new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop ()
	{
		running=false;
		try
		{ thread.join(); }
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}
	
	
	/** runs the game */
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer=System.currentTimeMillis();
		final double ns = 1000000000.0 / 30.0;	// 30=30 ups
		double delta=0;
		int frames=0;
		int updates=0;
		
		requestFocus();
		
		//s.mostra();
		
		// main game cycle
		while (running)
		{
			boolean shouldRender=false;
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			// update: 30 times in each second
			while (delta>=1)
			{
				update();	//updates!
				updates++;
				shouldRender=true;
				delta--;
			}
			
			// render: 30 or less times in each second
			if (shouldRender) {
				render(); //renders!
				frames++;
			}
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				frame.setTitle("Pio! | ups: "+updates+", fps: "+frames);
				updates=0;
				frames=0;
			}
		}
	}
	
	/** update: called 30 times per second */
	public void update()
	{	
		if (imageDisplayed)
			imageDisplayedThings();
		
		key.update();
		if (key.pause && pauseCD==0)
		{
			paused=!paused;
			pauseCD=5;
		}
		
		if (pauseCD>0)
			pauseCD--;
		if (!paused)
			level.update();
		
		if(key.f5)
			CurrentLevelSaver.levelSaver(getLevel());
	}
	
	/** Things that have to be done when an image is being displyed */
	private void imageDisplayedThings()
	{
		// if space is pressed or enough time has passed, display the image no more
		if (key.space)
		{
			imageCD=0;
			imageDisplayed=false;
			paused=false;
		}
		if(imageCD>=imageTime)
		{
			imageCD=0;
			imageDisplayed=false;
			paused=false;
		}	
		else
			imageCD++;
	}
	
	/** render: called 30 or less times per second (depending on the computer's ability) */
	public void render()
	{
		long a=System.nanoTime();
		
		BufferStrategy bs=getBufferStrategy();
		if (bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		
		// level rendering
		if (!imageDisplayed)
		{
			screen.clear();
			
			// it is rendered only the part of the level close to the player
			int xScroll,yScroll;
			
			if(level instanceof SokobanLevel)
			{
				xScroll=players.x-(screen.getWidth()>>1);
				yScroll=players.y-(screen.getHeight()>>1);
			} else {
				xScroll=player.x-(screen.getWidth()>>1);
				yScroll=player.y-(screen.getHeight()>>1);
			}
			
			level.render(xScroll+8, yScroll+8, screen);
			//level.render(xScroll+8+random.nextInt(3)-1, yScroll+8+random.nextInt(3)-1, screen); //shacking
			
			if (paused)
				Image.pause.render(screen);
		}
		
		if (player.dead)
			Image.die.render(screen);
		
		if (!imageDisplayed && !paused && !player.dead)
			player.renderGui(screen);
		
		// pixels becomes equal to pixels of 'screen'
		for(int i=0;i<pixels.length;i++)
			pixels[i]=screen.pixels[i];
			
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		
		/* Crosshair
		g.fillRect(Mouse.getX(), Mouse.getY()-20, 2, 40);
		g.fillRect(Mouse.getX()-20, Mouse.getY(), 40, 2);//*/	
		
		/* Center lines
		g.drawLine(getWW()/2, 0, getWW()/2, getWH());
		g.drawLine(0, getWH()/2, getWW(), getWH()/2);//*/
		
		g.dispose();
		bs.show();
		
	}
	
	/** Starts the game! */
	public static void main(String[] args)
	{
		game=new Game();
		game.levelsInit();
		game.frame.setResizable(false);
		game.frame.setTitle("Pio!");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
		game.running=true;
	}
	
	/** Displays the image i for t ticks, the game is paused in the meantime */
	public void displayImage(Image i, int t)
	{
		i.render(screen);
		imageDisplayed=true;
		imageTime=t;
		imageCD=0;
		paused=true;
	}
	
	/** Sets the level, and puts the player ad the stairs */
	public void setLevel(Level l, boolean up)
	{
		if(!(this.level==null))
			this.level.remove(player);
		this.level=l;
		level.addPlayer(player, up);
		player.init(level);
	}
	
	/** Changes the level, and puts the player at the default spawn x,y (psx, psy) */
	public void setLevel(Level l)
	{
		if(!(this.level==null))
			this.level.remove(player);
		this.level=l;
		level.addPlayer(player,psx,psy);
		player.init(level);
	}
	
	/** Changes the level, and puts the player at the given x, y */
	public void setLevel(Level l, int x, int y)
	{
		if(!(this.level==null))
			this.level.remove(player);
		this.level=l;
		level.addPlayer(player,x,y);
		player.init(level);
	}
	
	/** Returns the level in the id position, if it is empty, returns null */
	public Level getLevel(int id)
	{
		if (id<0 || id >levels.length-1)
			return null;
		return this.levels[id];
	}
	
	/** Returns the current level */
	public Level getLevel()
	{
		return this.level;
	}
	
	/** Adds the entity to the level */
	public void add(int level, Entity e)
	{
		levels[level].add(e);
	}
	
	/** render the level, changes happen in render() */
	public void stopGame()
	{
		int xScroll=player.x-screen.getWidth()/2;
		int yScroll=player.y-screen.getHeight()/2;
		
		level.render(xScroll, yScroll, screen);
	}
}