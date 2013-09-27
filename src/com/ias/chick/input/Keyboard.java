package com.ias.chick.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{

	private boolean[] keys=new boolean[200];
	public boolean up,down,left,right;
	public boolean r,l,shift,space,f5;
	public boolean pause;
	
	
	public void update()
	{
		up=keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down=keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left=keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right=keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		r=keys[KeyEvent.VK_R];	// used once to switch through levels
		pause=keys[KeyEvent.VK_P] || keys[KeyEvent.VK_ESCAPE];
		l=keys[KeyEvent.VK_L];
		shift=keys[KeyEvent.VK_SHIFT];
		space=keys[KeyEvent.VK_SPACE];
		f5=keys[KeyEvent.VK_F5];
	}
	
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()]=true;
	}

	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()]=false;
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

}
