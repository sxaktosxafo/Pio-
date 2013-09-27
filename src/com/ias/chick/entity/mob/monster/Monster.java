package com.ias.chick.entity.mob.monster;

import java.util.Random;

import com.ias.chick.Game;
import com.ias.chick.entity.item.BluePot;
import com.ias.chick.entity.item.RedPot;
import com.ias.chick.entity.mob.Mob;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.sound.Sound;

public abstract class Monster extends Mob
{
	protected Sprite s1,s2;
	
	int cd=120;
	int scd=0; //sprite countdown
	int xd,yd;
	int damage;
	
	Random random=new Random();
	
	public void render(Screen s)
	{
		if (scd%10<=5) sprite=s1;
		else sprite=s2;
		
		s.renderSprite(x, y, sprite);
	}
	
	public void update()
	{
		if (this.collides(Game.game.player))
			Game.game.player.hit(damage);
		
		if (this.hp<=0)
		{
			die();
		}
		
		if (scd < 750000) scd++;
		else scd=0;
		
		int xa=Game.game.player.x-x;
		int ya=Game.game.player.y-y;
		
		xd=0;
		yd=0;
		
		if (xa*xa+ya*ya<200*200)
		{
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
			
		}
		
		move(xd,yd);
	}
	
	protected void die()
	{
		if (random.nextBoolean())
			level.add(new RedPot(x,y,Sprite.redPot,10));
		else
			level.add(new BluePot(x,y,Sprite.bluePot,10));
		Sound.dead.play();
		super.die();
	}
}
