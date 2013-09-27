package com.ias.chick.entity.mob;

import java.util.ArrayList;

import com.ias.chick.Game;
import com.ias.chick.entity.Entity;
import com.ias.chick.entity.Particle;
import com.ias.chick.entity.weapons.Projectile;
import com.ias.chick.graphics.Image;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.input.Keyboard;
import com.ias.chick.input.Mouse;
import com.ias.chick.level.CurrentLevelSaver;
import com.ias.chick.level.tile.Tile;
import com.ias.chick.sound.Sound;
import com.ias.chick.spell.Spell;

public class Player extends Mob
{
	public boolean dead=false;
	
	protected Keyboard input;
	private int anim=0;
	private boolean walking;
	protected int stCD; //stairs countdown
	
	private boolean canShoot;
	private int canShootCD=10;
	
	private boolean canBeak;
	private int canBeakCD=0;
	private int beekTime;
	private int beakDam;
	private int beakDir;
	
	private int beakDist;
	
	public int mana;
	public int maxMana;
	private boolean canCast;
	private int canCastCD=0;
	public ArrayList<Byte> spells= new ArrayList<Byte>();
	private int spellsIndex=0;
	private boolean canSwitch;
	private int canSwitchCD=0;
	
	protected boolean vulnerable=true;
	protected int invulnTicks=15;
	protected int vulnerableCD=invulnTicks;
	
	private byte tick=0;
	
	public Player(Keyboard i)
	{
		this.input=i;
		sprite=Sprite.player_forward;
		stInitThings();
	}
	
	public Player(int x,int y, Keyboard i)
	{
		this.x=x;
		this.y=y;
		this.input=i;
		sprite=Sprite.player_forward;
		stInitThings();
	}
	
	private void stInitThings()
	{
		this.speed=3;
		this.maxHp=50;
		this.hp=maxHp;
		this.maxMana=100;
		this.mana=maxMana;
		beakDam=1;
		invulnTicks=30;
		vulnerableCD=invulnTicks;
		this.beakDist=15;
		this.beekTime=15;
		
		this.spells.add(Spell.heal.id);
	}
	
	/*
	private void shoot(double dir)
	{
		Projectile pr=new Projectile(x,y,dir,this);
		level.add(pr);
	}
	 */
	
	public void update()
	{
		if(tick<127)
			tick++;
		else
			tick=0;
		
// heath stuff (die and regeneration)
		if (this.hp<=0)
			die();
		else if(hp<maxHp*5/12)
			if(tick%16==0 && vulnerable)
				this.hp++;
			
// invulnerability after damage
		if (!vulnerable)
		{
			this.vulnerableCD--;
			if (this.vulnerableCD<=0)
				this.vulnerable=true;
		}
		
// animation		
		int xa=0,ya=0;
		if(anim < 7500) anim++;
		else anim=0;
		
// spells
		
		// casting count down
		if (!canCast) {
			canCastCD--;
			if (canCastCD<=0) {
				canCast=true;
			}
		}
		
		// casting & regenerating
		if (canCast)
		{
			if (Mouse.getB()==1)
			{
				canCastCD=cast(spells.get(spellsIndex));
				canCast=false;
			}
			
			if (mana<=maxMana>>2)
			{
				if(tick%32==0)
					mana++;
			}
			else if (mana<=maxMana>>1)
			{
				if(tick%16==0)
					mana++;
			}
			else if (mana<=(maxMana>>2)*3)
			{
				if(tick%8==0)
					mana++;
			}
			else if (mana<maxMana)
			{
				if(tick%4==0)
					mana++;
			}
		}
		
		// switching spell
		if (!canSwitch) {
			canSwitchCD--;
			if (canSwitchCD<=0) {
				canSwitch=true;
			}
		}
		
		if ((Mouse.getB()==3 || input.shift) && canSwitch)
		{
			if (spellsIndex<spells.size()-1)
				spellsIndex++;
			else
				spellsIndex=0;
			
			canSwitch=false;
			canSwitchCD=10;
		}
		
// lava
		if (level.getTile((x+8)>>4, (y+8)>>4)==Tile.lava && vulnerable())
			this.hp-=1;
		
		
// stairs
		if (level.getTile((x+8)>>4, (y+8)>>4)==Tile.stairsDown)
		{
			if (stCD==0)
				Game.game.setLevel(level.levDown, false); stCD=10;
		}

		if (level.getTile(x>>4, y>>4)==Tile.stairsUp)
		{
			if (stCD==0)
				Game.game.setLevel(level.levUp, true); stCD=10;
		}
		
		else if (stCD>0) stCD--;
		
// moving stuff
		if (input.up)    {ya=-1; ydir=-1;}
		if (input.down)  {ya=1;  ydir=1;}
		if (input.left)  {xa=-1; xdir=-1;}
		if (input.right) {xa=1;  xdir=1;}
		
		boolean b=true;
		
		// moves
		if(xa != 0)
		{
			move(xa*speed,0);
			walking=true;
			b=false;
		}
		
		// if it isn't exactly on a tile, continues until it reaches one
		// (this should make passing through 1-tile-wide corridors easier) 
		else if (x%16!=0)
		{
			move(xdir,0);
			walking=true;
			b=false;
		}
		
		// same as above, for y
		if(ya != 0)
		{
			move(0,ya*speed);
			walking=true;
			b=false;
		}
		else if (y%16!=0)
		{
			move(0,ydir);
			walking=true;
			b=false;
		}
		
		// at the start b=T, every walking thing makes b false
		if (b)
		{
			walking=false;
			ydir=0;
			xdir=0;
		}

// beaking stuff
		if (!canBeak) {
			canBeakCD--;
			if (canBeakCD<=0) {
				canBeak=true;

			}
		}
		
		if (input.space && canBeak)
		{
			for (Entity e: level.entities)
			{
				if (e.equals(this) || !(e instanceof Mob))
					continue;
				
				boolean xok=false,yok=false;

				xok=(x+size>=e.x-beakDist && x<=e.x+e.size+beakDist);
				yok=(y+size>=e.y-beakDist && y<=e.y+e.size+beakDist);
				
				if(xok && yok)
				{
					((Mob) e).hp-=beakDam;
					
					Sound.beak.play();
					
					if(y<e.y) beakDir=2;
					if(y>e.y+e.size) beakDir=0;
					if(x<e.x) beakDir=1;
					if(x>e.x+e.size) beakDir=3;
					
					canBeakCD=beekTime;
					canBeak=false;
					break;
				}
			}
		}
	}
	
	//  0
	// 3 1
	//  2
	public void render(Screen screen)
	{
		int flip=0;
		switch(dir)
		{
		case 0:
			sprite=Sprite.player_forward;
			if (walking)
			{
				if(anim%10<5)
					sprite=Sprite.player_forward_1;
			}
			break;
		case 1:
			sprite=Sprite.player_side;
			if (walking)
			{
				if(anim%10<5)
					sprite=Sprite.player_side_1;
			}
			break;
		case 2:
			sprite=Sprite.player_back;
			if (walking)
			{
				if(anim%10<5)
					sprite=Sprite.player_back_1;
			}
			break;
		case 3:
			sprite=Sprite.player_side;
			if (walking)
			{
				if(anim%10<5)
					sprite=Sprite.player_side_1;
			}
			flip=1;
			break;
		}
		
		if (canBeakCD>beekTime-10)
		{
			flip=0;
			
			switch (beakDir)
			{
			case 0:
				sprite=Sprite.player_forwardA;
				break;
			case 1:
				sprite=Sprite.player_sideA;
				break;
			case 2:
				sprite=Sprite.player_backA;
				break;
			case 3:
				sprite=Sprite.player_sideA;
				flip=1;
				break;
			}
		}
		
		if(level.getTile((x+8)>>4, (y+8)>>4) == Tile.water || level.getTile((x+8)>>4, (y+8)>>4) == Tile.lava)
			screen.renderPlayerW(x, y, sprite, flip);
		else
			screen.renderPlayer(x, y, sprite, flip);
		
		
	}
	
	public boolean vulnerable() {
		return this.vulnerable;
	}
	
	/** the mob is invulnerable for ticks ticks */
	public void invulnerable(int ticks)
	{
		this.vulnerable=false;
		this.vulnerableCD=ticks;
	}
	
	/** the mob is invulnerable for invulnTicks ticks */
	public void invulnerable()
	{
		this.vulnerable=false;
		this.vulnerableCD=invulnTicks;		// invulnerability ticks
	}
	
	/** Renders the current spell and health and mana bars*/
	public void renderGui(Screen screen)
	{
		screen.renderBar(5, 22, 6, maxHp, hp, 0xC00000, 0xFF6600);
		screen.renderBar(14, 22, 6, maxMana>>1, mana>>1, 0x0000C0, 0x0066FF);
		
		screen.renderSpriteOnScreen(5,3, Spell.getSpell(this.spells.get(spellsIndex)).sprite);
		
	}
	
	/** Tries to cast a spell, if there isn't enough mana, returns 0, else returns 30: spellCountDowns */
	private int cast (Byte spellId)
	{
		if (this.mana<Spell.getSpell(spellId).cost)
			return 0;
		else
		{
			// gives as x and y the distance from the center of the screen (the player)
			Spell.getSpell(spellId).cast(Mouse.getX()/2-Game.getWW()/4+8,Mouse.getY()/2-Game.getWH()/4+8,this.level,this);
			this.mana-=Spell.getSpell(spellId).cost;
			return 30;
		}
	}
	
	/** Learns a new spell */
	public void learn(byte s)
	{
		if(!spells.contains(s))
		{
			spells.add(s);
			Game.game.displayImage(Image.newSpell, 30*5);
		}
	}
	
	/** Learn a spell without displaying an image */
	public void learnN(byte s)
	{
		if(!spells.contains(s))
			spells.add(s);
	}
	
	/** Adds to the current mana m points (unless it is full) */
	public void restoreMana(int m)
	{
		while (m>0)
		{
			if(mana==maxMana)
				return;
			mana++;
			m--;
		}
	}
	
	public void levelUp() {
		this.maxMana+=50;
		this.maxHp+=25;
		this.hp=maxHp;
		this.mana=maxMana;
	}
	
	/** The player is dealt dam damage */
	public void hit(int dam)
	{
		if (vulnerable()) {
			super.hit(dam);
			invulnerable();
			Sound.hit.play();
		}
	}
	
	/** Die: deletes the saves and calls stopGame */
	protected void die()
	{
		if (!dead)
		{
			Sound.muore.play();
			this.dead=true;
			super.die();
			Game.game.stopGame();
			CurrentLevelSaver.cancelSaved();
		}
	}
	
	public void setstCD(int t)
	{
		this.stCD=t;
	}
	
	public void setSpeed(int s)
	{
		this.speed=s;
	}
	
	/** Returns the current spell as a string */
	public String spell()
	{
		return this.spells.get(spellsIndex).toString();
	}
}
