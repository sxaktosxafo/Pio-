package com.ias.chick.spell;

import com.ias.chick.entity.mob.Player;
import com.ias.chick.graphics.Sprite;
import com.ias.chick.level.Level;

public class Speed extends Spell
{

	public Speed(int cost, Sprite s, int id)
	{
		super(cost, s, (byte) id);
	}
	
	public void cast(int x,int y,Level level,final Player p)
	{
		p.setSpeed(7);
		new Thread(){
			public void run()
			{
				try {
					this.sleep(3000);
					p.setSpeed(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
