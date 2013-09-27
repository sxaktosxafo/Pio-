package com.ias.chick.entity.mob.monster.boss;

import com.ias.chick.Game;
import com.ias.chick.entity.item.BluePot;
import com.ias.chick.entity.item.RedPot;
import com.ias.chick.entity.item.Scroll;
import com.ias.chick.entity.mob.Mob;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.ArenaLevel;
import com.ias.chick.level.BossLevel;
import com.ias.chick.sound.Sound;

public abstract class Boss extends Mob
{
	protected Sprite sw1,sw2,sa;	// sprite walk and attack
	
	protected short tick;
	protected int damage;
	
	Scroll scroll;
	
	protected Player player=Game.game.player;
	
	protected boolean attacking=false;
	
	public void update()
	{
		if(tick<255)
			tick++;
		else
			tick=0;
		
		if (this.collides(player) && player.vulnerable())
			player.hit(damage);
		
		if (this.hp<=0)
			die();
		
		int xa=player.x-x-(size>>2);
		int ya=player.y-y-(size>>2);
		
		int xd=0;
		int yd=0;
		
		if (xa<0) {
			if (xa>-speed) xd=-1;	// prevent the monster from jumping up and down 2 pixels trying to reach the one between them
			else xd=(int)-speed;
		}
		if (xa>0) {
			if (xa<speed) xd=1;
			else xd=(int)speed;
		}
		if (ya<0) {
			if (ya>-speed) yd=-1;
			else yd=(int)-speed;
		}
		if (ya>0) {
			if (ya<speed) yd=1;
			else yd=(int)speed;
		}

		
		if(tick==64)
			attack();
		if(tick==94)
			finishAttack();
		
		move(xd,yd);
	}
	
	protected void attack()
	{
		attacking=true;
		this.speed=0;
	}
	
	protected void finishAttack()
	{
		attacking=false;
		this.speed=1;
	}
	public void render(Screen screen)
	{
		if (attacking)
			sprite=sa;
		else
			if (tick%32>=16)
				sprite=sw1;
			else
				sprite=sw2;
		screen.renderSprite(x, y, sprite);
		screen.renderBar(385, 22, 8, maxHp, hp, 0x000000, 0x808080);
	}
	
	protected void die()
	{
		super.die();
		Sound.muore.play();
		if(level.getClass().equals(ArenaLevel.class))
			((ArenaLevel) level).monsterDied(this);
		else if(level.getClass().equals(BossLevel.class))
			((BossLevel) level).died();
		level.add(new RedPot(x+16, y, Sprite.redPotB, 25));
		level.add(new BluePot(x+16, y+16, Sprite.bluePotB, 50));
	}
}