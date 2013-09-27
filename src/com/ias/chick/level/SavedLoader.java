package com.ias.chick.level;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.ias.chick.Game;
import com.ias.chick.entity.EntitySaveLoad;
import com.ias.chick.entity.mob.Player;
import com.ias.chick.level.tile.Tile;



public class SavedLoader
{
	private static int read;
	private static final int terminal=0x3B;
	public static int playerX;
	public static int playerY;
	public static int width;
	public static int height;
	public static byte id;
	public static byte levId;
	private static int playerMana;
	private static int playerHp;
	private static int spellSize;
	private static int spell;
	private static String errore= "Non c'è nessuna partita salvata: il gioco ricomincia dall'inizio!";
	private static String error= "No saves: game begins from the start";
	
	static ImageIcon icon=new ImageIcon(SavedLoader.class.getResource("/images/error.png"));
	
	private static InputStream file;
	
	public SavedLoader(){}
	
	/** Loads the current level and the player's x, y, hp, mana, keys, spells 
	 *  Calls the function that loads the previous ones
	 */
	public static void loadLevel(String path, Level[] levels) {

		try {

			file = SavedLoader.class.getResourceAsStream(path);

			width = file.read();
			height = file.read();

			if (width == 0 || height == 0)
				JOptionPane.showMessageDialog(null, errore, "Pio!",JOptionPane.WARNING_MESSAGE,icon);
			else {

				id = (byte) file.read();
				levels[id].x1 = file.read();
				levels[id].y1 = file.read();
				levels[id].x2 = file.read();
				levels[id].y2 = file.read();
				playerX = (file.read() << 4);
				playerY = (file.read() << 4);
				playerMana = file.read();
				playerHp = file.read();

				// KEYS
				for (int i = 0; i < Game.game.keys.length; i++) {
					int key = file.read();
					if (key == 0xAF)
						Game.game.keys[i] = true;
					else
						Game.game.keys[i] = false;
				}

				if (Game.game.keys[0])
					Tile.blueLock.open();
				if (Game.game.keys[1])
					Tile.redLock.open();

				// SPELLS
				spellSize = file.read();

				for (int i = 0; i < spellSize; i++)
					//Game.game.player.spells.add((byte) file.read());
					Game.game.player.learnN((byte)file.read());

				if ((read = file.read()) != terminal)
					System.out.println("Errore nel file!");

				System.out.println("level ID: " + id);
				System.out.println("width: " + width + " height: " + height);

				levels[id].tilesInt = new int[width * height];

				/*
				 * for (int y = 0; y < height; y++) { for (int x = 0; x < width;
				 * x++) levels[id].tilesInt[x + y * width] = file.read();
				 */

				for (int hu = 0; hu < levels[id].tilesInt.length; hu++)
					levels[id].tilesInt[hu] = file.read();

				levels[id].setLevels();
				//levels[id].entities.clear();

				refreshLevels(levels, id);
				
				Game.game.setLevel(levels[id]);
				Game.game.player.x = playerX;
				Game.game.player.y = playerY;
				Game.game.player.mana = playerMana;
				Game.game.player.hp = playerHp;

				file.close();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, error, "Pio!",JOptionPane.WARNING_MESSAGE,icon);
			System.out.println(e);
		}
	}
	
	protected void generateLevel()
	{
		
	}

  
	private static void refreshLevels(Level[] levels, byte id){
		
		id=(byte) (id-1);
		
		for(int i=id; i>0; i--){
			
			String savePath="/levels/"+i+".lev";
			file = SavedLoader.class.getResourceAsStream(savePath);
			
			System.out.println(savePath);	
			
			try{
				
				width=file.read();
				height=file.read();
				
				if(file.read() != i)
					System.out.println("Level matching error!!");
				
				levels[i].x1=file.read();
				levels[i].y1=file.read();
				levels[i].x2=file.read();
				levels[i].y2=file.read();
					
				if((read=file.read()) != terminal)
					System.out.println("Errore nel file!");
				
				levels[i].tilesInt=new int[width*height];

				for(int y=0; y<height; y++){
					for(int x=0; x<width; x++)			 
						levels[i].tilesInt[x+y*width]=file.read();
				}
				
				levels[i].setLevels();
				
				file.close();
				
			}catch(Exception e){System.out.println(e);}
			
		}
		
		EntitySaveLoad.loadEntities();
		
	}
	
}