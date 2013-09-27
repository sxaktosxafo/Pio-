package com.ias.chick.entity.mob;

import com.ias.chick.Game;
import com.ias.chick.graphics.Screen;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.input.Keyboard;

public class PlayerSokoban extends Player
{
	
	private boolean canMove;
	private int moveCD;
	private int flip=0;

	public PlayerSokoban(int x, int y, Keyboard i) {
		super(x, y, i);
		this.sprite=Sprite.player_back;
	}
	
	public void update() {
		
		byte xm=0,ym=0;
		
		if(stCD>0)
			stCD--;
		
		if(canMove) {
			if(input.up) {
				ym=-16;
				sprite=Sprite.player_forward;
			}
			else if(input.down) {
				ym=16;
				sprite=Sprite.player_back;
			}
			else if(input.left) {
				xm=-16;
				sprite=Sprite.player_side;
				flip=1;
			}
			else if(input.right){
				xm=16;
				sprite=Sprite.player_side;
				flip=0;
			}
			else return;
			move(xm,ym);
		}
		else 
			if (moveCD<=0) canMove=true;
			else moveCD--;
			
		//System.out.println("x: "+x+", y: "+y);
		
	}
	
	public void move(int xm, int ym) {
		canMove=false;
		moveCD=5;
		
		
		
		xm=xm>>4;
		ym=ym>>4;
		x=x>>4;
		y=y>>4;
		
		switch (level.getTile(x+xm, y+ym).ID)
		{
		case 7:	// wall || hole
		case 27:
			xm=0;
			ym=0;
			break;
		case 26:		// rock
			switch(level.getTile(x+xm+xm, y+ym+ym).ID) {
			case 26:
			case 7:
				xm=0;
				ym=0;
				break;
			case 27:
				level.setTile(x+xm+xm, y+ym+ym, 25);
				level.setTile(x+xm, y+ym, 25);
				break;
			case 25:
				level.setTile(x+xm+xm, y+ym+ym, 26);
				level.setTile(x+xm, y+ym, 25);
				break;
			}
			break;
		case 13:
			if (stCD==0)
			{
				Game.game.setLevel(level.levDown, false);
				stCD=10;
				Game.game.player.stCD=10;
			}
			break;
		case 12:
			if (stCD==0)
			{
				Game.game.setLevel(level.levUp, true);
				Game.game.player.stCD=10;
				stCD=10;
			}
			break;
		}
		
		
		
		x=x<<4;
		y=y<<4;
		x+=(xm<<4);
		y+=(ym<<4);
		
	}
	
	public void render(Screen screen)
	{
		screen.renderPlayer(x, y, sprite, flip);
	}
}
