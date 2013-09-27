package com.ias.chick.entity.item;

import com.ias.chick.Game;
import com.ias.chick.entity.Entity;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.sound.Sound;

public class Item extends Entity
{
	private Sprite sprite;
	
	public Item(int x, int y, Sprite s)
	{
		this.x=x;
		this.y=y;
		this.sprite=s;
		this.size=sprite.SIZE;
	}
	
	public void update()
	{
		if (this.collides(Game.game.player) || this.collides(Game.game.players))
			touched(Game.game.player);
	}
	
	public void render(Screen screen)
	{
		screen.renderSprite(x, y, sprite);
	}
	
	public void touched(Player p)
	{
		Sound.pickUp.play();
		level.remove(this);
	}
}
