package com.ias.chick.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound
{
	public static Sound dead=new Sound("/Sounds/expl.wav");
	public static Sound powUp=new Sound("/Sounds/powerup.wav");
	
	public static Sound muore=new Sound("/Sounds/morte.wav");
	public static Sound hit=new Sound("/Sounds/colpito.wav");
	public static Sound beak=new Sound("/Sounds/beccata.wav");
	public static Sound pickUp=new Sound("/Sounds/pickup.wav");
	
	public static Sound explosion=new Sound("/Sounds/explosion.wav");
	
	public static Sound exp=new Sound("/Sounds/expl.wav");
	
	public static Sound win=new Sound("/Sounds/pio_final.wav");
	
	public static Sound evil=new Sound("/Sounds/evil.wav");
	
	public static Sound[] story=new Sound[]
	{
		new Sound("/Sounds/story/pio1.wav"),
		new Sound("/Sounds/story/pio2.wav"),
		new Sound("/Sounds/story/pio3.wav"),
		new Sound("/Sounds/story/pio4.wav"),
		new Sound("/Sounds/story/pio5.wav"),
	};
	
	private AudioClip clip;
	
	public Sound (String path)
	{
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(path));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void playLoop()
	{
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {;
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
